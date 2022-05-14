package Util;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

public class DataParser {
 public static User getUser(int ID) throws SQLException {
  User usr = new User(ID);
    String db = Constant.url;
  String user = Constant.DBUserName;
  String pwd = Constant.DBUserName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pwd);
            String sql = "SELECT * FROM users u WHERE u.userID ="+ID+";";
            String sql2= "SELECT * FROM Projects p, users_has_groups ug WHERE p.groupID = ug.groups_groupID AND ug.users_userID ="+ID+";";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
           if(rs.next()) {
	         usr.setUserName(rs.getString("name"));
	         usr.setPassword(rs.getString("password"));
	         usr.setUserEmail(rs.getString("email"));
	         rs = s.executeQuery(sql2);
	         ArrayList<String> p = new ArrayList<String>();
	         ArrayList<Integer> pID = new ArrayList<Integer>();
	         while(rs.next()) {
	          pID.add(rs.getInt("groupID"));
	          p.add(rs.getString("title"));
	         }
	         usr.setProjects(p);
	         usr.setProjId(pID);
           }
           else {
        	   return null;
           }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        return usr;
 }
 
 public static ArrayList<User> getAllUser() throws SQLException{
  ArrayList<User> alluser = new ArrayList<User>();
     String db = Constant.url;
  String user = Constant.DBUserName;
  String pwd = Constant.DBUserName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pwd);
            String sql = "SELECT * FROM users u;";
            ArrayList<Integer> userIDs = new ArrayList<Integer>();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()) {
             User usr = new User(rs.getInt("userID"));
             usr.setUserName(rs.getString("name"));
          usr.setPassword(rs.getString("password"));
          usr.setUserEmail(rs.getString("email"));
          alluser.add(usr);
             
            }
            for(User u :alluser) {
             String sql2= "SELECT * FROM Projects p, users_has_groups ug WHERE p.groupID = ug.groups_groupID AND ug.users_userID ="+u.getId()+";";
             ArrayList<String> p = new ArrayList<String>();
             ArrayList<Integer> pID = new ArrayList<Integer>();
             rs = s.executeQuery(sql2);
             while(rs.next()) {
              pID.add(rs.getInt("groupID"));
              p.add(rs.getString("title"));
             }
             u.setProjects(p);
             u.setProjId(pID);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        return alluser;
  
 }
 
 public static Project getProject(int ID) throws SQLException {
  Project p = new Project();
  String db = Constant.url;
  String user = Constant.DBUserName;
  String pwd = Constant.DBUserName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db, user, pwd);
            String sql = "SELECT * FROM Projects p WHERE p.groupID ="+ID+";";
            String sql2 = "SELECT * FROM users u, users_has_groups ug WHERE ug.groups_groupID ="+ID+" AND u.userID = ug.users_userID;";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            p.setId(ID);
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            ArrayList<String> members = new ArrayList<String>();
            rs = s.executeQuery(sql2);
            while(rs.next()){
            	members.add(rs.getString("email"));
            }
            p.setMembers(members);
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        return p;
 }
 
 public static TodoList getTodo(int ID) throws SQLException{
	 TodoList todo = new TodoList();
	 String db = Constant.url;
	  String user = Constant.DBUserName;
	  String pwd = Constant.DBUserName;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(db, user, pwd);
	            String sql = "SELECT * FROM tasks t WHERE t.tasks_groupID ="+ID+";";
	            Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            while(rs.next()) {
		            Task ta = new Task();
		            ta.setId(rs.getInt("taskID"));
		            ta.setDueDate(rs.getString("taskDueDate"));
		            ta.setTaskName(rs.getString("taskInfo"));
		            todo.addTask(ta);
	            }
	            rs.close();
	            conn.close();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        //TODO return business based on id
	 
	 return todo;
	 
 }
 
 public static void removeTask(int projectID, String taskName, String DueDate) throws SQLException{
	 String db = Constant.url;
	  String user = Constant.DBUserName;
	  String pwd = Constant.DBUserName;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(db, user, pwd);
	            String sql = "DELETE FROM tasks t WHERE t.tasks_groupID = "+projectID + " AND t.taskInfo = '"+taskName+"' AND t.taskDueDate = '"+DueDate+"';";
	            Statement s = conn.createStatement();
	            s.execute(sql);
	            conn.close();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
 }
 
 public static JsonObject calenderTask(int groupId) {
	 Gson gs = new GsonBuilder().create();	 
	 String db = Constant.url;
	  String user = Constant.DBUserName;
	  String pwd = Constant.DBUserName;
	  TodoList todo = new TodoList();
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(db, user, pwd);
	            String sql = "SELECT * FROM tasks t WHERE t.tasks_groupID = "+groupId+";";
	            Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            while(rs.next()) {
		            Task ta = new Task();
		            ta.setId(rs.getInt("taskID"));
		            ta.setDueDate(rs.getString("taskDueDate"));
		            ta.setTaskName(rs.getString("taskInfo"));
		            todo.addTask(ta);
	            }
	            conn.close();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  JsonElement jsonElement = gs.toJsonTree(todo);
	  JsonObject jsonObject = (JsonObject) jsonElement;     
	  return jsonObject;
 }
 
 
}
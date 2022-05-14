
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.Serial;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;

import Util.Constant;
import Util.DataParser;
import Util.User;
@WebServlet("/createproject")

public class createproject extends HttpServlet {
    @Serial
    //private static final long serialVersionUID = 1L;
    private static String title;
    private static String[] members;
    private static String[] addmembers;
    private static String member;
    private static String email;
    private static String descript;
    private static String error = "Invalid title, member, or discription. Please try again.";
    private static boolean memberexist;
    private static int num;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		title = request.getParameter("groupTitle");
		addmembers = request.getParameterValues("memberEmail");
		
		//String[] members=member.split(",");
		descript = request.getParameter("groupDescription");
		Cookie cookie = null;
		User theuser=null;
		DataParser dp=new DataParser();
		Cookie[] cookies = null;
		cookies = request.getCookies();
		int cookieexist=0;
		String userid="";
		if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			   cookie = cookies[i];
			   if(cookie.getName().equals("userid")){
				    userid=cookie.getValue();
				    try {
						theuser= dp.getUser(Integer.valueOf(userid));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    if(theuser!=null){
				    	email=theuser.getUseremail();

				    }
				    
			   }
			  } 
		}
		if(addmembers==null || addmembers[0]=="") {
			members=new String[1];
			members[0]=email;
		}
		else {
			members=new String[addmembers.length+1];
			for(int i=0;i<addmembers.length;i++) {
				members[i]=addmembers[i];
			}
			members[members.length-1]=email;
		}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	member="";
    	memberexist=true;
    	num=0;
        doGet(request, response);
        Connection conn = null;
        String db = Constant.url;
        String user = Constant.DBUserName;
		String pwd = Constant.DBPassword;
		String sql = "INSERT INTO Projects (title, description) VALUES (?, ?)";
		//String sql4 = "INSERT INTO users_has_groups (users_userID, groups_groupID) VALUES (?, ?)";
		String sql2 = "SELECT COUNT(email) AS total FROM users WHERE email=?";
		String sql3="SELECT userID FROM users WHERE email=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(db, user, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(int i=0;i<members.length;i++) {
			String email=members[i];
			try (PreparedStatement pst2 = conn.prepareStatement(sql2);) {
				pst2.setString(1, email);
				ResultSet rs1 = pst2.executeQuery();
				rs1.next();
				if(rs1.getInt("total")==0) {
					error="did not found the user with email "+email;
					memberexist=false;
				}
					}catch (SQLException ex){
						System.out.println ("SQLException: " + ex.getMessage());
						}
			member+=members[i];
			if(i!=members.length-1) {
				member+=", ";
			}
		}
		if(!memberexist) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("newProject.jsp").include(request, response);
		}
		else{
			if (title.contentEquals("")||member.contentEquals("")||descript.contentEquals("")){
				error="empty input";
				request.setAttribute("error", error);
				request.getRequestDispatcher("newProject.jsp").include(request, response);
			}
			else {
	            Statement st;
				
				try (PreparedStatement thesql = conn.prepareStatement(sql);PreparedStatement pst3 = conn.prepareStatement(sql3);) {
	    			thesql.setString(1, title);
	    			thesql.setString(2, descript);
	    			thesql.execute();
						st = conn.createStatement();
			            String myStatement = "select groupID from Projects where title='"+title+"' AND description='"+descript+"';";
			            ResultSet rs = st.executeQuery(myStatement);
		            while(rs.next()){
		                num = (rs.getInt("groupID"));
		            }
				String repeat_mem = "";    
				    
				for(int i=0;i<members.length;i++) {
					String email=members[i];
					if(!repeat_mem.contains(email)) {
							repeat_mem+=email;
							repeat_mem+= ",";
							pst3.setString(1, email);
							ResultSet rs2 = pst3.executeQuery();
							rs2.next();
							int userid=rs2.getInt("userID");
							st = conn.createStatement();
				            String sql4 = "INSERT INTO users_has_groups (users_userID, groups_groupID) VALUES ("+Integer.toString(userid)+","+Integer.toString(num)+")";
				            PreparedStatement pst4 = conn.prepareStatement(sql4);
				            pst4.execute();
						}
					}
			    response.sendRedirect("enterproject？projectid="+num);
				}
			    catch (SQLException ex){
					System.out.println ("SQLException: " + ex.getMessage());
					}
				}
		}
		
    }
}

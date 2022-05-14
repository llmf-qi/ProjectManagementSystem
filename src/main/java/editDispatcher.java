
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
import Util.*;
@WebServlet("/editDispatcher")

public class editDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Serial
    private static String title;
    private static String[] members;
    private static String member;
    private static String descript;
    private static String error = "Invalid title, member, or discription. Please try again.";
    private static boolean memberexist;
    private static boolean already;
    private static int projectid;
    private static ArrayList<String> project_members;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Project p;
    	response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		title = request.getParameter("groupTitle");
		members = request.getParameterValues("memberEmail");
		//String[] members=member.split(",");
		descript = request.getParameter("groupDescription");
		String aid;
		projectid=1;
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		int cookieexist=0;
		String userid="";
		if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			   cookie = cookies[i];
			   if(cookie.getName().equals("projectid")){
				    aid=cookie.getValue();
				    projectid=Integer.valueOf(aid);
			   }
			} 
		}
		try {
			p = DataParser.getProject(projectid);
			project_members = p.getMemeber();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    	already = false;
        doGet(request, response);
        Connection conn = null;
        String db = Constant.url;
        String user = Constant.DBUserName;
		String pwd = Constant.DBPassword;
		String sql = "UPDATE Projects SET title=?, description=? WHERE groupID=?";
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
		if(members!=null) {
			//System.out.println("member input!");
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
				already = false;
				for(String s:project_members) {
					if(email.equals(s))
					{
						already = true;
						break;
					}
				}
				if(!already) {
					member+=members[i];
					if(i!=members.length-1) {
						member+=", ";
					}
				}
			}
			if(memberexist&&!already) {
				//System.out.println("member exist!");
				try (PreparedStatement thesql = conn.prepareStatement(sql);PreparedStatement pst3 = conn.prepareStatement(sql3);) {
					Statement st;
					st = conn.createStatement();
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
					            String sql4 = "INSERT INTO users_has_groups (users_userID, groups_groupID) VALUES ("+Integer.toString(userid)+","+Integer.toString(projectid)+")";
					            PreparedStatement pst4 = conn.prepareStatement(sql4);
					            pst4.execute();
							}
					}
				}
				catch (SQLException ex){
					System.out.println ("SQLException: " + ex.getMessage());
					}
			}
			}
		if ((!memberexist)||title.contentEquals("")||descript.contentEquals("")){
			error="empty input";
			request.setAttribute("error", error); 
			request.getRequestDispatcher("editInfo.jsp").include(request, response);
		}
		else {
			//System.out.println("you are here!");
			try (PreparedStatement thesql = conn.prepareStatement(sql);) {
    			thesql.setString(1, title);
    			thesql.setString(2, descript);
    			thesql.setInt(3, projectid);
    			thesql.execute();
    			response.sendRedirect("groupInfo.jsp？projectid="+projectid);
			}
		    catch (SQLException ex){
				System.out.println ("SQLException: " + ex.getMessage());
				}
			}
		
    }
}

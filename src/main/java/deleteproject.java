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
@WebServlet("/deleteproject")

public class deleteproject extends HttpServlet {
    @Serial
    //private static final long serialVersionUID = 1L;
    private static int num;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
    	String aid;
    	int projectid=1;
    	int userid=1;
        
    	Cookie cookie = null;
    	Cookie[] cookies = null;
    	cookies = request.getCookies();
    	int cookieexist=0;
    	if (cookies != null) {
    	for (int i = 0; i < cookies.length; i++) {
    		   cookie = cookies[i];
    		   
    		   if(cookie.getName().equals("projectid")){
    			    aid=cookie.getValue();
    			    projectid=Integer.valueOf(aid);
    		   }
    		   if(cookie.getName().equals("userid")){
   			    aid=cookie.getValue();
   			    userid=Integer.valueOf(aid);
   		   }
    		 
    		} 
    	}
    	Connection conn = null;
        String db = Constant.url;
        String user = Constant.DBUserName;
		String pwd = Constant.DBPassword;
		//String sql = "DELETE FROM Projects where groupID=?";
		String sql2 = "DELETE FROM users_has_groups where groups_groupID = ? AND users_userID=?";
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
		try (PreparedStatement pst2 = conn.prepareStatement(sql2);) {
			pst2.setInt(1, projectid);
			pst2.setInt(2, userid);
			pst2.execute();
			//System.out.println("quit the group "+projectid+" "+userid);
				}catch (SQLException ex){
					System.out.println ("SQLException: " + ex.getMessage());
					}
		cookie = null;
 	   cookies = null;
 	   cookies = request.getCookies();
 	   if( cookies != null ){
 	      for (int i = 0; i < cookies.length; i++){
 	         cookie = cookies[i];
 	         if((cookie.getName( )).compareTo("projectid") == 0 ){
 	            cookie.setMaxAge(0);
 	            response.addCookie(cookie);
 	         }
 	         
 	      }
 	   }
 		response.sendRedirect("index.jsp");
		
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        
    }
}

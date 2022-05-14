import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.Serial;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import Util.*;
@WebServlet("/TodoDispatcher")

public class TodoDispatcher extends HttpServlet {
	 @Serial
	    //private static final long serialVersionUID = 1L;
	    private static int groupID;
	    private static String taskName;
	    private static String DueDate;
	    private static String error = "Invalid title, member, or discription. Please try again.";
	    
	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	     * response)
	     */
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	response.setContentType("text/html");
			//PrintWriter out = response.getWriter();
			taskName = request.getParameter("taskName");
			DueDate = request.getParameter("taskDueDate");
			System.out.println(taskName);
			System.out.println("467");
			System.out.println(DueDate);
			DataParser dp=new DataParser();
			Project p;
			Boolean isLogin = false;
			User theuser=null;
			String username="";
			String email="";
			String aid;
			int projectid=1;
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
			groupID=projectid;
	    }

	    /**
	     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	     * response)
	     */
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	        String db = Constant.url;
	        String user = Constant.DBUserName;
			String pwd = Constant.DBPassword;
			String sql = "INSERT INTO tasks (tasks_groupID, taskInfo, taskDueDate) VALUES (?, ?, ?)";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(db, user, pwd);
	            PreparedStatement s = conn.prepareStatement(sql);
	            s.setInt(1, groupID);
	            s.setString(2, taskName);
	            s.setString(3, DueDate);
	            System.out.println(s);
	            s.execute();
	            conn.close();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("todoList.jsp?projectid="+groupID);
	    }
}


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
import Util.Project;
import Util.User;
@WebServlet("/removeTodoDispatcher")

public class removeTodoDispatcher extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Serial
	private static int groupID;
	private static String[] tasks;
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
		tasks = request.getParameterValues("deletedTask");
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
        if(tasks!=null) {
        	
	        try {
	        	for(String s: tasks) {
	        		String[] task = s.split(",,., ");
	        		System.out.println(task);
	        		String taskName = task[0];	        		
	        	    String DueDate = task[1]; 
	        		DataParser.removeTask(groupID, taskName, DueDate);
	        	}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		response.sendRedirect("todoList.jsp?projectid="+groupID);
    }
}

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
@WebServlet("/enterproject")

public class enterproject extends HttpServlet {
    @Serial
    //private static final long serialVersionUID = 1L;
    private static String projectid;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
       Cookie cookie = null;
 	   Cookie[] cookies = null;
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
		projectid= request.getParameter("projectid");
		Cookie ck=new Cookie("projectid",projectid); 
	    response.addCookie(ck);
	    response.sendRedirect("groupInfo.jsp");
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

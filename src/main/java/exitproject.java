import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
@WebServlet("/exitproject")
public class exitproject extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
    		response.sendRedirect("index.jsp");

    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
        
    }

}
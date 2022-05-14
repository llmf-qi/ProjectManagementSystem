import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.Serial;
import java.sql.*;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;

import Util.Constant;  
@WebServlet("/LoginDispatcher")

public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static String email;
    private static String psw;
    private static String error = "Invalid email or password. Or, bad Google login. Please try again.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
		email = request.getParameter("email");
		psw = request.getParameter("psw");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        Connection conn = null;
        String db = Constant.url;
        String user = Constant.DBUserName;
		String pwd = Constant.DBPassword;
		String sql = "SELECT * FROM users WHERE email="+""
				+ "'"+email+"'";

		if (email.contentEquals("")||psw.contentEquals("")){
			request.setAttribute("loginError", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);
		}
		else {
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
			try (PreparedStatement pst = conn.prepareStatement(sql);) {
				Statement st = conn.createStatement();
				  ResultSet rs = st.executeQuery(sql);
				  int found=0;
				  while (rs.next()) {
						if(psw.equals(rs.getString("password"))) {
							request.setAttribute("userid", rs.getInt("userID"));
							int userid=rs.getInt("userID");
							//userid=userid.replace(" ", "=");
							Cookie ck=new Cookie("userid",String.valueOf(userid));//creating cookie object  
						    response.addCookie(ck);
							found+=1;
							break;
						}
				  }
				  if(found==0) {
					    request.setAttribute("loginError", error);
						request.getRequestDispatcher("auth.jsp").include(request, response);
				  }
				  else {
					  response.sendRedirect("index.jsp");
				  }
				  
			  }catch (SQLException ex){
						System.out.println ("SQLException: " + ex.getMessage());
					}
		}
    }
}

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Constant;
import Util.Helper;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@WebServlet("/GoogleLoginDispatcher")

public class GoogleLoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static String email;
    private static String name;
    private static String psw;
    private static String error = "Can't do Google login with a registered email.";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        email = request.getParameter("email");
		name = request.getParameter("name");
		psw = request.getParameter("password");
		int exist=0;
        Connection conn = null;
        String db = Constant.url;
        String user = Constant.DBUserName;
      	String pwd = Constant.DBPassword;
		String sql = "INSERT INTO users (name, password, email) VALUES (?, ?, ?)";
		String sql2 = "SELECT COUNT(email) AS total FROM users WHERE email=?";
		String sql3 = "SELECT * FROM users WHERE email=?";
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
			pst2.setString(1, email);
			ResultSet rs1 = pst2.executeQuery();
			rs1.next();
			if(rs1.getInt("total")>0) {
				exist=1;
			}
			else {
				exist=0;
			}
				}catch (SQLException ex){
					System.out.println ("SQLException: " + ex.getMessage());
					}

		if (exist==1) {
			String sql4 = "SELECT * FROM users WHERE email="+""
					+ "'"+email+"'";
			Statement st;
			try {
				st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql4);
				while(rs.next()) {
					String userpwd=rs.getString("password");
					if (userpwd != null) {
						request.setAttribute("loginError", error);
						request.getRequestDispatcher("auth.jsp").include(request, response);
					} else {
						int userid=rs.getInt("userID");
						Cookie ck=new Cookie("userid",String.valueOf(userid));
					    response.addCookie(ck);
					    response.sendRedirect("index.jsp");
					    break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	    else {
	    	int userid = 0;
	    	try (PreparedStatement pst = conn.prepareStatement(sql);PreparedStatement pst3 = conn.prepareStatement(sql3);) {
	    			pst.setString(1, name);
	    			pst.setString(2, psw);
	    			pst.setString(3, email);
	    			pst.execute();
	    			pst3.setString(1, email);
					ResultSet rs3 = pst3.executeQuery();
					rs3.next();
					userid=rs3.getInt("userID");
	    				}catch (SQLException ex){
	    					System.out.println ("SQLException: " + ex.getMessage());
	    					}
			Cookie ck=new Cookie("userid",String.valueOf(userid)); 
		    response.addCookie(ck);
		    response.sendRedirect("index.jsp");
	    }
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

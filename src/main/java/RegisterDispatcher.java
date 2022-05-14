import javax.servlet.ServletException;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Constant;
import Util.Helper;

import java.io.IOException;
import java.io.Serial;
@WebServlet("/RegisterDispatcher")
/**
 * Servlet implementation class RegisterDispatcher
 */
public class RegisterDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static String email;
    private static String usrname;
    private static String psw;
    private static String cpsw;
    private static String error="";
    private static String agree;
    private static int exist=0;
    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
   
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
		email = request.getParameter("registerEmail");
		usrname=request.getParameter("registerName");
		psw = request.getParameter("registerPassword");
		cpsw=request.getParameter("confirmPassword");
    }	

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
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
		Helper ahelper=new Helper();
		if (email.contentEquals("")) {
			error = "Register email missing";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);}
		else if (!ahelper.isValidEmail(email)) {
			error = "Invalid email format";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);}
		else if (exist==1) {
			error = "Register email already existed";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);}
		else if (usrname.contentEquals("")) {
			error = "Register username missing";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);}
		else if (!ahelper.validName(usrname)) {
			error = "Invalid username format";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);}
		else if (psw.contentEquals("")) {
			error = "Register password missing";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);}
		else if (!psw.contentEquals(cpsw)) {
			error = "Register password does not match confirm password";
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);
	    }
	    else {
	    	int userid = 0;
	    	try (PreparedStatement pst = conn.prepareStatement(sql);PreparedStatement pst3 = conn.prepareStatement(sql3);) {
	    			pst.setString(1, usrname);
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

}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css"> -->
<link href="http://fonts.cdnfonts.com/css/handjet-2" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/7f10118ced.js" crossorigin="anonymous"></script>
<%
	Boolean isLogin = false;
	List<String> projectList = new ArrayList<String> ();
	List<Integer> projectidList = new ArrayList<Integer> ();
	String username="";
	String email="";
 	/* projectList.add("project 1");
	projectList.add("project 2");
	projectList.add("project 3");
	projectList.add("project 4");  */
	//Boolean isProjectEmpty = true;
%>
<%@ page import="Util.User"%>
<%@ page import="Util.Project"%>
<%@ page import="Util.DataParser"%>
<% 			
			DataParser dp=new DataParser();
			Cookie cookie = null;
			User theuser=null;
    		Cookie[] cookies = null;
    		cookies = request.getCookies();
    		int cookieexist=0;
    		String userid="";
			if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				   cookie = cookies[i];
				   if(cookie.getName().equals("userid")){
					    userid=cookie.getValue();
					    theuser= dp.getUser(Integer.valueOf(userid));
					    if(theuser!=null){
					    	email=theuser.getUseremail();
						    username=theuser.getUsername();
						    projectList=theuser.getProjects();
						    projectidList=theuser.getProjId();
					   		isLogin=true;
					    }
					    
				   }
				  } 
			}%>             
</head>
<body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script> -->
	
	<div class="container-fluid">
    <div class="row flex-nowrap">
    	<!-- sidebar -->
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0" id = "sidebar">
            <div class="d-flex flex-column align-items-center pt-2 text-white min-vh-100" id="sidebarContent">
            	<div class="sidebar-header">
                <a href="exitproject" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="d-none d-sm-inline">PM SYSTEM</span>
                </a>
                </div>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                    <li class="nav-item">
                    	<%
                    	if (!isLogin) {
                    		out.println("<a href=\"auth.jsp\" class=\"nav-link align-middle px-0\"><i class=\"fa-solid fa-users\"></i> <span class=\"ms-1 d-none d-sm-inline\">Login/Register</span></a>");
                    	} else {
                    		out.println("<a href=\"LogoutDispatcher\" class=\"nav-link align-middle px-0\"><i class=\"fa-solid fa-right-from-bracket\"></i> <span class=\"ms-1 d-none d-sm-inline\">Logout</span></a>");
                    	}
                    	%>
                        
                    </li>
                    
                </ul>
                
                <%
                if (isLogin) {
                	out.println("<div class=\"userInfo\"> <table id=\"user\">");
                	out.println("<tr><th id=\"icon\"><i class=\"fa-solid fa-user\"></i></th></tr>");
                	out.println("<tr><th id=\"name\">"+username+"</th></tr>");
                	out.println("<tr><th id = \"email\">"+email+"</th></tr>");
                	out.println("</table></div>");
                }
                %>                                                
            </div>
        </div>
        <!-- sidebar end -->
        
        <!-- content -->
        <div class="col py-3">
            <div class = "division top"> 
            	<p class = "title">Create a new project</p>
            	<%
            	if (isLogin) {
            		out.println("<button id=\"addButton\" type=\"button\" onclick=\"location.href='newProject.jsp'\"><i class=\"fa-solid fa-circle-plus\"></i></button>");
            	} else {
            		out.println("<button id=\"addButton\" type=\"button\" onclick=\"location.href='auth.jsp'\"><i class=\"fa-solid fa-circle-plus\"></i></button>");
            	}
            	%>
            	
            </div>
            
            <div class = "division bottom"> 
            	<p class = "title">Your Group Project</p>	
            	<%
            	if (projectList.isEmpty()) {
            		out.println("<p id=\"emptyMessage\">No groups yet... Try to create a new group!</p>");
            	} else {
            		out.println("<div class=\"container-fluid\"><div class = \"row\">");
            		for (int i = 0; i< projectList.size(); i++) {
            			out.println("<div class=\"col\"><button id=\"addButton\" type=\"submit\" onclick=\"location.href='enterproject?projectid="+projectidList.get(i)+"'\">" + projectList.get(i) + "</button> </div>");
            		}
            		out.println("</div></div>");
            	}
            	%>
            </div>
        </div>
        <!-- content end -->
        
      </div>
     </div>

</body>
</html>
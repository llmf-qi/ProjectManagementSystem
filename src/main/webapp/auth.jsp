<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login/Register</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">

<link href="http://fonts.cdnfonts.com/css/handjet-2" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/7f10118ced.js" crossorigin="anonymous"></script>
<script type="module" src="index.js"></script>

<style type="text/css">
.customGPlusSignIn {
	  height: 40px;
	  width: 400px;
      background: #4c8bf5;
      border-radius: 5px;
      color: white;
      display: grid;
      grid-template-columns:17px auto;
      justify-content: center;
      align-items: center;
      font-size: 15px;
}
.customGPlusSignIn:hover {
      cursor: pointer;
}
</style>
<%Boolean isLogin = false; %>             
</head>
<body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
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
                	out.println("<tr><th id=\"name\">Tommy Trojan</th></tr>");
                	out.println("<tr><th id = \"email\">trojan@usc.edu</th></tr>");
                	out.println("</table></div>");
                	
                }
                %>
                
                
                
            </div>
        </div>
        <!-- sidebar end -->
        
        <!-- content -->
        <div class="col py-3">
        
            <div class = "division top">
            
            	<form action="RegisterDispatcher" method="POST" id = "signup-form">
            	<div class = "container">
            	<div class = "row">
            		<div class = "col col-md-auto">
            		<p class = "title">Register</p>
            		</div>
            		<div class = "col">
            		<% String er = (String) request.getAttribute("error");
            		if (er != null) { %>
            		<div class="alert alert-danger" role="alert"><%out.println(er); %></div>
            		<% }%>
            		</div>
            	</div>
            	</div>
            	
            	<div class = "container-fluid">
            		<div class = "row row-cols-2">
            			<div class = "col">
            			<label class = "info">Email</label> <br>
    					<input class = "userInput" type="text" name="registerEmail">
            			</div>
            			<div class = "col">
            			<label class = "info">Name</label> <br>
    					<input class = "userInput" type="text" name="registerName">
            			</div> 
            			<div class = "col">
            			<label class = "info">Password</label> <br>
    					<input class = "userInput" type="password" name="registerPassword">
            			</div>
            			<div class = "col">
            			<label class = "info">Confirm Password</label> <br>
    					<input class = "userInput" type="password" name="confirmPassword">
            			</div>
            			<div class = "col">
            			</div>
            			<div class = "col">
            			<button type="submit" class = "accountButton"> <i class="fa-solid fa-user-plus"></i> Create Account </button>
            			</div>   
            		</div>
            		
            	</div>
            	</form>
            </div>
            
            <div class = "division bottom">
            	<form action="LoginDispatcher" method="POST" id="login-form">
            	<div class = "container">
            	<div class = "row">
            		<div class = "col col-md-auto">
            		<p class = "title">Login</p>
            		</div>
            		<div class = "col">
            		<% String e = (String) request.getAttribute("loginError");
            		if (e != null) { %>
            		<div class="alert alert-danger" role="alert"><%out.println(e); %></div>
            		<% }%>
            		</div>
            	</div>
            	</div>  
            	
            	<div class = "container-fluid">
            		<div class = "row row-cols-2 align-items-center">
            			<div class = "col">
            			<label class = "info">Email</label> <br>
    					<input class = "userInput" type="text" name="email">
            			</div>
            			
            			<div class = "col">
            			<label class = "info">Password</label> <br>
    					<input class = "userInput" type="password" name="psw">
            			</div>
            			<div class = "col">
            			<div id="gSignInWrapper">
    					<div id="googleLogin" class="customGPlusSignIn"> <i class="fa-brands fa-google"></i> Sign in with Google</div>
    					</div>
    					<div id="name"></div>
    					
            			</div>
            			<div class = "col">
            			<button type="submit" class = "accountButton"> <i class="fa-solid fa-user-plus"></i> Sign in </button>
            			</div>
            		</div>
            		
            	</div>	
            	</form>
            </div>
        </div>
        <!-- content end -->
        
      </div>
     </div>

</body>

</html>
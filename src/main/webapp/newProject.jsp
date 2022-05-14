<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Create a new project</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css"> -->
<link href="http://fonts.cdnfonts.com/css/handjet-2" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/7f10118ced.js" crossorigin="anonymous"></script>
<%
	Boolean isLogin = true;
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
<script>

function add(){
	var newdiv = document.createElement('div');
	newdiv.id = "emailInput"
    newdiv.innerHTML = "<input class = 'memberInput' type='text' name='memberEmail'><input type='button' id = 'minusBtn' value='-' onClick='removeInput(this);'>";
    document.getElementById('addMember').appendChild(newdiv);
}
function removeInput(btn){
    btn.parentNode.remove();
}
</script>             
</head>
<body>
	<% String er = (String) request.getAttribute("error");
	if (er != null) { %>
		<h1 style="background-color:pink;padding: 40px 0;margin-bottom:0;text-align: center;font-size:14px;"><%out.println(er); %></h1>
	<% }%>
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
                    		out.println("<a href=\"index.jsp\" class=\"nav-link align-middle px-0\"><i class=\"fa-solid fa-right-from-bracket\"></i> <span class=\"ms-1 d-none d-sm-inline\">Logout</span></a>");
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
        
		<form id="groupForm" action="createproject" method="post"> <!-- submit title and description -->
            <div class = "division"> 
            	<p class = "title">Create a new project</p>
            	<div class="container infoPart">
            		<div class="row">
            			<div class="col-2"><label class = "info">Title</label></div>
    					<div class="col-7"><input class = "groupInput" type="text" name="groupTitle"></div>
   		 				<div class="col"></div>
            		</div>
            		<div class="row">
            		<!-- get multiple input with the same name: https://stackoverflow.com/a/41508004 -->
            			<div class="col-2"><label class = "info">Member</label></div>
            			<div class="col-7" id = "addMember"><div id="emailInput"><input class = "groupInput" type="text" name="memberEmail"></div></div>
            			<div class="col"><input type="button" onclick="add()" id="addMemberBtn" value = "+"></div>
            		</div>
            		<div class="row">
            			<div class="col-2"><label class = "info">Description</label></div>
    					<div class="col-7"><textarea name="groupDescription" rows="10" cols="30" id="userText"></textarea></div>
   		 				<div class="col"></div>
            		</div>
            		<div class = "row">
            			<div class="col-2"></div>
            			<div class="col-7"><button type="submit" id = "createButton">Create</button></div>
            			<div class="col"></div>
            		</div>
            	</div>
            </div>
            </form>
        </div>
        <!-- content end -->
        
      </div>
     </div>

</body>
</html>
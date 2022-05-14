<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<link href="http://fonts.cdnfonts.com/css/handjet-2" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/7f10118ced.js" crossorigin="anonymous"></script>
<%Boolean isLogin = true; %>             
</head>
<body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
	
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
                        <a href="groupInfo.jsp" class="nav-link align-middle px-0">
                           <i class="fa-solid fa-circle-info"></i> <span class="ms-1 d-none d-sm-inline">Group Info</span>
                        </a>
                    </li>
                    <li>
                        <a href="calendar.jsp" class="nav-link px-0 align-middle">
                            <i class="fa-solid fa-calendar"></i> <span class="ms-1 d-none d-sm-inline">Calendar</span></a>
                    </li>
                    <li>
                        <a href="todoList.jsp" data-bs-toggle="collapse" class="nav-link px-0 align-middle ">
                            <i class="fa-solid fa-list"></i> <span class="ms-1 d-none d-sm-inline">To-do List</span></a>
                    </li>
                    <li>
                        <a href="index.jsp" class="nav-link px-0 align-middle">
                            <i class="fa-solid fa-right-from-bracket"></i> <span class="ms-1 d-none d-sm-inline">Logout</span> </a>
                    </li>
                </ul>
                <hr>
                <div class="userInfo">
                    <table id="user">
                    	<tr>
                    		<th id="icon"><i class="fa-solid fa-user"></i></th>
                    	</tr>
                    	<tr>
                    		<th id="name">Tommy Trojan</th>
                    	</tr>
                    	<tr>
                    		<th id = "email">trojan@usc.edu</th>
                    	</tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- sidebar end -->
        
        <!-- content -->
        <div class="col py-3">
            <h3>Content</h3>
            <hr class = "divide">
        </div>
        <!-- content end -->
        
      </div>
     </div>

</body>
</html>

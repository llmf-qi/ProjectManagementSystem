<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.script.*" %>
<%@ page import = "java.util.*" %>
<%@ page import="com.google.gson.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Home Page</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link rel="stylesheet" type="text/css" href="calendar.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css"
    />
    <link href="http://fonts.cdnfonts.com/css/handjet-2" rel="stylesheet" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/7f10118ced.js"
      crossorigin="anonymous"
    ></script>
<%@ page import="Util.User"%>
<%@ page import="Util.Project"%>
<%@ page import="Util.DataParser"%>
<%@ page import="Util.TodoList"%>
<%@ page import="Util.Task"%>

<%
	

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
           if(cookie.getName().equals("userid")){
                userid=cookie.getValue();
                theuser= dp.getUser(Integer.valueOf(userid));
                if(theuser!=null){
                    email=theuser.getUseremail();
                    username=theuser.getUsername();
                       isLogin=true;
                }
                
           }
         
        } 
    }

    p=dp.getProject(projectid);
    JsonObject calendar = DataParser.calenderTask(projectid);
    String tasks = calendar.toString();
    System.out.println(tasks);
    
%>

  </head>
  <body onload = "retrieveData()">
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
            
    <div class="container-fluid">
      <div class="row flex-nowrap">
        <!-- sidebar -->
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0" id="sidebar">
          <div
            class="d-flex flex-column align-items-center pt-2 text-white min-vh-100"
            id="sidebarContent"
          >
            <div class="sidebar-header">
              <a
                href="exitproject"
                class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none"
              >
                <span class="d-none d-sm-inline">PM SYSTEM</span>
              </a>
            </div>
            <ul
              class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start"
              id="menu"
            >
              <li class="nav-item">
                <a href="groupInfo.jsp" class="nav-link align-middle px-0">
                  <i class="fa-solid fa-circle-info"></i>
                  <span class="ms-1 d-none d-sm-inline">Group Info</span>
                </a>
              </li>
              <li>
                <a href="calendar.jsp" class="nav-link px-0 align-middle">
                  <i class="fa-solid fa-calendar"></i>
                  <span class="ms-1 d-none d-sm-inline">Calendar</span></a
                >
              </li>
              <li>
                <a
                  href="todoList.jsp" class="nav-link px-0 align-middle"
                >
                  <i class="fa-solid fa-list"></i>
                  <span class="ms-1 d-none d-sm-inline">To-do List</span></a
                >
              </li>
              <li>
                <a href="LogoutDispatcher" class="nav-link px-0 align-middle">
                  <i class="fa-solid fa-right-from-bracket"></i>
                  <span class="ms-1 d-none d-sm-inline">Logout</span>
                </a>
              </li>
            </ul>
            <hr />
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
        <div class="col py-3 division">
          <p class = "title">Calendar</p>
          <div class="container">
            <div class="calendar-tools row">
              <div class="col">
                <button class="btn btn-link text-dark" id="chevleft">
                  <i class="fas fa-chevron-left"></i>
                </button>
                <span class="calendar-heading" id="calheading"></span>
                <button class="btn btn-link text-dark" id="chevright">
                  <i class="fas fa-chevron-right"></i>
                </button>
              </div>
              <div class="col-2">
                <button type="button" class="btn btn-primary calendarButton" data-bs-toggle="modal" data-bs-target="#Modalform">Add Task</button>
                <div class="modal fade" id="Modalform" tabindex="-1" aria-labelledby="ModalformLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="ModalformLable">New Task</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                      </div>
                      <div class="modal-body">
                        <form>
                          <div class="mb-3">
                            <label for="task-title" class="col-form-label">Title:</label>
                            <input type="text" class="form-control" id="task-title" required>
                          </div>
                          <div class="mb-3">
                            <label for="task-date" class="col-form-label">Date:</label>
                            <input type="date" class="form-control" id="task-date" required>
                          </div>
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="addTask">Add Task</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <br/>
            <div class="calendar row">
              <table class="month" id="month_cal">
                <thead>
                  <tr class="days-row">
                    <th>Sun</th>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                  </tr>
                </thead>
              </table>
            </div>
          </div>
        </div>
        <!-- content end -->
    </div>
    </div>
     <%
     if (isLogin) {
         out.println("<div id=\"taskjson\" class=\"d-none\">" + tasks + "</div>");
     }
     %>   
    <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"
    ></script>
    <script src="calendar.js"></script>
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        
    </head>

    <body>
    		<% Cookie cookie = null;
    		   Cookie[] cookies = null;
    		   cookies = request.getCookies();
    		   int cookieexist=0;
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					   cookie = cookies[i];
					   if(cookie.getName().equals("projectid")){
						   String projectid=cookie.getValue();
					    }
					  }
			}

		String title = (String) request.getParameter("title");
		if (title == null) title = "";

		String member = (String) request.getParameter("member");
		if (member == null) member = "";
		
		String description = (String) request.getParameter("description");
		if (description == null) description = "";
	%>
    </body>

    </html>
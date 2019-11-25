<%-- 
    Document   : courses
    Created on : 2019-11-24, 11:47:12
    Author     : c
--%>

<%@page import="fr.utbm.entity.Location"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>是你掉的这些课吗？</h1>
        <ol>
            <% for(Location e: (List<Location>)request.getAttribute("courses")){%>
            <li><%out.print(e.toString());%></li>
            <%}%>
    </body>
</html>

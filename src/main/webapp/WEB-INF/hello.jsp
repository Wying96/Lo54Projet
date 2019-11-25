<%-- 
    Document   : hello
    Created on : 2019-11-22, 17:20:52
    Author     : wuying
--%>

<%@page import="java.util.List"%>
<%@page import="fr.utbm.entity.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Controller中的message</h1>
        ${message}
        <!--使用{% 。。。 %}来写java代码-->
        <ol>
            <% for(Course e: (List<Course>)request.getAttribute("cours")){%>
            <li><%out.print(e.toString());%></li>
            <%}%>
    </body>
</html>

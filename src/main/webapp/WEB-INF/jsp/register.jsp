<%-- 
    Document   : register
    Created on : 2019-11-26, 19:20:45
    Author     : wuying
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body style="text-align:center;">
        <h4 align="center"> N° de session vous avez choisi : ${course}</h4>

        <form class="sui-form form-horizontal " action="<%=basePath%>/registrersession" method="post">
            <input type="hidden" value="${course}" id="coursesessionid" name="coursesessionid"/>
            <div class="control-group"  >
                <label for="inputuserName" class="control-label">Last name：</label>
                <div class="controls" >
                    <input type="text" name="lastname" >
                </div>
            </div>
            <div class="control-group">
                <label for="inputPassword" class="control-label">First name：</label>
                <div class="controls">
                    <input type="text" name="firstname" >
                </div>
            </div>
            <div class="control-group">
                <label for="inputPassword" class="control-label">address：</label>
                <div class="controls">
                    <input type="text" name="address" >
                </div>
            </div>
            <div class="control-group">
                <label for="inputPassword" class="control-label">phone：</label>
                <div class="controls">
                    <input type="text" name="phone" >
                </div>
            </div>
            <div class="control-group">
                <label for="inputPassword" class="control-label">Email：</label>
                <div class="controls">
                    <input type="text" name="email" >
                </div>
            </div>
            <button type="submit" class="sui-btn btn-info">queren</button>
        </form>
    </body>
</html>

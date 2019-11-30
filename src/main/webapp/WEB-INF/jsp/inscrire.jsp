<%-- 
    Document   : login
    Created on : Nov 27, 2019, 6:42:07 PM
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
        <title>Register Formation Ecole</title>
    </head>
    <body style="text-align:center;">
        <form class="sui-form form-horizontal " action="<%=basePath%>/inscrireclient" method="post">
             ${msg}
            <div class="control-group">
                <label for="inputPassword" class="control-label">Address：</label>
                <div class="controls">
                    <input type="text" name="address" >
                </div>
            </div>
              <div class="control-group"  >
                <label for="inputuserName" class="control-label">Password：</label>
                <div class="controls" >
                    <input type="password" name="password" >
                </div>
            </div>
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
            <button type="submit" class="sui-btn btn-info">Valider</button>
        </form>
    </body>
</html>

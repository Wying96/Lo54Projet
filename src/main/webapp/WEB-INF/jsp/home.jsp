<%-- 
    Document   : login
    Created on : Nov 30, 2019, 8:02:06 PM
    Author     : wuying
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

     <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">


<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>


<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body style="text-align:center;">

 
		<div class="modal show" id="loginModal">
    <div class="modal-dialog">
      <div class="modal-content">
            <div class="modal-header">
               
              <button type="button" class="close">×</button>
              <h2 class="text-center text-primary">Bienvenue au Formation Ecole</h2>
               <img src="http://www.citedesmetiers.ch/var/cdmt/storage/images/media/images/fotolia_38216436_subscription_xxl/78429-1-fre-FR/Fotolia_38216436_Subscription_XXL_large.jpg" />
            </div>
            <div class="modal-body">
                 
            <form class="form col-md-12 center-block" id="loginForm" action="login" method="post">
            <div class="form-group-lg"  id="accountDiv">
               
              <label class="sr-only" for="inputAccount">Identifiant </label>
              <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
                <input class="form-control" id="inputAccount" name="accountNo" type="text" placeholder="Identifiant" required autofocus>
              </div>
              <div class="hidden text-center" id="accountMsg"><span class="glyphicon glyphicon-exclamation-sign"></span>用户名不存在</div>
            </div>
            <br>
            <div class="form-group-lg" id="pwdDiv">
             
               <div style="color:red">
  
 ${error}
</div>
                 </c:if>
              <label class="sr-only" for="inputPassword">Mot de passe</label>
              <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                <input class="form-control" id="inputPassword" name="pwd" type="password" placeholder="Mot de passe" required>
                <div class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></div>
              </div>
              <div class="hidden text-center" id="pwdMsg"><span class="glyphicon glyphicon-exclamation-sign"></span>用户名密码错误</div>
            </div>
            <div class="form-group">
              <button class="btn btn-default btn-lg col-md-6" id="btn_register" type="submit">Login</button>
              <button class="btn btn-default btn-lg col-md-6"><a href="inscrire">s'inscrire</a></button>
            </div>
          </form>
        </div>
        <div class="modal-footer">
</html>

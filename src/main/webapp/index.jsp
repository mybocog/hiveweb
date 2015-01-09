<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/4
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="lib/css/bootstrap.css">
    <link rel="stylesheet" href="lib/css/flat-ui.css">
</head>
<body style="background-color: #1abc9c">
<div style="margin-left: 35%;margin-top: 15%">
    <div style="position: static;float: left;margin-top: 20;margin-right: 20" class="login-icon">
        <img src="lib/img/icon.png" alt="Welcome to Hiveweb" />
        <h4>Welcome to <small>Hive Web</small></h4>
    </div>

    <form method="post" action="login">
        <div style="float: left; height: 240; width: 320" class="login-form">
            <div class="form-group">
                <input type="text" name="username" class="form-control login-field" value="" placeholder="Enter your account" id="login-name" />
                <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
                <input type="password" name="password" class="form-control login-field" value="" placeholder="Password" id="login-pass" />
                <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <button type="submit" class="btn btn-primary btn-lg btn-block">Log in</button>
            <a class="login-link" href="resetpwd.jsp">Reset Password</a>
        </div>
    </form>
</div>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/1/5
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="resetpwd.js"></script>
    <script type="text/javascript" src="lib/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="lib/js/bootstrap.js"></script>
    <link rel="stylesheet" href="lib/css/bootstrap.css">
</head>
<body>

<div id="info" align="center">

</div>
<div align="center">
    <form>
        <input class="form-control" type="text" id="username" placeholder="Account" style="width:300px;height:30px;margin-top: 20">
        <input class="form-control" type="password" id="oldpassword" placeholder="Old password" style="width:300px;height:30px;margin-top: 5">
        <input class="form-control" type="password" id="newpassword" placeholder="New password" style="width:300px;height:30px;margin-top: 5">
        <input class="form-control" type="password" id="newpassword2" placeholder="New password again" style="width:300px;height:30px;margin-top: 5">
        <input class="btn btn-info" type="button" value="submit" style="width:150px;height:40px;margin-top: 20" onclick="submitpassword()">
    </form>
</div>


</body>
</html>

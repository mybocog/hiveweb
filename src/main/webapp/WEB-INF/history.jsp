<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/23
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../lib/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../lib/js/bootstrap.js"></script>
    <script type="text/javascript" src="../history.js"></script>
    <link rel="stylesheet" href="../lib/css/bootstrap.css">
</head>
<body onload="preload()">
<%@include file="nav3.jsp"%>
<div class="container-fluid" style="margin-top: 70;">
    <div class="row">
        <div class="col-md-12">
            <table id="historytable" class="table table-hover table-striped">
            </table>
        </div>
    </div>
</div>
</body>
</html>

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
    <script type="text/javascript" src="lib/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="lib/js/bootstrap.js"></script>
    <script type="text/javascript" src="adhoc.js"></script>
    <link rel="stylesheet" href="lib/css/bootstrap.css">
</head>
<body onload="loadcm()">
<%@include file="nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <tr class="active">
                    <td>select count(1) from orders</td>
                </tr>
                <tr class="success">
                    <td>select count(1) from orders</td>
                </tr>
                <tr class="warning">
                    <td>select count(1) from orders</td>
                </tr>
                <tr class="danger">
                    <td>select count(1) from orders</td>
                </tr>
                <tr class="info">
                    <td>select count(1) from orders</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>

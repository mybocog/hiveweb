<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/3
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="lib/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="lib/js/bootstrap.js"></script>
    <script type="text/javascript" src="lib/js/codemirror.js"></script>
    <script type="text/javascript" src="lib/js/sql.js"></script>
    <script type="text/javascript" src="query.js"></script>
    <link rel="stylesheet" href="lib/css/codemirror.css">
    <link rel="stylesheet" href="lib/css/bootstrap.css">
</head>
<body onload="loadcm()">
<%@include file="nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <%@include file="side-left.jsp"%>
        </div>
        <div class="col-md-10">
            <textarea id="sqledit" wrap="virtual" rows="10" cols="100" style="color:Indigo; font-family:'verdana'; font-size:20px; border: none"></textarea>
            <div class="btn-group">
                <button type="button" class="btn btn-default btn-lg">1</button>
                <button type="button" class="btn btn-default btn-lg">2</button>
                <button type="button" class="btn btn-default btn-lg">3</button>
            </div>
            <button type="button" class="btn btn-primary btn-lg" style="margin-left: 30%">submit</button>
            <button type="button" class="btn btn-danger btn-lg" style="margin-left: 3%">kill</button>
        </div>
    </div>
</div>

</body>
</html>

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
    <script type="text/javascript" src="adhoc.js"></script>
    <link rel="stylesheet" href="lib/css/codemirror.css">
    <link rel="stylesheet" href="lib/css/bootstrap.css">
</head>
<body onload="setjobno(1);loadcm();updatestatus();">
<%@include file="nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <%@include file="side-left.jsp"%>
        </div>
        <div class="col-md-9">
            <%@include file="adhoc-edit.jsp"%>
        </div>
    </div>
</div>

</body>
</html>

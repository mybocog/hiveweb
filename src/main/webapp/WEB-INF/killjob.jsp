<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/23
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../lib/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../lib/js/bootstrap.js"></script>
    <script type="text/javascript" src="../killjob.js"></script>
    <link rel="stylesheet" href="../lib/css/bootstrap.css">
</head>
<body onload="killedlog()">
<%@include file="nav4.jsp"%>
<div class="container-fluid" style="margin-top: 100;">
    <div class="row" style="margin-bottom: 100">
        <div class="col-md-3">
            <input class="form-control" id="jobid" placeholder="Please input Job ID" >
            <input type="button" id="kill" class="btn btn-danger" value="kill" onclick="killjobid()" style="margin-top:20; display: none"></button>
        </div>
        <div class="col-md-1">
            <input type="button" id="submit" class="btn btn-warning" value="kill" onclick="submitjobid()" ></button>
        </div>
        <div class="col-md-8">
        </div>
    </div>

    <div class="row">
        <div class="col-md-5">
        <table id="killedlog" class="table table-hover table-bordered">
        </table>
        </div>
        <div class="col-md-7">
        </div>
    </div>
</div>

</body>
</html>

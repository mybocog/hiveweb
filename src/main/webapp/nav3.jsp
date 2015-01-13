<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/3
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<style type="text/css">
    .navbar-default .navbar-brand {
        color: gray;
    }
    .navbar-default .navbar-nav > li > a {
        color: darkgray;
    }
    .navbar-default{
        background-color: #d9edf7;
    }
    .navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus {
        color: #555;
        background-color: white;
    }
</style>

<nav class="nav navbar-default"  style="margin-bottom: 20;">
    <div class="navbar-header">
            <h class="navbar-brand">Hive</h>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li><a href="adhoc.jsp">AdHoc1</a></li>
            <li><a href="adhoc2.jsp">AdHoc2</a></li>
            <li class="active"><a href="history.jsp">History</a></li>
            <li><a href="killjob.jsp">KillJob</a></li>
            <%--
                        <li><a href="runquery.jsp">RunQuery</a></li>
                        <li><a href="#" target="_blank">JobMonitor</a></li>
            --%>
        </ul>

        <ul class="nav navbar-nav navbar-right" style="margin-right: 10">
<%--            <li><a href="#">UserName</a></li>
            <li><a href="#">Quit</a></li>
--%>
        </ul>
    </div>
</nav>

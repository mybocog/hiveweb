<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">

    <select id="dbselect" class="form-control" style="margin-bottom: 20" onchange="dbchange(this.value)">
        <option> </option>
    </select>

    <div class="panel panel panel-default">
        <div id="db_panel_head" class="panel-heading" style="background-color: #ddd"><b>dbname</b></div>
        <div id="db_panel_list" class="list-group">
            <a href="#" class="list-group-item" onclick="desctable('a')"><span class="badge"></span><b>tablename</b></a>
        </div>
    </div>

    <div class="panel panel panel-default">
        <div id="tb_panel_head" class="panel-heading" style="background-color: #ddd"><b>table name</b></div>
        <div id="tb_panel_body" class="panel-body">
            <p>table info</p>
        </div>

        <ul id="tb_panel_list" class="list-group">
            <li class="list-group-item"><span class="badge">type</span><b>column</b></li>
        </ul>
    </div>
</div>
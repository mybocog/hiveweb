<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">
    <div class="input-group" style="margin-bottom: 20;">
        <span class="input-group-addon"><b>DB</b></span>
        <select id="dbselect" class="form-control" onchange="dbchange(this.value)">
            <option> </option>
        </select>
    </div>


    <div class="panel panel panel-info">
        <div id="db_panel_head" class="panel-heading" style=""><b></b></div>
        <div id="db_panel_list" class="list-group" style="max-height: 400; overflow-y: auto">
            <a href="#" class="list-group-item" onclick="desctable('a')"><span class="badge"></span><b></b></a>
        </div>
    </div>

    <div class="panel panel panel-info">
        <div id="tb_panel_head" class="panel-heading" style=""><b></b></div>
        <div id="tb_panel_body" class="panel-body">
            <p></p>
        </div>

        <ul id="tb_panel_list" class="list-group"  style="max-height: 400; overflow-y: auto">
            <li class="list-group-item"><span class="badge"></span><b></b></li>
        </ul>
    </div>
</div>
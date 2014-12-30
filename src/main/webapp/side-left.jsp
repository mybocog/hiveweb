<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">

        <select id="dbselect" class="form-control" style="margin-bottom: 20">
            <option>null1</option>
            <option>null2</option>
        </select>

    <div class="panel panel panel-default">
        <div class="panel-heading" style="background-color: #ddd"><b>mysql</b></div>
        <div class="list-group">
            <a href="#" class="list-group-item"><span class="badge"></span><b>orders</b></a>
            <a href="#" class="list-group-item"><span class="badge"></span><b>order_items</b></a>
            <a href="#" class="list-group-item"><span class="badge"></span><b>tuanmei_user</b></a>
            <a href="#" class="list-group-item"><span class="badge"></span><b>feedbacks</b></a>
            <a href="#" class="list-group-item"><span class="badge"></span><b>pathdiagram</b></a>
        </div>
    </div>

    <div class="panel panel panel-default">
        <div class="panel-heading" style="background-color: #ddd"><b>orders</b></div>
        <div class="panel-body">
            <p>updated at 2014.12.28 4:23</p>
            <p>external table</p>
        </div>

        <ul class="list-group">
            <li class="list-group-item"><span class="badge">int</span><b>order_id</b></li>
            <li class="list-group-item"><span class="badge">int</span><b>uid</b></li>
            <li class="list-group-item"><span class="badge">string</span><b>name</b></li>
            <li class="list-group-item"><span class="badge">long</span><b>timestamp</b></li>
            <li class="list-group-item"><span class="badge">long</span><b>create</b></li>
        </ul>
    </div>
</div>
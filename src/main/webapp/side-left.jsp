<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
        <select class="form-control">
            <option value="0">Choose hero</option>
            <option value="1">Spider Man</option>
        </select>
        </div>
    </div>

    <div class="row">
        <ul id="main-nav" class="nav" style="border: 1px solid #ccc;border-radius: 4px; margin-top: 15">
            <li>
                <a href="#t1" class="nav-header collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-list-alt" style="margin-right: 10"></span>t1<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
                <ul id="t1" class="nav nav-list collapse secondmenu">
                    <li><a href="#" style="margin-left: 10; color: #808080">b1</a></li>
                    <li><a href="#" style="margin-left: 10; color: #808080">b2</a></li>
                    <li><a href="#" style="margin-left: 10; color: #808080">b3</a></li>
                    <li><a href="#" style="margin-left: 10; color: #808080">b4</a></li>
                </ul>
            </li>
            <li>
                <a href="#t2" class="nav-header collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-list-alt" style="margin-right: 10"></span>t2<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
            </li>
            <li>
                <a href="#t3" class="nav-header collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-list-alt" style="margin-right: 10"></span>t3<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
            </li>

        </ul>
    </div>
</div>
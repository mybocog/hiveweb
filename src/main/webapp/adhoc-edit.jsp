<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/12/10
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10">
            <textarea class="form-control" id="sqledit" wrap="virtual" rows="10" cols="100" style="color:Indigo; font-family:'verdana'; font-size:20px; border: none"></textarea>
        </div>
        <div class="col-md-2">
            <div class="btn-group-vertical" style="width: 100%; margin-bottom: 30">
                <button type="button" id="submitbutton" class="btn btn-lg btn-info" style="" onclick="submitsql()">submit</button>
            </div>
            <div class="btn-group-vertical" style="width: 100%">
                <button type="button" id="stopbutton" class="btn btn-lg btn-danger" style="" onclick="stopjob()">stop</button>
            </div>
        </div>
</div>

<div id="divdownload" style="margin-top: 30">
</div>

<ul id="display" class="list-unstyled" style="margin-top: 30">
    <li></li>
</ul>

<div id="hiveresult" style="width:100%;height:auto;overflow:auto;margin-top: 30" >
<%--    <table class="table table-hover table-bordered table-striped">
        <thead><tr><th>col</th></tr></thead>
                <tr><td>value</td></tr>
    </table>
--%>
</div>
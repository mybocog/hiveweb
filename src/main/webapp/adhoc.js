/**
 * Created by Administrator on 2014/12/4.
 */
var intvid;
var timestamp;
var gettype;
var editor;
var jobno;
var processline=15;
var resultline=20;

function setjobno(n){
    jobno = n
    if(n=="2"){
        str = navigator.userAgent
        if(str.indexOf("Windows")>=0){
            var body = document.getElementById("body")
            body.style.fontFamily="cursive"
            var sqledit = document.getElementById('sqledit')
            sqledit.style.fontFamily="cursive"
        }
        else if(str.indexOf("Mac")>=0){
            var body = document.getElementById("body")
            body.style.fontFamily="Comic Sans MS"
            var sqledit = document.getElementById('sqledit')
            sqledit.style.fontFamily="cursive"
        }
    }
}

function loadcm(){
    var mime="text/x-mysql";
    editor = CodeMirror.fromTextArea(document.getElementById('sqledit'), {
        mode: mime,
        lineWrapping:true,
        lineNumbers: true
    });
}

function dbinfo(){
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/dbinfo?&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            db1st=""
            dblist = xmlhttp.responseText;
            dbs = dblist.split(",")
            var tmphtml = ""
            for(i in dbs){
                db1st=dbs[0]
                tmphtml += "<option>"+dbs[i]+"</option>"
            }
            var dbselect = document.getElementById("dbselect");
            dbselect.innerHTML=tmphtml

            tableinfo(db1st)
        }
    };
}

function tableinfo(db){
    var db_panel_head = document.getElementById("db_panel_head");
    db_panel_head.innerHTML="<b>"+db+"</b>"
    var db_panel_list = document.getElementById("db_panel_list");
    db_panel_list.innerHTML="<a class=\"list-group-item\">loading...</a>"
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/tableinfo?&db="+db+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            tblist = xmlhttp.responseText;
            tbs = tblist.split(",")
            var tmphtml = ""
            for(i in tbs){
                tmphtml += "<a style=\"cursor: pointer;\" class=\"list-group-item\" onclick=\"desctable('"+db+"','"+tbs[i]+"')\"><b>"+tbs[i]+"</b></a>"
            }
            var db_panel_list = document.getElementById("db_panel_list");
            db_panel_list.innerHTML=tmphtml
        }
    };
}

function desctable(db,tb){
        var tb_panel_body = document.getElementById("tb_panel_body");
        tb_panel_body.innerHTML="loading...";
        var tb_panel_list = document.getElementById("tb_panel_list");
        tb_panel_list.innerHTML="";
        var xmlhttp;
        xmlhttp=new XMLHttpRequest();
        xmlhttp.open("GET","/desctable?&db="+db+"&tb="+tb+"&rand="+new Date().getTime(),true);
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200 )
            {
                rp = xmlhttp.responseText;
                rps = rp.split(";")
                cols = rps[0].split(",")
                types = rps[1].split(",")
                var tmphtml = ""
                for(i in cols){
                    tmphtml += "<li class=\"list-group-item\"><span class=\"badge\">"+types[i]+"</span><b>"+cols[i]+"</b></li>"
                }
                var tb_panel_head = document.getElementById("tb_panel_head");
                tb_panel_head.innerHTML="<b>"+tb+"</b>";
                var tb_panel_body = document.getElementById("tb_panel_body");
                tb_panel_body.innerHTML="<p>"+rps[2]+"</p><p>"+rps[3]+"</p>";
                var tb_panel_list = document.getElementById("tb_panel_list");
                tb_panel_list.innerHTML=tmphtml;
            }
        };
}

function dbchange(db){
    tableinfo(db)
}

Date.prototype.format = function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

function getprocess()
{
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/getprocess?&g="+gettype+"&j="+jobno+"&l="+processline+"&rl="+resultline+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            var str = xmlhttp.responseText;
            if(gettype=="e" && str.substr(0,8)=="finished")
            {
                leaverunning();
                enterfinished(timestamp);
                var resulttip = document.getElementById("resulttip");
                s = str.split(",")
                if(s[3]=="s"){
                    resulttip.innerHTML="<p style='color: green'>The result has a total of "+s[1]+" lines</p>";
                }
                else{
                    resulttip.innerHTML="<p style='color: red'>You only got "+s[1]+" lines</p>";
                }
                return;
            }
            if(gettype=="e" && str=="error")
            {
                leaverunning();
                setTimeout(getprocess,100);
                return;
            }
            if(gettype=="e"){
                var display = document.getElementById("display");
                display.innerHTML=str;
                return;
            }
            if(gettype=="r"){
                var hiveresult = document.getElementById("hiveresult");
                hiveresult.innerHTML=str;
                return;
            }
        }
    };
}

function leaverunning()
{
    clearInterval(intvid);
    var submitbutton = document.getElementById("submitbutton");
    submitbutton.disabled="";
}

function enterrunning(para)
{
    timestamp=para;
    var submitbutton = document.getElementById("submitbutton");
    var divdownload = document.getElementById("divdownload");
    var hiveresult = document.getElementById("hiveresult");
    var resulttip = document.getElementById("resulttip");
    submitbutton.disabled="disabled";
    divdownload.innerHTML="";
    hiveresult.innerHTML="";
    resulttip.innerHTML=""
    gettype="e";
    intvid= setInterval(getprocess,1000);
}

function enterfinished(para)
{
    gettype="r";
    getprocess();
    var display = document.getElementById("display");
    display.innerHTML="";
    var divdownload = document.getElementById("divdownload");
    divdownload.innerHTML="<a href=\"/download?&t="+para+"\">Download Result</a>";
}

function submitsql()
{
    String.prototype.trim = function()
    {
        return this.replace(/(^\s*)|(\s*$)/g, '');
    };
    String.prototype.removesemicolon = function()
    {
        return this.replace(/;/g, '');
    };
    String.prototype.removetab = function()
    {
        return this.replace(/\t/g, ' ');
    };

    var dbselect = document.getElementById("dbselect");
    db = dbselect.value

    var sqledit = document.getElementById("sqledit");
    var sqlstr = editor.getValue();
    sqlstr=sqlstr.trim().removesemicolon().removetab();
    if(sqlstr==""){
        return;
    }
    if(sqlstr.search(/^\s*drop/i)!=-1){
        return;
    }
    if(sqlstr.search(/^\s*alter/i)!=-1){
        return;
    }
    if(sqlstr.search(/^\s*dfs/i)!=-1){
        return
    }
    var limit="1"
    if(sqlstr.search(/^\s*desc/i)!=-1 || sqlstr.search(/^\s*describe/i)!=-1  || sqlstr.search(/^\s*show/i)!=-1){
        limit="0"
    }

    timestamp = new Date().format("yyyyMMddhhmmss");
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("POST","/handlesql?li="+limit+"&t="+timestamp+"&db="+db+"&j="+jobno+"&rand="+new Date().getTime(),true);
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.send("sql="+sqlstr.replace(/%/g,"%25").replace(/\+/g,"%2B"));
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
//            xmlhttp.responseText
            enterrunning(timestamp);
        }
    };
}

function updatestatus()
{
    dbinfo();
    loadcm();
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/getstatus?&j="+jobno+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            var status=xmlhttp.responseText;
            if(status=="init")
            {
                return;
            }

            var words = status.split(";");
            editor.setValue(words[2]);
            if(words[0]=="running" || words[0]=="error")
            {
                enterrunning(words[1]);
                return;
            }
            else if(words[0]=="finished")
            {
                enterfinished(words[1]);
                return
            }
        }
    };
}

function stopjob()
{
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/killhive?&j="+jobno+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            var divdownload = document.getElementById("divdownload");
            var str=xmlhttp.responseText;
            if(str=="no running")
            {
                divdownload.innerHTML="<p style='color: #ff0000'>Stop: No job is running</p>"
                return;
            }
            else if(str=="no jobid")
            {
                divdownload.innerHTML="<p style='color: #ff0000'>Stop: Cannot find job id</p>"
                return;
            }
            divdownload.innerHTML="<p style='color: #ff0000'>"+str+"</p>";
        }
    };
}
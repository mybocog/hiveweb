/**
 * Created by Administrator on 2014/12/4.
 */
var intvid;
var timestamp;
var gettype;
var editor;
var jobno;
var processline=10;

function setjobno(n){
    jobno = n
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
    var username = "test"
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/dbinfo?&u="+username+"&rand="+new Date().getTime(),true);
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
                tmphtml += "<a class=\"list-group-item\" onclick=\"desctable('"+db+"','"+tbs[i]+"')\"><b>"+tbs[i]+"</b></a>"
            }
            var db_panel_list = document.getElementById("db_panel_list");
            db_panel_list.innerHTML=tmphtml
        }
    };
}

function desctable(db,tb){
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
                tb_panel_body.innerHTML="<p>"+rps[2]+"</p><p>"+rps[3]+" updated</p>";
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
    xmlhttp.open("GET","/getprocess?&g="+gettype+"&j="+jobno+"&l="+processline+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            var str = xmlhttp.responseText;
            if(gettype=="e" && str=="finished")
            {
                leaverunning();
                enterfinished(timestamp);
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
    submitbutton.disabled="disabled";
    divdownload.innerHTML="";
    hiveresult.innerHTML="";
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

    var dbselect = document.getElementById("dbselect");
    db = dbselect.value

    var sqledit = document.getElementById("sqledit");
    var sqlstr = editor.getValue();
    sqlstr=sqlstr.trim();
    if(sqlstr==""){
        return;
    }
    if(sqlstr.search(/^\s*test/i)!=-1 || sqlstr.search(/^\s*desc/i)!=-1 || sqlstr.search(/^\s*select/i)!=-1  || sqlstr.search(/^\s*show/i)!=-1)
    {

    }
    else
    {
        alert("You must input SELECT sql");
        return;
    }

    timestamp = new Date().format("yyyyMMddhhmmss");
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("POST","/handlesql?t="+timestamp+"&db="+db+"&j="+jobno+"&rand="+new Date().getTime(),true);
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
                loadcm();
                dbinfo();
                return;
            }

            var words = status.split(";");
//            sqledit = document.getElementById('sqledit')
//            sqledit.innerHTML = words[2]
            loadcm();
            if(words[0]=="running")
            {
                enterrunning(words[1]);
                return;
            }
            else if(words[0]=="finished")
            {
                enterfinished(words[1]);
            }
        }
    };
}

function killjob()
{
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/killhive?&j="+jobno+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            var str=xmlhttp.responseText;
            if(str=="no running")
            {
                return;
            }
            else if(str=="null")
            {
                return;
            }
            var divdownload = document.getElementById("divdownload");
            divdownload.innerHTML=xmlhttp.responseText;
        }
    };
}
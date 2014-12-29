/**
 * Created by Administrator on 2014/12/4.
 */
var intvid;
var timestamp;
var gettype;
var linenum=20;
var rand=0;
var editor;


function loadcm()
{
    var mime="text/x-mysql";
    editor = CodeMirror.fromTextArea(document.getElementById('sqledit'), {
        mode: mime,
        lineWrapping:true
    });
}

function dbinfo(username){
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/dbinfo?&username="+username+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            xmlhttp.responseText;
        }
    };
}

function tableinfo(username){
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/tableinfo?&username="+username+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            xmlhttp.responseText;
        }
    };
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
    var display = document.getElementById("display");
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/data/getprocess?g="+gettype+"&l="+linenum+"&rand="+new Date().getTime(),true);
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
            display.innerHTML=xmlhttp.responseText;
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
    submitbutton.disabled="disabled";
    divdownload.innerHTML="";
    gettype="e";
    intvid= setInterval(getprocess,1000);
}

function enterfinished(para)
{
    gettype="r";
    getprocess();

    var divdownload = document.getElementById("divdownload");
    divdownload.innerHTML="<a href=\"/data/download?&t="+para+"\">Download Result</a>";
}

function submitsql()
{
    String.prototype.trim = function()
    {
        return this.replace(/(^\s*)|(\s*$)/g, '');
    };

    var sqledit = document.getElementById("sqledit");
    var dblist = document.getElementById("dblist");
    var sqlstr = editor.getValue();
    sqlstr=sqlstr.trim();
    if(sqlstr=="")
    {
        alert("Please input SQL");
        return;
    }
//|| sqlstr.search(/^\s*create\s*table\s*tmp_/i)!=-1
    if(sqlstr.search(/^\s*desc/i)!=-1 || sqlstr.search(/^\s*select/i)!=-1  || sqlstr.search(/^\s*show/i)!=-1)
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
    xmlhttp.open("POST","/data/handlesql?t="+timestamp+"&db="+dblist.value+"&rand="+new Date().getTime(),true);
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    xmlhttp.send("sql="+encodeURI(sqlstr.replace(/%/g,"%25").replace(/\+/g,"%2B")));
    xmlhttp.send("sql="+sqlstr.replace(/%/g,"%25").replace(/\+/g,"%2B"));
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 )
        {
            if(xmlhttp.responseText=="permissiondenied")
            {
                alert("Permission Denied on this Database!");
                return;
            }
            if(xmlhttp.responseText.indexOf("forbiddentable")>0)
            {
                v=xmlhttp.responseText.split(",")
                alert("Permission Denied on the table:"+v[0]);
                return;
            }
            enterrunning(timestamp);
        }
    };
}

function updatestatus()
{
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/data/getstatus?&rand="+new Date().getTime(),true);
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
            var words = status.split(",");
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
    xmlhttp.open("GET","/data/killmrjob?&rand="+new Date().getTime(),true);
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

function showtables()
{
    var stbutton = document.getElementById("stbutton");
    stbutton.disabled="disabled";
    var dblist = document.getElementById("dblist");
    var display = document.getElementById("display");
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/data/showtable?db="+dblist.value+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            display.innerHTML=xmlhttp.responseText;
            stbutton.disabled="";
        }
    };
}
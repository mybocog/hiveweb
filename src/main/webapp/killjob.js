/**
 * Created by Administrator on 2014/12/23.
 */
var jobid;

function killedlog(){
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/killedlog?&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            str = xmlhttp.responseText;
            if(str==""){
                return
            }
            var killedlog = document.getElementById("killedlog");
            killedlog.innerHTML ="<thead><tr><th>Time</th><th>User</th><th>JobID</th></tr></thead>"+str
        }
    };
}

function submitjobid(){
    var jobidedit = document.getElementById("jobid");
    jobid = jobidedit.value
    if(jobid==""){
        return
    }
    var submitbutton = document.getElementById("submit");
    submitbutton.style.display="none"
    var killbutton = document.getElementById("kill");
    killbutton.style.display=""
    killbutton.disabled =""
    killbutton.value="ARE YOU SURE?"
}

function killjobid(){
    var jobidedit = document.getElementById("jobid");
    if(jobidedit.value==""){
        return
    }
    else{
        jobidedit.value=""
    }

    var killbutton = document.getElementById("kill");
    killbutton.disabled ="disabled"
    killbutton.value="waiting..."

    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/killjob?&jobid="+jobid+"&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            killbutton.value=xmlhttp.responseText;
        }
    };
    killbutton.style.display="none"
    var submitbutton = document.getElementById("submit");
    submitbutton.style.display=""
}
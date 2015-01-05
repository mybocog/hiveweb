/**
 * Created by Administrator on 2015/1/5.
 */
function submitpassword()
{
    var new1 = document.getElementById("newpassword");
    var new2 = document.getElementById("newpassword2");
    var old = document.getElementById("oldpassword");
    var un = document.getElementById("username");
    var div = document.getElementById("info");
    div.innerHTML="";
    if(un.value=="" || old.value=="" || new1.value=="" || new2.value=="")
    {
        alert("input is empty!");
        return;
    }
    if(new1.value!=new2.value)
    {
        alert("the new passwords are not same!");
        return;
    }
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("POST","/resetpwd",true);
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.send("username="+un.value+"&oldpassword="+old.value+"&newpassword="+new1.value);
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 )
        {
            if(xmlhttp.responseText=="ok")
            {
                div.innerHTML="change successfully";
                return;
            }
            else if(xmlhttp.responseText=="loginerror")
            {
                div.innerHTML="account or password is wrong";
                return;
            }
        }
    };
}
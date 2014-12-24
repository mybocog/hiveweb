/**
 * Created by Administrator on 2014/12/24.
 */
function preload()
{
    var historytable = document.getElementById("historytable");
    var xmlhttp;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","/history?&rand="+new Date().getTime(),true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200 )
        {
            historytable.innerHTML = xmlhttp.responseText;
        }
    };
}
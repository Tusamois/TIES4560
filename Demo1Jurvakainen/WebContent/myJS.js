function askIP()
{
    //window.document.getElementById('div3').style.display = 'none';
	var q_str = 'value='+document.getElementById('t1').value;
	doAjax('MyServlet',q_str,'askIP_back','post',0);
}
function askIP_back(result)
{
	/*if (result.substring(0,5)=='error'){
	   window.document.getElementById('div3').style.display = 'block';
	   window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{*/
	   window.document.getElementById('t2').value=""+result;
   //}
}

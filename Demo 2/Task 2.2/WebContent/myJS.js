window.onload = customize;
Code = "";
function customize(){
	try{
	Code = window.location.href.substring(35);
	}catch(err){
		
	}
}


function sendRequest()
{
    var q_str = 'type=number';
	doAjax('MyDBox',q_str,'sendRequest_back','post',0);
}
function sendRequest_back(result)
{
	if (result.substring(0,5)=='error'){
	   //window.document.getElementById('div3').style.display = 'block';
	   //window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   window.location = result;
	   //window.document.getElementById('t2').value=""+result;
   }
}

function accessToken()
{
    var q_str = 'code='+Code;
	doAjax('MyDBox',q_str,'accessToken_back','get',0);
}

function accessToken_back(result)
{
	if (result.substring(0,5)=='error'){
	   //window.document.getElementById('div3').style.display = 'block';
	   //window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   //window.location = result;
	   window.document.getElementById('t2').value=""+result;
   }
}



function convertPrice()
{
    window.document.getElementById('div3').style.display = 'none';
	var q_str = 'type=price&value='+document.getElementById('t3').value;
	doAjax('MyConvertor_Servlet',q_str,'convertPrice_back','post',0);
}
function convertPrice_back(result)
{
	if (result.substring(0,5)=='error'){
	   window.document.getElementById('div3').style.display = 'block';
	   window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   window.document.getElementById('t4').value=""+result;
   }
}

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

function getAccount()
{
    var q_str = 'code='+Code;
	doAjax('MyDBox',q_str,'getAccount_back','get',0);
}

function getAccount_back(result)
{
	if (result.substring(0,5)=='error'){
	   //window.document.getElementById('div3').style.display = 'block';
	   //window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   //window.location = result;
	   window.document.getElementById('t2').value=""+result;
   }
}


function uploadFile()
{
	var q_str = 'type=file&path='+document.getElementById('f1').value;
	doAjax('MyDBox',q_str,'uploadFile_back','post',0);
}

function uploadFile_back(result)
{
	if (result.substring(0,5)=='error'){
	   //window.document.getElementById('div3').style.display = 'block';
	   //window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   window.document.getElementById('t1').value=""+result;
   }
}

function getUserStorage()
{
	var q_str = 'type=storage';
	doAjax('MyDBox',q_str,'getUserStorage_back','post',0);
}

function getUserStorage_back(result)
{
	if (result.substring(0,5)=='error'){
	   //window.document.getElementById('div3').style.display = 'block';
	   //window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
   }else{
	   window.document.getElementById('t3').value=""+result;
   }
}


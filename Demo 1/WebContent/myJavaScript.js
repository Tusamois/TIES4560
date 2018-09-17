function doQueryCountry_back(result){
	window.document.getElementById("result").value = result;
	//window.document.getElementByID("resultCity").value = city;
}

function doQueryCountry(){
	//var IP = window.document.getElementById('IP').value;
	var q_str = 'IP='+document.getElementById('IP').value;
	doAjax("MyServlet", q_str, "doQueryCountry_back", "post", 0);
}

/*function doQueryW(){
	//var IP = window.document.getElementById('IP').value;
	var q_str = 'City='+document.getElementById('City').value;
	doAjax("MyServlet", q_str, "customize", "post", 0);
}*/

/*function doQueryCapital_back(result){
	//window.document.getElementById("result").value = result;
	window.document.getElementByID("resultCity").value = city;
}

function doQueryCapital(){
	//var IP = window.document.getElementById('IP').value;
	var q_str = 'capital='+document.getElementById('result').value;
	doAjax("MyServlet", q_str, "doQueryCapital_back", "post", 0);
}*/
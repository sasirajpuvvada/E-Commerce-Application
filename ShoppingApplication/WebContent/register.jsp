<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="index.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" type="text/javascript"></script>
</head>


<script>

$(document).ready(function(){
	
	$("#username1").change(function(){
	
		var myuser = $("#username1").val();
		
		$.ajax({
			type:"POST",
			data:{myuser:myuser},
			url:"checkusers",
			success: function(result){
				$("#uservalid").html(result);
			}
		}); 
		
	});
	
});

</script>

<script>

function validate() {
	
	var user = document.getElementById('username1').value;
	var pass1 = document.getElementById('password1').value;
	var pass2 = document.getElementById('password2').value;
	var phone = document.getElementById('phone').value;
	var address = document.getElementById('address').value;
	
	if(user=="") {
		document.getElementById('uservalid').innerHTML="please enter valid username";
		return false;
	} else if(user.length<2 || user.length>10){
		document.getElementById('uservalid').innerHTML="username should contain 2 to 10 charachter";
		return false;
	} else{
		document.getElementById('uservalid').innerHTML="";
	}
	
	if(pass1=="") {
		document.getElementById('passvalid').innerHTML="please enter valid password";
		return false;
	} else if(pass1.length<2 || pass1.length>10) {
		document.getElementById('passvalid').innerHTML="password should contain 2 to 10 charachter";
		return false;
	} else {
		document.getElementById('passvalid').innerHTML="";
	}
	if(pass2=="") {
		document.getElementById('passvalid2').innerHTML="please enter confirm password";
		return false;
	} else {
		document.getElementById('passvalid2').innerHTML="";
	}
	
	if(pass1!=pass2){
		document.getElementById('passvalid2').innerHTML="passwords does not match";
		return false;
	} else {
		document.getElementById('passvalid2').innerHTML="";
	}
	
	if(phone=="") {
		document.getElementById('phonevalid').innerHTML="please enter valid phone";
		return false;
	} else if(isNaN(phone)) {
		document.getElementById('phonevalid').innerHTML="please enter digits only";
		return false;
	} if(phone.length!=10) {
		document.getElementById('phonevalid').innerHTML="Number must contain 10 digits";
		return false;
	} else {
		document.getElementById('phonevalid').innerHTML="";
	}
	
	if(address=="") {
		document.getElementById('addvalid').innerHTML="please enter valid address";
		return false;
	} else {
		document.getElementById('addvalid').innerHTML="";
	}
	
}

</script>




<body>


<div class="login-box">
<form action="Register" method="POST" onsubmit="return validate()">
<div class="textbox"><input type="text" name="username1" id="username1" placeholder="Username" autocomplete="off"/>  <span id="uservalid" class="valid"></span> </div>
<div class="textbox"><input type="password" name="password1" id="password1" placeholder="Password" autocomplete="off"/>	<span id="passvalid" class="valid"></span></div>
<div class="textbox"><input type="password" name="password2" id="password2" placeholder="Confirm Password" autocomplete="off"/>	<span id="passvalid2" class="valid"></span></div>
<div class="textbox"><input type="text" name="phone" id="phone" placeholder="Phone" autocomplete="off"/>	<span id="phonevalid" class="valid"></span></div>
<div class="textbox"><input type="text" name="address" id="address" placeholder="Address" autocomplete="off"/>	<span id="addvalid" class="valid"></span></div>
<input class="btn" type="submit" value="Register"/>
</form>
</div>

</body>
</html>
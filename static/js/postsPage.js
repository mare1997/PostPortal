var userId = "";
var username="";
var userRole = "";
$(document).ready(function(e) {
	loginStatus();
});
function loginStatus(){
	userId = sessionStorage.getItem("id");
	userRole = sessionStorage.getItem("userRole");
	console.log("current user id: "+ userId);
	console.log("current userrole: "+ userRole);
	
	if(userId != 'null' && userId != null){
		console.log("Nije null");
		
		$('#loggeduser').text(userRole);
		var y = document.getElementById("navBarNotLogIn");
		y.style.display = "none";
		var y = document.getElementById("navBarLogIn");
		y.style.display = "block";
		
		if(userRole != "ADMIN"){
			$('#adminPage').hide();
			
			if(userRole !="PUBLISHER"){
				$("#newPost").hide();
				$("#deletePostNav").hide();
				$("#editPostNav").hide();
			}
		}
	}else{
		console.log("jeste null");
		var y = document.getElementById("navBarNotLogIn");
		y.style.display = "block";
		var y = document.getElementById("navBarLogIn");
		y.style.display = "none";
		
	}
	
}
function login(){
	console.log("usao u login")
	var username =  $('#username').val().trim();
	var password = $('#password').val().trim();
	if(username=="" || password==""){
		alert("All fields must be filled.")
		return;
	}
	$.ajax({
		type: 'GET',
        url: 'http://localhost:8080/osa/users/get/'+username+'/'+password,
		cache: false,
        success: function (response) {
        	if (typeof(Storage) !== "undefined") {
        	    sessionStorage.setItem("id", response.id);
        	    username = response.username;
        	    sessionStorage.setItem("userRole", response.userType);
        	    console.log(response.id,response.userType)
        	    location.reload();
        	} else {
        	    alert("Sorry, your browser does not support Web Storage...");
        	}
        	$('#loginModal').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status="404"){
				alert("That user doesnt exist.");
			}
			$('#loginModal').modal('toggle');
		}
    });
}


function logout(){
	console.log("logout");
    sessionStorage.setItem("id", null);
    sessionStorage.setItem("userRole", null);
    window.location.href = "postsPage.html";
}
function registerE(){
	$('#pictureANU').hide();
	$('#pictureUploadCheckerAddNewUserByAdmin').prop('checked', false);
}
function uploadCheckerRegister(){
	$('#pictureANU').toggle();
}
function register(){
	var username =  $('#regUsername').val().trim();
	var password = $('#regPassword').val().trim();
	var name =  $('#regName').val().trim();
	var photo =  $('#regPicture')[0].files[0];
	
	if(username=="" || password=="" || name==""){
		alert("All fields must be filled");
		return;
	}
	var checked = false;
	if($('#regPicUploadCheck').prop('checked')){
		checked=true;
	}
	if(checked == true && typeof photo == 'undefined'){
		alert("You must select a photo.");
		return;
	}
	console.log(username+" "+password+" "+name+" ");
	var data={
			'username':username,
			'password':password,
			'name':name,
			'userType':"USER"
	}
	
	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/osa/users/add',
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {
        	if(checked==true){
        		uploadImg(response.id,photo);
        	}
        	alert("Registration successful.")
        	$('#registerModal').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="403"){
				alert("Username already taken.");
			}
			
		}
    });
}
function uploadImg(id,photo){
	var data=new FormData();
	data.append('id',id);
	data.append('photo',photo);
	$.ajax({
		type: 'POST',
        contentType: false,
        url: 'http://localhost:8080/osa/users/upload_photo',
        data: data,
        dataType: false,
		cache: false,
		processData: false,
        success: function (response) {
        	console.log("Picture user upload success...");
           
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}
function format(tempDate) {
	var date = new Date(tempDate);
	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();
	var monthNames = [
		  "January", "February", "March",
		  "April", "May", "June", "July",
		  "August", "September", "October",
		  "November", "December"
		];
	
	return day + '. ' + monthNames[monthIndex] + ' ' + year+'.';
}
function getRandomCordinate() {
	var to = 180;
	var from = -180;
	var fixed = 4;
    return (Math.random() * (to - from) + from).toFixed(fixed) * 1;
    // .toFixed() returns string, so ' * 1' is a trick to convert to number
}


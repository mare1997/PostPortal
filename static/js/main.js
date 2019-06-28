
$(document).ready(function() {
	if(userId == "null" || userRole !="ADMIN"){
		window.location.href = "postsPage.html";
	}
	loadUsers();
});

function loadUsers(){
	$.ajax({
		type: 'GET',
        url: "http://localhost:8080/osa/users/",
        dataType: 'json',
		cache: false,
        success: function (response) {
        	
			var usersDiv = $('#usersRow');
			usersDiv.empty();
			for(var i=0; i<response.length; i++) {
				user = response[i];
				if(user.id == userId){
					continue;
				}

				var userPhoto = 'http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640-300x300.png';
				if(user.photo!=null){
					userPhoto = 'data:image/gif;base64,'+user.photo;
				}
				console.log(user)
				usersDiv.append(
						'<div class="col-md-12 col-sm-12 col-xs-12">'+
		                '<ul id="info">'+
		                '<li><img src="'+userPhoto+'" alt="Lights" style="width:130px;height: 130px">'+
		               '<h3>'+user.name+'</h3></a></li>'+
		                '<li><p>'+"Username: "+user.username +'</p></li>'+
		                '<li><p>'+"Password: "+user.password+'</p></li>'+
		                '<li><p>'+"User Type: "+user.userType+'</p></li>'+
		                '<li>'+
		                	'<button type="button" class="dropdown-toggle btn btn-default" data-toggle="dropdown" id="optionsForAdminOrOwners"><span class="glyphicon glyphicon-menu-hamburger"></span></button>'+
							'<ul class="dropdown-menu" id="optionsForOwnerAndAdmin">'+
					        	'<li id="deleteUser"><a href="#deleteUserModal" data-toggle="modal" onclick="takeUserId('+ user.id+')">Delete user</a></li>'+
					        	'<li id="editUser"><a href="#editUserByAdmin" data-toggle="modal" onclick="editUserE('+ user.id+')">Edit user</a></li>'+
					        '</ul>'+
		                '</li>'+
		                '</ul>'+
		               ' </div>'
						
						
				);
			}
           
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}
var userId=0;
function takeUserId(uid){
	userId=uid;
}
function deleteUser(){
	console.log(userId);
	$.ajax({
        url: 'http://localhost:8080/osa/users/delete/'+userId,
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("user delete success: ");
        	
        	$('#deleteUserModal').modal('toggle');
        	loadUsers();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}
function uploadCheckerEditUserByAdmin(){
	$('#pictureUE').toggle();
}
function editUserE(uid){
	userId=uid;
	$('#pictureUE').hide();
	$('#pictureUploadCheckerEditUserByAdmin').prop('checked', false);
	$('#passwordEditUserByAdmin').val("");
	$('#nameEditUserByAdmin').val("");
	$('#roleSelect').val("");
}
function editUserByAdmin(){
	var password = $('#passwordEditUserByAdmin').val().trim();
	var name = $('#nameEditUserByAdmin').val().trim();
	var photo = $('#pictureUE')[0].files[0];
	var userType = $('#roleSelect').val();
	if(password=="" || name==""){
		alert("All fields must be filled");
		return;
	}
	var checked = false;
	if($('#pictureUploadCheckerEditUserByAdmin').prop('checked')){
		checked=true;
	}
	
	if(checked == true && typeof photo == 'undefined'){
		alert("You must select a photo.");
		return;
	}
	console.log(+password+" "+name+" "+userType);
	var data={
			'password':password,
			'name':name,
			'userType':userType
	}
	console.log(data);
	$.ajax({
		type: 'PUT',
        contentType: 'application/json',
        url: 'http://localhost:8080/osa/users/update/'+userId,
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {
        	console.log(checked);
        	if(checked==true){
        		uploadImg(response.id,photo);
        	}
        	
        	$('#editUserByAdmin').modal('toggle');
        	loadUsers();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="403"){
				alert("Username already taken.");
			}
			$('#editUserByAdmin').modal('toggle');
		}
    });
}
function editProfileE(){
	$('#passwordEditUser').val("");
	$('#nameEditUser').val("");
	$('#picture').hide();
	$('#pictureUploadCheckerEditUser').prop('checked', false);
}
function uploadCheckerEditUser(){
	$('#picture').toggle();
}
function editProfile(){
	var password =  $('#passwordEditUser').val().trim();
	var name = $('#nameEditUser').val().trim();
	var photo =  $('#picture')[0].files[0];
	if( name=="" || password==""){
		alert("All fields must be filled");
		return;
	}
	var checked = false;
	if($('#pictureUploadCheckerEditUser').prop('checked')){
		checked=true;
	}
	if(checked == true && typeof photo == 'undefined'){
		alert("You must select a photo.");
		return;
	}
	var data={
			'name':name,
			'password':password,
			'userType':userRole
	}
	$.ajax({
		type: 'PUT',
        contentType: 'application/json',
        url: 'http://localhost:8080/osa/users/update/'+userId,
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {
        	if(checked==true){
        		console.log(response.id)
        		console.log(photo)
        		uploadImg(response.id,photo);
        	}
        	$('#postAuthor').text("Author: "+response.name)
        	$('#editUser').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
		}
    });
}
function addNewUserE(){
	$('#pictureANU').hide();
	$('#pictureUploadCheckerAddNewUserByAdmin').prop('checked', false);
}
function uploadCheckerAdd(){
	$('#pictureANU').toggle();
}
function addNewuserbyAdmin(){
	var username = $('#usernameAddNewUserByAdmin').val().trim();
	var password = $('#passwordAddNewUserByAdmin').val().trim();
	var name = $('#nameAddNewUserByAdmin').val().trim();
	var photo = $('#pictureANU')[0].files[0];
	var userType = $('#roleSelect').val();
	if(username=="" || password=="" || name==""){
		alert("All fields must be filled");
		return;
	}
	var checked = false;
	if($('#pictureUploadCheckerAddNewUserByAdmin').prop('checked')){
		checked=true;
	}
	if(checked == true && typeof photo == 'undefined'){
		alert("You must select a photo.");
		return;
	}
	var data={
			'username':username,
			'password':password,
			'name':name,
			'userType':userType
	}
	
	console.log(data);
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
        	
        	$('#AddNewUserByAdmin').modal('toggle');
        	loadUsers();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			if(jqXHR.status=="403"){
				alert("Username already taken.");
			}
			
		}
    });
}


/**
 * 
 */

function IndexJspValidation(){
		var email_id = document.getElementById('email_id').value;
		var password = document.getElementById('password').value;
		var atpos = email_id.indexOf("@");
	    var dotpos = email_id.lastIndexOf(".");
		
		if(email_id == "" || email_id == null){
				
			alert("Please Enter Email ID");
			document.getElementById('email_id').focus();
			return false;
		}
		else if(atpos<1 || dotpos<atpos+2 || dotpos+2>=email_id.length){
			 alert("Not a valid e-mail address");
			 document.getElementById('email_id').focus();
			 return false;
		}
		else if(password == "" || password == null){
			alert("Please Enter Password");
			document.getElementById('password').focus();
			return false;
			
		}
	return true;
}


function CreateTaskValidation(){
 var TaskName = document.getElementById('TaskName').value;
 var description = document.getElementById('description').value;
 var Starting_date=  document.getElementById('Starting_date').value;
 var ending_date = document.getElementById('ending_date').value;
 
 if(TaskName == "" || TaskName == null){
	 alert("Please Enter Task Name");
	 document.getElementById('TaskName').focus();
	 return false;
 }
 else if(description == "" || description == null){
	 alert("Please Enter Description");
	 document.getElementById('description').focus();
	 return false;
}
 else if(Starting_date == null || Starting_date == null){
	 	alert("Please Enter Starting Date");
	 	document.getElementById('Starting_date').focus();
	 	return false;
 }
 else if(ending_date == null || ending_date == ""){
	 alert("Please Enter Ending Date");
	 document.getElementById('ending_date').focus();
	 return false;
}
 return true;
}


function UserRegValidation(){
	var email_id = document.getElementById('email_id').value;
	var password = document.getElementById('password').value;
	var user_role = document.getElementById('user_role').value;
	
	var atpos = email_id.indexOf("@");
    var dotpos = email_id.lastIndexOf(".");
	
	if(email_id == "" || email_id == null || atpos<1 || dotpos<atpos+2 || dotpos+2>=email_id.length){
		alert("Please Enter Valid Email ID Address");
		document.getElementById('email_id').focus();
		return false;
		
	}
	else if(password == "" || password == null){
		alert("Please Enter Password");
		document.getElementById('password').focus();
		return false;
	}
	else if(user_role == -1){
		alert("Please Select User Role");
		document.getElementById('user_role').focus();
		return false;
	}
	return true; 
}



<%@page import="com.bean.Login"%>
<%@page import="com.model.ServiceModel"%>
<%@page import="com.bean.TaskManagement"%>
<%@page import="java.util.ArrayList"%>
<%@include file="sessionexpreid.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!private String admin; %>
<%!private String user; %>
<%
admin=(String) session.getAttribute("admin");
user=(String)session.getAttribute("user");
	if(admin!=null){
	%>		
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<p>Welcome,<%=admin %> & <a href="Logout">Logout</a></p>
<a href="NewUser.jsp">New User</a><br/>
<a href="TaskDetails.jsp">Task Details</a>

</body>
	
	</html>
		
		
		
	<%}
	else if(user !=null ){
	%>		
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
var request; 
function updateTask(x,id){
var r1= x.parentNode.parentNode;
var cell0=r1.cells[0].innerHTML;
var cell1= r1.cells[1].innerHTML;
var cell2=r1.cells[2].innerHTML;
var cell3 = r1.cells[3].innerHTML;

document.getElementById('TaskName').value = cell0.trim();
document.getElementById('description').value = cell1.trim();
document.getElementById('Starting_date').value = cell2.trim();
document.getElementById('ending_date').value = cell3.trim();
document.getElementById('task_id').value = id;
document.getElementById('Submit').value = "Update";

}

function DeleteTask(id) {
	var url="TaskController?Submit=Delete&task_id="+id;
	if(confirm("Are you sure want to Delete?") == true){
		if(window.XMLHttpRequest){  
			request=new XMLHttpRequest();  
			}  
			else if(window.ActiveXObject){  
			request=new ActiveXObject("Microsoft.XMLHTTP");  
			}  
		try  
		{  
		//request.onreadystatechange=getInfo;  
		request.open("GET",url,true);  
		request.send();  
		alert("Record Delete Successfully");
		window.location.reload();
		
		}  
		catch(e)  
		{  
		alert("Unable to connect to server");  
		}	
	
	}
	else{

		}
	
}

function ResetAll(){
	document.getElementById('Submit').value='Submit';
	document.getElementById('task_id').value='';
	
}




</script>
</head>
<body>
<p>Welcome,<%=user %> & <a href="Logout">Logout</a></p>
<a href="AllotedWork.jsp">See Your Alloted Work</a>
<a href="Create Task.jsp">Create Task</a>
<%!private ArrayList<TaskManagement> arrayList; %>

<%! private ServiceModel serviceModel; %>

		
		
		
		
	</body>
	
	</html>	
		
	<%
	}
	else{
		response.sendRedirect("index.jsp");
	}


%>




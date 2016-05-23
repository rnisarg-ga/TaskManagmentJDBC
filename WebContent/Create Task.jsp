<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.bean.TaskManagement"%>
 <%@page import="java.util.ArrayList"%>
 <%@page import="com.model.ServiceModel"%>
 <%@include file="sessionexpreid.jsp" %>
 <%@page import="com.bean.Login"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!private String admin; %>
<%!private String user; %>

<%

user=(String)session.getAttribute("user");

if(user!=null){
%>	
	
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/Validation.js"></script>
<script type="text/javascript">
var request; 
function updateTask(x,id){
var r1= x.parentNode.parentNode;
var cell0=r1.cells[1].innerHTML;
var cell1= r1.cells[2].innerHTML;
var cell2=r1.cells[3].innerHTML;
var cell3 = r1.cells[4].innerHTML;

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
<%!private ArrayList<TaskManagement> arrayList; %>

<%! private ServiceModel serviceModel; %>

<p>Welcome,<%=user %> & <a href="Logout">Logout</a></p>
		<form action="TaskController" method="post" onsubmit="return CreateTaskValidation()">
		<input type="hidden" name="task_id" id="task_id"/> 
		<table >
		<tr>
		<td>Your Task Name</td>
		<td><input type="text" name="TaskName" id="TaskName" placeholder="Enter Your Task Name"/>  </td>
		</tr>
		
		
		<tr>
		<td>Description</td>
		<td>
		<textarea name="description" id="description" placeholder="Enter Your Description">
		</textarea>
		
		 </td>
		</tr>
		
		<tr>
		<td>Starting Date</td>
		<td>
		<input  type="text" name="Starting_date" id="Starting_date" placeholder="Starting Date"/> 
		</td>
		
		</tr>
		
		<tr>
		<td>
		Ending Date
		</td>
		<td>
		<input  type="text" name="spend_Time" id="ending_date" placeholder="Ending Date"/> 
		</td> 
		</tr>
		
		<tr>
			<td>Add Member</td>
			<td>
			
				<select name="user_role" id="user_role" multiple="multiple">
				<%
				
					try{
						
						ArrayList<Login> loginList = serviceModel.GetUserList(user);
						for(Login login : loginList){
						%>	
						
						<option value="<%=login.getEmail_id() %>"><%=login.getEmail_id() %> </option>	
							
						<% }
					}
					catch(Exception e){e.printStackTrace();}				
				
				%>
				
				</select>
			</td>
		</tr>
		
		<tr>
		<td> </td>
		<td>
		<input type="submit" name="Submit" id="Submit" value="Submit"/> 
		<input type="reset" name="Reset" id="Reset" value="Reset" onclick="ResetAll()"/> 
		</td>
		</tr>
		
		
		</table>
		</form>
		
		<table border="1">
		<thead>
		<tr>
		<th> </th>
		
		<th>Task Name</th>
		<th>Description</th>
		<th>Starting Date</th>
		<th>Ending Date</th>
		<th>Allowed Memeber</th>
		<th>Operation</th>
		</tr>
		
		</thead>
		<tbody>
			<%
			try{
				serviceModel = new ServiceModel();
				arrayList= serviceModel.getUserTask(user);
				for(TaskManagement management : arrayList){
				%>	
					<tr>
					<td><a href="TaskDetails.jsp?task_id=<%=management.getTask_id()%>">Click Here</a> </td>
					<td> <%=management.getTaskName() %> </td>
					<td><%=management.getDescription() %> </td>
					<td><%=management.getStarting_date() %> </td>
					<td><%=management.getEnding_date()%> </td>
					<td>
					<%
					try{
						ArrayList<Login> list = serviceModel.GetMemeberList(management.getTask_id());
						for(Login login : list){
							out.println(login.getEmail_id()+"-"); 
						
						}
						
						
					}catch(Exception e){e.printStackTrace();}
					
					%>
					</td>
					<td><a href="#"  onclick="updateTask(this,<%=management.getTask_id()%>)">Update</a> ||<a href="#"  onclick="DeleteTask(<%=management.getTask_id()%>)">Delete</a> </td>
					</tr>
					
					
				<%
				}
				
				
			}
			catch(Exception e){e.printStackTrace();} 
			
			
			
			%>
		
		
		
		</tbody>
		
		</table>

</body>
</html>


	


<% }
else {
	response.sendRedirect("index.jsp");
	
}
%>

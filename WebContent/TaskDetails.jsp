<%@page import="com.bean.TaskTiming"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.ServiceModel"%>
<%@include file="sessionexpreid.jsp" %>
<%@page import="com.bean.TaskManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%!private String admin; %>
<%!private String user; %>
<%!private TaskManagement management;  %>
<%!private ServiceModel serviceModel;   %>
<%!private ArrayList<TaskManagement> arrayList;  %> 
<%!private String task_id; %>

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
<p>Task Details</p>

<table border="1">
	<thead>
		<tr>
			<th>Task Name</th>
			<th>Task Description </th>
			<th>Task Starting Date </th>
			<th>Task Ending Date </th>
			<th>Total Time Spend </th>
			
			
		</tr>
	
	</thead>
	<tbody>
		<%
		try{
			serviceModel = new ServiceModel();
			management = new TaskManagement();
			arrayList = serviceModel.GetAllTaskManagement();
			
			for(TaskManagement management : arrayList){
			%>	
			<tr>	
			<td><a href="WorkLog.jsp?task_id=<%=management.getTask_id()%>"><%=management.getTaskName()%></a>  </td>
			<td><%=management.getDescription()%> </td>
			<td><%=management.getStarting_date() %> </td>
			<td><%=management.getEnding_date() %> </td>
			<td>
			<%
				try{
					ArrayList<TaskTiming> list = serviceModel.TotalSpendTime(management.getTask_id());
					String pattern="[a-zA-Z]\\s";
					String d=null,h=null,m=null;
					Integer days=0,hours=0,minuets=0;
				
					for(TaskTiming taskTiming : list){
								String[] t1= taskTiming.getSpend_Time().split(pattern);
								for(int i=0;i<t1.length;i++){
										d=t1[0];
										h=t1[1];
										m=t1[2];
								}	
								
								String[] t2= m.split("[a-zA-Z]");
								for(String t3 : t2){
										m = t3;
									
								}	
								
								minuets += Integer.parseInt(m);
								hours += Integer.parseInt(h);
								days += Integer.parseInt(d);
						}
						
						if(minuets >= 60){
								hours += minuets / 60;
								minuets = minuets % 60;
							
						}	
						if(hours > 8){
								days += hours / 8;
								hours = hours % 8;
							
						}
					
						out.println(days.toString()+"D "+hours.toString()+"H "+minuets.toString()+"M");
					
					

				}catch(Exception e){e.printStackTrace();}
			
			
			%>
			
			
			</td>
				</tr>
				
				
			<%}
		}catch(Exception e){e.printStackTrace();}
		
		
		
		
		%>
		
	
	</tbody>



</table>


</body>
</html>
	
	
	
<%}
else if(user !=null){
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

<p>
Task Details
</p>

<%
task_id = request.getParameter("task_id");

try{
	management = new TaskManagement();
	serviceModel = new 	ServiceModel();
	arrayList =  serviceModel.TaskDetails(task_id);
	for(TaskManagement management :arrayList)
	{
	%>
		<table>
			<tr>
			<td>Task Name</td>
			<td> <%=management.getTaskName() %></td>
			</tr>
			<tr>
			<td>Description</td>
			<td><%=management.getDescription() %> </td>
			
			</tr>
			<tr>
			<Td>Starting Date</Td>
			<td><%=management.getStarting_date() %> </td>
			</tr>
			<tr>
			<td>Ending Date</td>
			<td><%=management.getEnding_date()%> </td>
			</tr>
			
		</table>
		
	<%}	
}
catch(Exception e){e.printStackTrace(); }

%>

<br/>
<a href="WorkLog.jsp?task_id=<%=task_id%>">Add WorkLog</a>


<p>WorkLog Details</p>
<table border="1">
 <thead>
    <tr>
      <th>Email ID</th>
      <th>Starting Time</th>
      <th>
      Time Spend Task
      </th>
    </tr>
  </thead>


<%
	try{
		ArrayList<TaskTiming> arrayList = serviceModel.TaskTimingDetails(task_id);
		for(TaskTiming taskTiming : arrayList){
			
		%>	
		<tbody>
			
			<tr>
			<td><%=taskTiming.getLogin().getEmail_id() %></td>
				<td><%=taskTiming.getStarting_date() %></td>
			<td><%
				String delimiter = "[a-zA-Z]" + "\\s";
				String[] temp= taskTiming.getSpend_Time().split(delimiter);
				Integer days=0,hours=0,mintes=0;
				mintes = Integer.parseInt(temp[2].replace("M", ""));
				for(int i=0;i<temp.length;i++){
					days= Integer.parseInt(temp[0]);
					hours= Integer.parseInt(temp[1]);
				}
				
				if(mintes >=60){
						hours += mintes /60;
						mintes = mintes %60;
					
				}
				if(hours > 8){
						days += hours /8;
						hours = hours /8;
					
				}
				out.println(days.toString()+"D "+hours+"H "+mintes+"M");
			
			
			
			%></td>  
			
			</tr>
		
			
			
			</tbody>
			
		<%
			}	
	
		
		
	}
catch(Exception  e){
		e.printStackTrace();
	
}


%>
	

</table>



</body>
</html>
	
<% }
else {
	
	response.sendRedirect("index.jsp");
}

%>


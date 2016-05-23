<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@include file="sessionexpreid.jsp" %>
 <%@page import="com.bean.TaskTiming"%>
 <%@page import="java.util.ArrayList"%>
<%@page import="com.model.ServiceModel"%>
 <%@page import="com.bean.TaskManagement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!private TaskManagement management;  %>
<%!private ServiceModel serviceModel;   %>
<%!private ArrayList<TaskManagement> arrayList;  %> 

<%!private String admin; %>
<%!private String user; %>
<%!private String task_id; %>

<% 
admin=(String) session.getAttribute("admin");
user=(String)session.getAttribute("user");


%>

<% 
if(admin!=null){
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Welcome,<%=admin %> & <a href="Logout">Logout</a></p>
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
			<td>
			<%
				String[] temp = taskTiming.getSpend_Time().split("[a-zA-Z]"+"\\s");
				Integer days=0,hours=0,minutes=0;
				minutes = Integer.parseInt(temp[2].replace("M", ""));
				for(int i=0;i<temp.length;i++){
					days = Integer.parseInt(temp[0]);
					hours  = Integer.parseInt(temp[1]);
					
				}
				
				if(minutes >=60){
						hours += minutes/60;
						minutes =  minutes%60;
				}
				if(hours >8){
						days += hours /8;
						hours = hours % 8;
					
				}
				
				out.println(days.toString()+"D "+hours+"H "+minutes+"M");
			
			
			%>
			</td>  
			
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


<%}
else if(user!=null){
%>	
	
	
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function TimeFormat(seconds){
	//var seconds = parseInt(document.getElementById('seconds').value);
	/*seconds = Math.floor(seconds/1000);
	var minutes = Math.floor(seconds /60);
	seconds = seconds %60;
	var hours = Math.floor(minutes / 60);
	minutes = minutes % 60;
	var days = Math.floor(hours / 24);
	hours = hours %24;*/
			

	var numdays = Math.floor(seconds / 86400);

	var numhours = Math.floor((seconds % 86400) / 3600);

	var numminutes = Math.floor(((seconds % 86400) % 3600) / 60);

	var numseconds = ((seconds % 86400) % 3600) % 60;
	
	
	document.getElementById('minutes').value =numminutes;
	
	document.getElementById('hours').value = numhours;
	document.getElementById('days').value = numdays;
	
	
	
	//var isValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/.test(inputField.value);
	
	
}
function Days(){
	
var minutes =  parseInt(document.getElementById('minutes').value);
var hours = Math.floor(minutes/60);

var days=Math.floor(hours / 24);

if(hours > 8){
	days = Math.floor(days+1);
	hours =0;
}


minutes = Math.floor(minutes % 60);
hours = Math.floor(hours % 24);




document.getElementById('minutes').value= minutes;
document.getElementById('hours').value = hours;
document.getElementById('days').value = days;

}

function ondatechange(){
var static_date =  document.getElementById('static_date').value;
var days = parseInt(static_date.substring(0,2));
var hours = parseInt(static_date.substring(2,5));
//var minutes =parseInt(static_date.substring(5,8));
var minutes = parseInt(static_date.substring(5,7));

if(minutes >= 60)
{
		minutes = Math.floor(minutes % 60);
		hours = Math.floor(hours+1);
}

if(hours > 8){
	 hours = Math.floor(hours % 24);
	 days = Math.floor(days+1);

}
alert("Days :- "+days+"Hours :-"+hours+"Minutes :-"+minutes);

	
}


</script>

<body>
<p>Welcome,<%=user %> & <a href="Logout">Logout</a></p>
<form action="TaskTimingController" method="post">
<input type="hidden" name="task_id" value="<%=request.getParameter("task_id")%>"/>
<table>
<tr>
<td>Starting Time</td>
<td>
<input type="text" name="Startingtime" placeholder="Starting Time" placeholder="Starting Time"/> 
</td>
</tr>

<tr>
<td>Time Spend For Task </td>
<td>
<input type="text" name="spendtime" id="static_date"   placeholder="Format 1D 1H 1M"/> 
<!--  
<input type="text" name="days" id="days" placeholder="Days" size="3"/>:
<input type="text" name="hours" id="hours" placeholder="hours" size="3"/>:
<input type="text" name="minutes" id="minutes" placeholder="minutes" size="3" onblur="Days()" />
  <input type="text" name="seconds" id="seconds" placeholder="seconds" size="3" onkeyup="TimeFormat(this.value)"/>  -->
</td>
</tr>


<tr>
<td></td>
<td>
<input type="submit" name="Submit" id="Submit" value="Submit"/> 
<input type="reset" name="Reset" id="Reset" value="Reset"/> 
</td>
</tr>


</table>

</form>



</body>
</html>
	
<%
}
else {
	response.sendRedirect("index.jsp");
	
}

%>

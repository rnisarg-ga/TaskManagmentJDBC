<%@page import="java.util.ArrayList"%>
<%@page import="com.model.ServiceModel"%>
<%@page import="com.bean.TaskTiming"%>
<%@include file="sessionexpreid.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!private String admin; %>
<%!private String user; %>
<%!private TaskTiming taskTiming ;%>
<%!private ServiceModel serviceModel; %>
<%!private ArrayList<TaskTiming> arrayList; %>

<% 
user=(String)session.getAttribute("user");
if(user!=null){
%>	
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>Welcome,<%=user %> & <a href="Logout">Logout</a></p>
<table>
<thead>

<tr>
<th>Task Name</th>
<th>Description</th>
<th>Starting Date</th>
<th>Ending Date</th>

</tr>

</thead>
<%

try{
	taskTiming = new TaskTiming();
	serviceModel = new ServiceModel();
	arrayList = serviceModel.AllotedTaskList(user);
	for(TaskTiming taskTiming : arrayList){
	%>
	<tr>
		<td>
		<a href="TaskDetails.jsp?task_id=<%=taskTiming.getManagement().getTask_id() %>"><%=taskTiming.getManagement().getTaskName() %></a></td>
		<td><%=taskTiming.getManagement().getDescription()  %></td>
<td><%=taskTiming.getManagement().getStarting_date() %> </td>
<td><%=taskTiming.getManagement().getEnding_date() %> </td>

	
	</tr>	
		
		
		
	<%}
	
}catch(Exception e){e.printStackTrace();}



%>

<tbody>


</tbody>

</table>


</body>
</html>


	
	
<%
}
else{
	response.sendRedirect("index.jsp");
	
}



%>

<%@page import="com.bean.UserRole"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.ServiceModel"%>
<%@include file="sessionexpreid.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!private String admin; %>
<%!private String user; %>
<%!private ServiceModel  model; %>

<%
admin=(String) session.getAttribute("admin");
user=(String)session.getAttribute("user");
	if(admin!=null){
	%>	
		
	
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"  src="js/Validation.js"></script>
</head>
<body>
<p>Welcome,<%=admin %> & <a href="Logout">Logout</a></p>
<%
	model= new ServiceModel();
	
%>
<p>User Registration</p>
<form action="RegistrationController" method="post" onsubmit="return UserRegValidation()">
<table>
<tr>
<td>Email ID</td>
<td>
<input type="text" name="email_id" id="email_id" placeholder="Email ID"/> 
</td>
</tr>
<tr>

<td>Password</td>
<td>
<input type="password" name="password" id="password" placeholder="Password"/> 
</td>
</tr> 
<tr>
<td>Select Role</td>
<td>
<select name="user_role" id="user_role">
<option value="-1">--Select User Role--</option>
<% 
try{
ArrayList<UserRole> arrayList = model.GetAllUserRole();
for(UserRole role: arrayList){
%>	

<option value="<%=role.getUser_role_id()%>"><%=role.getUser_role()%> </option>
	
	
<% }
 
	
	
	
}catch(Exception e){e.printStackTrace();}  

%>
</td>
</tr>

<tr>
<td></td>
<td>
<input type="submit" name="submit" value="Submit"/>
<input type="reset" name="Reset" value="Reset"/>  
</td>
</tr>

</table>

</form>
</body>
</html>
		


<%	}
	%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.bean.Login"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.ServiceModel"%>
<%@include file="sessionexpreid.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/Validation.js"></script>

</head>
<body>
<%! ServiceModel serviceModel; %>
<%!ArrayList<Login> arrayList ;  %>

<form action="LoginController" method="post" onsubmit="return IndexJspValidation();">
<table>
<tr>
<td>Login ID(Email ID) </td>
<td><input type="text" name="email_id" id="email_id" placeholder="Enter Your Email ID"/> </td>
</tr>


<tr>
<td>Password </td>
<td><input type="password"  name="password" id="password" placeholder="Enter Your password" /> </td>
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
<table >
<tr>
<td>Email ID</td>
<td>password </td>
<td>User Role</td>
</tr>
<% 
try{
	serviceModel = new ServiceModel();
	arrayList =serviceModel.getUserList();
	for(Login login : arrayList){
		%>
		<tr>
<td>
 <%=login.getEmail_id() %>
</td>
<td>
<%=login.getPassword() %>
</td>
<td>
<%=login.getRole().getUser_role() %>
</td>
</tr>
			
		
	<%}
	
}
catch(Exception e){System.out.println(e);}




%>

</table>

</body>
</html>
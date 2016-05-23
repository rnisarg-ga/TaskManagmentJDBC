# TaskManagmentJDBC
Task Management System Using JDBC
These project is worked on Basic Servlet,JSP and JDBC Technologies

There are three user:
1.admin :- will do add user and can see task details
2.User :- can create and alloted task to another user as well as also add work-log details

Worklog Details Format must be :- 1D 1H 1M
user will enter work-log detail and it will calculate worklog


if(minutes>60)
{
	hours += minutes /60;
	minutes = minutes %60;
}

if(hours >8){
	days += hours /8;
	hours = hours %8;

}
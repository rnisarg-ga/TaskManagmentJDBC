package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import com.bean.Login;
import com.bean.TaskManagement;
import com.bean.TaskTiming;
import com.bean.UserRole;
import com.dbconnection.dbconnection;

public class ServiceModel {
	private Connection  con = dbconnection.getConnection();
	private PreparedStatement smt;
	private ResultSet rs;
	private boolean flag =false;

	
	public boolean LoginController(com.bean.Login login){
		try {
			String sql="select count(login.login_id) from login "
					+ "where login_id=? and password=?";
			smt =con.prepareStatement(sql);
			smt.setString(1, login.getEmail_id());
			smt.setString(2, login.getPassword());
			rs = smt.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)>0)
				{
						flag = true;
				}
				else{
					flag = false;
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public ArrayList getUserList(){
		ArrayList<com.bean.Login> arrayList = new ArrayList<com.bean.Login>();
		try {
			String sql="select * from login "
					+ "JOIN user_role ON user_role.user_role_id = login.user_role_id ";
			smt=con.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
				Login login = new Login();
				UserRole role = new UserRole();
				role.setUser_role_id(rs.getInt(4));
				role.setUser_role(rs.getString(5));
				login.setRole(role);
				login.setEmail_id(rs.getString(1));
				login.setPassword(rs.getString(2));
				
				arrayList.add(login);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	
	public boolean AdminController(Login login){
		try {
			String sql="select count(login.login_id) from login "
					+ "JOIN user_role ON user_role.user_role_id = login.user_role_id "
					+ "where login.login_id =? and password=?  and user_role.role_name='admin' ";
			smt =con.prepareStatement(sql);
			smt.setString(1, login.getEmail_id());
			smt.setString(2, login.getPassword());
			rs = smt.executeQuery();
			while(rs.next()){
				
				if(rs.getInt(1)>0)
				{
						flag = true;
				}
				else{
					flag = false;
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
		
	}
	
	
	public boolean UserController(Login login){
		try {
			String sql="select count(login.login_id) from login "
					+ " JOIN user_role ON user_role.user_role_id = login.user_role_id "
					+ "where login_id=? and password=? and user_role.role_name='User' ";
			smt =con.prepareStatement(sql);
			smt.setString(1, login.getEmail_id());
			smt.setString(2, login.getPassword());
			rs = smt.executeQuery();
			while(rs.next()){
				
				if(rs.getInt(1)>0)
				{
						flag = true;
				}
				else{
					flag = false;
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
		
	}
	
	
	public boolean AddTask(com.bean.TaskManagement management,String[] user_role){
		Random randomGenerator = new Random();
		int key=0;
	    for (int idx = 1; idx <= 10000000; ++idx){
	      int randomInt = randomGenerator.nextInt(1000000000);
	     key =randomInt;
	    }
		
		try {
			String sql="insert into task_management(task_id,"
					+ "task_name,"
					+ "Task_description,"
					+ "login_id,"
					+ "task_statring_date,"
					+ "task_ending_date) values(?,?,?,?,?,?)";
			smt = con.prepareStatement(sql);
			smt.setInt(1, key);
			smt.setString(2, management.getTaskName());
			smt.setString(3, management.getDescription());
			smt.setString(4, management.getLogin().getEmail_id());
			smt.setString(5,  management.getStarting_date());
			smt.setString(6,management.getEnding_date());
			smt.executeUpdate();
			
			for(int i=0;i<user_role.length;i++){
					String sql1="insert into task_alloted(task_id,login_id)  values('"+key+"','"+user_role[i]+"')";
					Statement smt= con.createStatement();
					smt.executeUpdate(sql1);
				}
			
			flag = true;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return flag;
		
		
	}
	
	public ArrayList getUserTask(String login_id){
		ArrayList<com.bean.TaskManagement> arrayList = new ArrayList<com.bean.TaskManagement>();
		
		try {
			String sql="select * from task_management where login_id=? ";
			smt = con.prepareStatement(sql);
			smt.setString(1, login_id);
			rs= smt.executeQuery();
			while(rs.next()){
					TaskManagement management = new TaskManagement();
					management.setTask_id(rs.getInt(1));
					management.setTaskName(rs.getString(2));
					management.setDescription(rs.getString(3));
					management.setStarting_date(rs.getString(5));
					management.setEnding_date(rs.getString(6));
					arrayList.add(management);
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arrayList;
		
		
	}
	
	public boolean UpdateUserTask(com.bean.TaskManagement management){
		try {
			String sql="update task_management "
					+ "set task_name=?,"
					+ "Task_description=?,"
					+ "task_statring_date=?,"
					+ "task_ending_date=? "
					+ "where task_id=?";
			smt= con.prepareStatement(sql);
			smt.setString(1, management.getTaskName());
			smt.setString(2, management.getDescription());
			smt.setString(3, management.getStarting_date());
			smt.setString(4, management.getEnding_date());
			smt.setInt(5, management.getTask_id());
			smt.executeUpdate();
			flag = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
		
	}
	
	
	public void DeleteUserTask(int task_id){
		try {
			
			String sql1="delete from task_alloted where task_id =?";
			smt = con.prepareStatement(sql1);
			smt.setInt(1, task_id);
			smt.executeUpdate();
			
			String sql="delete from  task_management where task_id=?";
			smt = con.prepareStatement(sql);
			smt.setInt(1, task_id);
			smt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean AddTaskTiming(com.bean.TaskTiming taskTiming){
		
		try {
			String sql="insert into task_timing1(task_id,task_starting_date,spend_time,login_id) values(?,?,?,?)";
			smt= con.prepareStatement(sql);
			smt.setInt(1, taskTiming.getManagement().getTask_id());
			smt.setString(2, taskTiming.getStarting_date());
			smt.setString(3, taskTiming.getSpend_Time());
			smt.setString(4, taskTiming.getLogin().getEmail_id());
			smt.executeUpdate();
			flag = true;
			
			//System.out.println(taskTiming.getStarting_date()+"-"+ taskTiming.getSpend_Time());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
	
	public ArrayList TaskDetails(String task_id){
		ArrayList<com.bean.TaskManagement> arrayList = new ArrayList<TaskManagement>();
		try {
			String sql="SELECT * FROM hibernatetest.task_management where task_id=?";
			smt = con.prepareStatement(sql);
			smt.setString(1, task_id);
			rs = smt.executeQuery();
			while(rs.next()){
					TaskManagement management = new TaskManagement();
					management.setTaskName(rs.getString(2));
					management.setDescription(rs.getString(3));
					management.setStarting_date(rs.getString(5));
					management.setEnding_date(rs.getString(6));
					arrayList.add(management);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayList;
		
		
	}
	
	public ArrayList TaskTimingDetails(String task_id){
		
		ArrayList<com.bean.TaskTiming> arrayList = new ArrayList<com.bean.TaskTiming>();
		try {
			String sql="select * from task_timing1 where task_timing1.task_id=?";
			smt= con.prepareStatement(sql);
			smt.setString(1, task_id);
			rs = smt.executeQuery();
			while(rs.next()){
					TaskTiming taskTiming = new TaskTiming();
					Login login = new Login();
					com.bean.TaskManagement management = new TaskManagement();
					management.setTask_id(rs.getInt(2));
					
					login.setEmail_id(rs.getString(5));
					taskTiming.setManagement(management);
					taskTiming.setLogin(login);
					taskTiming.setSpend_Time(rs.getString(4));
					taskTiming.setStarting_date(rs.getString(3));
					taskTiming.setTasktiming_id(rs.getInt(1));
					
					arrayList.add(taskTiming);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return arrayList;
		
	}
	
	public ArrayList GetAllUserRole(){
		
		ArrayList<com.bean.UserRole> arrayList = new ArrayList<com.bean.UserRole>();
		try {
			String sql="select * from user_role  where user_role.role_name <>'admin' ";
			smt=con.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
					UserRole role = new UserRole();
					role.setUser_role(rs.getString(2));
					role.setUser_role_id(rs.getInt(1));
					arrayList.add(role);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayList;
		
	}
	
	public boolean AddUser(com.bean.Login login){
		
		try {
			String sql="Insert into login(login_id,password,user_role_id)  values(?,?,?)";
			smt =con.prepareStatement(sql);
			smt.setString(1, login.getEmail_id());
			smt.setString(2, login.getPassword());
			smt.setInt(3, login.getRole().getUser_role_id());
			smt.executeUpdate();
			flag = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	public ArrayList GetUserList(String login_id){
		
		ArrayList<com.bean.Login> arrayList = new ArrayList<com.bean.Login>();
			
			try {
				String sql="select login.login_id from login "
						+ "where login.login_id<>'admin@admin.com' "
						+ "and login.login_id<> ?";
				smt = con.prepareStatement(sql);
				smt.setString(1, login_id);
				rs = smt.executeQuery();
				while(rs.next()){
						Login login = new Login();
						login.setEmail_id(rs.getString(1));
						arrayList.add(login);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return arrayList;
		
	}
	
	public ArrayList GetMemeberList(int task_id){
		ArrayList<com.bean.Login> arrayList = new ArrayList<Login>();
		
		try {
			String sql="select login_id from task_alloted where task_alloted.task_id=?";
			smt= con.prepareStatement(sql);
			smt.setInt(1, task_id);
			rs = smt.executeQuery();
			while(rs.next()){
					Login  login = new Login();
					login.setEmail_id(rs.getString(1));
					arrayList.add(login);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList AllotedTaskList(String login_id){
		System.out.println(login_id);
		ArrayList<com.bean.TaskTiming> arrayList = new ArrayList<com.bean.TaskTiming>();
		try {
			String sql="select task_management.task_name,"
					+ "task_management.Task_description,"
					+ "task_management.task_statring_date,"
					+ "task_management.task_ending_date,"
					+ "task_management.task_id "
					+ "from task_alloted "
					+ "JOIN task_management ON task_management.task_id = task_alloted.task_id "
					+ "where task_alloted.login_id=?";
			smt= con.prepareStatement(sql);
			smt.setString(1, login_id);
			rs = smt.executeQuery();
			while(rs.next()){
				com.bean.TaskManagement management = new TaskManagement();
				TaskTiming taskTiming = new TaskTiming();
				management.setTask_id(rs.getInt("task_management.task_id"));
				management.setTaskName(rs.getString("task_management.task_name"));
				management.setDescription(rs.getString("task_management.Task_description"));
				management.setStarting_date(rs.getString("task_management.task_statring_date"));
				management.setEnding_date(rs.getString("task_management.task_ending_date"));
				taskTiming.setManagement(management);
				arrayList.add(taskTiming);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList GetAllTaskManagement(){
	ArrayList<com.bean.TaskManagement> arrayList = new ArrayList<com.bean.TaskManagement>();
		try {
			String sql="select task_management.task_id,"
					+ "task_management.task_name,"
					+ "task_management.Task_description,"
					+ "task_management.task_statring_date,"
					+ "task_management.task_ending_date "
					+ "from task_management ";
			smt=  con.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
						TaskManagement management = new TaskManagement();
						management.setTask_id(rs.getInt(1));
						management.setTaskName(rs.getString(2));
						management.setDescription(rs.getString(3));
						management.setStarting_date(rs.getString(4));
						management.setEnding_date(rs.getString(5));
						arrayList.add(management);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
		
	} 
	
	public ArrayList TotalSpendTime(int task_id){
		ArrayList<com.bean.TaskTiming> arrayList = new ArrayList<com.bean.TaskTiming>();
		try {
			String sql="select spend_time "
					+ "from task_timing1 "
					+ "where task_timing1.task_id=?";
			smt = con.prepareStatement(sql);
			smt.setInt(1, task_id);
			rs = smt.executeQuery();
			while(rs.next()){
					TaskTiming taskTiming = new TaskTiming();
					taskTiming.setSpend_Time(rs.getString(1));
					arrayList.add(taskTiming);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayList;
		
	}
	
	
	
}

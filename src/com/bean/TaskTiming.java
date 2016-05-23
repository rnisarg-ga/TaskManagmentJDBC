package com.bean;

import java.io.Serializable;

public class TaskTiming implements Serializable {
	
	private int tasktiming_id;
	private TaskManagement management;
	private String Starting_date;
	private String spend_Time;
	private Login login;
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public int getTasktiming_id() {
		return tasktiming_id;
	}
	public void setTasktiming_id(int tasktiming_id) {
		this.tasktiming_id = tasktiming_id;
	}
	public TaskManagement getManagement() {
		return management;
	}
	public void setManagement(TaskManagement management) {
		this.management = management;
	}
	public String getStarting_date() {
		return Starting_date;
	}
	public void setStarting_date(String starting_date) {
		Starting_date = starting_date;
	}
	public String getSpend_Time() {
		return spend_Time;
	}
	public void setSpend_Time(String spend_Time) {
		this.spend_Time = spend_Time;
	}
	
	
	

}

package com.bean;

import java.io.Serializable;
import java.util.Date;

public class TaskManagement implements Serializable {
	
	private int task_id;
	private String TaskName;
	private String description;
	private String Starting_date;
	private String ending_date;
	private Login login;
	
	public String getStarting_date() {
		return Starting_date;
	}
	public void setStarting_date(String starting_date) {
		Starting_date = starting_date;
	}
	public String getEnding_date() {
		return ending_date;
	}
	public void setEnding_date(String ending_date) {
		this.ending_date = ending_date;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTaskName() {
		return TaskName;
	}
	public void setTaskName(String taskName) {
		TaskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	

}

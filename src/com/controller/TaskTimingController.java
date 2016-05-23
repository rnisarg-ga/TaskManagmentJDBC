package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Login;
import com.bean.TaskManagement;
import com.bean.TaskTiming;
import com.model.ServiceModel;

/**
 * Servlet implementation class TaskTimingController
 */
@WebServlet("/TaskTimingController")
public class TaskTimingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskTimingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected static void Process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	try(PrintWriter out = response.getWriter()) {
    		
    		com.bean.TaskTiming taskTiming = new TaskTiming();
    		com.bean.TaskManagement management = new TaskManagement();
    		com.model.ServiceModel serviceModel = new ServiceModel();
    		com.bean.Login login = new Login();
    		boolean flag= false;
    		
    		
    	HttpSession session = request.getSession();
    	
    	Integer task_id = Integer.parseInt(request.getParameter("task_id"));
    	String Starting_date = request.getParameter("Startingtime");
    	String spend_Time = request.getParameter("spendtime");
    	
    	String d=null,h=null,m=null;
    	
    	String delimiter = "[a-zA-Z]"+"\\s";
    	String[] temp = spend_Time.split(delimiter);
    	
    	for(int i=0;i<temp.length;i++){
    			d=temp[0];
    			h=temp[1];
    			m=temp[2];
    	}
    
    	String[] t1 = m.split("[a-zA-Z]");
    	for(String t2:t1){
    		m = t2;
    	}
    	Integer days= Integer.parseInt(d);
    	Integer hours = Integer.parseInt(h);
    	Integer minutes = Integer.parseInt(m);
    	
    	if(minutes >=60){
    			hours += minutes /60;
    			minutes = minutes %60;
    		
    	}
    	
    	if(hours > 8){
    			days += hours / 8;
    			hours = hours % 8;
    			
    		
    	}
    	
    	/*if(minutes >540){
    		//hours +=minutes /60;
    			hours++;
    		minutes = minutes %60;
    		out.println("Minutes If");
    		
    	}
    	if(hours >8){
    			//days += hours /60;
    			days++;
    			hours  = hours % 60;
    			out.println("Hours IF");
    		
    	}*/
    	 
    	String total_spend_time = days.toString()+"D "+hours.toString()+"H "+minutes.toString()+"M";
    	
    	login.setEmail_id((String)session.getAttribute("user"));
    	management.setTask_id(task_id);
    
    	
    	
    	taskTiming.setManagement(management);
    	taskTiming.setLogin(login);
    	taskTiming.setSpend_Time(total_spend_time);
    	taskTiming.setStarting_date(Starting_date);
    
    	
    
    	
    	flag = serviceModel.AddTaskTiming(taskTiming);
    	
    	if(flag){
    		
    		response.sendRedirect("TaskDetails.jsp?task_id="+task_id);
    		
    	}
    	else{
    		out.println(flag);
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    		
    		
			
		} 
    	
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Process(request, response);
	}

}

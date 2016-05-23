package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bean.Login;
import com.bean.TaskManagement;
import com.model.ServiceModel;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/TaskController")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
   
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html; charset=UTF-8");
    	try(PrintWriter out = response.getWriter()){
    		com.bean.Login login = new Login();
    		com.bean.TaskManagement management = new TaskManagement();
    		boolean flag =false;
    		com.model.ServiceModel serviceModel = new ServiceModel();
    		
    		
    		int task_id = 0;
    		String TaskName = request.getParameter("TaskName");
    		String description = request.getParameter("description");
    		String Starting_date = request.getParameter("Starting_date");
    		String ending_date =request.getParameter("spend_Time");
    		String Submit =request.getParameter("Submit");
    		String[] user_role= request.getParameterValues("user_role");
    		
    		
    			out.println(ending_date+"<br/>"+Submit);
    		
    		switch (Submit) {
			case "Submit":
				HttpSession session =request.getSession();
	    		login.setEmail_id((String)session.getAttribute("user"));
	    		
	    		management.setTaskName(TaskName);
	    		management.setDescription(description);
	    		management.setStarting_date(Starting_date);
	    		management.setEnding_date(ending_date);
	    		out.println(ending_date);
	    		management.setLogin(login);
	    		
	    		flag =serviceModel.AddTask(management,user_role);
	    		if(flag){
	    				response.sendRedirect("dashboard.jsp"); 
	    			
	    		}
	    		else{
	    			
	    			out.println(flag);
	    		}
	    		
				break;
				
			case "Update":
				
				task_id = Integer.parseInt(request.getParameter("task_id"));
				management.setTask_id(task_id);
				management.setTaskName(TaskName);
				management.setDescription(description);
				management.setStarting_date(Starting_date);
				management.setEnding_date(ending_date);
				flag =serviceModel.UpdateUserTask(management);
				
				if(flag){
					response.sendRedirect("dashboard.jsp"); 
				}
				else{
					
					out.println(flag);
				}
				
				
				break;
				
				case "Delete":
					task_id = Integer.parseInt(request.getParameter("task_id"));
					serviceModel.DeleteUserTask(task_id);
				break;
    		}
    		
    		
    	}
    	catch (Exception e) {
			System.out.println(e);
		}
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

}

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Login;
import com.bean.UserRole;
import com.model.ServiceModel;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private com.bean.UserRole role = new UserRole();
	private com.bean.Login login = new Login();
	private com.model.ServiceModel model = new ServiceModel(); 
	private boolean flag= false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    		response.setContentType("text/html; charset=UTF-8");
    		try(PrintWriter out=response.getWriter()){
    			String email_id= request.getParameter("email_id");
    			String password = request.getParameter("password");
    			int user_role= Integer.parseInt(request.getParameter("user_role"));
    			String submit = request.getParameter("submit");
    			
    			
    			switch (submit) {
				case "Submit":
						login.setEmail_id(email_id);
						login.setPassword(password);
						role.setUser_role_id(user_role);
						login.setRole(role);
						flag = model.AddUser(login);
						if(flag){
							response.sendRedirect("dashboard.jsp");
							
						}
						else{
							
							out.println(flag);
						}
						
					
					
					break;
					


				}
    			
    			
    			
    			
    			
    			
    		}
    	
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProcessRequest(request, response);
	}

}

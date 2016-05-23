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
import com.model.ServiceModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private com.bean.Login login = new Login(); 
	    private com.model.ServiceModel serviceModel = new ServiceModel();
	    private boolean flag;
	    private boolean flag1;
	    private boolean flag2;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out =response.getWriter()) {
			String email_id = request.getParameter("email_id");
			String password = request.getParameter("password");
			HttpSession session;
			
			login.setEmail_id(email_id);
			login.setPassword(password);
			
			flag = serviceModel.LoginController(login);
			
			
			if(flag){
					if(flag1 = serviceModel.AdminController(login)){
						session = request.getSession();
						session.setAttribute("admin", email_id);
						
						response.sendRedirect("dashboard.jsp");
						
					}
					else if(flag2=serviceModel.UserController(login)){
						session = request.getSession();
						session.setAttribute("user", email_id);
						//out.println("User Flag"+flag2);
						response.sendRedirect("dashboard.jsp");
						
						
					}
					else {
						response.sendRedirect("errorpage.jsp");
					}
				
				
				
				
			}
			else{
				response.sendRedirect("errorpage.jsp");
				
			}
			
		} 
	}

}

package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ClientService;

/**
 * Servlet implementation class ControlAccess
 */
public class ControlAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlAccess() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("user") != null && ClientService.logOK(request.getParameter("user"), request.getParameter("password"))){
			session = request.getSession();
			session.setAttribute("user", request.getParameter("user"));
		}
		
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher dispatcher_login_page = this.getServletContext().getRequestDispatcher("/login.jsp");
		session = request.getSession();
		//response.set
		boolean isLogged = (session != null && session.getAttribute("user") != null) ? true:false;
		if(isLogged){
			
		}else{			
			dispatcher_login_page.forward(request,response);
		}
		
		
	}

}

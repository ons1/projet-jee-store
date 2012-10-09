package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        // TODO Auto-generated constructor stub
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
		if(request.getParameter("user") != null){
			session = request.getSession();
			session.setAttribute("user", request.getParameter("user"));
		}
		
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		
		RequestDispatcher dispatcher_login_page = this.getServletContext().getRequestDispatcher("/login.jsp");
		session = request.getSession();
		boolean isLogged = (session != null && session.getAttribute("user") != null) ? true:false;
		if(isLogged){
			PrintWriter out = response.getWriter();
			out.println("<h1>Hello world ! </h1>");
			out.println("<h1>getServletName() : " +
					getServletName() + "</h1>");
			out.println("<h1>URL : " +
					request.getRequestURI() + "</h1>");
			out.println("<h1>USER : "+session.getAttribute("user")+"</h1>");
		}else{			
			dispatcher_login_page.forward(request,response);
		}
		
		
	}

}

package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Panier;

import controller.service.ClientService;

/**
 * Servlet implementation class ControlAccess
 */
public class ControlAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	
	
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
		//Quoi qu'il arrive, on redirige vers la page l'index
		 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientService clientService = new ClientService();
		if(clientService.checkUserPassword(request.getParameter("user"), request.getParameter("password"))){
			session = request.getSession();
			session.setAttribute("user", request.getParameter("user"));
			session.setAttribute("panier", new Panier());
			session.setAttribute("erreur", "");
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}else{
			if(session != null)
				session.invalidate();
			
			request.setAttribute("erreur", "Utlisateur et/ou mot de passe incorrect !");
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}

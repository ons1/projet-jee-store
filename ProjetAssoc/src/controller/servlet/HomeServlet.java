package controller.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Controle de session
		if(request.getSession(false) == null || request.getSession(false).getAttribute("user") == null)
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
		else
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
		EntityManager em = emf.createEntityManager();
		ClientService clientService = new ClientService(em);
		if(clientService.checkUserPassword(request.getParameter("user"), request.getParameter("password"))){
			session = request.getSession();
			session.setAttribute("user", request.getParameter("user"));
			session.setAttribute("panier", new Panier());
			
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}else{
			request.setAttribute("erreur", "Utlisateur et/ou mot de passe incorrect !");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		//Fermeture du manager
		em.close();
	}

}

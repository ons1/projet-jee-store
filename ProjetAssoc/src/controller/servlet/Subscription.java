package controller.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Panier;
import controller.service.ClientService;

/**
 * Servlet implementation class Subscription
 */
public class Subscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscription() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
		EntityManager em = emf.createEntityManager();
		ClientService clientService = new ClientService(em);
		String id = request.getParameter("id");
		String mdp = request.getParameter("password1");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		System.out.println(id+" "+mdp+" "+nom+" "+prenom+" "+adresse+" "+cp+" "+ville);
		clientService.create(id, mdp, nom, prenom, adresse, cp, ville, pays);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		em.close();

		
	}

}

package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Commande;
import model.EstCompose;
import model.Panier;

import controller.service.ArticleService;
import controller.service.CommandeService;
import controller.service.EstComposeService;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
		EntityManager em = emf.createEntityManager();
		CommandeService commandeService = new CommandeService(em);
		EstComposeService estComposeService = new EstComposeService(em);
		Collection<Commande> listCommande = commandeService.getUserCommandes(request.getParameter("user"));
		
		System.out.println(listCommande);
		
		request.setAttribute("list_commande", listCommande);
		ArrayList<EstCompose> lignes = new ArrayList<EstCompose>();
		for (Commande commande:listCommande){
			for(EstCompose ec: commandeService.getLignesCommandes(commande.getIdentifiant())){
				lignes.add(ec);
			}
		request.setAttribute("list_lignesCommande", lignes);
			
		}
		request.setAttribute("list_commande", commandeService.getUserCommandes(request.getParameter("user")));
		
		
		this.getServletContext().getRequestDispatcher("/commande.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
		EntityManager em = emf.createEntityManager();
		CommandeService commandeService = new CommandeService(em);
		EstComposeService estComposeService = new EstComposeService(em);
		HttpSession session = request.getSession();
		String user = session.getAttribute("user").toString();
		request.setAttribute("list_commande", commandeService.getUserCommandes(user));
		//String action = request.getParameter("action");
		Panier panier = (Panier) session.getAttribute("panier");

			//if(action.equals("commander"))
			//{
				if (panier.getNumberArticle()!=0){
					int id = commandeService.create(user);
					estComposeService.create(id, panier);
					this.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
				}
			//}
			this.getServletContext().getRequestDispatcher("/commande.jsp").forward(request,response);
		
	}

}

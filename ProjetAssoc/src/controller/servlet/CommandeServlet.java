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
import model.LigneCommande;
import model.LignePanier;
import model.Panier;

import controller.service.ArticleService;
import controller.service.CommandeService;

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
		//Controle la session
		if(request.getSession(false) == null || request.getSession(false).getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/home").forward(request,response);
		}else{
			//Recupere la liste des commandes en base et la renvoi a la jsp commande
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
			EntityManager em = emf.createEntityManager();
			CommandeService commandeService = new CommandeService(em);
			Collection<Commande> listCommande = commandeService.getUserCommandes((String)request.getSession().getAttribute("user"));
			
			ArrayList<LigneCommande> lignes = new ArrayList<LigneCommande>();
			for (Commande commande:listCommande){
				for(LigneCommande ec: commande.getLigneCommandes()){
					lignes.add(ec);
				}

			}
			request.setAttribute("list_lignesCommande", lignes);
			request.setAttribute("list_commande", listCommande);


			System.out.println("redirection commande jsp");
			this.getServletContext().getRequestDispatcher("/commande.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Panier panier = (Panier) session.getAttribute("panier");
		String action = request.getParameter("action");
		
		if(action.equals("commander")){
			if (panier.getNumberArticle()>0){
				System.out.println("panier non nul");
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
				EntityManager em = emf.createEntityManager();
				CommandeService commandeService = new CommandeService(em);
				String user = (String)session.getAttribute("user");
				commandeService.createCommande(user,panier);
				panier.getLignesPanier().clear();
				this.doGet(request, response);
			}else{
				System.out.println("panier nul");
				request.setAttribute("erreur", "Votre panier est vide !");
				this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request,response);
			}
		}else if(action.equals("annuler")){
			if(panier != null){
				//Remet les quantite en stock + vide panier
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
				EntityManager em = emf.createEntityManager();
				ArticleService articleService = new ArticleService(em);
				for(LignePanier l:panier.getLignesPanier()){
					articleService.addQteArticle(l.getArticle(), l.getQuantite());
				}
				panier.getLignesPanier().clear();
			}
			this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request,response);
		}
		

	}

}

package controller.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.ClientService;

/**
 * Servlet implementation class Subscription
 */
public class SubscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubscriptionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/subscription.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Controle des 2 passwords
		if (!request.getParameter("password1").equals(request.getParameter("password2"))){
			System.out.println("LES MOTS DE PASSE SONT DIFFERENTS");
			String erreur = "LES MOTS DE PASSE SONT DIFFERENTS";
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/subscription.jsp").forward(request,response);
		}
		//controle des champs obligatoires
		else if((request.getParameter("id").equals(""))||(request.getParameter("password1").equals(""))){
			System.out.println("LES CHAMPS OBLIGATOIRES NE SONT PAS REMPLIS");
			String erreur = "LES CHAMPS OBLIGATOIRES NE SONT PAS REMPLIS";
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/subscription.jsp").forward(request,response);
		}
		else{
			//Tout se passe bien, creation en base
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
			EntityManager em = emf.createEntityManager();
			ClientService clientService = new ClientService(em);
			String id = request.getParameter("id");
			if(!clientService.userExist(id)){
				String mdp = request.getParameter("password1");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String adresse = request.getParameter("adresse");
				String cp = request.getParameter("cp");
				String ville = request.getParameter("ville");
				String pays = request.getParameter("pays");
				System.out.println(id+" "+mdp+" "+nom+" "+prenom+" "+adresse+" "+cp+" "+ville);
				System.out.println(clientService.create(id, mdp, nom, prenom, adresse, cp, ville, pays));

				System.out.println("REDIRECTION LOGIN BY SUBSCRIPTION");
				request.setAttribute("subOK", id+" : Votre inscription a bien été prise en compte, veuillez vous logger !");
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
			}else{
				request.setAttribute("erreur", id+" : existe déja, veuillez choisir un autre identifiant");
				this.getServletContext().getRequestDispatcher("/subscription.jsp").forward(request,response);
			}
			//fermeture du manager
			em.close();
		}

	}

}

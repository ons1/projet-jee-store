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

import model.Article;
import model.Panier;
import model.exception.ArticleNotFoundException;

import controller.service.ArticleService;


/**
 * Servlet implementation class PanierServlet
 */
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Controle de session
		if(request.getSession(false) == null || request.getSession(false).getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/home").forward(request,response);
		}else{
			this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
		EntityManager em = emf.createEntityManager();
		ArticleService articleService = new ArticleService(em);
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Panier panier = (Panier) session.getAttribute("panier");
		try {

			Article art = articleService.getArticle(Integer.parseInt((String) request.getParameter("code")));
			
			//AJOUTER UN ARTICLE DANS LE PANIER
			if(action.equals("ajouter"))
			{
				int qte = Integer.parseInt(request.getParameter("quantite"));
				//SI STOCK SUFFISANT
				if(qte <= art.getStock())
				{
					if(panier != null)
						panier.addArticle(art,qte);
					else
					{
						panier = new Panier();
						panier.addArticle(art,qte);
						session.setAttribute("panier", panier);
					}
					articleService.updateQteArticle(art,(art.getStock()-qte));
				}
				//SI STOCK INSUFFISANT
				else
				{
					request.setAttribute("erreur","Stock insuffisant !");
				}
				this.getServletContext().getRequestDispatcher("/articles").forward(request,response);
			}
			//SUPPRESSION DE L'ARTICLE DANS LE PANIER
			else if(action.equals("supprimer"))
			{
				int qte = Integer.parseInt(request.getParameter("quantite"));
				if(panier != null)
				{
					panier.removeArticle(art);
				}
				//On remet en stock
				articleService.updateQteArticle(art,(art.getStock()+qte));
				this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request,response);
			}
			//MAJ QUANTITE DANS LE PANIER
			else if(action.equals("majqte"))
			{
				int qte = Integer.parseInt(request.getParameter("quantite"));
				int qteOld = Integer.parseInt(request.getParameter("quantiteOld"));
				if((qte > qteOld && (qte-qteOld) <= art.getStock()) || qte <= qteOld){
					panier.majArticle(art,qte);
					//On met a jour le stock
					articleService.updateQteArticle(art,(art.getStock()+qteOld)-qte);
				}else{
					request.setAttribute("erreur","Stock insuffisant !");
				}

				this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request,response);
			}
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("BAD CODE");
			e.printStackTrace();
		} 
		catch (ArticleNotFoundException e) 
		{
			e.printStackTrace();
		} 

		
		//Fermeture du manager
		em.close();
	}



}

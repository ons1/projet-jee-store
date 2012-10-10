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
			
			if(action.equals("ajouter"))
			{
				if(panier != null)
					panier.addArticle(art);
				else
				{
					panier = new Panier();
					panier.addArticle(art);
					session.setAttribute("panier", panier);
				}
			}else if(action.equals("supprimer"))
			{
				if(panier != null)
				{
					panier.removeArticle(art);
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("BAD CODE");
			e.printStackTrace();
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
		} 

		this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request,response);
		
		//Fermeture du manager
		em.close();
	}



}

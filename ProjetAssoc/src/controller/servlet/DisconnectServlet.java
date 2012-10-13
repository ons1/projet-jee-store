package controller.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.ArticleService;

import model.LignePanier;
import model.Panier;

/**
 * Servlet implementation class Disconnect
 */
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisconnectServlet() {
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
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Panier panier = (Panier) request.getSession().getAttribute("panier");
		
		//on remet les articles du panier en stock
		if(panier != null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
			EntityManager em = emf.createEntityManager();
			ArticleService articleService = new ArticleService(em);
			for(LignePanier l:panier.getLignesPanier()){
				articleService.addQteArticle(l.getArticle(), l.getQuantite());
			}
		}
		request.getSession().invalidate();
		this.getServletContext().getRequestDispatcher("/home").forward(request, response);
	}

}

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
import controller.service.ClientService;

/**
 * Servlet implementation class ArticleServlet
 */
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleServlet() {
		super();
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
		//Controle de session
		if(request.getSession(false) == null || request.getSession(false).getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/home").forward(request,response);
		}else{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
			EntityManager em = emf.createEntityManager();
			ArticleService articleService = new ArticleService(em);
			request.setAttribute("list_article", articleService.getListArticleOnBD());
			this.getServletContext().getRequestDispatcher("/articles.jsp").forward(request,response);
			
			//Fermeture du manager
			em.close();
		}
	}

}

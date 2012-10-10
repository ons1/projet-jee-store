package test;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import controller.service.ArticleService;
import controller.service.ClientService;

import model.Article;


public class TestBD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");

		// R�cup�ration d�une instance de "EntityManager"
		EntityManager em = emf.createEntityManager();
		
		System.out.println("RECHERCHE");
		//Author author = em.find(Author.class, 5);
		Article article = em.find(Article.class, 4);
		System.out.println(article);
		ClientService clientService = new ClientService(em);
		System.out.println(clientService.checkUserPassword("admin", "admini"));
		System.out.println(clientService.checkUserPassword("admin", "admin"));
		
		
		ArticleService articleService = new ArticleService(em);
		Collection<Article> list_article = articleService.getListArticleOnBD();
		
		for(Article art:list_article){
			System.out.println(art);
		}
		/*AuthorService auth_serv = new AuthorService(em);
		Author author = auth_serv.find(5);
		auth_serv.delete(5);
		
		
		System.out.println(author == null ?"NULL":author.getId()+" "+author.getFirstName()+" "+author.getLastName());*/
		// Fermeture de l� "EntityManager"
		em.close();
		emf.close();
		
	}

}
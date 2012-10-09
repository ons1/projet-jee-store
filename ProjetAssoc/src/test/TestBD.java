package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Article;


public class TestBD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");

		// Récupération d’une instance de "EntityManager"
		EntityManager em = emf.createEntityManager();
		
		System.out.println("RECHERCHE");
		//Author author = em.find(Author.class, 5);
		Article article = em.find(Article.class, 5);
		System.out.println(article);
		/*AuthorService auth_serv = new AuthorService(em);
		Author author = auth_serv.find(5);
		auth_serv.delete(5);
		
		System.out.println(author == null ?"NULL":author.getId()+" "+author.getFirstName()+" "+author.getLastName());*/
		// Fermeture de l’ "EntityManager"
		em.close();
		emf.close();
	}

}

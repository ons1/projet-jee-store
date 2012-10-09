package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Article;
import model.Client;


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
		Article article = em.find(Article.class, 4);
		System.out.println(article);
		
		Client my_cli = em.find(Client.class, "admini");
		System.out.println((my_cli != null && my_cli.getMdp().equals("admin") ? true:false));
		/*AuthorService auth_serv = new AuthorService(em);
		Author author = auth_serv.find(5);
		auth_serv.delete(5);
		
		System.out.println(author == null ?"NULL":author.getId()+" "+author.getFirstName()+" "+author.getLastName());*/
		// Fermeture de l’ "EntityManager"
		em.close();
		emf.close();
		
	}

}

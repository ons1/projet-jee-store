package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Commande;

public class TestAssoc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");

		// Récupération d’une instance de "EntityManager"
		EntityManager em = emf.createEntityManager();
		
		System.out.println("RECHERCHE article avec id 4 :");
		Commande cmd = em.find(Commande.class, 1);
		System.out.println(cmd.getIdentifiant());
		System.out.println(cmd.getLigneCommandes());

	}

}

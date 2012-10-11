package controller.service;


import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Article;
import model.Client;
import model.Commande;
import model.Panier;

public class CommandeService {
	
	
private EntityManager em;
	
	public CommandeService(EntityManager pem) {
		em = pem;
	}
	
	public int getId(){
		return 666;
	}

	
	public int create(){
		//EntityManager em = emf.createEntityManager();
		int id = this.getId();
		try{
		      EntityTransaction entr=em.getTransaction();
		      entr.begin();
		      Commande newCommande = new Commande();
		      newCommande.setIdentifiant(id);
		      em.persist(newCommande);
		      entr.commit();
		      return id;
		    }
		catch (Exception e) {
			return 0;
		}
		    
	}

}

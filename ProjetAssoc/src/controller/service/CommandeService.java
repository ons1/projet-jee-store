package controller.service;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;

import model.Article;
import model.Client;
import model.Commande;
import model.EstCompose;
import model.Panier;

public class CommandeService {


	private EntityManager em;

	public CommandeService(EntityManager pem) {
		em = pem;
	}

	public int getId(){
		return 666;
	}


	public int create(String user){
		//EntityManager em = emf.createEntityManager();
		int id = this.getId();
		try{
			EntityTransaction entr=em.getTransaction();
			entr.begin();
			Commande newCommande = new Commande();
			newCommande.setIdentifiant(id);
			newCommande.setClient(user);
			em.persist(newCommande);
			entr.commit();
			return id;
		}
		catch (Exception e) {
			return 0;
		}

	}

	@SuppressWarnings("unchecked")
	public  Collection<Commande> getUserCommandes(String user){
		return em.createQuery("SELECT a FROM "+ Commande.class.getName()+" a WHERE a.client ='admin'").getResultList();	 	
	}

	@SuppressWarnings("unchecked")
	public Collection<EstCompose> getLignesCommandes(Integer id){
		return em.createQuery("SELECT a FROM "+ EstCompose.class.getName()+" a WHERE a.identifiant ="+id).getResultList();	 	
	}



}

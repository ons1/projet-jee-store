package controller.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import model.Article;
import model.Client;
import model.Commande;
import model.LigneCommande;
import model.LignePanier;
import model.Panier;

public class CommandeService {


	private EntityManager em;

	public CommandeService(EntityManager pem) {
		em = pem;
	}

	public int getId(){
		return 666;
	}


	public boolean create(String user,Panier panier){
		//EntityManager em = emf.createEntityManager();
		int id = this.getId();
		try{
			EntityTransaction entr=em.getTransaction();
			entr.begin();
			Commande newCommande = new Commande();
			//newCommande.setIdentifiant(id);
			newCommande.setClient(user);
			newCommande.setDate_commande(new Date());
			//Set<LigneCommande> s = new HashSet<LigneCommande>(); 
			em.persist(newCommande);
			entr.commit();
			System.out.println("idcommande : "+newCommande.getIdentifiant());
			EntityTransaction entr2=em.getTransaction();
			entr2.begin();
			for(LignePanier l:panier.getLignesPanier()){
				/*s.add(*/em.persist(new LigneCommande(newCommande.getIdentifiant(),l.getArticle().getCode(),l.getQuantite()));
			}
			//newCommande.setLigneCommandes(s);
			entr2.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public  Collection<Commande> getUserCommandes(String user){
		return em.createQuery("SELECT a FROM "+ Commande.class.getName()+" a WHERE a.client ='admin'").getResultList();	 	
	}

	



}

package controller.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Client;
import model.Commande;
import model.EstCompose;
import model.LignePanier;
import model.Panier;

public class EstComposeService {


	private EntityManager em;

	public EstComposeService(EntityManager pem) {
		em = pem;
	}

	public boolean create(Integer id, Panier panier){
		//EntityManager em = emf.createEntityManager();
		try{

			for (LignePanier p : panier.getLignesPanier()){
				Integer code = p.getArticle().getCode();
				Integer quantite =p.getQuantite();
				this.createLine(id, code, quantite);
				System.out.println(p);
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}


	}
	public boolean createLine(Integer id, Integer code, Integer quantite){
		try{
			EntityTransaction entr=em.getTransaction();
			entr.begin();
			EstCompose newCommandeEstCompose = new EstCompose();
			newCommandeEstCompose.setIdentifiant(id);
			newCommandeEstCompose.setCode(code);
			newCommandeEstCompose.setQuantite(quantite);
			em.persist(newCommandeEstCompose);
			entr.commit();
			System.out.println(newCommandeEstCompose);
			return true;}
		catch (Exception e) {
			return false;
		}

	}
}

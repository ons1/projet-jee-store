package controller.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Client;

public class ClientService {

	private EntityManagerFactory emf;
	public ClientService() {
		emf = Persistence.createEntityManagerFactory("ProjetAssoc");
	}
	
	public boolean checkUserPassword(String user, String password){
		//Creation d'un manager
		EntityManager em = emf.createEntityManager();
		
		//Controle de l'utilisateur
		Client my_cli = em.find(Client.class, user);
		
		//Fermeture du manager
		em.close();
		
		return (my_cli != null && my_cli.getMdp().equals(password) ? true:false);
		
	}
}

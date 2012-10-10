package controller.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Client;

public class ClientService {

	private EntityManager em;
	public ClientService(EntityManager pem) {
		em = pem;
	}
	
	public boolean checkUserPassword(String user, String password){
		//Controle de l'utilisateur
		Client my_cli = em.find(Client.class, user);
		
		return (my_cli != null && my_cli.getMdp().equals(password) ? true:false);
		
	}
}

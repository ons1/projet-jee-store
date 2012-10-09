package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Client;

public class ClientService {

	public static boolean logOK(String user, String password){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetAssoc");
		EntityManager em = emf.createEntityManager();
		
		boolean finded = false;
		Client my_cli = em.find(Client.class, user);
		finded = (my_cli != null && my_cli.getMdp().equals(password) ? true:false);

		em.close();
		return finded;
	}
}

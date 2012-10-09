package controller;

import javax.persistence.EntityManager;

import model.Client;

public class ClientService {
	private static EntityManager my_entity_manager;
	
	public ClientService(EntityManager my_em) {
		my_entity_manager = my_em;
	}

	public static boolean logOK(String user, String password){
		boolean finded = false;
		Client my_cli = my_entity_manager.find(Client.class, user);
		finded = (my_cli != null && my_cli.getMdp().equals(password) ? true:false);
		return finded;
	}
}

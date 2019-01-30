package models.codePromo;

import models.user.Client;

public class CodeUtilisateur extends CodePromo {
	
	private Client client ;

	// Constructeur --------------------------------------------------------------

	public CodeUtilisateur(Client client ,int id,String code,double pourcentage) {
		super(id, code, pourcentage);
		this.client = client;
	}
	
	// Setters & Getters --------------------------------------------------------------
	
	public Client getClient() {
		return this.client;
	}

}

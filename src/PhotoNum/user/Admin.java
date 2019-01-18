package PhotoNum.user;

import PhotoNum.inventaire.ListeInventaire;

public class Admin extends Utilisateur {

	private ListeInventaire inventaire ; // a completer
	
	// Constructeur --------------------------------------------------------------
	
	public Admin(String email, String nom, String prenom, String mdp) {
		super(email, nom, prenom, mdp);
	}

	// Setters & Getters --------------------------------------------------------------
	
	public ListeInventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(ListeInventaire inventaire) {
		this.inventaire = inventaire;
	}
	
	

}

package models.user;

public class Utilisateur {
	
	private String email;
	private String nom ;
	private String prenom ;
	private String mdp ;

	// constructeurs --------------------------------------------------------------
	
	public Utilisateur(String email , String nom , String prenom , String mdp) {
		this.email= email ;
		this.nom = nom ;
		this.prenom = prenom ;
		this.mdp = mdp ;
	}
	
	// Setters & Getters --------------------------------------------------------------
	
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Utilisateur [email=" + email + ", nom=" + nom + ", prenom=" + prenom + "";
	}
	
	
	
}

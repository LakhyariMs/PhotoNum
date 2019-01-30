package models.codePromo;

public class CodePromo {

	private int id ;
	private String code ;
	private double pourcentage ;
	private boolean estUtilisee ;
	
	// Constructeur --------------------------------------------------------------

	public CodePromo(int id , String code , double pourcentage ) {
		this.id = id ;
		this.code = code ;
		this.pourcentage = pourcentage ;
		this.estUtilisee = false ; // par defaut le code n est pas utlise au debut 
	}

	// Setters & Getters --------------------------------------------------------------

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public double getPourcentage() {
		return pourcentage;
	}

	public boolean isEstUtilisee() {
		return estUtilisee;
	}

	public void setEstUtilisee(boolean estUtilisee) {
		this.estUtilisee = estUtilisee;
	}

	@Override
	public String toString() {
		return "CodePromo [id=" + id + ", code=" + code + ", pourcentage=" + pourcentage + ", estUtilisee="
				+ estUtilisee + "]";
	}
	
	
	

	
	
}

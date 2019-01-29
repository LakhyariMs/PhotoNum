package PhotoNum.adresse;

public class Adresse {
	


	private int id ;
	private String libelle ;
	
	//Constructeur ------------------------------------------------------------->
	
	public Adresse(int id , String libelle ) {
		this.id = id ;
		this.libelle = libelle ;
	}

	//Setters & Getters -------------------------------------------------------->

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Adresse [libelle=" + libelle + "]";
	}
	
	
	
}

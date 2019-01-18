package PhotoNum.adresse;

public class AdressePR extends Adresse {

	private String nomPR ;   // nom magasin du point de relais
	
	//Constructeur ------------------------------------------------------------->
	
	public AdressePR(int id, String libelle , String nomPR) {
		super(id, libelle);
		this.nomPR = nomPR ;
	}

	//Setters & Getters -------------------------------------------------------->

	public String getNomPR() {
		return nomPR;
	}

	public void setNomPR(String nomPR) {
		this.nomPR = nomPR;
	}

}

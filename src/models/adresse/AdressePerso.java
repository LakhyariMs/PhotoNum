package models.adresse;

public class AdressePerso extends Adresse {

	public AdressePerso(int id, String libelle) {
		super(id, libelle);
	}

	@Override
	public String toString() {
		return "AdressePerso [Libelle = " + getLibelle() + "]";
	}
	
	

}

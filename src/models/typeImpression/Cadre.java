package models.typeImpression;

public class Cadre extends TypeImpression {

	public enum TailleCadre {
		
	}
	
	private TailleCadre taille;
	
	public Cadre(int id, TailleCadre taille) {
		super(id);
		this.taille = taille;
	}
	
	public TailleCadre getTaille() {
		return taille;
	}

}

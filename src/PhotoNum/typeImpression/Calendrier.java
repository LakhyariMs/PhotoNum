package PhotoNum.typeImpression;

import java.util.HashSet;

import PhotoNum.fichiers.Photo;

class Calendrier extends TypeImpression {

	public enum TypeCalendrier {
		Mural, Bureau
	}
	
	private TypeCalendrier type;
	private int nbPages;
	
	// caracteristiques
	private HashSet<Photo> numeroPage;
	
	public Calendrier(int id, TypeCalendrier type, int nb) {
		super(id);
		this.type = type;
		this.nbPages = nb;
	}

	public TypeCalendrier getType() {
		return type;
	}

	public int getNbPages() {
		return nbPages;
	}
	
}

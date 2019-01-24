package PhotoNum.typeImpression;

import java.util.HashSet;

import PhotoNum.fichiers.Photo;

public class Album extends TypeImpression {
	
	public enum FormatQualite {
		
	}
	
	public enum TypeCouverture {
		
	}
	
	private String titre;
	private FormatQualite format;
	private TypeCouverture type;
	private int nbPages;
	
	// caracteristiques
	private HashSet<Photo> numeroPage;
	private boolean photoCouverture;
	
	public Album(int id, String titre, FormatQualite format, TypeCouverture type, int nbP) {
		super(id);
		this.titre = titre;
		this.format = format;
		this.type = type;
		this.nbPages = nbP;
	}

	public String getTitre() {
		return titre;
	}

	public FormatQualite getFormat() {
		return format;
	}

	public TypeCouverture getType() {
		return type;
	}

	public int getNbPages() {
		return nbPages;
	}

	public boolean isPhotoCouverture() {
		return photoCouverture;
	}
	
	

}

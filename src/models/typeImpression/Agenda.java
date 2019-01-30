package models.typeImpression;

import java.util.HashSet;

import models.fichiers.Photo;

public class Agenda extends TypeImpression {

	public enum TypeAgenda {
		t52, t365
	}
	
	public enum Modele {
		
	}
	
	private TypeAgenda type;
	private Modele modele;
	
	// caracteritiques
	private HashSet<Photo> numeroPage;
	
	public Agenda(int id, TypeAgenda type, Modele modele) {
		super(id);
		this.type = type;
		this.modele = modele;
	}

	public TypeAgenda getType() {
		return type;
	}

	public Modele getModele() {
		return modele;
	}
	
	

}

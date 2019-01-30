package models.fichiers;

import java.util.ArrayList;

public class FichierImage {

	private int id ;
	private String chemin ;
	private String information ;
	private String resolution ;
	private String emailClient ;
	private ArrayList<Photo> photos = new ArrayList<Photo>();
	private EtatFichier estPartage ;
	
	
	// Constructeur --------------------------------------------------------------

	
	public FichierImage(int id , String path , String info , String resolution , int etatFichier ,String email) {
		this.id = id ;
		this.chemin = path ;
		this.information = info ;
		this.resolution = resolution ;
		this.emailClient = email ;
		// le fichier a 3 etats EnAttente/Privé:Public
		switch (etatFichier) {
			case 0:
				this.estPartage = EtatFichier.Privee;
			break;
			case 1:
				this.estPartage = EtatFichier.Public;
			break;
			case 2:
				this.estPartage = EtatFichier.EnAttente;
			break;
		}
	}

	// Setters & Getters --------------------------------------------------------------

	public int getId() {
		return id;
	}
	
	public String getEmailClient() {
		return emailClient;
	}

	public String getChemin() {
		return chemin;
	}


	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getInformation() {
		return information;
	}


	public void setInformation(String information) {
		this.information = information;
	}


	public String getResolution() {
		return resolution;
	}


	public void setResolution(String resolution) {
		this.resolution = resolution;
	}


	public EtatFichier EstPartage() {
		return estPartage;
	}


	public void setEstPartage(EtatFichier estPartage) {
		this.estPartage = estPartage;
	}
	
	// Photos  --------------------------------------------------------------

	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
	
	public boolean removePhoto(Photo photo ) {
		if(this.photos.remove(photo))
			return true ;
		return false;
	}
	
	public ArrayList<Photo> getAllPhoto(){
		return this.photos;
	}
	
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "FichierImage [id=" + id + ", chemin=" + chemin + ", information=" + information + ", resolution="
				+ resolution + ", emailClient=" + emailClient + ", photos=" + photos + ", estPartage=" + estPartage
				+ "]";
	}
	
	

}

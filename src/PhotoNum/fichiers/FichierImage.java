package PhotoNum.fichiers;

import java.util.ArrayList;

public class FichierImage {

	private int id ;
	private String chemin ;
	private String information ;
	private String resolution ;
	private String emailClient ;
	private ArrayList<Photo> photos = new ArrayList<Photo>();
	private boolean estPartage ;
	
	
	// Constructeur --------------------------------------------------------------

	
	public FichierImage(int id , String path , String info , String resolution , boolean estPartage ,String email) {
		this.id = id ;
		this.chemin = path ;
		this.information = info ;
		this.resolution = resolution ;
		this.estPartage = estPartage;
		this.emailClient = email ;
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


	public boolean EstPartage() {
		return estPartage;
	}


	public void setEstPartage(boolean estPartage) {
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
	

}

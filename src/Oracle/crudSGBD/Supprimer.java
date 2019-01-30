package oracle.crudSGBD;

public class Supprimer {

	private Connexion connexion;
	

	
	// Constructor -------------------------------------------------
	
	public Supprimer() {
		this.connexion = new Connexion();
	}
	
	public Supprimer(Connexion connx){
		this.connexion = connx ;
	}
	
	// Méthodes -------------------------------------------------------
	
	
	
	public boolean deleteFichierImage(String email ,String path){
		
		boolean estSupprimer = false ;
		String query = "DELETE FROM fichierImage Where email = '"+email+"' AND chemin = '"+path+"'";
		return this.connexion.executeQuery(query) ;
		
	}
}

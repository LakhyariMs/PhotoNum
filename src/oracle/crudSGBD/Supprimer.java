package oracle.crudSGBD;

public class Supprimer {

	private Connexion connexion;

	// Constructor -------------------------------------------------

	public Supprimer() {
		this.connexion = new Connexion();
	}

	public Supprimer(Connexion connx) {
		this.connexion = connx;
	}

	// Méthodes -------------------------------------------------------

	public boolean deleteFichierImage(String email, String path) {
		String query = "DELETE FROM fichierImage Where email = '" + email + "' AND id = '" + path + "'";
		return this.connexion.executeQuery(query);
	}
	
	public boolean deletePhoto(int id) {
		String query = "DELETE FROM photo Where idPhoto = " + id;
		return this.connexion.executeQuery(query);
	}
	
	public boolean deleteAdresse(int id) {
		String query = "DELETE FROM AdressePerso Where idAdressePerso = " + id;
		return this.connexion.executeQuery(query);
	}
	
	
}

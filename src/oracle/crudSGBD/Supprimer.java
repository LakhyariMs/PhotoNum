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
	
	
	/**
	 * Supprimer un article 
	 * @param id
	 * @return
	 */
	public boolean deleteArticle(int id ) {
		String queryDelete = "DELETE FROM article WHERE idarticle = "+id;
		return this.connexion.executeQuery(queryDelete);
	}
	
	/**
	 * Supprimer un type d'impression
	 * @param id
	 * @return
	 */
	public boolean deleteTypeImpression(int id ) {
		String queryDelete = "DELETE FROM typeimpression WHERE idimpression = "+id;
		return this.connexion.executeQuery(queryDelete);
	}
	
	
	/**
	 * Supprimer un cadre de la BD
	 * @param id
	 * @return
	 */
	public boolean deleteCadre(int id ) {
		String queryDelete = "DELETE FROM cadre WHERE idcadre = "+id;
		return this.connexion.executeQuery(queryDelete);
	}
	
	/**
	 * Supprimer un tirage de la bd 
	 * @param id
	 * @return
	 */
	public boolean deleteTirage(int id ) {
		String queryDelete = "DELETE FROM tirage WHERE idtirage = "+id;
		return this.connexion.executeQuery(queryDelete);
	}
}

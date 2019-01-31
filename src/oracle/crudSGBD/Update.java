package oracle.crudSGBD;

public class Update {

	private Connexion connexion;

	public Update() {
		this.connexion = new Connexion();
	}

	/**
	 * Modifier le prix de vente d'un produit dans l'inventaire
	 * 
	 * @param ref      Reference produit
	 * @param newPrice nouveau prix
	 * @return True si la modif est un Succes sinon False
	 */
	public boolean updateReferencePrice(String ref, double newPrice) {

		String query = "UPDATE inventaire SET prixdevente = " + newPrice + " WHERE reference = '" + ref + "'";

		if (this.connexion.executeQuery(query))
			return true;

		return false;
	}

	/**
	 * Modifier la qte d'un produit dans l'inventaire
	 * 
	 * @param ref    Refernce produit
	 * @param newQte la nouvelle qte
	 * @return True si la modification est un succes sinon False
	 */
	public boolean updateReferenceQte(String ref, int newQte) {

		String query = "UPDATE inventaire SET quantite = " + newQte + " WHERE reference = '" + ref + "'";

		if (this.connexion.executeQuery(query))
			return true;

		return false;
	}

	public boolean updateStatutFichier(String email, int id, int statut) {
		String requete = "UPDATE INTO FichierImage SET estPartage = " + statut + "' WHERE idFichier = " + id
				+ " AND email = '" + email + "'";

		if (this.connexion.executeQuery(requete)) {
			System.out.println("Statut chang� ");
			return true;
		}
		System.out.println("Echec de l'approvisionnement");
		return false;
	}

	/**
	 * Modifier l'etat du client
	 * 
	 * @param email  Email du client
	 * @param statut True pour activer le client , false pour le desactiver
	 * @return True si la modification est un succes sinon False
	 */
	public boolean updateClientState(String email, boolean statut) {
		int estActiver;

		if (statut) // traduire boolean en 0 ou 1 utiliser dans la base
			estActiver = 1;
		else
			estActiver = 0;

		String query = "UPDATE client SET estactive = " + estActiver + " WHERE email = '" + email + "'";
		if (this.connexion.executeQuery(query))
			return true;

		return false;
	}

	public boolean updatePrixCommande(int id, double prix) {

		String query = "UPDATE commande SET prixTotal = " + prix + " WHERE idcommande = '" + id + "'";
		if (this.connexion.executeQuery(query))
			return true;

		return false;
	}
	
	public boolean updatePrixANDAdresseCommande(int id , double prix , int idAdresse ) {
		
		String query = "UPDATE commande SET prixTotal = "+prix+", idadresseperso = "+idAdresse+" WHERE idcommande = '"+id+"'";
		if(this.connexion.executeQuery(query))
			return true;
		
		return false ;
	}
	

}

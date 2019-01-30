package oracle.crudSGBD;

public class Inserer {

	private Connexion connexion;

	public Inserer() {
		this.connexion = new Connexion();
	}

	// partie client 
	public boolean inscriptionClient(String email, String nom, String prenom, String mdp) {
		String requete = "INSERT INTO Client(email, nom, prenom, MDP, estactive) " + "VALUES('" + email + "', '" + nom
				+ "', '" + prenom + "', '" + mdp + "', 1)";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Inscription reussie");
			return true;
		} else {
			System.out.println("Echec de l'inscription");
		}
		return false;
	}

	public void ajouterAdresse(String newAdresse, String email) {
		String requete = "INSERT INTO adressePerso(adresse, email) " + "VALUES('" + newAdresse + "', '" + email + "')";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Adresse ajoutée");
		} else {
			System.out.println("Echec ! l'adresse n'a pas été ajoutée");
		}
	}
	
	public void addFichierImage(String chemin, String info, String resolution, int estPartage, String email) {
		String requete = "INSERT INTO FichierImage(chemin, information, resolution, estPartage, dateUtil, email) " 
				+ "VALUES('" + chemin + "', '" + info + "', '" + resolution + "', " + estPartage + ", SYSDATE, '" + email + "')";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Fichier Image ajouté !");
		} else {
			System.out.println("Echec de l'ajout du fichier");
		}
	}
	
	public void updateStock(String reference, int nouvelleQte) {
		String requete = "UPDATE INTO Inventaire SET quantite = " + nouvelleQte + "WHERE reference = '" + reference + "'";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Stock " + reference + " mis à jour !");
		} else {
			System.out.println("Echec de l'approvisionnement");
		}
	}

	public void commander() {

	}
	
	// partie admin
	// sert a rien car 1 seul admin
	public void addAdmin(String email, String nom, String prenom, String mdp) {
		String requete = "INSERT INTO Administrateur(email, nom, prenom, MDP) " + "VALUES('" + email + "', '" + nom
				+ "', '" + prenom + "', '" + mdp + "')";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Nouveau admin ajouté");
		} else {
			System.out.println("Echec de l'ajout");
		}
	}
	
	public void addArticle(String reference, int quantite, int prix) {
		String requete = "INSERT INTO Inventaire(reference, quantite, prix) " + "VALUES('" + reference + "', " + quantite
				+ ", " + prix + ")";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Article ajouté !");
		} else {
			System.out.println("Echec de l'ajout de l'article");
		}
	}
	
	

}

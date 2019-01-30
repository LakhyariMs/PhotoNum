package oracle.crudSGBD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import main.UtilExecute;

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

	public void addCommande(int id, String date, String modeLiv, double prixTot, String statut, String email,
			int estPaye, int idAdr, int idCode) {

		String query = "INSERT INTO Commande(idcommande,datecommande,modelivraison,prixtotal,statut,email,estpaye,idadresseperso,idcodeutilisateur)"
				+ "VALUES (" + id + ", '" + date + "', '" + modeLiv + "', " + prixTot + ", '" + statut + "' , '" + email
				+ "' , " + estPaye + " , null , null)";
		if (this.connexion.insertQuery(query)) {
			System.out.println("Commande Ajoutee");
		} else {
			System.out.println("Echec d'ajout");
		}

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
				+ "VALUES('" + chemin + "', '" + info + "', '" + resolution + "', " + estPartage + ", SYSDATE, '"
				+ email + "')";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Fichier Image ajouté !");
		} else {
			System.out.println("Echec de l'ajout du fichier");
		}
	}

	public void addCadre(int id, int idImpression, String ref, int idPhoto) {
		String query = "INSERT INTO cadre " + "VALUES (" + id + "," + idImpression + ",'" + ref + "' , " + idPhoto
				+ ")";
		if (this.connexion.insertQuery(query)) {
			System.out.println("Cadre Ajoutee");
		} else {
			System.out.println("Echec Cadre");
		}
	}

	public void addTypeImpression(int idImpression, String ref, String email) {
		String query = "INSERT INTO typeImpression " + "VALUES (" + idImpression + ",'" + ref + "','" + email + "')";

		if (this.connexion.insertQuery(query)) {
			System.out.println("Type Impression Ajoutee");
		} else {
			System.out.println("Echec Type Impression");
		}
	}

	public void modifierStatut(String email, int id, int statut) {
		String requete = "UPDATE INTO FichierImage SET estPartage = " + statut + "' WHERE idFichier = " + id
				+ " AND email = '" + email + "'";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Statut changé ");
		} else {
			System.out.println("Echec de l'approvisionnement");
		}
	}

	public void commander(int typeImpression, String reference, int qte, String email, int idAdr) {
		String type;
		int IdArticle, IdTypeImpression, IdCadre;
		int IdCommande;
		UtilExecute util = new UtilExecute();
		Inserer inserer = new Inserer();
		Update update = new Update();
		switch (typeImpression) {
		case 5:
			type = "cadre";
			break;
		}
		IdCommande = util.generateID();
		System.out.println(IdCommande);
		IdArticle = util.generateID();
		System.out.println(IdArticle);
		IdTypeImpression = util.generateID();
		System.out.println(IdTypeImpression);
		IdCadre = util.generateID();
		System.out.println(IdCadre);
		int idPhoto = 6;

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

		inserer.addCommande(IdCommande, dateFormat.format(new java.util.Date()), "Domicile", 0.0, "En attente", email,
				0, 0, 0);

		int addtypeImp = 0;
		if (addtypeImp == 0) {
			inserer.addTypeImpression(IdTypeImpression, reference, email);
			addtypeImp = 1;
		}

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (addtypeImp == 1)
			inserer.addCadre(IdCadre, IdTypeImpression, reference, idPhoto);

		// on cherche le prix de l'inventaire
		inserer.addArticle(IdArticle, qte, 1000, IdCommande, IdTypeImpression);

		update.updatePrixCommande(IdCommande, 1000);

		// genere ID article et commande typeImpression
		// creer type impresion vide et sauv l id
		// cadre besoin id photo
		// crerrer article => recupere le prix et calculer le prx totale
		// update commande pour finir

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

	public void addInventaire(String reference, int quantite, int prix) {
		String requete = "INSERT INTO Inventaire(reference, quantite, prix) " + "VALUES('" + reference + "', " + quantite
				+ ", " + prix + ")";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Article ajouté !");
		} else {
			System.out.println("Echec de l'ajout de l'article");
		}
	}
	
	public void addArticle(int idArticle , int qte , double prix , int idCommande , int idImpression ) {
		String requete = "INSERT INTO article  VALUES("+idArticle+","+qte+","+prix+","+idCommande+" , "+idImpression+" )";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Article ajoute !");
		} else {
			System.out.println("Echec de l'ajout de l'article");
		}
	}

}

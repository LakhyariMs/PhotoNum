package oracle.crudSGBD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
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

	private double ajouterArticle(int idCommande, String reference, int qte, String email, int idPhoto) {

		UtilExecute util = new UtilExecute();
		Inserer inserer = new Inserer();
		Consulter consulter = new Consulter();
		int idTypeImpression = util.generateID();
		int idArticle = util.generateID();
		int idCatImpression = util.generateID();
		double prixRef;

		// STEP 1 : Ajouter le type d'impression
		inserer.addTypeImpression(idTypeImpression, reference, email);

		// STEP 2 : Ajouter l'impression ( exemple : cadre )
		inserer.addCadre(idCatImpression, idTypeImpression, reference, idPhoto);

		// STEP 3 : Chercher le prix de la ref dans l'inventaire et calculer le prix
		// total
		prixRef = consulter.getReferencePrice(reference);

		System.out.println("Prix d'Article : " + prixRef * qte);

		// STEP 4 : Ajouter a la table Article

		inserer.addArticle(idArticle, qte, prixRef * qte, idCommande, idTypeImpression);

		return prixRef * qte;
	}
	public void commander(String email) {

		String type;
		int IdCommande;
		int choix = 1;
		float somme = 0;
		UtilExecute util = new UtilExecute();
		Inserer inserer = new Inserer();
		Update update = new Update();
		Scanner sc = new Scanner(System.in);
		String continuer = "oui";

		IdCommande = util.generateID();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); // date d'aujrd hui

		// STEP 1 : Ajouter une commande vide
		inserer.addCommande(IdCommande, dateFormat.format(new java.util.Date()), "Domicile", 0.0, "En attente", email,
				0, 0, 0);

		// STEP 2 : A.Article + A.TypeImpression + A.Impression
		while (choix != 0) {
			// afficher le sous-menu d'impression
			choix = util.commandeChoix();
			// afficher sous menu photo => selectionner une photo
			int idPhoto = util.showPhoto(email);

			// Si il prend un choix
			if (choix > 0 && idPhoto > 0) {
				// affihcer les reference du type de produit et selectionner une ref
				String reference = util.showReferences(choix);
				System.out.println("**** ETAPE 4 : Entrez la quantité");
				System.out.print("> ");
				int qte = sc.nextInt();
				double prixArticle = this.ajouterArticle(IdCommande, reference, qte, email, idPhoto);
				System.out.println("Prix Total d'article : " + prixArticle);
				somme += prixArticle;
				
				System.out.println("Voulez-Vous commander une Autre Impression (1/0)");
				choix = sc.nextInt();
			}
		}

		int idAdresse = util.showAdresses(email);

		// STEP 3 : Modifer les infos de la commande => Mise a jour du prix
		update.updatePrixANDAdresseCommande(IdCommande, somme, idAdresse);
		// update.updatePrixCommande(IdCommande, somme);

		System.out.println("Prix Total de la commande : " + somme);

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
		String requete = "INSERT INTO Inventaire(reference, quantite, prix) " + "VALUES('" + reference + "', "
				+ quantite + ", " + prix + ")";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Article ajouté !");
		} else {
			System.out.println("Echec de l'ajout de l'article");
		}
	}

	public void addArticle(int idArticle, int qte, double prix, int idCommande, int idImpression) {
		String requete = "INSERT INTO article  VALUES(" + idArticle + "," + qte + "," + prix + "," + idCommande + " , "
				+ idImpression + " )";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Article ajoute !");
		} else {
			System.out.println("Echec de l'ajout de l'article");
		}
	}

}

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

	public boolean addCommande(int id, String date, String modeLiv, double prixTot, String statut, String email,
			int estPaye, int idAdr, int idCode) {

		String query = "INSERT INTO Commande(idcommande,datecommande,modelivraison,prixtotal,statut,email,estpaye,idadresseperso,idcodeutilisateur)"
				+ "VALUES (" + id + ", '" + date + "', '" + modeLiv + "', " + prixTot + ", '" + statut + "' , '" + email
				+ "' , " + estPaye + " , null , null)";
		if (this.connexion.insertQuery(query)) {
			System.out.println("Commande Ajoutee");
			return true ;
		} else {
			System.out.println("Echec d'ajout");
			return false ;
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
		Supprimer supprimer = new Supprimer();
		int idTypeImpression = util.generateID();
		int idArticle = util.generateID();
		int idCatImpression = util.generateID();
		double prixRef;
		Scanner sc = new Scanner(System.in);

		// STEP 1 : Ajouter le type d'impression
		inserer.addTypeImpression(idTypeImpression, reference, email);

		// STEP 2 : Ajouter l'impression ( exemple : cadre )
		inserer.addCadre(idCatImpression, idTypeImpression, reference, idPhoto);

		// STEP 3 : Chercher le prix de la ref dans l'inventaire et calculer le prix
		// total
		prixRef = consulter.getReferencePrice(reference);

		System.out.println("Prix d'Article : " + prixRef * qte);

		// STEP 4 : Ajouter a la table Article

		boolean estPossible = inserer.addArticle(idArticle, qte, prixRef*qte, idCommande, idTypeImpression);
		
		if(!estPossible) {
			System.out.println("Action Impossible !");
			supprimer.deleteCadre(idCatImpression);
			supprimer.deleteTypeImpression(idTypeImpression);
			return -1;
		}
			
		
		System.out.println("Votre Article : Reference : "+reference+", Quantite : "+qte+", Prix Total :"+prixRef*qte);
		
		System.out.println("Vous confirmer cette article (1/0) ?");
		int confrimerArticle = sc.nextInt();
		
		if(confrimerArticle == 1)
			return prixRef * qte;
		else {
			
			System.out.println("Article Annuler !");
			supprimer.deleteArticle(idArticle);
			supprimer.deleteCadre(idCatImpression);
			supprimer.deleteTypeImpression(idTypeImpression);
			
			return 0;
		}
	}

	public void commander(String email) {

		String type;
		int IdCommande;
		int choix = 1;
		float somme = 0;
		int nbrArticle = 0;
		UtilExecute util = new UtilExecute();
		Inserer inserer = new Inserer();
		Update update = new Update();
		Supprimer supprimer= new Supprimer();
		Scanner sc = new Scanner(System.in);
		String continuer = "oui";

		IdCommande = util.generateID();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); // date d'aujrd hui

		// STEP 1 : Ajouter une commande vide
		boolean estAjouter = inserer.addCommande(IdCommande, dateFormat.format(new java.util.Date()), "Domicile", 0.0, "En attente", email,
				0, 0, 0);
		
		if(!estAjouter)
			return;

		// STEP 2 : A.Article + A.TypeImpression + A.Impression
		while (choix != 0) {
			// afficher le sous-menu d'impression
			choix = util.commandeChoix();
			if(choix == 0)
				return;
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
				
				if(prixArticle == -1) {
					return ;
				}
				
				if(prixArticle > 0) {
					somme += prixArticle;
					nbrArticle ++;
				}
				
				System.out.println("Voulez-Vous Ajouter un Autre Article (1/0) ?");
				choix = sc.nextInt();
			}
		}
		
		if(nbrArticle != 0 ) 
		{
			int idAdresse = util.showAdresses(email);


			// update.updatePrixCommande(IdCommande, somme);
			System.out.println("Prix Total de la commande : " + somme);
			
			float nvPrix = util.showCodePromo(email, somme);
			
			if(nvPrix != -1)
				somme = nvPrix ;
			
			System.out.println("Le nouveau prix de votre commande est de : " + somme);
			System.out.println("Payez cette Commande maintenant (1/0): " + somme);
			int choixPaye = sc.nextInt();
			
			if(choixPaye == 1) {
				System.out.println("Paiement en cours ..........");
				// STEP 3 : Modifer les infos de la commande => Mise a jour du prix
				update.updatePrixANDAdresseCommande(IdCommande, somme, idAdresse,1);
				inserer.addToHistorique(IdCommande, dateFormat.format(new java.util.Date()), email, somme);
				
				if(somme > 100) 
				{
					System.out.println("Suite a Votre commande qui depasse les 100 euros vous benificez d'un Code Promo");
					System.out.println("de reduction de 10% sur votre prochain achat ");
					// insere code promo
					int code = util.generateID();
					inserer.addCodeUtilisateur(code, email);	
				}
			}
			else {
				System.out.println("Paiement non effectue , Commande enregistre");
				update.updatePrixANDAdresseCommande(IdCommande, somme, idAdresse,0);
			}
				
		}
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

	public boolean addArticle(int idArticle, int qte, double prix, int idCommande, int idImpression) {
		String requete = "INSERT INTO article  VALUES(" + idArticle + "," + qte + "," + prix + "," + idCommande + " , "
				+ idImpression + " )";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Article ajoute !");
			return true ;
		} else {
			System.out.println("Echec de l'ajout de l'article");
			return false;
		}
	}
	
	
	public boolean addCodeUtilisateur(int code ,String email ) {
		UtilExecute util = new UtilExecute();
		String numCode = "CACHAT"+util.generateID();
		String requete = "INSERT INTO codeutilisateur  VALUES("+code+",'"+numCode+"', 10 , 0 ,'"+ email +"' )";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Code Ajoutee !");
			return true ;
		} else {
			System.out.println("Echec de l'ajout du code");
			return false;
		}
	}
	

	public boolean addToHistorique(int idCommande ,String date ,String email,float prix) {
		
		UtilExecute util = new UtilExecute();
		String requete = "INSERT INTO historique  VALUES("+idCommande+",'"+date+"', 'Domicile' , "+prix+" , 'En cours' , '"+email+"' )";
		if (this.connexion.insertQuery(requete)) {
			return true ;
		} else {
			return false;
		}
	}

}

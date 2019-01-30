package main;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import models.user.Admin;
import models.user.Client;
import models.user.Utilisateur;
import oracle.crudSGBD.Consulter;
import oracle.crudSGBD.Inserer;
import oracle.crudSGBD.Supprimer;
import oracle.crudSGBD.Update;
import oracle.helpersSGBD.PrinterUtils;
import views.ConsoleUtils;
import views.Menu;
import views.MenuItem;

public class PhotoNum {

	private static Scanner sc = new Scanner(System.in);
	private UtilExecute util;
	private Consulter consulter;
	private Inserer inserer;
	private Update update;
	private Client client;
	private Admin admin;
	private Supprimer supprimer;

	protected PhotoNum(UtilExecute util, Consulter c, Inserer i, Supprimer s, Update u) {
		this.util = util;
		consulter = c;
		inserer = i;
		supprimer = s;
		update = u;
	}

	private void mainMenu() {
		Menu menu = new Menu();
		menu.setTitle("*** Menu Principal ***");
		menu.addItem(new MenuItem("Se connecter", this, "performConnection"));
		menu.addItem(new MenuItem("S'inscrire", this, "performInscription"));
		menu.execute();
	}

	public void performConnection() {
		System.out.println("\nEmail : ");
		String email = "";
		if (sc.hasNextLine())
			email = sc.nextLine();
		System.out.println("\nMot De Passe : ");
		String mdp = "";
		if (sc.hasNextLine())
			mdp = sc.nextLine();

		client = util.isClient(email, mdp);
		if (client == null) {
			admin = util.isAdmin(email, mdp);
			if (admin == null) {
				ConsoleUtils.pauseExecution();
			} else {
				this.sousMenuAdmin();
			}
		} else {
			this.sousMenuClient();
		}

	}

	public void performInscription() throws InvocationTargetException {
		Client cli = new Client();
		System.out.println("\nEmail : ");
		String email = "";
		if (sc.hasNextLine()) {
			email = sc.nextLine();
			System.out.println(email);
			cli.setEmail(email);
		}
		System.out.println("\nMot De Passe : ");
		String mdp = "";
		if (sc.hasNextLine()) {
			mdp = sc.nextLine();
			cli.setMdp(mdp);
		}
		System.out.println("Nom : ");
		String nom = "";
		if (sc.hasNextLine()) {
			nom = sc.nextLine();
			cli.setNom(nom);
		}
		System.out.println("\nPrénom : ");
		String prenom = "";
		if (sc.hasNextLine()) {
			prenom = sc.nextLine();
			cli.setPrenom(prenom);
		}

		boolean confirmer = inserer.inscriptionClient(cli.getEmail(), cli.getNom(), cli.getPrenom(), cli.getMdp());
		if (confirmer) {
			client = cli;
			this.sousMenuClient();
		}
	}

	/////
	// PARTIE CLIENT
	/////
	public void sousMenuClient() {
		Menu menu = new Menu();
		menu.setTitle("*** Bonjour " + client.getPrenom() + " ***");
		menu.addItem(new MenuItem("Faire Une Impression", this, "sousMenuImpression"));
		menu.addItem(new MenuItem("Mes Fichiers Image", this, "sousMenuFichiersImage"));
		menu.addItem(new MenuItem("Mes Photos", this, "sousMenuPhotos"));
		menu.addItem(new MenuItem("Afficher Mes Commandes", this, "performAfficherCommandes"));
		menu.addItem(new MenuItem("Mon Profil", this, "sousMenuProfil"));
		menu.execute();
	}

	// SOUS MENU : MES IMPRESSIONS
	public void sousMenuImpression() {
		Menu menu = new Menu();
		menu.setTitle("*** Les Impressions ***");
		menu.addItem(new MenuItem("Album", this, "performAlbum"));
		menu.addItem(new MenuItem("Agenda", this, "performAgenda"));
		menu.addItem(new MenuItem("Cadre", this, "performCadre"));
		menu.addItem(new MenuItem("Calendrier", this, "performCalendrier"));
		menu.addItem(new MenuItem("Tirage", this, "performTirage"));
		menu.execute();
	}
 
	public void performAlbum() {

	}

	public void performAgenda() {

	}

	public void performCadre() {

	}

	public void performCalendrier() {

	}

	public void performTirage() {

	}

	public void commander() {
		
	}

	// SOUS MENU : FICHIERS IMAGES
	public void sousMenuFichiersImage() {
		Menu menu = new Menu();
		menu.setTitle("*** Fichiers Images ***");
		menu.addItem(new MenuItem("Consulter (Les Fichiers Partagés) ", this, "performFichiersImageConsulter"));
		menu.addItem(new MenuItem("Modifier", this, "performFichiersImageModifier"));
		menu.addItem(new MenuItem("Supprimer", this, "performFichiersImageSupprimer"));
		menu.execute();
	}

	public void performFichiersImageConsulter() {
		PrinterUtils.print(consulter.getClientFichierImage(client.getEmail()));
		ConsoleUtils.pauseExecution();
	}

	public void performFichiersImageModifier() {
		System.out.println("Modification ... \n");
		PrinterUtils.print(consulter.getClientFichierImage(client.getEmail()));
		String input = this.getInfoConsole("Id : ");
		////////////////////////
		///////////////////////
		ConsoleUtils.pauseExecution();
	}

	public void performFichiersImageSupprimer() {
		System.out.println("Suppression ... \n");
		PrinterUtils.print(consulter.getClientFichierImage(client.getEmail()));
		String input = this.getInfoConsole("Path du Fichier Image : ");
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm) {
			supprimer.deleteFichierImage(client.getEmail(), input);
			System.out.println("\nSuppression faite ! ...");
		} else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}

	// SOUS MENU : MES PHOTOS
	public void sousMenuPhotos() {
		Menu menu = new Menu();
		menu.setTitle("*** Mes Photos ***");
		menu.addItem(new MenuItem("Consulter", this, "performFichiersPhotosConsulter"));
		menu.addItem(new MenuItem("Modifier", this, "performFichiersPhotosModifier"));
		menu.addItem(new MenuItem("Supprimer", this, "performFichiersPhotosSupprimer"));
		menu.execute();
	}

	public void performFichiersPhotosConsulter() {
		PrinterUtils.print(consulter.getPhotosClient(client.getEmail()));
		ConsoleUtils.pauseExecution();
	}

	public void performFichiersPhotosModifier() {
		System.out.println("Modification ... Fonction déliberemment non réalisée\n");
		ConsoleUtils.pauseExecution();
	}

	public void performFichiersPhotosSupprimer() {
		System.out.println("Suppression ... \n");
		String input = this.getInfoConsole("Path du Fichier Image : ");
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm) {
			supprimer.deletePhoto(Integer.parseInt(input));
			System.out.println("\nSuppression faite ! ...");
		} else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}

	// SOUS MENU : AFFICHER MES COMMANDES
	public void performAfficherCommandes() {
		System.out.println("Mes Commandes \n");
		PrinterUtils.print(consulter.getAllClientCommande(client.getEmail()));
		ConsoleUtils.pauseExecution();
	}

	// SOUS MENU : MON PROFIL
	public void sousMenuProfil() {
		Menu menu = new Menu();
		menu.setTitle("*** Mon Profil ***");
		menu.addItem(new MenuItem("Afficher Les Détails ", this, "performAfficherDetailsProfil"));
		menu.addItem(new MenuItem("Modifier", this, "performFichiersPhotosModifier"));
		menu.addItem(new MenuItem("Ajouter Une Adresse", this, "performAjouterAdresse"));
		menu.addItem(new MenuItem("Supprimer Une Adresse", this, "performSupprimerAdresse"));
		menu.execute();
	}

	public void performAfficherDetailsProfil() {
		System.out.println(consulter.getClient(client.getEmail()).toString());
		System.out.println("Mes adresses : \n");
		PrinterUtils.print(consulter.getClientAdresses(client.getEmail()));
		ConsoleUtils.pauseExecution();
	}

	public void performAjouterAdresse() {
		System.out.println("Ajout d'une Adresse ... \n");
		String input = this.getInfoConsole("L'adresse : ");
		inserer.ajouterAdresse(input, client.getEmail());
		ConsoleUtils.pauseExecution();
	}

	public void performSupprimerAdresse() {
		System.out.println("Suppression d'une Adresse ... \n");
		PrinterUtils.print(consulter.getClientAdresses(client.getEmail()));
		String input = this.getInfoConsole("L'adresse numéro :");
		supprimer.deleteAdresse(Integer.parseInt(input));
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nSuppression faite ! ...");
		else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}

	/////
	// PARTIE ADMIN
	/////

	public void sousMenuAdmin() {
		Menu menu = new Menu();
		menu.setTitle("*** Bonjour " + admin.getPrenom() + " ***");
		menu.addItem(new MenuItem("Supprimer Un Client", this, "performSupprimerClient"));
		menu.addItem(new MenuItem("Supprimer Un Fichier Image", this, "performSupprimerFichierImage"));
		menu.addItem(new MenuItem("Approvisionner les Stocks", this, "performStock"));
		menu.addItem(new MenuItem("Mettre A Jour Une Commande", this, "performStatutCommande"));
		menu.addItem(new MenuItem("Afficher Une Impression ", this, "performAfficherImpression"));
		menu.addItem(new MenuItem("Historique Des Commandes", this, "performHistoriqueCommandes"));
		menu.execute();
	}

	public void performSupprimerClient() {
		System.out.println("Suppression d'une Adresse ... \n");
		PrinterUtils.print(consulter.getClientAdresses(client.getEmail()));
		String input = this.getInfoConsole("L'adresse numéro :");
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nSuppression faite ! ...");
		else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}

	public void performSupprimerFichierImage() {
		System.out.println("Suppression d'une Adresse ... \n");
		PrinterUtils.print(consulter.getClientAdresses(client.getEmail()));
		String input = this.getInfoConsole("L'adresse numéro :");
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nSuppression faite ! ...");
		else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}

	public void performStock() {
		PrinterUtils.print(consulter.getAllInventaire());
		String input = this.getInfoConsole("La référence :");
		String input1 = this.getInfoConsole("Nouvelle Quantité");
		update.updateReferenceQte(input, Integer.parseInt(input1));
		ConsoleUtils.pauseExecution();
	}

	
	public void performStatutCommande() {
		System.out.println("Suppression d'une Adresse ... \n");
		PrinterUtils.print(consulter.getClientAdresses(client.getEmail()));
		String input = this.getInfoConsole("L'adresse numéro :");
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nSuppression faite ! ...");
		else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}

	public void performAfficherImpression() {
		String input = this.getInfoConsole("L'adresse numéro :");
		ConsoleUtils.pauseExecution();
	}

	public void performHistoriqueCommandes() {
		PrinterUtils.print(consulter.getHistorique());
		ConsoleUtils.pauseExecution();
	}

	private String getInfoConsole(String message) {
		System.out.println(message);
		String str = "";
		if (sc.hasNextLine())
			return str = sc.nextLine();
		return str;
	}

	public static void main(String[] args) {

		new PhotoNum(new UtilExecute(), new Consulter(), new Inserer(), new Supprimer(), new Update()).mainMenu();
	}
}
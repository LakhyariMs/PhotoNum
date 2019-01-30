package main;

import java.util.Scanner;

import models.user.Admin;
import models.user.Client;
import oracle.crudSGBD.Consulter;
import oracle.crudSGBD.Inserer;
import views.ConsoleUtils;
import views.Menu;
import views.MenuItem;

public class PhotoNum {

	private static Scanner sc = new Scanner(System.in);
	private UtilExecute util;
	private Consulter consulter;
	private Inserer inserer;
	private Client client;
	private Admin admin;
	
	protected PhotoNum(UtilExecute util, Consulter c, Inserer i) {
		this.util = util;
		consulter = c;
		inserer = i;
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
			}
			else {
				this.sousMenuAdmin();
			}
		}
		else {
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
		menu.addItem(new MenuItem("Album", this, "xxx"));
		menu.addItem(new MenuItem("Agenda", this, "xxx"));
		menu.addItem(new MenuItem("Cadre", this, "xxx"));
		menu.addItem(new MenuItem("Calendrier", this, "xxx"));
		menu.addItem(new MenuItem("Tirage", this, "xxx"));
		menu.execute();
	}
	
	/*
	 * 
	 * ici generation code + realisation commande
	 */
	
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
		System.out.println("Consultation ... \n");
		///////////////////
		ConsoleUtils.pauseExecution();
	}
	
	public void performFichiersImageModifier() {
		System.out.println("Modification ... \n");
		///////////////////
		ConsoleUtils.pauseExecution();
	}
	
	public void performFichiersImageSupprimer() {
		System.out.println("Suppression ... \n");
		///////////////////
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nSuppression faite ! ...");
		else
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
		System.out.println("Consultation ... \n");
		///////////////////
		ConsoleUtils.pauseExecution();
	}
	
	public void performFichiersPhotosModifier() {
		System.out.println("Modification ... \n");
		ConsoleUtils.pauseExecution();
	}
	
	public void performFichiersPhotosSupprimer() {
		System.out.println("Suppression ... \n");
		///////////////////
		boolean confirm = ConsoleUtils.requestConfirmation();
		if (confirm)
			System.out.println("\nSuppression faite ! ...");
		else
			System.out.println("\nSuppression annulée !");
		ConsoleUtils.pauseExecution();
	}
	
	// SOUS MENU : AFFICHER MES COMMANDES
	public void performAfficherCommandes() {
		System.out.println("\n Mes Commandes \n");
		/////
		int taille = 5; // a modifier
		for (int i = 0; i < taille ; i++) {
			System.out.println("n: " ); // a completer
		}
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
		System.out.println("Ajout d'une Adresse ... \n");
		///////////////////
		ConsoleUtils.pauseExecution();
	}
	
	public void performAjouterAdresse() {
		System.out.println("Ajout d'une Adresse ... \n");
		///////////////////
		ConsoleUtils.pauseExecution();
	}
	
	public void performSupprimerAdresse() {
		System.out.println("Suppression d'une Adresse ... \n");
		///////////////////
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
		menu.setTitle("*** Inscription ***");
		menu.addItem(new MenuItem("Option"));
	}	
	
	public static void main(String[] args) {
		
		new PhotoNum(new UtilExecute(), new Consulter(), new Inserer()).mainMenu();
	}
}
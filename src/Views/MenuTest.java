package Views;

import java.util.Scanner;

public class MenuTest {

	private static Scanner sc = new Scanner(System.in);
	
	private void mainMenu() {
		Menu menu = new Menu();
		menu.setTitle("*** Menu Principal ***");
		menu.addItem(new MenuItem("Se connecter", this, "sousMenuA"));
		menu.addItem(new MenuItem("S'inscrire", this, "sousMenuB"));
		menu.execute();
	}

	public void sousMenuA() {
		Menu menu = new Menu();
		menu.setTitle("*** Bonjour client ***");
		menu.addItem(new MenuItem("Faire Une Impression", this, "sousMenuImpression"));
		menu.addItem(new MenuItem("Mes Fichiers Image", this, "sousMenuFichiersImage"));
		menu.addItem(new MenuItem("Mes Photos", this, "sousMenuPhotos"));
		menu.addItem(new MenuItem("Afficher Mes Commandes", this, "performAfficherCommandes"));
		menu.addItem(new MenuItem("Mon Profil", this, "sousMenuProfil"));
		menu.execute();
	}
	
	public void sousMenuB() {
		Menu menu = new Menu();
		menu.setTitle("*** Inscription ***");
		// ici ton Util Execute
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

	public static void main(String[] args) {
		new MenuTest().mainMenu();
	}
}
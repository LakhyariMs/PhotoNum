package Oracle.connexionSGBD;

import java.util.ArrayList;
import java.util.List;

import Oracle.crudSGBD.Consulter;
import PhotoNum.adresse.Adresse;
import PhotoNum.adresse.AdressePR;
import PhotoNum.codePromo.CodePromo;
import PhotoNum.codePromo.CodeUtilisateur;
import PhotoNum.commande.Commande;
import PhotoNum.commande.HistoriqueCommande;
import PhotoNum.fichiers.FichierImage;
import PhotoNum.fichiers.Photo;
import PhotoNum.inventaire.Inventaire;
import PhotoNum.user.Admin;
import PhotoNum.user.Client;

public class PhotoNum {

	public static void main(String[] args) {

		Consulter consulter = new Consulter();

		System.out.println("---------------------Inventaire-----------------------");
		ArrayList<Inventaire> lesInventaires = consulter.getAllInventaire();

		for (Inventaire inventaire : lesInventaires) {
			System.out.println(inventaire.toString()); // La table est vide dans la base
		}

		System.out.println("---------------------Commandes-----------------------");
		ArrayList<Commande> lesCommandes = consulter.getAllCommande();

		for (Commande commande : lesCommandes) {
			System.out.println(commande.toString()); // La table est vide dans la base
		}

		System.out.println("---- Commande d'un client ");
		ArrayList<Commande> lesCommandes2 = consulter.getAllClientCommande("1@uga.com");

		for (Commande commande : lesCommandes2) {
			System.out.println(commande.toString()); // La table est vide dans la base
		}

		System.out.println("---------------------Historique-----------------------");

		ArrayList<HistoriqueCommande> historique = consulter.getHistorique();

		for (HistoriqueCommande hc : historique) {
			System.out.println(historique.toString()); // La table est vide dans la base
		}

		System.out.println("-------------------------------------Info Client----------------------------------------");

		Client client = consulter.getClient("said@uga.com");
		System.out.println(client.toString());
		
		
		System.out.println("--------------------- Fichier Images -----------------------");

		ArrayList<FichierImage> fichiers = consulter.getClientFichierImage("said@uga.com");

		for (FichierImage adr : fichiers ) {
			System.out.println(adr.toString()); // La table est vide dans la base
		}
		
		
		System.out.println("---------------------Adresse Perso-----------------------");

		ArrayList<Adresse> adresses = consulter.getClientAdresses("said@uga.com");

		for (Adresse adr : adresses ) {
			System.out.println(adr.toString()); // La table est vide dans la base
		}
		
		
		ArrayList<CodeUtilisateur> codes = consulter.getClientCodeUser("said@uga.com");

		for (CodeUtilisateur cd: codes ) {
			System.out.println(cd.toString()); // La table est vide dans la base
		}
		
		System.out.println("---------------------Adresse Point Relais-----------------------");

		ArrayList<AdressePR> adressesPR = consulter.getAdressePointDeRelai();

		for (AdressePR zer : adressesPR ) {
			System.out.println(zer.toString()); // La table est vide dans la base
		}
		
		
		System.out.println("---------------------Code Promo-----------------------");

		ArrayList<CodePromo> codesPromo = consulter.getAllCodePromo();

		for (CodePromo cp : codesPromo ) {
			System.out.println(cp.toString()); // La table est vide dans la base
		}

		/*
		 * System.out.println("------------- les Clients ----------------");
		 * 
		 * Client client = null ; client = consulter.getClient("1@uga.com");
		 * System.out.println(client.getNom()+" "+client.getPrenom()+" "+client.
		 * getAdresse(1).toString()+" => "+client.getEmail());
		 * 
		 * Admin admin = null ; admin = consulter.getAdmin("pgeere0@bing.com"); if(admin
		 * != null){
		 * System.out.println(client.getNom()+" "+client.getPrenom()+" => "+client.
		 * getEmail()); }else{ System.out.println("Vide"); } // ------------ Test
		 * Fichier Image List<FichierImage> fichiers =
		 * consulter.getClientFichierImage("jbaudry1@twitpic.com"); for(FichierImage
		 * file : fichiers) {
		 * System.out.println(file.getChemin()+" "+file.getResolution()+" "+file.
		 * getInformation()+" => "+file.getEmailClient()); for(Photo photo :
		 * file.getAllPhoto()) { System.out.println(photo.toString()); } }
		 */

		/*
		 * List<Client> listclient = consulter.getAllClients(); for (Client client1 :
		 * listclient) {
		 * System.out.println(client1.getNom()+" "+client1.getPrenom()+" => "+client1.
		 * getEmail()); }
		 */

		/*
		 * System.out.println("------------- les Admins ----------------");
		 * 
		 * List<Admin> listAdmin = consulter.getAllAdmins(); for (Admin ad : listAdmin )
		 * { System.out.println(ad.getNom()+" "+ad.getPrenom()+" => "+ad.getEmail()); }
		 */
	}

}

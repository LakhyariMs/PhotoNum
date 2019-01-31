package main;

import java.util.ArrayList;
import java.util.Scanner;

import models.adresse.Adresse;
import models.adresse.AdressePR;
import models.adresse.AdressePerso;
import models.fichiers.FichierImage;
import models.fichiers.Photo;
import models.user.Admin;
import models.user.Client;
import oracle.crudSGBD.Consulter;

public class UtilExecute {
	private Consulter consulter;
	
	
	public UtilExecute() {
		this.consulter = new Consulter();
	}
	
	
	/**
	 * Verifier si le compte est celui d'un client
	 * @param email mail client
	 * @param mdp mdp client
	 * @return Objet Client sinon rien
	 */
	public Client isClient(String email , String mdp ) {
		ArrayList<Client> cliens = this.consulter.getAllClients();
		
		for (Client client : cliens) {
			if(client.getEmail().equals(email) && client.getMdp().equals(mdp)) {
				System.out.println("Compte Existe !");
				return client ;
			}	
		}
		
		System.out.println("Compte client erron� !");
		return null;
	}
	
	/**
	 * Verifier s'il s'agit d'un compte Administrateur
	 * @param email email de l'admin
	 * @param mdp mot de passe de l'admin
	 * @return Objet Admin sinon null 
	 */
	public Admin isAdmin(String email , String mdp ) {
		ArrayList<Admin> admins = this.consulter.getAllAdmins();
		
		for (Admin admin : admins ) {
			if(admin.getEmail().equals(email) && admin.getMdp().equals(mdp)) {
				System.out.println("Compte Correct !");
				return admin ;
			}	
		}
		System.out.println("Compte admin erron� !");
		return null;
	}
	
	/**
	 *  Genere un code promo Aleatoire
	 *  Normalement on doit donner au client un code promo  , si il depasse 100euro d'achat
	 * @return String-code promo
	 */
	public String generateCodePromo() {
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<10;x++)   { // j'ai pris par defaut la longeur du code promo = 10
	       int i = (int)Math.floor(Math.random() * chars.length() -1); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }
	    System.out.println(pass);
	    return pass;	    
	}
	
	public int generateID() {	
	    String chars = "1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<5;x++)   { // j'ai pris par defaut la longeur du code promo = 10
	       int i = (int)Math.floor(Math.random() * chars.length()); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }	    
	    return Integer.parseInt(pass);
	    
	}
<<<<<<< HEAD
	
public int commandeChoix() {
=======

	public int commandeChoix() {
>>>>>>> b2971367f945112e27e01336222caa88b51e4929
		
		Scanner sc = new Scanner(System.in);
		int choix ;
		System.out.println("* ETAPE 1 : choisir le ....");
		System.out.println("1... Album");
		System.out.println("2... Agenda");
		System.out.println("3... Cadre");
		System.out.println("4... Calendrier");
		System.out.println("5... Tirage");
		System.out.println("0... Retour");
		System.out.print("> ");
		choix = sc.nextInt();

			return choix ;
	}
	
	
	public String showReferences(int typeImpression){
		String categorie = null;
		ArrayList<String> references;
		Consulter consulter = new Consulter();
		Scanner sc = new Scanner(System.in);
		
		switch (typeImpression) {
			case 1 : 
				categorie = "album";
			break;
			case 2 : 
				categorie = "agenda";
			break;
			case 3 : 
				categorie = "cadre";
			break;
			case 4 : 
				categorie = "calendrier";
			break;
			case 5 : 
				categorie = "tirage";
			break;
		}
		
		references = consulter.getReferences(categorie);
		int i = 1;
		System.out.println("*** ETAPE 3 : choisir la reference .");
		for (String string : references) {
			
			System.out.print(i+"."+string+"  ");
			i++;
		}
		System.out.println();
		System.out.println("-> Veuilliez choisir une Reference");
		System.out.print("> ");
		String ref = sc.nextLine();
		System.out.println();
		
		return ref ;
	}
	
	
	public int showAdresses(String email ) {
		ArrayList<Adresse> adressesPerso = new ArrayList<>();
		ArrayList<AdressePR> adressesPR = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int choix ;
		Consulter consulter = new Consulter();
		System.out.println("***** ETAPE 5 : choisir l'adresse de Livraison .");
		System.out.println("1... Adresses Perso");
		System.out.println("2... Adresses Point de Relais");
		choix = sc.nextInt();
		
		switch (choix) {
			case 1:
				adressesPerso = consulter.getClientAdresses(email);
				
				for (Adresse adr : adressesPerso ) {
					System.out.println(adr.toString());
				}
			break;

			case 2:
				adressesPR = consulter.getAdressePointDeRelai();
				
				for (Adresse adr : adressesPR ) {
					System.out.println(adr.toString());
				}
			break;
		}
		
		System.out.println("Entrez l'ID de l'adresse ");
		System.out.print("> ");
		int idAdresse = sc.nextInt();
		
		return idAdresse;
	}
	
	
	public int showPhoto(String email) {
		ArrayList<Photo> photos = null ;
		Scanner sc = new Scanner(System.in);
		int choix ;
		Consulter consulter = new Consulter(); 
		System.out.println("** ETAPE 2 : choisir la photo .");
		System.out.println("1... Mes Photos");
		System.out.println("2... Photos Partagee");
		System.out.println("3... Photos Populaire");
		System.out.println("0... Annuler");
		System.out.print("> ");
		choix = sc.nextInt();
		
		switch (choix) {
			
			case 1:  // Les photos d'un client
				photos = consulter.getPhotosClient(email);
			break;
			
			case 2: // Tous les photos public
				photos = new ArrayList<Photo>();
				for (FichierImage fi : consulter.getSharedFichierImage()) {
					for (Photo photo : fi.getAllPhoto()) {
						photos.add(photo);
					}
				}
			break;
			
			case 3: // Les photos populaires
				photos = consulter.getPopularPhoto();
			break;
			
			case 0 :
				return 0;
			
		}
		
		
		for (Photo photo : photos) {
			System.out.println(photo.toString());
		}
		
		System.out.println("Entrez l'ID du photo que vous souhaitez Imprimer");
		System.out.print("> ");
		int idPhoto = sc.nextInt();
		
		
		return idPhoto;		
	}
<<<<<<< HEAD

=======
	
>>>>>>> b2971367f945112e27e01336222caa88b51e4929
}

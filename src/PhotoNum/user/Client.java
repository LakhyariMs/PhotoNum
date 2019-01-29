package PhotoNum.user;

import java.util.ArrayList;
import PhotoNum.adresse.AdressePerso;
import PhotoNum.codePromo.CodePromo;
import PhotoNum.commande.Commande;
import PhotoNum.fichiers.FichierImage;

public class Client extends Utilisateur {

	private boolean estActive ; // on part du principe que tt les comptes client sont activé au debut 
	private ArrayList<AdressePerso> adresses = new ArrayList<AdressePerso>() ;
	private ArrayList<CodePromo> codesPromo = new ArrayList<CodePromo>();
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	private ArrayList<FichierImage> fichierImage  = new ArrayList<FichierImage>();
	
	// Constructeur --------------------------------------------------------------
	
	//-------- constructeur avec une seul adresse 
	
	public Client(String email , String nom , String prenom , String mdp , AdressePerso adresse) {
		super(email,nom ,prenom , mdp);
		this.estActive = true ;
		this.adresses.add(adresse);
	}
	

	
	public Client() {
		super();
	}



	//-------- constructeur avec un tableau d'adresse
	
	public Client(String email , String nom , String prenom , String mdp ) {
		super(email,nom ,prenom , mdp);
		this.estActive = true ;
	}	
	
	public Client(String email , String nom , String prenom , String mdp , ArrayList<AdressePerso> adresses) {
		super(email,nom ,prenom , mdp);
		this.estActive = true ;
		this.adresses = adresses ;
	}
	
	// Setters & Getters --------------------------------------------------------------

	public boolean estActive() {
		return estActive;
	}

	public void setEstActive(boolean estActive) {
		this.estActive = estActive;
	}
	
	// Adresse -----------------------------------------
	
	public void addAdresse(AdressePerso newAdr ) {
		this.adresses.add(newAdr);
	}
	
	public boolean removeAdresse(AdressePerso oldAdr) {
		if(this.adresses.remove(oldAdr)) {
			return true ;
		}
		return false ;
	}
	
	public AdressePerso getAdresse(int id ) {
		for (AdressePerso adressePerso : adresses) {
			if(adressePerso.getId() ==  id)
				return adressePerso ;
		}
		return null ;
	}
	
	public ArrayList<AdressePerso> getAllAdresses() {
		return this.adresses;
	}
	
	public void SetAdresses(ArrayList <AdressePerso> adresses ) {
		this.adresses = adresses ;
	}
	
	// Codes Promos -----------------------------------------

	public void addCodePromo(CodePromo newCode ) {
		this.codesPromo.add(newCode);
	}
	
	public boolean removeCodePromo(CodePromo oldCode) {
		if(this.codesPromo.remove(oldCode)) {
			return true ;
		}
		return false ;
	}
	
	public ArrayList<CodePromo> getAllCodePromo() {
		return this.codesPromo;
	}
	
	public void SetCodePromo(ArrayList <CodePromo> codesPromo) {
		this.codesPromo = codesPromo ;
	}
	
	// Commandes -----------------------------------------

	public void addCommande(Commande newCmd ) {
		this.commandes.add(newCmd);
	}
	
		// cette methode sert a rien ... (je la laisse en cas ou )
	public boolean removeCommande(Commande oldCmd) {
		if(this.commandes.remove(oldCmd)) {
			return true ;
		}
		return false ;
	}
	
	public ArrayList<Commande> getAllCommandes() {
		return this.commandes;
	}
	
	public void SetCommandes (ArrayList<Commande> commandes) {
		this.commandes = commandes ;
	}
	
	// Fichier Image  -----------------------------------------

	public void addFichier(FichierImage file) {
		this.fichierImage.add(file);
	}
	
		// cette methode sert a rien ... (je la laisse en cas ou )
	public boolean removeFichier(FichierImage file) {
		if(this.fichierImage.remove(file)) {
			return true ;
		}
		return false ;
	}
	
	public ArrayList<FichierImage> getAllFichierImage() {
		return this.fichierImage;
	}
	
	public void SetFichierImage (ArrayList<FichierImage> fichiersImg) {
		this.fichierImage = fichiersImg ;
	}



	@Override
	public String toString() {
		return super.toString()+"Client [estActive=" + estActive + ", adresses=" + adresses + ", codesPromo=" + codesPromo
				+ ", commandes=" + commandes + ", fichierImage=" + fichierImage + "]";
	}
	
	
	
	

	
}

package PhotoNum.commande;

import java.sql.Date;
import java.util.ArrayList;

import PhotoNum.adresse.Adresse;
import PhotoNum.article.Article;

public class Commande {
	
	private int id ;
	private Date date; // a changer en Date 
	private ModeLivraison modeLivraison ;
	private float prixTotale ;
	private StatusCmd status ;
	private boolean estPaye ;
	private String emailClient ;
	private Adresse adresse ;
	private ArrayList<Article> articles = new ArrayList<Article>();
	
	

	// Constructeur --------------------------------------------------------------


	// Constructeur sans articles en attente d'ajout d'articles
	public Commande(int id , Date date , String modeLiv ,float prix , String s , int paye  , String mail) {
		this.id = id;
		this.date = date ;
		this.setModeLivraison(modeLiv);
		this.prixTotale = prix ;
		this.setStatus(s);
		this.setEstPaye(paye);
		this.emailClient = mail ;
	}
		// Constructeur avec un seul article 
	public Commande(int id , Date date , String modeLiv ,float prix , String s , int paye , Article article ) {
		this.id = id;
		this.date = date ;
		this.setModeLivraison(modeLiv);
		this.prixTotale = prix ;
		this.setStatus(s);
		this.setEstPaye(paye); ;
		this.articles.add(article);
	}
		// Constructeur avec des articles
	public Commande(int id , Date date , String modeLiv ,float prix , String s , int paye , ArrayList<Article> articles ) {
		this.id = id;
		this.date = date ;
		this.setModeLivraison(modeLiv);
		this.prixTotale = prix ;
		this.setStatus(s);
		this.setEstPaye(paye);
		this.articles = articles ;
	}

	
	
	// Setters & Getters --------------------------------------------------------------

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public ModeLivraison getModeLivraison() {
		return modeLivraison;
	}

	
	public void setModeLivraison(String modeLivraison) {
		// J'ai mis le switch parceque c est compliqué de bosser avec l'enum
		// sachant qu'il accepte pas les espaces dans les noms , contrairement a de ce qu il y a dans la base
		switch (modeLivraison) {
		
		case "Point relais":
			this.modeLivraison = ModeLivraison.PointRelais;
		break;
			
		case "Domicile":
			this.modeLivraison = ModeLivraison.Domicile;
		break;
		
		}
	}

	public float getPrixTotale() {
		return prixTotale;
	}

	public void setPrixTotale(float prixTotale) {
		this.prixTotale = prixTotale;
	}

	public StatusCmd getStatus() {
		return status;
	}

	public void setStatus(String status) {
		
		switch (status) {
			case "En cours":
				this.status = StatusCmd.EnCours;
			break;
				
			case "En attente":
				this.status = StatusCmd.EnAttente;
			break;
			
			case "Prêt  à l envoi":
				this.status = StatusCmd.PretAEnvoyer;
			break;
			
			case "Envoyée":
				this.status = StatusCmd.Validee;
			break;
			
			case "Annulee":
				this.status = StatusCmd.Annulee;
			break;
		}
	}

	public boolean estPaye() {
		return estPaye;
	}

	public void setEstPaye(int entier) {
		switch (entier) {
		case 0:
			this.estPaye = false ;
			break;
			
		case 1:
			this.estPaye = true ;
			break;	
		}
	}

	public String getEmailClient() {
		return emailClient;
	}
	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	public ArrayList<Article> getArticles() {
		return articles;
	}
	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}
	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + ", modeLivraison=" + modeLivraison + ", prixTotale="
				+ prixTotale + ", status=" + status + ", estPaye=" + estPaye + ", emailClient=" + emailClient
				+ ", adresse=" + adresse + ", articles=" + articles + "]";
	}
	
	

	
	
	

	
	
	
	

	
}

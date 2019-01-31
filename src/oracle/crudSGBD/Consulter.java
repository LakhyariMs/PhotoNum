package oracle.crudSGBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.adresse.Adresse;
import models.adresse.AdressePR;
import models.adresse.AdressePerso;
import models.article.Article;
import models.codePromo.CodePromo;
import models.codePromo.CodeUtilisateur;
import models.commande.Commande;
import models.commande.HistoriqueCommande;
import models.fichiers.FichierImage;
import models.fichiers.Photo;
import models.inventaire.Inventaire;
import models.user.Admin;
import models.user.Client;

public class Consulter {

	private Connexion connexion;

	public Consulter() {
		this.connexion = new Connexion();
	}

	// Client
	// ------------------------------------------------------------------------
	/**
	 * Liste de tous les clients
	 * 
	 * @return Liste Client
	 */
	public ArrayList<Client> getAllClients() {
		ArrayList<Client> clients = new ArrayList<>();
		String query = "SELECT email,nom,prenom,mdp FROM client";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				Client client = new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

	/**
	 * Liste de tous les Admins
	 * 
	 * @return Liste Admin
	 */
	public ArrayList<Admin> getAllAdmins() {
		ArrayList<Admin> lesAdmins = new ArrayList<>();
		String query = "SELECT email,nom,prenom,mdp FROM administrateur";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				Admin admin = new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				lesAdmins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}// Méthodes -------------------------------------------------------

		return lesAdmins;
	}

	/**
	 * Liste les informations d'un client
	 * 
	 * @param email email de l'utilisateur (email est unique)
	 * @return
	 */
	// il manque d'ajouter les adresses
	public Client getClient(String email) {
		Client client = null;
		String query = "SELECT c.email,nom,prenom,mdp,a.adresse FROM client c , adresseperso a WHERE c.email ='" + email
				+ "' AND c.email = a.email";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				client = new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				client.addAdresse(new AdressePerso(1, rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;

	}

	/**
	 * Liste les information sur un admin (email est unique)
	 * 
	 * @param email
	 * @return
	 */
	public Admin getAdmin(String email) {
		Admin admin = null;
		String query = "SELECT email,nom,prenom,mdp FROM Administrateur WHERE email ='" + email + "'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				admin = new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;

	}

	// Fichier Images
	// ------------------------------------------------------------------------

	/**
	 * Recuperer tous les fichiersImage de la BD
	 * 
	 * @return Liste de fichierImage
	 */
	public ArrayList<FichierImage> getAllFichiersImages() {
		ArrayList<FichierImage> fichiers = new ArrayList<FichierImage>();
		String query = "SELECT * FROM fichierImage";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				FichierImage fichier = new FichierImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6));
				fichiers.add(fichier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fichiers;
	}

	/**
	 * Recupere les fichiers Images d'un client
	 * 
	 * @param email email (indetifiant) client
	 * @return Liste fichier Images
	 */
	public ArrayList<FichierImage> getClientFichierImage(String email) {
		ArrayList<FichierImage> fichiers = new ArrayList<FichierImage>();
		ArrayList<Photo> photos = new ArrayList<Photo>();
		String query = "SELECT * FROM fichierImage WHERE email = '" + email + "'";
		ResultSet rs = this.connexion.selectQuery(query);
		ResultSet rs2;
		try {
			while (rs.next()) {
				FichierImage fichier = new FichierImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6));

				rs2 = this.connexion.selectQuery("SELECT * FROM photo WHERE idfichier=" + rs.getInt(1));
				while (rs2.next()) {
					Photo photo = new Photo(rs2.getInt(1), rs2.getString(2), rs2.getString(3));
					photos.add(photo);
				}
				fichier.setPhotos(photos);
				// pour réinitialiser la variable
				photos = new ArrayList<Photo>();

				fichiers.add(fichier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fichiers;
	}
	
	/**
	 * Recupere les fichiers Images d'un client
	 * 
	 * @param email email (indetifiant) client
	 * @return Liste Photos
	 */
	public ArrayList<Photo> getPhotosClient(String email) {
		ArrayList<Photo> photos = new ArrayList<Photo>();
		String query = "SELECT * FROM fichierImage WHERE email = '" + email + "'";
		ResultSet rs = this.connexion.selectQuery(query);
		ResultSet rs2;
		try {
			while (rs.next()) {
				rs2 = this.connexion.selectQuery("SELECT * FROM photo WHERE idfichier=" + rs.getInt(1));
				while (rs2.next()) {
					Photo photo = new Photo(rs2.getInt(1), rs2.getString(2), rs2.getString(3));
					photos.add(photo);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photos;
	}
	
	
	/**
	 * Afficher Tous les fichiers qui partager
	 * 
	 * @return liste des Fichier Image qui sont partager 
	 */
	public ArrayList<FichierImage> getSharedFichierImage()
	{
		ArrayList<FichierImage> fichiers = new ArrayList<FichierImage>();
		ArrayList<Photo> photos = new ArrayList<Photo>();
		String query = "SELECT * FROM fichierImage WHERE estpartage = 1" ;
		ResultSet rs = this.connexion.selectQuery(query);
		ResultSet rs2;
		try {
			while (rs.next()) {
				FichierImage fichier = new FichierImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6));

				rs2 = this.connexion.selectQuery("SELECT * FROM photo WHERE idfichier=" + rs.getInt(1));
				while (rs2.next()) {
					Photo photo = new Photo(rs2.getInt(1), rs2.getString(2), rs2.getString(3));
					photos.add(photo);
				}
				fichier.setPhotos(photos);
				// pour r�initialiser la variable
				photos = new ArrayList<Photo>();

				fichiers.add(fichier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fichiers;
	}
	

	// Commandes -------------------------------------------------------------------

	public ArrayList<Commande> getAllCommande() {

		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		String query = "SELECT * FROM Commande";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				Commande commande = new Commande(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getFloat(4),
						rs.getString(5), rs.getInt(7), rs.getString(6));
				Integer idAdrPerso = rs.getInt(8);
				Integer idAdrPR = rs.getInt(9);

				if (idAdrPerso != 0) {
					String query2 = "Select idAdressePerso , adresse from adresseperso WHERE idAdressePerso = "
							+ idAdrPerso + " AND  email = '" + rs.getString(6) + "'";
					ResultSet rs2 = this.connexion.selectQuery(query2);
					while (rs2.next()) {
						commande.setAdresse(new Adresse(rs2.getInt(1), rs2.getString(2)));
					}
				}
				if (idAdrPR != 0) {
					String query2 = "SELECT * FROM adressePR WHERE idAdressePr = " + idAdrPR;
					ResultSet rs2 = this.connexion.selectQuery(query2);
					while (rs2.next()) {
						commande.setAdresse(new AdressePR(rs2.getInt(1), rs2.getString(2), rs.getString(3)));
					}
				}

				lesCommandes.add(commande);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lesCommandes;
	}

	/**
	 * Recuperer toutes les commandes faites par le client lambda
	 * 
	 * @param email email du client
	 * @return liste de toutes ces commandes
	 */
	// a terminer
	public ArrayList<Commande> getAllClientCommande(String email) {

		ArrayList<Commande> commandes = new ArrayList<Commande>();
		String query = "SELECT * FROM Commande where email = '" + email + "'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				Commande commande = new Commande(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getFloat(4),
						rs.getString(5), rs.getInt(7), rs.getString(6));
				commandes.add(commande);

				Integer idAdrPerso = rs.getInt(8);
				Integer idAdrPR = rs.getInt(9);

				if (idAdrPerso != 0) {
					String query2 = "Select idAdressePerso , adresse from adresseperso WHERE idAdressePerso = "
							+ idAdrPerso + " AND  email = '" + rs.getString(6) + "'";
					ResultSet rs2 = this.connexion.selectQuery(query2);
					while (rs2.next()) {
						commande.setAdresse(new Adresse(rs2.getInt(1), rs2.getString(2)));
					}
				}
				
				if (idAdrPR != 0) {
					String query2 = "SELECT * FROM adressePR WHERE idAdressePr = " + idAdrPR;
					ResultSet rs2 = this.connexion.selectQuery(query2);
					while (rs2.next()) {
						commande.setAdresse(new AdressePR(rs2.getInt(1), rs2.getString(2), rs.getString(3)));
					}
				}

				ArrayList<Article> articles = new ArrayList<Article>();
				String query3 = "SELECT * FROM article WHERE idcommande =" + commande.getId();
				ResultSet rs3 = this.connexion.selectQuery(query3);
				while (rs3.next()) {
					Article article = new Article(rs3.getInt(1), rs3.getInt(2), rs3.getFloat(3));
					articles.add(article);
				}
				commande.setArticles(articles);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return commandes;
	}

	// a modfier probleme dans l'enumerateur
	public ArrayList<HistoriqueCommande> getHistorique() {

		ArrayList<HistoriqueCommande> historique = new ArrayList<HistoriqueCommande>();

		String query = "SELECT * FROM historique";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return historique;
	}

	// Article  ----------------------------------------------------------------------------------------

	// a revoire apres , aPromoucun idée

	// Inventaire ----------------------------------------------------------------------

	public ArrayList<Inventaire> getAllInventaire() {
		ArrayList<Inventaire> inventaires = new ArrayList<Inventaire>();
		String query = "SELECT * FROM inventaire ";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				// ici en Inventaire faut ajouter les ReferenceInvetaire pour les enums
				Inventaire inv = new Inventaire(rs.getString(1), rs.getInt(2), rs.getInt(3));
				inventaires.add(inv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventaires;
	}
	
	/**
	 * Recuperer le prix d'une reference
	 * @param reference Reference produit 
	 * @return prix de reference 
	 */
	public double getReferencePrice( String reference ) {
		double price = 0.0 ;
		String query = "SELECT prixdevente FROM inventaire WHERE reference = '"+reference+"'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				price = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return price ;
	}
	
	
	public ArrayList<String> getReferences( String typeImpression ) {
		
		ArrayList<String> references = new ArrayList<String>();
		String query = "SELECT reference FROM inventaire where categorie ='"+typeImpression+"'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				references.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return references;			
	}
	
	// Adresse -----------------------------------------------------------------------
	
	// Adresse Perso -----------------------------------------------

	
	public ArrayList<Adresse> getClientAdresses(String email) {
		ArrayList<Adresse> adresses = new ArrayList<Adresse>();
		String query = "SELECT * FROM AdressePerso WHERE email = '"+email+"'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				Adresse adresseClient = new AdressePerso(rs.getInt(1), rs.getString(2));
				adresses.add(adresseClient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresses;
	}
	
	// Adresse Point de Relais ----------------------------------------
	
	public ArrayList<AdressePR> getAdressePointDeRelai () {
		ArrayList<AdressePR> adresses = new ArrayList<AdressePR>();
		String query = "SELECT * FROM AdressePR ";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				AdressePR adressePR = new AdressePR(rs.getInt(1), rs.getString(2), rs.getString(3));
				adresses.add(adressePR);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresses;
	}
	
	// Code Promo -----------------------------------------------------------------------

	public ArrayList<CodeUtilisateur> getClientCodeUser(String email ) {
		ArrayList<CodeUtilisateur> codesUser = new ArrayList<CodeUtilisateur>();
		String query = "SELECT * FROM codeUtilisateur WHERE email= '"+email+"'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				//Client client = new Client(); a modier getClient()
				CodeUtilisateur code = new CodeUtilisateur(getClient(email), rs.getInt(1),rs.getString(2), rs.getInt(3));
				codesUser.add(code);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codesUser;
	}
	
	
	public ArrayList<CodePromo> getAllCodePromo() {
		ArrayList<CodePromo> codePromo = new ArrayList<CodePromo>();
		String query = "SELECT * FROM CodeComm ";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				CodePromo code = new CodePromo(rs.getInt(1), rs.getString(2), rs.getDouble(3));
				codePromo.add(code);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codePromo;
	}
	
	
	public ArrayList<Photo> getPopularPhoto() {
		
		ArrayList<Photo> photos = new ArrayList<Photo>();
		String query = "SELECT idPhoto ,parametres,description FROM (SELECT idphoto FROM cadre GROUP BY idphoto having count(*) >2 ) natural join photo ";				
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while (rs.next()) {
				Photo photo = new Photo(rs.getInt(1), rs.getString(2), rs.getString(3));
				photos.add(photo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String query2 = "SELECT idPhoto ,parametres,description FROM (SELECT idphoto FROM tirage GROUP BY idphoto having count(*) >2 ) natural join photo ";				
		ResultSet rs2 = this.connexion.selectQuery(query);
		try {
			while (rs2.next()) {
				Photo photo = new Photo(rs.getInt(1), rs.getString(2), rs.getString(3));
				photos.add(photo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return photos ;
	}


}

package Oracle.crudSGBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import PhotoNum.adresse.AdressePerso;
import PhotoNum.fichiers.FichierImage;
import PhotoNum.fichiers.Photo;
import PhotoNum.user.Admin;
import PhotoNum.user.Client;

public class Consulter {
		
	private Connexion connexion;
	
	public Consulter() {
		this.connexion = new Connexion();
	}
	
	// Client ------------------------------------------------------------------------
	
	/**
	 * Liste de tous les clients
	 * @return Liste Client
	 */
	public ArrayList<Client> getAllClients(){
		ArrayList<Client> clients = new ArrayList<>();
		String query ="SELECT email,nom,prenom,mdp FROM client";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while(rs.next()) {
				Client client = new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	/**
	 * Liste de tous les Admins 
	 * @return Liste Admin
	 */
	public ArrayList<Admin> getAllAdmins(){
		ArrayList<Admin> lesAdmins = new ArrayList<>();
		String query = "SELECT email,nom,prenom,mdp FROM administrateur";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while(rs.next()) {
				Admin admin= new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				lesAdmins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesAdmins;
	}
	
	/**
	 * Liste les infromations d'un client 
	 * @param email email de l'utilisateur (email est unique)
	 * @return
	 */
	// il manque d'ajouter les adresses et les commandes
	public Client getClient(String email) {
		Client client = null ;
		String query ="SELECT c.email,nom,prenom,mdp,a.adresse FROM client c , adresseperso a WHERE c.email ='"+email+"' AND c.email = a.email";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while(rs.next()) {
				client = new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				client.addAdresse(new AdressePerso(1, rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
		
	}
	
	/**
	 * Liste les information sur un admin (email est unique)
	 * @param email
	 * @return
	 */
	public Admin getAdmin(String email) {
		Admin admin = null ;
		String query ="SELECT email,nom,prenom,mdp FROM Administrateur WHERE email ='"+email+"'";
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while(rs.next()) {
				admin = new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
		
	}
	
	
	// Fichier Images  ------------------------------------------------------------------------

	/**
	 *  Recuperer tous les fichiersImage de la BD
	 * @return Liste de fichierImage
	 */
	public ArrayList<FichierImage> getAllFichiersImages(){
		ArrayList<FichierImage> fichiers = new ArrayList<FichierImage>();
		String query = "SELECT * FROM fichierImage" ;
		ResultSet rs = this.connexion.selectQuery(query);
		try {
			while(rs.next()) {
				FichierImage fichier =new FichierImage(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getString(4), rs.getInt(5), rs.getString(6));
				fichiers.add(fichier);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fichiers;
	}
	
	/**
	 * Recupere les fichiers Images d'un client
	 * @param email email (indetifiant) client
	 * @return Liste fichier Images
	 */
	public ArrayList<FichierImage> getClientFichierImage(String email){
		ArrayList<FichierImage> fichiers = new ArrayList<FichierImage>();
		ArrayList<Photo> photos = new ArrayList<Photo>();
		String query = "SELECT * FROM fichierImage WHERE email = '"+email+"'";
		ResultSet rs = this.connexion.selectQuery(query);
		ResultSet rs2 ;
		try {
			while(rs.next()) {
				FichierImage fichier =new FichierImage(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getString(4), rs.getInt(5), rs.getString(6));
				
				rs2 = this.connexion.selectQuery("SELECT * FROM photo WHERE idfichier="+rs.getInt(1));
				while (rs2.next()) {
					Photo photo = new Photo(rs2.getInt(1), rs2.getString(2), rs2.getString(3));
					photos.add(photo);
				}
				fichier.setPhotos(photos);
				// pour réinitialiser la variable 
				photos = new ArrayList<Photo>();
				
	
				fichiers.add(fichier);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return fichiers ;
	}
	
}

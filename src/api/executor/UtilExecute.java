package api.executor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import Oracle.crudSGBD.Consulter;
import PhotoNum.user.Admin;
import PhotoNum.user.Client;

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
				System.out.println("Compte Correcte !");
				return client ;
			}	
		}
		
		System.out.println("Compte Erroné ! , Réessayer a Nouveau  . ");
		return null;
	}
	
	/**
	 * Verifier s il s agit d'un compte Administrateur
	 * @param email email Admin
	 * @param mdp mdp Amdin
	 * @return Objet Admin sino null 
	 */
	public Admin isAdmin(String email , String mdp ) {
		ArrayList<Admin> admins = this.consulter.getAllAdmins();
		
		for (Admin admin : admins ) {
			if(admin.getEmail().equals(email) && admin.getMdp().equals(mdp)) {
				System.out.println("Compte Correcte !");
				return admin ;
			}	
		}
		
		System.out.println("Compte Erroné ! , Réessayer a Nouveau  . ");
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
}

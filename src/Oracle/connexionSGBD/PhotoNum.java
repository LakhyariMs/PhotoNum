package Oracle.connexionSGBD;

import java.util.List;

import Oracle.crudSGBD.Consulter;
import PhotoNum.fichiers.FichierImage;
import PhotoNum.fichiers.Photo;
import PhotoNum.user.Admin;
import PhotoNum.user.Client;

public class PhotoNum {

	public static void main(String[] args) {

		
		Consulter consulter = new Consulter();
		
		System.out.println("------------- les Clients ----------------");
		
		Client client = null ;
		client = consulter.getClient("jbaudry1@twitpic.com");
		System.out.println(client.getNom()+" "+client.getPrenom()+" "+client.getAdresse(1).toString()+" => "+client.getEmail());
		
		Admin admin = null ;
		admin = consulter.getAdmin("pgeere0@bing.com");
		if(admin != null){
			System.out.println(client.getNom()+" "+client.getPrenom()+" => "+client.getEmail());
		}else{
			System.out.println("Vide");
		}
	 // ------------ Test Fichier Image	
		List<FichierImage> fichiers = consulter.getClientFichierImage("jbaudry1@twitpic.com");
		for(FichierImage file : fichiers) {
			System.out.println(file.getChemin()+" "+file.getResolution()+" "+file.getInformation()+" => "+file.getEmailClient());
				for(Photo photo : file.getAllPhoto()) {
					System.out.println(photo.toString());
				}
		}

		
/*		List<Client> listclient = consulter.getAllClients();
		for (Client client1 : listclient) {
			System.out.println(client1.getNom()+" "+client1.getPrenom()+" => "+client1.getEmail());
		}*/
		
/*		System.out.println("------------- les Admins ----------------");
		
		List<Admin> listAdmin = consulter.getAllAdmins();
		for (Admin ad : listAdmin ) {
			System.out.println(ad.getNom()+" "+ad.getPrenom()+" => "+ad.getEmail());
		}*/
	}

}

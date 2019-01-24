package Oracle.connexionSGBD;

import java.util.List;

import Oracle.crudSGBD.Consulter;
import PhotoNum.fichiers.FichierImage;
import PhotoNum.user.Admin;
import PhotoNum.user.Client;

public class PhotoNum {

	public static void main(String[] args) {

		
		Consulter consulter = new Consulter();
		
		System.out.println("------------- les Clients ----------------");
		
		Client client = null ;
		client = consulter.getClient("pgeere0@bing.com");
		System.out.println(client.getNom()+" "+client.getPrenom()+" "+client.getAdresse(1).toString()+" => "+client.getEmail());
		
		Admin admin = null ;
		admin = consulter.getAdmin("pgeere0@bing.com");
		if(admin != null){
			System.out.println(client.getNom()+" "+client.getPrenom()+" => "+client.getEmail());
		}else{
			System.out.println("Vide");
		}
		
		List<FichierImage> fichiers = consulter.getClientFichierImage("pgeere0@bing.com");
		for(FichierImage file : fichiers) {
			System.out.println(file.getChemin()+" "+file.getResolution()+" "+file.getInformation()+" => "+file.getEmailClient());
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

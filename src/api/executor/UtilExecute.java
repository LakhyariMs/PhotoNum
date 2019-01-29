package api.executor;

import java.util.ArrayList;

import Oracle.crudSGBD.Consulter;
import PhotoNum.user.Client;

public class UtilExecute {
	private Consulter consulter;
	
	
	public UtilExecute() {
		this.consulter = new Consulter();
	}
	
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
}

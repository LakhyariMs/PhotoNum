package scenarios;

import oracle.crudSGBD.Connexion;
import views.ConsoleUtils;

public class Scenario4 {

	private static final Connexion connexion = new Connexion();

	public void performScenarioT1() {
		System.out.println("*** Terminal 1 ***\n");
		
		System.out.println("On change le statut du fichier Image 3 .. ");
		connexion.executeQuery("UPDATE FichierImage SET estPartage = 1 WHERE idFichier = 3 ");
		ConsoleUtils.pauseExecution();
		
		String query1 = "insert into Commande (idCommande, dateCommande, modeLivraison, prixTotal, statut, email, estPaye, idAdressePerso, idCodeUtilisateur) "
				+ "values (20, '01-FEB-19', 'Domicile', 65.83, 'En attente', 'yassine@uga.com', 0, 10, 10)";
		String query2 = "insert into Commande (idCommande, dateCommande, modeLivraison, prixTotal, statut, email, estPaye, idAdressePerso, idCodeUtilisateur) "
				+ "values (21, '01-FEB-19', 'Domicile', 65.83, 'En attente', 'yassine@uga.com', 0, 4, 4)";
		System.out.println("On simule une commande .. ");
		connexion.insertQuery(query1);
		ConsoleUtils.pauseExecution();
		System.out.println(".. Une autre commande .. ");
		connexion.insertQuery(query2);
		ConsoleUtils.pauseExecution();
		
		System.out.println(" On commit ..  ");
		connexion.commit();
		ConsoleUtils.pauseExecution();
		
		System.out.println("On change le statut de la commande 1");
		query1 = "UPDATE Commande SET statut='PretAEnvoyer' WHERE idCommande=20";
		query2 = "UPDATE Commande SET statut='PretAEnvoyer' WHERE idCommande=21";
		
		System.out.println("On change le statut de la commande 1 .. ");
		connexion.insertQuery(query1);
		ConsoleUtils.pauseExecution();
		
		System.out.println(".. maintenant de la deuxieme de la commande 2 .. ");
		connexion.insertQuery(query2);
		ConsoleUtils.pauseExecution();
		
		System.out.println("Fin.");
		connexion.commit();	
		ConsoleUtils.pauseExecution();
		

	}

	public void performScenarioT2() {
		System.out.println("*** Terminal 2 ***\n");

		String query1 = "UPDATE Commande SET statut='Envoyee' WHERE idCommande=21";
		String query2 = "UPDATE Commande SET statut='Envoyee' WHERE idCommande=20";
		
		System.out.println("On change le statut de la commande 2 .. ");
		connexion.insertQuery(query1);
		ConsoleUtils.pauseExecution();
		
		System.out.println(".. maintenant de la deuxieme de la commande 1 .. ");
		connexion.insertQuery(query2);
		ConsoleUtils.pauseExecution();
	
		connexion.commit();
		System.out.println("Fin.");
		
	}

}
	


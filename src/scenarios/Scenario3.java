package scenarios;

import java.sql.Connection;
import oracle.crudSGBD.Connexion;
import oracle.crudSGBD.Consulter;
import oracle.crudSGBD.Inserer;
import oracle.helpersSGBD.PrinterUtils;
import views.ConsoleUtils;

public class Scenario3 {

	private static final Connexion connexion = new Connexion();
	private static final Inserer inserer = new Inserer();
	private static final Consulter consulter = new Consulter();

	public void performScenarioT1() {

		System.out.println("*** Terminal 1 ***\n");
		
		connexion.setIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
		ConsoleUtils.pauseExecution();
		
		System.out.println("On commande 2 articles .. ");
		String query = "insert into Commande (idCommande, dateCommande, modeLivraison, prixTotal, statut, email, estPaye, idAdressePerso, idCodeUtilisateur)"
				+ " values (5, '01-FEB-19', 'Domicile', 65.83, 'En attente', 'paola@uga.com', 0, 8, 8)";
		connexion.executeQuery(query);
		inserer.addArticle(10, 2, 169.34, 5, 10);
		ConsoleUtils.pauseExecution();

		PrinterUtils.print(consulter.getAllCommande());
		ConsoleUtils.pauseExecution();
		
		//
		PrinterUtils.print(consulter.getAllCommande());
		ConsoleUtils.pauseExecution();
		
		connexion.commit();
		ConsoleUtils.pauseExecution();
				
		System.out.println("On affiche les fichiers Images ..");
		PrinterUtils.print(consulter.getAllFichiersImages());
		
		ConsoleUtils.pauseExecution();
		
		System.out.println("Fin.");
	}

	public void performScenarioT2() {

		System.out.println("*** Terminal 2 ***\n");
		
		System.out.println("On verifie que ce client ne connait pas la commande .. ");
		ConsoleUtils.pauseExecution();
		PrinterUtils.print(consulter.getAllCommande());
		
		ConsoleUtils.pauseExecution();
		System.out.println("On change le statut du fichier image .. ");
		if (connexion.executeQuery("UPDATE FichierImage SET estPartage = 0 WHERE idFichier = 1"))
			System.out.println("Mise a jour reussie.");

		ConsoleUtils.pauseExecution();

		PrinterUtils.print(consulter.getAllFichiersImages());
		ConsoleUtils.pauseExecution();
		
		PrinterUtils.print(consulter.getAllCommande());
		ConsoleUtils.pauseExecution();	
		
		System.out.println("Fin.");
		
		
	}

}


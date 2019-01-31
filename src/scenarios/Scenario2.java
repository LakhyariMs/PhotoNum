package scenarios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.crudSGBD.Connexion;
import oracle.crudSGBD.Consulter;
import oracle.crudSGBD.Inserer;
import oracle.crudSGBD.Update;
import oracle.helpersSGBD.PrinterUtils;
import oracle.helpersSGBD.SQLWarningsExceptions;
import views.ConsoleUtils;

public class Scenario2 {

	private static final Connexion connexion = new Connexion();
	private static final Inserer inserer = new Inserer();
	private static final Consulter consulter = new Consulter();
	private static final Update update = new Update();

	public void performScenarioT1() {

		System.out.println("*** Terminal 1 ***\n");
		
		PrinterUtils.print(consulter.getAllInventaire());
		ConsoleUtils.pauseExecution();
		System.out.println("On apprivisionne le produit LuxeG_Grand de 10");
		if (update.updateReferenceQte("LuxeG_Grand", 10))
			System.out.println("Insertion reussie !");
		ConsoleUtils.pauseExecution();
		PrinterUtils.print(consulter.getAllInventaire());	
		
		ConsoleUtils.pauseExecution();

		connexion.commit();
		
		ConsoleUtils.pauseExecution();


	}

	public void performScenarioT2() {

		System.out.println("*** Terminal 2 ***\n");

		connexion.setIsolationLevel(Connection.TRANSACTION_SERIALIZABLE);
		
		ConsoleUtils.pauseExecution();

		System.out.println("On insere un article .. ");
		inserer.addArticle(9, 2, 169.34, 4, 9);
		
		ConsoleUtils.pauseExecution();
		System.out.println("Session verrouillée .. ");
		
		ConsoleUtils.pauseExecution();
		
		String requete = "SELECT quantite FROM Inventaire whWHEREere Inventaire.reference = 'LuxeG_Grand'";
		ResultSet rs = connexion.selectQuery(requete);
		try {
			while (rs.next()) {
				System.out.println("Quantite Inventaire : " + rs.getInt(1));
			}
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		ConsoleUtils.pauseExecution();
		connexion.commit();
		
		ResultSet rs1 = connexion.selectQuery(requete);
		try {
			while (rs1.next()) {
				System.out.println("Quantite Inventaire : " + rs1.getInt(1));
			}
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		} finally {
			try {
				rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		ConsoleUtils.pauseExecution();
		
	}

}


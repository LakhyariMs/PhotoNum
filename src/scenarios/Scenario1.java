package scenarios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.crudSGBD.Connexion;
import oracle.crudSGBD.Inserer;
import oracle.helpersSGBD.SQLWarningsExceptions;
import views.ConsoleUtils;

public class Scenario1 {

	private static final Connexion connexion = new Connexion();
	private static final Inserer inserer = new Inserer();

	public void performScenarioT1() {

		System.out.println("*** Terminal 1 ***\n");

		// connexion.setAutoCommit(false);

		System.out.println("On insere un article .. ");
		inserer.addArticle(7, 2, 169.34, 3, 7);
		
		ConsoleUtils.pauseExecution();
		
		connexion.commit();
		
		ConsoleUtils.pauseExecution();

	}

	public void performScenarioT2() {

		System.out.println("*** Terminal 2 ***\n");

		connexion.setIsolationLevel(Connection.TRANSACTION_SERIALIZABLE);

		String requete = "SELECT quantite FROM Inventaire JOIN TypeImpression ON TypeImpression.idImpression = 7 "
				+ "WHERE Inventaire.reference = TypeImpression.reference";
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
		
		System.out.println("On insere un article .. ");
		inserer.addArticle(7, 2, 169.34, 3, 7);
		
		ConsoleUtils.pauseExecution();
		System.out.println("Session verrouillée .. ");
		
		ConsoleUtils.pauseExecution();
		connexion.commit();
		
		ConsoleUtils.pauseExecution();
		String requete1 = "SELECT quantite FROM Inventaire JOIN TypeImpression ON TypeImpression.idImpression = 8 "
				+ "WHERE Inventaire.reference = TypeImpression.reference";
		ResultSet rs1 = connexion.selectQuery(requete1);
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
		
	}

}

package Oracle.crudSGBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Oracle.connexionSGBD.SQLWarningsExceptions;

public class Inserer {

	private Connexion connexion;

	public Inserer() {
		this.connexion = new Connexion();
	}

	public void inscriptionClient(Connection conn, String email, String nom, String prenom, String mdp, int activated)
			throws SQLException {
		String requete = "INSERT INTO client(email, nom, prenom, MDP, estactive) " + "VALUES('" + email + "', '" + nom
				+ "', '" + prenom + "', '" + mdp + "', 1)";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Inscription reussie");
		} else {
			System.out.println("Echec de l'inscription");
		}
	}

	public void ajouterAdresse(Connection conn, String newAdresse, String email) throws SQLException {
		String requete = "INSERT INTO adressePerso(adresse, email) " + "VALUES('" + newAdresse + "', '" + email + "')";
		if (this.connexion.insertQuery(requete)) {
			System.out.println("Adresse ajoutée");
		} else {
			System.out.println("Echec ! l'adresse n'a pas été ajoutée");
		}
	}

	public void commander(Connection conn) throws SQLException {

	}

}

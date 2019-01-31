package oracle.crudSGBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.helpersSGBD.DatabaseAccessProperties;
import oracle.helpersSGBD.SQLWarningsExceptions;

public class Connexion {

	private final String configurationFile = "BD.properties";
	private DatabaseAccessProperties dap ;
	private Connection connection ;
	private String jdbcDriver,dbUrl,username,password ;
	
	public Connexion() {
		this.init();
	}
	
	/**
	 * Initialise la connexion a la BD 
	 */
	private void init() {
		try {
			// Load info from BD.properties
			this.dap = new DatabaseAccessProperties(configurationFile);
			this.jdbcDriver = dap.getJdbcDriver();
			this.dbUrl = dap.getDatabaseUrl();
			this.username = dap.getUsername();
			this.password = dap.getPassword();
			// Load the database driver
			Class.forName(this.jdbcDriver);
			// Get a connection to the database
			this.connection = DriverManager.getConnection(dbUrl,username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * (Selection & Projection)
	 * Execute & Retourne la valeur d'une requete passÃ© en parametre
	 * @param query Requete SQL
	 * @return
	 */
	public ResultSet selectQuery(String query) {
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
		return null;
	}
	
	/**
	 * Execute une requete de UPDATE ou DELETE 
	 * @param query Requete SQL
	 */
	public boolean executeQuery(String query) {
		try {
			Statement statement = (Statement) connection.createStatement();
			if(statement.executeQuery(query) != null)
				return true;
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
		return false ;
	}
	
	
	/**
	 * Execute une requete insertion
	 * @param query Requete SQL
	 * @return True si l'insertion est effectuer , false sinon
	 */
	public boolean insertQuery(String query) {
		try {
			Statement statement = (Statement) connection.createStatement();
			if(statement.executeUpdate(query) > 0)
				return true;
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
		return false;
	}
	
	public void commit() {
		try {
			connection.commit();
			System.out.println("Commit réussi.\n");
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
	}
	
	public void rollback() {
		try {
			connection.rollback();
			System.out.println("Rollback réussi.\n");
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
	}
	
	public void setAutoCommit(boolean state) {
		try {
			this.connection.setAutoCommit(state);
			System.out.println("---> AutoCommit est maintenant : "+state);
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
	}
	
	public void setIsolationLevel(int level) {
		try {
			connection.setTransactionIsolation(level);
		} catch (SQLException e) {
			SQLWarningsExceptions.printExceptions(e);
		}
	}
}

package Oracle.crudSGBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Oracle.connexionSGBD.DatabaseAccessProperties;

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
	 * Execute & Retourne la valeur d'une requete passé en parametre
	 * @param query Requete SQL
	 * @return
	 */
	public ResultSet selectQuery(String query) {
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Execute une requete de UPDATE ou DELETE 
	 * @param query Requete SQL
	 */
	public void executeQuery(String query) {
		try {
			Statement statement = (Statement) connection.createStatement();
			statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
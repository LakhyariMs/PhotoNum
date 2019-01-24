package Oracle.connexionSGBD;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionSGBD {

	 private static final String configurationFile = "BD.properties";
	    
	    public static void main( String args[] ) {
	        try {
	        	
	            String jdbcDriver, dbUrl, username, password;
	            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
	            jdbcDriver = dap.getJdbcDriver();
	            dbUrl = dap.getDatabaseUrl();
	            username = dap.getUsername();
	            password = dap.getPassword();
	            // Load the database driver
	            Class.forName(jdbcDriver) ;
	          
	            // Get a connection to the database
	            try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
	            	System.out.println("------------ Connected to the DataBase ------------");
	            	System.out.println("Creating statement");
	            	
	            	Statement stmnt = (Statement) conn.createStatement();
	            	String sql = "Select email from client";
	            	ResultSet rs = stmnt.executeQuery(sql);
	            	
	            	while(rs.next()) {
	            		String email = rs.getString("email");
	            		System.out.println(email);
	            	}
	            	rs.close();
	            }
	        } catch( SQLException se ) {
	            SQLWarningsExceptions.printExceptions(se);
	        } catch( ClassNotFoundException e ) {
	            System.err.println( "Exception: " + e.getMessage()) ;
	        }
	    }
}

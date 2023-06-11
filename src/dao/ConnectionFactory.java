package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	private static final String DATA_SOURCE = "java:jboss/datasources/MySQLDS";
	
	public static Connection getConnection() throws SQLException {
		try {
			
			InitialContext context = new InitialContext();
			DataSource datasource = (DataSource) context.lookup(DATA_SOURCE);
			
			return datasource.getConnection();
			
		} catch (NamingException e) {
			throw new SQLException("Failed to lookup Datasource", e);
		}
	}
	
}

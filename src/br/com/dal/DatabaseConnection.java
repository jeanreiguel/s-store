package br.com.dal;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
	private static DatabaseConnection databaseConnection;
	private Connection connection;
	
	private DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"+
					"loja?user=root&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
					);
					
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Não foi possível conectar com o banco de dados");
		}
	}
	
	public static DatabaseConnection getInstance() {
		if(databaseConnection == null) databaseConnection = new DatabaseConnection();
		
		return databaseConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
}

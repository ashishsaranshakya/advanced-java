package dev.ashish.app.DAOPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database db=new Database();
	private static final String URI="jdbc:mysql://localhost:3306/people";
	private Connection con;
	
	public static Database instance() {
		return db;
	}
	
	private Database() {
		
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void connect() throws SQLException {
		con=DriverManager.getConnection(URI,"root","6210");
	}
	
	public void close() throws SQLException {
		con.close();
	}
}

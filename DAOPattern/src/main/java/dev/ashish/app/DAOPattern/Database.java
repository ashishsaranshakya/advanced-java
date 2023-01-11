package dev.ashish.app.DAOPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	private static Database db=new Database();
	private static String URI;
	private Connection con;
	
	public static Database instance() {
		return db;
	}
	
	private Database() {
		
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void connect(Properties props) throws SQLException {
		String server=props.getProperty("server");
		String port=props.getProperty("port");
		String database=props.getProperty("database");
		String user=props.getProperty("user");
		String password=props.getProperty("password");
		URI=String.format("jdbc:mysql://%s:%s/%s",server,port,database);
		con=DriverManager.getConnection(URI,user,password);
	}
	
	public void close() throws SQLException {
		con.close();
	}
}

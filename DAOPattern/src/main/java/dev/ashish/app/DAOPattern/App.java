package dev.ashish.app.DAOPattern;

import java.sql.SQLException;

public class App{
    public static void main( String[] args ){
    	
    	Database db=Database.instance();
    	
    	try {
    		db.connect();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "Finished" );
    }
}

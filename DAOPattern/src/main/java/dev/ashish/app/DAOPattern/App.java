package dev.ashish.app.DAOPattern;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class App{
    public static void main( String[] args ){
    	
    	Properties props=Profile.getProperties("db");
    	Database db=Database.instance();

    	try {
    		db.connect(props);
    		UserDao userDao=new UserDao();
    		//userDao.save(new User("Rishabh"));
    		var users=userDao.getAll();
    		for(var user:users) {
    			System.out.println(user);
    		}
    		System.out.println(userDao.findById(1));
    		
    		userDao.update(new User(13,"Sam"));
    		
    		users=userDao.getAll();
    		for(var user:users) {
    			System.out.println(user);
    		}
    		System.out.println();
    		
    		userDao.delete(new User(17,null));
    		
    		users=userDao.getAll();
    		for(var user:users) {
    			System.out.println(user);
    		}
    		System.out.println();
    		
    		db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "Finished" );
    }
}

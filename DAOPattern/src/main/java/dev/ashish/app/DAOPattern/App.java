package dev.ashish.app.DAOPattern;

import java.sql.SQLException;

public class App{
    public static void main( String[] args ){
    	
    	Database db=Database.instance();
   
    	try {
    		db.connect();
    		UserDao userDao=new UserDao();
//    		userDao.save(new User("Harshish"));
//    		userDao.save(new User("Sanjan"));
//    		userDao.save(new User("Prakash"));
    		
    		var users=userDao.getAll();
    		for(var user:users) {
    			System.out.println(user);
    		}
    		System.out.println(userDao.findById(1));
    		
    		userDao.update(new User(14,"Sanjana"));
    		
    		users=userDao.getAll();
    		for(var user:users) {
    			System.out.println(user);
    		}
    		System.out.println();
    		
    		userDao.delete(new User(14,"Sanjana"));
    		
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

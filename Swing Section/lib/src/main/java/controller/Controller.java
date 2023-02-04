package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import model.Database;
import model.Profile;
import model.User;
import model.UserDao;
import gui.MainFrame;
import gui.MainPanel;

public class Controller {
	
	private MainFrame mainFrame;
	private MainPanel mainPanel;
	
	public Controller(){
		
		Properties props=Profile.getProperties("db");
    	Database db=Database.instance();
    	try {
    		db.connect(props);
    	}
    	catch(Exception e) {
    		
    	}
    	
    	UserDao userDao=new UserDao();
    	
    	
		mainFrame=new MainFrame();
		mainPanel=new MainPanel();
		mainPanel.setFormListener((u,p)->{
			System.out.println(u+" "+p);
			userDao.save(new User(u,p));
		});
		
		mainFrame.setContentPane(mainPanel);
		
		mainFrame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					db.close();
				}
				catch(Exception ignored) {}
				super.windowClosing(e);
			}
			
		});
		
	}
	
	
}

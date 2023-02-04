package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		super("Swing App");
		setSize(1000,800);
		setJMenuBar(createMenu());
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private JMenuBar createMenu() {
		var menuBar=new JMenuBar();
		var fileMenu=new JMenu("File");
		var exitItem=new JMenuItem("Exit");
		exitItem.addActionListener(e->System.exit(0));
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		return menuBar;
	}
	
}

package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private UserFormListener formListener;
	
	public MainPanel() {
//		setBackground(Color.RED);
		var flabel=new JLabel("Add User");
		flabel.setFont(new Font("Serif",Font.PLAIN,30));
		setLayout(new GridBagLayout());
		
		var gc=new GridBagConstraints();
		gc.gridx=0;
		gc.gridy=0;
		gc.weighty=1;
		add(flabel,gc);
		gc.weighty=1.5;
		gc.gridy++;
		gc.anchor=GridBagConstraints.NORTH;
		
		add(createFormPanel(),gc);
	}
	
	private JPanel createFormPanel() {
		JPanel panel=new JPanel();
		var pad=30;
		var i_border=BorderFactory.createEmptyBorder(pad,pad,pad,pad);
		var o_border=BorderFactory.createEtchedBorder();
		
		panel.setBorder(BorderFactory.createCompoundBorder(o_border,i_border));
		var nlabel=new JLabel("Name: ");
		var plabel=new JLabel("Password: ");
		var nedit=new JTextField(15);
		var pedit=new JTextField(15);
		var btn=new JButton("Save");
		
		var rpad=new Insets(0,0,0,10);
		var zpad=new Insets(0,0,0,0);
		panel.setLayout(new GridBagLayout());
		var gc=new GridBagConstraints();
		gc.gridx=gc.gridy=0;
		
		gc.weighty=0.1;
		gc.gridwidth=1;
		gc.gridy++;
		gc.anchor=GridBagConstraints.LINE_END;
		gc.insets=rpad;
		panel.add(nlabel,gc);
		gc.insets=zpad;
		gc.anchor=GridBagConstraints.LINE_START;
		gc.gridx++;
		panel.add(nedit,gc);
		gc.insets=rpad;
		gc.anchor=GridBagConstraints.LINE_END;
		gc.gridx=0;
		gc.gridy++;
		panel.add(plabel,gc);
		gc.insets=zpad;
		gc.anchor=GridBagConstraints.LINE_START;
		gc.gridx++;
		panel.add(pedit,gc);
		gc.weighty=30;
		gc.gridy++;
		gc.anchor=GridBagConstraints.FIRST_LINE_END;
		panel.add(btn,gc);
		
		btn.addActionListener(e->{
			if(formListener!=null) {
				formListener.formSubmitted(nedit.getText(), pedit.getText());
			}
		});
		
		return panel;
	}
	
	public void setFormListener(UserFormListener formListener) {
		this.formListener=formListener;
	}
	
}

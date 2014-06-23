package pointOfSale;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CategoryGUI extends JPanel implements ActionListener {

	MenuButton jbDine = new MenuButton("Dine In", "Dine", this);
	MenuButton jbGo = new MenuButton("To Go", "ToGo", this);
	MenuButton jbClock = new MenuButton("Clock In", "ClockIn", this);
	MenuButton jbOut = new MenuButton("Log Out", "Out", this);
	boolean admin = false;
	
	public CategoryGUI(boolean admin){
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, new Color(0,100,0)));
		
		GridBagConstraints c = new GridBagConstraints();
		this.admin = admin;
		
		c.gridheight = 1;
		c.weighty = 4.0;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		add(jbDine, c);
		
		c.gridx = 1;
		add(jbGo, c);
		
		c.gridheight = 1;
		c.weighty = 1.2;
		c.gridwidth = 2;
		c.gridx = 0;
		add(jbClock, c);
		add(jbOut, c);
		
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equalsIgnoreCase("Dine")){
			
			SystemInit.setTablePanel(admin);
			
		//	TransactionGUI.setAdminPrivilege(admin);
		//	SystemInit.setTransactionScreen();
		}
		else if(arg0.getActionCommand().equalsIgnoreCase("ClockIn")){
			
			jbClock.setText("Clock Out");
			jbClock.setActionCommand("ClockOut");
			
		}
		else if(arg0.getActionCommand().equalsIgnoreCase("ClockOut")){
			
			jbClock.setText("Clock In");
			jbClock.setActionCommand("ClockIn");
			
		}
		else if(arg0.getActionCommand().equalsIgnoreCase("Out")){
			SystemInit.setLogInScreen();
		}
	}
}

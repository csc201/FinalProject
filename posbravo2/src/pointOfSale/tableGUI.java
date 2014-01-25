package pointOfSale;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class tableGUI extends JPanel implements ActionListener, MouseListener {

	
	boolean admin = false;
	JLabel table1 = new JLabel("<html><b>1</b><br><span style='font: 8px;'>Name: John<br>Seated: 3<br>Server: Charles<br>Status:Progress</span></html>");
	JLabel table2 = new JLabel("2");
	JLabel table3 = new JLabel("3");
	JLabel table4 = new JLabel("4");
	JLabel table5 = new JLabel("5");
	JLabel table6 = new JLabel("6");
	JLabel table7 = new JLabel("7");
	JLabel table8 = new JLabel("8");
	JLabel table9 = new JLabel("9");
	JLabel table10 = new JLabel("10");
	
	MenuButton jblog = new MenuButton("Log Out", "Out", this);
	MenuButton jbBack = new MenuButton("Back", "Back", this);
	MenuButton jbMain = new MenuButton("Main Hall", "Main", this);
	MenuButton jbBar = new MenuButton("Bar", "Bar", this);
	MenuButton jbPatio = new MenuButton("Patio", "Patio", this);
	
	JPanel top = new JPanel();
	JPanel halfbot = new JPanel();
	JPanel mid = new JPanel();
	JPanel bot = new JPanel();
	
	public tableGUI(boolean isAdmin){
		
		this.admin = isAdmin;
		setLayout(new GridLayout(2, 1)); 
		
		
		top.setLayout(new GridLayout(5, 5, 5, 5));
		top.setBackground(new Color(194, 178, 128));
		halfbot.setLayout(new GridLayout(2 , 1));
		mid.setLayout(new GridLayout(1, 3));
		bot.setLayout(new GridLayout(2, 1));
		
		table1.setBackground(Color.cyan);
		table1.setHorizontalAlignment(SwingConstants.CENTER);
		table1.setOpaque(true);
		table1.setBorder(BorderFactory.createLineBorder(Color.black));
		table1.addMouseListener(this);
		
		table2.setHorizontalAlignment(SwingConstants.CENTER);
		table2.setOpaque(true);
		table2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table3.setHorizontalAlignment(SwingConstants.CENTER);
		table3.setOpaque(true);
		table3.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table4.setHorizontalAlignment(SwingConstants.CENTER);
		table4.setOpaque(true);
		table4.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table5.setHorizontalAlignment(SwingConstants.CENTER);
		table5.setOpaque(true);
		table5.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table6.setHorizontalAlignment(SwingConstants.CENTER);
		table6.setOpaque(true);
		table6.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table7.setHorizontalAlignment(SwingConstants.CENTER);
		table7.setOpaque(true);
		table7.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table8.setHorizontalAlignment(SwingConstants.CENTER);
		table8.setOpaque(true);
		table8.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table9.setHorizontalAlignment(SwingConstants.CENTER);
		table9.setOpaque(true);
		table9.setBorder(BorderFactory.createLineBorder(Color.black));
		
		table10.setHorizontalAlignment(SwingConstants.CENTER);
		table10.setOpaque(true);
		table10.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		
		top.add(table1);
		top.add(table2);
		top.add(table3);
		top.add(table4);
		top.add(table5);
		top.add(table6);
		top.add(table7);
		top.add(table8);
		top.add(table9);
		top.add(table10);
		
		mid.add(jbMain);
		mid.add(jbBar);
		mid.add(jbPatio);
		
		bot.add(jbBack);
		bot.add(jblog);
		
		halfbot.add(mid);
		halfbot.add(bot);
		
		
		add(top);
		add(halfbot);
		
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == table1){
		
			String options[] = {"Edit", "Back", "Proceed"};
			int result = JOptionPane.showOptionDialog(this, "<html><span style='font: 20px;'>Name: John<br>Seated: 3<br>Server: Charles<br>Status:Progress</span></html>", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			switch (result){
			case 0:break;
			case 1:break;
			case 2:	TransactionGUI.setAdminPrivilege(admin);
					SystemInit.setTransactionScreen();
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

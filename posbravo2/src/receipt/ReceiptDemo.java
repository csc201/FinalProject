package receipt;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;

/**
 * This class runs the receipt manager application for demonstration in my CSC 201 class as part of my final exam
 * 
 * @author Stephen
 *
 */
public class ReceiptDemo {
	private static String MERCHANT_FILE_PATH = "Files/Receipt/Merchants/";
	private static String MENU_FILE_PATH = "Files/Receipt/Menus/";
	private static String EXTRA_FILE_PATH = "Files/Receipt/Extras/";
	
	private static JFrame demoFrame;
	private static JMenuBar demoMenu;
	private static ReceiptManager receiptManager;
	private static Merchant merchant;
	private static MenuItem[] receiptMenu;
	private static ExtraCost[] receiptExtras;
	private static int menuPool;
	private static int extraPool;
	private static String salesTax;
	private static JDialog dialog;
	private static int dialogChoice;
	
	/**
	 * Runs through the process of generating a Receipting from file and providing it to a receipt manager then displaying the receipt manager.
	 * Allows the user to create randomized (somewhat) receipts from the limited information on file.
	 * @param args
	 */
	public static void main(String[] args) {
		setWindowsLookAndFeel();
		try {
			ObjectInputStream merchantStream = new ObjectInputStream(new FileInputStream(MERCHANT_FILE_PATH + "Good Burger"));
			ObjectInputStream menuStream = new ObjectInputStream(new FileInputStream(MENU_FILE_PATH + "Good Burger"));
			ObjectInputStream extraStream = new ObjectInputStream(new FileInputStream(EXTRA_FILE_PATH + "Good Burger"));
			
			merchant = (Merchant) merchantStream.readObject();
			receiptMenu = (MenuItem[]) menuStream.readObject();
			receiptExtras = (ExtraCost[]) extraStream.readObject();
			
			merchantStream.close();
			menuStream.close();
			extraStream.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Class Not Found Exception", JOptionPane.ERROR_MESSAGE);
		}
		salesTax = "5";
		
		menuPool = 10;
		while(menuPool < receiptMenu.length)
			menuPool *= 10;
		
		extraPool = 10;
		while(extraPool < receiptExtras.length)
			extraPool *= 10;
		
		demoMenu = new JMenuBar();
		demoMenu.add(new JMenu("System"));
		demoMenu.getMenu(0).add(new JMenuItem("Generate Receipt"));
		demoMenu.getMenu(0).add(new JMenuItem("Change Sales Tax"));
		demoMenu.getMenu(0).add(new JMenuItem("Exit"));
		for(int i=0; i < demoMenu.getMenu(0).getItemCount(); i++)
			demoMenu.getMenu(0).getItem(i).addActionListener(new MenuListener());
		
		
		demoFrame = new JFrame();
		demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demoFrame.setSize(300,300);
		demoFrame.setTitle("Receipt Demo Class");
		demoFrame.setJMenuBar(demoMenu);
		demoFrame.setVisible(true);
	}
	/**
	 * Returns a random number which is a valid index for either the MenuItem or ExtraCost array
	 * 
	 * @param pool
	 * @param limit
	 * @return
	 */
	private static int getRandomNumber(int pool, int limit) {
		int random;
		do {
			random = (int)(pool * Math.random());
		} while(random >= limit);
		return random;
	}
	/**
	 * Sets the UI manager to use the Windows LookAndFeel
	 * 
	 */
	private static void setWindowsLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(UnsupportedLookAndFeelException e) {}
		catch(ClassNotFoundException e) {}
		catch(InstantiationException e) {}
		catch(IllegalAccessException e) {}
	}
	/**
	 * Generates a random receipt
	 */
	private static void generateReceipt() {
		if(receiptManager != null)
			receiptManager.setVisible(false);
		

		Receipt receipt = new Receipt(merchant, "Stephen", new BigDecimal(salesTax));
		int items = getRandomNumber(100,50);
		for(int i=0; i < items; i++) {
			MenuItem menuItem = receiptMenu[getRandomNumber(menuPool, receiptMenu.length)];
			if(getRandomNumber(10,10) % 3 == 0)
				menuItem.addExtra(receiptExtras[getRandomNumber(extraPool, receiptExtras.length)]);
			receipt.addItem(menuItem);
		}
		
		receiptManager = new ReceiptManager(merchant, "Stephen", new BigDecimal(salesTax));
		receiptManager.setVisible(true);
	}
	/**
	 * Allows the user to change the sales tax provided to the receipt
	 */
	private static void changeSalesTax() {
		dialog = new JDialog(demoFrame, "Sales Tax", true);
		dialog.setSize(200,100);
		dialog.setResizable(false);
		dialog.setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - dialog.getWidth())/2,
				((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - dialog.getHeight())/2);
		JComboBox<String> taxBox = new JComboBox<String>();
		for(int i=0; i <= 100; i++)
			taxBox.addItem(i+"");
		taxBox.setSelectedIndex(Integer.parseInt(salesTax));
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton button = new JButton("Set");
		button.setActionCommand(0 +"");
		button.addActionListener(new DialogListener());
		buttonPanel.add(button);
		
		button = new JButton("Cancel");
		button.setActionCommand(1 +"");
		button.addActionListener(new DialogListener());
		buttonPanel.add(button);
		
		dialog.add(new JLabel("Select a Tax Rate"), BorderLayout.NORTH);
		dialog.add(taxBox, BorderLayout.CENTER);
		dialog.add(new JLabel("%"), BorderLayout.EAST);
		dialog.add(buttonPanel, BorderLayout.SOUTH);
		
		dialogChoice = -1;
		dialog.setVisible(true);
		
		if(dialogChoice == 0)
			salesTax = taxBox.getItemAt(taxBox.getSelectedIndex());
	}
	/**
	 * Listens for menu commands to the demo classes JMenu
	 * 
	 * @author Stephen
	 *
	 */
	private static class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Exit"))
				System.exit(0);
			else if(e.getActionCommand().equals("Generate Receipt"))
				generateReceipt();
			else if(e.getActionCommand().equals("Change Sales Tax"))
				changeSalesTax();
		}
	}
	/**
	 * Responds to JDialog buttosn by closing the JDialog and setting the dialogChoice integer to the
	 * action command of the button
	 * 
	 * @author Stephen
	 *
	 */
	private static class DialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.setVisible(false);
			dialogChoice = Integer.parseInt(e.getActionCommand());
		}
		
	}
}

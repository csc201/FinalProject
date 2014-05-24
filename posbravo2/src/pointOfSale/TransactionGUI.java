package pointOfSale;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * 
 * @author Stephen Collins, Vanessa Harris, Kolter Bradshaw, Cristhian Ramirez
 * (Date: 4/24/2013) 
 * Purpose: To load the GUI for processing and saving transactions. Upon successful login, 
 * the user is able to select items from each category into a receipt panel and save the receipt
 * by pressing the "Checkout" button. Administrators are given supplemented privileges to access and change
 * system info through a "System" button. 
 *
 */
public class TransactionGUI extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static boolean adminPrivilege;
	
	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel transactionPanel = new JPanel(new GridLayout(3,1));
	private JPanel receiptButtonPanel = new JPanel(new GridLayout(3,3));
	private JPanel transButtonPanel = new JPanel(new GridLayout(6,1));
	private JPanel logoPanel = new JPanel(new GridLayout(1,1));
	private ItemPanel menuPanel = new ItemPanel();
	private JPanel checkoutButtonPanel = new JPanel(new GridLayout(3,1));
	private MenuButton systemButton = new MenuButton("System","System",this);
	private MenuButton processButton = new MenuButton("Process", "Process", this);
	//private MenuButton checkoutButton = new MenuButton("Checkout","Checkout",this);
	private JLabel adminLabel = new JLabel("Admin: ", SwingConstants.RIGHT);
	private ImageIcon logo = new ImageIcon("Files/Icons/logoSmall.jpg");
	private JLabel logoLabel = new JLabel(logo,SwingConstants.CENTER);
	private Border margin = BorderFactory.createMatteBorder(25,25,25,25,DARK_CHAMPAGNE);
	private Border edge = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
										DARK_CHAMPAGNE,DARK_CHAMPAGNE);
	/**
	 * Main constructor which positions components for completing transactions in grid layout.
	 * Allows admin "System" button and label visibility dependent on the 
	 * determined user privilege status
	 */
	TransactionGUI()
	{
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		adminLabel.setFont(new Font(Font.SERIF,Font.BOLD,24));
		adminLabel.setForeground(Color.RED);
		//checkoutButton.setFont(new Font(Font.SERIF,Font.PLAIN,24));
		
		receiptButtonPanel.setBackground(DARK_CHAMPAGNE);
		receiptButtonPanel.add(new MenuButton("Delete Line","Delete Line",this));
		receiptButtonPanel.add(new MenuButton("Delete All","Delete All",this));
		receiptButtonPanel.add(new MenuButton("Log Off","Log Off",this));;	
		
		receiptButtonPanel.add(processButton);
		receiptButtonPanel.add(systemButton);
		receiptButtonPanel.add(new MenuButton("Categories","Categories",this));
		//receiptButtonPanel.add(adminLabel);
		//originally 6
		Tools.addBlankSpace(receiptButtonPanel,3);
		
		//checkoutButtonPanel.setBackground(DARK_CHAMPAGNE);
		//Tools.addBlankSpace(checkoutButtonPanel,2);
		
		logoPanel.setBackground(DARK_GREEN);
		logoLabel.setVerticalAlignment(SwingConstants.CENTER);
		logoLabel.setBorder(BorderFactory.createCompoundBorder(margin,edge));
		logoPanel.add(logoLabel);
		//checkoutButtonPanel.add(checkoutButton);
		
		transButtonPanel.setBackground(DARK_CHAMPAGNE);
		
		transButtonPanel.add(new MenuButton("Issue", "Issue", this));
		transButtonPanel.add(new MenuButton("Sale", "Sale", this));
		transButtonPanel.add(new MenuButton("VoidSale", "VoidSale", this));
		transButtonPanel.add(new MenuButton("Return", "Return", this));
		transButtonPanel.add(new MenuButton("Reload", "Reload", this));
		transButtonPanel.add(new MenuButton("Balance", "Balance", this));
		
		
		transactionPanel.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
		transactionPanel.setBackground(DARK_CHAMPAGNE);
		transactionPanel.add(receiptButtonPanel);
		transactionPanel.add(transButtonPanel);
		transactionPanel.add(logoPanel);
		//transactionPanel.add(checkoutButtonPanel);
		
		halfPanel.add(new ReceiptPanel());
		halfPanel.add(transactionPanel);
		
		if(adminPrivilege)
		{
			adminLabel.setVisible(true);
			systemButton.setVisible(true);
		}
		else
		{
			adminLabel.setVisible(false);
			systemButton.setVisible(false);
		}
		
		add(halfPanel);
		add(menuPanel);
		
	}
	/**
	 * ActionListener for the transaction screens system oriented or receipt panel oriented buttons.
	 */
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Log Off"))
			SystemInit.setLogInScreen();
		else if(event.getActionCommand().equals("Delete All"))
			ReceiptPanel.clearReceipt();
		else if(event.getActionCommand().equals("Delete Line"))
			ReceiptPanel.deleteItem();
		else if(event.getActionCommand().equals("Checkout"))
			ReceiptPanel.saveReceipt();
		//Tanes need to add the print to printer
		else if(event.getActionCommand().equals("System"))
			SystemInit.setAdminScreen();
		else if(event.getActionCommand().equals("Categories"))
			menuPanel.displayCategories();
		else if(event.getActionCommand().equals("Process")) {	
			//Tanes need to add the log file to prevent cheating
			if (!ReceiptPanel.getListModel().isEmpty()) {
				ReceiptPanel.saveReceipt();
			}
	
			SystemInit.setProcessScreen(adminPrivilege);
		}
		else if(event.getActionCommand().equals("Sale"))
		{
			ListModel <String> list = ReceiptPanel.getListModel();
			String total = "";
			for(int count=0; count < list.getSize(); count++)
			{
				String read = list.getElementAt(count);
				Scanner regex = new Scanner(read);
				if (read.toLowerCase().contains("total")) {
				total = regex.findInLine("\\d*\\.\\d{0,2}");
				} 
			}
			String swipe = "0~IPAD100KB|24~98AC18281702140C|1~11|2~|3~758EF3BF11CB565D01897EA56D86621E037A6A18C966FCCE7302E98CC767541A|4~|5~AFB37F8FF324C238163B30D3910C4B6E02A91DC5C8A7258A06DDB61ECD1D3E8914EA75DBAD73A56874E35389E1B0F5DDE3362B6D5858A3BB|6~|7~;6050110000600006320=250100000?|8~|9~00000000|10~010001|11~9500030000081C20014C|12~00002200|";
			String[] temp = new String[]{"","","",""};
			try {
				temp = CardProcess.getEncryption(swipe, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String [] data = new String[] {"Swiped", "Sale", "merchantID2", "000003", "000003", "POS BRAVO v1.0", temp[2], temp[1], total};
			Response res = new Response(8, data);
			
		}
		else if(event.getActionCommand().equals("VoidSale"))
		{
			
		}
		else if(event.getActionCommand().equals("Issue"))
		{
			
		}
		else if(event.getActionCommand().equals("Balance"))
		{
			
		}
		else if(event.getActionCommand().equals("Return"))
		{
			
		}
		else if(event.getActionCommand().equals("Reload"))
		{
			
		}
		    CardPanel.reset();
	}
	/**
	 * Setter for the adminPrivilege variable 
	 * @param newAdminPrivilege desired new boolean value for adminPrivelege
	 */
	public static void setAdminPrivilege(boolean newAdminPrivilege)
	{
		adminPrivilege = newAdminPrivilege;
	}
}

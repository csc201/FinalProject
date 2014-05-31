package pointOfSale;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowStateListener;
import java.util.Scanner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
public class TransactionGUI3 extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static boolean adminPrivilege;
	
	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel wrapperPanel = new JPanel(new GridLayout(1, 1));
	private JPanel transactionPanel = new JPanel(new GridBagLayout());
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel transButtonPanel = new JPanel(new GridLayout(7,1));
	
	private JPanel receiptButtonPanel = new JPanel(new GridLayout(2,3));
	
	private JPanel markerPanel = new JPanel(new GridLayout(2, 1));
	private JPanel markerButtonPanel = new JPanel(new GridLayout(2, 2));
	private JPanel markerTop = new JPanel(new GridLayout(2,1));
	private JPanel markerDisplay = new JPanel(new GridLayout(2,2));
	private JPanel voidPanel = new JPanel(new GridLayout(1, 2));
	private JPanel returnPanel = new JPanel(new GridLayout(1, 2));
	//private JPanel markerTop = new JPanel(new GridLayout(1,2));
	//private JPanel markerBot = new JPanel(new GridLayout(1,3));
	private JLabel tagLabel = new JLabel();
	private JLabel priceLabel = new JLabel();
	private JLabel voidLabel = new JLabel();
	private JLabel returnLabel = new JLabel();

	private JLabel voidIcon = new JLabel("No");
	private JLabel returnIcon = new JLabel("No");
	
	private JPanel logoPanel = new JPanel(new GridLayout(1,1));
	private ItemPanel menuPanel = new ItemPanel();
	private JPanel checkoutButtonPanel = new JPanel(new GridLayout(3,1));
	private JLabel adminLabel = new JLabel("Admin: ", SwingConstants.RIGHT);
	private JLabel creditLabel = new JLabel("Credit", SwingConstants.CENTER);
	private JLabel giftLabel = new JLabel("Gift", SwingConstants.CENTER);
	private JLabel prevLabel = new JLabel("Previous", SwingConstants.CENTER);
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
	TransactionGUI3()
	{
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		adminLabel.setFont(new Font(Font.SERIF,Font.BOLD,24));
		adminLabel.setForeground(Color.RED);
		
		checkoutButtonPanel.setBackground(DARK_CHAMPAGNE);
		Tools.addBlankSpace(checkoutButtonPanel,2);
		
		logoPanel.setBackground(DARK_GREEN);
		logoLabel.setVerticalAlignment(SwingConstants.CENTER);
		logoLabel.setBorder(BorderFactory.createCompoundBorder(margin,edge));
		//logoPanel.add(adminLabel);
		logoPanel.add(logoLabel);
		
		c.weightx = 1.0;
		c.weighty = 2.0;
		c.fill = GridBagConstraints.BOTH;
		transactionPanel.setBackground(DARK_CHAMPAGNE);
		transactionPanel.add(new MenuButton("System","System",this), c);
		transactionPanel.add(new MenuButton("Process", "Process", this), c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		transactionPanel.add(new MenuButton("Log Off","Log Off",this), c);
		
		creditLabel.setVerticalAlignment(SwingConstants.CENTER);
		creditLabel.setFont(new Font(Font.SERIF,Font.BOLD,24));
		
		giftLabel.setVerticalAlignment(SwingConstants.CENTER);
		giftLabel.setFont(new Font(Font.SERIF,Font.BOLD,24));
		
		c.weighty = 1.0;
		transactionPanel.add(creditLabel, c);

		c.weighty = 2.0;
		transactionPanel.add(new MenuButton("Sale", "creditSale", this), c);

		c.weighty = 1.0;
		transactionPanel.add(giftLabel, c);

		c.weighty = 2.0;
		transactionPanel.add(new MenuButton("Issue", "Issue", this), c);
		transactionPanel.add(new MenuButton("Sale", "giftSale", this), c);
		//transButtonPanel.add(new MenuButton("VoidSale", "VoidSale", this));
		//transButtonPanel.add(new MenuButton("Return", "Return", this));
		transactionPanel.add(new MenuButton("Reload", "Reload", this), c);
		transactionPanel.add(new MenuButton("Balance", "Balance", this), c);
		
		
		//markerDisplay.add(markerTop);
		//markerDisplay.add(markerBot);
		
		tagLabel.setText("Chilli Cheese Nachos");
		tagLabel.setBackground(Color.WHITE);
		
		priceLabel.setText("$13.54");
		priceLabel.setBackground(Color.WHITE);
		
		voidLabel.setText("Void");
		voidLabel.setBackground(Color.WHITE);
		
		returnLabel.setText("Note");
		returnLabel.setBackground(Color.WHITE);
		
		voidIcon.setBackground(Color.WHITE);

		returnIcon.setBackground(Color.WHITE);
		
		voidPanel.setBackground(Color.WHITE);

		returnPanel.setBackground(Color.WHITE);
		
		markerDisplay.setBackground(Color.WHITE);
		
		tagLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		priceLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));		
		
		c.weighty = 1.0;
		markerDisplay.add(tagLabel);
		markerDisplay.add(priceLabel);
		
		voidLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		returnLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		
		voidIcon.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		

		c.weighty = 1.0;
		voidPanel.add(voidLabel);
		voidPanel.add(voidIcon);
		returnPanel.add(returnLabel);
		returnPanel.add(returnIcon);
		
		markerDisplay.add(voidPanel);
		markerDisplay.add(returnPanel);
		
		
		prevLabel.setVerticalAlignment(SwingConstants.CENTER);
		prevLabel.setFont(new Font(Font.SERIF,Font.BOLD,24));
		
		transactionPanel.add(prevLabel, c);
		
		c.weighty = 2.0;
		transactionPanel.add(new MenuButton("Adjust", "Adjust", this), c);
		
		c.weighty = 1.0;
		transactionPanel.add(markerDisplay, c);
		
		c.weighty = 2.0;
		transactionPanel.add(new MenuButton("Void", "Void", this), c);
		transactionPanel.add(new MenuButton("Note", "Note", this), c);

		//markerTop.setBackground(DARK_CHAMPAGNE);
		//markerPanel.setBackground(DARK_CHAMPAGNE);
		
		//markerPanel.add(markerTop);
		//markerPanel.add(markerButtonPanel);
		
		
		transactionPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		transactionPanel.setBackground(DARK_CHAMPAGNE);
		//transactionPanel.add(receiptButtonPanel);
		//transactionPanel.add(receiptButtonPanel);
		//transactionPanel.add(transButtonPanel);
		//transactionPanel.add(markerPanel);
		//transactionPanel.add(logoPanel);
		//transactionPanel.add(checkoutButtonPanel);
		
		wrapperPanel.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
		wrapperPanel.add(transactionPanel);
		
		halfPanel.add(new ReceiptPanel());
		halfPanel.add(wrapperPanel);
		
		if(adminPrivilege)
		{
			adminLabel.setVisible(true);
		}
		else
		{
			adminLabel.setVisible(false);
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
		
		
		else if(event.getActionCommand().equals("creditSale"))
		{
			String total = getTotal();
			popup("Please Swipe....", this);
			

			
		}
		else if(event.getActionCommand().equals("giftSale"))
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
	public static String getTotal(){
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
		return total;
	}
	public static void popup(String message, JPanel parent){
		final JWindow screen = new JWindow();
		JPanel disabler = new JPanel(new GridLayout(1,1));
		screen.setSize(parent.getParent().getSize());
		disabler.setBackground(Color.BLACK);
		screen.setContentPane(disabler);
		screen.setOpacity(0.5f);
		screen.setVisible(true);
		
		
        final JWindow popup = new JWindow(screen);
        JPanel contentPane = new JPanel();
        final JTextField input = new JTextField();
        
        MenuButton option = new MenuButton("CANCEL", "CANCEL", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub;
				screen.dispose();
				popup.dispose();
			}
        	
        });
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridwidth = GridBagConstraints.REMAINDER;
        con.weightx = 1.0;
        con.weighty = 2.0;
        con.fill = GridBagConstraints.BOTH;
        contentPane.add(new JLabel(message, SwingConstants.CENTER), con);
        con.weighty = 1.0;
        contentPane.add(input, con);
        con.insets = new Insets(20, 10, 10, 10);
        con.weighty = 2.0;
        contentPane.add(option, con);
        contentPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        contentPane.setBackground(DARK_CHAMPAGNE);
        
        popup.addKeyListener(new KeyAdapter(){
    		public void keyTyped(KeyEvent e) {
    			input.setText(input.getText() + e.getKeyChar());
    			System.out.print(e.getKeyChar());
    			Tools.update(input);
    		}
        });
        popup.setFocusable(true);
        popup.setContentPane(contentPane);
        popup.setSize(300, 200);
        popup.setBackground(DARK_CHAMPAGNE);
        int x = parent.getParent().getWidth()/2-150;
        int y = parent.getParent().getHeight()/2-100;
        popup.setLocation(x, y);
        popup.setVisible(true);
        popup.requestFocus();
       System.out.print(popup.isFocusable() + " | " + popup.isFocused() + " | " + popup.isFocusOwner());
       
	}
}	

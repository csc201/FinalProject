package pointOfSale;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * 
 * @author Stephen Collins, Vanessa Harris, Kolter Bradshaw, Cristhian Ramirez
 * (Date: 4/24/2013) 
 * Purpose: A component of the LogInGUI class.  Allows the user to enter a password up to 6 characters long
 * while masking the password entered.  Evaluates the password and if it is valid, transfers the user to the
 * transaction screen.  If the password is valid, evaluates whether it is an administrator password and, if it is,
 * passes a boolean value to the TransactionGUI class which allows access to administrator privileges.  If the
 * password is not valid, the user is informed through a JOptionPane.
 *
 */
public class KeyPad extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String SECURITY_FILE = "Files/System/SecurityCodes";
	
	private JPanel buttonRow1 = new JPanel(new GridLayout(1,4));
	private JPanel buttonRow2 = new JPanel(new GridLayout(1,4));
	private JPanel buttonRow3 = new JPanel(new GridLayout(1,4));
	private JPanel buttonRow4 = new JPanel(new GridLayout(1,4));

	private JTextField numberField = new JTextField("",11);
	private String numberCode = "";
	private boolean validCode = false;
	private boolean adminPrivilege = false;
	
	//private static int timeout;
	//private static String tranType, frequency, partialAuth, merchantID, password;
	
	/**
	 * Constructs a panel containing a grid of buttons used to enter a password
	 */
	KeyPad()
	{
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setLayout(new GridLayout(6,1));
		setBackground(DARK_CHAMPAGNE);
		
		numberField.setEditable(false);
		numberField.setBackground(Color.WHITE);
		numberField.setFont(new Font(Font.SERIF,Font.PLAIN,18));
		
		buttonRow1.add(new MenuButton("0","0",this));
		buttonRow1.add(new MenuButton("1","1",this));
		buttonRow1.add(new MenuButton("2","2",this));
		buttonRow1.add(new MenuButton("3","3",this));
		
		buttonRow2.add(new MenuButton("4","4",this));
		buttonRow2.add(new MenuButton("5","5",this));
		buttonRow2.add(new MenuButton("6","6",this));
		buttonRow2.add(new MenuButton("7","7",this));
		
		buttonRow3.add(new MenuButton("8","8",this));
		buttonRow3.add(new MenuButton("9","9",this));
		buttonRow3.add(new MenuButton("Clear","10",this));
		buttonRow3.add(new MenuButton("Fast Food","11",this));
		
		buttonRow4.add(new MenuButton("To Go","12",this));
		buttonRow4.add(new MenuButton("Deli","13",this));
		buttonRow4.add(new MenuButton("Dine In","14",this));
		buttonRow4.add(new MenuButton("Back Office","15",this));
		
		
		add(numberField);
		Tools.addBlankSpace(this,1);
		add(buttonRow1);
		add(buttonRow2);
		add(buttonRow3);
		add(buttonRow4);
	}
	/**
	 * Action listener assigned to the keypad.  Creates a password reflecting the user's entries, clears
	 * the password or calls checkCode() to evaluate the password.
	 */
	public void actionPerformed(ActionEvent event)
	{
		if(Integer.parseInt(event.getActionCommand()) < 10)
		{
			if(numberCode.length() < 6)
			{
				numberCode = numberCode + event.getActionCommand();
				numberField.setText(numberField.getText() + "*");
			}
		}
		else if (event.getActionCommand().equals("10"))
		{
			numberCode = "";
			numberField.setText("");
		}
		else if (event.getActionCommand().equals("11"))
			checkCode("11");
		else if (event.getActionCommand().equals("12"))
			checkCode("12");
		else if (event.getActionCommand().equals("13"))
			checkCode("13");
		else if (event.getActionCommand().equals("14"))
			checkCode("14");
		else if (event.getActionCommand().equals("15"))
			checkCode("15");
	}
	/**
	 * Private helper method used to check the user entered password against a text file list of
	 * valid passwords.  If the password checks out, the user is transfered to the transaction screen
	 * along with the appropriate access privilege (server or admin)
	 */
	private void checkCode(String code)
	{
		Scanner inputStream = null;
		try
		{
			//DB
			inputStream = new Scanner(new File(SECURITY_FILE));
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"ERROR: File Not Found");
		}
			
		while(inputStream.hasNextLine() && !validCode)
		{
			String line = inputStream.nextLine();
				
			if(line.equals(""))
				;
			else
			{
				validCode = numberCode.equals(line.substring(0,6));
				adminPrivilege = line.substring(7,8).equals("A");
			}
		}
		
		if(validCode)
		{
			//initProperties();
			switch(code){
			case "11":TransactionGUI.setAdminPrivilege(adminPrivilege);
						SystemInit.setTransactionScreen();
						break;
			case "12": //To Go: DB for existing customers 
				
				break;
			case "13": //Delievery: DB for existing customers
				break;
			case "14": SystemInit.setTablePanel(adminPrivilege); //Dine In
			//David needs to integrate 	
				break;
			case "15": SystemInit.setCategoryPanel(adminPrivilege); //BackOffice
				break;
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"ERROR: Invalid Security Code");
			numberCode = "";
			numberField.setText("");
		}
	}
	/*
	private void initProperties() {
		Properties properties = new Properties();
		try {
			File file = new File("Files/Properties/MercuryMerchantIDDev.properties");
			FileInputStream fileReader  = new FileInputStream(file);
			properties.load(fileReader);
			fileReader.close();
			fileReader = null;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String id = properties.getProperty("idType");
			if(id.equals("Encrypted")){
				id = "merchantID2";
			}
			else if(id.equals("Plain")){
				id = "merchantID1";
			}
			
			this.merchantID = properties.getProperty(id);
			
			String temp = id.substring(id.length()-1);
			temp = "password" + temp;
			this.password = properties.getProperty(temp);
			
			this.tranType = properties.getProperty("tranType");
			this.frequency = properties.getProperty("frequency");
			this.partialAuth = properties.getProperty("partialAuth");
			this.timeout = Integer.parseInt(properties.getProperty("timeout"));
		
		
	/*
		finally{
			try{
				fileReader.close();fileReader = null;
			}
			catch(IOException e){
				e.printStackTrace();}
		}*/

	 //}	
			//InputStream fileReader2 = getClass().getResourceAsStream("/Files/Properties/MercuryMerchantIDDev.properties");
}

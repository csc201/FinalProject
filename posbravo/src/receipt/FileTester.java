package receipt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Test texts files written to binary files for the purpose of providing a library for the ReceiptDemo class
 * 
 * @author Stephen
 *
 */
public class FileTester {
	private static String MERCHANT_FILE_PATH = "Files/Receipt/Merchants/";
	private static String MENU_FILE_PATH = "Files/Receipt/Menus/";
	private static String EXTRA_FILE_PATH = "Files/Receipt/Extras/";
	
	/**
	 * Runs through the process of reading serialized Merchant, MenuItem and Extracost objects and prints their to toString values to verify they
	 * were saved correctly.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Merchant merchant = null;
		MenuItem[] itemArray = null;
		ExtraCost[] extraArray = null;
		
		try {
			ObjectInputStream merchantStream = new ObjectInputStream(new FileInputStream(MERCHANT_FILE_PATH + "Good Burger"));
			ObjectInputStream menuStream = new ObjectInputStream(new FileInputStream(MENU_FILE_PATH + "Good Burger"));
			ObjectInputStream extraStream = new ObjectInputStream(new FileInputStream(EXTRA_FILE_PATH + "Good Burger"));
			
			merchant = (Merchant) merchantStream.readObject();
			itemArray = (MenuItem[]) menuStream.readObject();
			extraArray = (ExtraCost[]) extraStream.readObject();
			
			merchantStream.close();
			menuStream.close();
			extraStream.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		catch(ClassNotFoundException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		System.out.println(merchant.toString());
		for(MenuItem item : itemArray)
			System.out.println(item.toString());
		for(ExtraCost extra : extraArray)
			System.out.println(extra.toString());
	}
}

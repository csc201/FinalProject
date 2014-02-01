package receipt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Converts simple text files to serialized Merchant, MenuItem and ExtraCost objects for the purpose of providing a library for the ReceiptDemo class.
 * 
 * @author Stephen
 *
 */
public class FileWriter {
	private static String MERCHANT_FILE_PATH = "Files/Receipt/Merchants/";
	private static String MENU_FILE_PATH = "Files/Receipt/Menus/";
	private static String EXTRA_FILE_PATH = "Files/Receipt/Extras/";
	
	/**
	 * Runs through the process of reading simple text files, creating Merchant, MenuItem and ExtraCost objects then saving them in serialized form.
	 * @param args
	 */
	public static void main(String[] args) {
		Merchant merchant = new Merchant("Good Burger","Fast Food", "703-323-3000", 
				new Address("8333 Little River Turnpike","","Annandale",Address.State.VA,"22003"));
		ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
		ArrayList<ExtraCost> extraList = new ArrayList<ExtraCost>();
		
		Scanner menuInput = null;
		Scanner extraInput = null;
		
		try {
			menuInput = new Scanner(new File(MENU_FILE_PATH + "FastFoodText"));
			extraInput = new Scanner(new File(EXTRA_FILE_PATH + "FastFoodText"));
		}
		catch(FileNotFoundException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		int loop = 0;
		while(menuInput.hasNextLine()) {
			MenuItem item = new MenuItem(menuInput.next(), menuInput.next());
			menuList.add(item);
			menuInput.nextLine();
			System.out.println("Menu " + loop);
			loop++;
		}
		loop=0;
		while(extraInput.hasNextLine()) {
			ExtraCost cost = new ExtraCost(extraInput.next(), extraInput.next());
			extraList.add(cost);
			if(extraInput.hasNextLine())
				extraInput.nextLine();
			System.out.println("Extra " + loop);
			loop++;
		}
		menuInput.close();
		extraInput.close();
		
		try {
			ObjectOutputStream merchantStream = new ObjectOutputStream(new FileOutputStream(MERCHANT_FILE_PATH + "Good Burger"));
			ObjectOutputStream menuStream = new ObjectOutputStream(new FileOutputStream(MENU_FILE_PATH + "Good Burger"));
			ObjectOutputStream extraStream = new ObjectOutputStream(new FileOutputStream(EXTRA_FILE_PATH + "Good Burger"));
			
			merchantStream.writeObject(merchant);
			menuStream.writeObject(menuList.toArray(new MenuItem[menuList.size()]));
			extraStream.writeObject(extraList.toArray(new ExtraCost[extraList.size()]));
			
			merchantStream.close();
			menuStream.close();
			extraStream.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}

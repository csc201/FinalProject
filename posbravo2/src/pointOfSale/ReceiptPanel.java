package pointOfSale;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import receipt.*;
import receipt.MenuItem;
/**
 * 
 * @author Stephen Collins, Vanessa Harris, Kolter Bradshaw, Cristhian Ramirez
 * (Date: 4/24/2013) 
 * Purpose: Creates a panel which processes, saves and loads the receipts 
 * created through TransactionGUI. 
 *
 */
public class ReceiptPanel extends JPanel
{
	private static final String TEMP_CASHIER = "CASHIER";
	private static final Merchant TEMP_MERCHANT = new Merchant("La dolce Vita", "Restaurant"," 703-323-3000",
			new Address("8333 Little River Turnpike","","Annandale",Address.State.VA,"22003"));
	private static final long serialVersionUID = 1L;
	private static final String RECEIPT_PATH = "Files/Receipt/";
	private static final String RECEIPT_LIST_FILE = RECEIPT_PATH + "ReceiptList";
	private static final String RECEIPT_LOG_FILE = RECEIPT_PATH + "ReceiptLog";
	private static final String TAX_FILE = "Files/Tax/SalesTax";
	
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private static JList<String> receiptList = new JList<String>(listModel);
	
	private static String newReceipt = "";
	private static long subtotalAmount = 0;
	private static long taxAmount = 0;
	private static long tipAmount = 0;
	private static long totalAmount = 0;
	private static double salesTax=0;
	private static String transaction = "";

	private static ReceiptManager receiptManager = new ReceiptManager(TEMP_MERCHANT, TEMP_CASHIER, BigDecimal.valueOf(salesTax));
	
	
	
//<<<<<<< HEAD
	public static String getNewReceipt() {
		return newReceipt;
	}
//=======
	private static ReceiptLog receiptLog = new ReceiptLog();
//>>>>>>> origin/test4
	
	public static JList<String> getReceiptList() {
		return receiptList;
	}

	public static DefaultListModel<String> getListModel() {
		return listModel;
	}
	/**
	 * Constructs the ReceiptPanel.  Sets the initial conditions of the panel and the receiptList JList object.
	 * Adds a JScrollPane containing receiptList to this JPanel.
	 */
	ReceiptPanel()
	{
		setLayout(new GridLayout(1,1));
		readTax();
		
		receiptManager = new ReceiptManager(TEMP_MERCHANT, TEMP_CASHIER, BigDecimal.valueOf(salesTax));
		
		add(receiptManager);
	}
	/**
	 * Static method called by the ItemPanel class to add an item to the receiptList.
	 * Also adjusts the subtotals/totals to match the new receiptList elements.
	 * @param itemPrice (String) Price of user selected item
	 * @param itemName (String) Name of user selected item
	 */
	public static void addItem(String itemPrice, String itemName)
	{
		//long itemPricelong=Long.parseLong(itemPrice);
		itemPrice = itemPrice.substring(0, itemPrice.length()-2) +"." 
				+ itemPrice.substring(itemPrice.length()-2, itemPrice.length());
		MenuItem item = new MenuItem(itemName, itemPrice);
		//totalAmount=totalAmount+itemPricelong;
		
		
		receiptManager.addItem(item);
	}
	private static long updateTotals(long totalAmount)
	{
		taxAmount = Math.round(subtotalAmount * salesTax / 100.0);
		totalAmount = subtotalAmount + taxAmount;
		return totalAmount;
		
	}
	/**
	 * Called by the Delete button in the CheckOutPanel class to remove an item from the receiptList.
	 * Also adjusts the subtotals/totals to match the new receiptList elements.
	 */
	public static void deleteItem()
	{
//<<<<<<< HEAD
		if(receiptList.getSelectedIndex() < listModel.getSize()-4 && receiptList.getSelectedIndex() > -1)
		{
			
			
			String value = receiptList.getSelectedValue();
			int countLoop = 0;
			int count1 = 0;
			while(countLoop < listModel.getSize()){
				if(listModel.get(countLoop).equals(value)){
					count1++;
				}
				countLoop++;
			}
			
			countLoop = 0;
			int count2 = 0;
			while(countLoop < listModel.getSize()){
				if(listModel.get(countLoop).equals("-"+value)){
					count2++;
				}
				countLoop++;
			}
			
			
			if(++count2 <= count1){	
				if(!value.contains("-")){
					listModel.add(receiptList.getSelectedIndex()+1, "-"+receiptList.getSelectedValue());
					String itemPrice = receiptList.getSelectedValue().substring(0,
									receiptList.getSelectedValue().indexOf(" "));
					for (int x = 0; x < listModel.getSize(); x++) {
						if (listModel.get(x).contains("Subtotal")) {
							String subtotalString = listModel.get(x).substring(
									0, listModel.get(x).indexOf(" "));
							subtotalAmount = Tools.toAmount(subtotalString);
						}
					}
					subtotalAmount = subtotalAmount - Tools.toAmount(itemPrice);
					updateTotals(subtotalAmount);
				}
			}
			
			receiptList.clearSelection();
			
			if(listModel.getSize() == 5)
				clearReceipt();
			else
			{
				for(int count=0; count < 4; count++)
					listModel.removeElementAt(listModel.getSize()-1);
				//listModel.addElement(" ");
				listModel.addElement(Tools.toMoney(subtotalAmount) + manualTab(Tools.toMoney(subtotalAmount)) + "Subtotal");
				listModel.addElement(Tools.toMoney(taxAmount) + manualTab(Tools.toMoney(taxAmount)) + "Tax");
				listModel.addElement(Tools.toMoney(tipAmount) + manualTab(Tools.toMoney(tipAmount)) + "Tip");
				listModel.addElement(Tools.toMoney(totalAmount) + manualTab(Tools.toMoney(totalAmount)) + "Total");
			}
		}
//=======
		receiptManager.deleteSelectedItem();
//>>>>>>> origin/test4
	}
	/**
	 * Removes all elements from the receiptList and resets the total price to zero.
	 */
	public static void clearReceipt()
	{
		receiptManager.clearReceipt();
	}
	/**
	 * Saves the receiptList to text file before clearing it
	 */
	public static void saveReceipt()
	{
		
		//Write to text file by using timestamp as file name
		PrintWriter listWriter = null;
		PrintWriter contentWriter = null;
		newReceipt = getTimeStamp();
		try
		{
			listWriter = new PrintWriter(new FileOutputStream(RECEIPT_LIST_FILE, true));
			contentWriter = new PrintWriter(RECEIPT_PATH + newReceipt);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		
		contentWriter.println("OPEN");
		listWriter.println(newReceipt);
		for(int count=0; count < listModel.getSize(); count++)
			contentWriter.println(listModel.elementAt(count));
		
		listWriter.close();
		contentWriter.close();
		clearReceipt();
		
		//write to ReceiptLog as an object file
		ObjectOutputStream outputStream;
		try {
			ArrayList<Receipt> receiptList = receiptManager.getList();
			for(Receipt receipt : receiptList)
				receiptLog.addReceipt(receipt);
			clearReceipt();
			
			outputStream = new ObjectOutputStream(new FileOutputStream(RECEIPT_LOG_FILE));
			outputStream.writeObject(receiptLog);
			outputStream.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
		}

	}
	public static void saveReceipt(String name)
	{
		PrintWriter listWriter = null;
		PrintWriter contentWriter = null;
		newReceipt = getTimeStamp();
		if(name != null && (!name.equals(""))){
			newReceipt = name;
		}
		try
		{
			listWriter = new PrintWriter(new FileOutputStream(RECEIPT_LIST_FILE, true));
			contentWriter = new PrintWriter(RECEIPT_PATH + newReceipt);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		
		contentWriter.println("OPEN");
		listWriter.println(newReceipt);
		for(int count=0; count < listModel.getSize(); count++)
			contentWriter.println(listModel.elementAt(count));
		
		listWriter.close();
		contentWriter.close();
		clearReceipt();
	}
	public static void saveReceiptReturn(String time, String item)
	{

		PrintWriter listWriter = null;
		PrintWriter contentWriter = null;
		newReceipt = time;
		
		try
		{
			listWriter = new PrintWriter(new FileOutputStream(RECEIPT_LIST_FILE, true));
			contentWriter = new PrintWriter(RECEIPT_PATH + newReceipt);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		
		contentWriter.println("RETURN");
		listWriter.println(newReceipt);
		contentWriter.println(item);	
		for(int count=listModel.getSize()-5; count < listModel.getSize(); count++)
			contentWriter.println(listModel.elementAt(count));
		
		listWriter.close();
		contentWriter.close();
		clearReceipt();
	}

	public static void saveReceiptReturn(String time)
	{

		PrintWriter listWriter = null;
		PrintWriter contentWriter = null;
		newReceipt = time;
		
		try
		{
			listWriter = new PrintWriter(new FileOutputStream(RECEIPT_LIST_FILE, true));
			contentWriter = new PrintWriter(RECEIPT_PATH + newReceipt);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		
		contentWriter.println("RETURN");
		listWriter.println(newReceipt);
		for(int count=1; count < listModel.getSize(); count++){
				contentWriter.println(listModel.elementAt(count));
			
}
		
		listWriter.close();
		contentWriter.close();
		clearReceipt();
	}

	/**
	 * Loads items of the selected receipt from text file back into receipt list
	 * @param receiptFile Text file to be loaded
	 **/
		public static void loadReceipt(String receiptFile)
		{
			Scanner inputStream = null;
			try
			{
				inputStream = new Scanner(new File(RECEIPT_PATH + receiptFile));
			}
			catch(FileNotFoundException e)
			{
				try{
					inputStream = new Scanner(new File(receiptFile));
					transaction = receiptFile;
				} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null,"File Not Found");
				}
			}
			clearReceipt();
			while(inputStream.hasNextLine())
				listModel.addElement(inputStream.nextLine());
			inputStream.close();
			
		
		}
	 /*
	public static void loadReceipt(String receiptFile)
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(RECEIPT_PATH + receiptFile));
		}
		catch(FileNotFoundException e)
		{
			try{
				inputStream = new Scanner(new File(receiptFile));
				transaction = receiptFile;
			} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"File Not Found");
			}
		}
		clearReceipt();
		while(inputStream.hasNextLine())
			listModel.addElement(inputStream.nextLine());
		inputStream.close();
		
	
	}*/
	/**
	 * JLists do not recognize the tab character, so this inserts a manual tab that, while not perfect,
	 * gets the job done.
	 * @param entry First character, which the "tab" will follow
	 * @return A variable number of blank spaces to act as a tab
	 */
	protected static String manualTab(String entry)
	{
		String tab = "";
		for(int count=0; count < 15 - entry.length(); count++){
			tab += " ";
		}
		return tab;
	}
	/**
	 * Method to retrieve the date and time of checkout for each receipt
	 * @return returns the current time in String format
	 */
	public static String getTimeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Private helper method which reads the local sales tax amount 
	 * from a text file and sets a double value equal to it.
	 */
	private static void readTax()
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(TAX_FILE));
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		
		salesTax = Double.parseDouble(inputStream.next());
		inputStream.close();
		
		receiptManager.setSalesTax(BigDecimal.valueOf(salesTax).divide(new BigDecimal(100)));
	}
	
	public static String getTransaction() {
		return transaction;
	}
}

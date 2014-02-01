package pointOfSale;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Stephen Collins, Vanessa Harris, Kolter Bradshaw, Cristhian Ramirez
 * (Date: 4/24/2013) 
 * Purpose: Reads text files generated from the transaction screen which contain saved transactions (receipts).
 * These saved receipts are displayed on the ReceiptPanel object which is a component of the AdminGUI class.
 * This class is a component of the AdminGUI class.
 *
 */
public class ReceiptLoader extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;  //Added to satisfy the compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String RECEIPT_PATH = "Files/Receipts/";
	private static final String RECEIPT_LIST = RECEIPT_PATH + "ReceiptList";
	
	private JPanel upperPanel = new JPanel(new GridLayout(3,1));
	private JPanel buttonPanel = new JPanel(new GridLayout(1,3));
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> receiptList = new JList<String>(listModel);
	private JLabel titleLabel = new JLabel("Load Saved Receipts", SwingConstants.CENTER);
	private JLabel listLabel = new JLabel("Select Receipt from list below", SwingConstants.LEFT);
	private String firstline;
	private static String[] ext;
	/**
	 * Arranges all components in this class onto a JPanel and reads all saved receipt text files, displaying
	 * the text files names in a JList
	 */
	ReceiptLoader()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		readReceipts();
		receiptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		listLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		listLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 18));
		
		buttonPanel.setBackground(DARK_CHAMPAGNE);
		buttonPanel.add(new MenuButton("Load","Load",this));
		//buttonPanel.add(new MenuButton("Delete","Delete",this));
		buttonPanel.add(new MenuButton("Void", "Void", this));
		buttonPanel.add(new MenuButton("Adjust", "Adjust", this));
		//buttonPanel.add(new MenuButton("Delete All","Delete All",this));
		//buttonPanel.add(new MenuButton("Return","Return",this));
		
		upperPanel.setBackground(DARK_CHAMPAGNE);
		upperPanel.add(titleLabel);
		upperPanel.add(buttonPanel);
		upperPanel.add(listLabel);
		
		add(upperPanel);
		add(new JScrollPane(receiptList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
	}
	/**
	 * Responds to user input by loading or deleting saved receipt text files
	 */
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Load") && receiptList.getSelectedIndex() > -1)
			ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
		if(event.getActionCommand().equals("Delete") && receiptList.getSelectedIndex() > -1){
			//JList<String> receiptPanelList = ReceiptPanel.getReceiptList();
			//if(receiptPanelList.isSelectionEmpty()){
			Scanner read = null;
			try {
				read = new Scanner(new File("Files/Receipts/" + receiptList.getSelectedValue()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String type = read.nextLine();
			if(type.contains("OPEN") || type.contains("PROGRESS")){
				deleteReceipt();
			}
			
			/*}
			else{
				ReceiptPanel.deleteItem();
			}*/
		}
		if(event.getActionCommand().equals("Delete All"))
			deleteAll();
		if(event.getActionCommand().equalsIgnoreCase("Return")){
			
			Scanner read = null;
				try {
					//DB look up Invoice table by receipt number
					read = new Scanner(new File("Files/Receipts/" + receiptList.getSelectedValue()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ext = ResponseExtract.getData(receiptList.getSelectedValue(), "Return");
				
			
				String type = read.nextLine();
				if(type.contains("SWIPED")){
					JList<String> receiptPanelList = ReceiptPanel.getReceiptList();
					ListModel<String> listmodel = ReceiptPanel.getListModel();
					String amount = "0";
					for(int x = 0; x < listmodel.getSize(); x++){
						if(listmodel.getElementAt(x).contains("Total")){
							Scanner ext = new Scanner(listmodel.getElementAt(x));
							amount = ext.findInLine("\\d+\\.\\d+");
							
						}
					}
					if(receiptPanelList.isSelectionEmpty()){ 
						String data[] = {"Return", CardPanel.getInvoiceNo() + "", CardPanel.getInvoiceNo() + "", "POS BRAVO v1.0", ext[5] , amount, "merchantID2" };
						Response res = new Response(2, data);
						if (res.getResponse().contains("Approved")) {
							JOptionPane.showMessageDialog(null, "Success");
							String time = ReceiptPanel.getTimeStamp();
							ReceiptPanel.saveReceiptReturn(time);
							CardPanel.saveTransaction(res.getXML(), res.getResponse(), 1, new File(RECEIPT_PATH + time));
							listModel.clear();
							readReceipts();
						}
					}
					else{
						amount = receiptPanelList.getSelectedValue();
						Scanner ext1 = new Scanner(amount);
						String data[] = {"Return", CardPanel.getInvoiceNo() + "", CardPanel.getInvoiceNo() + "", "POS BRAVO v1.0", ext[5] , ext1.findInLine("\\d+.\\d+"), "merchantID2" };
						Response res = new Response(2, data);
						if (res.getResponse().contains("Approved")) {
							JOptionPane.showMessageDialog(null, "Success");
							String time = ReceiptPanel.getTimeStamp();
							String item = receiptPanelList.getSelectedValue();
							ReceiptPanel.saveReceiptReturn(time, item);
							CardPanel.saveTransaction(res.getXML(), res.getResponse(), 1, new File(RECEIPT_PATH + time));
							listModel.clear();
							readReceipts();
						}
					}	
				}
				/*else if(type.contains("SWIPED")){
					ReceiptPanel.saveReceiptReturn();
					ext = ResponseExtract.getData(receiptList.getSelectedValue(), "Return");
					listModel.clear();
					readReceipts();
			
				}*/
				
				read.close();
			
			
		}
		//Assumes no issue with parsing out the data
		//Asterisk (*) indicates there was reversal voidsale issue so it was processed or need to be processed as standard voidsale, which is done automatically
		if(event.getActionCommand().equals("Void") && receiptList.getSelectedIndex() > -1 && checkValidState(receiptList.getSelectedValue()))
		{
			//ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
			//String firstLine = ReceiptPanel.getListModel().get(0).trim();
			String inputVoid[] = null;
			String data[] = null;
			if(firstline.equalsIgnoreCase("SWIPED")) {
				Response response3 = null;
				if(!firstline.contains("*")){
					data = ResponseExtract.getData(receiptList.getSelectedValue(), "PreAuthCapture");
					inputVoid = new String[] {"", data[1], data[2], "POS BRAVO v1.0", data[5], data[3], data[7], data[8], data[6], data[0]};
					response3 = new Response(4, inputVoid);
					CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
				}
				if(response3 != null){
					if(response3.getResponse().contains("Approved")) {
						ProcessPanel.closeReceipt("SWIPED VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
						ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
					} else {
						inputVoid = new String[] {"Standard", data[1], data[2], "POS BRAVO v1.0", data[5], data[3], data[7], data[8], data[6], data[0]};
						response3 = new Response(4, inputVoid);
						CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
						if(response3.getResponse().contains("Approved")) {
							ProcessPanel.closeReceipt("SWIPED VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));	
							ReceiptPanel.loadReceipt(receiptList.getSelectedValue());

						}
						else{
							ProcessPanel.closeReceipt("SWIPED*", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));	
							ReceiptPanel.loadReceipt(receiptList.getSelectedValue());

						}
					}
				}
				else{
					inputVoid = new String[] {"Standard", data[1], data[2], "POS BRAVO v1.0", data[5], data[3], data[7], data[8], data[6], data[0]};
					response3 = new Response(4, inputVoid);
					CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
					if(response3.getResponse().contains("Approved")) {
						ProcessPanel.closeReceipt("SWIPED* VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));	
						ReceiptPanel.loadReceipt(receiptList.getSelectedValue());

					}
				}
				return;
		
			} else if(firstline.equalsIgnoreCase("PROGRESS")) {
				Response response3 = null;
				if(!firstline.contains("*")){
					data = ResponseExtract.getData(receiptList.getSelectedValue(), "PreAuth");
					inputVoid = new String[] {"", data[1], data[1], "POS BRAVO v1.0", data[5], data[4], data[7], data[8], data[6], data[0]};
					response3 = new Response(4, inputVoid);
					CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
				}
				if(response3 != null) {
					if(response3.getResponse().contains("Approved")){ 
						ProcessPanel.closeReceipt("PROGRESS VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
						ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
					}
					else {
						String capture[] = {data[1], data[1], data[5], data[4], data[4], "0", data[6], data[7], data[0]};
						CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 2, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
						if(response3.getResponse().contains("Approved")) {
							data = ResponseExtract.getData(receiptList.getSelectedValue(), "PreAuthCapture");
							inputVoid = new String[] {"Standard", data[1], data[2], "POS BRAVO v1.0", data[5], data[3], data[7], data[8], data[6], data[0]};
							response3 = new Response(4, inputVoid);
							CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
							if(response3.getResponse().contains("Approved")) {
								ProcessPanel.closeReceipt("PROGRESS* VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));	
								ReceiptPanel.loadReceipt(receiptList.getSelectedValue());

							}
							else{
								ProcessPanel.closeReceipt("PROGRESS*", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));	
								ReceiptPanel.loadReceipt(receiptList.getSelectedValue());

							}


						}
						else{
							//Assuming PreAuthCapture never fail
						}
					}
					return;
				}
				else{
					data = ResponseExtract.getData(receiptList.getSelectedValue(), "PreAuthCapture");
					inputVoid = new String[] {"Standard", data[1], data[2], "POS BRAVO v1.0", data[5], data[3], data[7], data[8], data[6], data[0]};
					response3 = new Response(4, inputVoid);
					CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
					if(response3.getResponse().contains("Approved")) {
						ProcessPanel.closeReceipt("PROGRESS* VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));	
						ReceiptPanel.loadReceipt(receiptList.getSelectedValue());

					}

				}
			} else if(firstline.equalsIgnoreCase("OPEN")) {
				ProcessPanel.closeReceipt("OPEN VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
				//deleteReceipt();
				ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
				return;
			}  else if(firstline.equalsIgnoreCase("CASH")) {
				ProcessPanel.closeReceipt("CASH VOIDED", new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
				ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
				
				//deleteReceipt();
				return;
			}
			else{
				System.out.print("Extract Error");
				return;
			}
		
			/*Response response3 = new Response(4, inputVoid);
			CardPanel.saveTransaction(response3.getXML(), response3.getResponse(), 3, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
			if(response3.getResponse().contains("Approved")) {
				ProcessPanel.closeReceipt(title, new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue()));
				//tabStrings = new String[]{"","","",""};
				//SystemInit.setTransactionScreen();
				
				ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
				
			} else {
				//String response = CardPanel.getText(response3.getResponse());
//				Scanner regex = new Scanner(response3.getResponse());
//				String error = regex.findInLine("<TextResponse>[\\.a-zA-Z \\d]*</TextResponse>");
				//display.setText("Unable to void: " + response/*error.substring("<TextResponse>".length(), error.length() - "</TextResponse>".length())*///);
				//regex.close();
				//tabStrings[2] = ""; tabStrings[3] = "";
				//current = "";
				//Tools.update(receiptList);
			//}
			//setToVoid(receiptList.getSelectedValue());
		}
	}
	
	
	private void setToVoid(String fileName)
	{
		System.out.println("HERE");
		File toCopy = new File(RECEIPT_PATH + "/" + fileName);
		String temp = "VOID";
		Scanner reader = null;
		PrintWriter copy = null;
		try {
			reader = new Scanner(toCopy);
			reader.nextLine();
			while(reader.hasNextLine())
			{
				temp += "\n" + reader.nextLine();
			}
			reader.close();
			copy = new PrintWriter(toCopy);
			copy.println(temp);
			copy.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean checkValidState(String fileName)
	{
		File toCheck = new File(RECEIPT_PATH + "/" + fileName);
		Scanner checker = null;
		try {
			checker = new Scanner(toCheck);
			firstline = checker.nextLine();
			checker.close();
			if(firstline == null)
			{
				throw new FileNotFoundException();
			}
			//if(firstline.equalsIgnoreCase("PROGRESS") || firstline.equalsIgnoreCase("SWIPED") || firstline.equalsIgnoreCase("OPEN"))
			//{
				//return true;
			//} else {
				//	JOptionPane.showMessageDialog(null, "This ticket has already VOIDED or This ticket is not SWIPED/PROGRESS/OPEN type.");
				//	return false;
			//}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Private helper method which is called to read the text files stored in the system
	 */
	private void readReceipts()
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(RECEIPT_LIST));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		while(inputStream.hasNextLine())
		{
			String line = inputStream.nextLine().trim();
			
			if(line.equals(""))
				;
			else
				listModel.addElement(line);
		}
		inputStream.close();
	}
	/**
	 * Private helper method which deletes a receipt, removing it from the JList and saving the change to the
	 * system.
	 */
	private void deleteReceipt()
	{
		File file = new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue());
		file.delete();
		
		listModel.removeElementAt(receiptList.getSelectedIndex());
		
		saveReceiptList();
		ReceiptPanel.clearReceipt();
	}
	/**
	 * Private helper method which removes all receipt files from the JList and from the System
	 */
	private void deleteAll()
	{
		File receiptDirectory = new File(RECEIPT_PATH);
		File[] file = receiptDirectory.listFiles();
		
		for(int count=0; count < file.length; count++)
			file[count].delete();
		
		listModel.removeAllElements();
		saveReceiptList();
		ReceiptPanel.clearReceipt();
	}
	/**
	 * Method called to save any changes made to the receipt text file data
	 */
	private void saveReceiptList()
	{
		PrintWriter listWriter = null;
		try
		{
			listWriter = new PrintWriter(RECEIPT_LIST);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		for(int count = 0; count < listModel.getSize(); count++)
			listWriter.println(listModel.getElementAt(count));
		listWriter.close();
	}
}

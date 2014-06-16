package pointOfSale;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ResponseExtract {

	private static String fileName = "";
	//private static String value = null;
	
	public ResponseExtract(String fileName){
		this.fileName = fileName;
	}
	
	/*public ResponseExtract(File file){
		double value = 0;
		try {
			Scanner read = new Scanner(file);
			while(read.hasNextLine()){
				String temp = read.nextLine();
				if(temp.contains("Approved")){
					String temp2[] = temp.split(" ");
					value = Double.parseDouble(temp2[0].substring(1));
					DecimalFormat format = new DecimalFormat("0.00");
					this.value = format.format(value);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
//fileName2 is the relative name of the file without the extension, receiptType is the type of the transaction to extract data from 
	public static String[] getData(String fileName2, String receiptType) {
		Scanner reader = null;
		String[] toReturn = new String[10];
		if(!fileName2.equals("") && fileName2 != null){
			fileName = fileName2;
		}
		//1 is for preauth
		if (receiptType.equals("PreAuth") || receiptType == null || receiptType.equals("")) {
			try {
				reader = new Scanner(new File("Files/Transaction/Response/1/"
						+ fileName + ".xml"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} 
		else {
			//2 is for preauth capture
			try {
				reader = new Scanner(new File("Files/Transaction/Response/2/"
						+ fileName + ".xml"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				// return null;
			}
		}

		if (reader != null) {
			while (reader.hasNextLine()) {
				String read = reader.nextLine();
				Scanner regex = new Scanner(read);
				try {
					if (read.contains("MerchantID")) {
						toReturn[0] = regex
								.findInLine("<MerchantID>[\\da-zA-Z =]+</MerchantID>");
						toReturn[0] = toReturn[0]
								.substring("<MerchantID>".length(),toReturn[0].length()
										- "</MerchantID>".length()).trim();
					} else if (read.contains("InvoiceNo")) {
						toReturn[1] = regex
								.findInLine("<InvoiceNo>[\\d]+</InvoiceNo>");
						toReturn[1] = toReturn[1].substring(
								"<InvoiceNo>".length(), toReturn[1].length()
								- "</InvoiceNo>".length()).trim();
					} else if (read.contains("RefNo")) {
						toReturn[2] = regex
								.findInLine("<RefNo>[\\d]+</RefNo>");
						toReturn[2] = toReturn[2].substring("<RefNo>".length(),
								toReturn[2].length() - "</RefNo>".length()).trim();
					} else if (read.contains("Purchase")) {
						toReturn[3] = regex
								.findInLine("<Purchase>[\\d\\.]+</Purchase>");
						toReturn[3] = toReturn[3].substring(
								"<Purchase>".length(), toReturn[3].length()
								- "</Purchase>".length()).trim();
					} else if (read.contains("Authorize")) {
						toReturn[4] = regex
								.findInLine("<Authorize>[\\d\\.]*</Authorize>");
						toReturn[4] = toReturn[4].substring(
								"<Authorize>".length(), toReturn[4].length()
								- "</Authorize>".length()).trim();
					} else if (read.contains("RecordNo")) {
						toReturn[5] = regex
								.findInLine("<RecordNo>.+</RecordNo>");
						toReturn[5] = toReturn[5].substring(
								"<RecordNo>".length(), toReturn[5].length()
								- "</RecordNo>".length()).trim();
					} else if (read.contains("AuthCode")) {
						toReturn[6] = regex
								.findInLine("<AuthCode>[\\da-zA-Z]+</AuthCode>");
						toReturn[6] = toReturn[6].substring(
								"<AuthCode>".length(), toReturn[6].length()
								- "</AuthCode>".length()).trim();
					} else if (read.contains("AcqRefData")) {
						toReturn[7] = regex
								.findInLine("<AcqRefData>[\\da-zA-Z ]+</AcqRefData>");
						toReturn[7] = toReturn[7].substring(
								"<AcqRefData>".length(), toReturn[7].length()
								- "</AcqRefData>".length()).trim();
					} else if (read.contains("ProcessData")) {
						toReturn[8] = regex
								.findInLine("<ProcessData>[\\da-zA-Z \\|]+</ProcessData>");
						toReturn[8] = toReturn[8].substring(
								"<ProcessData>".length(), toReturn[8].length()
								- "</ProcessData>".length()).trim();
					} else if (read.contains("TextResponse")) {
						toReturn[9] = regex.findInLine("<TextResponse>.*</TextResponse>");
						toReturn[9] = toReturn[9].substring("<TextResponse>".length(),
								toReturn[9].length() - "</TextResponse>".length()).trim();
					}
				} catch (Exception e) {
					e.printStackTrace();
				
				}
			}
			reader.close();
			
			if(!toReturn[3].equals(toReturn[4])){
				toReturn[3] = toReturn[4];
			}
			
			if(toReturn[0].contains("395347306")){
				toReturn[0] = "merchantID1";
			}
			else if(toReturn[0].contains("395347308") || toReturn[0].contains("118725340908147")){
				toReturn[0] = "merchantID2";
			}
			System.out.println(toReturn[0]);
		}

		return toReturn;
	}
}
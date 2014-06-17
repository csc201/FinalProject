package testing;

import java.util.Scanner;

public class resExtract {

	public static Scanner reader = null;
	public static Scanner regex = null;
	public static String code[] = new String[16];

	
	public resExtract(){
		
	}
	
	public static String [] getCodes(String xml) {
		
			reader = new Scanner(xml);
	
		while (reader.hasNextLine()) {
			String read = reader.nextLine();
			regex = new Scanner(read);
            if (read.contains("AuthCode")) {
            	String temp = null;
				temp = regex
						.findInLine("<AuthCode>[\\da-zA-Z]*</AuthCode>");
				temp = temp.substring("<AuthCode>".length(),
						temp.length() - "</AuthCode>".length());
				code[0] = temp;
            }
            else if (read.contains("RefNo")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<RefNo>[\\da-zA-Z]+</RefNo>");
        				temp = temp.substring("<RefNo>".length(),
        						temp.length() - "</RefNo>".length());
        				code[1] = temp;
                    }
            else if (read.contains("RecordNo")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<RecordNo>.*</RecordNo>");
        				temp = temp.substring("<RecordNo>".length(),
        						temp.length() - "</RecordNo>".length());
        				code[2] = temp;
                    }
            else if (read.contains("AcqRefData")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<AcqRefData>[\\da-zA-Z ]+</AcqRefData>");
        				temp = temp.substring("<AcqRefData>".length(),
        						temp.length() - "</AcqRefData>".length());
        				code[3] = temp;
                    }
            else if (read.contains("ProcessData")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<ProcessData>[\\da-zA-Z |]+</ProcessData>");
        				temp = temp.substring("<ProcessData>".length(),
        						temp.length() - "</ProcessData>".length());
        				code[4] = temp;
                    }
            else if (read.contains("BatchNo")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<BatchNo>[\\da-zA-Z |]+</BatchNo>");
        				temp = temp.substring("<BatchNo>".length(),
        						temp.length() - "</BatchNo>".length());
        				code[5] = temp;
                    }
            else if (read.contains("BatchItemCount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<BatchItemCount>[\\da-zA-Z |]+</BatchItemCount>");
        				temp = temp.substring("<BatchItemCount>".length(),
        						temp.length() - "</BatchItemCount>".length());
        				code[6] = temp;
                    }
            else if (read.contains("NetBatchTotal")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<NetBatchTotal>[\\da-zA-Z |\\.\\-]+</NetBatchTotal>");
        				temp = temp.substring("<NetBatchTotal>".length(),
        						temp.length() - "</NetBatchTotal>".length());
        				code[7] = temp;
            }
            else if (read.contains("CreditPurchaseCount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<CreditPurchaseCount>[\\da-zA-Z |]+</CreditPurchaseCount>");
        				temp = temp.substring("<CreditPurchaseCount>".length(),
        						temp.length() - "</CreditPurchaseCount>".length());
        				code[8] = temp;
            }
            else if (read.contains("CreditPurchaseAmount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<CreditPurchaseAmount>[\\da-zA-Z |\\.]+</CreditPurchaseAmount>");
        				temp = temp.substring("<CreditPurchaseAmount>".length(),
        						temp.length() - "</CreditPurchaseAmount>".length());
        				code[9] = temp;
            }
            else if (read.contains("CreditReturnCount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<CreditReturnCount>[\\da-zA-Z |]+</CreditReturnCount>");
        				temp = temp.substring("<CreditReturnCount>".length(),
        						temp.length() - "</CreditReturnCount>".length());
        				code[10] = temp;
            }
            else if (read.contains("CreditReturnAmount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<CreditReturnAmount>[\\da-zA-Z |\\.]+</CreditReturnAmount>");
        				temp = temp.substring("<CreditReturnAmount>".length(),
        						temp.length() - "</CreditReturnAmount>".length());
        				code[11] = temp;
            }
            else if (read.contains("DebitPurchaseCount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<DebitPurchaseCount>[\\da-zA-Z |]+</DebitPurchaseCount>");
        				temp = temp.substring("<DebitPurchaseCount>".length(),
        						temp.length() - "</DebitPurchaseCount>".length());
        				code[12] = temp;
            }
            else if (read.contains("DebitPurchaseAmount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<DebitPurchaseAmount>[\\da-zA-Z |\\.]+</DebitPurchaseAmount>");
        				temp = temp.substring("<DebitPurchaseAmount>".length(),
        						temp.length() - "</DebitPurchaseAmount>".length());
        				code[13] = temp;
            }
            else if (read.contains("DebitReturnCount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<DebitReturnCount>[\\da-zA-Z |]+</DebitReturnCount>");
        				temp = temp.substring("<DebitReturnCount>".length(),
        						temp.length() - "</DebitReturnCount>".length());
        				code[14] = temp;
            }
            else if (read.contains("DebitReturnAmount")) {
            	String temp = null;
        				temp = regex
        						.findInLine("<DebitReturnAmount>[\\da-zA-Z |\\.]+</DebitReturnAmount>");
        				temp = temp.substring("<DebitReturnAmount>".length(),
        						temp.length() - "</DebitReturnAmount>".length());
        				code[15] = temp;
            }
     

            
					regex.close();
		}
		return code;
	}
}

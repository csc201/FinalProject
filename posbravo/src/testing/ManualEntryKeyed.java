package testing;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.*;

public class ManualEntryKeyed {

	public static void main(String [] args){
//Testing For Manual Entry---------------------------------------------------------------------------------------------------
		Response set = new Response();
		set.setIDnPas("merchantID2");
		
//case#1 PreAuth/PreAuthCapture/Reversal Voidsale         
/*	    String card = "0~IPAD100KB|24~98AC18281702140C|1~33|2~E0D41A50DEDBFA8C563911A88E1724F45609804BB2498B345D9AD71D2DA06C17012D2ABDBF05E6B2B44D058AEDAE5B45577BEDA9B3072FF6|3~A061C55A42FD89B1A59C74BD9C4020B044DE8BD2E5C73B39C0895BF722EE5EFE3C2EDAC2EFC0557B|4~|6~%M373953006001001^MANUAL ENTRY/^15120000000000000000?|7~;373953006001001=15120000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D4|12~00000000|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		System.out.print(m1.group());
		//String one[] = {"22", "22", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.09", "1.09", "123", "4 Corporate SQ", "30329"};
	    String one[] = {"23", "23", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.08", "1.08", "Keyed"};
		//String one[] = {"24", "24", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.10", "1.10", "123", "4 Corporate SQ", "30329"};
		
		
        //PreAuth Encrypted
		Response test = new Response(4, one);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		
		//PreAuth Capture		
		String [] info = getCodes(test.getResponse());
		String two[] = {"23", "23", info[2], "1.08", "1.08", "", info[0], info[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
		
		String three[] = {"24", info2[1], "POS BRAVO v1.0", info2[2], "1.08", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
//case#1 Sale/Reversal Voidsale
	
		//Sale
/*		String yuk[] = {"25", "25", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.08", "Keyed"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
		
		String three[] = {"26", info2[1], "POS BRAVO v1.0", info2[2], "1.08", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/

//case#2 PreAuth/PreAuthCapture/Reversal Voidsale
/*		String card = "0~IPAD100KB|24~98AC18281702140C|1~33|2~5D27DA5456F23BAAD2D41C84FEAFE4E2892EDA2DDCAAC63D7D9586B8879E5EEE9FFB1ACEFCD3C28FC7BF8A3DACC685C7BC318FCC7DFD15D0|3~2C4611492778214F5DA211971A829B7607008F3BF536E881019B8872B32BAB27137E1817CD1919DA|4~|6~%M6011900006005677^MANUAL ENTRY/^1512000000000000000?|7~;6011900006005677=1512000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000C8|12~00000000|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		System.out.print(m1.group());
		String one[] = {"27", "27", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.09", "1.09", "Keyed"};
		
        //PreAuth Encrypted
		
		
		Response test = new Response(4
				
				
				, one);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		
		//PreAuth Capture		
		String [] info = getCodes(test.getResponse());
		String two[] = {"27", "27", info[2], "1.09", "1.09", "", info[0], info[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
		
		String three[] = {"28", info2[1], "POS BRAVO v1.0", info2[2], "1.09", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
//case#2 Sale/Reversal Voidsale
		//Sale
/*		String yuk[] = {"30", "30", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.09", "Keyed"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
		
		String three[] = {"31", info2[1], "POS BRAVO v1.0", info2[2], "1.09", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/

//case#3 PreAuth/PreAuthCapture/Reversal Voidsale
		String card = "0~IPAD100KB|24~98AC18281702140C|1~33|2~E1C41F4E26AC699FA695E9D0960F6129C897C9F093BD4AB9F21C22E04490999B2EFE247BC264FBCB3A4FD3E4B52937CE0497D0C28863B508|3~BEDDD19A8B388F885DBCDDB6263943C980C1DDA82D58FAC42FD24D14DE23ED73EADD8CDA09F35D89|4~|6~%M5439750002000248^MANUAL ENTRY/^1512000000000000000?|7~;5439750002000248=1512000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000C9|12~00000000|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		System.out.print(m1.group());
/*		String one[] = {"32", "32", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.10", "1.10", "Keyed"};
		
        //PreAuth Encrypted
		Response test = new Response(4, one);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		
		//PreAuth Capture		
		String [] info = getCodes(test.getResponse());
		String two[] = {"32", "32", info[2], "1.10", "1.10", "", info[0], info[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
		
		String three[] = {"33", info2[1], "POS BRAVO v1.0", info2[2], "1.10", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/
//case#3 Sale/Reversal Voidsale
		
		//Sale
		String yuk[] = {"34", "34", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.10", "Keyed"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
				
		String three[] = {"35", info2[1], "POS BRAVO v1.0", info2[2], "1.10", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");		
	
	}
	public static String [] getCodes(String xml) {
		
		
		Scanner reader = null;
		Scanner regex = null;
		String code[] = new String[5];

		
		
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
            
            
					regex.close();
		}
		return code;
	}
}

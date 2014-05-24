package testing;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.*;

public class CardPresentSwipedSale {

	public static void main(String[] args){
		Response set = new Response();
		set.setIDnPas("merchantID2");
		
		String card = "0~IPAD100KB|24~98AC18281702140C|1~11|2~812C72CA9B05C907CBB9AB95EA7D86E3AEB5698B38AC7601A45BB9DC1D42CEEDC7BD8587052209946689EC23356E1C67|3~D7E14F5CB294A2D8F2D3D93F700BF425DD7585BC1366294BD3FE39BCC4258D66F1D8C2B5BC8AB9E7|4~|5~7F4EB719A60BB8D4965488647134621C0AA803D8FD6B5AE1678F86177318FCAB08E5EB43450853345F2C51C2EAEF46F62F982F3B49D6BEAC|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D2|12~00002200|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
//case#1 Sale/Reverse Voidsale
		//replace card data with swiped
	
		
		//Sale
/*		String yuk[] = {"35", "35", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "2.02", "Swiped"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
				
		String three[] = {"36", info2[1], "POS BRAVO v1.0", info2[2], "2.02", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/
//case#2 Sale/Reversal Voidsale/Voidsale
		//Sale
		
		String yuk[] = {"14", "14", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "24.01", "Swiped"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
				
		String three[] = {"15", info2[1], "POS BRAVO v1.0", info2[2], "24.01", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//Standard Voidsale
		
		three = new String[]{"16", info2[1], "POS BRAVO v1.0", info2[2], "24.01", info2[3], info2[4], info2[0], "merchantID2" };

		
		test = new Response(5, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

//case#3 Sale/Adjust/Voidsale
		//Sale
	/*	String yuk[] = {"40", "40", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "2.03", "Swiped"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//Adjust
		String [] info2 = getCodes(test.getResponse());
		String []adj = new String[]{"40", info2[1], "POS BRAVO v1.0", info2[2], "2.13", "2.13", "", info2[0], "merchantID2"};
		
		test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		
		//Standard Voidsale
		info2 = getCodes(test.getResponse());
		String three[] = {"41", info2[1], "POS BRAVO v1.0", info2[2], "2.13", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(5, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
//case#4 Sale/Adjust/Voidsale
		//Sale
/*		String yuk[] = {"42", "42", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "2.04", "Swiped"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
				
		//Adjust
		String [] info2 = getCodes(test.getResponse());
		String []adj = new String[]{"42", info2[1], "POS BRAVO v1.0", info2[2], "2.04", "2.04", "1.00", info2[0], "merchantID2"};
	
		test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
				
				
		//Standard Voidsale
		//info2 = getCodes(test.getResponse());
		String three[] = {"43", info2[1], "POS BRAVO v1.0", info2[2], "3.04", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(5, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
//case#5 Sale/Sale
		//Sale
/*		String yuk[] = {"44", "44", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "23.54",  "Swiped"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//Sale
		String yuk2[] = {"45", "45", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "3.54", "Swiped"};

		test = new Response(6, yuk2);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
//case#6 Sale/Reversal Voidsale
		//Sale
/*		String yuk[] = {"46", "46", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "23.54", "Swiped"};

		Response test = new Response(6, yuk);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
				
		String three[] = {"47", info2[1], "POS BRAVO v1.0", info2[2], "20.00", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/
		
		
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

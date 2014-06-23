package testing;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.Response;

public class CardPresentSwipedPreAuth {

	public static void main(String[] args){
		Response set = new Response();
		set.setIDnPas("merchantID2");
		
		String card = "0~IPAD100KB|24~98AC18281702140C|1~33|2~EC5A3972BE3A7DD16559861151BAE7C5F961B63D9ED7076DF0C835F873FA9E48ABD0535C751C322E8EA493ED015F7A0DF3312600C8C4AA9E|3~FD1E93B24B1F14EC989506E6CC04E1C360948EDEC23FFBAFE724DD4C04B847D3E243466E7738ABCC|4~|6~%M4005550000000480^MANUAL ENTRY/^1512000000000000000?|7~;4005550000000480=1512000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000CB|12~00000000|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
//case#4 PreAuth/Reversal Voidsale/PreAuthCapture/Voidsale		
	
		//PreAuth Encrypted
		String one[] = {"51", "51", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "24.01", "24.01", "Swiped"};
		
		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
				
		String three[] = {"52", "52", "POS BRAVO v1.0", info2[2], "24.01", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(3, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
				
		
		//PreAuth Capture		
		String two[] = {"51", "51", info2[2], "24.01", "24.01", "", info2[0], info2[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		
		//Standard Voidsale
		info2 = getCodes(test.getResponse());

		String three2[] = {"53", info2[1], "POS BRAVO v1.0", info2[2], "24.01", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(5, three2);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

//case#5 PreAuth/PreAuthCapture/Adjust/VoidSale
		//PreAuth Encrypted
/*		String one[] = {"54", "54", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "3.05", "3.05", "Swiped"};
				
		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//PreAuth Capture
		String [] info2 = getCodes(test.getResponse());
		
		String two[] = {"54", "54", info2[2], "3.05", "3.05", "", info2[0], info2[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");	
		
		//Adjust
		String [] info = getCodes(test.getResponse());
		String []adj = new String[]{"54", info[1], "POS BRAVO v1.0", info[2], "3.50", "3.50", "", info[0], "merchantID2"};
		
		test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
	
		//Standard Voidsale
		info2 = getCodes(test.getResponse());

		String three2[] = {"55", info2[1], "POS BRAVO v1.0", info2[2], "3.50", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(5, three2);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
//case#6 PreAuth/PreAuthCapture/Adjust Gratuity/Voidsale
		
		//PreAuth Encrypted
/*		String one[] = {"56", "56", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "3.06", "3.06", "Swiped"};
				
		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//PreAuth Capture
		String [] info2 = getCodes(test.getResponse());
		
		String two[] = {"56", "56", info2[2], "3.06", "3.06", "0.60", info2[0], info2[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");	
		
		//Adjust
		String [] info = getCodes(test.getResponse());
		String []adj = new String[]{"56", info[1], "POS BRAVO v1.0", info[2], "3.06", "3.06", "1.00", info[0], "merchantID2"};
		
		test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
	
		//Standard Voidsale
		info2 = getCodes(test.getResponse());

		String three2[] = {"57", info2[1], "POS BRAVO v1.0", info2[2], "4.06", info2[3], info2[4], info2[0], "merchantID2" };

		test = new Response(5, three2);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
//case#7 PreAuth/PreAuth/PreAuthCapture/PreAuthCapture
		
		//PreAuth Encrypted
/*		String one[] = {"58", "58", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "23.54", "23.54", "Swiped"};
						
		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		String [] info2 = getCodes(test.getResponse());//For PreAuthCapture 39
		
		//PreAuth Encrypted
		String one2[] = {"59", "59", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "3.54", "3.54", "Swiped"};
						
		test = new Response(4, one2);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		String [] info = getCodes(test.getResponse());//For PreAuthCapture 40
		
		
		//PreAuth Capture
		
		String two[] = {"58", "58", info2[2], "20.00", "20.00", "0.00", info2[0], info2[3]};
		
		test = new Response(2, two);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");	
		
		//PreAuth Capture
		
		String two2[] = {"59", "59", info[2], "3.54", "3.54", "0.00", info[0], info[3]};
		
		test = new Response(2, two2);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");	
		*/
//case#8 PreAuth/Reversal Voidsale
		//PreAuth Encrypted
/*		String one[] = {"60", "60", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "23.54", "23.54", "Swiped"};
								
		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//Reversal Voidsale
		String [] info2 = getCodes(test.getResponse());
						
		String three[] = {"61", "61", "POS BRAVO v1.0", info2[2], "20.00", info2[3], info2[4], info2[0], "merchantID2" };

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

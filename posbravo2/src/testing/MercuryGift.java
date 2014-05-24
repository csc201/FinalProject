package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.Response;

public class MercuryGift {

	public static void main(String [] args){
		Response set = new Response();
		set.setIDnPas("merchantID2");
		
		//Card#1
		String card = "0~IPAD100KB|24~98AC18281702140C|1~11|2~|3~064E2D32867D75803261EE9543050205B332D9D2626F0BF008E627D87EEFDAD0|4~|5~29F9BF6F3C3CE90D7D41914A3429FC9154F09EC8464248104EF93FC1C1DB7CE19501A49D0AA658ED847E76A1D5E9F3C376F73FF597DD9F14|6~|7~;6050110000800001113=250100000?|8~|9~00000000|10~010001|11~9500030000081C2000D5|12~00002200|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		
		//Card#2
		String card2 = "0~IPAD100KB|24~98AC18281702140C|1~11|2~|3~776D9AFFC7F98CDE6BB336EDFAFB537E8454ACB0B3C4576BA1E57E431787948F|4~|5~983BE35EE3C94B8B2E7C5ADC8EE13A9A3798DFE01087F9BF0049083E4E8551B3A0A73ED7651B18B479AD4BA6ACAD1E00D20105B8BD85CFB2|6~|7~;6050110000500006321=250100000?|8~|9~00000000|10~010001|11~9500030000081C2000D6|12~00002200|";
		Pattern pregex2 = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m2 = pregex2.matcher(card);
		m2.find();
		System.out.println(m.group());
		Pattern pregex3 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m3 = pregex3.matcher(card);
		m3.find();
		
		//987654321
		String card3 = "";
		Pattern pregex4 = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m4 = pregex4.matcher(card);
		m4.find();
		System.out.println(m.group());
		Pattern pregex5 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m5 = pregex5.matcher(card);
		m5.find();
		
	
/*		String elv[] = {"Swiped", "Balance", "merchantID2", "000065", "000065", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2)};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		elv = new String[]{"Keyed", "Issue", "merchantID2", "000066", "0000066", "POS BRAVO v1.0", card3.substring(m5.start()+3, m5.end()-3), card3.substring(m4.start()+2, m4.end()-2), "100.00"};
		
		test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		elv = new String[]{"Keyed", "Issue", "merchantID2", "000067", "000067", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "550.00"};
		
		test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/		
/*		String []elv = new String[]{"Swiped", "Issue", "merchantID2", "000066", "000066", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "100.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/
		
/*		String [] elv = new String[]{"Swiped", "Issue", "merchantID2", "000067", "000067", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "50.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String []elv = new String[]{"Swiped", "Reload", "merchantID2", "000068", "000068", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "55.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "Return", "merchantID2", "000069", "000069", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "25.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000070", "000070", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "5.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000070", "000070", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "5.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
/*		String []elv = new String[]{"Swiped", "Balance", "merchantID2", "000071", "000071", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2)};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String []elv = new String[]{"Swiped", "Issue", "merchantID2", "000072", "000072", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2), "125.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/

/*		String [] elv = new String[]{"Swiped", "Sale", "merchantID2", "000073", "000073", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "75.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
		//String info[] = resExtract.getCodes(test.getResponse());
		
/*		String [] elv = new String[]{"Swiped", "VoidSale", "merchantID2", "000076", "560216", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "75.00", "560216"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
/*		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000077", "000077", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "200.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000078", "000078", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2), "25.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "Balance", "merchantID2", "000079", "000079", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2)};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000080", "000080", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2), "80.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
/*		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000081", "000081", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2), "15.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
/*		String [] elv = new String[]{"Swiped", "Balance", "merchantID2", "000082", "000082", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2)};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
		String [] elv = new String[]{"Swiped", "NoNSFSale", "merchantID2", "000083", "000083", "POS BRAVO v1.0", card2.substring(m3.start()+3, m3.end()-3), card2.substring(m2.start()+2, m2.end()-2), "5.00"};
		
		Response test = new Response(11, elv);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		
		
	}
}

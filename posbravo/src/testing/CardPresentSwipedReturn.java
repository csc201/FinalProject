package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.Response;

public class CardPresentSwipedReturn {
	
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
		
//case#1 Return
		//Return
/*		String ret[] = {"61", "61", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "4.02", "4.02", "Swiped"};
		
		Response test = new Response(8, ret);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/
//case#2 Return/VoidReturn
		
		//Return
		String ret[] = {"62", "62", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "4.03", "4.03", "Swiped"};
		
		Response test = new Response(8, ret);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//VoidReturn
		String [] info2 = resExtract.getCodes(test.getResponse());
		String vdret[] = {"63", info2[1], "POS BRAVO v1.0", info2[2], "4.03", info2[0], "merchantID2"};
		
		test = new Response(9, vdret);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
	}
	
}

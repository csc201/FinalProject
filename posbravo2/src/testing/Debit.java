package testing;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.Response;

public class Debit {

	public static void main(String [] args){
		Response set = new Response();
		set.setIDnPas("merchantID2");

		//#5
		String card = "0~IPAD100KB|24~98AC18281702140C|1~21|2~9E3AB048E1F7965DEDF1CA1B04182D32804DEA0DB89FF90C397801918031951AE4DD1C6575445C81F054CEA6930A4664|3~3C86446B05048B9002FF4C0DDCC29098042F0FF1A7B65C696D3EAE14B143E8CFE29AFA793EFED712|4~|5~5D7FA1AA0F3F73CFE21537FB833F9CB61A75D88FE0124D043D54AC0D7EF7D9FAB08ACAE8D185F764B24383174F77953409CF1D0E0CFEE8FD|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D0|12~00002200|13~FFFF3D0100002C200002|14~1C61B5129D749DFC|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		Pattern pregex2 = Pattern.compile("13~.*\\|14"); //encrypted pin key
		Matcher m2 = pregex2.matcher(card);
		m2.find();
		Pattern pregex3 = Pattern.compile("14~.*\\|"); //encrypted pin 
		Matcher m3 = pregex3.matcher(card);
		m3.find();
		//#6
		String card2 = "0~IPAD100KB|24~98AC18281702140C|1~21|2~640C5555178FB6F17692AC159C3DD5655C4A25972F8AEE79B6F15FE405486CD7B9557283E543F79684B31276654893C3|3~BC96BB8FCFBBE57870A52D292ACA3B78291AD19D4080E25E031BF0EC4494D6D0558F26DC36910C93|4~|5~96761B085538D6C5A6AF90ECD47D9414D37B6784837C29FC8E0378748B74A0C91D667727C7CAA576D803122378C9E45A1606D14349320789|6~%B5439750002000248^TEST/MPS^15120000000000000?|7~;5439750002000248=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D1|12~00002200|13~FFFF3D0100002C200003|14~7828278B747E10B2|";
		Pattern pregex4 = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m4 = pregex4.matcher(card2);
		m4.find();
		Pattern pregex5 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m5 = pregex5.matcher(card2);
		m5.find();
		System.out.println(m.group());
		Pattern pregex6 = Pattern.compile("13~.*\\|14"); //encrypted pin key
		Matcher m6 = pregex6.matcher(card2);
		m6.find();
		Pattern pregex7 = Pattern.compile("14~.*\\|"); //encrypted key
		Matcher m7 = pregex7.matcher(card2);
		m7.find();
		
	/*	String ten[] = {"Sale", "84", "84", "POS BRAVO v1.0", card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "23.28", "", card2.substring(m7.start()+3, m7.end()-1), card2.substring(m6.start()+7, m6.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
	/*	String[] ten = new String[]{"Sale", "90", "90", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "5.03", "", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
	/*	String [] ten = new String[]{"Return", "86", "86", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "5.04", "", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
		String [] ten = new String[]{"Sale", "87", "87", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "5.05", "1.00", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		

		


	}
}

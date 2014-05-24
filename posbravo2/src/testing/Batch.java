package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.Response;

public class Batch {

	public static void main(String[] args){
		Response set = new Response();
		set.setIDnPas("merchantID2");
//case#1 Credit/BatchSummary/BatchClose
	
		
/*		String card = "0~IPAD100KB|24~98AC18281702140C|1~11|2~812C72CA9B05C907CBB9AB95EA7D86E3AEB5698B38AC7601A45BB9DC1D42CEEDC7BD8587052209946689EC23356E1C67|3~D7E14F5CB294A2D8F2D3D93F700BF425DD7585BC1366294BD3FE39BCC4258D66F1D8C2B5BC8AB9E7|4~|5~7F4EB719A60BB8D4965488647134621C0AA803D8FD6B5AE1678F86177318FCAB08E5EB43450853345F2C51C2EAEF46F62F982F3B49D6BEAC|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D2|12~00002200|";
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		
		String [] randomCredit = {"1", "1", "POS BRAVO v1.0",  card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "0.01", "Swiped"};
		
		Response cre = new Response(6, randomCredit);
		
		System.out.println(cre.getXML());
		System.out.println(cre.getResponse());
		System.out.println("_________________________________");
		
		String creInfo[] = resExtract.getCodes(cre.getResponse());
		randomCredit = new String[]{"1", creInfo[1], "POS BRAVO v1.0",  creInfo[2], "0.02", "0.02", "0.01", creInfo[0] , "merchantID2"};
		
		cre = new Response(7, randomCredit);
		
		System.out.println(cre.getXML());
		System.out.println(cre.getResponse());
		System.out.println("_________________________________");

		
		String[] bat = {"BatchSummary", "merchantID2", "POS BRAVO v1.0"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
		
		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] {"BatchClose", "merchantID2", "POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15]};
		
		res = new Response(12, bat);
		
		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
	*/
//case#2 Debit/BatchSummary/BatchClose
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
		
/*		String [] randomDebit = {"Sale", "1", "1", "POS BRAVO v1.0",  card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "0.01", "0.01", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response deb = new Response(10, randomDebit);
		
		System.out.println(deb.getXML());
		System.out.println(deb.getResponse());
		System.out.println("_________________________________");
		
		String[] bat = {"Credit", "BatchSummary", "merchantID2", "POS BRAVO v1.0"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
		
		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] {"Credit", "BatchClose", "merchantID2", "POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15]};
		
		res = new Response(12, bat);
		
		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
		
	*/	
		
		
	/*	String []randomDebit = new String[]{"Return", "2", "2", "POS BRAVO v1.0",  card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "0.02", "", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response deb = new Response(10, randomDebit);
		
		System.out.println(deb.getXML());
		System.out.println(deb.getResponse());
		System.out.println("_________________________________");

		
		String[] bat = {"Credit","BatchSummary", "merchantID2", "POS BRAVO v1.0"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
		
		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] {"Credit", "BatchClose", "merchantID2", "POS BRAVO v1.0", info[5], info[6], info[7], "1", info[9], info[10], info[11], info[12], info[13], info[14], info[15]};
		
		res = new Response(12, bat);
		
		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
	*/
//case #5 CBatch
		String[] bat = {"CBatch","BatchSummary", "merchantID2", "POS BRAVO v1.0"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getSoap());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
		
//case #6 CAllDetail
	/*	String[] bat = {"CAllDetail", "merchantID2", "2", "11/22/13", "11/22/13"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getSoap());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
		*/
//case #7 CTranDetail
/*		String[] bat = {"CTranDetail", "merchantID2", "2"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getSoap());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");*/
//case #8 CAllGiftDetail
/*		String[] bat = {"CAllGiftDetail", "merchantID2", "2", "11/10/13", "11/22/13"};
		
		Response res = new Response(12, bat);
		
		System.out.println(res.getSoap());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
*/
	}
}

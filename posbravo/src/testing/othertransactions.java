package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pointOfSale.Response;

public class othertransactions {

	public static void main(String [] args){
		Response set = new Response();
		set.setIDnPas("merchantID2");
		/*String [] cre = {"1", "1", "POS BRAVO v1.0", "4005550000000480=15125025432198712345", "1.02", "1.02"};
		
		Response test = new Response(1, cre);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		*/
		
		//card #5
		String card = "0~IPAD100KB|24~98AC18281702140C|1~11|2~FDC81D61C147580AD70E62D9596125FE5411DC43F3B243C7F82AEC7BB6290AF682C574FD2C70D11D400CA88D1E8739C6|3~1F71F0FBE3248FA139BC0C66D76FB6DA5AB3840CFCFC7DDDACE2BE0C966B8A3F7C91909EEB394B81|4~|5~C48F5DF3FD5C8C817EA6B6885F026811FA251F59A225588733FFE647D2203804EB6CE63E15A4DB90E2D05B5DDFA033209CC1ED346BD7C852|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D7|12~00002200|";
		
		Pattern pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		
		//card #6
		String card2 = "0~IPAD100KB|24~98AC18281702140C|1~11|2~F2DDF9E1268063DB6D0AB160E4606381A775E7656D98FC4F177B059AC0C5F648F36203660B2A8ECE54DD94D1D7DE3B3F|3~DF7750C8019A1FD4A7247A67363C990333936832710DB068061D41F7E78F3B273234C4140E8D2FBF|4~|5~B2E1B57E9A04B0B8EA9F5427EEBC8F620541ABA6BF3E9683BCF55E4B929BCA93536914E8AD671BD98345251B53DB5CAEF40F8002A3ADF14D|6~%B5439750002000248^TEST/MPS^15120000000000000?|7~;5439750002000248=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D8|12~00002200|";
		Pattern pregex4 = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m4 = pregex4.matcher(card2);
		m4.find();
		Pattern pregex5 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m5 = pregex5.matcher(card2);
		m5.find();
		System.out.println(m.group());

		
		//PreAuth Encrypted
/*		String one[] = {"53", "53", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.07", "1.07", "Swiped"};
		
		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//PreAuth Capture
		
		String [] info2 = resExtract.getCodes(test.getResponse());
		
		String two[] = {"53", "53", info2[2], "1.07", "1.07", "", info2[0], info2[3]};
				
		test = new Response(2, two);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//PreAuth Encrypted
		String one2[] = {"54", "54", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.07", "1.07", "Swiped"};
		
		test = new Response(4, one2);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//PreAuth Capture
		
		String [] info = resExtract.getCodes(test.getResponse());
		
		String two2[] = {"54", "54", info[2], "1.07", "1.07", "", info[0], info[3]};
				
		test = new Response(2, two2);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		// PreAuth Encrypted
		/*String one2[] = { "52", "52", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "1.07", "1.07",
				"Swiped" };
*/
/*		String one2[] = {"54", "54", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.07", "1.07", "Swiped"};
		
		Response test = new Response(4, one2);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		// PreAuth Capture

		String[] info3 = resExtract.getCodes(test.getResponse());

		String two3[] = { "54", "54", info3[2], "1.07", "1.07", "", info3[0],
				info3[3] };

		test = new Response(2, two3);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		
		
	*/	
	/*	//Sale
	 String [] cre = {"2", "2", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "10.01", "Swiped" }; 
		
		Response test = new Response(6 , cre);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
*/
		//Reversal
/*		String three[] = {"2", "0001", "POS BRAVO v1.0", "SVKrBe3BEUna3fCgNGgIn+E56w9aOFW5IhESEAAjEAjCMA==", "10.01", "aEb013328077776483cRBADd5e00fJj479188131124195444lA  m000005oP", "|00|410100201000", "VI1001"
				
				, "merchantID2" };

		Response test = new Response(3, three);
				
		System.out.println(test.getXML());
		System.out.println(test.getSoap());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/
		//Sale
/*		String [] cre = {"3", "3", "POS BRAVO v1.0", card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.02", "Swiped" }; 
		
		Response test = new Response(6 , cre);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
*/
	
		//PreAuth Encrypted
/*		String one[] = { "4", "4", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.03", "10.03",
				"Swiped" };

		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
		// PreAuth Capture

	
/*		String two[] = { "4", "4", "nm49T6GVtYTYWMagz9ihw7EOkISg+jftIhESEAAjEAjCMw==", "10.03", "10.03", "", "VI1003",
				"aEb013328093317066cRBADd5e00fJj479193131124200427lA  m000005oP" };

		Response test = new Response(2, two);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/	
		// PreAuth Encrypted
/*		String one[] = { "5", "5", "POS BRAVO v1.0",
				card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.04", "10.04",
				"Swiped" };

		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		// PreAuth Capture

		String[] info2 = resExtract.getCodes(test.getResponse());

		String two[] = { "5", "5", info2[2], "10.04", "10.04", "", info2[0],
				info2[3] };

		test = new Response(2, two);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/
/*		String card = "0~IPAD100KB|24~98AC18281702140C|1~21|2~9E3AB048E1F7965DEDF1CA1B04182D32804DEA0DB89FF90C397801918031951AE4DD1C6575445C81F054CEA6930A4664|3~3C86446B05048B9002FF4C0DDCC29098042F0FF1A7B65C696D3EAE14B143E8CFE29AFA793EFED712|4~|5~5D7FA1AA0F3F73CFE21537FB833F9CB61A75D88FE0124D043D54AC0D7EF7D9FAB08ACAE8D185F764B24383174F77953409CF1D0E0CFEE8FD|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D0|12~00002200|13~FFFF3D0100002C200002|14~1C61B5129D749DFC|";
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
		m3.find();*/
		//Debit Sale
/*		String[] ten = new String[]{"Sale", "1", "1", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "10.05", "", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
		//#6
/*		String card2 = "0~IPAD100KB|24~98AC18281702140C|1~21|2~640C5555178FB6F17692AC159C3DD5655C4A25972F8AEE79B6F15FE405486CD7B9557283E543F79684B31276654893C3|3~BC96BB8FCFBBE57870A52D292ACA3B78291AD19D4080E25E031BF0EC4494D6D0558F26DC36910C93|4~|5~96761B085538D6C5A6AF90ECD47D9414D37B6784837C29FC8E0378748B74A0C91D667727C7CAA576D803122378C9E45A1606D14349320789|6~%B5439750002000248^TEST/MPS^15120000000000000?|7~;5439750002000248=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D1|12~00002200|13~FFFF3D0100002C200003|14~7828278B747E10B2|";
		Pattern pregex4 = Pattern.compile("3~.*\\|4"); //encrypted card data
		Matcher m4 = pregex4.matcher(card2);
		m4.find();
		Pattern pregex5 = Pattern.compile("11~.*\\|12"); //encrypted key
		Matcher m5 = pregex5.matcher(card2);
		m5.find();
		Pattern pregex6 = Pattern.compile("13~.*\\|14"); //encrypted pin key
		Matcher m6 = pregex6.matcher(card2);
		m6.find();
		Pattern pregex7 = Pattern.compile("14~.*\\|"); //encrypted key
		Matcher m7 = pregex7.matcher(card2);
		m7.find();
		//Debit Sale
		String ten[] = {"Sale", "2", "2", "POS BRAVO v1.0", card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.06", "", card2.substring(m7.start()+3, m7.end()-1), card2.substring(m6.start()+7, m6.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		ten = new String[]{"Return", "3", "3", "POS BRAVO v1.0", card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.06", "", card2.substring(m7.start()+3, m7.end()-1), card2.substring(m6.start()+7, m6.end()-3)};
		
		test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/
		//Sale
	/*	String[] cre = { "4", "4", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.07", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
	*/
	// Sale
/*		String[] cre = { "5", "5", "POS BRAVO v1.0",
				card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.08", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());*/
/*		String[] bat = {"Credit","BatchSummary", "merchantID2", "POS BRAVO v1.0"};
		
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
		// Sale
/*		String[] cre = { "6", "6", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.09", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());

		//Batch
		String[] bat = {"Credit","BatchSummary", "merchantID2", "POS BRAVO v1.0"};
		
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
	
		// PreAuth Encrypted
/*		String one[] = { "7", "7", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.10", "10.10",
				"Swiped" };

		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
		// PreAuth Capture

/*		String two[] = { "7", "7", "ihEdDg82jz1BX8ubiAUWKWIZ6buHwcu1IhESEAAjEAjCUA==", "10.10", "10.10", "2.00", "VI1010",
				"aEb013328047709384cRBADd5e00fJj479404131124204304lA  m000005oP" };

		Response test = new Response(2, two);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/		// PreAuth Encrypted
/*		one =new String[] { "8", "8", "POS BRAVO v1.0",
				card2.substring(m5.start() + 3, m5.end() - 3),
				card2.substring(m4.start() + 2, m4.end() - 2), "10.11",
				"10.11", "Swiped" };

		test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");

		// PreAuth Capture

		String[] info2 = resExtract.getCodes(test.getResponse());

		String two[] = { "8", "8", info2[2], "10.11", "10.11", "2.00", info2[0],
				info2[3] };

		test = new Response(2, two);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
*/
		//Reversal
/*		String three[] = {
				"9",
				"0001",
				"POS BRAVO v1.0",
				"9RVy5g3Wlv0UBG9C0atn35mN64z7mcVEIhESEAAjEAjCUg==",
				"12.11",
				"bMCC1510141124  c00j478833131124204305lMCC",
				"|15|410100200000", "MC1011"

				, "merchantID2" };

		Response test = new Response(3, three);

		System.out.println(test.getXML());
		System.out.println(test.getSoap());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/
		// Batch
/*		String[] bat = { "Credit", "BatchSummary", "merchantID2",
				"POS BRAVO v1.0" };

		Response res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");

		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] { "Credit", "BatchClose", "merchantID2",
				"POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9],
				info[10], info[11], info[12], info[13], info[14], info[15] };

		res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");*/
		
    /*    card = "0~IPAD100KB|24~98AC18281702140C|1~11|2~6D5D994B128BD791CD655B9DA4BE5841A4E294C75D60E0E7A8949600D4A425727C2A5C3C0F3C5594A6FD983188757099|3~A814C7D7094DC99CF2F8C0CD6E708359CFC49C2E6F4E173BAD95738CC651978909F5EA20D992F031|4~|5~0A80466BF4148DDB8D2A946B109EEE0A4B710E6866C0454DEB610A82A4FCF95147D2A30A469CCD108D4392D98A0C6D4CF86D6993812AA4AE|6~%B6011900006005677^TEST/MPS^15120000000000000?|7~;6011900006005677=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D9|12~00002200|";
		
		pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		m = pregex.matcher(card);
		m.find();
		pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		m1 = pregex1.matcher(card);
		m1.find();
		
		// PreAuth Encrypted
		String one[] = { "10", "10", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.12", "10.12",
				"Swiped" };

		Response test = new Response(4, one);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	

		// PreAuth Capture

		String[] info2 = resExtract.getCodes(test.getResponse());

		String two[] = { "10", "10", info2[2], "10.12", "10.12", "2.00", info2[0],
				info2[3] };

		test = new Response(2, two);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/
		
	/*	// Batch
		String[] bat = { "Credit", "BatchSummary", "merchantID2",
				"POS BRAVO v1.0" };

		Response res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");

		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] { "Credit", "BatchClose", "merchantID2",
				"POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9],
				info[10], info[11], info[12], info[13], info[14], info[15] };

		res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");
	*/
		// Sale
/*		String[] cre = { "12", "12", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.13", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());

		// Sale
		cre = new String[]{ "13", "13", "POS BRAVO v1.0",
				card2.substring(m5.start() + 3, m5.end() - 3),
				card2.substring(m4.start() + 2, m4.end() - 2), "10.14",
				"Swiped" };

		test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		
		//Adjust
		String [] info2 = resExtract.getCodes(test.getResponse());
		
		String []adj = new String[]{"13", info2[1], "POS BRAVO v1.0", info2[2], "10.14", "2.00", "", info2[0], "merchantID2"};
		
		test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
	*/	
		//Adjust
	/*	
		String []adj = new String[]{"12", "0003", "POS BRAVO v1.0", "BhQLJHBEwjQc5GwFVa3g4gtiF0HyFQFQIhESEAAjEAjCbw==", "10.13", "10.13", "2.00", "VI1013", "merchantID2"};
		
		Response test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
		//Voidsale
/*		String [] three = new String[]{"11", "0004", "POS BRAVO v1.0", "+bUSplemELL5S+UiXnIzc9bxe1inWMZCIhESEAAjEAjCcQ==", "12.14", "", "", "MC1014", "merchantID2" };

		
		Response test = new Response(5, three);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());*/
		
		//card #7
		card = "0~IPAD100KB|24~98AC18281702140C|1~11|2~6D5D994B128BD791CD655B9DA4BE5841A4E294C75D60E0E7A8949600D4A425727C2A5C3C0F3C5594A6FD983188757099|3~A814C7D7094DC99CF2F8C0CD6E708359CFC49C2E6F4E173BAD95738CC651978909F5EA20D992F031|4~|5~0A80466BF4148DDB8D2A946B109EEE0A4B710E6866C0454DEB610A82A4FCF95147D2A30A469CCD108D4392D98A0C6D4CF86D6993812AA4AE|6~%B6011900006005677^TEST/MPS^15120000000000000?|7~;6011900006005677=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D9|12~00002200|";
		
		pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
		m = pregex.matcher(card);
		m.find();
		pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
		m1 = pregex1.matcher(card);
		m1.find();
		
/*		String[] cre = { "14", "14", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.15", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		
		//Adjust
		String [] info2 = resExtract.getCodes(test.getResponse());
		
		String []adj = new String[]{"14", info2[1], "POS BRAVO v1.0", info2[2], "10.15", "10.15", "2.00", info2[0], "merchantID2"};
		
		test = new Response(7, adj);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		// Batch
		String[] bat = { "Credit", "BatchSummary", "merchantID2",
				"POS BRAVO v1.0" };

		Response res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");

		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] { "Credit", "BatchClose", "merchantID2",
				"POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9],
				info[10], info[11], info[12], info[13], info[14], info[15] };

		res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");*/
		
/*		card = "0~IPAD100KB|24~98AC18281702140C|1~21|2~9E3AB048E1F7965DEDF1CA1B04182D32804DEA0DB89FF90C397801918031951AE4DD1C6575445C81F054CEA6930A4664|3~3C86446B05048B9002FF4C0DDCC29098042F0FF1A7B65C696D3EAE14B143E8CFE29AFA793EFED712|4~|5~5D7FA1AA0F3F73CFE21537FB833F9CB61A75D88FE0124D043D54AC0D7EF7D9FAB08ACAE8D185F764B24383174F77953409CF1D0E0CFEE8FD|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D0|12~00002200|13~FFFF3D0100002C200002|14~1C61B5129D749DFC|";
		pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
	    m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
	    m1 = pregex1.matcher(card);
		m1.find();
		Pattern pregex2 = Pattern.compile("13~.*\\|14"); //encrypted pin key
		Matcher m2 = pregex2.matcher(card);
		m2.find();
		Pattern pregex3 = Pattern.compile("14~.*\\|"); //encrypted pin 
		Matcher m3 = pregex3.matcher(card);
		m3.find();
		//Debit Sale
		String[] ten = new String[]{"Sale", "15", "15", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "10.16", "", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/

		//#6
/*		card2 = "0~IPAD100KB|24~98AC18281702140C|1~21|2~640C5555178FB6F17692AC159C3DD5655C4A25972F8AEE79B6F15FE405486CD7B9557283E543F79684B31276654893C3|3~BC96BB8FCFBBE57870A52D292ACA3B78291AD19D4080E25E031BF0EC4494D6D0558F26DC36910C93|4~|5~96761B085538D6C5A6AF90ECD47D9414D37B6784837C29FC8E0378748B74A0C91D667727C7CAA576D803122378C9E45A1606D14349320789|6~%B5439750002000248^TEST/MPS^15120000000000000?|7~;5439750002000248=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D1|12~00002200|13~FFFF3D0100002C200003|14~7828278B747E10B2|";
		pregex4 = Pattern.compile("3~.*\\|4"); // encrypted card data
		m4 = pregex4.matcher(card2);
		m4.find();
		pregex5 = Pattern.compile("11~.*\\|12"); // encrypted key
		m5 = pregex5.matcher(card2);
		m5.find();
		Pattern pregex6 = Pattern.compile("13~.*\\|14"); // encrypted pin key
		Matcher m6 = pregex6.matcher(card2);
		m6.find();
		Pattern pregex7 = Pattern.compile("14~.*\\|"); // encrypted key
		Matcher m7 = pregex7.matcher(card2);
		m7.find();
		//Debit Sale
		String ten[] = {"Sale", "16", "16", "POS BRAVO v1.0", card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.17", "", card2.substring(m7.start()+3, m7.end()-1), card2.substring(m6.start()+7, m6.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		ten = new String[]{"Return", "17", "17", "POS BRAVO v1.0", card2.substring(m5.start()+3, m5.end()-3), card2.substring(m4.start()+2, m4.end()-2), "10.17", "", card2.substring(m7.start()+3, m7.end()-1), card2.substring(m6.start()+7, m6.end()-3)};
		
		test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		*/
/*		card = "0~IPAD100KB|24~98AC18281702140C|1~21|2~9E3AB048E1F7965DEDF1CA1B04182D32804DEA0DB89FF90C397801918031951AE4DD1C6575445C81F054CEA6930A4664|3~3C86446B05048B9002FF4C0DDCC29098042F0FF1A7B65C696D3EAE14B143E8CFE29AFA793EFED712|4~|5~5D7FA1AA0F3F73CFE21537FB833F9CB61A75D88FE0124D043D54AC0D7EF7D9FAB08ACAE8D185F764B24383174F77953409CF1D0E0CFEE8FD|6~%B4005550000000480^TEST/MPS^15120000000000000?|7~;4005550000000480=15120000000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000D0|12~00002200|13~FFFF3D0100002C200002|14~1C61B5129D749DFC|";
		pregex = Pattern.compile("3~.*\\|4"); //encrypted card data
	    m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		pregex1 = Pattern.compile("11~.*\\|12"); //encrypted key
	    m1 = pregex1.matcher(card);
		m1.find();
		Pattern pregex2 = Pattern.compile("13~.*\\|14"); //encrypted pin key
		Matcher m2 = pregex2.matcher(card);
		m2.find();
		Pattern pregex3 = Pattern.compile("14~.*\\|"); //encrypted pin 
		Matcher m3 = pregex3.matcher(card);
		m3.find();
		//Debit Sale
		String[] ten = new String[]{"Sale", "18", "18", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "10.18", "", card.substring(m3.start()+3, m3.end()-1), card.substring(m2.start()+7, m2.end()-3)};
		
		Response test = new Response(10, ten);
		
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		// Batch
		String[] bat = { "Credit", "BatchSummary", "merchantID2",
				"POS BRAVO v1.0" };

		Response res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");

		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] { "Credit", "BatchClose", "merchantID2",
				"POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9],
				info[10], info[11], info[12], info[13], info[14], info[15] };

		res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");*/
		
		// Sale
/*		String[] cre = { "19", "19", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.19", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());
*/
		// Sale
/*		String [] cre = new String[] { "20", "20", "POS BRAVO v1.0",
				card2.substring(m5.start() + 3, m5.end() - 3),
				card2.substring(m4.start() + 2, m4.end() - 2), "10.20",
				"Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());*/
		
		// Sale
	/*	String[] cre = { "21", "21", "POS BRAVO v1.0",
				card.substring(m1.start() + 3, m1.end() - 3),
				card.substring(m.start() + 2, m.end() - 2), "10.21", "Swiped" };

		Response test = new Response(6, cre);

		System.out.println(test.getXML());
		System.out.println(test.getResponse());*/
		// Batch
		String[] bat = { "Credit", "BatchSummary", "merchantID2",
				"POS BRAVO v1.0" };

		Response res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");

		String info[] = resExtract.getCodes(res.getResponse());
		bat = new String[] { "Credit", "BatchClose", "merchantID2",
				"POS BRAVO v1.0", info[5], info[6], info[7], info[8], info[9],
				info[10], info[11], info[12], info[13], info[14], info[15] };

		res = new Response(12, bat);

		System.out.println(res.getXML());
		System.out.println(res.getResponse());
		System.out.println("_________________________________");

		}
}

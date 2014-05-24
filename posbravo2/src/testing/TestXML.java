package testing;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

import pointOfSale.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestXML extends JFrame {
	
	public static void main(String [] args){
	
		File f = new File("C:\\Users\\first is the pc name\\Documents\\test.txt");
		
		System.out.print(f.canRead());
		FileInputStream fs;
		try {
			fs = new FileInputStream(f);
			int a = 1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			FileOutputStream fd = new FileOutputStream(f);
			while(true){
			fd.write(300000);
			}
			
		}
		catch(Exception e){
			
		}
		
	}

		/*
		Scanner inp = new Scanner(System.in);
		
		double x = inp.nextDouble();
		double y = inp.nextDouble();
		
		System.out.println(((double)((int) (x * 100) + (int) (y * 100)))/100);
		
		for(int z = 0; z < 200; z++){
		//	String temp = String.valueOf(z);
		//	System.out.print(temp.substring(0, temp.length()-2) + "." + temp.substring(temp.length()-2));
		//	System.out.println((double)z/100);
		//	double q = (double)z/100;
		//	System.out.println("*" + q*100);
			System.out.println((double)(z + z)/100);
		}
		
		
	}
		/*double value = 0;
		try {
			Scanner read = new Scanner(new File("Files/Receipt/2013-12-08_16.32.19"));
			while(read.hasNextLine()){
				String temp = read.nextLine();
				if(temp.contains("Approved")){
					String temp2[] = temp.split(" ");
					value = Double.parseDouble(temp2[0].substring(1));

				}
			}
			DecimalFormat format = new DecimalFormat("0.00");
			if(0.02 > 0)
			System.out.print(format.format(value));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*//*
		System.out.println(new testNum().getNum2());
		testNum a = new testNum();
		a.incNum();
		System.out.println(new testNum().getNum2());
		System.out.println(a.getNum2());

		System.out.println(new testNum().getNum());
		System.out.println(a.getNum());
	}

	
	/*
	JTextField display = new JTextField();
	String swipe = "";
	int deleter = 0;
	public static void main(String [] args){
		System.out.print("11111".substring(0, 3));
		if(true)return;
		TestXML a = new TestXML();
		a.setSize(100, 100);
		a.setVisible(true);
	}

	public TestXML(){
		setLayout(new GridLayout(1,2));
		KeyBarsListener listen = new KeyBarsListener();
		
		add(display);
		display.addKeyListener(listen);
		display.addMouseListener(new MouseAdapter(){
			 public void mouseEntered( MouseEvent e ) {
				 display.setCaretPosition(display.getText().length());
			      // called when the pointer enters the applet's rectangular area
			   }
			   public void mouseExited( MouseEvent e ) {

					 display.setCaretPosition(display.getText().length());
			      // called when the pointer leaves the applet's rectangular area
			   }
			   public void mouseClicked( MouseEvent e ) {

					 display.setCaretPosition(display.getText().length());
			      // called after a press and release of a mouse button
			      // with no motion in between
			      // (If the user presses, drags, and then releases, there will be
			      // no click event generated.)
			   }
			   public void mousePressed( MouseEvent e ) {  // called after a button is pressed down

					 display.setCaretPosition(display.getText().length());
			      // "Consume" the event so it won't be processed in the
			      // default manner by the source which generated it.
			      e.consume();
			   }
			   public void mouseReleased( MouseEvent e ) {  // called after a button is released

					 display.setCaretPosition(display.getText().length());
					deleter = 1;
					 
			   }
			   public void mouseMoved( MouseEvent e ) {  // called during motion when no buttons are down

					 display.setCaretPosition(display.getText().length());
			      e.consume();
			   }
			   public void mouseDragged( MouseEvent e ) {  // called during motion with buttons down

					 display.setCaretPosition(display.getText().length());
			      e.consume();
			   }
		});
	}
	private class KeyBarsListener extends KeyAdapter {
		
		public void keyPressed(KeyEvent e){
				if( e.getKeyCode() >= KeyEvent.VK_0 || e.getKeyCode() <= KeyEvent.VK_0){
				
				}
				else{
					e.consume();
				}
		}
		public void keyTyped(KeyEvent e){
			if( e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9){
				
			}
			else{
				e.consume();
			}
			
			swipe += String.valueOf(e.getKeyChar());
			System.out.println(swipe);
		}

	/*public static void main(String[] args) {
		/*String a = null;
		if(a == null){
			if(a.equals("a") || a == null){
			
				System.out.print("Works");
			}
		}
		if(true)return;*/
		/*String checker [] = new String [9];
		checker[3] = "22.26";
		checker[8] = "30.25";
		int purchase = Integer.valueOf(checker[8].replace(".", ""));
		int authorize = Integer.valueOf(checker[3].replace(".", ""));
		int subt = purchase - authorize;
		//DecimalFormat df = new DecimalFormat("0.00");
		ReceiptPanel.addItem(String.valueOf(subt), "Remaining Balance");
		ReceiptPanel.saveReceipt();
		if(true)return;
		*/
		//Object retrn[] = new Object[]{true};
		//if((boolean)retrn[0] == true){
		//	System.out.println("IT WORKS");
		//	return;
		//}
		
		//ProcessPanel.closeReceipt("PROGRESS", new File("Files/Receipt/2013-10-25_09.49.37"));
		//StringBuilder str = new StringBuilder(null);

		
		
	/*	PrintWriter f;
		try {
			f = new PrintWriter(new File("Files/Receipt/2013-10-25_09.49.39"));
			f.println("aaaaa\naaaa");
			f.println("Baaa\naaa");
			f.close();
			
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		if(true)return;
		PrintStream s;
		Exception e = new Exception();
		try {
			s = new PrintStream(new File("lala"));
			e.printStackTrace(s);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String a = "1";
		String c = null;
		int b = 0;
		
		try{
			//b = c.length();
			c = a.substring(5, 10);
			a = "3";
		}
		catch(Exception e2){
			System.out.println("There is error");
			e2.printStackTrace();
		}
		System.out.println("Ok System doesn't crash");
		System.out.println(a);
		if(true)return;
		
		String swipe1 = "0~IPAD100KB|24~98AC18281702140C|1~33|2~EC5A3972BE3A7DD16559861151BAE7C5F961B63D9ED7076DF0C835F873FA9E48ABD0535C751C322E8EA493ED015F7A0DF3312600C8C4AA9E|3~FD1E93B24B1F14EC989506E6CC04E1C360948EDEC23FFBAFE724DD4C04B847D3E243466E7738ABCC|4~|6~%M4005550000000480^MANUAL ENTRY/^1512000000000000000?|7~;4005550000000480=1512000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000CB|12~00000000|";
		String temp1[] = {""};
		if(swipe1.contains("MANUAL ENTRY")){
			temp1[0] = "True";
		}
		else{
			temp1[0] = "False";
		}
		System.out.print(temp1[0]);
		if(true)return;
		
		String swipe = ";12312313=1231231?asfafasf";
		Scanner regex = new Scanner(swipe);
		System.out.println(swipe);
		String temp = regex.findInLine(";\\d+=\\d+\\?");
		System.out.println(temp);
		temp = temp.substring(1, temp.length()-1);
		System.out.println(temp);
		
if(true)return;
		String ref = "0";
		String card = "0~IPAD100KB|24~98AC18281702140C|1~33|2~2EE099E348918FB1BBFF1D5C0FEAEBA1FE02E62B303661AE804C5233A225A716D674C685DC92C0B9D3AD2BA2F1307063EFBB168239C98836|3~9E7952E11A1230CC702D7AA11BFB8F7A138A34C694E8D5986006AE1DC8BB5040EB0380F4B40D6BBB|4~|6~%M373953006001001^MANUAL ENTRY/^15120000000000000000?|7~;373953006001001=15120000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000C5|12~00000000|"
					
				;
		Pattern pregex = Pattern.compile("3~.*\\|4");
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12");
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		
		String [] data = {"CTranDetail", "3", "merchantID2"};
		
		Response res = new Response(9, data);
		
		System.out.println(res.getSoap());
		System.out.println(res.getResponse());
		System.out.println("____________________________________________");
		
		if(true)return;
//		System.out.print(m1.group());
		//String one[] = {"22", "22", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.09", "1.09", "123", "4 Corporate SQ", "30329"};
	//	String one[] = {"23", "23", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.08", "1.08", "1234", "1661 E. Camelback", "85016"};
//		String one[] = {"24", "24", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "1.10", "1.10", "123", "4 Corporate SQ", "30329"};
		
	/*	String four[] = {"16", "16", "POS BRAVO v1.0", "9500030000081C20001A", "2B11F45ABEE6A6B288A76FED3BBCCE63130970C742BDD607D75F09821AAF2C6482F1AB593E0A97BA", "2.00", "2.00"};
		//non encrypted cc 
		Response set = new Response();
		set.setIDnPas("merchantID2");*/
/*	
		//Testing For Manual Entry

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
				
				String three[] = {"23", info2[1], "POS BRAVO v1.0", info2[2], "1.08", info2[3], info2[4], info2[0], "merchantID1" };

				test = new Response(3, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");
*/		
		
/*		
	//Testing for Card Present(Swiped) Preauth and Preauthcapture
		
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
				
				String three[] = {"23", info2[1], "POS BRAVO v1.0", info2[2], "1.08", info2[3], info2[4], info2[0], "merchantID1" };

				test = new Response(3, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");
				
				//Standard Voidsale
		
				test = new Response(5, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");
*/
	
/*
 		//Testing for Card Present(Swiped) Sale
		String card = "0~IPAD100KB|24~98AC18281702140C|1~33|2~8388ED97A523FCCF909C5A8A21AB0253386326EEEE7557060C480624184D99956F7D5CB5697F9DA1E2213B2C0B3C1F0B83EC627718FAF629|3~82EFB6A5ECE71AB0710CABB5313910CB976A7048859723A01ACE85C2A19D42AE9BF1B5ABD18B3D44|4~|6~%M4005550000000480^MANUAL ENTRY/^1512000000000000000?|7~;4005550000000480=1512000000000000?|8~|9~00000000|10~000001|11~9500030000081C2000C6|12~00000000|"				;
		Pattern pregex = Pattern.compile("3~.*\\|4");
		Matcher m = pregex.matcher(card);
		m.find();
		System.out.println(m.group());
		Pattern pregex1 = Pattern.compile("11~.*\\|12");
		Matcher m1 = pregex1.matcher(card);
		m1.find();
		
				//Sale
				String yuk[] = {"25", "25", "POS BRAVO v1.0", card.substring(m1.start()+3, m1.end()-3), card.substring(m.start()+2, m.end()-2), "2.02", "123", "4 Corporate SQ", "30329"};

				Response test = new Response(6, yuk);

				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");

				//Reversal Voidsale
				String [] info2 = getCodes(test.getResponse());
				
				String three[] = {"25", info2[1], "POS BRAVO v1.0", info2[2], "2.02", info2[3], info2[4], info2[0], "merchantID1" };

				test = new Response(3, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");


				//Standard Voidsale
		
				test = new Response(5, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");

				//Adjust
				String []adj = new String[]{"25", info2[1], "POS BRAVO v1.0", info2[2], "2.13", "2.13", "", info2[0], "merchantID1"};
				
				test = new Response(7, adj);

				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");
*/
	 //  if(true) return;

//--------------------------------------------------------------------------------------------
				
				/*
				test = new Response(5, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");
			    */
				/*test = new Response(5, three);
				
				System.out.println(test.getXML());
				System.out.println(test.getResponse());
				System.out.println("____________________________________________");
				*/
				/*
				Response.batch = true;
				String twel [] = {"BatchSummary", "merchantID1"};
				
				Response test3 = new Response(12, twel);
				System.out.println(test3.getXML());
				System.out.println(test3.getResponse());
				System.out.println("____________________________________________");
				*/
		//		if(true)return;
				
		/*String 
		if(true)return;*/
		
/*		String []one = new String[]{"000001", "000001", "POS BRAVO v1.0", "6050110010027251161", "100.00", "Issue"};
		
		Response test1 = new Response(11, one);
		System.out.println(test1.getXML());
		System.out.println(test1.getResponse());
		System.out.println("____________________________________________");
		
		
		
		if(true)return;
		
		//adjust tips
		/*
		test = new Response(2, two);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());

		ref = getRefcode(test.getResponse());
		System.out.println("____________________________________________");
		*///void
		/*test = new Response(3, three);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/
		//encrypted cc with magtek ipad 100kb
		/*test = new Response(4, four);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");*/
		
		//adjust
	/*	String sev[] = {"0001", ref, "POS BRAVO v1.0", "KqaoIkv1k9wUbUmJ9wmtTKFWZhyPMas0IhESEAAjEAeDBw==", "0.05", "KfJ", "|15|410100700000", "VI0105", "395347306=TOKEN", "0.05", "1.00"};
		
		test = new Response(7, sev);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		
		//standard voidsale
		String fiv[] = {"0001", ref, "POS BRAVO v1.0", "KqaoIkv1k9wUbUmJ9wmtTKFWZhyPMas0IhESEAAjEAeDBw==", "1.05", "KfJ", "|15|410100700000", "VI0105", "395347306=TOKEN", "1.05"};
		
		test = new Response(5, fiv);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());

		String eigh[] = {"10", "10", "POS BRAVO v1.0", "4003000123456781", "1215", "1.05", "1.00", "", "", ""};
		
		test = new Response(8, eigh);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		ref = getRefcode(test.getResponse());
		
		String nine[] = {"10", ref, "POS BRAVO v1.0", "KqaoIkv1k9wUbUmJ9wmtTKFWZhyPMas0IhESEAAjEAeDBw==", "1.05", "KfJ", "|15|410100700000", "VI0105", "merchantID1"};
		
		test = new Response(9, nine);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		*/

	//}
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
	public static String getRefcode(String xml) {
	
	
		Scanner reader = new Scanner(xml);
		Scanner regex = null;
		String temp = null;

		
	
	
		while (reader.hasNextLine()) {
			String read = reader.nextLine();
			regex = new Scanner(read);
            if (read.contains("RefNo")) {
				temp = regex
						.findInLine("<RefNo>[\\da-zA-Z]+</RefNo>");
				temp = temp.substring("<RefNo>".length(),
						temp.length() - "</RefNo>".length());
				
            }
            
		}
		reader.close();
		
		return temp;
	}
	public static String test() throws ArrayIndexOutOfBoundsException{
		int a = 1;
		int b = 2;
		String array[] = new String[5];
		return array[8];
	}
}

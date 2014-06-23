package pointOfSale;

import java.util.Scanner;


//Returns card data in format as required

public class CardProcess {

	public CardProcess(){
		
	}
	
	public static String getTrack(String swipe) throws Exception{
		Scanner regex = new Scanner(swipe);
		String temp = regex.findInLine(";\\d+=\\d+\\?");
		if(temp == null){
			regex.close();
			throw new Exception();
		}
		temp = temp.substring(1, temp.length()-1);
		regex.close();
		return temp;
	}

	public static String[] getEncryption(String swipe, boolean debit) throws Exception{
		String [] temp = new String[5];
		
		if(swipe.contains("MANUAL ENTRY")){
			temp[0] = "Keyed";
		}
		else{
			temp[0] = "Swiped";
		}
		
		//Encryption Block
		Scanner regex = new Scanner(swipe);
		StringBuilder str = new StringBuilder(regex.findInLine("3~.*\\|4"));
		temp[1] = str.substring(2, str.length()-2);
		regex.close();
		
		//Encryption Key
		regex = new Scanner(swipe);
		str = str.replace(0, str.length(), regex.findInLine("11~.*\\|12"));
		temp[2] = str.substring(3, str.length()-3);
		regex.close();
		
		if(debit){
		//Encryption Pin Key
		regex = new Scanner(swipe);
		str = str.replace(0, str.length(), regex.findInLine("13~.*\\|14"));
		temp[3] = str.substring(3, str.length()-3);
		regex.close();

		//Encryption Pin Block
		regex = new Scanner(swipe);
		str = str.replace(0, str.length(), regex.findInLine("14~.*\\|"));
		temp[4] = str.substring(3, str.length()-3);
		regex.close();
		}
		return temp;
		
	}
}

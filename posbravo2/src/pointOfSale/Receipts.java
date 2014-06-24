package pointOfSale;

/**Receipts.java
 * 
 * Has an ArrayList with Receipt objects. Each Receipt has a userID, receiptNo, and amount.
 * Mostly for demo purposes, since ReceiptManager wasn't working. However, the functionality is there and can be easily expanded upon.
 * 
 * @author Helen Li
 */
import java.util.ArrayList;

public class Receipts {
	//attributes
	int userID;
	int receiptNo;
	double amount;
	
	private ArrayList<Receipts> myReceipts = new ArrayList<Receipts>();
	
	Receipts(){
		//making up new receipts here
		Receipts r1 = new Receipts(1111,111,10.00);
		Receipts r2 = new Receipts(1112,222,12.00);
		myReceipts.add(r1);
		myReceipts.add(r2);
	}
	
	Receipts (int rn, int i, double a) {
		receiptNo = rn;
		userID = i;
		amount = a;
	}
	
	//all 3 getters
	public int getReceiptNo() {
		return this.receiptNo;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public double getAmount() {
		return this.amount;
	}


	//need to override toString, or else the ArrayList will print out a memory address
	@Override
	public String toString() {
		return "Receipts [userID=" + userID + ", receiptNo=" + receiptNo
				+ ", amount=" + amount + "]";
	}
	
	/**
	 * searches through all Receipt objects by ticket ID
	 * @param i
	 * @return
	 */
	public String findReceiptByid(int i) {
		for (Receipts r : myReceipts) {
			 if(r.getReceiptNo()== i) {
				 System.out.println("The ticket ID with" +i +" exists");
				 System.out.println(r);
				 return r.toString();
			} 
		}
		return "Error: Not Found";
	}
	
	/**
	 * searches through all Receipt objects by user ID
	 * @param i
	 * @return
	 */
	public String findUserByid(int i) {
		for (Receipts r : myReceipts) {
			 if(r.getUserID()== i) {
				 System.out.println("The User ID with" +i +" exists");
				 System.out.println(r);
				 return r.toString();
			} 
		}
		return "Error: Not Found";
	}
	
	/**
	 * searches through all Receipt objects by amount
	 * @param i
	 * @return
	 */
	public String findReceiptByAmount(double i) {
		for (Receipts r : myReceipts) {
			 if(r.getAmount()== i) {
				 System.out.println("The ticket with amount" +i +" exists");
				 System.out.println(r);
				 return r.toString();
			} 
		}
		return "Error: Not Found";
	}

}

package pointOfSale;

import java.util.ArrayList;

public class Receipts {
	//attributes
	int id;
	int receiptNo;
	double amount;
	
	private static ArrayList<Receipts> myReceipts = new ArrayList<Receipts>();
	
	Receipts(){
		//making up new receipts here
		Receipts r1 = new Receipts(1111, 123,12);
		myReceipts.add(r1);
	}
	
	Receipts (int i, int rn, double a) {
		id = i;
		receiptNo = rn;
		amount = a;
		
		//myReceipts.add(new Receipts(this.id, this.receiptNo, this.amount));

	}
	
	public int getID() {
		System.out.println(this.id);
		return this.id;
		
	}

	@Override
	public String toString() {
		return "Receipts [id=" + id + ", receiptNo=" + receiptNo + ", amount="
				+ amount + "]";
	}
	
	public String findReceiptByid(int i) {
		for (Receipts r : myReceipts) {
			 if(r.getID()== i) {
				 System.out.println("The ID with" +i +" exists");
				 System.out.println(r);
				 return r.toString();
			} 
		}
		return "Not Found";
	}

	public static void main (String[] args) {
		
		
		Receipts rc = new Receipts();
		
		
		/*for (Receipts r: myReceipts) {
			System.out.println(r);
			
			r.getID();
			if(r.getID() == 1111) 
				 System.out.println("The ID is 1111");
		}*/
		

	}
	
}

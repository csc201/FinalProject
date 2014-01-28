package receipt;
import java.io.Serializable;
import java.util.ArrayList;

public class ReceiptLog implements Serializable {
	private static final long serialVersionUID = 1L;  //Default value, added to satisfy compiler
	
	private int receiptNum;
	private ArrayList<Receipt>receiptList;
	
	public ReceiptLog() {
		receiptList = new ArrayList<Receipt>();
		receiptNum = 1;
	}
	
	public void addReceipt(Receipt receipt) {
		int index = indexOf(receipt.getReceiptNum());
		
		if(index == -1) {
			receipt.setReceiptNum(receiptNum);
			receiptNum++;
			
			receiptList.add(receipt);
		}
		else {
			receiptList.remove(index);
			receiptList.add(index, receipt);
		}
	}
	public void removeReceipt(int index) {
		receiptList.remove(index);
	}
	public void removeAll() {
		receiptList.clear();
	}
	public Receipt getReceipt(int index) {
		return receiptList.get(index);
	}
	public int indexOf(int receiptNum) {
		int minIndex = 0;
		int maxIndex = receiptList.size()-1;
		int midIndex;
		
		while(maxIndex >= minIndex) {
			midIndex = (maxIndex + minIndex) / 2;
			
			if(receiptList.get(midIndex).getReceiptNum() < receiptNum)
				minIndex = midIndex+1;
			else if(receiptList.get(midIndex).getReceiptNum() > receiptNum)
				maxIndex = midIndex-1;
			else
				return midIndex;
		}
		return -1;
	}
}

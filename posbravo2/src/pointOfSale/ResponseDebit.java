package pointOfSale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ResponseDebit {
	private String merchantID /*"023358150511666""395347306=TOKEN"*/ , track2, soap, startDate, endDate, batchNo, itemCount, netBatch, creditCount, creditAmt, creditRetCount, creditRetAmt, debitPurCount, debitPurAmt, debitRetCount, debitRetAmt, entryType , cashback, encPin, dervk, cvv, addr, zip, tranType /*= "Credit"*/, tranCode, invoiceNo, refNo, memo, frequency /*= "OneTime"*/, recordNo, partialAuth /*"Allow"*/, accountNum, expDate, purchase, authorize, gratuity, authCode, acqRefData, processData, encryptedBlock, encryptedKey;
	private String result = "";
	
	public ResponseDebit(int type, String[] data, String tranType, String frequency, String partialAuth) {
		//onLoad();
		
		this.tranType = tranType;
		this.frequency = frequency;
		this.partialAuth = partialAuth;
		
		switch (type) {
		case 7:
			tranCode = data[0];
			tranType = "Debit";
			recordNo = "RecordNumberRequested";
			invoiceNo = data[1];
			refNo = data[2];
			memo = data[3];
			encryptedKey = data[4];
			encryptedBlock = data[5];
			purchase = data[6];
			cashback = data[7];
			encPin = data[8];
			dervk = data[9];
			merchantID = data[10];
			result = getResult1();
			break;
		}
	}

	private String getResult1() {
		String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
		temp += merchantID;
		temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
		temp += tranType;
		temp += "</TranType>\n\t\t<TranCode>";
		temp += tranCode;
		temp += "</TranCode>\n\t\t<InvoiceNo>";
		temp += invoiceNo;// "38";
		temp += "</InvoiceNo>\n\t\t<RefNo>";
		temp += refNo;// "38";
		temp += "</RefNo>\n\t\t<Memo>";
		temp += memo;
		temp += "</Memo>\n\t\t<Frequency>";
		temp += frequency;
		temp += "</Frequency>\n\t\t<RecordNo>";
		temp += recordNo;
		temp += "</RecordNo>";
		temp += "\n\t\t<Account>";
		temp += "\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>Swiped</AccountSource>";
		temp += "\n\t\t\t<EncryptedBlock>";
		temp += encryptedBlock;
		temp += "</EncryptedBlock>\n\t\t\t<EncryptedKey>";
		temp += encryptedKey;
		temp += "</EncryptedKey>\n\t\t</Account>";
		temp += "\n\t\t<Amount>\n\t\t\t<Purchase>";
		temp += purchase;
		temp += "</Purchase>";
		if (tranCode.equals("Sale")) {
			temp += "\n\t\t\t<CashBack>" + cashback + "</CashBack>";
		}
		temp += "\n\t\t</Amount>";
		temp += "\n\t\t<PIN>\n\t\t\t<PINBlock>";
		temp += encPin;
		temp += "</PINBlock>\n\t\t\t<DervdKey>";
		temp += dervk;
		temp += "</DervdKey>\n\t\t</PIN>";
		temp += "\n\t</Transaction>\n</TStream>";

		return temp;
	}
/*
	private void initProperties() {
		properties = new Properties();
		file = new File("Files/Properties/MercuryMerchantIDDev.properties");
		try {
			fileReader = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// InputStream fileReader2 =
		// getClass().getResourceAsStream("/Files/Properties/MercuryMerchantIDDev.properties");

		try {
			properties.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				fileReader = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void onLoad() {
		initProperties();
		this.tranType = properties.getProperty("tranType");
		this.frequency = properties.getProperty("frequency");
		this.partialAuth = properties.getProperty("partialAuth");
		this.timeout = Integer.parseInt(properties.getProperty("timeout"));

	}
*/
	public String getXML() {
		return this.result;
	}


}

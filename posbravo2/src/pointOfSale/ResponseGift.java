package pointOfSale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ResponseGift {
	private String merchantID /* "023358150511666""395347306=TOKEN" */,
			track2, soap, startDate, endDate, batchNo, itemCount, netBatch,
			creditCount, creditAmt, creditRetCount, creditRetAmt,
			debitPurCount, debitPurAmt, debitRetCount, debitRetAmt, entryType,
			cashback, encPin, dervk, cvv, addr, zip, tranType /* = "Credit" */,
			tranCode, invoiceNo, refNo, memo, frequency /* = "OneTime" */,
			recordNo, partialAuth /* "Allow" */, accountNum, expDate, purchase,
			authorize, gratuity, authCode, acqRefData, processData,
			encryptedBlock, encryptedKey;
	
	private String result = "";


	public ResponseGift(int type, String[] data, String tranType, String frequency, String partialAuth) {
		//onLoad();
		
		this.tranType = tranType;
		this.frequency = frequency;
		this.partialAuth = partialAuth;
		
		switch (type) {
		case 8:
			entryType = data[0];
			tranCode = data[1];
			merchantID = data[2];
			tranType = "PrePaid";
			invoiceNo = data[3];
			refNo = data[4];
			memo = data[5];
			encryptedKey = data[6];
			encryptedBlock = data[7];
			if (!tranCode.equals("Balance")) {
				purchase = data[8];
			}
			if (tranCode.contains("Void")) {
				authCode = data[9];
			}
			result = getResult1();
			break;
		}
	}

	private String getResult1() {
		String temp = "<TStream>\n\t<Transaction>\n\t\t<IpPort>9100</IpPort>\n\t\t<MerchantID>";
		temp += merchantID;
		temp += "</MerchantID>";
		temp += "\n\t\t<OperatorID>test</OperatorID>";
		temp += "\n\t\t<TranType>";
		temp += tranType;
		temp += "</TranType>\n\t\t<TranCode>";
		temp += tranCode;
		temp += "</TranCode>\n\t\t<InvoiceNo>";
		temp += invoiceNo;
		temp += "</InvoiceNo>\n\t\t<RefNo>";
		temp += refNo;
		temp += "</RefNo>\n\t\t<Memo>";
		temp += memo;
		temp += "</Memo>";
		temp += "\n\t\t<Account>\n\t\t\t";
		temp += "\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>";
		temp += entryType + "</AccountSource>";
		temp += "\n\t\t\t<EncryptedBlock>";
		temp += encryptedBlock;
		temp += "</EncryptedBlock>\n\t\t\t<EncryptedKey>";
		temp += encryptedKey;
		temp += "</EncryptedKey>\n\t\t</Account>";
		if (!tranCode.equals("Balance")) {
			temp += "\n\t\t<Amount>\n\t\t\t<Purchase>" + purchase;
			temp += "</Purchase>\n\t\t</Amount>";
		}
		if (tranCode.contains("Void")) {
			temp += "\n\t\t<AuthCode>" + authCode + "</AuthCode>";
		}
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

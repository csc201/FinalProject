package pointOfSale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ResponseBatch {
	private static String password, merchantID /* "023358150511666""395347306=TOKEN" */,
			track2, soap, startDate, endDate, batchNo, itemCount, netBatch,
			creditCount, creditAmt, creditRetCount, creditRetAmt,
			debitPurCount, debitPurAmt, debitRetCount, debitRetAmt, entryType,
			cashback, encPin, dervk, cvv, addr, zip, tranType /* = "Credit" */,
			tranCode, invoiceNo, refNo, memo, frequency /* = "OneTime" */,
			recordNo, partialAuth /* "Allow" */, accountNum, expDate, purchase,
			authorize, gratuity, authCode, acqRefData, processData,
			encryptedBlock, encryptedKey;
	private String result = "", response = "";
	private Properties properties;
	private File file;
	private FileInputStream fileReader;
	private int timeout;

	public ResponseBatch(int type, String[] data) {
		onLoad();
		switch (type) {
		case 9:
			tranType = data[0];
			if (!tranType.equals("CBatch") && !tranType.equals("CAllDetail")
					&& !tranType.equals("CTranDetail")
					&& !tranType.equals("CAllGiftDetail")) {
				tranCode = data[1];
				merchantID = data[2];
				memo = data[3];
				if (tranCode.equals("BatchClose")) {
					batchNo = data[4];
					itemCount = data[5];
					netBatch = data[6];
					creditCount = data[7];
					creditAmt = data[8];
					creditRetCount = data[9];
					creditRetAmt = data[10];
					debitPurCount = data[11];
					debitPurAmt = data[12];
					debitRetCount = data[13];
					debitRetAmt = data[14];
				}
				result = getResult1();
			} else if (tranType.equals("CAllDetail")
					|| tranType.equals("CAllGiftDetail")) {
				merchantID = data[1];
				invoiceNo = data[2];
				startDate = data[3];
				endDate = data[4];
			} else if (tranType.equals("CTranDetail")) {
				merchantID = data[1];
				invoiceNo = data[2];
			}
		}
	}

	private String getResult1() {
		String temp = "<TStream>\n\t<Admin>\n\t\t<MerchantID>";
		temp += merchantID;
		temp += "</MerchantID>";
		if (tranCode.equals("BatchClose")) {
			temp += "\n\t\t<OperatorID>test</OperatorID>";
		}
		temp += "\n\t\t<TranCode>" + tranCode;
		temp += "</TranCode>";
		if (tranCode.equals("BatchClose")) {
			temp += "\n\t\t<BatchNo>" + batchNo + "</BatchNo>";
			temp += "\n\t\t<BatchItemCount>" + itemCount + "</BatchItemCount>";
			temp += "\n\t\t<NetBatchTotal>" + netBatch + "</NetBatchTotal>";
			temp += "\n\t\t<CreditPurchaseCount>" + creditCount
					+ "</CreditPurchaseCount>";
			temp += "\n\t\t<CreditPurchaseAmount>" + creditAmt
					+ "</CreditPurchaseAmount>";
			temp += "\n\t\t<CreditReturnCount>" + creditRetCount
					+ "</CreditReturnCount>";
			temp += "\n\t\t<CreditReturnAmount>" + creditRetAmt
					+ "</CreditReturnAmount>";
			temp += "\n\t\t<DebitPurchaseCount>" + debitPurCount
					+ "</DebitPurchaseCount>";
			temp += "\n\t\t<DebitPurchaseAmount>" + debitPurAmt
					+ "</DebitPurchaseAmount>";
			temp += "\n\t\t<DebitReturnCount>" + debitRetCount
					+ "</DebitReturnCount>";
			temp += "\n\t\t<DebitReturnAmount>" + debitRetAmt
					+ "</DebitReturnAmount>";
		}
		temp += "\n\t\t<Memo>";
		temp += memo;
		temp += "</Memo>\n\t</Admin>";
		temp += "\n</TStream>";

		return temp;
	}
	public void setIDnPas(String id){
		initProperties();
		//for voidsale, reference appropriate ids based on merchantID on the sent receipt 
		if(id.equals("395347306=TOKEN")){
			id = "merchantID1";
		}
		else if(id.equals("395347308=E2ETKN")){
			id = "merchantID2";
		}
		
		this.merchantID = properties.getProperty(id);
		System.out.println(merchantID);
		String temp = id.substring(id.length()-1);
		
		temp = "password" + temp;
		this.password = properties.getProperty(temp);
		System.out.println(password);
		
	}
	
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

	public String getXML() {
		return this.result;
	}



}

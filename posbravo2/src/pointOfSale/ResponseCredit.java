package pointOfSale;


public class ResponseCredit {
	
	private String merchantID /*"023358150511666""395347306=TOKEN"*/ , track2,   entryType , cashback, encPin, dervk, cvv, addr, zip, tranType /*= "Credit"*/, tranCode, invoiceNo, refNo, memo, frequency /*= "OneTime"*/, recordNo, partialAuth /*"Allow"*/, accountNum, expDate, purchase, authorize, gratuity, authCode, acqRefData, processData, encryptedBlock, encryptedKey;
	private String result = "";
	
	 public ResponseCredit()
	    {
	    
	    }
		
	public ResponseCredit(int type__, String[] data, String tranType, String frequency, String partialAuth) {
	//	onLoad();
		this.tranType = tranType;
		this.frequency = frequency;
		this.partialAuth = partialAuth;
		
		switch (type__) {
		
		//PreAuth/Return For Manual
		case 0:
			tranCode = "PreAuth";
			if(data[0].equals("Return")){
				tranCode = data[0];
			}
			invoiceNo = data[1];
			refNo = data[2];
			memo = data[3];
			recordNo = "RecordNumberRequested";
			accountNum  = data[4];
			expDate = data[5];
			purchase = data[6];
			authorize = data[7];
			merchantID = data[8];
			result = getResult0();
			break;
			
		//PreAuth/Return For Non-Encrypted
		case 1:
			tranCode = "PreAuth";
			if(data[0].equals("Return")){
				tranCode = data[0];
			}
			invoiceNo = data[1];
			refNo = data[2];
			memo = data[3];
			recordNo = "RecordNumberRequested";
			track2 = data[4];
			purchase = data[5];
			authorize = data[6];
			merchantID = data[7];
			result = getResult1();
			break;
			
		//PreAuth/Return For Encrypted Reader
		case 2:
			tranCode = "PreAuth";
			if(data[0].equalsIgnoreCase("Return")){
				tranCode = "ReturnByRecordNo";
				invoiceNo = data[1];
				refNo = data[2];
				memo = data[3];
				recordNo = data[4];
				authorize = data[5];
				merchantID = data[6];
				result = getResultRec2(); 
				return;
			}
			invoiceNo = data[1];
			refNo = data[2];
			memo = data[3];
			recordNo = "RecordNumberRequested";
			encryptedKey = data[4];
			encryptedBlock = data[5];
			purchase = data[6];
			authorize = data[7];
			gratuity = "0.0";
			entryType = data[8];
			merchantID = data[9];
			/*if(entryType.equals("Keyed")){
				address and/or zip
			}*/
			result = getResult2();
			break;
			
		case 3:
			tranCode = "PreAuthCaptureByRecordNo";
			invoiceNo = data[0];
			refNo = data[1];
			recordNo = data[2];
			purchase = data[3];
			authorize = data[4];
			gratuity = data[5];
			authCode = data[6];
			acqRefData = data[7];
			merchantID = data[8];
			result = getResult3();
			break;

		// Voidsale
		case 4:
			tranCode = "VoidSaleByRecordNo";
			if(data[0].equals("VoidReturn")){
				tranCode = "VoidReturnByRecordNo";
			}
			invoiceNo = data[1];
			refNo = data[2];
			memo = data[3];
			recordNo = data[4];
			purchase = data[5];
			acqRefData = data[6];
			processData = data[7];
			authCode = data[8];
			merchantID = data[9];
			if (data[0].equals("Standard") || data[0].equals("VoidReturn")) {
				result = getResult5();
			} else {
				result = getResult4();
			}
			break;

		// Credit Sale
		case 5:
			tranCode = "Sale";
			invoiceNo = data[0];
			refNo = data[1];
			memo = data[2];
			recordNo = "RecordNumberRequested";
			encryptedKey = data[3];
			encryptedBlock = data[4];
			purchase = data[5];
			entryType = data[6];
			merchantID = "merchantID2";
			result = getResult6();
			break;

		case 6:
			tranCode = "AdjustByRecordNo";
			invoiceNo = data[0];
			refNo = data[1];
			memo = data[2];
			recordNo = data[3];
			purchase = data[4];
			authorize = data[5];
			gratuity = data[6];
			authCode = data[7];
			merchantID = data[8];
			result = getResult7();
			break;



		}

	}
		
	/*	 private void initProperties() {
				properties = new Properties();
				file = new File("Files/Properties/MercuryMerchantIDDev.properties");
				try {
					fileReader  = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//InputStream fileReader2 = getClass().getResourceAsStream("/Files/Properties/MercuryMerchantIDDev.properties");

				try{
				properties.load(fileReader);}catch(IOException e){
					e.printStackTrace();}
				finally{try{fileReader.close();fileReader = null;
				}
				catch(IOException e){
					e.printStackTrace();}}
		 }*/
		/* private void onLoad() {
		    initProperties();
			this.tranType = properties.getProperty("tranType");
			this.frequency = properties.getProperty("frequency");
			this.partialAuth = properties.getProperty("partialAuth");
			this.timeout = Integer.parseInt(properties.getProperty("timeout"));
		
		}*/
		 
		 //<?xml version="1.0"?> <TStream>  <Transaction>   <MerchantID>395347308=E2ETKN</MerchantID>   <TranType>Credit</TranType>   <TranCode>PreAuth</TranCode>   <InvoiceNo>16</InvoiceNo>   <RefNo>16</RefNo> //use RefNo=InvoiceNo on PreAuth requests   <Memo>MPS Example XML v1.0</Memo>         <PartialAuth>Allow</PartialAuth> //Required to "Allow" partial approvals   <Frequency>OneTime</Frequency> //use to request a Token for "one time" use(6 months)   <RecordNo>RecordNumberRequested</RecordNo>   <Account> //use for encrypted data elements in place of Track1 or Track2    <EncryptedFormat>MagneSafe</EncryptedFormat>   <AccountSource>Swiped</AccountSource>   <EncryptedBlock>F40DDBA1F645CC8DB85A6459D45AFF8002C244A0F74402B479 ABC9915EC9567C81BE99CE4483AF3D</EncryptedBlock> //for E2E (P2PE), always use Track2 block <EncryptedKey>9012090B01C4F200002B</EncryptedKey>  </Account>   <Amount>    <Purchase>2.00</Purchase> //Purchase=Authorize on request    <Authorize>2.00</Authorize>   </Amount>  </Transaction> </TStream> 

		 private String getResult0()
		 {
			 String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
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
			 temp += "</Memo>\n\t\t<Frequency>";
			 temp += frequency;
			 temp += "</Frequency>\n\t\t<RecordNo>";
			 temp += recordNo;
			 temp += "</RecordNo>\n\t\t<PartialAuth>";
			 temp += partialAuth;
			 temp += "</PartialAuth>\n\t\t<Account>\n\t\t\t<AcctNo>";
			 temp += accountNum;
			 temp += "</AcctNo>\n\t\t\t<ExpDate>";
			 temp += expDate;
			 temp += "</ExpDate>\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>";
			 temp += purchase;
			 temp += "</Purchase>\n\t\t\t<Authorize>";
			 temp += authorize;
			 temp += "</Authorize>\n\t\t</Amount>";
			 temp+= "\n\t</Transaction>\n</TStream>";

			 return temp;
		 }

		private String getResult1()
		{
			String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
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
			temp += "</Memo>\n\t\t<Frequency>";
			temp += frequency;
			temp += "</Frequency>\n\t\t<RecordNo>";
			temp += recordNo;
			temp += "</RecordNo>\n\t\t<PartialAuth>";
			temp += partialAuth;
			temp += "</PartialAuth>\n\t\t<Account>\n\t\t\t<Track2>";//\n\t\t\t<AcctNo>";
			temp += track2;//accountNum;
			temp += "</Track2>"; //"</AcctNo>\n\t\t\t<ExpDate>";
			//temp += expDate;
			temp += /*</ExpDate>*/"\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>";
			temp += purchase;
			temp += "</Purchase>\n\t\t\t<Authorize>";
			temp += authorize;
			temp += "</Authorize>\n\t\t</Amount>";
			temp+= "\n\t</Transaction>\n</TStream>";
			
			return temp;
		}
		//for magtek ipad 100kb
		private String getResult2()
		{
			String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
			temp += merchantID;
			temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
			temp += tranType;
			temp += "</TranType>\n\t\t<TranCode>";
			temp += tranCode;
			temp += "</TranCode>\n\t\t<InvoiceNo>";
			temp += invoiceNo;//"38";
			temp += "</InvoiceNo>\n\t\t<RefNo>";
			temp += refNo;//"38";
			temp += "</RefNo>\n\t\t<Memo>";
			temp += memo;
			temp += "</Memo>\n\t\t<PartialAuth>";
			temp += partialAuth;
			temp += "</PartialAuth>\n\t\t<Frequency>";
			temp += frequency;
			temp += "</Frequency>\n\t\t<RecordNo>";
			temp += recordNo;
			temp += "</RecordNo>";
			temp += "\n\t\t<Account>";
			temp += "\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>";
			temp += entryType + "</AccountSource>";
			temp += "\n\t\t\t<EncryptedBlock>";
			temp += encryptedBlock;
			temp += "</EncryptedBlock>\n\t\t\t<EncryptedKey>";
			temp += encryptedKey;
			temp += "</EncryptedKey>\n\t\t</Account>";
			temp += "\n\t\t<Amount>\n\t\t\t<Purchase>";
			temp += purchase;
			temp += "</Purchase>\n\t\t\t<Authorize>";
			temp += authorize;
			temp += "</Authorize>\n\t\t</Amount>";
			temp += "\n\t</Transaction>\n</TStream>";
			
			return temp;
		}
		private String getResult3()
		{
			String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
			temp += merchantID;
			temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
			temp += tranType;
			temp += "</TranType>\n\t\t<TranCode>";
			temp += tranCode;
			temp += "</TranCode>\n\t\t<PartialAuth>";
			temp += partialAuth;
			temp += "</PartialAuth>\n\t\t<InvoiceNo>";
			temp += invoiceNo;
			temp += "</InvoiceNo>\n\t\t<RefNo>";
			temp += refNo;
			temp += "</RefNo>\n\t\t<RecordNo>";
			temp += recordNo;
			temp += "</RecordNo>\n\t\t<Frequency>";
			temp += frequency;
			temp += "</Frequency>\n\t\t<Amount>\n\t\t\t<Purchase>";
			temp += purchase;
			temp += "</Purchase>\n\t\t\t<Authorize>";
			temp += authorize;
			temp += "</Authorize>\n\t\t\t<Gratuity>";
			temp += gratuity;
			temp += "</Gratuity>\n\t\t</Amount>\n\t\t<TranInfo>\n\t\t\t<AuthCode>";
			temp += authCode;
			temp += "</AuthCode>\n\t\t\t<AcqRefData>";
			temp += acqRefData;
			temp += "</AcqRefData>\n\t\t</TranInfo>\n\t</Transaction>\n</TStream>";
			
			return temp;
		}
		
		private String getResult4()
		{
			String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
			temp += merchantID;
			temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>";
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
			temp += "</Memo>\n\t\t<RecordNo>";
			temp += recordNo;
			temp += "</RecordNo>\n\t\t<Frequency>";
			temp += frequency;
			temp += "</Frequency>\n\t\t<Amount>\n\t\t\t<Purchase>";
			temp += purchase;
			temp += "</Purchase>\n\t\t</Amount>\n\t\t<TranInfo>\n\t\t\t<AcqRefData>";
			temp += acqRefData;
			temp += "</AcqRefData>\n\t\t\t<ProcessData>";
			temp += processData;
			temp += "</ProcessData>\n\t\t\t<AuthCode>";
			temp += authCode;
			temp += "</AuthCode>\n\t\t</TranInfo>\n\t</Transaction>\n</TStream>";
			
			return temp;
		}


		private String getResult5()
		{
			String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
			temp += merchantID;
			temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
			temp += tranType;
			temp += "</TranType>\n\t\t<TranCode>";
			temp += tranCode;
			temp += "</TranCode>\n\t\t<InvoiceNo>";
			temp += invoiceNo;
			temp += "</InvoiceNo>\n\t\t<RefNo>";
			temp += refNo;
			temp += "</RefNo>\n\t\t<Memo>";
			temp += memo;
			temp += "</Memo>\n\t\t<RecordNo>";
			temp += recordNo;
			temp += "</RecordNo>\n\t\t<Frequency>";
			temp += frequency;
			temp += "</Frequency>\n\t\t<Amount>\n\t\t\t<Purchase>";
			temp += purchase;
			temp += "</Purchase>\n\t\t</Amount>\n\t\t\t<AuthCode>";
			temp += authCode;
			temp += "</AuthCode>\n\t</Transaction>\n</TStream>";
			
			return temp;
		}
		
		

		
		//for magtek ipad 100kb Credit Sale
			private String getResult6()
			{
				String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
				temp += merchantID;
				temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
				temp += tranType;
				temp += "</TranType>\n\t\t<TranCode>";
				temp += tranCode;
				temp += "</TranCode>\n\t\t<InvoiceNo>";
				temp += invoiceNo;//"38";
				temp += "</InvoiceNo>\n\t\t<RefNo>";
				temp += refNo;//"38";
				temp += "</RefNo>\n\t\t<Memo>";
				temp += memo;
				temp += "</Memo>\n\t\t<PartialAuth>";
				temp += partialAuth;
				temp += "</PartialAuth>\n\t\t<Frequency>";
				temp += frequency;
				temp += "</Frequency>\n\t\t<RecordNo>";
				temp += recordNo;
				temp += "</RecordNo>";
				temp += "\n\t\t<Account>";
				temp += "\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>";
				temp += entryType + "</AccountSource>";
				temp += "\n\t\t\t<EncryptedBlock>";
				temp += encryptedBlock;
				temp += "</EncryptedBlock>\n\t\t\t<EncryptedKey>";
				temp += encryptedKey;
				temp += "</EncryptedKey>\n\t\t</Account>";
				temp += "\n\t\t<Amount>\n\t\t\t<Purchase>";
				temp += purchase;
				temp += "</Purchase>\n\t\t</Amount>";
				temp += "\n\t</Transaction>\n</TStream>";
				
				return temp;
			}
			private String getResult7()
			{
				String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
				temp += merchantID;
				temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
				temp += tranType;
				temp += "</TranType>\n\t\t<TranCode>";
				temp += tranCode;
				temp += "</TranCode>\n\t\t<InvoiceNo>";
				temp += invoiceNo;
				temp += "</InvoiceNo>\n\t\t<RefNo>";
				temp += refNo;
				temp += "</RefNo>\n\t\t<Memo>";
				temp += memo;
				temp += "</Memo>\n\t\t<RecordNo>";
				temp += recordNo;
				temp += "</RecordNo>\n\t\t<Frequency>";
				temp += frequency;
				temp += "</Frequency>\n\t\t<Amount>\n\t\t\t<Purchase>";
				temp += purchase;
				temp += "</Purchase>\n\t\t\t<Authorize>";
				temp += authorize;	
				temp +=	"</Authorize>";
				temp += "\n\t\t\t<Gratuity>" + gratuity + "</Gratuity>";		
		        temp += "\n\t\t</Amount>";
				temp += "\n\t\t\t<AuthCode>" + authCode + "</AuthCode>";
				temp += "\n\t</Transaction>\n</TStream>";
				return temp;
				
			}
			private String getResultRec2()
			{
				String temp = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>";
				temp += merchantID;
				temp += "</MerchantID>\n\t\t<OperatorID>test</OperatorID>\n\t\t<TranType>";
				temp += tranType;
				temp += "</TranType>\n\t\t<TranCode>";
				temp += tranCode;
				temp += "</TranCode>\n\t\t<PartialAuth>";
				temp += partialAuth + "</PartialAuth>";		
				temp +=	"\n\t\t<InvoiceNo>";
				temp += invoiceNo;//"38";
				temp += "</InvoiceNo>\n\t\t<RefNo>";
				temp += refNo;//"38";
				temp += "</RefNo>\n\t\t<Memo>";
				temp += memo;
				temp += "</Memo>\n\t\t<Frequency>";
				temp += frequency;
				temp += "</Frequency>\n\t\t<RecordNo>";
				temp += recordNo;
				temp += "</RecordNo>";
				temp += "\n\t\t<Amount>\n\t\t\t<Purchase>";
				temp += authorize;
				temp += "</Purchase>\n\t\t</Amount>";
				temp += "\n\t</Transaction>\n</TStream>";
				
				return temp;
			}
		
		

		public String getXML() {
			return this.result;
		}
		
		
		
	
}

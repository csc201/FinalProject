package receipt;

import java.io.Serializable;

/**
 * This is a simple container class for a merchant object to be used as part of a receipt managing application
 * for my final exam in CSC 201. It is meant to store relevant data for a typical merchant (Name, phone, address...)
 * and provide it to the receipt for display.
 * 
 * @author Stephen Collins
 *
 */
public class Merchant implements Serializable {
	private static final long serialVersionUID = 1L; //Default value
	private String merchantName;
	private String merchantType;
	private Address merchantAddress;
	private String merchantPhone;
	
	/**
	 * Instantiates the Merchant object with a name, phone number, Address object and logo image
	 * 
	 * @param merchantName
	 * @param merchantPhone
	 * @param merchantAddress
	 * @param merchantLogo
	 */
	Merchant(String merchantName, String merchantType, String merchantPhone, Address merchantAddress) {
		this.merchantName = merchantName;
		this.merchantType = merchantType;
		this.merchantPhone = merchantPhone;
		this.merchantAddress = merchantAddress;
	}
	/**
	 * Returns the merchant name
	 * 
	 * @return
	 */
	public String getName() { return merchantName; }
	/**
	 * Sets the merchant name to a new String
	 * 
	 * @param merchantName
	 */
	public void setName(String merchantName) { this.merchantName = merchantName; }
	/**
	 * Returns the type of business run by this merchant (Restaurant, hotel, etc.)
	 * 
	 * @return
	 */
	public String getType() { return merchantType; }
	/**
	 * Sets the type of business run by this merchant (Restaurant, hotel, etc.) to a new String value
	 * 
	 * @param merchantType
	 */
	public void setType(String merchantType) { this.merchantType = merchantType; }
	/**
	 * Returns the merchant phone number
	 * 
	 * @return
	 */
	public String getPhone() { return merchantPhone; }
	/**
	 * Sets the merchant phone number to a new String value
	 * 
	 * @param merchantPhone
	 */
	public void setPhone(String merchantPhone) { this.merchantPhone = merchantPhone; }
	/**
	 * Returns the merchant Address object
	 * 
	 * @return
	 */
	public Address getAddress() { return merchantAddress; }
	/**
	 * Sets the merchant address to a new Address object
	 * 
	 * @param merchantAddress
	 */
	public void setAddress(Address merchantAddress) { this.merchantAddress = merchantAddress; }
	/**
	 * Returns a String description of the Merchant object
	 */
	public String toString() {
		return "Merchant Name: " + merchantName + "\n"
				+ "Merchant Phone: " + merchantPhone + "\n"
				+ "Merchant Address:\n"
				+ merchantAddress.toString();
	}
	
}

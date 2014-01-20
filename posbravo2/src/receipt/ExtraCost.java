package receipt;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Simple container class used to store additions to a MenuItem object. Stores the name of the addition,
 * and the dollar amount of the addition.
 * 
 * @author Stephen Collins
 *
 */
public class ExtraCost implements Serializable {
	private static final long serialVersionUID = 1L; //Default value
	private String name;
	private BigDecimal amount;
	
	/**
	 * Instantiates the Extra object with a String name and BigDecimal amount, created using the argument 
	 * int value.
	 * 
	 * @param name
	 * @param amount
	 */
	ExtraCost(String name, int amount) { this(name, new BigDecimal(amount)); }
	/**
	 * Instantiates the Extra object with a String name and BigDecimal amount, created using the argument 
	 * String value.
	 * 
	 * @param name
	 * @param amount
	 */
	ExtraCost(String name, String amount) { this(name, new BigDecimal(amount)); }
	/**
	 * Instantiates the Extra object with a String name and BigDecimal amount.
	 * 
	 * @param name
	 * @param amount
	 */
	ExtraCost(String name, BigDecimal amount) {
		this.name = name;
		this.amount = amount;
	}
	/**
	 * Returns the name of the Extra object
	 * 
	 * @return
	 */
	public String getName() { return name; }
	/**
	 * Sets the name of the extra object to a new String value
	 * 
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	/**
	 * Returns the BigDecimal dollar amount for this Extra object
	 * 
	 * @return
	 */
	public BigDecimal getAmount() { return amount; }
	/**
	 * Sets the dollar amount for this Extra object to a new BigDecimal value, created using the argument
	 * int value
	 * 
	 * @param amount
	 */
	public void setAmount(int amount) { setAmount(new BigDecimal(amount)); }
	/**
	 * Sets the dollar amount for this Extra object to a new BigDecimal value, created using the argument
	 * String value
	 * 
	 * @param amount
	 */
	public void setAmount(String amount) { setAmount(new BigDecimal(amount)); }
	/**
	 * Sets the dollar amount for this Extra object to a new BigDecimal value
	 * 
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) { this.amount = amount; }
	/**
	 * Returns a String description of this Extra object
	 */
	public String toString() { 
		return "Name: " + name + "\n"
				+ "Amount: " + amount + "\n";
	}
}

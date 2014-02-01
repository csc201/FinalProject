package receipt;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is part of my final exam projects and is a container class for items on a merchant menu
 * 
 * @author Stephen Collins
 * 
 */
public class MenuItem implements Serializable {
	private static final long serialVersionUID = 1L; //Default value
	private String name;
	private BigDecimal price;
	private ArrayList<ExtraCost> extraList;
	private String discountPercent;
	private BigDecimal discount;
	private boolean hasDiscount;
	private int splitCount;
	
	/**Instantiates the menu item class with a new name and price using the integer argument to create a
	 * BigDecimal object
	 * 
	 * @param name
	 * @param price
	 */
	public MenuItem(String name, int price) { this(name, new BigDecimal(price)); }
	/**
	 * Instantiates the menu item class with a new name and a double value price, which is converted to a 
	 * BigDecimal object
	 * 
	 * @param name
	 * @param price
	 */
	public MenuItem(String name, String price) { this(name, new BigDecimal(price)); }
	/**
	 * Instantiates the menu item class with a new name and price using a BigDecimal object for the price
	 * 
	 * @param name
	 * @param price
	 */
	MenuItem(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
		extraList = new ArrayList<ExtraCost>();
		discountPercent = "0";
		discount = new BigDecimal(1);
		hasDiscount = false;
		splitCount = 1;
	}
	/**
	 * Returns the MenuItem name
	 * 
	 * @return
	 */
	public String getName() { return name; }
	/**
	 * Sets the MenuItem name to a new String value
	 * 
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	/**
	 * Returns the MenuItem price
	 * 
	 * @return
	 */
	public BigDecimal getPrice() { return price; }
	/**
	 * Sets the MenuItem price to a new BigDecimal value
	 * 
	 * @param price
	 */
	public void setPrice(BigDecimal price) { this.price = price; }
	/**
	 * Sets the MenuItem price to a new BigDecimal value, created from the argument int value
	 * 
	 * @param price
	 */
	public void setprice(int price) { this.price = new BigDecimal(price); }
	/**
	 * Sets the MenuItem price to a new BigDecimal value, created from the argument double value
	 * 
	 * @param price
	 */
	public void setPrice(String price) { this.price = new BigDecimal(price); }
	/**
	 * Adds a modification to the MenuItem which will increase the final price.
	 * The item price and all extras will be summed before a discount is applied.
	 * 
	 * @param name
	 * @param value
	 */
	public void addExtra(ExtraCost extra) { extraList.add(extra); }
	/**
	 * Removes the extra modification at the argument index
	 * 
	 * @param index
	 */
	public void removeExtra(int index) { extraList.remove(index); }
	/**
	 * Returns the ArrayList containing this MenuItem's Extra objects
	 * 
	 * @return
	 */
	public Collection<ExtraCost> getExtraList() { return extraList; }
	/**
	 * Returns true if ExtraCost objects have been added to this MenuItem
	 * 
	 * @return
	 */
	public boolean hasExtraCosts() { return extraList.size() > 0; }
	/**
	 * Returns the number of extra costs added to this MenuItem
	 * 
	 * @return
	 */
	public int getExtraCount() { return extraList.size(); }
	/**
	 * Returns a String value equal to the discount as a percentage
	 * 
	 * @return
	 */
	public String getDiscountPercent() { return discountPercent; }
	/**
	 * Sets a discount for this MenuItem. The name is the type of discount (Employee, Preferred Customer, etc.)
	 * and the int argument is the percentage value of the discount.
	 * 
	 * @param discountName
	 * @param discountPercent
	 */
	public void setDiscount(String discountPercent) {
		this.discountPercent = discountPercent; 
		discount = new BigDecimal(100).subtract(new BigDecimal(discountPercent));
		discount = discount.divide(new BigDecimal(100));
		
		if(discount.intValue() == 1)
			hasDiscount = false;
		else
			hasDiscount = true;
	}
	/**
	 * Removes any discount currently set for this MenuItem.
	 */
	public void removeDiscount() {
		discount = new BigDecimal(1);
		discountPercent = "0"; 
		hasDiscount = false;
	}
	/**
	 * Returns true if this MenuItem currently has a discount set
	 * 
	 * @return
	 */
	public boolean hasDiscount() { return hasDiscount; }
	/**
	 * Sets an integer splitter for this MenuItem to simulate splitting the item between multiple customers.
	 * The final price of the MenuItem will be divided by the number of times this menu item has been split
	 * (1 by default).
	 * 
	 * @param splitCount
	 */
	public void split(int splitCount) { this.splitCount = splitCount; }
	/**
	 * Returns the number of times this MenuItems's final price is being split
	 * 
	 * @return
	 */
	public int getSplitCount() { return splitCount; }
	/**
	 * Returns true if this MenuItem has been split between multiple customers
	 * 
	 * @return
	 */
	public boolean isSplit() { return splitCount > 1; }
	/**
	 * Returns a String description of this MenuItem
	 */
	public String toString() { return generateString(); }
	/*
	 * Generates a String description of this MenuItem
	 */
	private String generateString() {
		String description = name + "\t\t" + price + "\n";
		for(ExtraCost m : extraList)
			description += "\t" + m.getName() + "\t" + m.getAmount() + "\n";
		if(hasDiscount())
			description += "\t" + "Discount " + "\t" + discountPercent + "%\n";
		if(isSplit())
			description += "\t" + "Split" + "\t" + splitCount + "\n";
		if(hasDiscount() || extraList.size() > 0)
		description += "Final" + "\t\t" + getFinalPrice();
		
		return description;
	}
	/**
	 * Returns the final price after all extra costs have been added, the discount applied and the price
	 * split between the number of paying customers.
	 * 
	 * @return
	 */
	public BigDecimal getFinalPrice() {
		return price.add(sumExtras(0)).multiply(discount, MathContext.DECIMAL64)
				.divide(new BigDecimal(splitCount), 2, BigDecimal.ROUND_HALF_UP);
	}
	/*
	 * Returns the sum of all extra costs for this MenuItem
	 */
	private BigDecimal sumExtras(int index) {
		if(index > extraList.size()-1)
			return BigDecimal.ZERO;
		else
			return extraList.get(index).getAmount().add(sumExtras(index +1));
	}
}

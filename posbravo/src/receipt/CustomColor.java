package receipt;
import java.awt.Color;

/**
 * This class is a collection of public color constants used by GUI classes in the 
 * @author Stephen Collins
 *
 */
public class CustomColor extends Color {
	private static final long serialVersionUID = 1L; //Default serial ID
	
	public static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	public static final Color DARK_GREEN = new Color(0,100,0);
	public static final Color IVORY = new Color(238,238,224);
	public static final Color LIGHT_GRAY = new Color(211,211,211);
	public static final Color SKY_BLUE = new Color(135,206,250);
	public static final Color ROYAL_BLUE = new Color(65,105,225);
	public static final Color TAN = new Color(210,180,140);
	public static final Color KHAKI = new Color(240,230,140);
	public static final Color PALE_GOLDENROD = new Color(238,232,170);
	/**
	 * Creates a new customer color object using red, green, blue integer
	 * arguments.
	 * @param r
	 * @param g
	 * @param b
	 */
	CustomColor(int r, int g, int b) {
		super(r,g,b);
	}
}

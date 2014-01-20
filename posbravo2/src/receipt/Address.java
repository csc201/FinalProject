package receipt;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Defines a class which can be used to store an address for locations within the United
 * State of America
 * @author Stephen Collins
 *
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 1L; //Default value
	private String streetAddress;
	private String suiteAddress;
	private String city;
	private State state;
	private String zipCode;
	private boolean isZipCode;
	
	/**
	 * Instantiates the USaddress class with empty String variables and the state defaulted to 
	 * Alabama
	 */
	Address() {
		streetAddress = "";
		suiteAddress = "";
		city = "";
		state = State.NONE;
		zipCode = "";
	}
	/**
	 * Instantiates the USAddress class and initializes all variables
	 * @param streetAddress
	 * @param suiteAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	Address(String streetAddress, String suiteAddress, String city, State state, String zipCode) {
		this.streetAddress = streetAddress;
		this.suiteAddress = suiteAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	/**
	 * Returns the street address of this location
	 * @return
	 */
	public String getStreetAddress() { return streetAddress; }
	/**
	 * Returns the suite number of this location
	 * @return
	 */
	public String getSuiteAddress() { return suiteAddress; }
	/**
	 * Returns the city of this location
	 * @return
	 */
	public String getCity() { return city; }
	/**
	 * Returns the state of this location
	 * @return
	 */
	public State getState() { return state; }
	/**
	 * Returns the US postal code (zipcode) of this location
	 * @return
	 */
	public String getZipCode() { return zipCode; }
	/**
	 * Sets the street address to a new String value
	 * @param streetAddress
	 */
	public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
	/**
	 * Sets the suite number to a new String value
	 * @param suiteAddress
	 */
	public void setSuiteAddress(String suiteAddress) { this.suiteAddress = suiteAddress; }
	/**
	 * Sets the city to a new String value
	 * @param city
	 */
	public void setCity(String city) { this.city = city; }
	/**
	 * Sets the state to a new USState constant
	 * @param state
	 */
	public void setState(State state) { this.state = state; }
	/**
	 * Sets the US postal code (zipcode) to a new String value
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }
	/**
	 * Returns a String representation of this address
	 */
	public String toString() {
		if(suiteAddress.equals(""))
			return streetAddress + "\n"
					+ city + ", " + state + " " + zipCode;
		else
			return streetAddress + "\n"
					+ suiteAddress + "\n"
					+ city + ", " + state + " " + zipCode;
	}
	/**
	 * Checks a given String to see whether it fits US postal code
	 * format(XXXXX or XXXXX-XXXX).
	 * @param zipCode
	 * @return
	 */
	public boolean isZipCode(String zipCode) {
		isZipCode = true;
		
		if(zipCode.length() == 5) {
			for(int count=0; count < 5; count++)
				if(!Character.isDigit(zipCode.charAt(count)))
					isZipCode = false;
		}
		else if(zipCode.length() == 10 && zipCode.charAt(5) == '-') {
			for(int count=0; count < 10; count++)
				if(!Character.isDigit(zipCode.charAt(count)) && count != 5)
					isZipCode = false;
		}
		else
			isZipCode = false;
		
		return isZipCode;
	}
	/**
	 * Returns a JPanel containing a GUI for editing the values of this USAddress
	 * instance
	 * @return
	 */
	public JPanel getVisualEditor() { 
		return new VisualEditor();
	}
	
	
	/**
	 * Creates a set of constants representing the states and territories of the USA, organized by
	 * postal abbreviation
	 * @author Stephen Collins
	 *
	 */
	public enum State {
		NONE(""),
		AL("Alabama"),
		AK("American Samoa"),
		AZ("Arizona"),
		AR("Arkansas"),
		CA("California"),
		CO("Colorado"),
		CT("Connecticut"),
		DE("Delaware"),
		DC("District of Columbia"),
		FM("Federated States of Micronesia"),
		FL("Florida"),
		GA("Georgia"),
		GU("Guam"),
		HI("Hawaii"),
		ID("Idaho"),
		IL("Illinois"),
		IN("Indiana"),
		IA("Iowa"),
		KS("Kansas"),
		KY("Kentucky"),
		LA("Louisiana"),
		ME("Maine"),
		MH("Marshall Islands"),
		MD("Maryland"),
		MA("Massachusetts"),
		MI("Michigan"),
		MN("Minnesota"),
		MS("Mississippi"),
		MO("Missouri"),
		MT("Montana"),
		NE("Nebraska"),
		NV("Nevada"),
		NH("New Hampshire"),
		NJ("New Jersey"),
		NM("New Mexico"),
		NY("New York"),
		NC("North Carolina"),
		ND("North Dakota"),
		MP("Northern Mariana Islands"),
		OH("Ohio"),
		OK("Oklahoma"),
		OR("Oregon"),
		PW("Palau"),
		PA("Pennsylvania"),
		PR("Puerto Rico"),
		RI("Rhode Island"),
		SC("South Carolina"),
		SD("South Dakota"),
		TN("Tennessee"),
		TX("Texas"),
		UT("Utah"),
		VT("Vermont"),
		VI("Virgin Islands"),
		VA("Virginia"),
		WA("Washington"),
		WV("West Virginia"),
		WI("Wisconsin"),
		WY("Wyoming");
		
		private String stateName;
		
		/**
		 * Assigns the state name to the postal abbreviation for the state
		 * @param stateName
		 */
		private State(String stateName) { this.stateName = stateName; }
		/**
		 * Returns the unabbreviated version of the state name
		 * @return
		 */
		public String toString() { return stateName; }
		/**
		 * Returns the postal abbreviation for the state named in the String argument. Returns
		 * an empty String if the state is not found.
		 * @param stateName
		 * @return
		 */
		public static String findAbbreviation(String stateName) {
			for(State state : State.values())
				if(state.toString().equals(stateName))
					return state.name();
			return null;
		}
	}
	/*
	 * This private class creates the GUI returns by the getVisualEditor method. It implements
	 * a document listener to save an text field changes to the variables used by this USAddress
	 * instance.
	 */
	private class VisualEditor extends JPanel {
		private static final long serialVersionUID = 1L; //Default value
		
		private JTextField streetField;
		private JTextField suiteField;
		private JTextField cityField;
		private JTextField zipField;
		private JComboBox<String> stateBox;
		
		VisualEditor() {
			setLayout(new GridLayout(8,1));
			setSize(300,300);
			streetField = new JTextField(streetAddress, 50);
			streetField.setBorder(BorderFactory.createLoweredBevelBorder());
			streetField.getDocument().addDocumentListener(new StreetListener());
			suiteField = new JTextField(suiteAddress, 50);
			suiteField.setBorder(BorderFactory.createLoweredBevelBorder());
			suiteField.getDocument().addDocumentListener(new SuiteListener());
			cityField = new JTextField(city, 50);
			cityField.setBorder(BorderFactory.createLoweredBevelBorder());
			cityField.getDocument().addDocumentListener(new CityListener());
			zipField = new JTextField(zipCode, 50);
			zipField.setBorder(BorderFactory.createLoweredBevelBorder());
			zipField.getDocument().addDocumentListener(new ZipListener());
			
			stateBox = new JComboBox<String>(); //This JCombo box will be populated by the USState enum class
			stateBox.setBackground(streetField.getBackground());
			stateBox.addActionListener(new StateListener());
			
			for(State state : State.values())
				stateBox.addItem(state.toString());
			
			add(new JLabel("Street:"));
			add(streetField);
			add(new JLabel("Suite:"));
			add(suiteField);
			add(new JLabel("City:"));
			add(cityField);
			
			JPanel panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("State:"));
			panel.add(new JLabel("Zip Code:"));
			add(panel);
			
			panel = new JPanel(new GridLayout(1,2));
			panel.add(stateBox);
			panel.add(zipField);
			add(panel);
		}
		/*
		 * Listens for changes in the stateBox JComboBox and changes the String state value
		 * to reflect any change
		 */
		private class StateListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) { state = State.values()[stateBox.getSelectedIndex()]; }
		}
		private class StreetListener implements DocumentListener {
			@Override
			public void changedUpdate(DocumentEvent e) { streetAddress = streetField.getText(); }
			@Override
			public void insertUpdate(DocumentEvent e) {streetAddress = streetField.getText(); }
			@Override
			public void removeUpdate(DocumentEvent e) { streetAddress = streetField.getText(); }
			
		}
		private class SuiteListener implements DocumentListener {
			@Override
			public void changedUpdate(DocumentEvent e) { suiteAddress = suiteField.getText(); }
			@Override
			public void insertUpdate(DocumentEvent e) {suiteAddress = suiteField.getText(); }
			@Override
			public void removeUpdate(DocumentEvent e) { suiteAddress = suiteField.getText(); }
		}
		private class CityListener implements DocumentListener {
			@Override
			public void changedUpdate(DocumentEvent e) { city = cityField.getText(); }
			@Override
			public void insertUpdate(DocumentEvent e) { city = cityField.getText(); }
			@Override
			public void removeUpdate(DocumentEvent e) { city = cityField.getText(); }
		}
		private class ZipListener implements DocumentListener {
			@Override
			public void changedUpdate(DocumentEvent e) { zipCode = zipField.getText(); }
			@Override
			public void insertUpdate(DocumentEvent e) { zipCode = zipField.getText(); }
			@Override
			public void removeUpdate(DocumentEvent e) { zipCode = zipField.getText(); }
		}
	}
}

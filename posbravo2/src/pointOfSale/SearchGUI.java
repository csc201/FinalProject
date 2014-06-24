package pointOfSale;

/**SearchGUI.java
 * This program searches through tickets based on the receipt number, userID or amount
 * @author Helen
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SearchGUI extends JFrame implements ActionListener {
	
	//all the components in this GUI
	public JLabel receiptLabel;
	public JLabel userIDLabel;
	public JLabel amountLabel;
	public JTextField receiptField;
	public JTextField userIDField;
	public JTextField amountField;
	public JTextArea resultsArea;
	public JScrollPane scroll;
	public JButton closeButton;
	public JButton searchButton;
	public JFrame frame;
	public JPanel panel;
	public JPanel resultsPanel;
	public JPanel buttonPanel;
	
	public SearchGUI() {
		
		//initializing components
		receiptLabel = new JLabel("Receipt: ");
		receiptField = new JTextField(10);
		userIDLabel = new JLabel("User ID: ");
		userIDField = new JTextField(20);
		amountLabel = new JLabel("Amount: ");
		amountField = new JTextField(20);
		resultsArea = new JTextArea(5, 20);
		closeButton = new JButton("Close");
		searchButton = new JButton("Search");
		frame = new JFrame("Search Tickets");
		panel = new JPanel();
		resultsPanel = new JPanel();
		buttonPanel = new JPanel();

		GridLayout aGridLayout = new GridLayout(0,2);
		
		//customizing components
		receiptLabel.setLabelFor(receiptField);
		panel.setLayout(aGridLayout);
		
		resultsPanel.setBorder(new TitledBorder ( new EtchedBorder (), "Search Results" ));
		
		resultsArea.setEditable(false);
		scroll = new JScrollPane(resultsArea);
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		frame.setLayout(new BorderLayout());
		
		//populating JPanels
		panel.add(receiptLabel, BorderLayout.WEST);
		panel.add(receiptField, BorderLayout.EAST);
		panel.add(userIDLabel, BorderLayout.WEST);
		panel.add(userIDField, BorderLayout.EAST);
		panel.add(amountLabel, BorderLayout.WEST);
		panel.add(amountField, BorderLayout.EAST);
		buttonPanel.add(searchButton);
		buttonPanel.add(closeButton);
		resultsPanel.add(scroll);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(resultsPanel, BorderLayout.SOUTH);
			
		
		//JFrame necessities
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);

		/** ActionListener
		 * closes window when closeButton's clicked
		 */
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
	}
	
	public static void main (String[] args) {
		
		SearchGUI searchGUI = new SearchGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Search")) {
			//start to integrate back 
			//need to get ticket number and loop thru ticket list
			Integer.parseInt(receiptField.getText());
		}
	}
	
	//BufferedReader br =	new BufferedReader(new InputStreamReader(System.in));
	//String data = br.readLine();
}

package pointOfSale;

/**SearchGUI.java
 * 
 * This program searches through tickets based on the receipt number, userID or amount
 * This program relies on Receipts.java (mostly for demo purposes, since ReceiptManager wasn't returning Receipt objects correctly)
 * 
 * @author Helen Li
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import receipt.CustomColor;

public class SearchGUI extends JFrame {
	
	//all the components in this GUI
	public JLabel receiptLabel;
	public JLabel userIDLabel;
	public JLabel amountLabel;
	public JTextField receiptField;
	public JTextField userIDField;
	public JTextField amountField;
	public JTextArea resultsArea;
	public JScrollPane scroll;
	public JButton searchButton;
	public JButton clearButton;
	public JFrame frame;
	public JPanel panel;
	public JPanel resultsPanel;
	public JPanel buttonPanel;

	public SearchGUI() {
		
		//initializing components
		receiptLabel = new JLabel("Receipt: ");
		receiptField = new JTextField(10);
		userIDLabel = new JLabel("User ID: ");
		userIDField = new JTextField(10);
		amountLabel = new JLabel("Amount: ");
		amountField = new JTextField(10);
		resultsArea = new JTextArea(5, 27);
		searchButton = new JButton("Search");
		clearButton = new JButton("Clear");
		frame = new JFrame("Search Tickets");
		panel = new JPanel(); //the north panel, with all the labels and fields
		resultsPanel = new JPanel();
		buttonPanel = new JPanel();

		//the grid for the main panel
		GridLayout aGridLayout = new GridLayout(0,2);
		
		//customizing components
		receiptLabel.setLabelFor(receiptField);
		userIDLabel.setLabelFor(userIDField);
		amountLabel.setLabelFor(amountField);
		panel.setLayout(aGridLayout);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		panel.setBackground(CustomColor.KHAKI);
		buttonPanel.setBackground(CustomColor.KHAKI );
		resultsPanel.setBackground(CustomColor.KHAKI );
		resultsPanel.setBorder(new TitledBorder ( new EtchedBorder (), "Search Results" ));
		resultsArea.setLineWrap(true);
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
		buttonPanel.add(clearButton);
		resultsPanel.add(scroll);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(resultsPanel, BorderLayout.SOUTH);
			
		
		//JFrame necessities
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);

		/** 
		 * resets all text fields to null
		 */
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiptField.setText(null);
				userIDField.setText(null);
				amountField.setText(null);
				resultsArea.setText(null);
			}
		});
		
		/**
		 * logic when the search button is pressed
		 */
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Receipts aReceipt = new Receipts();
				
				int searchReceiptNo = Integer.parseInt(receiptField.getText()); //parse the input to an int
				resultsArea.setText(aReceipt.findReceiptByid(searchReceiptNo)); //prints the full receipt object as a string to the result area
				
				int searchUserNo = Integer.parseInt(userIDField.getText());
				resultsArea.append(aReceipt.findUserByid(searchUserNo) + "\n");
				
				double searchAmountNo = Double.parseDouble(amountField.getText());
				resultsArea.append(aReceipt.findReceiptByAmount(searchAmountNo) + "\n");
				
			}
		});
		
	}
	
	
	public static void main (String[] args) {		
		new SearchGUI();
	}
	
}

package receipt;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;

/**
 * This class stores the necessary data to create a simulated store receipt. Information about the merchant/store, cashier and sales tax rate must
 * be provided by the calling class. This class will read the date from the System time. BigDecimal values are used to keep calculations exact.
 * 
 * @author Stephen
 *
 */
public class Receipt implements Serializable {
	private static final long serialVersionUID = 1L;  //Default value, added to satisfy compiler
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private Merchant merchant;
	private String cashier;
	private BigDecimal subtotal;
	private BigDecimal salesTax;
	private Date date;
	private LinkedHashMap<Integer, MenuItem> itemMap;
	private ReceiptGUI gui;
	private String tableNum;
	private int receiptNum;
	private String note;
	/**
	 * Get the note from the ticket or receipt
	 *  Modified by  Tanes on 6-23-14
	 * @return
	 */
	public String getNote() {
		return note;
	}
	/**
	 * Set special notes to the ticket or receipt
	 * Modified by  Tanes on 6-23-14
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * Instantiates the Receipt object with the argument Merchant, cashier and taxPercent data
	 * @param merchant
	 * @param cashier
	 * @param taxPercent
	 */
	Receipt(Merchant merchant, String cashier, BigDecimal salesTax) {
		this.merchant = merchant;
		this.cashier = cashier;
		this.salesTax = salesTax;
		this.note="Remarks: NONE";
		tableNum = "";
		date = new Date();
		itemMap = new LinkedHashMap<Integer, MenuItem>();
		gui = new ReceiptGUI();
		
	}
	/**
	 * Creates a Receipt object with the date already provided
	 * @param merchant
	 * @param cashier
	 * @param salesTax
	 * @param date
	 */
	Receipt(Merchant merchant, String cashier, BigDecimal salesTax, Date date, String table) {
		this.merchant = merchant;
		this.cashier = cashier;
		this.salesTax = salesTax;
		this.date = date;
		this.tableNum = table;
		itemMap = new LinkedHashMap<Integer, MenuItem>();
		gui = new ReceiptGUI();
	}
	/**
	 * Creates a Receipt object with initial MenuItem objects assigned to it
	 * @param merchant
	 * @param cashier
	 * @param salesTax
	 * @param date
	 * @param itemCollection
	 */
	Receipt(Merchant merchant, String cashier, BigDecimal salesTax, Date date, Collection<MenuItem> itemCollection, String table) {
		this.merchant = merchant;
		this.cashier = cashier;
		this.salesTax = salesTax;
		this.date = date;
		this.tableNum = table;
		itemMap = new LinkedHashMap<Integer, MenuItem>();
		for(MenuItem item : itemCollection)
			itemMap.put(itemMap.size()+1, item);
		gui = new ReceiptGUI();
	}
	/**
	 * Adds a MenuItem to the receipt
	 * 
	 * @param item
	 */
	public void addItem(MenuItem item) {
		itemMap.put(itemMap.size()+1, item);
		gui.updateReceipt();
	}
	/**
	 * Adds a MenuItem to the receipt storing at the orderNumber key value in the MenuItem hashmap
	 * 
	 * @param orderNumber
	 * @param item
	 */
	public void addItem(int orderNumber, MenuItem item) {
		itemMap.put(orderNumber, item);
		gui.updateReceipt();
	}
	public void removeItem(int orderNumber) {
		itemMap.remove(orderNumber);
		gui.updateReceipt();
	}
	/**
	 * Removes the currently selected MenuItem from the Receipt
	 */
	public void removeSelectedItem() {
		itemMap.remove(gui.getSelectedKey());
		gui.updateReceipt();
	}
	/**
	 * Removes all items from the receipt;
	 */
	public void removeAll() {
		itemMap.clear();
		gui.updateReceipt();
	}
	/**
	 * Forces the GUI to re-read the stored MenuItem data
	 */
	public void refresh() { gui.updateReceipt(); }
	/**
	 * Returns the MenuItem at the orderNumber key value in the MenuItem hashmap
	 * 
	 * @param orderNumber
	 * @return
	 */
	public MenuItem getItem(int orderNumber) { return itemMap.get(orderNumber); }
	/**
	 * Returns the currently selected MenuItem
	 * 
	 * @return
	 */
	public MenuItem getSelectedItem() { return itemMap.get(gui.getSelectedKey()); }
	/**
	 * Returns true if a valid MenuItem is currently selected in this Receipt's GUI, false if a blank
	 * line is selected or nothing is selected
	 * 
	 * @return
	 */
	public boolean hasOrderSelected() { return gui.hasOrderSelected(); }
	/**
	 * Returns the identifying number assigned to this receipt, if any
	 * @return
	 */
	public int getReceiptNum() { return receiptNum; }
	/**
	 * Sets the identifying number assigned to this receipt to a new value
	 * @param receiptNum
	 */
	public void setReceiptNum(int receiptNum) { 
		this.receiptNum = receiptNum;
		gui.updateReceipt();
	}
	
	/**
	 * Returns the HashMap key value for the currently selected MenuItem in this receipt's GUI
	 * 
	 * @return
	 */
	public int getSelectedOrderNum() { return gui.getSelectedKey(); }
	/**
	 * Returns the HashMap containg this Receipt's MenuItem's and order number key values
	 * 
	 * @return
	 */
	public HashMap<Integer, MenuItem> getItems() { return itemMap; }
	/**
	 * Returns the current number of MenuItems stored in this Receipt
	 * 
	 * @return
	 */
	public int itemCount() { return itemMap.size(); }
	/**
	 * Returns a String description of this Receipt object
	 */
	public String toString() {
		return "Receipt for service at " + merchant.getName() + "\n"
				+ "Containing " + itemMap.size() + " items\n"
				+ "Processed by " + cashier + "\n"
				+ "On " + new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	/**
	 * Returns a new instance of the Receipt class with the same merchant, cashier, salestax and date values
	 * as this Receipt but with no objects.
	 * 
	 * @return
	 */
	public Receipt emptyCopy() { return new Receipt(merchant, cashier, salesTax, date, tableNum); }
	/**
	 * Adds a MouseListener to all components except the MenuItem JTable
	 * 
	 * @param listener
	 */
	public void addReceiptListener(MouseListener listener) { 
		gui.addMouseListener(listener);
		for(Component component : gui.getComponents())
			if(!(component instanceof JTable))
				component.addMouseListener(listener);
	}
	/**
	 * Adds a MouseListener to the MenuItem Jtable
	 * 
	 * @param listener
	 */
	public void addReceiptItemListener(MouseListener listener) { gui.addReceiptItemListener(listener); }
	/**
	 * Adds a MouseWheelListener to all components in this Receipt's GUI
	 * @param listener
	 */
	public void addReceiptWheelListener(MouseWheelListener listener) {
		for(Component component : gui.getComponents())
			component.addMouseWheelListener(listener);
	}
	/**
	 * Removes mouselisteners from the GUI
	 */
	public void removeReceiptListeners() { 
		for(MouseListener listener : gui.getMouseListeners())
			gui.removeMouseListener(listener);
	}
	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
		gui.updateReceipt();
	}
	/**
	 * Removes mouse listeners from the GUI Jtable displaying this receipt's MenuITems
	 * 
	 */
	public void removeReceiptItemListener() { gui.removeReceiptItemListeners(); }
	/**
	 * Clears any selection from this Receipt's JTable GUI displaying this receipt's MenuItems
	 */
	public void clearSelection() { gui.clearSelection(); }
	/**
	 * Returns the GUI for this receipt object
	 * 
	 * @return
	 */
	public JPanel getGUI() { return gui; }
	/*
	 * Returns the sum of all MenuItems stored in this receipt so that sales tax may be applied
	 */
	private void sumSubtotal() {
		subtotal = BigDecimal.ZERO;
		for(MenuItem item : itemMap.values())
			subtotal = subtotal.add(item.getFinalPrice());
	}
	/*
	 * Defines the GUI used by this receipt object
	 */
	private class ReceiptGUI extends JPanel {
		private static final long serialVersionUID = 1L; //Default value
		
		private StringTableModel tableModel;
		private JTable table;
		private StringTableModel totalModel;
		private JTable totalTable;
		private JLabel tableNumLabel;
		private JLabel receiptNumLabel;
		
		/*
		 * Creates a JPanel GUI to allow the user to select the MenuItems stored in this receipt and interact with the object as if it
		 * were a receipt
		 */
		ReceiptGUI() {
			setLayout(new BorderLayout());
			tableModel = new StringTableModel(new String[] {"Order","Item","Extra","Cost"});
			table = new JTable(tableModel);
			table.setBackground(Color.WHITE);
			table.setFocusable(false);
			table.setRowSelectionAllowed(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setColumnSelectionAllowed(false);
			table.setShowGrid(true);//Do not forget to set the grid to false after the modification by Tanes
			table.setIntercellSpacing(new Dimension(0, 0));
			table.setTableHeader(null);
			
			add(headerPanel(), BorderLayout.NORTH);
			add(scrollPanel(), BorderLayout.CENTER);
			add(footerPanel(), BorderLayout.SOUTH);
		}
		/*
		 * Formats the upper third of the Receipt GUI
		 */
		private JPanel headerPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.WHITE));
			panel.setBackground(Color.WHITE);
			
			JLabel label = new JLabel(merchant.getName(), SwingConstants.CENTER);
			label.setFont(new Font(Font.SERIF, Font.BOLD, 24));
			label.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));
			panel.add(label, BorderLayout.CENTER);
			
			JPanel subPanel = new JPanel(new GridLayout(3,1));
			subPanel.setBackground(Color.WHITE);
			label = new JLabel(merchant.getAddress().getStreetAddress(), SwingConstants.CENTER);
			subPanel.add(label);
			label = new JLabel(merchant.getAddress().getCity() + ", " 
					+ merchant.getAddress().getState() + " " 
					+ merchant.getAddress().getZipCode(), SwingConstants.CENTER);
			subPanel.add(label);
			label = new JLabel(merchant.getPhone(), SwingConstants.CENTER);
			subPanel.add(label);
			
			panel.add(subPanel, BorderLayout.SOUTH);
			
			return panel;
		}
		/*
		 * Formats the middle of the Receipt GUI, containing the interactive JTable displaying this receipt's MenuItems
		 */
		private JScrollPane scrollPanel() {
			JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.WHITE));
			scrollPane.getViewport().setBackground(Color.WHITE);
			return scrollPane;
		}
		/*
		 * Formats the lower third of the Receipt GUI, where the Final Cost is displayed
		 */
		private JPanel footerPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.WHITE));
			panel.setBackground(Color.WHITE);
			
			totalModel = new StringTableModel(new String[] { "Order","Item","Extra","Cost" });
			totalTable = new JTable(totalModel);
			totalTable.setBackground(Color.WHITE);
			totalTable.setFocusable(false);
			totalTable.setRowSelectionAllowed(false);
			totalTable.setColumnSelectionAllowed(false);
			totalTable.setShowGrid(false);
			totalTable.setIntercellSpacing(new Dimension(0, 0));
			totalTable.setTableHeader(null);
			
			totalModel.addRow(new String[] { "","","SUBTOTAL","" });
			totalModel.addRow(new String[] { "","","TAX","" });
			totalModel.addRow(new String[] { "","","TOTAL","" });
			totalModel.addRow(new String[] { "","","REMARKS","" });
			
			panel.add(totalTable, BorderLayout.NORTH);
			
			JLabel label = new JLabel("Thank You for Your Patronage!", SwingConstants.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.WHITE));
			panel.add(label, BorderLayout.CENTER);
			
			JPanel subPanel = new JPanel(new GridLayout(2,1));
			subPanel.setBackground(Color.WHITE);
			
			JPanel receiptNumPanel = new JPanel();
			receiptNumPanel.setBackground(Color.WHITE);
			if(receiptNum > 0) {
				String receiptNumString = String.valueOf(receiptNum);
				while(receiptNumString.length() < 9)
					receiptNumString = "0" + receiptNumString;
				receiptNumLabel = new JLabel(receiptNumString);
			}
			else
				receiptNumLabel = new JLabel("");
			receiptNumPanel.add(receiptNumLabel);
			subPanel.add(receiptNumPanel);
			
			JPanel infoPanel = new JPanel(new GridLayout(1,3));
			infoPanel.setBackground(Color.WHITE);
			label = new JLabel(new SimpleDateFormat(DATE_FORMAT).format(date));
			infoPanel.add(label);
			
			if(tableNum.equals(""))
				tableNumLabel = new JLabel(tableNum, SwingConstants.CENTER);
			else
				tableNumLabel = new JLabel("Table: " + tableNum, SwingConstants.CENTER);
			infoPanel.add(tableNumLabel);
			
			label = new JLabel("Cashier: " + cashier, SwingConstants.RIGHT);
			infoPanel.add(label);
			subPanel.add(infoPanel);
			
			panel.add(subPanel, BorderLayout.SOUTH);
			
			return panel;
		}
		/*
		 * Rewrites the receipt's JTables to reflect any changes in the Receipt's MenuItems
		 */
		public void updateReceipt() {
			tableModel.deleteAllRows();
			
			for(Integer key : itemMap.keySet()) {
				tableModel.addRow(new String[] { key.toString(), itemMap.get(key).getName(), "", itemMap.get(key).getPrice().toPlainString() });
				for(ExtraCost extra : itemMap.get(key).getExtraList())
					tableModel.addRow(new String[] { "", "", extra.getName(), extra.getAmount().toPlainString() });
				if(itemMap.get(key).hasDiscount())
					tableModel.addRow(new String[] { "", "", "DISCOUNT", itemMap.get(key).getDiscountPercent() + "%" });
				if(itemMap.get(key).isSplit())
					tableModel.addRow(new String[] { "", "", "SPLIT/" + itemMap.get(key).getSplitCount(), "" });
				if(itemMap.get(key).hasExtraCosts() || itemMap.get(key).hasDiscount() || itemMap.get(key).isSplit())
					tableModel.addRow(new String[] { "", "", "FINAL", itemMap.get(key).getFinalPrice().toPlainString() });
				tableModel.addRow(new String[] { "","","","" });
			}
			sumSubtotal();
			totalModel.setValueAt(subtotal.toPlainString(), 0, 3);
			totalModel.setValueAt(subtotal.multiply(salesTax).setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString(), 1, 3);
			totalModel.setValueAt(subtotal.multiply(salesTax.add(BigDecimal.ONE)).setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString(), 2, 3);
			
			if(tableNum.equals(""))
				tableNumLabel.setText(tableNum);
			else
				tableNumLabel.setText("Table: " + tableNum);
			
			if(receiptNum > 0) {
				String receiptNumString = String.valueOf(receiptNum);
				while(receiptNumString.length() < 9)
					receiptNumString = "0" + receiptNumString;
				receiptNumLabel.setText(receiptNumString);
			}
			else
				receiptNumLabel.setText("");
			
			refresh();
		}
		/*
		 * Adds a Mouse listener to the JTable displaying this Receipt's MenuItems
		 */
		public void addReceiptItemListener(MouseListener listener) { table.addMouseListener(listener); }
		/*
		 * Removes all Mouse Listeners from the JTable displaying this Receipt's MenuItems
		 */
		public void removeReceiptItemListeners() { 
			for(MouseListener listener : table.getMouseListeners())
				table.removeMouseListener(listener);
		}
		/*
		 * Clears any Selections from the JTable displaying this Receipt's MenuItems
		 */
		public void clearSelection() { table.clearSelection(); }
		/*
		 * Returns the HashMap key values associated with the currently selected row
		 */
		public int getSelectedKey() {
			for(int r=table.getSelectedRow(); r >= 0; r--)
				if(!tableModel.getValueAt(r, 0).equals(""))
					return Integer.parseInt(tableModel.getValueAt(r, 0));
			return -1;
		}
		/*
		 * Refreshes the visiblity of this Receipt's jtables to refelct any changes
		 */
		public void refresh() {
			validate();
			table.setVisible(false);
			totalTable.setVisible(false);
			table.setVisible(true);
			totalTable.setVisible(true);
		}
		/*
		 * Returns true if a JTable row which is part of a MenuItem display is selected
		 */
		public boolean hasOrderSelected() {
			return table.getSelectedRow() > -1 && (!tableModel.getValueAt(table.getSelectedRow(),2).equals("")
					|| !tableModel.getValueAt(table.getSelectedRow(),3).equals(""));
		}
	}
}

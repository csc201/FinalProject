package receipt;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Manages Receipt Objects to allow the user to create copies of an original receipt and moves MenuItems between them.
 * 
 * @author Stephen Collins
 *
 */
public class ReceiptManager extends JPanel {
	private static final long serialVersionUID = 1L; //Default value
	
	private JPanel receiptPanel;
	private ArrayList<Receipt> receiptList;
	private JPopupMenu popup;
	private JComboBox<Integer> receiptBox;
	private JComboBox<String> tableBox;
	private Dimension screenSize;
	private boolean receiptIsSelected;
	private int selectedReceiptIndex;
	private int selectedItemNum;
	private JDialog dialog;
	private int dialogChoice;
	private Merchant merchant;
	private String cashier;
	private BigDecimal salesTax;
	
	/**
	 * Instantiates the ReceiptManger using the argument Receipt to create an original receipt which can be copied and manipulated
	 * 
	 * @param original
	 */
	public ReceiptManager(Merchant merchant, String cashier, BigDecimal salesTax) {
		setBackground(CustomColor.PALE_GOLDENROD);
		addMouseListener(new BackgroundListener());
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.merchant = merchant;
		this.cashier = cashier;
		this.salesTax = salesTax;
		
		receiptList = new ArrayList<Receipt>();
		receiptList.add(new Receipt(this.merchant, this.cashier, this.salesTax));
		receiptList.get(0).addReceiptListener(new ReceiptListener());
		receiptList.get(0).addReceiptItemListener(new ReceiptItemListener());
		receiptList.get(0).addReceiptWheelListener(new ReceiptScroller());
		receiptIsSelected = false;
		
		generatePopupMenu();
		
		add(generateReceiptPanel());
	}
	private JPanel generateReceiptPanel() {
		JPanel receiptFrame = new JPanel();
		receiptFrame.setLayout(new BorderLayout());
		receiptFrame.setPreferredSize(new Dimension((int)screenSize.getWidth()/4 - 10, (int)screenSize.getHeight()-50));
		
		JPanel splitPanel = new JPanel();
		splitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		splitPanel.setBackground(CustomColor.PALE_GOLDENROD);
		
		splitPanel.add(new JLabel("Receipt #"));
		
		receiptBox = new JComboBox<Integer>();
		receiptBox.addItem(1);
		receiptBox.addItemListener(new ReceiptBoxListener());
		splitPanel.add(receiptBox);
		
		JButton button = new JButton("Copy");
		button.setBackground(CustomColor.PALE_GOLDENROD);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createMatteBorder(5,10,5,10, CustomColor.PALE_GOLDENROD)));
		button.addActionListener(new ButtonListener());
		splitPanel.add(button);
		
		button = new JButton("Delete");
		button.setBackground(CustomColor.PALE_GOLDENROD);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createMatteBorder(5,10,5,10, CustomColor.PALE_GOLDENROD)));
		button.addActionListener(new ButtonListener());
		splitPanel.add(button);
		
		button = new JButton("Delete All");
		button.setBackground(CustomColor.PALE_GOLDENROD);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
				BorderFactory.createMatteBorder(5,10,5,10, CustomColor.PALE_GOLDENROD)));
		button.addActionListener(new ButtonListener());
		splitPanel.add(button);
		
		receiptPanel = new JPanel();
		receiptPanel.setLayout(new GridLayout(1,1));
		receiptPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEtchedBorder()));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(CustomColor.PALE_GOLDENROD);
		
		tablePanel.add(new JLabel("Table #"));
		
		tableBox = new JComboBox<String>();
		for(int i=0; i < 100; i++)
			tableBox.addItem("" + i);
		tableBox.addItemListener(new TableBoxListener());
		tablePanel.add(tableBox);
		
		receiptFrame.add(splitPanel, BorderLayout.NORTH);
		receiptFrame.add(receiptPanel, BorderLayout.CENTER);
		receiptFrame.add(tablePanel, BorderLayout.SOUTH);
		
		viewReceipt(0);
		
		return receiptFrame;
	}
	public ArrayList<Receipt> getList() { return receiptList; }
	public void clearReceipt() {
		deleteAll();
	}
	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
		for(Receipt receipt : receiptList)
			receipt.refresh();
	}
	/*
	 * Constructs the popup menus which appear when the user right clicks a receipt
	 */
	private void generatePopupMenu() {
		ReceiptPopupListener popupListener = new ReceiptPopupListener();
		JMenuItem item;
		
		popup = new JPopupMenu();
		item = new JMenuItem("Split");
		item.addActionListener(popupListener);
		popup.add(item);
		item = new JMenuItem("Discount");
		item.addActionListener(popupListener);
		popup.add(item);
	}
	public void deleteSelectedItem() {
		if(receiptList.get(receiptBox.getSelectedIndex()).hasOrderSelected())
			receiptList.get(receiptBox.getSelectedIndex()).removeSelectedItem();
	}
	public void addItem(MenuItem item) {
		receiptList.get(receiptBox.getSelectedIndex()).addItem(item);
	}
	/*
	 * Creates another copy of the receipt and selects it
	 */
	private void createCopy() {
		receiptList.add(receiptList.get(0).emptyCopy());
		receiptList.get(receiptList.size()-1).addReceiptListener(new ReceiptListener());
		receiptList.get(receiptList.size()-1).addReceiptWheelListener(new ReceiptScroller());
		receiptList.get(receiptList.size()-1).addReceiptItemListener(new ReceiptItemListener());
		receiptList.get(receiptList.size()-1).addReceiptWheelListener(new ReceiptScroller());
		
		receiptBox.addItem(receiptList.size());
		receiptBox.setSelectedIndex(receiptList.size()-1);
		clearSelections();
	}
	/*
	 * Deletes a copy of a receipt moving all of its MenuItems back to the Original
	 */
	private void deleteCopy() {
		if(receiptList.size() == 1) 
			deleteAll();
		else {
			int index = receiptBox.getSelectedIndex();
			
			for(Integer orderNumber : receiptList.get(index).getItems().keySet()) {
				if(receiptList.get(index).getItem(orderNumber).isSplit()) {
					MenuItem item = receiptList.get(index).getItem(orderNumber);
					item.split(item.getSplitCount()-1);
					
					for(Receipt receipt : receiptList)
						if(receipt.getItems().containsKey(orderNumber))
							receipt.addItem(orderNumber, item);
				}
				else if(index == 0)
					receiptList.get(1).addItem(orderNumber, receiptList.get(index).getItem(orderNumber));
				else
					receiptList.get(0).addItem(orderNumber, receiptList.get(index).getItem(orderNumber));
			}
			
			receiptList.remove(index);
			
			if(index == 0)
				viewReceipt(0);
			else
				receiptBox.setSelectedIndex(index-1);
			
			receiptBox.removeItemAt(receiptBox.getItemCount()-1);
			clearSelections();
		}
	}
	/*
	 * Deletes all Receipt Copies, moving their MenuItems back to the Original
	 */
	private void deleteAll() {
		receiptPanel.removeAll();
		receiptPanel.validate();
		
		receiptList.clear();
		receiptList.add(new Receipt(merchant, cashier, salesTax));
		
		receiptBox.removeAllItems();
		receiptBox.addItem(1);
		
		viewReceipt(0);
		clearSelections();
	}
	/*
	 * Changes the receipt copy gui currently inhabiting the JPanel created to contain them
	 */
	private void viewReceipt(int index) {
		receiptPanel.removeAll();
		receiptPanel.add(receiptList.get(index).getGUI());
		receiptPanel.validate();
	}
	/*
	 * Transfers MenuItems betwen the Original receipt and currently active receipt copy
	 */
	private void transferSelection() {
		if(receiptList.get(selectedReceiptIndex).getItem(selectedItemNum).isSplit() 
				&& receiptList.get(receiptBox.getSelectedIndex()).getItems().containsKey(selectedItemNum)) {
			MenuItem item = receiptList.get(selectedReceiptIndex).getItem(selectedItemNum);
			receiptList.get(selectedReceiptIndex).removeItem(selectedItemNum);
			item.split(item.getSplitCount()-1);
			
			for(Receipt receipt : receiptList)
				if(receipt.getItems().containsKey(selectedItemNum))
					receipt.addItem(selectedItemNum, item);
		}
		else {
			receiptList.get(receiptBox.getSelectedIndex()).addItem(receiptList.get(selectedReceiptIndex).getItem(selectedItemNum));
			receiptList.get(selectedReceiptIndex).removeItem(selectedItemNum);
		}
		clearSelections();
	}
	/*
	 * Clears all selections from the Receipt GUIS
	 */
	private void clearSelections() {
		receiptIsSelected = false;
	}
	/*
	 * Brings up a JDialog box to allow the user to split a MenuItem between several different receipts
	 */
	private void splitItem() {
		if(receiptList.get(receiptBox.getSelectedIndex()).hasOrderSelected()) {
			int orderNumber = receiptList.get(receiptBox.getSelectedIndex()).getSelectedOrderNum();
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			JCheckBox[] receiptCheckBox = new JCheckBox[receiptList.size()];
			for(int i=0; i < receiptList.size(); i++) {
				receiptCheckBox[i] = new JCheckBox("Receipt " + (i+1), receiptList.get(i).getItems().containsKey(orderNumber));
				receiptCheckBox[i].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
				panel.add(receiptCheckBox[i]);
			}
			
			JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setSize(150,300);
			
			dialogChoice = -1;
			showDialog(scrollPane, "Split Item", new String[] { "Split", "Cancel" });
			
			if(dialogChoice == 0) {
				MenuItem item = receiptList.get(receiptBox.getSelectedIndex()).getItem(orderNumber);
				
				for(Receipt receipt : receiptList)
					receipt.removeItem(orderNumber);
				
				int splitCount = 0;
				for(JCheckBox box : receiptCheckBox)
					if(box.isSelected())
						splitCount++;
				
				item.split(splitCount);
				
				for(int i=0; i < receiptCheckBox.length; i++)
					if(receiptCheckBox[i].isSelected())
						receiptList.get(i).addItem(orderNumber, item);
			}
		}
		clearSelections();
	}
	/*
	 * Brings up a JDialog box to allow the user to change the discount for a MenuItem
	 */
	private void discountItem() {
		if(receiptList.get(receiptBox.getSelectedIndex()).hasOrderSelected()) {
			MenuItem item = receiptList.get(receiptBox.getSelectedIndex()).getSelectedItem();
			int orderNumber = receiptList.get(receiptBox.getSelectedIndex()).getSelectedOrderNum();
			
			JPanel panel = new JPanel();
			panel.setSize(200,100);
			
			JComboBox<Integer> discountBox = new JComboBox<Integer>();
			for(int i=0; i <= 100; i++)
				discountBox.addItem(i);
			discountBox.setSelectedIndex(Integer.parseInt(item.getDiscountPercent()));
			
			panel.add(new JLabel("Select Discount: "));
			panel.add(discountBox);
			
			dialogChoice = -1;
			showDialog(panel, "Set Discount", new String[] { "Set","Cancel" });
			if(dialogChoice == 0) {
				item.setDiscount(discountBox.getSelectedItem().toString());
				
				receiptList.get(receiptBox.getSelectedIndex()).addItem(orderNumber, item);
			}
		}
		clearSelections();
	}
	/*
	 * Refreshes the receipt, causing its GUI to rewrite its MenuItem data
	 */
	private void refreshReceipt() {
		receiptList.get(receiptBox.getSelectedIndex()).refresh();
	}
	/*
	 * Shows a dialog box containing the argument JComponent, with the argument String title, and a number of buttons equal to the String array
	 * provided for the button text. A integer, dialogChoice, is set to the index value of the button selected according to that buttons text
	 * in the string array. If the user exits the dialog box, dialog choice is set to -1
	 */
	private void showDialog(JComponent component, String title, String[] buttonText) {
		dialog = new JDialog();
		dialog.setTitle(title);
		dialog.setModal(true);
		dialog.setSize(component.getSize());
		dialog.setResizable(false);
		dialog.setLocation(((int)screenSize.getWidth() - dialog.getWidth())/2, ((int)screenSize.getHeight() - dialog.getHeight())/2);
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for(int i=0; i < buttonText.length; i++) {
			JButton button = new JButton(buttonText[i]);
			button.setActionCommand(i+"");
			button.addActionListener(new DialogListener());
			buttonPanel.add(button);
			
			if(i==0)
				dialog.getRootPane().setDefaultButton(button);
		}
		
		dialog.add(component, BorderLayout.CENTER);
		dialog.add(buttonPanel, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
	/*
	 * Respons to JButtons used to create or delete copies
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Copy"))
				createCopy();
			else if(e.getActionCommand().equals("Delete"))
				deleteCopy();
			else if(e.getActionCommand().equals("Delete All"))
				deleteAll();
		}	
	}
	/*
	 * Responds to the JComboBox which contains the number of current copies
	 */
	private class ReceiptBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				viewReceipt(receiptBox.getSelectedIndex());
		}
	}
	private class ReceiptListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(receiptIsSelected && selectedReceiptIndex != receiptBox.getSelectedIndex())
				transferSelection();
		}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	private class ReceiptItemListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger())
				popup.show(e.getComponent(), e.getX(), e.getY());
			else if(receiptIsSelected && selectedReceiptIndex != receiptBox.getSelectedIndex())
				transferSelection();
			else {
				receiptIsSelected = true;
				selectedReceiptIndex = receiptBox.getSelectedIndex();
				selectedItemNum = receiptList.get(receiptBox.getSelectedIndex()).getSelectedOrderNum();
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.isPopupTrigger())
				popup.show(e.getComponent(), e.getX(), e.getY());
		}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}	
	}
	private class BackgroundListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			clearSelections();
		}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	private class ReceiptPopupListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Split"))
				splitItem();
			else if(e.getActionCommand().equals("Discount"))
				discountItem();
			else if(e.getActionCommand().equals("Refresh"))
				refreshReceipt();
		}
	}
	/*
	 * Responds to the JDialog created by the show dialog method
	 */
	private class DialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.setVisible(false);
			dialogChoice = Integer.parseInt(e.getActionCommand());
		}
	}
	/*
	 * Responds to mouse wheel events over the copy gui by scrolling through the created Receipt copies
	 */
	private class ReceiptScroller implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int movement= receiptBox.getSelectedIndex() + e.getWheelRotation();
			if(movement >= 0 && movement < receiptBox.getItemCount())
				receiptBox.setSelectedIndex(movement);
		}
	}
	private class TableBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				for(Receipt receipt : receiptList)
					receipt.setTableNum(tableBox.getItemAt(tableBox.getSelectedIndex()));
		}
		
	}
}

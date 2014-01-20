package receipt;
import javax.swing.*;

import java.awt.*;
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
 * @author Stephen
 *
 */
public class ReceiptManager extends JFrame {
	private static final long serialVersionUID = 1L; //Default value
	private Receipt original;
	private ArrayList<Receipt> copyList;
	private JPanel contentPanel;
	private JPanel copyFrame;
	private JPanel copyPanel;
	private JPopupMenu originalPopup;
	private JPopupMenu copyPopup;
	private JComboBox<Integer> copyBox;
	private Dimension screenSize;
	private boolean copyIsSelected;
	private boolean originalIsSelected;
	private JDialog dialog;
	private int dialogChoice;
	
	/**
	 * Instantiates the ReceiptManger using the argument Receipt to create an original receipt which can be copied an manipulated
	 * 
	 * @param original
	 */
	ReceiptManager(Receipt original) {
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		
		generateBackground();
		generatePopups();
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.original = original;
		this.original.addReceiptListener(new OriginalListener());
		this.original.addReceiptItemListener(new OriginalItemListener());
		copyList = new ArrayList<Receipt>();
		originalIsSelected = false;
		copyIsSelected = false;
		
		contentPanel.add(generateOriginalReceiptPanel());
		generateCopyReceiptPanel();
	}
	/**
	 * Generates the background of the Receipt manager
	 */
	private void generateBackground() {
		JPanel borderPanel = new JPanel(new BorderLayout());
		borderPanel.setBackground(CustomColor.PALE_GOLDENROD);
		borderPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, borderPanel.getBackground()));
		getContentPane().add(borderPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(CustomColor.PALE_GOLDENROD);
		JButton button = new JButton("Exit");
		button.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		button.addActionListener(new ExitListener());
		buttonPanel.add(button);
		borderPanel.add(buttonPanel, BorderLayout.NORTH);
		
		contentPanel = new JPanel(new FlowLayout());
		contentPanel.setBackground(CustomColor.PALE_GOLDENROD);
		contentPanel.addMouseListener(new BackgroundListener());
		borderPanel.add(contentPanel, BorderLayout.CENTER);
	}
	/*
	 * Constructs a JPanel to hold the original Receipt object's GUI
	 */
	private JPanel generateOriginalReceiptPanel() {
		JPanel originalReceiptPanel = new JPanel(new BorderLayout());
		originalReceiptPanel.setPreferredSize(new Dimension(400,(int)screenSize.getHeight()-100));
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBackground(CustomColor.PALE_GOLDENROD);
		JButton button = new JButton("Create Copy");
		button.addActionListener(new ButtonListener());
		panel.add(button);
		originalReceiptPanel.add(panel, BorderLayout.NORTH);
		
		panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEtchedBorder()));
		panel.add(original.getGUI());
		originalReceiptPanel.add(panel, BorderLayout.CENTER);
		
		return originalReceiptPanel;
	}
	/*
	 * Constructs a JPanel to hold the GUI for the Receipt copies
	 */
	private void generateCopyReceiptPanel() {
		copyFrame = new JPanel(new BorderLayout());
		copyFrame.setPreferredSize(new Dimension(400,(int)screenSize.getHeight()-100));
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBackground(CustomColor.PALE_GOLDENROD);
		panel.add(new JLabel("Receipt Copy:"));
		
		copyBox = new JComboBox<Integer>();
		copyBox.addItemListener(new CopyBoxListener());
		panel.add(copyBox);
		
		JButton button = new JButton("Delete Copy");
		button.addActionListener(new ButtonListener());
		panel.add(button);
		
		button = new JButton("Delete All");
		button.addActionListener(new ButtonListener());
		panel.add(button);
		
		copyPanel = new JPanel(new GridLayout(1,1));
		copyPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEtchedBorder()));
		
		copyFrame.add(panel, BorderLayout.NORTH);
		copyFrame.add(copyPanel, BorderLayout.CENTER);
	}
	/*
	 * Constructs the popup menus which appear when the user right clicks a receipt
	 */
	private void generatePopups() {
		OriginalPopupListener originalListener = new OriginalPopupListener();
		CopyPopupListener copyListener = new CopyPopupListener();
		JMenuItem item;
		
		originalPopup = new JPopupMenu();
		item = new JMenuItem("Split");
		item.addActionListener(originalListener);
		originalPopup.add(item);
		item = new JMenuItem("Discount");
		item.addActionListener(originalListener);
		originalPopup.add(item);
		
		copyPopup = new JPopupMenu();
		item = new JMenuItem("Split");
		item.addActionListener(copyListener);
		copyPopup.add(item);
		item = new JMenuItem("Discount");
		item.addActionListener(copyListener);
		copyPopup.add(item);
	}
	/*
	 * Creates another copy of the receipt and selects it
	 */
	private void createCopy() {
		copyList.add(original.emptyCopy());
		copyList.get(copyList.size()-1).addReceiptListener(new CopyListener());
		copyList.get(copyList.size()-1).addReceiptWheelListener(new CopyScroller());
		copyList.get(copyList.size()-1).addReceiptItemListener(new CopyItemListener());
		copyList.get(copyList.size()-1).addReceiptWheelListener(new CopyScroller());
		copyBox.addItem(copyList.size());
		
		if(copyList.size() == 1) {
			contentPanel.add(copyFrame);
			contentPanel.repaint();
			contentPanel.validate();
		}
		
		copyBox.setSelectedIndex(copyList.size()-1);
		clearSelections();
	}
	/*
	 * Deletes a copy of a receipt moving all of its MenuItems back to the Original
	 */
	private void deleteCopy() {
		if(copyList.size() == 1) 
			deleteAll();
		else {
			for(Integer orderNumber : copyList.get(copyBox.getSelectedIndex()).getItems().keySet()) {
				if(copyList.get(copyBox.getSelectedIndex()).getItem(orderNumber).isSplit()) {
					MenuItem item = copyList.get(copyBox.getSelectedIndex()).getItem(orderNumber);
					item.split(item.getSplitCount()-1);
					
					if(original.getItems().containsKey(orderNumber))
						original.addItem(orderNumber, item);
					for(Receipt copy : copyList)
						if(copy.getItems().containsKey(orderNumber))
							copy.addItem(orderNumber, item);
				}
				else
					original.addItem(orderNumber, copyList.get(copyBox.getSelectedIndex()).getItem(orderNumber));
			}
			
			copyList.remove(copyBox.getSelectedIndex());
			
			if(copyBox.getSelectedIndex() == 0)
				viewCopy(0);
			else
				copyBox.setSelectedIndex(copyBox.getSelectedIndex()-1);
			
			copyBox.removeItemAt(copyBox.getItemCount()-1);
			clearSelections();
		}
	}
	/*
	 * Deletes all Receipt Copies, moving their MenuItems back to the Original
	 */
	private void deleteAll() {
		for(Receipt receipt : copyList)
			for(Integer orderNumber : receipt.getItems().keySet()) {
				if(receipt.getItem(orderNumber).isSplit())
					receipt.getItem(orderNumber).split(1);
				original.addItem(orderNumber, receipt.getItem(orderNumber));
			}
		
		copyList.clear();
		copyBox.removeAllItems();
		
		contentPanel.remove(copyFrame);
		contentPanel.repaint();
		contentPanel.validate();
		clearSelections();
	}
	/*
	 * Changes the receipt copy gui currently inhabiting the JPanel created to contain them
	 */
	private void viewCopy(int index) {
		copyPanel.removeAll();
		copyPanel.add(copyList.get(index).getGUI());
		copyPanel.validate();
	}
	/*
	 * Transfers MenuItems betwen the Original receipt and currently active receipt copy
	 */
	private void transferSelection() {
		if(originalIsSelected && original.hasOrderSelected() && copyBox.getSelectedIndex() > -1) {
			copyList.get(copyBox.getSelectedIndex()).addItem(original.getSelectedOrderNum(), original.getSelectedItem());
			original.removeSelectedItem();
		}
		else if(copyIsSelected && copyBox.getSelectedIndex() > -1 && copyList.get(copyBox.getSelectedIndex()).hasOrderSelected()) {
			original.addItem(copyList.get(copyBox.getSelectedIndex()).getSelectedOrderNum(), copyList.get(copyBox.getSelectedIndex()).getSelectedItem());
			copyList.get(copyBox.getSelectedIndex()).removeSelectedItem();
		}
		clearSelections();
	}
	/*
	 * Clears all selections from the Receipt GUIS
	 */
	private void clearSelections() {
		original.clearSelection();
		if(copyList.size() > 0)
			copyList.get(copyBox.getSelectedIndex()).clearSelection();
		originalIsSelected = false;
		copyIsSelected = false;
	}
	/*
	 * Brings up a JDialog box to allow the user to split a MenuItem between several different receipts
	 */
	private void splitItem(Receipt receipt) {
		if(receipt.hasOrderSelected()) {
			int orderNumber = receipt.getSelectedOrderNum();
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			JCheckBox originalCheckBox = new JCheckBox("Original", original.getItems().containsKey(orderNumber));
			originalCheckBox.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
			panel.add(originalCheckBox);
			
			JCheckBox[] copyCheckBox = new JCheckBox[copyList.size()];
			for(int i=0; i < copyList.size(); i++) {
				copyCheckBox[i] = new JCheckBox("Copy " + (i+1), copyList.get(i).getItems().containsKey(orderNumber));
				copyCheckBox[i].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
				panel.add(copyCheckBox[i]);
			}
			
			JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setSize(150,300);
			
			dialogChoice = -1;
			showDialog(scrollPane, "Split Item", new String[] { "Split", "Cancel" });
			
			if(dialogChoice == 0) {
				MenuItem item = receipt.getItem(orderNumber);
				
				original.removeItem(orderNumber);
				for(Receipt copy : copyList)
					copy.removeItem(orderNumber);
				
				int splitCount = 0;
				if(originalCheckBox.isSelected())
					splitCount++;
				for(JCheckBox box : copyCheckBox)
					if(box.isSelected())
						splitCount++;
				
				item.split(splitCount);
				
				if(originalCheckBox.isSelected())
					original.addItem(orderNumber, item);
				for(int i=0; i < copyCheckBox.length; i++)
					if(copyCheckBox[i].isSelected())
						copyList.get(i).addItem(orderNumber, item);
			}
		}
		clearSelections();
	}
	/*
	 * Brings up a JDialog box to allow the user to change the discount for a MenuItem
	 */
	private void discountItem(Receipt receipt) {
		if(receipt.hasOrderSelected()) {
			MenuItem item = receipt.getSelectedItem();
			int orderNumber = receipt.getSelectedOrderNum();
			
			JPanel panel = new JPanel();
			panel.setSize(150,100);
			
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
				
				if(original.getItems().containsKey(orderNumber))
					original.addItem(orderNumber, item);
				for(Receipt copy : copyList)
					if(copy.getItems().containsKey(orderNumber))
						copy.addItem(orderNumber, item);
			}
		}
		clearSelections();
	}
	/*
	 * Refreshes the receipt, causing its GUI to rewrite its MenuItem data
	 */
	private void refreshReceipt(Receipt receipt) {
		receipt.refresh();
	}
	/*
	 * Shows a dialog box containing the argument JComponent, with the argument String title, and a number of buttons equal to the String array
	 * provided for the button text. A integer, dialogChoice, is set to the index value of the button selected according to that buttons text
	 * in the string array. If the user exits the dialog box, dialog choice is set to -1
	 */
	private void showDialog(JComponent component, String title, String[] buttonText) {
		dialog = new JDialog(this, title, true);
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
			if(e.getActionCommand().equals("Create Copy"))
				createCopy();
			else if(e.getActionCommand().equals("Delete Copy"))
				deleteCopy();
			else if(e.getActionCommand().equals("Delete All"))
				deleteAll();
		}	
	}
	/*
	 * Responds to the JComboBox which contains the number of current copies
	 */
	private class CopyBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				viewCopy(copyBox.getSelectedIndex());
		}
	}
	/*
	 * responds to mouse clicks within the entier Original receipt gui
	 */
	private class OriginalListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(copyIsSelected)
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
	/*
	 * Responds to mouse clicks on the original receipt's JTable displaying its 
	 * MenuItems
	 */
	private class OriginalItemListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger())
				originalPopup.show(e.getComponent(), e.getX(), e.getY());
			else if(copyIsSelected)
				transferSelection();
			else if(e.getClickCount() == 2) {
				originalIsSelected = true;
				transferSelection();
			}
			else
				originalIsSelected = true;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.isPopupTrigger())
				originalPopup.show(e.getComponent(), e.getX(), e.getY());
		}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}		
	}
	/*
	 * Responds to mouse clicks over a receipt copy's enter gui
	 */
	private class CopyListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(originalIsSelected)
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
	/*
	 * Responds to mouse clicks for copy's JTable displaying its MenuItems
	 */
	private class CopyItemListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger())
				copyPopup.show(e.getComponent(), e.getX(), e.getY());
			else if(originalIsSelected)
				transferSelection();
			else if(e.getClickCount() == 2) {
				copyIsSelected = true;
				transferSelection();
			}
			else
				copyIsSelected = true;
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.isPopupTrigger())
				copyPopup.show(e.getComponent(), e.getX(), e.getY());
		}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	/*
	 * Responds to clicks in the ReceiptManager's background by clearing selections
	 */
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
	/*
	 * Responds to the popup menu for the original receipt
	 */
	private class OriginalPopupListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Split"))
				splitItem(original);
			else if(e.getActionCommand().equals("Discount"))
				discountItem(original);
			else if(e.getActionCommand().equals("Refresh"))
				refreshReceipt(original);
		}
		
	}
	/*
	 * Reponds to the popup menu for the receipt copies
	 */
	private class CopyPopupListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Split"))
				splitItem(copyList.get(copyBox.getSelectedIndex()));
			else if(e.getActionCommand().equals("Discount"))
				discountItem(copyList.get(copyBox.getSelectedIndex()));
			else if(e.getActionCommand().equals("Refresh"))
				refreshReceipt(copyList.get(copyBox.getSelectedIndex()));
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
	 * Responds to the Exits button by closing this ReceiptManager
	 */
	private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	/*
	 * Responds to mouse wheel events over the copy gui by scrolling through the created Receipt copies
	 */
	private class CopyScroller implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int movement= copyBox.getSelectedIndex() + e.getWheelRotation();
			if(movement >= 0 && movement < copyBox.getItemCount())
				copyBox.setSelectedIndex(movement);
		}
	}
}

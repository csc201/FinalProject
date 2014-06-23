package guisForLayeredPane;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import pointOfSale.SystemInit;
import pointOfSale.Tools;

public class GuiForCreditSale extends JPanel implements ActionListener{

	private JTextField cardNo;
	private pointOfSale.MenuButton cancel;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final  Color PALE_GOLDENROD = new Color(238,232,170);
	
	public GuiForCreditSale(String message){
		cardNo = new JTextField(20);
		cancel = new pointOfSale.MenuButton("Cancel", "Cancel", this);
		cardNo.requestFocusInWindow();
		
		setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridwidth = GridBagConstraints.REMAINDER;
        con.weightx = 1.0;
        con.weighty = 2.0;
        con.fill = GridBagConstraints.BOTH;
        add(new JLabel(message, SwingConstants.CENTER), con);
        con.weighty = 1.0;
        add(cardNo, con);
        con.insets = new Insets(20, 10, 10, 10);
        con.weighty = 2.0;
        add(cancel, con);
        
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setBackground(DARK_CHAMPAGNE);
        setSize(300, 200);
        
        System.out.println(cardNo.isFocusOwner());
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Cancel")){
			SystemInit.swap();
		}
	}



}

package giftcard;

import java.awt.*;
import javax.swing.*;

public class GiftcardGUI extends JFrame {

	JPanel buttonPanel = new JPanel();

	public GiftcardGUI(JPanel parent) {
		buttonPanel.setLayout(new GridLayout(4, 3));
		// GridBagConstraints c = new GridBagConstraints();
		for (int i = 1; i <= 9; i++)
			buttonPanel.add(new JButton("" + i));
		buttonPanel.add(new JButton("" + 0));
		buttonPanel.add(new JButton("."));
		buttonPanel.add(new JButton("Enter"));

		JPanel giftPanel = new JPanel(new BorderLayout());
		giftPanel.add(new JTextField("Enter the amount of giftcard."),
				BorderLayout.NORTH);
		giftPanel.add(buttonPanel, BorderLayout.CENTER);
		add(giftPanel);
		int x = parent.getParent().getWidth() / 2 - 100;
		int y = parent.getParent().getHeight() / 2 - 100;
		setLocation(x, y);
		setSize(200, 200);
		setVisible(true);
		pack();
	}
}

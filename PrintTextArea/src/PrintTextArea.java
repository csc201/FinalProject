import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PrintTextArea extends JFrame implements ActionListener {
	JButton jbtPrint = new JButton();
	JTextArea txtArea = new JTextArea();

	public PrintTextArea() {
		add(jbtPrint, BorderLayout.NORTH);
		add(txtArea, BorderLayout.SOUTH);
		txtArea.setEditable(true);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		new PrintTextArea();
	}
	//@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Print"));
		try{
			txtArea.print();
		}catch(PrinterException e1)
		{
			e1.printStackTrace();
		}
	}

}
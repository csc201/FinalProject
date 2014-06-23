package pointOfSale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

public class LayeredPanels{

	private JLayeredPane wrap;
	private JPanel screen;
	private JPanel popup;
	private JPanel origin;
	private JButton button;
	private Color buttonColor;
	
	public LayeredPanels(JPanel parent, JPanel orig, JPanel gui){
		 
		wrap = new JLayeredPane();
		screen = new JPanel();
		popup = gui;
		origin = orig;
		
		
		screen.setBackground(Color.BLACK);
		screen.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}
			
		});
		screen.addInputMethodListener(new InputMethodListener(){

			@Override
			public void caretPositionChanged(InputMethodEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}
			
		});
	
		screen.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}
			
		});
		screen.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				arg0.consume();
			}
			
		});
		
		Dimension size = new Dimension(parent.getWidth()-40, parent.getHeight()-40);
		screen.setSize(size);
		origin.setSize(size);
		
        int x = parent.getParent().getWidth()/2-popup.getWidth()/2;
        int y = parent.getParent().getHeight()/2-popup.getHeight()/2;
 
        popup.setLocation(x, y);
		
		screen.setVisible(false);
		popup.setVisible(false);
		
		screen.setBackground(new Color(0, 0, 0, 64));
		
		
		wrap.add(screen, 1);
		wrap.add(popup, 0);
		//wrap.add(origin, JLayeredPane.POPUP_LAYER);
		wrap.add(origin, -1);
		popup.requestFocusInWindow();
		System.out.println(popup.isFocusOwner() + ": POPUP");

		System.out.println(origin.isFocusOwner() + ": ORIGIN");

		System.out.println(wrap.isFocusOwner() + ": WRAP");

		System.out.println(screen.isFocusOwner() + ": SCREEN");
		
	}
	public void setButton(JButton but, Color col){
		button = but;
		buttonColor = col;
	}
	
	public void swap(){
		if(screen.isVisible() == true){
			screen.setVisible(false);
			popup.setVisible(false);
			origin.setEnabled(true);
			origin.setFocusable(true);
			origin.setFocusTraversalKeysEnabled(true);
			origin.setFocusTraversalPolicyProvider(true);
			button.setBackground(buttonColor);	
		}
		else {
			screen.setVisible(true);
			popup.setVisible(true);	
			popup.requestFocusInWindow();
			origin.setEnabled(false);
			origin.setFocusable(false);
			origin.setFocusTraversalKeysEnabled(false);
			origin.setFocusTraversalPolicyProvider(false);
			button.setBackground(Color.GREEN);
		}
	}
	
	public JLayeredPane getPane(){
		return wrap;
	}
		/*
		if(wrap.getLayer(origin)==JLayeredPane.POPUP_LAYER){
			wrap.setLayer(origin, JLayeredPane.DEFAULT_LAYER);
			screen.setVisible(true);
			popup.setVisible(true);
		}
		else if(wrap.getLayer(origin)==JLayeredPane.POPUP_LAYER){
			wrap.setLayer(origin, JLayeredPane.MODAL_LAYER);
			screen.setVisible(false);
			popup.setVisible(false);
		}*/
	
}

package net.tfobz.webbrowser;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.plaf.basic.BasicArrowButton;
import java.net.*;

import java.awt.event.*;

public class MyWeb extends JFrame{
	
	private JTextField addressBar;
	private JLabel label;
	private JButton button;
	private JScrollPane scroll;
    private JEditorPane pane;
	private String URL = "https://www.google.de";
	
	
	public MyWeb() {
		JFrame frame = new JFrame();
		frame.setTitle("Webbrowser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
        addressBar = new JTextField();
        	addressBar.setBounds(100, 20, 600, 20);
        	
        label = new JLabel("Adresse:");
        		label.setBounds(10, 20, 70,20);
        		label.setDisplayedMnemonic('s');
        		label.setLabelFor(addressBar);
        	
        button = new BasicArrowButton(BasicArrowButton.EAST);
        	button.setBounds(730, 20, 20, 20);
        	
        pane = new JEditorPane();
        pane.setEditable(false);
        try {pane.setPage(URL);} catch (IOException e) { ; }
        
        scroll = new JScrollPane(pane);
        	scroll.setBounds(0, 50, 785, 515);
        	        	
        
        	
        frame.add(button);
        frame.add(label);
        frame.add(addressBar);
        frame.add(scroll);
        frame.setVisible(true);
        
        
        addressBar.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		
        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        	        try {pane.setPage(addressBar.getText());		
        	        	} catch (IOException a) { 
        	        	JOptionPane.showMessageDialog(frame, "Website nicht gefunden oder falsche URL", "Fehler", JOptionPane.ERROR_MESSAGE);
        	        }
        		}
        		frame.setTitle("Webbrowser - " + addressBar.getText());
        	}
        });
        
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
    	        try {pane.setPage(addressBar.getText());
    	        } catch (IOException a) { 
    	        	JOptionPane.showMessageDialog(frame, "Website nicht gefunden oder falsche URL", "Fehler", JOptionPane.ERROR_MESSAGE);
    	        }
    	        frame.setTitle("Webbrowser " + addressBar.getText());
			}
        });
        
        
        addressBar.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e){
            		addressBar.selectAll();            		
            }
        });
        
        
        
        
        pane.addHyperlinkListener(new HyperlinkListener() {
        	public void hyperlinkUpdate(HyperlinkEvent e) {
        		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
        			try {pane.setPage(e.getURL());
        		      }
        		      catch (Exception x) {
        		    	  JOptionPane.showMessageDialog(frame, "Kann Hyperlink nicht folgen", "Fehler", JOptionPane.ERROR_MESSAGE);
        		      }
        	        frame.setTitle("Webbrowser " + addressBar.getText());
                 }else {
                	 return;
                 	}
        	}
        });
	}
}
        
        
        

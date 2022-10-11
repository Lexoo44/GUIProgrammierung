package net.tfobz.euroumrechner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EuroUmrechnerGUI extends JFrame{

	private JLabel[] jLabels = new JLabel[13];
	private JTextField[] jTextFields = new JTextField[13];
	public Object[] jTextField;
	EuroUmrechner umrechner = new EuroUmrechner();

	
	public EuroUmrechnerGUI() {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("EuroUmrechner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(380, 460);
		
		int höhe = 80;
		for(int i = 0; i < umrechner.WAEHRUNGEN.length; i++) {
			if(i == 0) {
				höhe = 30;
			}else if(i == 1){
				höhe = 80;
			}
			
			jTextFields[i] = new JTextField();
				jTextFields[i].setBounds(210, höhe, 130, 20);
				jTextFields[i].addKeyListener(new MeinTastaturAbhoerer());
				jTextFields[i].setHorizontalAlignment(SwingConstants.RIGHT);
				

			jLabels[i] = new JLabel();
				jLabels[i].setText(umrechner.WAEHRUNGEN[i] + ":");
				jLabels[i].setBounds(30, höhe, 160, 20);
				jLabels[i].setHorizontalAlignment(SwingConstants.RIGHT);
				
				frame.add(jLabels[i]);
				frame.add(jTextFields[i]);	
			
			höhe += 25;
		}
		
		frame.setVisible(true);
	}
	
	private class MeinTastaturAbhoerer extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			try {
			for(int i = 0; i < umrechner.WAEHRUNGEN.length; i++) {
				 if (e.getSource() == jTextFields[i] ) {
	                    umrechner.setBetrag(Double.parseDouble(jTextFields[i].getText()));
	                    umrechner.setWaehrung(i);
	                    for (int j = 0; j <  jTextFields.length; j++) {
	                        if (j != i) {
	                            jTextFields[j].setText((double) Math.round(umrechner.getBetrag(j) * 100) / 100 + "");
	                        }
	                    }
	                }
	            }
	        } catch(NumberFormatException x) {
	        	System.out.println(e.getClass().getName());
	        }
			
		}
	}
}





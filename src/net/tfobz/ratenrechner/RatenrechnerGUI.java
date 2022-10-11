package net.tfobz.ratenrechner;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RatenrechnerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tbar;
	private JTextField tjahreszinssatz;
	private JTextField tlaufzeit;
	private JTextField trate;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RatenrechnerGUI frame = new RatenrechnerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RatenrechnerGUI() {
		RatenRechner rechner = new RatenRechner();
		setTitle("Ratenrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Vorschüssig");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnNewRadioButton.setBounds(125, 43, 113, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Nachschüssig");
		rdbtnNewRadioButton_1.setBounds(261, 43, 113, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton);
		
		//Barwert
		tbar = new JTextField();
		tbar.setHorizontalAlignment(SwingConstants.RIGHT);
		tbar.setBounds(152, 87, 86, 20);
		contentPane.add(tbar);
		tbar.setColumns(10);
		
		JButton bbar = new JButton("Berechne Barwert");
		bbar.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection() == rdbtnNewRadioButton || rdbtnNewRadioButton.isSelected() == true) {
					rechner.setNachschuessig(false);
				}
				else {
					rechner.setNachschuessig(true);
					rdbtnNewRadioButton_1.setSelected(true);
				}
				try {
					rechner.setJahresZinssatz(tjahreszinssatz.getText());
					rechner.setLaufZeitInJahren(tlaufzeit.getText());
					rechner.setRate(trate.getText());
					rechner.setRatenProJahr(comboBox.getSelectedItem().toString());
					tbar.setText(rechner.getBarwert());

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Falsche eingabe oder leeres Feld", "Fehler", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		bbar.setBounds(261, 86, 150, 23);
		contentPane.add(bbar);
		
		//Jahreszinssatz
		tjahreszinssatz = new JTextField();
		tjahreszinssatz.setHorizontalAlignment(SwingConstants.RIGHT);
		tjahreszinssatz.setBounds(152, 118, 86, 20);
		contentPane.add(tjahreszinssatz);
		tjahreszinssatz.setColumns(10);
		
		tlaufzeit = new JTextField();
		tlaufzeit.setHorizontalAlignment(SwingConstants.RIGHT);
		tlaufzeit.setBounds(152, 149, 86, 20);
		contentPane.add(tlaufzeit);
		tlaufzeit.setColumns(10);
		
		JButton blauf = new JButton("Berechne Laufzeit");
		blauf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection() == rdbtnNewRadioButton || rdbtnNewRadioButton.isSelected() == true) {
					rechner.setNachschuessig(false);
				}
				else {
					rechner.setNachschuessig(true);
					rdbtnNewRadioButton_1.setSelected(true);
				}
				
				try {
					rechner.setBarwert(tbar.getText());
					rechner.setJahresZinssatz(tjahreszinssatz.getText());
					rechner.setRate(trate.getText());
					rechner.setRatenProJahr(comboBox.getSelectedItem().toString());
					tlaufzeit.setText(rechner.getLaufzeitInJahren());

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Falsche eingabe oder leeres Feld", "Fehler", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		blauf.setBounds(261, 148, 150, 23);
		contentPane.add(blauf);
		
		trate = new JTextField();
		trate.setHorizontalAlignment(SwingConstants.RIGHT);
		trate.setBounds(152, 213, 86, 20);
		contentPane.add(trate);
		trate.setColumns(10);
		
		JButton brate = new JButton("Berechne Rate");
		brate.setBounds(261, 214, 150, 23);
		contentPane.add(brate);
		brate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(group.getSelection() == rdbtnNewRadioButton || rdbtnNewRadioButton.isSelected() == true) {
					rechner.setNachschuessig(false);
				}
				else {
					rechner.setNachschuessig(true);
					rdbtnNewRadioButton_1.setSelected(true);
				}
				
				try {
					rechner.setBarwert(tbar.getText());
					rechner.setJahresZinssatz(tjahreszinssatz.getText());
					rechner.setLaufZeitInJahren(tlaufzeit.getText());
					rechner.setRatenProJahr(comboBox.getSelectedItem().toString());
					trate.setText(rechner.getRate());

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Falsche eingabe oder leeres Feld", "Fehler", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		JButton btilg = new JButton("Tilgungsplan");
		btilg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TilgungsplanGUI t = new TilgungsplanGUI(RatenrechnerGUI.this);
				rechner.setJahresZinssatz(tjahreszinssatz.getText());
				rechner.setLaufZeitInJahren(tlaufzeit.getText());
				rechner.setRate(trate.getText());
				rechner.setRatenProJahr(comboBox.getSelectedItem().toString());
				rechner.setBarwert(tbar.getText());
				t.setVisible(true);
				t.dispose();
			}
		});
		btilg.setBounds(341, 265, 119, 23);
		contentPane.add(btilg);
		
		
		JLabel lblNewLabel = new JLabel("Barwert: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(69, 90, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Jahreszinssatz:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(35, 121, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Laufzeit in Jahren:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 152, 123, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rate:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(92, 216, 44, 14);
		contentPane.add(lblNewLabel_3);
		
		String[] combolist = {"1", "4", "6", "12"};
		comboBox = new JComboBox(combolist);
		comboBox.setToolTipText("");
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(152, 180, 86, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Raten pro Jahr:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(43, 184, 93, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("RATENRECHNER");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(165, 11, 175, 25);
		contentPane.add(lblNewLabel_5);
	}
}

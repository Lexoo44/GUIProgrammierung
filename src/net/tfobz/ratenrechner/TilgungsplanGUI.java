package net.tfobz.ratenrechner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;

public class TilgungsplanGUI extends JDialog{
	
	private JButton button;
	private JScrollPane scroll;
    private JEditorPane pane;
	private RatenRechner rechner = new RatenRechner();
	private File f;
    private boolean acceptable = false;


	
	
	public TilgungsplanGUI( JFrame parent) {
		super(parent);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		this.setSize(500, 600);
		this.getContentPane().setLayout(null);
		this.setTitle("Tilgungsplan");
		
		pane = new JEditorPane();
        pane.setEditable(false);
        pane.setContentType("text/html");
        pane.setText(rechner.getTilgungsplan());
        
        scroll = new JScrollPane(pane);
        	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        	scroll.setBounds(0, 0, 485, 510);
        
        button = new JButton("Speichern");
        	button.setBounds(350, 520, 100, 25);
        	
        this.add(button);
        this.add(scroll);
        
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
				
				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						f = jfc.getSelectedFile();
						if(f.exists()) {
							int result = JOptionPane.showConfirmDialog(new JFrame(), "The file exists, overwrite?",
			                        "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
							if (result == JOptionPane.YES_OPTION) {
			                    acceptable = true;
			                }
						}
						else {
							acceptable = false;
							BufferedWriter out = new BufferedWriter(new FileWriter(jfc.getSelectedFile().getPath() + ".html"));
						}
					}catch(Exception x) {
						System.out.println(x.getClass().getName());
	                    System.out.println(x.getMessage());
					}
				}
				
			}
		});
	}
}

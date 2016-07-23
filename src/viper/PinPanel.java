package viper;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PinPanel {

	public JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PinPanel window = new PinPanel(null,null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param newUser 
	 * @param code 
	 */
	public PinPanel(String code, CurrentProfile newUser) {
		initialize(code,newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String code, CurrentProfile newUser) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 402, 170);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("PIN");
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			//	if(c==KeyEvent.VK_ENTER)
			}
		});
		passwordField.setBounds(92, 51, 273, 25);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPin = new JLabel("PIN");
		lblPin.setBounds(22, 57, 46, 14);
		frame.getContentPane().add(lblPin);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CurrentProfile.checkPin(passwordField.getText(),newUser.uid)){
					
					String path=FileClient.getPathViper(code)+newUser.uid+".txt";
					FileClient.connectToServer(code,""+newUser.uid+".txt");
					JOptionPane.showMessageDialog(null, "Content fetched!!!");
					if (Desktop.isDesktopSupported()) {
			            try {
			                
			            	File myFile = new File( path);
			                Desktop.getDesktop().open(myFile);
			                frame.dispose();
			            } catch (IOException ex) {
			                // no application registered for PDFs
			            	ex.printStackTrace();
			            	JOptionPane.showMessageDialog(null, "error!! "+ex);
			            }
			        }
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "retry!!!");
				}
			}
		});
		button.setBounds(276, 107, 89, 23);
		frame.getContentPane().add(button);
		
		
	}
}

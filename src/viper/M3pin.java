package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class M3pin {

	JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M3pin window = new M3pin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public M3pin(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 387, 154);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("PIN");
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setBounds(26, 46, 56, 18);
		frame.getContentPane().add(lblPin);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CurrentProfile.checkPin(passwordField.getText(), newUser.uid)){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								M3Panel window = new M3Panel(newUser);
								frame.dispose();
								window.frmMDetails.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});					
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong pin!!");
				}
			}
		});
		button.setBounds(162, 81, 93, 30);
		frame.getContentPane().add(button);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		passwordField.setBounds(94, 42, 239, 25);
		frame.getContentPane().add(passwordField);
	}

}

package viper;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class EditPin {

	JFrame frmPin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPin window = new EditPin();
					window.frmPin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public EditPin(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newuser){
		frmPin = new JFrame();
		frmPin.setResizable(false);
		frmPin.setTitle("Pin");
		frmPin.setBounds(100, 100, 450, 186);
		frmPin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPin.getContentPane().setLayout(null);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setBounds(34, 48, 56, 18);
		frmPin.getContentPane().add(lblPin);
		
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
		passwordField.setBounds(86, 40, 293, 35);
		frmPin.getContentPane().add(passwordField);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CurrentProfile.checkPin(passwordField.getText(),newuser.uid)){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								EditPanel window = new EditPanel(newuser);
								frmPin.dispose();
								window.frmEdit.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				else{
					JOptionPane.showMessageDialog(null, "retry!!!");
				}
			}
		});
		button.setBounds(286, 109, 93, 30);
		frmPin.getContentPane().add(button);
		
		JButton btnCan = new JButton("<<");
		btnCan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CurrentProfile.fetchAll(newuser);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UserProfilePanel window = new UserProfilePanel(newuser);
							window.frame.setVisible(true);
							frmPin.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnCan.setBounds(86, 109, 93, 30);
		frmPin.getContentPane().add(btnCan);
	}
}

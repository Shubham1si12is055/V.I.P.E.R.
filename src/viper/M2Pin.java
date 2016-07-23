package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class M2Pin {

	JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M2Pin window = new M2Pin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public M2Pin(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 415, 165);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("PIN");
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setBounds(44, 37, 56, 18);
		frame.getContentPane().add(lblPin);
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
		passwordField.setBounds(92, 33, 300, 25);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CurrentProfile.checkPin(passwordField.getText(), newUser.uid)){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								M2Pane window = new M2Pane(newUser);
								frame.dispose();
								window.frmM.setVisible(true);
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
		button.setBounds(299, 82, 93, 30);
		frame.getContentPane().add(button);
		
	
	}
}

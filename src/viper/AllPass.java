package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AllPass {

	public  JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllPass window = new AllPass();
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
	public AllPass(CurrentProfile otherPofile,CurrentProfile newuser,int c) {
		initialize(otherPofile,newuser,c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile otherPofile,CurrentProfile newuser,int c) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("DOCTOR Password Pane");
		frame.setBounds(100, 100, 385, 207);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(34, 44, 82, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 41, 212, 23);
		frame.getContentPane().add(passwordField);
		
		JButton btnNext = new JButton(">>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				String passwd=passwordField.getText();
				
				boolean valid=false;
				
				if(passwd.length()==0){
					JOptionPane.showMessageDialog(null, "plz enter username and password");
				}
				else{
				
					try {
						
						MessageDigest m = null;
						try {
							m = MessageDigest.getInstance("MD5");
							} catch (NoSuchAlgorithmException nA) {
							// TODO Auto-generated catch block
								nA.printStackTrace();
								JOptionPane.showMessageDialog(null, "NoSuchAlgorithmException ");
							}				
						m.reset();
						m.update(passwd.getBytes());
						byte[] digest=m.digest();
						BigInteger bigInt=new BigInteger(1,digest);
						String hashtext=bigInt.toString(16);
						while(hashtext.length()<32){
							hashtext="0"+hashtext;
						}
						valid = CurrentProfile.usernamePasswordCheck(newuser.username,hashtext);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(valid){
						if(CurrentProfile.checkPin(passwordField_1.getText(),newuser.uid)){
							if(c==2){
								EventQueue.invokeLater(new Runnable() {
							
								public void run() {
									try {
										M2Pane window = new M2Pane(otherPofile);
										window.frmM.setVisible(true);
										frame.dispose();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								
							});
							}else{
								EventQueue.invokeLater(new Runnable() {
									public void run() {
										try {
											KeenDetail window = new KeenDetail(otherPofile);
											frame.dispose();
											window.frmKeenDetails.setVisible(true);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Wrong pin!!!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Wrong password!!!");
					}
				
				}
				
				
			}
		});
		btnNext.setBounds(251, 135, 89, 23);
		frame.getContentPane().add(btnNext);
		
		JLabel lblKey = new JLabel("Doctor Key");
		lblKey.setBounds(34, 88, 82, 14);
		frame.getContentPane().add(lblKey);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		passwordField_1.setBounds(128, 85, 212, 20);
		frame.getContentPane().add(passwordField_1);
	}
}

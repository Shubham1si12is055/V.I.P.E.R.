package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChangePassword {

	public JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangePassword(CurrentProfile user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Change Password");
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setBounds(49, 49, 109, 23);
		frame.getContentPane().add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(49, 100, 109, 23);
		frame.getContentPane().add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(49, 149, 109, 23);
		frame.getContentPane().add(lblConfirmPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 50, 173, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(196, 101, 173, 20);
		frame.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(196, 150, 173, 20);
		frame.getContentPane().add(passwordField_2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//String username=txtfUsername.getText();
				String oldPasswd=passwordField.getText();
				String newPasswd=passwordField_1.getText();
				String conPasswd=passwordField_2.getText();
				
				boolean valid=false;
				
				if(oldPasswd.length()==0||newPasswd.length()==0||conPasswd.length()==0){
					JOptionPane.showMessageDialog(null, "plz enter password");
				}
				else if(!newPasswd.equals(conPasswd)){
					JOptionPane.showMessageDialog(null, "password  not matching");
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
						m.update(oldPasswd.getBytes());
						byte[] digest=m.digest();
						BigInteger bigInt=new BigInteger(1,digest);
						String hashtext=bigInt.toString(16);
						while(hashtext.length()<32){
							hashtext="0"+hashtext;
						}
						valid = CurrentProfile.usernamePasswordCheck(user.username,hashtext);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(valid){
						
						
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
							m.update(newPasswd.getBytes());
							byte[] digest=m.digest();
							BigInteger bigInt=new BigInteger(1,digest);
							String hashtext=bigInt.toString(16);
							while(hashtext.length()<32){
								hashtext="0"+hashtext;
							}
							CurrentProfile.changePassword(user.uid,hashtext);
							
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						JOptionPane.showMessageDialog(null, "PASSWORD CHANGED!!!");
						frame.dispose();
						CurrentProfile user = new CurrentProfile();
						CurrentProfile.fetchAll(user);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									UserProfilePanel window = new UserProfilePanel(user);
									window.frame.setVisible(true);
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					else{
						JOptionPane.showMessageDialog(null, "old password didn't match!!!");
						frame.dispose();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									ChangePassword window = new ChangePassword(user);
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					
				}
			}
		});
	
	
		btnOk.setBounds(196, 208, 89, 23);
		frame.getContentPane().add(btnOk);
		
}
}
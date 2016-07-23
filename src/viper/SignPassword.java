package viper;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SignPassword {

	public JFrame frmSignUpPass;
	private JTextField txtUid;
	private JTextField txtUsername;
	private JPasswordField pswd1;
	private JPasswordField pswd2;
	public int uid;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignPassword window = new SignPassword();
					window.frmSignUpPass.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SignPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignUpPass = new JFrame();
		frmSignUpPass.setTitle("SIGN UP: Password");
		frmSignUpPass.setResizable(false);
		frmSignUpPass.setBounds(100, 100, 441, 316);
		frmSignUpPass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUpPass.getContentPane().setLayout(null);
		frmSignUpPass.setVisible(true);
		
		uid=-1;
		try{
			uid= CurrentProfile.getUid();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
		}
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(34, 56, 46, 14);
		frmSignUpPass.getContentPane().add(lblUid);
		
		txtUid = new JTextField();
		txtUid.setBounds(172, 53, 219, 20);
		frmSignUpPass.getContentPane().add(txtUid);
		txtUid.setColumns(10);
		txtUid.setText(Integer.toString(uid));
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(34, 106, 86, 14);
		frmSignUpPass.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(172, 103, 219, 20);
		frmSignUpPass.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(34, 148, 86, 14);
		frmSignUpPass.getContentPane().add(lblPassword);
		
		JLabel lblPassword_1 = new JLabel("PASSWORD");
		lblPassword_1.setBounds(34, 190, 86, 14);
		frmSignUpPass.getContentPane().add(lblPassword_1);
		
		pswd1 = new JPasswordField();
		pswd1.setBounds(172, 145, 219, 20);
		frmSignUpPass.getContentPane().add(pswd1);
		
		pswd2 = new JPasswordField();
		pswd2.setBounds(172, 187, 219, 20);
		frmSignUpPass.getContentPane().add(pswd2);
		
		JButton btnBack = new JButton("<-BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmSignUpPass.dispose();
			}
		});
		btnBack.setBounds(34, 237, 89, 23);
		frmSignUpPass.getContentPane().add(btnBack);
		
		JButton btnNext = new JButton("NEXT->");
		btnNext.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String pass1;
					pass1=pswd1.getText();
					String pass2=pswd2.getText();
					if(!pass1.equals(pass2)){
						
						JOptionPane.showMessageDialog(null, "THE TWO PASSWORD DOES NOT MATCH!!!");
						
						frmSignUpPass.setVisible(false);
						
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									
									SignPassword  window = new SignPassword ();
									window.frmSignUpPass.setVisible(true);
									frmSignUpPass.dispose();
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
						frmSignUpPass.dispose();
						
					}
					else{
						MessageDigest m = null;
						try {
							m = MessageDigest.getInstance("MD5");
							} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "NoSuchAlgorithmException ");
							}
						CurrentProfile newUser=new CurrentProfile();
				
				m.reset();
				m.update(pass1.getBytes());
				byte[] digest=m.digest();
				BigInteger bigInt=new BigInteger(1,digest);
				String hashtext=bigInt.toString(16);
				while(hashtext.length()<32){
					hashtext="0"+hashtext;
				}
				newUser.password=hashtext;
				String username =txtUsername.getText();
				newUser.username=username;
				newUser.uid=uid;
				boolean valid = false;
				
					valid = newUser.loginTableInsert(hashtext);
				
				if(valid==false){
					//JOptionPane.showMessageDialog(null, "no database server connection!!!");
					frmSignUpPass.setVisible(false);
					
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								
								SignPassword  window = new SignPassword ();
								window.frmSignUpPass.setVisible(true);
								frmSignUpPass.dispose();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
					frmSignUpPass.dispose();
				}
				
			else{	
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignL1Panel window = new SignL1Panel(newUser);
							window.frmSignUp.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frmSignUpPass.dispose();
							
					}
					}
			}	
		});
		
		btnNext.setBounds(303, 237, 89, 23);
		frmSignUpPass.getContentPane().add(btnNext);
		frmSignUpPass.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNext, txtUid, txtUsername, pswd1, pswd2, lblUid, lblUsername, lblPassword, lblPassword_1, btnBack}));
	}

	
}

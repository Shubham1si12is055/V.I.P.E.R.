package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
public class login {

	public JFrame frmViper;
	private JTextField txtfUsername;
	private JPasswordField passwordField;
	//static boolean flag=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmViper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		frmViper = new JFrame();
		frmViper.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		frmViper.setTitle("V.I.P.E.R");
		frmViper.setResizable(false);
		frmViper.setLocationRelativeTo(null);
		frmViper.setBounds(100, 100, 450, 269);
		frmViper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViper.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 444, 237);
		frmViper.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		lblUsername.setBounds(53, 81, 69, 14);
		panel.add(lblUsername);
		
		txtfUsername = new JTextField();
		txtfUsername.setBounds(165, 76, 246, 26);
		panel.add(txtfUsername);
		txtfUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		lblPassword.setBounds(53, 139, 69, 14);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 128, 246, 26);
		panel.add(passwordField);
		
		JButton signInButton = new JButton("SIGN IN");
		signInButton.setBackground(Color.yellow);
		signInButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							//frmViper.setVisible(false);
							SureSignIn window = new SureSignIn();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		signInButton.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		signInButton.setBounds(53, 196, 89, 37);
		panel.add(signInButton);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.setBackground(Color.green);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username=txtfUsername.getText();
				String passwd=passwordField.getText();
				
				boolean valid=false;
				
				if(username.length()==0||passwd.length()==0){
					JOptionPane.showMessageDialog(null, "plz enter username and password");
				}
				else{
				
					try {
						
						MessageDigest m = null;
						try {
							m = MessageDigest.getInstance("MD5");
							} catch (NoSuchAlgorithmException nA) {
		
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
						valid = CurrentProfile.usernamePasswordCheck(username,hashtext);
						
						
					} catch (Exception e1) {
						// 
						e1.printStackTrace();
					}
					if(valid){
						//JOptionPane.showMessageDialog(null, "username and password ACCEPTED!!!");
						CurrentProfile user = new CurrentProfile();
						user.username=username;
						CurrentProfile.fetchAll(user);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									UserProfilePanel window = new UserProfilePanel(user);
									window.frame.setVisible(true);
									frmViper.dispose();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Incorrect username or password!!!");
					}
				}
				
			}

			
		});
		loginButton.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		loginButton.setBounds(322, 196, 89, 37);
		panel.add(loginButton);
		
		JButton btnGuest = new JButton("Guest");
		btnGuest.setBackground(Color.ORANGE);
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {			
					public void run() {
						CurrentProfile guest=new CurrentProfile();
						guest.uid=9999;
						guest.nameFirst="guest";
						guest.doctor=0;
						guest.key=9999;
						try {
							SearchPanel window = new SearchPanel(guest);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnGuest.setBounds(322, 12, 89, 28);
		panel.add(btnGuest);
		
		JButton btnServer = new JButton("serverIP");
		btnServer.setBackground(Color.DARK_GRAY);
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							IPAdress window = new IPAdress();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnServer.setBounds(223, 12, 98, 28);
		panel.add(btnServer);
		
		JButton btnNewButton = new JButton("StartServer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ServerPanel window = new ServerPanel();
							frmViper.dispose();
							window.frmServer.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(49, 10, 162, 30);
		panel.add(btnNewButton);
	}
}

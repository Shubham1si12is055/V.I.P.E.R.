package viper;

import java.awt.EventQueue;

import javax.naming.ldap.Rdn;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignL2Panel {

	public JFrame frmSignUp2;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JLabel lblCity;
	private JTextField txtCity;
	private JLabel lblState;
	private JTextField txtState;
	private JLabel lblPin;
	private JTextField txtPin;
	private JLabel lblReligion;
	private JTextField txtReligion;
	private JLabel lblResume;
	private JTextField txtResume;
	private JButton btnUpload;
	private JButton btnNext;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignL2Panel window = new SignL2Panel();
					window.frmSignUp2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}8/

	/**
	 * Create the application.
	 * @param newUser 
	 */
	public SignL2Panel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp2 = new JFrame();
		frmSignUp2.setTitle("SIGN UP (2/6)");
		frmSignUp2.setResizable(false);
		frmSignUp2.setBounds(100, 100, 644, 455);
		frmSignUp2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp2.getContentPane().setLayout(null);
		frmSignUp2.setVisible(true);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(511, 32, 133, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frmSignUp2.getContentPane().add(lblUid);
		
		
		JLabel lblPhone = new JLabel("PHONE");
		lblPhone.setBounds(63, 60, 100, 14);
		frmSignUp2.getContentPane().add(lblPhone);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(63, 107, 82, 14);
		frmSignUp2.getContentPane().add(lblAddress);
		
		txtPhone = new JTextField();
		txtPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		txtPhone.setText("0");
		txtPhone.setBounds(163, 57, 383, 20);
		frmSignUp2.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(163, 104, 383, 20);
		frmSignUp2.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		lblCity = new JLabel("CITY");
		lblCity.setBounds(63, 157, 46, 14);
		frmSignUp2.getContentPane().add(lblCity);
		
		txtCity = new JTextField();
		txtCity.setBounds(163, 154, 173, 20);
		frmSignUp2.getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		lblState = new JLabel("STATE");
		lblState.setBounds(63, 207, 46, 14);
		frmSignUp2.getContentPane().add(lblState);
		
		txtState = new JTextField();
		txtState.setBounds(163, 204, 383, 20);
		frmSignUp2.getContentPane().add(txtState);
		txtState.setColumns(10);
		
		lblPin = new JLabel("PIN");
		lblPin.setBounds(368, 160, 46, 14);
		frmSignUp2.getContentPane().add(lblPin);
		
		txtPin = new JTextField();
		txtPin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		txtPin.setText("0");
		txtPin.setBounds(419, 154, 120, 20);
		frmSignUp2.getContentPane().add(txtPin);
		txtPin.setColumns(10);
		
		lblReligion = new JLabel("RELIGION");
		lblReligion.setBounds(63, 256, 70, 14);
		frmSignUp2.getContentPane().add(lblReligion);
		
		txtReligion = new JTextField();
		txtReligion.setBounds(163, 253, 173, 20);
		frmSignUp2.getContentPane().add(txtReligion);
		txtReligion.setColumns(10);
		
		lblResume = new JLabel("RESUME");
		lblResume.setBounds(63, 307, 70, 14);
		frmSignUp2.getContentPane().add(lblResume);
		
		txtResume = new JTextField();
		txtResume.setBounds(163, 304, 251, 20);
		frmSignUp2.getContentPane().add(txtResume);
		txtResume.setColumns(10);
		
		JRadioButton rdbtnDoctor = new JRadioButton("DOCTOR");
		rdbtnDoctor.setBounds(425, 251, 121, 24);
		frmSignUp2.getContentPane().add(rdbtnDoctor);
		
		btnUpload = new JButton("UPLOAD");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//newUser.resLoc="";
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "txt","docx","pdf");
				filechooser.setFileFilter(filter);
				//ThumbNailView thumbsView = new ThumbNailView();
				//filechooser.setAccessory(new ImagePreview(filechooser));
				int returnVal = filechooser.showDialog(null,"select an file");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = filechooser.getSelectedFile();
					txtResume.setText(file.getPath());
					newUser.resLoc=file.getPath();
					//newUser.resName=file.getName();
					newUser.resLoc=newUser.resLoc.replace("\\", "\\\\");
				}
			}
		});
		btnUpload.setBounds(457, 303, 89, 23);
		frmSignUp2.getContentPane().add(btnUpload);
		
		btnNext = new JButton(">>");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				newUser.phone=Long.parseLong(txtPhone.getText());
				newUser.address=txtAddress.getText();
				newUser.city=txtCity.getText();
				newUser.pin=Integer.parseInt(txtPin.getText());
				newUser.state=txtState.getText();
				newUser.religion=txtReligion.getText();
				if(rdbtnDoctor.isSelected())
					newUser.doctor=1;
				else 
					newUser.doctor=0;
				//boolean f=false;
				
				/*try {
					f=newUser.l2Tableinsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error 3!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error 4!!!\t");
				*/
				/*
					TODO add upload picture to the server location
				*/
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignL3Panel window = new SignL3Panel(newUser);
							window.frmSignUp.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				frmSignUp2.dispose();
			
			}
		});
		btnNext.setBounds(541, 394, 89, 23);
		frmSignUp2.getContentPane().add(btnNext);
		
		
		
		
	}
}

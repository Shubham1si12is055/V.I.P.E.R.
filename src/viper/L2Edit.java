package viper;

import java.awt.EventQueue;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class L2Edit {

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
	private JTextField txtResume;
	private JButton btnUpload;
	private JButton btnNext;
	private JRadioButton rdbtnResume;

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
	public L2Edit(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp2 = new JFrame();
		frmSignUp2.setTitle("L2 EDIT");
		frmSignUp2.setResizable(false);
		frmSignUp2.setBounds(100, 100, 644, 455);
		frmSignUp2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		txtPhone.setText(Long.toString(newUser.phone));
		txtPhone.setBounds(163, 57, 383, 20);
		frmSignUp2.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(163, 104, 383, 20);
		frmSignUp2.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		txtAddress.setText(newUser.address);
		
		lblCity = new JLabel("CITY");
		lblCity.setBounds(63, 157, 46, 14);
		frmSignUp2.getContentPane().add(lblCity);
		
		txtCity = new JTextField();
		txtCity.setBounds(163, 154, 173, 20);
		frmSignUp2.getContentPane().add(txtCity);
		txtCity.setColumns(10);
		txtCity.setText(newUser.city);
		
		lblState = new JLabel("STATE");
		lblState.setBounds(63, 207, 46, 14);
		frmSignUp2.getContentPane().add(lblState);
		
		
		txtState = new JTextField();
		txtState.setBounds(163, 204, 383, 20);
		frmSignUp2.getContentPane().add(txtState);
		txtState.setColumns(10);
		txtState.setText(newUser.state);
		
		lblPin = new JLabel("PIN");
		lblPin.setBounds(368, 160, 46, 14);
		frmSignUp2.getContentPane().add(lblPin);
		
		txtPin = new JTextField();
		txtPin.setText(Integer.toString(newUser.pin));
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
		txtReligion.setText(newUser.religion);
		
		txtResume = new JTextField();
		txtResume.setBounds(163, 304, 251, 20);
		frmSignUp2.getContentPane().add(txtResume);
		txtResume.setColumns(10);
		txtResume.setText(newUser.resLoc);
		
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
				}
			}
		});
		btnUpload.setBounds(457, 303, 89, 23);
		frmSignUp2.getContentPane().add(btnUpload);
		
		JCheckBox chckbxDoctor = new JCheckBox("DOCTOR");
		chckbxDoctor.setBounds(457, 251, 127, 24);
		frmSignUp2.getContentPane().add(chckbxDoctor);
		
		btnNext = new JButton("NEXT->");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnResume.isSelected())
					CurrentProfile.changeResume=true;
				else
					CurrentProfile.changeResume=false;
				
				newUser.phone=Long.parseLong(txtPhone.getText());
				newUser.address=txtAddress.getText();
				newUser.city=txtCity.getText();
				newUser.pin=Integer.parseInt(txtPin.getText());
				newUser.state=txtState.getText();
				newUser.religion=txtReligion.getText();
				if(chckbxDoctor.isSelected())
				newUser.doctor=1;
				else 
					newUser.doctor=0;
				boolean f=false;
				
				try {
					f=newUser.l2TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error 3!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error 4!!!\t");
				
				/*
					TODO add upload picture to the server location
				*/
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							L3Edit window = new L3Edit(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				frmSignUp2.dispose();
			
			}
		});
		btnNext.setBounds(457, 372, 89, 23);
		frmSignUp2.getContentPane().add(btnNext);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				newUser.phone=Long.parseLong(txtPhone.getText());
				newUser.address=txtAddress.getText();
				newUser.city=txtCity.getText();
				newUser.pin=Integer.parseInt(txtPin.getText());
				newUser.state=txtState.getText();
				newUser.religion=txtReligion.getText();
				if(chckbxDoctor.isSelected())
				newUser.doctor=1;
				else 
					newUser.doctor=0;
				boolean f=false;
				
				if(rdbtnResume.isSelected())
					CurrentProfile.changeResume=true;
				else
					CurrentProfile.changeResume=false;
				
				try {
					f=newUser.l2TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error 3!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error 4!!!\t");
				
				/*
					TODO add upload picture to the server location
				*/
			
				
				frmSignUp2.dispose();
			
				
			}
		});
		btnOk.setBounds(243, 368, 93, 30);
		frmSignUp2.getContentPane().add(btnOk);
		
		rdbtnResume = new JRadioButton("RESUME");
		rdbtnResume.setBounds(63, 305, 82, 18);
		frmSignUp2.getContentPane().add(rdbtnResume);
		
		
	}
}

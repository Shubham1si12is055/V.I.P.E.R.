package viper;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class SignM3Panel {

	JFrame frmSignUp;
	private JTextField txtMed;
	private JTextField txtDoc;
	private JTextField txtHospital;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignM3Panel window = new SignM3Panel();
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
	 */
	public SignM3Panel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("SIGN UP (6/6)");
		frmSignUp.setResizable(false);
		frmSignUp.setBounds(100, 100, 557, 355);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(390, 28, 55, 16);
		lblUid.setText(Integer.toString(newUser.uid));
		frmSignUp.getContentPane().add(lblUid);
		
		txtMed = new JTextField();
		txtMed.setText("n/a");
		txtMed.setBounds(198, 84, 200, 24);
		frmSignUp.getContentPane().add(txtMed);
		txtMed.setColumns(10);
		
		JRadioButton rdbtnMedicalHistory = new JRadioButton("MEDICAL HISTORY");
		rdbtnMedicalHistory.setBounds(31, 87, 155, 18);
		frmSignUp.getContentPane().add(rdbtnMedicalHistory);
		
		JRadioButton rdbtnDoctorReport = new JRadioButton("DOCTOR REPORT");
		rdbtnDoctorReport.setBounds(31, 155, 153, 18);
		frmSignUp.getContentPane().add(rdbtnDoctorReport);
		
		JRadioButton rdbtnHospitalName = new JRadioButton("HOSPITAL NAME");
		rdbtnHospitalName.setBounds(31, 224, 155, 18);
		frmSignUp.getContentPane().add(rdbtnHospitalName);
		
		txtDoc = new JTextField();
		txtDoc.setText("n/a");
		txtDoc.setColumns(10);
		txtDoc.setBounds(196, 152, 200, 24);
		frmSignUp.getContentPane().add(txtDoc);
		
		txtHospital = new JTextField();
		txtHospital.setText("n/a");
		txtHospital.setColumns(10);
		txtHospital.setBounds(198, 221, 200, 24);
		frmSignUp.getContentPane().add(txtHospital);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "txt","docx","pdf");
				filechooser.setFileFilter(filter);
				//ThumbNailView thumbsView = new ThumbNailView();
				//filechooser.setAccessory(new ImagePreview(filechooser));
				int returnVal = filechooser.showDialog(null,"select an file");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = filechooser.getSelectedFile();
					txtMed.setText(file.getPath());
					newUser.medHistoryLoc=file.getPath();
					//newUser.medHistoryName=file.getName();
				}
			}
		});
		btnUpload.setBounds(424, 83, 98, 26);
		frmSignUp.getContentPane().add(btnUpload);
		
		JButton button = new JButton("UPLOAD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "txt","docx","pdf");
				filechooser.setFileFilter(filter);
				//ThumbNailView thumbsView = new ThumbNailView();
				//filechooser.setAccessory(new ImagePreview(filechooser));
				int returnVal = filechooser.showDialog(null,"select an file");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = filechooser.getSelectedFile();
					txtDoc.setText(file.getPath());
					newUser.docReportLoc=file.getPath();
					//newUser.docReportName=file.getName();
				}
				
			}
		});
		button.setBounds(424, 151, 98, 26);
		frmSignUp.getContentPane().add(button);
		
		JButton btnNext = new JButton("FINISH");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnHospitalName.isSelected())
					newUser.hospitalname=txtHospital.getText();
				if(!rdbtnMedicalHistory.isSelected()){
					newUser.medHistoryLoc="";
				}
				if(!rdbtnDoctorReport.isSelected()){
					newUser.docReportLoc="";
				}
				
				boolean f=false;
				
				try {
					f=newUser.l1Tableinsert();
					f=f&&newUser.m3Tableinsert();
					f=f&&newUser.l2Tableinsert();
					f=f&&newUser.l3Tableinsert();
					f=f&&newUser.m1Tableinsert();
					f=f&&newUser.m2Tableinsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "Error database server connection!!!\t");
				else
					JOptionPane.showMessageDialog(null, "data saved!!!\t");
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UserProfilePanel window = new UserProfilePanel(newUser);
							window.frame.setVisible(true);
							frmSignUp.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnNext.setBounds(322, 288, 200, 26);
		frmSignUp.getContentPane().add(btnNext);
		
	
	}
}

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

public class M3Edit{

	JFrame frame;
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
	public M3Edit(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 557, 355);
		frame.getContentPane().setLayout(null);
		frame.setTitle("M2 Edit");
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(393, 12, 55, 16);
		lblUid.setText(Integer.toString(newUser.uid));
		frame.getContentPane().add(lblUid);
		
		JCheckBox chckbxMedicalHistory = new JCheckBox("MEDICAL HISTORY");
		chckbxMedicalHistory.setBounds(42, 84, 148, 24);
		frame.getContentPane().add(chckbxMedicalHistory);
		
		JCheckBox chckbxDoctorReport = new JCheckBox("DOCTOR REPORT");
		chckbxDoctorReport.setBounds(42, 152, 148, 24);
		frame.getContentPane().add(chckbxDoctorReport);
		
		JCheckBox chckbxHospitalName = new JCheckBox("HOSPITAL NAME");
		chckbxHospitalName.setBounds(42, 221, 134, 24);
		frame.getContentPane().add(chckbxHospitalName);
		
		txtMed = new JTextField();
		txtMed.setText(newUser.medHistoryLoc);
		txtMed.setBounds(198, 84, 200, 24);
		frame.getContentPane().add(txtMed);
		txtMed.setColumns(10);
		
		txtDoc = new JTextField();
		txtDoc.setText(newUser.docReportLoc);
		txtDoc.setColumns(10);
		txtDoc.setBounds(196, 152, 200, 24);
		frame.getContentPane().add(txtDoc);
		
		txtHospital = new JTextField();
		txtHospital.setText(newUser.hospitalname);
		txtHospital.setColumns(10);
		txtHospital.setBounds(198, 221, 200, 24);
		frame.getContentPane().add(txtHospital);
		
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
				}
			}
		});
		btnUpload.setBounds(424, 83, 98, 26);
		frame.getContentPane().add(btnUpload);
		
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
				}
				
			}
		});
		button.setBounds(424, 151, 98, 26);
		frame.getContentPane().add(button);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxHospitalName.isSelected())
					newUser.hospitalname=txtHospital.getText();
				if(!chckbxMedicalHistory.isSelected()){
					newUser.medHistoryLoc="";
				}
				if(!chckbxDoctorReport.isSelected()){
					newUser.docReportLoc="";
				}
				
				boolean f=false;
				
				try {
					f=newUser.m3TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				
				
			}
		});
		btnOk.setBounds(245, 278, 93, 30);
		frame.getContentPane().add(btnOk);
	}
}

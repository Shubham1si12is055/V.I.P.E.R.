package viper;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class M2Edit {

	JFrame frame;
	private JTextField txtDoc;
	private JTextField txtInsurence;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignM2Panel window = new SignM2Panel();
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
	 * @param newUser 
	 */
	public M2Edit(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 474, 280);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("M2 Edit");
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(352, 11, 46, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frame.getContentPane().add(lblUid);
		
		txtDoc = new JTextField();
		txtDoc.setBounds(169, 61, 183, 20);
		txtDoc.setText(Integer.toString(newUser.doctor));
		frame.getContentPane().add(txtDoc);
		txtDoc.setColumns(10);
		
		txtInsurence = new JTextField();
		txtInsurence.setBounds(169, 116, 183, 20);
		frame.getContentPane().add(txtInsurence);
		txtInsurence.setColumns(10);
		txtInsurence.setText(newUser.insurenceLoc);
		
		
		
		JCheckBox chckbxInsurence = new JCheckBox("INSURENCE");
		chckbxInsurence.setBounds(41, 101, 200, 50);
		frame.getContentPane().add(chckbxInsurence);
		
		JCheckBox chckbxDoctorUid = new JCheckBox("DOCTOR UID");
		chckbxDoctorUid.setBounds(41, 46, 200, 50);
		frame.getContentPane().add(chckbxDoctorUid);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.setBounds(352, 115, 89, 23);
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
					txtInsurence.setText(file.getPath());
					newUser.insurenceLoc=file.getPath();
				}
			}
		});
		frame.getContentPane().add(btnUpload);
		
		JButton btnNext = new JButton("NEXT->");
		btnNext.setBounds(352, 175, 89, 23);
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(chckbxDoctorUid.isSelected()){
					newUser.doctorUid=Integer.parseInt(txtDoc.getText());
				}
				if(!chckbxInsurence.isSelected()){
					newUser.insurenceLoc="";
				}
				boolean f=false;
				
				try {
					f=newUser.m2TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M3Edit window = new M3Edit(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				frame.dispose();
				
				
				
			}
		});
		frame.getContentPane().add(btnNext);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				if(chckbxDoctorUid.isSelected()){
					newUser.doctorUid=Integer.parseInt(txtDoc.getText());
				}
				if(!chckbxInsurence.isSelected()){
					newUser.insurenceLoc="";
				}
				boolean f=false;
				
				try {
					f=newUser.m2TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				
				
				
				frame.dispose();
				
				
			}
		});
		btnOk.setBounds(184, 190, 93, 30);
		frame.getContentPane().add(btnOk);
	}
}

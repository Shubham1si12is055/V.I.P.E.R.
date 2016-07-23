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

import javax.swing.JRadioButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignM2Panel {

	JFrame frmSignUp;
	private JTextField txtKeen;
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
	public SignM2Panel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("SIGN UP (5/6)");
		frmSignUp.setResizable(false);
		frmSignUp.setBounds(100, 100, 474, 406);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(352, 11, 46, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frmSignUp.getContentPane().add(lblUid);
		
		txtKeen = new JTextField();
		txtKeen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		txtKeen.setText("0");
		txtKeen.setBounds(198, 91, 243, 20);
		frmSignUp.getContentPane().add(txtKeen);
		txtKeen.setColumns(10);
		
		txtDoc = new JTextField();
		txtDoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		txtDoc.setText("0");
		txtDoc.setBounds(161, 172, 280, 20);
		frmSignUp.getContentPane().add(txtDoc);
		txtDoc.setColumns(10);
		
		txtInsurence = new JTextField();
		txtInsurence.setBounds(161, 262, 191, 20);
		frmSignUp.getContentPane().add(txtInsurence);
		txtInsurence.setColumns(10);

		JRadioButton rdbtnNextToKeen = new JRadioButton("NEXT TO KEEN UID");
		rdbtnNextToKeen.setBounds(43, 92, 160, 18);
		frmSignUp.getContentPane().add(rdbtnNextToKeen);
		
		JRadioButton rdbtnDoctorUid = new JRadioButton("DOCTOR UID");
		rdbtnDoctorUid.setBounds(41, 173, 119, 18);
		frmSignUp.getContentPane().add(rdbtnDoctorUid);
		
		JRadioButton rdbtnInsurence = new JRadioButton("INSURENCE");
		rdbtnInsurence.setBounds(43, 263, 119, 18);
		frmSignUp.getContentPane().add(rdbtnInsurence);
		
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
					txtInsurence.setText(file.getPath());
					newUser.insurenceLoc=file.getPath();
					//newUser.insurenceName=file.getName();
				}
			}
		});
		btnUpload.setBounds(352, 261, 89, 23);
		frmSignUp.getContentPane().add(btnUpload);
		
		JButton btnNext = new JButton(">>");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(rdbtnNextToKeen.isSelected()){
					newUser.keen=Integer.parseInt(txtKeen.getText());
				}
				
				if(rdbtnDoctorUid.isSelected()){
					newUser.doctorUid=Integer.parseInt(txtDoc.getText());
				}
				if(!rdbtnInsurence.isSelected()){
					newUser.insurenceLoc="";
				}
			//	boolean f=false;
				/*
				try {
					f=newUser.m2Tableinsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				*/
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignM3Panel window = new SignM3Panel(newUser);
							window.frmSignUp.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				frmSignUp.dispose();
				
				
				
			}
		});
		btnNext.setBounds(371, 345, 89, 23);
		frmSignUp.getContentPane().add(btnNext);
		
	}
}

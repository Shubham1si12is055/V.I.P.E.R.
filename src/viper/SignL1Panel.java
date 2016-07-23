package viper;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignL1Panel {

	public JFrame frmSignUp;
	
	private JTextField txtName1;
	private JTextField txtDate;
	private JTextField txtFather;
	private JTextField txtEmail;
	private JTextField txtPhoto;
	public  File file;
	private JTextField txtName2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignL1Panel window = new SignL1Panel();
					window.frmSignUp.setVisible(true);
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
	/*public SignL1Panel() {
		initialize();
	}*/
	public SignL1Panel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp = new JFrame();
		frmSignUp.setResizable(false);
		//frmSignUp.setAlwaysOnTop(true);
		frmSignUp.setTitle("SIGN UP (1/6)");
		frmSignUp.setBounds(100, 100, 557, 483);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		frmSignUp.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 531, 432);
		frmSignUp.getContentPane().add(panel);
		panel.setLayout(null);
		
		//allfield[0] = new JTextField();
		//allfield[0].setBounds(165, 60, 246, 20);
		//panel.add(allfield[0]);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(33, 67, 46, 14);
		panel.add(lblName);
		
		JLabel lblDob = new JLabel("D.O.B");
		lblDob.setBounds(33, 121, 46, 14);
		panel.add(lblDob);
		
		JLabel lblFatherName = new JLabel("FATHER'S NAME");
		lblFatherName.setBounds(33, 167, 109, 14);
		panel.add(lblFatherName);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(33, 222, 46, 14);
		panel.add(lblEmail);
		
		JLabel lblSex = new JLabel("SEX");
		lblSex.setBounds(33, 271, 46, 14);
		panel.add(lblSex);
		
		JLabel lblPicture = new JLabel("PICTURE");
		lblPicture.setBounds(33, 332, 74, 14);
		panel.add(lblPicture);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(384, 22, 107, 16);
		lblUid.setText(Integer.toString(newUser.uid));
		panel.add(lblUid);
		
		txtName1 = new JTextField();
		txtName1.setBounds(158, 64, 163, 20);
		panel.add(txtName1);
		txtName1.setColumns(10);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(153, 267, 109, 23);
		panel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(356, 267, 109, 23);
		panel.add(rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		txtDate = new JTextField();
		txtDate.setText("DD-MM-YYYY");
		txtDate.setBounds(158, 118, 333, 20);
		panel.add(txtDate);
		txtDate.setColumns(10);
		
		txtFather = new JTextField();
		txtFather.setBounds(160, 164, 331, 20);
		panel.add(txtFather);
		txtFather.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(158, 216, 333, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnSelect = new JButton("SELECT IMAGE");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPhoto.setText("");
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Images", "jpg","JPG","GIF", "gif","JPEG","png","PNG");
				filechooser.setFileFilter(filter);
				//ThumbNailView thumbsView = new ThumbNailView();
				//filechooser.setAccessory(new ImagePreview(filechooser));
				int returnVal = filechooser.showDialog(null,"select an image");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					file = filechooser.getSelectedFile();
					txtPhoto.setText(file.getPath());
					newUser.picLoc=file.getPath();
				//	newUser.picName=file.getName();
					newUser.picLoc=newUser.picLoc.replace("\\", "\\\\");
				}
			}
		});
		btnSelect.setBounds(374, 326, 124, 26);
		panel.add(btnSelect);
		
		JButton btnNext = new JButton(">>");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//String name,father,dob,email,photo;
				
				boolean f=false;
				
				do{
					newUser.nameFirst=txtName1.getText();
					newUser.nameLast=txtName2.getText();
				
					newUser.father=txtFather.getText();
					newUser.dob=txtDate.getText();
					newUser.email=txtEmail.getText();
					newUser.picLoc=file.getPath();//.getAbsolutePath();
					
					if(rdbtnFemale.isSelected()){
						newUser.sex='f';
					}
					
					if(rdbtnMale.isSelected()){
						newUser.sex='m';
					}
					if(newUser.nameFirst.length()!=0&&newUser.father.length()!=0&&newUser.dob.length()!=0&&newUser.email.length()!=0&&newUser.picLoc.length()!=0&&newUser.sex!=0){
						f=true;
					}
					else{
						JOptionPane.showMessageDialog(null, "enter every details!!!");
					}
				}while(!f);
				/*try {
					f=newUser.l1Tableinsert();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				*/
		
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignL2Panel window = new SignL2Panel(newUser);
							window.frmSignUp2.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frmSignUp.dispose();
				
			}

			
		});
		btnNext.setBounds(433, 406, 98, 26);
		panel.add(btnNext);
		
		txtPhoto = new JTextField();
		txtPhoto.setBounds(158, 329, 204, 20);
		panel.add(txtPhoto);
		txtPhoto.setColumns(10);
		
		txtName2 = new JTextField();
		txtName2.setBounds(333, 64, 158, 20);
		panel.add(txtName2);
		txtName2.setColumns(10);
		
		
	//	panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, datePicker, lblName, lblDob, lblFatherName, lblEmail, lblSex, lblPicture, rdbtnMale, rdbtnFemale}));
		//allfield[0].setColumns(10);
		
	}
}

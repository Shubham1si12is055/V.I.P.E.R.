package viper;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignL3Panel {

	public JFrame frmSignUp;
	private JTextField txtDocument;
	private JTextField txtKey;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignL3Panel window = new SignL3Panel();
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
	public SignL3Panel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param newUser 
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("SIGN UP (3/6)");
		frmSignUp.setResizable(false);
		frmSignUp.setBounds(100, 100, 424, 272);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		frmSignUp.setVisible(true);
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(333, 21, 46, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frmSignUp.getContentPane().add(lblUid);
		
		JLabel lblDocument = new JLabel("DOCUMENT");
		lblDocument.setBounds(25, 76, 73, 14);
		frmSignUp.getContentPane().add(lblDocument);
		
		txtDocument = new JTextField();
		txtDocument.setBounds(108, 73, 189, 20);
		frmSignUp.getContentPane().add(txtDocument);
		txtDocument.setColumns(10);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "txt","docx","pdf");
				filechooser.setFileFilter(filter);
				//ThumbNailView thumbsView = new ThumbNailView();
				//filechooser.setAccessory(new ImagePreview(filechooser));
				int returnVal = filechooser.showDialog(null,"select an file");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = filechooser.getSelectedFile();
					txtDocument.setText(file.getPath());
					newUser.docLoc=file.getPath();
				//	newUser.docName=file.getName();
					newUser.docLoc=newUser.docLoc.replace("\\", "\\\\");
				}
			}
		});
		btnUpload.setBounds(307, 72, 89, 23);
		frmSignUp.getContentPane().add(btnUpload);
		
		JLabel lblKey = new JLabel("PIN");
		lblKey.setBounds(35, 146, 46, 14);
		frmSignUp.getContentPane().add(lblKey);
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		txtKey.setText("0");
		txtKey.setBounds(108, 143, 189, 20);
		frmSignUp.getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnNext = new JButton(">>");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUser.key=Integer.parseInt(txtKey.getText());
				boolean f=false;
				/*
				try {
					f=newUser.l3Tableinsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");*/
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignM1Panel window = new SignM1Panel(newUser);
							window.frmSignUp.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				frmSignUp.dispose();
			}
		});
		btnNext.setBounds(322, 215, 89, 23);
		frmSignUp.getContentPane().add(btnNext);
	}

}

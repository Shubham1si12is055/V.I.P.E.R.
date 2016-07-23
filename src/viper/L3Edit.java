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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class L3Edit {

	public JFrame frame;
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
	public L3Edit(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param newUser 
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setTitle("L3 Edit");
		frame.getContentPane().setLayout(null);
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(333, 21, 46, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frame.getContentPane().add(lblUid);
		
		txtDocument = new JTextField();
		txtDocument.setBounds(108, 73, 189, 20);
		frame.getContentPane().add(txtDocument);
		txtDocument.setColumns(10);
		txtDocument.setText(newUser.docLoc);
		JRadioButton rdbtnDocument = new JRadioButton("Document");
		rdbtnDocument.setBounds(21, 74, 119, 18);
		frame.getContentPane().add(rdbtnDocument);
		
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.setBounds(307, 72, 89, 23);
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
				}
			}
		});
		frame.getContentPane().add(btnUpload);
		
		JLabel lblKey = new JLabel("PIN");
		lblKey.setBounds(36, 146, 46, 14);
		frame.getContentPane().add(lblKey);
		
		txtKey = new JTextField();
		txtKey.setBounds(108, 143, 189, 20);
		txtKey.setText(""+newUser.key);
		frame.getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnNext = new JButton("NEXT->");
		btnNext.setBounds(307, 206, 89, 23);
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUser.key=Integer.parseInt(txtKey.getText());
				boolean f=false;
				if(rdbtnDocument.isSelected())
					CurrentProfile.changeDocument=true;
				else
					CurrentProfile.changeDocument=true;
				try {
					f=newUser.l3TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M1Edit window = new M1Edit(newUser);
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
				newUser.key=Integer.parseInt(txtKey.getText());
				boolean f=false;
				if(rdbtnDocument.isSelected())
					CurrentProfile.changeDocument=true;
				else
					CurrentProfile.changeDocument=true;
					
				
				try {
					f=newUser.l3TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				
				
				
				frame.dispose();
			}
		});
		btnOk.setBounds(157, 217, 93, 30);
		frame.getContentPane().add(btnOk);
		
		
	}

}

package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KeenDetail {

	public JFrame frmKeenDetails;
	private JTextField txtUid;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeenDetail window = new KeenDetail(null);
					window.frmKeenDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public KeenDetail(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newuser) {
		frmKeenDetails = new JFrame();
		frmKeenDetails.setTitle("DETAILS");
		frmKeenDetails.setBounds(100, 100, 450, 300);
		frmKeenDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKeenDetails.getContentPane().setLayout(null);
		
		JLabel lblUid = new JLabel("UID: ");
		lblUid.setBounds(52, 33, 56, 18);
		frmKeenDetails.getContentPane().add(lblUid);
		
		txtUid = new JTextField();
		txtUid.setText(""+newuser.uid);
		txtUid.setBounds(140, 29, 241, 25);
		frmKeenDetails.getContentPane().add(txtUid);
		txtUid.setColumns(10);
		
		JLabel lblName = new JLabel("NAME: ");
		lblName.setBounds(52, 82, 56, 18);
		frmKeenDetails.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setText(newuser.nameFirst+" "+newuser.nameLast);
		txtName.setBounds(140, 75, 241, 25);
		frmKeenDetails.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblContact = new JLabel("CONTACT:");
		lblContact.setBounds(52, 128, 71, 18);
		frmKeenDetails.getContentPane().add(lblContact);
		
		txtPhone = new JTextField();
		txtPhone.setText(""+newuser.phone);
		txtPhone.setBounds(140, 124, 241, 25);
		frmKeenDetails.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblEmail = new JLabel("EMAIL: ");
		lblEmail.setBounds(52, 181, 56, 18);
		frmKeenDetails.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setText(newuser.email);
		txtEmail.setBounds(140, 177, 241, 25);
		frmKeenDetails.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmKeenDetails.dispose();
			}
		});
		btnClose.setBounds(183, 227, 93, 30);
		frmKeenDetails.getContentPane().add(btnClose);
	}

}

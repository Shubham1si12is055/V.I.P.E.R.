package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SignM1Panel {

	JFrame frmSignUp;
	private JTextField txtBlood;
	private JTextField txtAllergies1;
	private JTextField txtAllergies2;
	private JTextField txtAllergies3;

	/**
	 * Launch the application.
	 * @param newUser 
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignM1Panel window = new SignM1Panel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*?
	/**
	 * Create the application.
	 */
	public SignM1Panel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("SIGN UP (4/6)");
		frmSignUp.setBounds(100, 100, 450, 300);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JLabel lblBloodGroup = new JLabel("BLOOD GROUP");
		lblBloodGroup.setBounds(37, 71, 116, 14);
		frmSignUp.getContentPane().add(lblBloodGroup);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(332, 11, 46, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frmSignUp.getContentPane().add(lblUid);
		
		txtBlood = new JTextField();
		txtBlood.setText("n/a");
		txtBlood.setBounds(177, 68, 166, 20);
		frmSignUp.getContentPane().add(txtBlood);
		txtBlood.setColumns(10);
		
		JLabel lblAllergies = new JLabel("ALLERGIES");
		lblAllergies.setBounds(37, 124, 75, 14);
		frmSignUp.getContentPane().add(lblAllergies);
		
		txtAllergies1 = new JTextField();
		txtAllergies1.setText("n/a");
		txtAllergies1.setBounds(177, 121, 166, 20);
		frmSignUp.getContentPane().add(txtAllergies1);
		txtAllergies1.setColumns(10);
		
		txtAllergies2 = new JTextField();
		txtAllergies2.setText("n/a");
		txtAllergies2.setBounds(177, 153, 166, 20);
		frmSignUp.getContentPane().add(txtAllergies2);
		txtAllergies2.setColumns(10);
		
		txtAllergies3 = new JTextField();
		txtAllergies3.setText("n/a");
		txtAllergies3.setBounds(177, 184, 166, 20);
		frmSignUp.getContentPane().add(txtAllergies3);
		txtAllergies3.setColumns(10);
		
		JButton btnNext = new JButton(">>");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUser.blood=txtBlood.getText();
				newUser.allergies1=txtAllergies1.getText();
				newUser.allergies2=txtAllergies2.getText();
				newUser.allergies3=txtAllergies3.getText();
				
				boolean f=false;
				
				/*try {
					f=newUser.m1Tableinsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");*/
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignM2Panel window = new SignM2Panel(newUser);
							window.frmSignUp.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frmSignUp.dispose();
				
			}
		});
		btnNext.setBounds(332, 227, 89, 23);
		frmSignUp.getContentPane().add(btnNext);
	}

}

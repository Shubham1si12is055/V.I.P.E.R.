package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class M1Panel {

	public JFrame frame;
	private JTextField txtblood;
	private JTextField txtA1;
	private JTextField txtA2;
	private JTextField txtA3;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M1Panel window = new M1Panel(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public M1Panel(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newuser) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setTitle("M1");
		newuser.fetchM1Details();
		
		JLabel lblBloodgroup = new JLabel("BloodGroup: ");
		lblBloodgroup.setBounds(48, 60, 92, 14);
		frame.getContentPane().add(lblBloodgroup);
		
		txtblood = new JTextField();
		txtblood.setBounds(175, 54, 122, 25);
		frame.getContentPane().add(txtblood);
		txtblood.setText(newuser.blood);
		txtblood.setColumns(10);
		
		JLabel lblAllergies = new JLabel("allergies");
		lblAllergies.setBounds(48, 104, 81, 18);
		frame.getContentPane().add(lblAllergies);
		
		txtA1 = new JTextField();
		txtA1.setBounds(175, 100, 161, 25);
		frame.getContentPane().add(txtA1);
		txtA1.setText(newuser.allergies1);
		txtA1.setColumns(10);
		
		txtA2 = new JTextField();
		txtA2.setColumns(10);
		txtA2.setText(newuser.allergies2);
		txtA2.setBounds(175, 147, 161, 25);
		frame.getContentPane().add(txtA2);
		
		txtA3 = new JTextField();
		txtA3.setColumns(10);
		txtA3.setText(newuser.allergies3);
		txtA3.setBounds(175, 201, 161, 25);
		frame.getContentPane().add(txtA3);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnOk.setBounds(349, 238, 93, 30);
		frame.getContentPane().add(btnOk);
	}
}

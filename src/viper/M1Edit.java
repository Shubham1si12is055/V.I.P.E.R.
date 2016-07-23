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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class M1Edit {

	JFrame frame;
	private JTextField txtBlood;
	private JTextField txtAllergies1;
	private JTextField txtAllergies2;
	private JTextField txtAllergies3;
	private JButton btnOk;

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
	public M1Edit(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("M1 Edit");
		
		JLabel lblBloodGroup = new JLabel("BLOOD GROUP");
		lblBloodGroup.setBounds(37, 71, 93, 14);
		frame.getContentPane().add(lblBloodGroup);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setBounds(332, 11, 46, 14);
		lblUid.setText(Integer.toString(newUser.uid));
		frame.getContentPane().add(lblUid);
		
		txtBlood = new JTextField();
		txtBlood.setBounds(177, 68, 166, 20);
		frame.getContentPane().add(txtBlood);
		txtBlood.setColumns(10);
		txtBlood.setText(newUser.blood);
		
		JLabel lblAllergies = new JLabel("ALLERGIES");
		lblAllergies.setBounds(37, 124, 75, 14);
		frame.getContentPane().add(lblAllergies);
		
		txtAllergies1 = new JTextField();
		txtAllergies1.setBounds(177, 121, 166, 20);
		frame.getContentPane().add(txtAllergies1);
		txtAllergies1.setColumns(10);
		txtAllergies1.setText(newUser.allergies1);
		
		txtAllergies2 = new JTextField();
		txtAllergies2.setBounds(177, 153, 166, 20);
		frame.getContentPane().add(txtAllergies2);
		txtAllergies2.setColumns(10);

		txtAllergies2.setText(newUser.allergies2);
		
		txtAllergies3 = new JTextField();
		txtAllergies3.setBounds(177, 184, 166, 20);
		frame.getContentPane().add(txtAllergies3);
		txtAllergies3.setColumns(10);
		txtAllergies3.setText(newUser.allergies3);
		
		JButton btnNext = new JButton("NEXT->");
		btnNext.setBounds(332, 227, 89, 23);
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUser.blood=txtBlood.getText();
				newUser.allergies1=txtAllergies1.getText();
				newUser.allergies2=txtAllergies2.getText();
				newUser.allergies3=txtAllergies3.getText();
				
				boolean f=false;
				
				try {
					f=newUser.m1TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M2Edit window = new M2Edit(newUser);
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
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newUser.blood=txtBlood.getText();
				newUser.allergies1=txtAllergies1.getText();
				newUser.allergies2=txtAllergies2.getText();
				newUser.allergies3=txtAllergies3.getText();
				
				boolean f=false;
				
				try {
					f=newUser.m1TableUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "database connection error !!!\t"+e1);
				}
				if(!f)
					JOptionPane.showMessageDialog(null, "database connection error !!!\t");
				
				frame.dispose();
			}
		});
		btnOk.setBounds(159, 238, 93, 30);
		frame.getContentPane().add(btnOk);
	}

}

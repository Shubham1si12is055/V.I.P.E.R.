package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPanel {

	JFrame frmEdit;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPanel window = new EditPanel();
					window.frmEdit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public EditPanel(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser){
		frmEdit = new JFrame();
		frmEdit.setTitle("Edit");
		frmEdit.setBounds(100, 100, 450, 300);
		frmEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEdit.getContentPane().setLayout(null);
		
		CurrentProfile.fetchSecurely(newUser);
		
		
		JLabel lblGeneralLevels = new JLabel("General levels:");
		lblGeneralLevels.setBounds(16, 27, 97, 18);
		frmEdit.getContentPane().add(lblGeneralLevels);
		
		JButton btnL = new JButton("L1");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							L1Edit window = new L1Edit(newUser);
							window.frmSignUp.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnL.setBounds(40, 57, 93, 30);
		frmEdit.getContentPane().add(btnL);
		
		JButton btnL_1 = new JButton("L2");
		btnL_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							L2Edit window = new L2Edit(newUser);
							window.frmSignUp2.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnL_1.setBounds(179, 57, 93, 30);
		frmEdit.getContentPane().add(btnL_1);
		
		JButton btnL_2 = new JButton("L3");
		btnL_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							L3Edit window = new L3Edit(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnL_2.setBounds(318, 57, 93, 30);
		frmEdit.getContentPane().add(btnL_2);
		
		JLabel lblMedicalLevels = new JLabel("Medical levels:");
		lblMedicalLevels.setBounds(16, 99, 97, 18);
		frmEdit.getContentPane().add(lblMedicalLevels);
		
		JButton btnM = new JButton("M1");
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			}
		});
		btnM.setBounds(40, 145, 93, 30);
		frmEdit.getContentPane().add(btnM);
		
		JButton btnM_1 = new JButton("M2");
		btnM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			}
		});
		btnM_1.setBounds(179, 145, 93, 30);
		frmEdit.getContentPane().add(btnM_1);
		
		JButton btnM_2 = new JButton("M3");
		btnM_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			}
		});
		btnM_2.setBounds(318, 145, 93, 30);
		frmEdit.getContentPane().add(btnM_2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CurrentProfile.fetchAll(newUser);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UserProfilePanel window = new UserProfilePanel(newUser);
							window.frame.setVisible(true);
							frmEdit.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				//frmEdit.dispose();
			}
		});
		btnOk.setBounds(179, 226, 93, 30);
		frmEdit.getContentPane().add(btnOk);
	}
}

package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SureSignIn {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SureSignIn window = new SureSignIn();
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
	 */
	public SureSignIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("ARE YOU SURE??");
		
		JLabel Sure = new JLabel("YOU CAN NOT GO BACK OR LEAVE SIGN IN PROCESS IN BETWEEN.");
		Sure.setBounds(24, 30, 438, 108);
		frame.getContentPane().add(Sure);
		
		JLabel label = new JLabel("");
		label.setBounds(195, 122, 55, 16);
		frame.getContentPane().add(label);
		
		JLabel lblProceed = new JLabel("PROCEED???");
		lblProceed.setBounds(173, 88, 85, 50);
		frame.getContentPane().add(lblProceed);
		
		JButton btnNo = new JButton("NO");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnNo.setBounds(24, 180, 98, 26);
		frame.getContentPane().add(btnNo);
		
		JButton btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							SignPassword  window = new SignPassword ();
							window.frmSignUpPass.setVisible(true);
							frame.dispose();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnYes.setBounds(302, 180, 98, 26);
		frame.getContentPane().add(btnYes);
	}
}

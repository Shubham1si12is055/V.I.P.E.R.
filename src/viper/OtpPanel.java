package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OtpPanel {

	JFrame frmOtp;
	private JTextField txtOtp;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtpPanel window = new OtpPanel();
					window.frmOtp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public OtpPanel(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmOtp = new JFrame();
		frmOtp.setResizable(false);
		frmOtp.setTitle("OTP");
		frmOtp.setBounds(100, 100, 407, 154);
		frmOtp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOtp.getContentPane().setLayout(null);
		
		JLabel lblOtp = new JLabel("OTP:");
		lblOtp.setBounds(44, 37, 56, 18);
		frmOtp.getContentPane().add(lblOtp);
		
		txtOtp = new JTextField();
		txtOtp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		txtOtp.setBounds(99, 33, 282, 25);
		frmOtp.getContentPane().add(txtOtp);
		txtOtp.setColumns(10);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CurrentProfile.otpCheck(Integer.parseInt(txtOtp.getText()),newUser.uid)){
					newUser.otpVerify=true;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								M3Panel window = new M3Panel(newUser);
								frmOtp.dispose();
								window.frmMDetails.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				else{
					JOptionPane.showMessageDialog(null, "Incorrect OTP");
				}
			}
		});
		button.setBounds(288, 74, 93, 30);
		frmOtp.getContentPane().add(button);
	}

}

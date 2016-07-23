package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IPAdress {

	JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IPAdress window = new IPAdress();
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
	public IPAdress() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 182);
		frame.setTitle("ServerIP");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText(CurrentProfile.server);
		textField.setBounds(98, 62, 290, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblServerip = new JLabel("serverIP:");
		lblServerip.setBounds(22, 65, 66, 14);
		frame.getContentPane().add(lblServerip);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CurrentProfile.server=textField.getText();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(167, 106, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

}

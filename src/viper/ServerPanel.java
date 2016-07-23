package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerPanel {

	public JFrame frmServer;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerPanel window = new ServerPanel();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ServerPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 450, 160);
		frmServer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JButton btnServerStart = new JButton("Server_Start");
		btnServerStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//FileServer.flag=true;
				btnServerStart.setText("Server_Started");
				FileServer.startServer();
			}
		});
		btnServerStart.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		btnServerStart.setBounds(31, 19, 391, 73);
		frmServer.getContentPane().add(btnServerStart);
		
		JButton button = new JButton("<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							login window = new login();
							frmServer.dispose();
							window.frmViper.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button.setBounds(214, 86, 44, 30);
		frmServer.getContentPane().add(button);
	}
}

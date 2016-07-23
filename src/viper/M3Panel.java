package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class M3Panel {

	public JFrame frmMDetails;
	private JTextField textField;
	private JLabel lblMedicalHistory;
	private JLabel lblDoctorReport;
	private JButton btnOk;
	private JButton btnDownload;
	private JButton btnDownload_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M3Panel window = new M3Panel();
					window.frmMDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public M3Panel(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmMDetails = new JFrame();
		frmMDetails.setTitle("M3");
		frmMDetails.setBounds(100, 100, 450, 316);
		frmMDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMDetails.getContentPane().setLayout(null);
		
		newUser.fetchM3Details();
		JLabel lblHospitalName = new JLabel("Hospital Name :");
		lblHospitalName.setBounds(42, 35, 118, 23);
		frmMDetails.getContentPane().add(lblHospitalName);
		
		textField = new JTextField();
		textField.setText(newUser.hospitalname);
		textField.setBounds(152, 33, 213, 25);
		frmMDetails.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblMedicalHistory = new JLabel("Medical History:");
		lblMedicalHistory.setBounds(42, 104, 105, 18);
		frmMDetails.getContentPane().add(lblMedicalHistory);
		
		lblDoctorReport = new JLabel("Doctor Report:");
		lblDoctorReport.setBounds(42, 167, 105, 18);
		frmMDetails.getContentPane().add(lblDoctorReport);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMDetails.dispose();
			}
		});
		btnOk.setBounds(177, 242, 93, 30);
		frmMDetails.getContentPane().add(btnOk);
		
		btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!CurrentProfile.checkFileExist("medHistoryLoc", "mlevel3", newUser.uid)){
					JOptionPane.showMessageDialog(null, "no file found!!!");return;
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PinPanel window = new PinPanel("B",newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnDownload.setBounds(152, 98, 213, 30);
		frmMDetails.getContentPane().add(btnDownload);
		
		btnDownload_1 = new JButton("Download");
		btnDownload_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!CurrentProfile.checkFileExist("docReportLoc", "mlevel3", newUser.uid)){
					JOptionPane.showMessageDialog(null, "no file found!!!");return;
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PinPanel window = new PinPanel("C",newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnDownload_1.setBounds(152, 161, 213, 30);
		frmMDetails.getContentPane().add(btnDownload_1);
	}

}

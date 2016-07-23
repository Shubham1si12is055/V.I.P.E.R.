package viper;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class M2Pane {

	public JFrame frmM;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M2Pane window = new M2Pane(null);
					window.frmM.setVisible(true);
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
	public M2Pane(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frmM = new JFrame();
		frmM.setTitle("M2");
		frmM.setBounds(100, 100, 450, 300);
		frmM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmM.getContentPane().setLayout(null);
		
		JLabel lblKeen = new JLabel("KEEN:");
		lblKeen.setBounds(37, 36, 46, 14);
		frmM.getContentPane().add(lblKeen);
		int keen[]=CurrentProfile.keenList(newUser.uid);
		String list[]=new String[20];
		int i=0;
		for(int keenId:keen){
			if(keenId==0)
				break;
			list[i]=keenId+": "+CurrentProfile.fetchUsername(keenId);
			i++;
		}
		JComboBox comboBox = new JComboBox(list);
		comboBox.setBounds(103, 23, 200, 41);
		frmM.getContentPane().add(comboBox);
		
		JButton btnDetail = new JButton("DETAIL");
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String txt=(String) comboBox.getSelectedItem();
				int id=txt.indexOf(":");
				txt=txt.substring(0,id);
				System.out.print(txt);
				if(id>0){
					id=Integer.parseInt(txt);
				}
				else{
					JOptionPane.showMessageDialog(null, "Error");
					return;
				}
				CurrentProfile other=new CurrentProfile();
							
				other.username=CurrentProfile.fetchUsername(id);
				//CurrentProfile.fetchAll(otherPofile);
				CurrentProfile.fetchAll(other);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							KeenDetail window = new KeenDetail(other);
							window.frmKeenDetails.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnDetail.setBounds(349, 28, 93, 30);
		frmM.getContentPane().add(btnDetail);
		
		JLabel lblDoctorId = new JLabel("DOCTOR ID:");
		lblDoctorId.setBounds(37, 121, 85, 18);
		frmM.getContentPane().add(lblDoctorId);
		
		newUser.fetchM2Detail();
		textField = new JTextField();
		textField.setText(""+newUser.doctorUid);
		textField.setBounds(122, 117, 181, 25);
		frmM.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInsurence = new JLabel("INSURENCE: ");
		lblInsurence.setBounds(37, 191, 85, 18);
		frmM.getContentPane().add(lblInsurence);
		
		JButton btnInsurenceDownload = new JButton("DOWNLOAD");
		btnInsurenceDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!CurrentProfile.checkFileExist("mlevel2", "insurenceLoc", newUser.uid)){
					JOptionPane.showMessageDialog(null, "no file found!!!");return;
				}
				
				
				String path=FileClient.getPathViper("A")+newUser.uid+".txt";
				FileClient.connectToServer("A",""+newUser.uid+".txt");
				JOptionPane.showMessageDialog(null, "Content fetched!!!");
				
				/*TODO
				 * EventQueue.invokeLater(new Runnable() {
					
					  public void run() {
						try {
							PinPanel window = new PinPanel("A",newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
				if (Desktop.isDesktopSupported()) {
		            try {
		                File myFile = new File( path);
		                Desktop.getDesktop().open(myFile);
		            } catch (IOException ex) {
		                // no application registered for PDFs
		            	ex.printStackTrace();
		            	JOptionPane.showMessageDialog(null, "error!! "+ex);
		            }
		        }
			}
		});
		btnInsurenceDownload.setBounds(122, 185, 181, 30);
		frmM.getContentPane().add(btnInsurenceDownload);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmM.dispose();
			}
		});
		btnOk.setBounds(349, 238, 93, 30);
		frmM.getContentPane().add(btnOk);
	}
}

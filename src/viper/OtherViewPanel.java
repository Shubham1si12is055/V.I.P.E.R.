package viper;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OtherViewPanel {

	public JFrame frame;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtReligion;
	private JTextField txtNameFirst;
	private JTextField txtFather;
	private JTextField txtNameLast;
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtherViewPanel window = new OtherViewPanel(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param otherPofile 
	 */
	public OtherViewPanel(CurrentProfile otherPofile,CurrentProfile newuser) {
		initialize(otherPofile,newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile otherPofile,CurrentProfile newuser) {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 652, 393);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(otherPofile.nameFirst+" "+otherPofile.nameLast);
		
		JLabel label = new JLabel("CITY");
		label.setBounds(123, 166, 46, 14);
		frame.getContentPane().add(label);
		
		txtCity = new JTextField();
		txtCity.setText(otherPofile.city);
		txtCity.setColumns(10);
		txtCity.setBounds(223, 160, 173, 23);
		frame.getContentPane().add(txtCity);
		
		JLabel label_1 = new JLabel("STATE");
		label_1.setBounds(406, 166, 46, 14);
		frame.getContentPane().add(label_1);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setText(otherPofile.state);
		txtState.setBounds(453, 160, 151, 23);
		frame.getContentPane().add(txtState);
		
		JLabel label_2 = new JLabel("RELIGION");
		label_2.setBounds(123, 229, 70, 14);
		frame.getContentPane().add(label_2);
		
		txtReligion = new JTextField();
		txtReligion.setColumns(10);
		txtReligion.setText(otherPofile.religion);
		txtReligion.setBounds(223, 224, 173, 22);
		frame.getContentPane().add(txtReligion);
		
		JCheckBox checkBox = new JCheckBox("DOCTOR");
		checkBox.setBounds(477, 224, 127, 24);
		if(otherPofile.doctor==1)
			checkBox.setSelected(true);
		frame.getContentPane().add(checkBox);
		
		JLabel label_3 = new JLabel("NAME");
		label_3.setBounds(123, 32, 90, 16);
		frame.getContentPane().add(label_3);
		
		txtNameFirst = new JTextField();
		txtNameFirst.setColumns(10);
		txtNameFirst.setText(otherPofile.nameFirst);
		txtNameFirst.setBounds(219, 28, 177, 24);
		frame.getContentPane().add(txtNameFirst);
		
		txtFather = new JTextField();
		txtFather.setColumns(10);
		txtFather.setText(otherPofile.father);
		txtFather.setBounds(243, 94, 359, 24);
		frame.getContentPane().add(txtFather);
		
		JLabel label_4 = new JLabel("FATHER'S NAME");
		label_4.setBounds(123, 100, 108, 16);
		frame.getContentPane().add(label_4);
		
		JButton btnL = new JButton("L2");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(newuser.uid==9999){
					JOptionPane.showMessageDialog(null, "Please login first!!");
					return;
				}
				if(newuser.doctor==0){
					JOptionPane.showMessageDialog(null, "Only for authorized doctor only!!");
					return;
				}
				if(!otherPofile.otpVerify){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AllPass window = new AllPass(otherPofile,newuser,1);
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});					
				}
				else{
					
				}
				
			}
		});
		btnL.setBounds(10, 291, 118, 37);
		frame.getContentPane().add(btnL);
		
		JButton btnM = new JButton("M1");
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M1Panel window = new M1Panel(otherPofile);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnM.setBounds(162, 291, 125, 37);
		frame.getContentPane().add(btnM);
		
		JButton btnM_1 = new JButton("M2");
		btnM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(newuser.uid==9999){
					JOptionPane.showMessageDialog(null, "Please login first!!");
					return;
				}
				if(newuser.doctor==0){
					JOptionPane.showMessageDialog(null, "Only for authorized doctor only!!");
					return;
				}
				if(!otherPofile.otpVerify){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AllPass window = new AllPass(otherPofile,newuser,2);
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});					
				}
				else{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								KeenDetail window = new KeenDetail(otherPofile);
								window.frmKeenDetails.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		btnM_1.setBounds(299, 291, 118, 37);
		frame.getContentPane().add(btnM_1);
		
		JButton btnM_2 = new JButton("M3");
		btnM_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(newuser.doctor==0){
					JOptionPane.showMessageDialog(null, "Only for authorized doctor only!!");
					return;
				}
				if(!otherPofile.otpVerify){
					ScheduledThreadPoolExecutor exec =new ScheduledThreadPoolExecutor(1);
					exec.schedule(new Runnable() {
						
						@Override
						public void run() {
							
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										OtpPanel window = new OtpPanel(otherPofile);
										window.frmOtp.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
												
						}
					}, 30, TimeUnit.SECONDS);
				}
				else{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								M3Panel window = new M3Panel(otherPofile);
								window.frmMDetails.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}				
			}
		});
		btnM_2.setBounds(429, 291, 127, 37);
		frame.getContentPane().add(btnM_2);
		

		BufferedImage img=null;
		try{
			img=ImageIO.read(new File(otherPofile.picLoc));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Image dimg= img.getScaledInstance(103, 115, Image.SCALE_SMOOTH);
		ImageIcon imageIcon=new ImageIcon(dimg);
		
		JLabel lblProfilePic = new JLabel(imageIcon);
		lblProfilePic.setBounds(10, 32, 103, 115);
		frame.getContentPane().add(lblProfilePic);
		
		JLabel lblUid = new JLabel("uid:");
		lblUid.setBounds(10, 160, 25, 14);
		frame.getContentPane().add(lblUid);
		
		JLabel lblUid_1 = new JLabel("uid");
		lblUid_1.setText(Integer.toString(otherPofile.uid));
		lblUid_1.setBounds(39, 160, 60, 14);
		frame.getContentPane().add(lblUid_1);
		
		txtNameLast = new JTextField();
		txtNameLast.setText(otherPofile.nameLast);
		txtNameLast.setBounds(418, 28, 186, 24);
		
		frame.getContentPane().add(txtNameLast);
		txtNameLast.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			}
		});
		btnOk.setBounds(551, 331, 93, 30);
		frame.getContentPane().add(btnOk);
	}

}

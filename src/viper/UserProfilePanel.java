package viper;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SpringLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JPanel;

public class UserProfilePanel {

	JFrame frame;
	private JTextField txtCity;
	private JTextField txtPin;
	private JTextField txtAddress;
	private JTextField txtState;
	private JTextField txtReligion;
	private JTextField txtName1;
	private JTextField txtdob;
	private JTextField txtFather;
	private JTextField txtEmail;
	private JTextField txtSex;
	private JTextField txtPhone;
	private JTextField txtName2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfilePanel window = new UserProfilePanel();
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
	 * @param newUser 
	 */
	public UserProfilePanel(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param newUser 
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle(newUser.nameFirst+" "+newUser.nameLast);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 939, 687);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(131, 44, 90, 16);
		
		frame.getContentPane().add(lblName);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(679, 11, 167, 26);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SearchPanel window = new SearchPanel(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnSearch);
		
		JLabel lblDob = new JLabel("D.O.B.");
		lblDob.setBounds(131, 86, 90, 16);
		frame.getContentPane().add(lblDob);
		
		JLabel lblFatherName = new JLabel("FATHER'S NAME");
		lblFatherName.setBounds(131, 135, 108, 16);
		frame.getContentPane().add(lblFatherName);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(131, 183, 78, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblSex = new JLabel("SEX");
		lblSex.setBounds(487, 183, 37, 16);
		frame.getContentPane().add(lblSex);
		
		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER");
		lblPhoneNumber.setBounds(131, 219, 108, 16);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(131, 267, 90, 16);
		frame.getContentPane().add(lblAddress);
		
		JLabel label = new JLabel("CITY");
		label.setBounds(131, 321, 78, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("PIN");
		label_1.setBounds(439, 314, 46, 16);
		frame.getContentPane().add(label_1);
		
		txtCity = new JTextField();
		txtCity.setBounds(227, 312, 194, 20);
		txtCity.setColumns(10);
		txtCity.setText(newUser.city);
		frame.getContentPane().add(txtCity);
		
		txtPin = new JTextField();
		txtPin.setBounds(487, 312, 169, 20);
		txtPin.setColumns(10);
		txtPin.setText(""+newUser.pin);
		frame.getContentPane().add(txtPin);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(227, 262, 429, 26);
		txtAddress.setColumns(10);
		txtAddress.setText(newUser.address);
		frame.getContentPane().add(txtAddress);
		
		JLabel label_2 = new JLabel("STATE");
		label_2.setBounds(131, 372, 78, 16);
		frame.getContentPane().add(label_2);
		
		txtState = new JTextField();
		txtState.setBounds(227, 362, 429, 26);
		txtState.setColumns(10);
		txtState.setText(newUser.state);
		frame.getContentPane().add(txtState);
		
		JLabel label_3 = new JLabel("RELIGION");
		label_3.setBounds(131, 417, 90, 16);
		frame.getContentPane().add(label_3);
		
		txtReligion = new JTextField();
		txtReligion.setBounds(227, 411, 194, 20);
		txtReligion.setColumns(10);
		txtReligion.setText(newUser.religion);
		frame.getContentPane().add(txtReligion);
		
		JCheckBox checkBox = new JCheckBox("DOCTOR");
		checkBox.setBounds(487, 411, 169, 28);
		if(newUser.doctor==1)
			checkBox.setSelected(true);
		else
			checkBox.setSelected(false);
		frame.getContentPane().add(checkBox);
		
		JLabel label_4 = new JLabel("RESUME");
		label_4.setBounds(131, 470, 90, 16);
		frame.getContentPane().add(label_4);
		
		txtName1 = new JTextField();
		txtName1.setBounds(227, 38, 194, 26);
		txtName1.setColumns(10);
		txtName1.setText(newUser.nameFirst);
		frame.getContentPane().add(txtName1);
		
		txtdob = new JTextField();
		txtdob.setBounds(227, 79, 429, 28);
		txtdob.setColumns(10);
		txtdob.setText(newUser.dob);
		frame.getContentPane().add(txtdob);
		
		txtFather = new JTextField();
		txtFather.setBounds(253, 125, 403, 26);
		txtFather.setColumns(10);
		txtFather.setText(newUser.father);
		frame.getContentPane().add(txtFather);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(227, 179, 254, 20);
		txtEmail.setColumns(10);
		txtEmail.setText(newUser.email);
		frame.getContentPane().add(txtEmail);
		
		txtSex = new JTextField();
		txtSex.setBounds(529, 179, 127, 20);
		txtSex.setColumns(10);
		txtSex.setText(""+newUser.sex);
		frame.getContentPane().add(txtSex);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(253, 214, 403, 26);
		txtPhone.setColumns(10);
		txtPhone.setText(""+newUser.phone);
		frame.getContentPane().add(txtPhone);
		
		
		BufferedImage img=null;
		File i=null;
		try{
			i=new File(newUser.picLoc);
			img=ImageIO.read(i);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Image dimg= img.getScaledInstance(103, 115, Image.SCALE_SMOOTH);
		ImageIcon imageIcon=new ImageIcon(dimg);
		JLabel lblProfilePic = new JLabel(imageIcon);
		lblProfilePic.setBounds(14, 11, 103, 140);
		frame.getContentPane().add(lblProfilePic);
		
	
		JButton btnDownload = new JButton("DOWNLOAD");
		btnDownload.setBounds(227, 465, 194, 26);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String path=FileClient.getPathViper("8")+newUser.uid+".txt";
				FileClient.connectToServer("8",""+newUser.uid+".txt");
				JOptionPane.showMessageDialog(null, "Content fetched!!!");
				
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
				/*EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TxtContentDisplay window = new TxtContentDisplay(path);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
				
			}
		});
		frame.getContentPane().add(btnDownload);
		
		JButton btnNextToKeen = new JButton("NEXT TO KEEN");
		btnNextToKeen.setBounds(679, 84, 167, 26);
		btnNextToKeen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NextToKeen window = new NextToKeen(newUser);
							window.frmNexttokeen.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnNextToKeen);
		
		JButton btnL = new JButton("L3");
		btnL.setBounds(679, 173, 167, 26);
		btnL.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent arg0) {
				
				if(CurrentProfile.checkFileExist("glevel3","docLoc",newUser.uid)){
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PinPanel window = new PinPanel("9",newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				}
				else{
					JOptionPane.showMessageDialog(null, "no File Found!!!");
				}
			}
		});
		frame.getContentPane().add(btnL);
		
		JButton btnM = new JButton("M1");
		btnM.setBounds(679, 214, 167, 26);
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M1Panel window = new M1Panel(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		frame.getContentPane().add(btnM);
		
		JButton btnM_1 = new JButton("M3");
		btnM_1.setBounds(679, 309, 167, 26);
		btnM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M3pin window = new M3pin(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});				
			}
		});
		frame.getContentPane().add(btnM_1);
		
		JButton btnEditProfile = new JButton("EDIT PROFILE");
		btnEditProfile.setBounds(679, 407, 167, 26);
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EditPin window = new EditPin(newUser);
							frame.dispose();
							window.frmPin.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnEditProfile);
		
		JButton btnChangePasswords = new JButton("CHANGE PASSWORDS");
		btnChangePasswords.setBounds(131, 556, 221, 26);
		btnChangePasswords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChangePassword window = new ChangePassword(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnChangePasswords);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.setBounds(487, 604, 85, 26);
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							login window = new login();
							window.frmViper.setVisible(true);
							frame.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnLogOut);
		
		JButton btnOtp = new JButton("OTP BLOCK!");
		btnOtp.setBounds(679, 556, 167, 26);
		btnOtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CurrentProfile.deleteOTP(newUser.uid);
				JOptionPane.showMessageDialog(null, "OTP BLOCKED!!");
			}
		});
		frame.getContentPane().add(btnOtp);
		
		JLabel lblUid = new JLabel(Integer.toString(newUser.uid));
		lblUid.setBounds(46, 183, 71, 16);
		frame.getContentPane().add(lblUid);
		
		txtName2 = new JTextField();
		txtName2.setBounds(435, 38, 221, 26);
		txtName2.setText(newUser.nameLast);
		frame.getContentPane().add(txtName2);
		txtName2.setColumns(10);
		
		JButton btnM_2 = new JButton("M2");
		btnM_2.setBounds(679, 262, 167, 27);
		btnM_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/*	EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M2Pane window = new M2Pane(newUser);
							window.frmM.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				EventQueue.invokeLater(new Runnable() {
					
					  public void run() {
						try {
							PinPanel window = new PinPanel("A",newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							M2Pin window = new M2Pin(newUser);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frame.getContentPane().add(btnM_2);
		
		JLabel lblUid_1 = new JLabel("uid:");
		lblUid_1.setBounds(14, 183, 60, 16);
		frame.getContentPane().add(lblUid_1);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CurrentProfile.fetchAll(newUser);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UserProfilePanel window = new UserProfilePanel(newUser);
							window.frame.setVisible(true);
							frame.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnRefresh.setBounds(679, 487, 167, 26);
		frame.getContentPane().add(btnRefresh);
		
		
	}
}

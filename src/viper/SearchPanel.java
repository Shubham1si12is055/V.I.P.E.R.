package viper;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import  facerecognition.gui.*;
import facerecognition.javafaces.FaceRec;
public class SearchPanel {

	JFrame frame;
	private JTextField txt;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPanel window = new SearchPanel();
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
	public SearchPanel(CurrentProfile newuser) {
		initialize(newuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 391, 157);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SEARCH");
		String list[]={"UID","USERNAME"};
		JComboBox comboBox = new JComboBox(list);
		comboBox.setBounds(10, 48, 109, 20);
		frame.getContentPane().add(comboBox);
		
		txt = new JTextField();
		txt.setBounds(129, 48, 137, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=(String)comboBox.getSelectedItem();
			//	CurrentProfile otherPofile=null;
			//	System.out.print(s);
				String username;
				switch(s){
					case "UID":
						
						username=CurrentProfile.fetchUsername(Integer.parseInt(txt.getText()));
						openOther(username);
						break;
					case "USERNAME":
						username=txt.getText();
						openOther(username);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Select One!!");
						break;
				}
			}

			private void openOther(String username) {
				CurrentProfile otherPofile=new CurrentProfile();
				otherPofile.username=username;
				CurrentProfile.fetchAll(otherPofile);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							OtherViewPanel window = new OtherViewPanel(otherPofile,newUser);
							frame.dispose();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnSearch.setBounds(276, 47, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnPicture = new JButton("PICTURE");
		btnPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int uid[]=CurrentProfile.getAllUid();
				for(int i:uid){
					if(i==0)
						continue;
					 String path;
					 System.out.println(i+"\t");
			    	  path=FileClient.getPathViper("7")+i+".png";
			    	  path=path.replace("\\", "\\\\");
			    	  FileClient.connectToServer("7", ""+i+".png");
				}
				EventQueue.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	FaceRecView view=new FaceRecView("FACE RECOGNITION");
		            	FaceRec model=new FaceRec();
		            	//new SimpleController
		            	new SimpleController(view,model);
		            }
		        });
				
			}
		});
		btnPicture.setBounds(156, 97, 89, 23);
		frame.getContentPane().add(btnPicture);
	}
}

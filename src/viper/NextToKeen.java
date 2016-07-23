package viper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NextToKeen {

	public JFrame frmNexttokeen;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NextToKeen window = new NextToKeen(null);
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
	public NextToKeen(CurrentProfile newUser) {
		initialize(newUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CurrentProfile newUser) {
		try{
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		frmNexttokeen = new JFrame();
		frmNexttokeen.setTitle("NextToKeen");
		frmNexttokeen.setResizable(false);
		frmNexttokeen.setBounds(100, 100, 450, 244);
		frmNexttokeen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNexttokeen.getContentPane().setLayout(null);
		
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
		comboBox.setBounds(22, 30, 200, 37);
		frmNexttokeen.getContentPane().add(comboBox);
		
		JButton btnOtpGenrate = new JButton("OTP Genrate");
		btnOtpGenrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id;
				String txt=(String) comboBox.getSelectedItem();
				id=txt.indexOf(":");
				txt=txt.substring(0,id);
			//	System.out.print(txt);
				if(id>0){
					id=Integer.parseInt(txt);
				}
				int OTP=CurrentProfile.InsertOTP(id);
				System.out.println(OTP);
				JOptionPane.showMessageDialog(null,"OTP: "+OTP+"\nWait for a while!!");
				if(OTP!=0){
				ScheduledThreadPoolExecutor exec =new ScheduledThreadPoolExecutor(1);
				exec.schedule(new Runnable() {
					
					@Override
					public void run() {
						int id;
						String txt=(String) comboBox.getSelectedItem();
						id=txt.indexOf(":");
						txt=txt.substring(0,id);
					//	System.out.print(txt);
						if(id>0){
							id=Integer.parseInt(txt);
						}
						CurrentProfile.deleteOTP(id);
						JOptionPane.showMessageDialog(null, "OTP expired!!!");
						
					}
				}, 60*1, TimeUnit.SECONDS);
				
				
				}
			}
		});
		btnOtpGenrate.setBounds(249, 33, 155, 31);
		frmNexttokeen.getContentPane().add(btnOtpGenrate);
		
		JButton btnAddNexttokeen = new JButton("Add NextToKeen");
		btnAddNexttokeen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().isEmpty())
					return;
				if(Integer.parseInt(textField.getText())<1){
					JOptionPane.showMessageDialog(null, "ERROR INVALID UID");
				}
				CurrentProfile.addKeen(newUser.uid,Integer.parseInt(textField.getText()));
				int keen1[]=CurrentProfile.keenList(newUser.uid);
				String uname;
				comboBox.removeAllItems();
				for(int keenId: keen1){
					uname=CurrentProfile.fetchUsername(keenId);
					comboBox.addItem(keenId+": "+uname);
				}
					
			}
		});
		btnAddNexttokeen.setBounds(249, 135, 155, 37);
		frmNexttokeen.getContentPane().add(btnAddNexttokeen);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE  )){
					arg0.consume();
				}
			}
		});
		textField.setBounds(80, 143, 142, 20);
		frmNexttokeen.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUid = new JLabel("UID:");
		lblUid.setBounds(22, 146, 46, 14);
		frmNexttokeen.getContentPane().add(lblUid);
	}
}

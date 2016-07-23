package viper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class CurrentProfile {
	public String nameFirst,nameLast,dob,religion,father,email,username,picLoc,resLoc="n/a",address,city,state,password;
	public String docLoc="n/a",blood="n/a",allergies1="n/a",allergies2="n/a",allergies3="n/a";
	public String insurenceLoc="n/a",medHistoryLoc="n/a",docReportLoc="n/a",hospitalname="n/a";
	public char sex=0;
	static String server="localhost";
	protected static boolean changePic=false;
	protected static boolean changeResume=false;
	protected static boolean changeDocument=false;
	int doctor;
	public int uid=0,pin=0,key=0,keen=0,doctorUid=0;
	long phone=0;
	boolean otpVerify=false;                           // if otp verification is done then no need of futher re-verification
	
	public CurrentProfile(){
		keen=0;doctorUid=0;insurenceLoc="";
	}
	
	 private void fileCopy(String fromFile, String toFile) {
		File source=new File(fromFile);
		File dest=new File(toFile);
		try {
			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR in copy of file "+fromFile);
			e.printStackTrace();
		}		
	}
	
	 static int getUid() throws SQLException{
		  int uid=-1;
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		 stmt = conn.createStatement();
	     
		 try{
		 String sql;
	      sql = "SELECT MAX(uid) FROM login WHERE 1";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  uid=Integer.parseInt(rs.getString(1))+1;
	      }
		
		 }catch(Exception e){
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "ERROR in executing sql statment");
		 }
		 conn.close();
		return uid;
	}
	
	
	static boolean usernamePasswordCheck(String username, String passwd) throws Exception {
		boolean valid=false;
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  return valid;
			  
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			return valid;
		}
		 stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT password FROM login WHERE username=\""+username+"\"";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  if(rs.getString(1).matches(passwd))
	    		  valid=true;
	    	  
	      }
		conn.close();
		return valid;
	}
	
	 
	//@SuppressWarnings("finally")
	public boolean loginTableInsert(String password){
		 	boolean valid=false;
		 	Connection conn = null;
		 	Statement stmt = null;
		 	String sql=null;
		 	try{
		 		Class.forName("com.mysql.jdbc.Driver");		
		 		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		 		stmt = conn.createStatement();
		 		sql = "INSERT INTO login(uid,username,password) VALUES ("+this.uid+",\'"+this.username+"\'"+",\'"+this.password+"\');";
		 		stmt.execute(sql);
		 		valid=true;
		 	}
		 	catch(Exception e){
		 		e.printStackTrace();
		 		JOptionPane.showMessageDialog(null, "no database server connection1!!!");
		 		return false;
		 		
		 	}
		 	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return valid;
		 	
		 
	      }
	

	 public boolean l1Tableinsert() throws SQLException{
			  
			  Connection conn = null;
			  String sql=null;
			  Statement stmt =null;
			  
			  try{
			Class.forName("com.mysql.jdbc.Driver");		
			conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
				stmt = conn.createStatement();
				String path=FileClient.getPathViper("1")+uid+".png" ;
				path=path.replace("\\", "\\\\");
				sql = "INSERT INTO gLevel1(nameFirst,nameLast,dob,fatherName,email,sex,picLoc,uid) VALUES ("+"\'"+nameFirst+"\',\'"+nameLast+"\',\'"+dob+"\',\'"+father+"\',\'"+email+"\',\'"+sex+"\',\'"+path+"\',"+uid+");";
				////System.out.print(sql);
				stmt.execute(sql);
				fileCopy(picLoc,path);
				picLoc=path;
				FileClient.connectToServer("1",""+uid+".png" );
			  }
			  catch(Exception e){
				  e.printStackTrace();
				  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
				  return false;
			  }
	      	conn.close();
			return true;
		
	}
	 
	 

	public boolean l2Tableinsert() throws SQLException{
		  Statement stmt = null;
		  Connection conn = null;
		  String sql=null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		stmt = conn.createStatement();
		String path=FileClient.getPathViper("2")+uid+".txt";
		path=path.replace("\\", "\\\\");
		sql = "INSERT INTO gLevel2(uid,phone,address,city,state,pin,religion,doctor,resLoc) VALUES ("+this.uid+","+phone+",\'"+address+"\',\'"+city+"\',\'"+state+"\',"+pin+",\'"+religion+"\',\'"+doctor+"\',\'"+path+"\')";
		////System.out.print(sql);
		stmt.execute(sql);
		fileCopy(resLoc,path);
		resLoc=path;
		FileClient.connectToServer("2",""+uid+".txt" );
		
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!  "+e);
			  return false;
			  //return valid;
		  }
	 	conn.close();
		return true;
	
	 }
	 
	 
	 public boolean l3Tableinsert() throws SQLException{
		 Statement stmt = null;String sql;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");	
		docLoc=docLoc.replace("\\", "\\\\");
		String path=FileClient.getPathViper("3")+uid+".txt" ;
		path=path.replace("\\", "\\\\");
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		stmt = conn.createStatement();
		if(docLoc!=null&&pin!=0){
		sql = "INSERT INTO gLevel3(uid,docLoc,passKey) VALUES ("+this.uid+",\'"+path+"\',"+key+");";
		//System.out.print(sql);
		stmt.execute(sql);
		if(changeDocument){
			fileCopy(docLoc,path);
			docLoc=path;
			FileClient.connectToServer("3",""+uid+".txt" );
		}
		}
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection !!! \n"+e);
			  return false;
		  }	 	
	 	conn.close();
		return true;
	
	 }
	 
	  
	 
	 public boolean m1Tableinsert() throws SQLException{
		  
		  Connection conn = null;
		  String sql=null;
		  Statement stmt =null;
		  
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
			stmt = conn.createStatement();
			sql = "INSERT INTO mLevel1(uid,bloodGroup,allergies1,allergies2,allergies3) VALUES ("+this.uid+",\'"+blood+"\',\'"+allergies1+"\',\'"+allergies2+"\',\'"+allergies3+"\');";
		 	//System.out.print(sql);
			stmt.execute(sql);
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
			  return false;
		  }
     	conn.close();
		return true;
	
}
	 
	 
	 
	 public boolean m2Tableinsert() throws SQLException{
			  
			  Connection conn = null;
			  String sql=null;
			  Statement stmt =null;
			  
			  try{
			Class.forName("com.mysql.jdbc.Driver");		
			conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
				stmt = conn.createStatement();
				
				if(!(insurenceLoc==null||insurenceLoc=="n/a"||insurenceLoc=="")){
					insurenceLoc=insurenceLoc.replace("\\", "\\\\");
					String path=FileClient.getPathViper("4")+uid+".txt" ;
					path=path.replace("\\", "\\\\");	
					
			 	sql = "INSERT INTO mLevel2(uid,insurenceLoc) VALUES ("+this.uid+",\'"+path+"\');";
			 	
			 	//System.out.print(sql);
			 	stmt.execute(sql);
			 	
			 	fileCopy(insurenceLoc,path);
				insurenceLoc=path;
				FileClient.connectToServer("4",""+uid+".txt" );
			 	
				}
				
				if(keen!=0){
			 	sql = "INSERT INTO keen(uid1,uid2) VALUES ("+uid+","+keen+");";
			 	//System.out.print(sql);
			 	stmt.execute(sql);
				}
				
				if(doctorUid!=0){
			 	sql = "INSERT INTO visits(uidPatient,uidDoc) VALUES ("+uid+","+doctorUid+");";
			 	//System.out.print(sql);
			 	stmt.execute(sql);
				}
			  }
			  catch(Exception e){
				  e.printStackTrace();
				  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
				  return false;
			  }
	      	conn.close();
			return true;
		
	}
	 

	 
	 
	 public boolean m3Tableinsert() throws SQLException{
		  
		  Connection conn = null;
		  String sql=null;
		  Statement stmt =null;
		  String path="n/a";
		  
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
			
			
			stmt = conn.createStatement();
			if(medHistoryLoc==null)
				medHistoryLoc="n/a";
			if(docReportLoc==null)
				medHistoryLoc="n/a";
			if(hospitalname==null)
				hospitalname="n/a";

			medHistoryLoc=medHistoryLoc.replace("\\", "\\\\");
			docReportLoc=docReportLoc.replace("\\", "\\\\");
			
			if(!(medHistoryLoc==null||medHistoryLoc=="n/a"||medHistoryLoc=="")){
				path=FileClient.getPathViper("5")+uid+".txt" ;
				path=path.replace("\\", "\\\\");
				fileCopy(insurenceLoc,path);
				insurenceLoc=path;
				FileClient.connectToServer("5",""+uid+".txt" );
				
			}
			if(!(docReportLoc==null||docReportLoc=="n/a"||docReportLoc=="")){
				path=FileClient.getPathViper("6")+uid+".txt" ;
				path=path.replace("\\", "\\\\");
				fileCopy(docReportLoc,path);
				docReportLoc=path;
				FileClient.connectToServer("6",""+uid+".txt" );
				
			}
			
			
		 	sql = "INSERT INTO mLevel3(uid,medHistoryLoc,docReportLoc,hospitalName) VALUES ("+this.uid+",\'"+medHistoryLoc+"\',\'"+docReportLoc+"\',\'"+hospitalname+"\');";
		 	//System.out.print(sql);
		 	stmt.execute(sql);
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
			  return false;
		  }
     	conn.close();
		return true;
	
}

	 
	public static void fetchAll(CurrentProfile user) {
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		 stmt = conn.createStatement();
	     
		 int count=0;
		 String sql;
	      sql = "select l.uid, a.nameFirst, a.nameLast, a.dob, a.fatherName, a.email, a.sex, a.picLoc, b.phone, b.address, b.city, b.state, b.pin, b.religion, b.doctor, b.resLoc  from login l, glevel1 a,glevel2 b where l.username=\'"+user.username+"\' and l.uid=a.uid and a.uid=b.uid ";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	 count++;
	    	  user.uid=Integer.parseInt(rs.getString(1));
	    	  user.nameFirst=rs.getString(2);
	    	  user.nameLast=rs.getString(3);
	    	  user.dob=rs.getString(4);
	    	  user.father=rs.getString(5);
	    	  user.email=rs.getString(6);
	    	  user.sex=rs.getString(7).charAt(0);
	    	  user.picLoc=rs.getString(8);
	    	  user.phone=Long.parseLong(rs.getString(9));
	    	  user.address=rs.getString(10);
	    	  user.city=rs.getString(11);
	    	  user.state=rs.getString(12);
	    	  user.pin=Integer.parseInt(rs.getString(13));
	    	  user.religion=rs.getString(14);
	    	  user.doctor=Integer.parseInt(rs.getString(15));
	    	  user.resLoc=rs.getString(16);  
	    	  
	    	  String path;
	    	  path=FileClient.getPathViper("7")+user.uid+".png";
	    	  path=path.replace("\\", "\\\\");
	    	  FileClient.connectToServer("7", ""+user.uid+".png");
	    	  user.picLoc=path;
	    	  
	      }
	     if(count==0)
	    	 JOptionPane.showMessageDialog(null, "NO MATCH FOUND!!");
		 }catch(Exception e){
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "!!ERROR in executing sql statment "+e);
		 }
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public static void fetchSecurely(CurrentProfile user) {
			
		user.fetchM1Details();
		user.fetchM2Detail();
		user.fetchM3Details();
		//String emaiAdd=null;
		  Statement stmt = null;
		  Statement stmt1 = null;
		  Statement stmt2 = null;
		  Connection conn = null;
		try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		
		//*****************for docloc**********************//
		
		 stmt = conn.createStatement();
	     
		
		 String sql;
	      sql = "SELECT docLoc,passkey FROM glevel3 WHERE uid="+user.uid+";";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  user.docLoc=(rs.getString(1));
	    	  user.pin=Integer.parseInt(rs.getString(2));
	      }
	     
	   //*****************for insurance loc**********************//
	     
	     stmt1 = conn.createStatement();
	      sql = "SELECT insurenceLoc FROM mlevel2 WHERE uid="+user.uid+";";
	      ResultSet rs1 = stmt1.executeQuery(sql);
	     if(rs1.next()){
	    	  user.insurenceLoc=(rs1.getString(1));
	      }
	     
	     //*****************for medical loc**********************//
	     
	     stmt2 = conn.createStatement();
	      sql = "SELECT medHistoryLoc,docReportLoc FROM mlevel3 WHERE uid="+user.uid+";";
	      ResultSet rs2 = stmt2.executeQuery(sql);
	     if(rs2.next()){
	    	  user.medHistoryLoc=(rs2.getString(1));
	    	  user.docReportLoc=(rs2.getString(2));
	      }
	    
		conn.close();
		 
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null, "ERROR in executing sql statment while fetchEmailAddress\n"+e);
		 }
		 finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		
	}
	
	
	static String fetchUsername(int i){
		 String username=null;
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		 stmt = conn.createStatement();
	     
		
		 String sql;
	      sql = "SELECT username FROM login WHERE uid="+i+";";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  username=(rs.getString(1));
	      }
		
		 }catch(Exception e){
			 JOptionPane.showMessageDialog(null, "ERROR in executing sql statment "+e);
		 }
		  finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return (username);
		
	}
	
	
	static String fetchEmailAddress(int i){
		  //int uid=-1;
		//String emaiAdd=null;
		  Statement stmt = null;
		  Connection conn = null;
		  String emailAdd=null;
		try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		 stmt = conn.createStatement();
	     
		
		 String sql;
	      sql = "SELECT email FROM glevel1 WHERE uid="+i+";";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  emailAdd=(rs.getString(1));
	      }
		
		 }catch(Exception e){
			 JOptionPane.showMessageDialog(null, "ERROR in executing sql statment while fetchEmailAddress\n"+e);
		 }
		 finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return (emailAdd);
	}

	public static int InsertOTP(int uid) {
		
		Connection conn = null;
	 	Statement stmt1 = null;
	 	Statement stmt2 = null;
	 	int random=0;
	 	String sql=null;
	 	try{
	 		Class.forName("com.mysql.jdbc.Driver");		
	 		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
	 		stmt1 = conn.createStatement();
	 		
	 		 sql = "SELECT otp1 FROM otpstore WHERE uid="+uid+";";
		      ResultSet rs = stmt1.executeQuery(sql);
		     if(rs.next()){
		    	  random=Integer.parseInt(rs.getString(1));
		     } 
	 		if(random==0){
	 		Random randomGenerator = new Random();
	 		random=randomGenerator.nextInt(1000000);
	 		stmt2 = conn.createStatement();
	 		sql = "INSERT INTO otpstore(uid,otp1) VALUES ("+uid+","+random+");";
	 		stmt2.execute(sql);
	 		// TODO enable sendemail
	 		SendEmail.sendMessage(fetchEmailAddress(uid) ,Long.toString(random));
	
	 		}
	 
	 	}
	 	catch(Exception e){
	 		e.printStackTrace();
	 		JOptionPane.showMessageDialog(null, "no database server connection while InsertOTP!!!\n"+e);
	 		
	 	}
	 	 finally{
				try {
					conn.close();
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
			}
		return random;

		
	}

	public static void deleteOTP(int uid) {
		Connection conn = null;
	 	Statement stmt = null;
	 	//int random=0;
	 	String sql=null;
	 	try{
	 		Class.forName("com.mysql.jdbc.Driver");		
	 		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
	 		stmt = conn.createStatement();
	 		
	 		 sql = "SELECT otp1 FROM otpstore WHERE uid="+uid+";";
		      ResultSet rs = stmt.executeQuery(sql);
		     if(rs.next()){
	
	 		stmt = conn.createStatement();
	 		sql = "DELETE FROM otpstore where uid="+uid+";";
	 		stmt.execute(sql);
	 		JOptionPane.showMessageDialog(null, "deleted");
	 		}
	 
	 	}
	 	catch(Exception e){
	 		e.printStackTrace();
	 		JOptionPane.showMessageDialog(null, "no database server connection while deleteOTP!!!\n"+e);
	 		
	 	}
	 	 finally{
				try {
					conn.close();
				} catch (SQLException e) {
		
					e.printStackTrace();
				}
			}		
	}

	public static int[] keenList(int uid) {

		  //int uid=-1;
		//String[] s1=new String[30];
		int s[]=new int[20];
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		  
		 stmt = conn.createStatement();
		 String sql;
	      sql = "SELECT uid1 FROM keen WHERE uid2="+uid;
	      ResultSet rs = stmt.executeQuery(sql);
	      int i=0;
	     while(rs.next()){
	    	  //return(rs.getString(1));
	    	 s[i++]=Integer.parseInt(rs.getString(1));
	      }
	     conn.close();
		
		 }catch(Exception e){
			 JOptionPane.showMessageDialog(null, "ERROR keenList in executing sql statment "+e);
			 
		 }
		return (s);
	}

	
	public static void addKeen(int uid2, int parseInt) {
	
		Connection conn = null;
	 	Statement stmt = null;
	 	boolean flag=true;
	
	 	String sql=null;
	 	try{
	 		Class.forName("com.mysql.jdbc.Driver");		
	 		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
	 		stmt = conn.createStatement();
	 		
	 		 sql = "SELECT uid2 FROM keen WHERE uid1="+uid2+";";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  if(parseInt==rs.getInt(1)){
		    		  flag = false;break;   //means the entry in next to keen already present
		    	  }
		      }
		     if(flag){
	 		stmt = conn.createStatement();
	 		sql = "INSERT INTO keen(uid1,uid2) VALUES ("+uid2+","+parseInt+");";
	 		stmt.execute(sql);
	 		JOptionPane.showMessageDialog(null, "NEXT TO KEEN INSERTED!!!");
	
	 		}
	 
	 	}
	 	catch(Exception e){
	 		e.printStackTrace();
	 		JOptionPane.showMessageDialog(null, "Error addKeen PLZ recheck!!!\n"+e);
	 		
	 	}
	 	 finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		
	}
	
	public static void changePassword(int uid, String passwd) throws Exception {
		// TODO Auto-generated method stub
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection123!!!");
			  
			  
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection4!!!");
		}
		 stmt = conn.createStatement();
	      String sql;
	      sql = "UPDATE login SET password=\'"+passwd+"\' WHERE uid="+uid+";";
	      
	      stmt.execute(sql);
	      //ResultSet rs = stmt.executeQuery(sql);
	    /* if(rs.next()){
	    	  if(rs.getString(1).matches(passwd))
	    		  valid=true;
	    	  
	      }*/
		conn.close();
	}


	public static boolean checkPin(String text,int uid) {
		boolean valid=false;
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  return valid;
			  
		  }
		try{
		 stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT passKey FROM glevel3 WHERE uid=\'"+uid+"\'";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  if(Integer.parseInt(rs.getString(1))==Integer.parseInt(text))
	    		  valid=true;
	    	  
	      }
		conn.close();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR "+e);
		}
		return valid;
	}


	public static boolean checkFileExist(String tableName, String coloumn, int uid) {
		boolean valid =false;  
		Statement stmt = null;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  
		 /* catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }*/
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		 stmt = conn.createStatement();
		 String sql;
	      sql = "SELECT "+coloumn+" FROM "+tableName+" WHERE uid="+uid+";";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  if(rs.getString(1)!=null||!rs.getString(1).matches("")||!rs.getString(1).matches("n/a")){
	    		  valid=true;
	    	  }
	      }
	     conn.close();
		 
		 }catch(Exception e){
			 e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "ERROR in executing sql statment "+e);
		 }
		 
		return valid;
		
	}

	
	
	public void fetchM1Details() {
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		if(!conn.isValid(0))
		{
			JOptionPane.showMessageDialog(null, "no database server connection!!!");
			//return valid;
		}
		 stmt = conn.createStatement();
	     
		 
		 String sql;
	      sql = "select bloodGroup, allergies1,allergies2,allergies3 from mlevel1 where uid="+uid+";";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  blood=rs.getString(1);
	    	  allergies1=rs.getString(2);
	    	  allergies2=rs.getString(3);
	    	  allergies3=rs.getString(4);
	      }
		
		 }catch(Exception e){
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "!!ERROR in executing sql statment "+e);
		 }
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

	public void fetchM2Detail() {
		
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		  
		 stmt = conn.createStatement();
		 String sql;
	      sql = "SELECT uidDoc FROM visits  WHERE uidPatient="+uid;
	      ResultSet rs = stmt.executeQuery(sql);
	     
	     if(rs.next()){
	    	  //return(rs.getString(1));
	    	doctorUid=Integer.parseInt(rs.getString(1));
	      }
	     conn.close();
		
		 }catch(Exception e){
			 JOptionPane.showMessageDialog(null, "ERROR <doctor> in executing sql statment "+e);
			 
		 }
		
	}
	
	

	public void fetchM3Details() {
		 Statement stmt = null;
		  Connection conn = null;
		  try{
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  //return valid;
		  }
		  
		 stmt = conn.createStatement();
		 String sql;
	      sql = "SELECT hospitalName FROM mlevel3  WHERE uid="+uid;
	      ResultSet rs = stmt.executeQuery(sql);
	     
	     if(rs.next()){
	    	  //return(rs.getString(1));
	    	hospitalname=rs.getString(1);
	      }
	     conn.close();
		
		 }catch(Exception e){
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "ERROR in <hospitalname> executing sql statment "+e);
			 
		 }
		
	}

	public static boolean otpCheck(int otp,int uid) {
		boolean valid=false;
		  Statement stmt = null;
		  Connection conn = null;
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!");
			  return valid;
			  
		  }
		try{
		 stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT otp1 FROM otpstore WHERE uid=\'"+uid+"\'";
	      ResultSet rs = stmt.executeQuery(sql);
	     if(rs.next()){
	    	  if(Integer.parseInt(rs.getString(1))==otp)
	    		  valid=true;
	    	  
	      }
		conn.close();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "OTP Expired "+e);
		}
		return valid;
	}

	public boolean l1TableUpdate() throws SQLException{
		  
		  Connection conn = null;
		  String sql=null;
		  //String path=null;
		  Statement stmt =null;
		  
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn =  DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
			stmt = conn.createStatement();
			picLoc=picLoc.replace("\\", "\\\\");
			String path=FileClient.getPathViper("1")+uid+".png" ;
			path=path.replace("\\", "\\\\");
			sql = "UPDATE gLevel1 SET nameFirst=\'"+nameFirst+"\', nameLast= \'"+nameLast+"\', dob= \'"+dob+"\',fatherName=\'"+father+"\', email=\'"+email+"\', sex= \'"+sex+"\', picLoc= \'"+path+"\' WHERE uid="+uid+";";
			////System.out.print(sql);
			stmt.execute(sql);
			if(changePic){
				fileCopy(picLoc,path);
				picLoc=path;
				FileClient.connectToServer("1",""+uid+".png" );
			}
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
			  return false;
		  }
  	conn.close();
		return true;
	
}


public boolean l2TableUpdate() throws SQLException{
	  Statement stmt = null;
	  Connection conn = null;
	  String sql=null;
	  try{
	Class.forName("com.mysql.jdbc.Driver");		
	conn =  DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
	stmt = conn.createStatement();
	String path=FileClient.getPathViper("2")+uid+".txt";
	path=path.replace("\\", "\\\\");
	
	sql = "UPDATE gLevel2 SET phone= "+phone+" , address= \'"+address+"\', city = \'"+city+"\', state = \'"+state+"\', pin= "+pin+", religion= \'"+religion+"\', doctor= \'"+doctor+"\', resLoc= \'"+path+"\' WHERE uid="+uid+";";
	////System.out.print(sql);
	stmt.execute(sql);
	if(changeResume){
			fileCopy(resLoc,path);
			resLoc=path;
			FileClient.connectToServer("2",""+uid+".txt" );
	}
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "no database server connection!!!  "+e);
		  return false;
		  //return valid;
	  }
	conn.close();
	return true;

}


public boolean l3TableUpdate() throws SQLException{
	 Statement stmt = null;String sql;
	  Connection conn = null;
	  try{
	Class.forName("com.mysql.jdbc.Driver");	
	docLoc=docLoc.replace("\\", "\\\\");
	String path=FileClient.getPathViper("3")+uid+".txt" ;
	path=path.replace("\\", "\\\\");
	conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
	stmt = conn.createStatement();
	if(docLoc!=null&&pin!=0){
	sql = "Update gLevel3 SET docLoc=\'"+docLoc+"\', passKey= "+pin+" WHERE uid="+uid+";";
	//System.out.print(sql);
	stmt.execute(sql);
	fileCopy(docLoc,path);
	docLoc=path;
	FileClient.connectToServer("3",""+uid+".txt" );
	}
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "no database server connection !!! \n"+e);
		  return false;
	  }	 	
	conn.close();
	return true;

}






public boolean m1TableUpdate() throws SQLException{
	  
	  Connection conn = null;
	  String sql=null;
	  Statement stmt =null;
	  
	  try{
	Class.forName("com.mysql.jdbc.Driver");		
	conn =  DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		stmt = conn.createStatement();
		sql = "UPDATE mLevel1 SET bloodGroup= \'"+blood+"\',allergies1= \'"+allergies1+"\', allergies2= \'"+allergies2+"\', allergies3= \'"+allergies3+"\' WHERE uid="+uid+";";
	 	//System.out.print(sql);
		stmt.execute(sql);
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
		  return false;
	  }
	conn.close();
	return true;

}



public boolean m2TableUpdate() throws SQLException{
		  
		  Connection conn = null;
		  String sql=null;
		  Statement stmt =null;
		  
		  try{
		Class.forName("com.mysql.jdbc.Driver");		
		conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
			stmt = conn.createStatement();
			if(!(insurenceLoc==null||insurenceLoc=="n/a"||insurenceLoc=="")){
				insurenceLoc=insurenceLoc.replace("\\", "\\\\");
				String path=FileClient.getPathViper("4")+uid+".txt" ;
				path=path.replace("\\", "\\\\");	
				
				sql = "UPDATE mLevel2 SET insurenceLoc= \'"+insurenceLoc+"\' WHERE uid="+uid+";";
		 	
		 	//System.out.print(sql);
		 	stmt.execute(sql);
		 	
		 	fileCopy(insurenceLoc,path);
			insurenceLoc=path;
			FileClient.connectToServer("4",""+uid+".txt" );
		 	
			}
			
			if(doctorUid!=0){
		 	//sql = "INSERT INTO visits(uidPatient,uidDoc) VALUES ("+uid+","+doctorUid+");";
		 	//System.out.print(sql);
		 	sql="UPDATE visits set uidDoc="+doctorUid+"where uidPaitent="+uid+";";
		 	stmt.execute(sql);
			}
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
			  return false;
		  }
  	conn.close();
		return true;
	
}



	
public boolean m3TableUpdate() throws SQLException{
	  Connection conn = null;
	  String sql=null;
	  Statement stmt =null;
	  String path="n/a";
	  
	  try{
	Class.forName("com.mysql.jdbc.Driver");		
	conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
		
		
		stmt = conn.createStatement();
		if(medHistoryLoc==null)
			medHistoryLoc="n/a";
		if(docReportLoc==null)
			medHistoryLoc="n/a";
		if(hospitalname==null)
			hospitalname="n/a";

		medHistoryLoc=medHistoryLoc.replace("\\", "\\\\");
		docReportLoc=docReportLoc.replace("\\", "\\\\");
		
		if(!(medHistoryLoc==null||medHistoryLoc=="n/a"||medHistoryLoc=="")){
			path=FileClient.getPathViper("5")+uid+".txt" ;
			path=path.replace("\\", "\\\\");
			fileCopy(insurenceLoc,path);
			insurenceLoc=path;
			FileClient.connectToServer("5",""+uid+".txt" );
			
		}
		if(!(docReportLoc==null||docReportLoc=="n/a"||docReportLoc=="")){
			path=FileClient.getPathViper("6")+uid+".txt" ;
			path=path.replace("\\", "\\\\");
			fileCopy(docReportLoc,path);
			docReportLoc=path;
			FileClient.connectToServer("6",""+uid+".txt" );
			
		}
		
		
		sql = "UPDATE mLevel3 SET medHistoryLoc=\'"+medHistoryLoc+"\', docReportLoc= \'"+docReportLoc+"\', hospitalName= \'"+hospitalname+"\' WHERE uid="+uid+";";//System.out.print(sql);
	 	stmt.execute(sql);
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  JOptionPane.showMessageDialog(null, "no database server connection!!!"+e);
		  return false;
	  }
 	conn.close();
	return true;

}

public static int[] getAllUid() {
	int s[]=new int[20];
	  Statement stmt = null;
	  Connection conn = null;
	  try{
	  try{
	Class.forName("com.mysql.jdbc.Driver");		
	conn = DriverManager.getConnection("jdbc:mysql://"+server+"/viper","pranjal","");
	  }
	  catch(Exception e){
		  JOptionPane.showMessageDialog(null, "no database server connection!!!");
		  //return valid;
	  }
	  
	 stmt = conn.createStatement();
	 String sql;
    sql = "SELECT uid FROM glevel1";
    ResultSet rs = stmt.executeQuery(sql);
    int i=0;
   while(rs.next()){
  	  //return(rs.getString(1));
  	 s[i++]=Integer.parseInt(rs.getString(1));
    }
   conn.close();
	
	 }catch(Exception e){
		 JOptionPane.showMessageDialog(null, "ERROR <AllUid> in executing sql statment "+e);
		 
	 }
	return (s);
}
	
	 	 
	 
}

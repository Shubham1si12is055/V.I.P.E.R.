package viper;
import java.io.*;
import java.net.*;
import java.util.logging.*;

import javax.swing.JOptionPane;


public class FileClient {

    private static Socket sock;
   private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;
    static String path=null;

    public static void connectToServer(String code,String fName){
    	fileName=fName;
    	path=getPathViper(code);
  	  path.replace("\\", "\\\\");
        try {
            sock = new Socket(CurrentProfile.server, 4444);
            stdin = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to the server, try again later.");
            //System.exit(1);
        }

        try {
			os = new PrintStream(sock.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in geting printstream");
		}
        if(Character.isLowerCase(code.charAt(0))){
        	code=""+Character.toUpperCase(code.charAt(0));
        }
        try {
              switch (code) {
              case "1":
              case "2":
              case "3":
              case "4":
              case "5":
              case "6":
                os.println(code);
                sendFile();
                break;
              case "7":
              case "8":
              case "9":
              case "a":
              case "b":
              case "c":
              case "A":
              case "B":
              case "C":
                os.println(code);
                /*System.err.print("Enter file name: ");
                fileName = stdin.readLine();*/
                os.println(fileName);
                receiveFile(fileName);
                break;
        }
        } catch (Exception e) {
            System.err.println("not valid input");
        }


//        try {
//			sock.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
    }

  /*  public static String selectAction() throws IOException {
        System.out.println("1. Send file.");
        System.out.println("2. Recieve file.");
        System.out.print("\nMake selection: ");

        return stdin.readLine();
    }*/

    public static void sendFile() {
        try {
          /*  System.err.print("Enter file name: ");
            fileName = stdin.readLine();
*/
            File myFile = new File(path+fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //bis.read(mybytearray, 0, mybytearray.length);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = sock.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            System.out.println("File "+fileName+" sent to Server.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File does not exist! "+e);
        }
    }

    public static void receiveFile(String fileName) {
        try {
            int bytesRead;
            InputStream in = sock.getInputStream();

            DataInputStream clientData = new DataInputStream(in);

            fileName = clientData.readUTF();
            //TODO
          //TODO
          //TODO
          //TODO
          //TODO
            //new File(path).mkdirs();
            File f = new File(path+fileName);
            if(f.exists()) {
            	if(f.delete()){
        			System.out.println(f.getName() + " is deleted!");
        		}	
            }
            
            OutputStream output = new FileOutputStream((path +fileName));
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

            output.close();
            in.close();

            System.out.println("File "+fileName+" received from Server.");
        } catch (IOException ex) {
            Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    static String getPathViper(String op){
 	   switch(op){
 	   case "1":
 		   return("D:\\viperTemp\\pic\\");
 	   case "2":
 		   return("D:\\viperTemp\\resume\\");
 	   case "3":
 		   return("D:\\viperTemp\\document\\");
 	   case "4":
 		   return("D:\\viperTemp\\insurence\\");
 	   case "5":
 		   return("D:\\viperTemp\\medhistory\\");
 	   case "6":
 		   return("D:\\viperTemp\\docreport\\");
 	   case "7":
 		   return("D:\\viperTemp\\pic\\");
 	   case "8":
 		   return("D:\\viperTemp\\resume\\");
 	   case "9":
 		   return("D:\\viperTemp\\document\\");
 	   case "A":
 		   return("D:\\viperTemp\\insurence\\");
 	   case "B":
 		   return("D:\\viperTemp\\medhistory\\");
 	   case "C":
 		   return("D:\\viperTemp\\docreport\\");
 	  case "a":
		   return("D:\\viperTemp\\insurence\\");
	   case "b":
		   return("D:\\viperTemp\\medhistory\\");
	   case "c":
		   return("D:\\viperTemp\\docreport\\");
 		   
 	   }
 	   JOptionPane.showMessageDialog(null, "Error in getting path code: "+op);
 	return ("D:\\viperTemp\\");								//never to be executed
     	
     }
}
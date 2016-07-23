package viper;



import java.net.*;
import java.util.logging.Level;
import java.util.logging.*;
import java.io.*;
/**
 
My task was to build a multi-thread file server and a client that can upload or download a named file over sockets. It is assumed that the client will finish after its operation and there is no need to supply a file list from the server (although I plan to add that). There is no error check if the client enters a file name that does not exist on the server.


Flow:

    Start Server
    Start Client, connection made if possible.
    Client then chooses whether to upload or download a file.
    Server receives this initial message and takes appropriate action.

 *
 */

class CLIENTConnection implements Runnable {

    private Socket clientSocket;
    private BufferedReader in = null;
    String path=null;

    public CLIENTConnection(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            String clientSelection;
            while ((clientSelection = in.readLine()) != null) {
            	System.out.println("\nclient Request code:"+clientSelection);
            	path=getPathViper(clientSelection);
                path.replace("\\", "\\\\");
                switch (clientSelection) {
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                        receiveFile(clientSelection);
                        break;
                    case "7":
                    case "8":
                    case "9":
                    case "A":
                    case "B":
                    case "C":  
                    case "a":
                    case "b":
                    case "c":
                        String outGoingFileName;
                        while ((outGoingFileName = in.readLine()) != null) {
                            sendFile(clientSelection,outGoingFileName);
                        }

                        break;
                    default:
                        System.err.println("\nIncorrect command received.");
                        break;
                }
                in.close();
                break;
            }

        } catch (IOException ex) {
            Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveFile(String clientSelection) {
        try {
            int bytesRead;
            
            //System.out.println("upload pathe +"+path);String fileName = clientData.readUTF();
            
            System.out.println("Executing recive");
            
            DataInputStream clientData = new DataInputStream(clientSocket.getInputStream());
            String fileName = clientData.readUTF();
           
            File f = new File(path+fileName);
            if(f.exists()) {
            	if(f.delete()){
        			System.out.println(f.getName() + " is deleted!");
        		}	
            }
            
           OutputStream output = new FileOutputStream((path+fileName));
           
            long size = clientData.readLong();
            System.out.println("4");
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }
            System.out.println("6");
            output.close();
            clientData.close();

            System.out.println("File "+fileName+" received from client.");
        } catch (IOException ex) {
            System.err.println("Client error. Connection closed. "+ex);
        }
    }

    public void sendFile(String clientSelection,String fileName) {
        try {
            //handle file read
        	//String path=getPathViper(clientSelection);
        	System.out.print("Execute sendfile\n");
        	  path.replace("\\", "\\\\");
            File myFile = new File(path+fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //bis.read(mybytearray, 0, mybytearray.length);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            //handle file send over socket
            OutputStream os = clientSocket.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            System.out.println("File "+fileName+" sent to client.");
        } catch (Exception e) {
            System.err.println("File does not exist!\n  "+fileName+" "+e);
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
	return ("D:\\viperTemp\\");								//never to be executed
    	
    }
    
    
    
}
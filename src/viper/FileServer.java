package viper;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class FileServer {

    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;
    public static void startServer(){

        try {
            serverSocket = new ServerSocket(4444);
            JOptionPane.showMessageDialog(null,"Server started.");
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null,"Error!! Port already in use.");
            System.exit(1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);

                Thread t = new Thread(new CLIENTConnection(clientSocket));

                t.start();

            } catch (Exception e) {
                System.err.println("Error in connection attempt.");
            }
        }
    }
    
    public static void stopServer(){
    	try {
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "server close!!");
			e.printStackTrace();
		}
    	
    }
 //   volatile static boolean flag=true;
    public static void main(String[] args) {
    	startServer();
    }
    
}
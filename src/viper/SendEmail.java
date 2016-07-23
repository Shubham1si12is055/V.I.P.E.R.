package viper;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendEmail {

    private static String USER_NAME = "system4viper";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "viper@12345"; // GMail password
    private static String RECIPIENT = null;

 /*   public static void main(String[] args) {
    	
    	
    }*/
    
    public static void sendMessage(String sendTo ,String msg){
    	
    //	Scanner s =new Scanner(System.in);
    	RECIPIENT=sendTo;
        String from = USER_NAME;
        String pass = PASSWORD;
        System.out.print("email ID: "+sendTo);
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "VIPER SYSTEM ACCESSED!!";
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
        Date resultdate = new Date(yourmilliseconds);
       // System.out.println(sdf.format(resultdate));
        String body = "Your viper system is being accessed on "+sdf.format(resultdate)+" you have 3min to block access\n OTP:"+msg;
        
        
        
        
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            body=body+"\nIP address : " + ip.getHostAddress()+"\nMAC address: ";

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

           // System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
            body=body+sb.toString();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e){

            e.printStackTrace();

        }
        
        
        
       

        sendFromGMail(from, pass, to, subject, body);
     //   s.close();
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.print("msg send!!");
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}


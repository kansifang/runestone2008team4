package my.application;

/**
 *
 * @author robert
 */

import java.net.*;
import java.io.*;
 
public class Client 
{	
	
	InputStream inStream;
	DataInputStream inDataStream;
	OutputStream outStream;
	DataOutputStream outDataStream;
	String message = "";
        Socket sock;
		
	// Construkctor, connects to an serverconnection on ip,port.
	public Client(String ip,int port) throws IOException
	{

		InetAddress host = InetAddress.getLocalHost();
		sock = new Socket(ip,port);
		System.out.println("Connected to ip: " + ip + " port: " + port);
		
	}	
		
	public void sendMessage(String message)
	{
            
            try {
                outStream = sock.getOutputStream();
		outDataStream = new DataOutputStream (outStream);		
		outDataStream.writeUTF(message);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }		
	}
	
	public String getMessage()
	{
             try {
                 
		inStream = sock.getInputStream ();
		inDataStream = new DataInputStream ( inStream );
		message = inDataStream.readUTF();
                
                if(message.length() > 0)
		return message;
              
             } catch (IOException e) {
                System.out.println(e.getMessage());
             }
            
        return "No message";     
        }				
}



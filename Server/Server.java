
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import javax.media.*;


public class  Server 
{	
	// Init
	InputStream inStream;
	DataInputStream inDataStream;
	OutputStream outStream;
	DataOutputStream outDataStream;
	Socket conn;
	String message="";
	boolean open = true;
	Timer timer;
	VideoTransmit transmit;
	MediaLocator ml;
	
	
	// Construct a server-connection on port port
	public Server(int port) throws IOException
	{
		ServerSocket sock = new ServerSocket(port);
		System.out.println("Server Started, waiting for connection...");
 		conn = sock.accept(); 
		System.out.println("Client connected.");
		System.out.println("Waiting for message from client...");
					
		do{
			getMessage(); 
						
		}while(!message.equals("bye"));								// When the client want to disconnect it send "bye"
		conn.close();	
	}
	
	// Reads from the instream	
	public void getMessage() throws IOException
	{
		inStream = conn.getInputStream();
		inDataStream = new DataInputStream (inStream);
		message = inDataStream.readUTF();
		System.out.println(message);								// Print out the incomming message in terminal
		
		
		
		// Here can you (US-team) edit your code
		if(message.length() > 0)		
		{		
			if(message.equals("CLIENT_forward")){
				sendMessage("Recieved message: " + message);
				// Send Bluetooth(forward)....
			}
			else if(message.equals("CLIENT_backwards")){
				sendMessage("Recieved message: " + message);
				// Send Bluetooth(backwards)....
			}
			else if(message.equals("CLIENT_left")){
				sendMessage("Recieved message: " + message);
			}
			else if(message.equals("CLIENT_right")){
				sendMessage("Recieved message: " + message);
			}
			else if(message.equals("sdfsdf")){
				sendMessage("Recieved message: " + message);
			}
			else
				sendMessage("Cant parse message: " + message);
		}				
	}
	
	// Send string message to the output
	public void sendMessage(String message) throws IOException
	{
		outStream = conn.getOutputStream();
		outDataStream = new DataOutputStream (outStream);	
		outDataStream.writeUTF(message);
	}
	

	public static void main(String[] args) throws IOException
	{
		// Videotransmit transmits the webcam via UDP
		new VideoTransmit(new MediaLocator("vfw://0"),args[0],args[1]);
		
		// Starts the server-socket for sending messages
		new Server(Integer.parseInt(args[1]));		
	}
}
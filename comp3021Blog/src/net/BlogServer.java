package net;

import java.io.*;
import java.net.*;

public class BlogServer {
	
	public static final int PORT = 3021;

	public static void main(String[] args) {
		
			String clientSentence;
			
			try {
			ServerSocket welcomeSocket = new ServerSocket(PORT);

			System.out.println("Server is listening on "+PORT+"...");
			
			Socket connectionSocket = welcomeSocket.accept();
			
			System.out.println("Client has connected to server");

			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			
			while((clientSentence = inFromClient.readLine())!=null)
			System.out.println("receive from client: " + clientSentence);
			
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	}
}

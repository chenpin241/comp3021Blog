package net;

import java.io.IOException;
import java.net.ServerSocket;


public class MultiThreadServer {

	public static final int PORT = 3021;
	
	public static void main(String[] args){
		ServerSocket server;
		int serverCount = 1;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server is listening on "+PORT+"...");
			while(true){
				
				ThreadHandler thread = new ThreadHandler(server.accept(), serverCount);
				new Thread(thread).start();
				System.out.println("Thread "+serverCount+" is Running");
				serverCount ++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
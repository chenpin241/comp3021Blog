package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ThreadHandler implements Runnable{

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private int visitor;
	private boolean isClosed;
	
	public ThreadHandler(Socket socket, int visitor) throws IOException{
		this.socket = socket;
		this.visitor = visitor;
		socket.setSoTimeout(0);
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer = new PrintWriter(socket.getOutputStream(), true);
		isClosed = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String line;
				
				while (socket.getInputStream().available() > 0 && (line = reader.readLine()) != null) {
					if (line.equals("visitor")){
						writer.println("You're the " + visitor + " visitor today");
						//writer.close();
					}
					else if (line.equals("quit")) {
						socket.close();
						isClosed = true;
						return;
					} else {
						writer.println("echo: " + line);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

}
package net;

import java.io.*;
import java.net.*;
import java.util.*;

import base.Post;

public class BlogClient {

	public static final String IP ="127.0.0.1";
	public static final int PORT = 3021;
	
	public static void main(String[] args){
		
		
		
		try {	          
			Socket socket = new Socket(IP, PORT);
			socket.setSoTimeout(0);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			while(true){
				
				System.out.print("Input something:");
				String cin = new Scanner(System.in).nextLine();
				writer.println(cin);
				if (cin.equals("quit"))
					break;
				else{
					String line;
					while(true){
						if(socket.getInputStream().available() > 0 && (line = reader.readLine()) != null){
							System.out.println(line);
							break;
						}
					}
				}
				//writer.close();
			}
			reader.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
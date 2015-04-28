package net;

import java.io.*;
import java.net.*;
import java.util.*;

import base.Post;

public class BlogClient {
	
	public static final String IP="127.0.0.1";
	public static final int PORT = 3021;
	
	public static void main(String[] args) {
        String sentence;
        Socket clientSocket = null;
        PrintWriter outToServer =null;
        BufferedReader inFromUser = null;

        try {
        	clientSocket= new Socket(IP,PORT);
        	outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
            System.out.println("Connected...");
    		inFromUser = new BufferedReader(new InputStreamReader(System.in));
    		System.out.println("Input the post: ");
    		while((sentence = inFromUser.readLine())!= null){
    			Date date = new Date();
				Post post = new Post(date, sentence);
				outToServer.println(post.toString());
    		}
            inFromUser.close();
            outToServer.flush();
            outToServer.close();
            clientSocket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
	}

}

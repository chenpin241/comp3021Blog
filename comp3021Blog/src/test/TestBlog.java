package test;

import java.io.*;
import java.util.Date;

import base.*;
import blog.*;

public class TestBlog {
	
	public String getInput(){
		String line = "";
		System.out.print("Enter the prompt: ");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			line = br.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
	
	public static void main(String[] args){
		
		TestBlog testBlog = new TestBlog();
		User user = new User(1, "COMP3021", "COMP3021@cse.ust.hk");
		Blog myBlog = new Blog(user);
		String prompt = null;
		
		while(!(prompt = testBlog.getInput()).equals("exit")){
			
			if (prompt.startsWith("list")){
				myBlog.list();
			}
			else if(prompt.startsWith("post")){
				Date date = new Date();
				String p = prompt.substring(5);
				Post post = new Post(date, p);
				myBlog.post(post);
			}
			else if(prompt.startsWith("delete")){
				String [] split = prompt.split(" ");
				int index = Integer.parseInt(split[1]);
				myBlog.delete(index);
			}
			else
				System.out.println("Error message. Logout please type 'exit'.");
		}
	}

}
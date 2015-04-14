package blog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import base.*;

public class Blog implements Serializable{
	
	private User user;
	private ArrayList<Post> allPosts;
	
	public Blog(User user) {
		super();
		this.user = user;
		this.allPosts = new ArrayList<Post>();
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Post> getAllPosts() {
		return allPosts;
	}

	public void setPosts(ArrayList<Post> allPosts) {
		this.allPosts = allPosts;
	}
	
	public void post(Post p){
		allPosts.add(p);
		System.out.println("A new post:" + "\n" + p.toString());	
	}
	
	public void list(){
		if (allPosts.isEmpty()==true)
			System.out.println("There is no post on the blog");
		else {
			System.out.println("Current posts:");
			for(int x=0,y=1; x < allPosts.size(); x++,y++)
			System.out.print("Post[" + y + "]:" + allPosts.get(x).toString() + "\n");
		}
		
	}
	
	public void delete(int index){
		if(index <1 || index > allPosts.size()){
			System.out.println("Illegal deletion");
		}
		else 
			allPosts.remove(index-1);
		
	}
	
	//@Override
	//public String toString() {
		
	//}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allPosts == null) ? 0 : allPosts.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (allPosts == null) {
			if (other.allPosts != null)
				return false;
		} else if (!allPosts.equals(other.allPosts))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	public void search(int month, String someone){
		Calendar cal = Calendar.getInstance();
		for (Post p : allPosts){
			cal.setTime(p.getDate());
			int postMonth = cal.get(Calendar.MONTH);
			if(++postMonth == month && p.getContent().contains(someone))
				System.out.println(p);	
				
		}
	}
	
	public void save(String filepath){
		try{
		FileOutputStream fs = new FileOutputStream(filepath);
		ObjectOutputStream os = new ObjectOutputStream(fs);
		os.writeObject(this);
		os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void load(String filepath){
		try {
			FileInputStream fs = new FileInputStream(filepath);
			ObjectInputStream is = new ObjectInputStream(fs);
			Object read = is.readObject();
			Blog blog = (Blog) read;
			this.user = blog.user;
			this.allPosts = blog.allPosts;
			is.close();
		} catch (FileNotFoundException e) {
			System.out.println("Wait! There is something wrong. I cannot find the file..");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
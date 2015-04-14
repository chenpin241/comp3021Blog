package base;

import java.io.Serializable;
import java.util.Date;




public class Post implements Comparable<Post>,Serializable {
	
	private Date date;
	private String content;
	

	public Post(Date date, String content){
		this.date = date;
		this.content = content;
	}
	

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return date + "\n" + content;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	public boolean contains(String keyword){
		if (keyword == null)
			return false;
		else return content.contains(keyword);
	}
	
	public int compareTo(Post p){
		return this.date.compareTo(p.date);
	}

}

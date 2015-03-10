package base;

import java.util.Comparator;

public class PostSortByContentLength implements Comparator<Post> {
	@Override
	public int compare(Post p1,Post p2){
	if(p1.getContent().length()>p2.getContent().length())
		return 1;
	else if(p1.getContent().length()<p2.getContent().length())
		return -1;
	else return 0;
	}
}

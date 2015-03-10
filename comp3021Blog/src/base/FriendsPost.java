package base;

import java.util.Date;

public class FriendsPost extends Post {
	
	private User friend;

	public FriendsPost(Date date, String content, User friend) {
		super(date, content);
		this.friend = friend;
	}

	@Override
	public String toString() {
		return friend.toString() + super.toString();
	}
	
	

}

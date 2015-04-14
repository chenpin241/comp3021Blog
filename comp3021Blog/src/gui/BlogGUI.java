package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import base.Post;
import base.User;
import blog.Blog;

public class BlogGUI implements ActionListener{
	//TODO: Ask TA: how to set the background color properly?
	
	//The reason why these are private variables is because it will be called later
	//on by other methods to handle events
	private JPanel upperPanel;
	private JPanel lowerPanel;
	private JPanel upperPanelButtonArray;
	private JTextArea postTextArea ;
	private JTextArea postContent ;
	private JButton refresh ;
	private JButton post ;
	private JLabel helpInfo ;
	private Blog myBlog;
	private final String BLOG_SAVE_PATH="c:\\blog.txt";

	public BlogGUI() {
		User me = new User(12345678, "Ben", "wsccp216@gmail.com");
		myBlog = new Blog(me);
	}
	
	class postListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String content = postTextArea.getText();
			// check whether the post is empty
			if(content == null){
				postContent.setText("Something is wrong");
			}else if( content.equals("")){
				//do nothing
			}
			//check whether the length of the post has exceeded
			if(content.length() > 140){
				postContent.setText("Your content exceed 140 characters.");
				postContent.setForeground(Color.RED);
				return;
			}
			// add the post to the file
			Date timeStamp = new Date();
			Post newPost = new Post(timeStamp, content);
			newPost.setContent(postTextArea.getText());
			myBlog.post(newPost);
			myBlog.save(BLOG_SAVE_PATH);
			myBlog.list();


			//  display the post in the display area and clear the area
			postTextArea.setText("");
			String post_content= newPost.getContent() +"\n" ;
			postContent.setText(post_content);
			helpInfo.setText("You can still input 140 characters");
			
		}
		
	}
	
	class refreshListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			myBlog.load(BLOG_SAVE_PATH);
			ArrayList<Post> postList= myBlog.getAllPosts();
			postContent.setText("");
			for (Post p : postList) {
				postContent.append(p.getContent() + "\n");
			}
		}
		
	}
	
	class helpInfoListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			//not useful
		}

		@Override
		public void keyPressed(KeyEvent e) {
			//not useful
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int contentLength = postTextArea.getText().length();
			if(contentLength<=140){
				helpInfo.setText(String.format("You can still input %d characters", 140-contentLength));
			}else{
				helpInfo.setText("Your post length has exceeded 140!");
			}
		}
		
	}
	
	public void setWindow() {
		JFrame mainFrame = new JFrame("Micro Blog Demo");
		mainFrame.setSize(500,700);
	    mainFrame.setLayout(new GridLayout(2, 1));//2 rows and 1 column
	    
		JPanel upperPanel = new JPanel(new BorderLayout());
		JPanel lowerPanel = new JPanel(new BorderLayout());

		postContent = new JTextArea("Here is my post!");//default text
		postContent.setLineWrap(true);
		postContent.setWrapStyleWord(true);
		postContent.setBackground(Color.LIGHT_GRAY);
		lowerPanel.add(postContent);
		
		postTextArea = new JTextArea("What's on your mind?");
		postTextArea.setLineWrap(true);
		postTextArea.setWrapStyleWord(true);
		postTextArea.setBackground(Color.YELLOW);
		refresh = new JButton("refresh");
		post = new JButton("post");
		helpInfo = new JLabel("You can still input 140 characters");
		upperPanelButtonArray = new JPanel(new GridLayout(1,2));
		upperPanelButtonArray.add(refresh);
		upperPanelButtonArray.add(post);
		upperPanel.add(upperPanelButtonArray,BorderLayout.SOUTH);
		upperPanel.add(helpInfo,BorderLayout.NORTH);
		upperPanel.add(postTextArea,BorderLayout.CENTER);

		mainFrame.add(upperPanel);
		mainFrame.add(lowerPanel);
		
		post.addActionListener(new postListener());
		postTextArea.addKeyListener(new helpInfoListener());
		refresh.addActionListener(new refreshListener());

		
		//always set visible at the end
		mainFrame.setVisible(true);
		//if the following line is omitted, the program won't be terminated correctly
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	public static void main(String[] args) {
		BlogGUI blogGUi = new BlogGUI();
		blogGUi.setWindow();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == post){
			postContent.setText("You clicked post");
		}else if(e.getSource() == refresh){
			postContent.setText("You clicked refresh");
		}
	}
}
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class BlogGUI implements ActionListener {
	private JFrame mainFrame;
	private JTextArea postTextArea;
	private JTextField postContent;
	private JButton refresh;
	private JButton post;
	private JPanel panel;
	private JLabel label;
	private JPanel southPanel;

	
	public BlogGUI() {}
	
	public void setWindow() {
		
		// Set up the main frame
		mainFrame = new JFrame("Micro Blog Demo");
		Container pane = mainFrame.getContentPane();
		mainFrame.setSize(500, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new GridLayout(2,0));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the first panel to hold label, text area, and 2 buttons
		panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10,10,0,10),
				BorderFactory.createEtchedBorder()));
		panel.setLayout(new BorderLayout(10, 10));
		
		// Create the label
		label = new JLabel("You can still input 140 Characters");
		label.setFont(new Font("Segoe WP",Font.BOLD,15));
		
		// Create the text area to post your text. Editable.
		postTextArea = new JTextArea(200,500);
		postTextArea.setBackground(Color.yellow);
		postTextArea.setText("What's on your mind?");
		postTextArea.setFont(new Font("Segoe WP",Font.PLAIN,14));
		
		// Create the second panel, will be nested inside the first panel
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(0,2));
		
		// Create the refresh button
		refresh = new JButton("Refresh");
		refresh.addActionListener(this);
		refresh.setBackground(Color.magenta);
		
		// Create the post button
		post = new JButton("Post");
		post.addActionListener(this);
		post.setBackground(Color.cyan);
		
		// Add the refresh and post buttons into the second panel
		southPanel.add(refresh, BorderLayout.WEST);
		southPanel.add(post, BorderLayout.EAST);
		
		// Add items into the panel
		panel.add(label, BorderLayout.NORTH);
		panel.add(postTextArea, BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);

		// Create a text field to post the content
		postContent = new JTextField();
		postContent.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10,10,10,10),
				BorderFactory.createEtchedBorder()));
		postContent.setEditable(false);
		postContent.setHorizontalAlignment(JTextField.CENTER);
		postContent.setFont(new Font("Segoe WP",Font.BOLD,22));
		
		// Add the created items into the content pane of the frame
		pane.add(panel);
		pane.add(postContent);
		
		// Make them visible
		mainFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		BlogGUI blogGUI = new BlogGUI();
		blogGUI.setWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == post) {
			postContent.setText(postTextArea.getText());
			
		} else if (e.getSource() == refresh) {
			postContent.setText("You click REFRESH!");
		}
	}
}
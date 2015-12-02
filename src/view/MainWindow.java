package view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;

import twitter4j.Status;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public MainWindow(List<Status> tweets) {
		
		setLayout(new GridLayout(0, 1));
		
		for(Status tweet : tweets) {
			add( new TweetComponent(tweet) );
		}
	}	
}

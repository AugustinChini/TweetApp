package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Tweet;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow(List<Tweet> tweets) {
		setTitle( "TweepApp" );
		setPreferredSize( new Dimension(500, 400) );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel container = new JPanel();
		container.setLayout( new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground( Color.LIGHT_GRAY );

		for(Tweet tweet : tweets) {
			container.add( new TweetComponent(tweet) );
		}
		
		JScrollPane scrollPane = new JScrollPane(container);
		add(scrollPane);
	}
}

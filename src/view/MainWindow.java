package view;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import twitterManager.TwitterManager;

import model.Tweet;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	
	JPanel tweetContainer;

	public MainWindow(TwitterManager tm) {
		
		// title
		setTitle( "TweepApp" );
		
		// when we close the main Window what's operation is done
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// size on the window
		setPreferredSize( new Dimension(1450, 720) );
		
		// ----- Add jMenuBar ----- //
    	JMenuBar menuBar = new JMenuBar();
    	
    	JMenu menu1 = new JMenu("Option");
		
		JMenuItem refresh = new JMenuItem("Refresh   ( F5 )");
		menu1.add(refresh);
		
		menu1.addSeparator();
 
		JMenuItem quitter = new JMenuItem("Exit         ( Esc )");
		menu1.add(quitter);
		menuBar.add(menu1);
		
		this.setJMenuBar(menuBar);
		// ----- End Add jMenuBar ----- //
		
		
		// ----- Add Form tweet search ----- //
		JPanel formPane = new JPanel(new GridLayout(1,3) );
		
		
		// Label
		JLabel l = new JLabel("HashTag", JLabel.CENTER);
		
		formPane.add(l);
		
		// Text field
		textField = new JTextField();
		textField.setPreferredSize( new Dimension( 200,30 ) );
		
		formPane.add(textField);
		
		// Button search
		JButton searchBt = new JButton("Search");
		searchBt.addActionListener(new EventHandler(tm, this));
		formPane.add(searchBt);
		// ----- End Add Form tweet search ----- //
		
		
		// ----- tweet container ----- //
		tweetContainer = new JPanel();
		tweetContainer.setLayout( new BoxLayout(tweetContainer, BoxLayout.Y_AXIS));
		tweetContainer.setBackground( Color.LIGHT_GRAY );
		
		// scroll pane which include tweets
		JScrollPane scrollPane = new JScrollPane(tweetContainer);
		add(scrollPane);
		
		// add form in the north borderLayout
		getContentPane().add(formPane, BorderLayout.NORTH);
	}
	
	public String getTextFieldValue()
	{
		return textField.getText();
	}
	
	public void fillTweetContainer(List<Tweet> tweets)
	{
		// Fill container with tweets
		for(Tweet tweet : tweets) {
			tweetContainer.removeAll();
			tweetContainer.add( new TweetComponent(tweet) );
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
}



class EventHandler implements ActionListener
{

	private String keyWord;
	
	TwitterManager tm;
	
	MainWindow mw;
	
	public EventHandler(TwitterManager tm, MainWindow mw)
	{
		this.tm = tm;
		this.mw = mw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		keyWord = mw.getTextFieldValue();
		
		System.out.println("KeyWord is : "+keyWord);
		tm.searchTweets(keyWord);
		mw.fillTweetContainer(tm.getTweetsConverted());
		
	}
	
}

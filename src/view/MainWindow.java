package view;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import twitterManager.TwitterManager;

import model.Tweet;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textFieldDate;
	
	JPanel tweetContainer;

	public MainWindow(TwitterManager tm) {
		
		// title
		setTitle( "TweepApp" );
		
		// when we close the main Window what's operation is done
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// size on the window
		setPreferredSize( new Dimension(1450, 720) );
		
		
		// ----- Add Form tweet search ----- //
		JPanel formPane = new JPanel(new GridLayout(1,4) );
		
		
		// Label
		JLabel l = new JLabel("HashTag", JLabel.CENTER);
		
		formPane.add(l);
		
		// Text field
		textField = new JTextField("starwars");
		textField.setPreferredSize( new Dimension( 100,30 ) );
		
		formPane.add(textField);
		
		// Text field date
		Calendar beginDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		textFieldDate = new JTextField(sdf.format(beginDate.getTime()));
		textFieldDate.setPreferredSize( new Dimension( 100,30 ) );
		
		formPane.add(textFieldDate);
		
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
	
	public String getDateTextFieldValue()
	{
		return textFieldDate.getText();
	}
	
	public void fillTweetContainer(List<Tweet> tweets)
	{
		tweetContainer.removeAll();
		// Fill container with tweets
		for(Tweet tweet : tweets) {
			tweetContainer.add( new TweetComponent(tweet) );
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
}



class EventHandler implements ActionListener
{

	private String keyWord;
	
	private String date;
	
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
		date = mw.getDateTextFieldValue();
		
		System.out.println("KeyWord is : "+keyWord);
		tm.searchTweets(keyWord, date);
		mw.fillTweetContainer(tm.getTweetsConverted());
		
	}
	
}

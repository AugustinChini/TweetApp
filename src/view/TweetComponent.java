package view;

import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.Status;

public class TweetComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ImageIcon image;
	private JLabel date;
	private JLabel text;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public TweetComponent(Status tweet) {
		super();
		
		setLayout(new FlowLayout());
		
		date = new JLabel( sdf.format(tweet.getCreatedAt()) );
		add(date);
		
		text = new JLabel( tweet.getText() );
		add(text);
		
		try {
			image = new ImageIcon( new URL(tweet.getUser().getMiniProfileImageURL()) );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		add(new JLabel(image));
		
	}
	
	public void setTweet(Status tweet) {
		date.setText( sdf.format(tweet.getCreatedAt()) );
		text.setText( tweet.getText() );
		try {
			image.setImage( (new ImageIcon( new URL(tweet.getUser().getMiniProfileImageURL()) ) ).getImage() );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}

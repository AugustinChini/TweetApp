package app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Media;
import model.Tweet;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitterManager.TwitterManager;
import view.MainWindow;

public class Main {

	public static void main(String[] args) {
		try {
			TwitterManager searcher = new TwitterManager();
			
			/*List<Status> tweets = searcher.getTweets("chat");
			
			for(Status tweet : tweets)
			{
				System.out.println(tweet.getCreatedAt() + " : " + tweet.getText());
			}*/
			
			System.out.println("\n===============================================================\n");
			
			Calendar beginDate = Calendar.getInstance();
			beginDate.add(Calendar.DATE, -2);
			
			//définir le format de la date
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        
	        System.out.println( sdf.format(beginDate.getTime()) );
	        
			//tweets = searcher.getTweets("starwars", sdf.format(beginDate.getTime()));
	        List<Status> tweets = searcher.getTweets("starwars");
			List<Tweet> tweetsConverted = new ArrayList<Tweet>();
	        
	        for(Status status : tweets)
	        {
	        	Tweet t = new Tweet();
	        	t.createdAt = status.getCreatedAt();
	        	t.profilUrl = status.getUser().getProfileImageURL();
	        	t.userName = status.getUser().getName();
	        	t.userScreenName = status.getUser().getScreenName();
	        	t.text = status.getText();
	        	
	        	for(MediaEntity media : status.getMediaEntities()) {
	        		Media m = new Media();
	        		m.type = media.getType();
	        		m.url = media.getMediaURL();
	        		
	        		t.medias.add(m);
				}
	        	
	        	tweetsConverted.add(t);
	        }
	        
			MainWindow window = new MainWindow(tweetsConverted);
			window.pack();
			window.setVisible(true);
			
			/*for(Status tweet : tweets)
			{				
				for(URLEntity urlEntity : tweet.getURLEntities()) {
					System.out.println("Entity url : " + urlEntity.getExpandedURL());
					System.out.println(urlEntity.getDisplayURL());
				}
				
				for(MediaEntity media : tweet.getMediaEntities()) {
					System.out.println("Media : " + media.getType() + " " + media.getMediaURL());
				}
				
				for(HashtagEntity hastag : tweet.getHashtagEntities() ) {
					System.out.println("Hastag : " + hastag.getText());
				}
				
				System.out.println(tweet.getQuotedStatus());
				
				System.out.println(tweet.getCreatedAt() + " : " + tweet.getText());
				System.out.println(tweet.getUser().getName() + " : " + tweet.getUser().getScreenName());
				
				System.out.println();
			}*/
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}

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
			
			MainWindow window = new MainWindow(searcher);
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

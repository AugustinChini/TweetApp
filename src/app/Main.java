package app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitterManager.TwitterManager;
import view.MainWindow;

public class Main {

	public static void main(String[] args) {
		try {
			TwitterManager searcher = new TwitterManager();
			
			List<Status> tweets = searcher.getTweets("chat");
			
			for(Status tweet : tweets)
			{
				System.out.println(tweet.getCreatedAt() + " : " + tweet.getText());
			}
			
			System.out.println("\n===============================================================\n");
			
			Calendar beginDate = Calendar.getInstance();
			beginDate.add(Calendar.DATE, -2);
			
			//définir le format de la date
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        
	        System.out.println( sdf.format(beginDate.getTime()) );
	        
			tweets = searcher.getTweets("chat", sdf.format(beginDate.getTime()));
			
			MainWindow window = new MainWindow(tweets);
			window.pack();
			window.setVisible(true);
			
			for(Status tweet : tweets)
			{
				System.out.println(tweet.getCreatedAt() + " : " + tweet.getText());
			}
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}

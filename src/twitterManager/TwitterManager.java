package twitterManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Media;
import model.Tweet;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterManager {
	protected final static String CONSUMER_KEY = "hP49CWN0KuutGM6PCn7pfVk7P";
	protected final static String CONSUMER_SECRET = "vQXz8BZ1tKBiSx1YTRwlkzT0Tfqf72hTdTSHv59hw5T1WFc0V8";
	private Twitter twitter;
	List<Tweet> tweetsConverted;

	public TwitterManager() throws TwitterException {
		OAuth2Token token;
		token = getOAuth2Token();
		
		if (token != null) {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setApplicationOnlyAuthEnabled(true);
			cb.setOAuthConsumerKey(CONSUMER_KEY);
			cb.setOAuthConsumerSecret(CONSUMER_SECRET);
			cb.setOAuth2TokenType(token.getTokenType());
			cb.setOAuth2AccessToken(token.getAccessToken());
			twitter = new TwitterFactory(cb.build()).getInstance();
		} else {
			throw new TwitterException("Could not create an authentification token");
		}
	}

	public List<Status> getTweets(String tag) throws TwitterException {
		Query query = new Query(tag);
		
		QueryResult result = twitter.search(query);

		return result.getTweets();
	}
	
	public List<Status> getTweets(String tag, String untilDate) throws TwitterException {
		Query query = new Query(tag);
		query.setUntil(untilDate);
		
		QueryResult result = twitter.search(query);

		return result.getTweets();
	}

	protected static OAuth2Token getOAuth2Token() {
		OAuth2Token token = null;
		ConfigurationBuilder cb;
		cb = new ConfigurationBuilder();
		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(CONSUMER_KEY);
		cb.setOAuthConsumerSecret(CONSUMER_SECRET);
		
		try {
			token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}
	
	
	public void searchTweets(String keyWord) {
		
		System.out.println("\n===============================================================\n");
		
		Calendar beginDate = Calendar.getInstance();
		beginDate.add(Calendar.DATE, -2);
		
		//definir le format de la date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        System.out.println( sdf.format(beginDate.getTime()) );
        
		//tweets = searcher.getTweets("starwars", sdf.format(beginDate.getTime()));
        List<Status> tweets =null;
		try {
			tweets = getTweets(keyWord);
		} catch (TwitterException e1) {
			e1.printStackTrace();
			System.out.println("Could not find tweets ...");
			System.exit(-1);
		}
		
		tweetsConverted = new ArrayList<Tweet>();
        
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
        
        
		
	}

	public List<Tweet> getTweetsConverted() {
		return tweetsConverted;
	}

}

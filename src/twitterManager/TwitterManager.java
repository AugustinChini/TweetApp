package twitterManager;

import java.util.List;

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

}

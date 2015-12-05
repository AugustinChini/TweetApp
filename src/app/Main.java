package app;

import twitter4j.TwitterException;
import twitterManager.TwitterManager;
import view.MainWindow;

public class Main {

	public static void main(String[] args) {
		try {
			TwitterManager searcher = new TwitterManager();
			
			MainWindow window = new MainWindow(searcher);
			window.pack();
			window.setVisible(true);
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}

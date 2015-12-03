package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet {
	public String userName;
	public String userScreenName;
	public String profilUrl;
	public String text;
	public Date createdAt;
	public List<Media> medias;
	
	public Tweet() {
		medias = new ArrayList<Media>();
	}
	
}

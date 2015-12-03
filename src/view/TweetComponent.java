package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Media;
import model.Tweet;

public class TweetComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ImageIcon profilImage;
	private JLabel date;
	private JLabel text;
	private List<JLabel> medias;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public TweetComponent(Tweet tweet) {
		super();
		
		medias = new ArrayList<JLabel>();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// User info pane
		JPanel userPane = new JPanel();
		userPane.setLayout( new FlowLayout(FlowLayout.LEFT) );
		add(userPane);
		
		date = new JLabel( sdf.format( tweet.createdAt ) );
		userPane.add(date);
		
		try {
			profilImage = new ImageIcon( new URL( tweet.profilUrl ) );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		userPane.add(new JLabel(profilImage));
		
		JPanel nameInfoPane = new JPanel();
		nameInfoPane.setLayout( new BoxLayout(nameInfoPane, BoxLayout.Y_AXIS) );
		userPane.add(nameInfoPane);
		
		nameInfoPane.add( new JLabel(tweet.userScreenName) );
		nameInfoPane.add( new JLabel(tweet.userName) );
		
		// Tweet text and image
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(container);
		
		for(Media media : tweet.medias ) {
			System.out.println(media.url);
			try {
				ImageIcon image = new ImageIcon( new URL( media.url) );
				JLabel label = new JLabel(image);
				label.setAlignmentX( Component.LEFT_ALIGNMENT );
				medias.add(label);
				container.add(label);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		text = new JLabel( tweet.text );
		text.setAlignmentX( Component.LEFT_ALIGNMENT );
		container.add(text);	
	}
}

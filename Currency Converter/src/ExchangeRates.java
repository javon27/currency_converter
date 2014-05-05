
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin3
 */
public class ExchangeRates {
    static String BASEURL = "http://themoneyconverter.com/rss-feed/";
    private String FromCurrency;
    private String ToCurrency;
    
    public ExchangeRates() {
        FromCurrency = "USD";
        SyndFeed feed = null;
        try {
            URL feedUrl = new URL(BASEURL+FromCurrency+"/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));

            System.out.println(feed);

        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
               String title = entry.getTitle();
               String descr = entry.getDescription().getValue();
               
               System.out.println("Title: "+title+"\nDescription: "+descr);
           }
        } else {
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
        }
    }
    
    public void update(String FromUnits) {
        
    }
    
    public String getUnits() {
        String temp = "";
        
        return temp;
    }
    
    public double getRate(String ToUnits) {
        double rate = 0;
        
        return rate;
    }
}


import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
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
    private Date rssDate;
    private HashMap rateMap;
    
    public ExchangeRates() {
        rateMap = new HashMap();
        this.update("USD");
    }
    
    public void update(String FromUnits) {
        SyndFeed feed = null;
        FromCurrency = FromUnits;
        try {
            URL feedUrl = new URL(BASEURL+FromCurrency+"/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));

            //System.out.println(feed);

        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
               String title = entry.getTitle();
               String descr = entry.getDescription().getValue();
               
               //System.out.println("Title: "+title+"\nDescription: "+descr);
               double rate;
               int st = descr.indexOf("=") + 1;
               int en = descr.indexOf(".", st) + 6;
               rate = Double.parseDouble(descr.substring(st, en).replace(",", ""));
               rateMap.put(title, rate);
           }
        } else {
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
        }
    }
    
    public String getUnits() {
        return FromCurrency;
    }
    
    public double getRate(String ToUnits) {
        String conversion = ToUnits+"/"+FromCurrency;
        return (Double) rateMap.get(conversion);
    }
    
    public void printHashMap() {
        System.out.println(rateMap);
    }
}

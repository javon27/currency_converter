
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    private HashMap rateMap;
    
    public ExchangeRates() {
        FromCurrency = "NaN";
        ToCurrency = "NaN";
        
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
            //custom title, error icon
            JOptionPane.showMessageDialog(null,
                    "This app needs a valid connection to the internet.\n"
                    + "This app will now close.",
                    "Check Internet",
                    JOptionPane.ERROR_MESSAGE);
            exit(1);
        }

        if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
               String title = entry.getTitle();
               String descr = entry.getDescription().getValue();
               
               System.out.println("Title: "+title+"\nDescription: "+descr);
               double rate;
               int st = descr.indexOf("=") + 1;
               int en = descr.indexOf(".", st) + 6;
               rate = Double.parseDouble(descr.substring(st, en).replace(",", ""));
               rateMap.put(title, rate);
           }
        } else {
            rateMap.put("NaN", new Double(-1));
        }
    }
    
    public String getUnits() {
        return FromCurrency;
    }
    
    public double getRate(String ToUnits) {
        String conversion = ToUnits+"/"+FromCurrency;
        double rate;
        try {
            rate = (Double) rateMap.get(conversion);
        } catch (Exception e) {
            rate = (Double) rateMap.get("NaN");
        }
        
        return rate;
    }
    
    public void printHashMap() {
        System.out.println(rateMap);
    }
}

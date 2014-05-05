/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static sun.security.x509.OIDMap.getClass;
/**
 *
 * @author admin3
 */
public class JsonReader {
    
    //private ArrayList<Currency> currencies;
    private ArrayList<String> unitsList;
    private ArrayList<String> currencyList;
    
    public JsonReader(String filename) {
        JSONParser parser = new JSONParser();
        unitsList = new ArrayList<String>();
        currencyList = new ArrayList<String>();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
 
            JSONArray jUnits = (JSONArray) jsonObject.get("units");
            Iterator<JSONObject> iterator = jUnits.iterator();
            while (iterator.hasNext()) {
                JSONObject temp = iterator.next();
                String country = (String) temp.get("CountryCurrency");
                String unit = (String) temp.get("Units");
                //Currency item = new Currency(unit, country);
                //units.add(item);
                unitsList.add(unit);
                currencyList.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String[] getUnits() {
        String temp[] = new String[unitsList.size()];
        temp = unitsList.toArray(temp);
        return temp;
    }
    
    public String[] getCountries() {
        String temp[] = new String[currencyList.size()];
        temp = currencyList.toArray(temp);
        return temp;
    }
    
}

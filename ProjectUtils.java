import java.util.regex.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.*;
import java.io.*;
import java.lang.*;

class ProjectUtils {
    public static void main(String[] args) {
        System.out.println(countryCodes);
        //System.out.println("1000 is " + currConvert("EUR", "USD", 1000));
    }
    public static ArrayList<String> countryCodes;

    /* intialize countryCodes */

    static {
        String[] locales = Locale.getISOCountries();
        countryCodes = new ArrayList<String>();
        for (String s: locales) {
            Locale l = new Locale("en", s);
            countryCodes.add(l.getISO3Country());
        }
    }

    /**
     * 
     * @param from ISO country code representing country of currency expressed in amount.
     * @param to ISO country code representing country of target currency
     * @param amount currency amount to be converted
     * 
     * @return value of amount, converted to currency of "to" country
     *
     * Highly brittle, quick-n-dirty function that uses an online currency
     * conversion API and just looks for a decimal number in the returned
     * JSON string. See https://exchangeratesapi.io/ for more info.
     *
     **/

     //Will take 2, 2 letter country codes, then an int representing the amount
    public static Double currConvert(String from, String to, double amount) {
        try {
            URL url = new 
                URL("https://api.exchangeratesapi.io/latest" + "?base=" + 
                        from + "&symbols=" + to);
            BufferedReader reader = new 
                BufferedReader(new InputStreamReader(url.openStream()));
            String jsonString = reader.readLine();
            //System.out.println("result " + jsonString);
            if (jsonString.length() > 0) {
                Pattern pattern = 
                    Pattern.compile("\\d+\\.\\d*");
                Matcher matcher = pattern.matcher(jsonString);
                if (matcher.find()) {
                    return Double.parseDouble(matcher.group()) * amount;
                }
                else return null;
            } 
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
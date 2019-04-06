import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.text.*;
import javax.swing.*;

public class RealEstateSale extends ProjectUtils {

    public String country; // contains a two letter country code representing where the sale took place
    public double price; // represents the amount of the sale(in the currency of the country)
    public Date dateOfSale; // represents the day when the sale was completed

    public RealEstateSale(String country, double price, Date dateOfSale) {
        this.country = country;
        this.price = price;
        this.dateOfSale = dateOfSale;

        countryTester(country);
        priceTester(price);
    }

    //Converts country code to currency code which is used by currConvert
    public String getCurrencyCode(String country) {
        String currencyCode = Currency.getInstance(new Locale("", country)).getCurrencyCode();
        return currencyCode;
    }

    //Changes double to two decimal spots
    public double beautifyDouble(double price) {
        DecimalFormat changeFormat = new DecimalFormat("#0.00");
        return (Double.parseDouble(changeFormat.format(price)));
    }

    // Tests to see if country is valid
    public String countryTester(String country) {

        Locale a = new Locale("en", country);
        String convertedCountry = a.getISO3Country();
        int validCountry = 0;

        for (int i = 0; i < ProjectUtils.countryCodes.size(); i++) {
            if (convertedCountry.equals(ProjectUtils.countryCodes.get(i))) {
                validCountry++;
            } else if (convertedCountry != ProjectUtils.countryCodes.get(i)) {
            }
        }

        if (validCountry == 0) {
            System.out.println("Not a valid country");
            System.exit(0);
        }

        return country;
    }

    // Tests to see if price is valid -- must be positive/non-zero
    public double priceTester(double price) {

        if (price <= 0) {
            System.out.println("INVALID PRICE, MUST BE POSITIVE.");
            System.exit(0);
        }
        return price;
    }

    // Getters
    public String getCountry() {
        return country;
    }

    public double getPrice() {
        double convertedAmount = currConvert("USD", getCurrencyCode(country), price);
        return beautifyDouble(convertedAmount);
    }

    public Date getDate() {
        return dateOfSale;
    }
}
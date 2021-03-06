package edu.csus.csc131.euc;

import java.util.HashMap;
import java.util.Map;

public class Rates {
    private static final int HOURS_IN_DAY = 24;
    private Map<Integer, Double> rates;

    public Rates() {
        rates = initMap();
    }

    public Rates(double[] in){
        rates = initMap();
        setRates(in);
    }

    public double getRate(int i) {
        return rates.get(i);
    }


    //Copy values from double array into usage map
    public void setRates(double[] in) {
        for(int i = 0; i < in.length; i++){
            if(in[i] < 0){
                System.out.println("Error: rate at hour \"" + i + "\" cannot be below zero.");
                this.rates = initMap();
                System.out.println("Rates have been reset to 0 for all hours.");
                return;
            }
            this.rates.replace(i, in[i]);
        }
    }

    //Place one value r at the given key i
    public void setRates(int i, double r) {
        if(r < 0){
            System.out.println("Error: rate entered at hour \"" + i + "\" cannot be below zero.");
            System.out.println("Rate change not accepted.");
            return;
        }
        else if(i < 0 || i > HOURS_IN_DAY - 1){
            System.out.println("Error: hour cannot be less than 0 or greater than 23.");
            return;
        }
        rates.replace(i, r);
    }

    @Override
    public String toString() {
        return "Rates{" +
                "rates=" + rates +
                '}';
    }

    private static Map<Integer, Double> initMap() {
        Map<Integer, Double> m = new HashMap<>();
        for (int i = 0; i < HOURS_IN_DAY; ++i) {
            m.put(i, 0.0);
        }
        return m;
    }
}

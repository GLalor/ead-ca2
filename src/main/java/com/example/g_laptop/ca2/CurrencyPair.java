// X00122026
// Graham Lalor

package com.example.g_laptop.ca2;

public class CurrencyPair {

    private String code;
    public String GetCode(){return code;}

    private double rate;

    private String currnecySymbol;
    public String GetCurrencySymbol(){ return currnecySymbol;}


    public CurrencyPair(String code, double rate, String symbol) {
        this.code = code;
        this.rate = rate;
        this.currnecySymbol = symbol;
    }

    public double convert(double amount){
        double result = 00.0;
        result = amount * rate;
        return result;
    }
}

package com.org.ait.calc.util;

/**
 * Created by Shiva on 28-10-2017.
 */

public class Parser {
    public static double getDouble(String val){
        try{
            return Double.parseDouble(val);
        }catch (Exception e){
            Logger.onErr(e);
            return -1;
        }
    }
}

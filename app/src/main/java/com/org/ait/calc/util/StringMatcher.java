package com.org.ait.calc.util;

import java.util.regex.Pattern;

/**
 * Created by Shiva on 29-10-2017.
 */

public class StringMatcher {
    public static String symbol;
    public static Pattern pattern;
    public final static String NUMBERS;
    static {
        NUMBERS = "[0123456789]";
        symbol = "[/*\\-+]";
        pattern =  Pattern.compile(symbol);
    }
    public static boolean isMatch(String searchVal){
        return pattern.matcher(searchVal).find();
    }
    public static int getCharCountInString(String str,String countChar){
       return str.length() - str.replace(countChar, "").length();
    }

}

package com.org.ait.calc.util;

import java.text.DecimalFormat;

/**
 * Created by Shiva on 07-11-2017.
 */

public class Formatter {
    private static DecimalFormat df;
    static {
         df = new DecimalFormat("#.##");
    }
    public static String getFormatVal(double d){
        return df.format(d);
    }
}

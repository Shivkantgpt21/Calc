package com.org.ait.calc.util;

/**
 * Created by Shiva on 28-10-2017.
 */

public class Logger {
    public static void onErr(Exception e){
        e.printStackTrace();
    }
    public static void onLog(String msg){
        onLog("msg",msg);
    }
    public static void onLog(String tag,String msg){
        System.out.println(tag+":"+msg);
    }
}

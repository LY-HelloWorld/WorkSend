package com.a51zhipaiwang.worksend.Utils;

import android.util.Log;

/**
 * Created by 罗怡 on 2017/2/13.
 * 自定义Log 可以设置LEVEL后让所有的log不再打印，实现一键控制
 */
public class MyLog {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;
    private static final int LEVEL = VERBOSE;

    public static void v(String tag , String msg){
        if (LEVEL <= VERBOSE){
            Log.v(tag , msg);
        }
    }
    public static void d(String tag , String msg){
        if (LEVEL <= DEBUG){
            Log.d(tag , msg);
        }
    }

    public static void i(String tag , String msg){
        if (LEVEL <= INFO){
            Log.i(tag , msg);
        }
    }

    public static void w(String tag , String msg){
        if (LEVEL <= WARN){
            Log.w(tag , msg);
        }
    }

    public static void e(String tag , String msg){
        if (LEVEL <= ERROR){
            Log.e(tag , msg);
        }
    }

}

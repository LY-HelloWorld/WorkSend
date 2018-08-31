package com.a51zhipaiwang.worksend.Utils.GetTime;

public class GetTime {
    public static String getTime(){

        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

        String  str=String.format("%010d", time);

        return str;

    }
}

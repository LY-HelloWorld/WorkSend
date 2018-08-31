package com.a51zhipaiwang.worksend.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 罗怡 on 2017/8/12.
 * 通过sharedPreferences来进行数据存储
 */

public class SharedPreferencesUtil {


    /**
     * 读取数据
     * @param key 相应数据存储的key值
     * @param info 数据存储的文件名
     * @param default_string  如果没有相应数据 就返回的默认值
     * @param context 上下文
     * @return
     */
    public static String readSharedPreference(String key, String info, String default_string, Context context){

        SharedPreferences sharedPreferences= context.getSharedPreferences(info,
                context.MODE_PRIVATE);
        return sharedPreferences.getString(key, default_string);

    }


    /**
     * 存储数据
     * @param key 存储数据时的key值
     * @param value 存储数据值
     * @param info 存储数据的文件名
     * @param context 上下文
     */
    public static void saveSharedPreference(String key, String value, String info, Context context){

        SharedPreferences mySharedPreferences= context.getSharedPreferences(info,
                context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();


    }



}

package com.a51zhipaiwang.worksend.Utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetJson{


    public static String GetCityJson(Context context, String fileName){
        StringBuilder stringBuilder=new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager=context.getAssets();
        //使用IO流读取json文件内容
        try{
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName),"GBK"));
            String line;
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
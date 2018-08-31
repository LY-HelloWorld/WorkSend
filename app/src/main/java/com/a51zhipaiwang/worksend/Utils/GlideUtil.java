package com.a51zhipaiwang.worksend.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.bumptech.glide.Glide;


/**
 * Created by 罗怡 on 2017/8/29.
 */

public class GlideUtil {

    public void GlideImage(String path, Context context, ImageView imageView){

        try {
            Glide.with(context).load(path).into(imageView);
        }catch (Exception e){
            MyLog.e("GlideUtil", "GlideImage(GlideUtil.java:20)" + e.getMessage());
        }

    }


    public void GlideImage(int id, Context context, ImageView imageView){

        try {
            Glide.with(context).load(id).into(imageView);
        }catch (Exception e){
            MyLog.e("Exception", e.getMessage());
        }

    }

    public void GlideImage(String path, Context context, ImageView imageView, int placeHolder){
        try {
            Glide.with(context).load(path).placeholder(placeHolder).into(imageView);
        }catch (Exception e){
            MyLog.e("Exception", e.getMessage());
        }
    }


}

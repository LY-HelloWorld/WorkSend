package com.a51zhipaiwang.worksend.Utils;

import android.content.Context;
import android.widget.Toast;

import com.a51zhipaiwang.worksend.Application.MyApplication;

/**
 * Created by 罗怡 on 2017/5/3.
 * 防止多次点击后Toast多次触发
 */
public class ToastUtil {
    private static Toast toast;
    public static void showToast(Context context, String content){
        if (toast == null){
            toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
            //toast.setGravity(Gravity.CENTER, 0, 0);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showToastTwo(String content){
        if (toast == null){
            toast = Toast.makeText(MyApplication.context, content, Toast.LENGTH_LONG);
            //toast.setGravity(Gravity.CENTER, 0, 0);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

}

package com.a51zhipaiwang.worksend.Utils.ReleaseResourceUtil;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.a51zhipaiwang.worksend.Utils.MyLog;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/14
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class ReleaseResourceUtil {

    public static void ReleaseImage(ImageView... images){
        for (int i = 0; i < images.length; i++) {
            if (images[i] == null){
                break;
            }else {
                Drawable drawable = images[i].getDrawable();
                if (drawable != null && drawable instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                        MyLog.e("ReleaseResourceUtil", "ReleaseImage(ReleaseResourceUtil.java:37)" + "recycle");
                    }
                }
            }
        }
    }


}

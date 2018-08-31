package com.a51zhipaiwang.worksend.CommonActivity.WebViewJsInterface;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.a51zhipaiwang.worksend.Enterprise.Activity.WebViewActivity.WebViewActivity;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.WXShare;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/29
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
public class CommonGetFromH5 {

    Activity activity;

    public CommonGetFromH5(Activity activity) {
        this.activity = activity;
    }

    /**
     * h5 调用分享接口
     * @param path
     */
    @JavascriptInterface
    public void fenXiangFromH5(String path){
        //ToastUtil.showToastTwo(path);
        WXShare wxShare = new WXShare(activity);
        wxShare.shareUrl(0, activity, path, "职派分享", "面试就有钱");
        MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + path);
    }


    @JavascriptInterface
    public void finishFromH5(){
        ActivityCollector.removeActivity(activity);
    }


    @JavascriptInterface
    public void toast2(){
        ToastUtil.showToastTwo("您还没有完善简历，不能申请该职位！");
    }
    @JavascriptInterface
    public void tip(String info){
        ToastUtil.showToastTwo(info);
    }
}

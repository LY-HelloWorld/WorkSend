package com.a51zhipaiwang.worksend.Personal.worktypeactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WebViewActivity.WebViewActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.WXShare;

public class WorkTypeActivity extends BaseActivity {

    public static final int FULLWORK = 1;
    public static final int PARTTIMEWORK = 2;

    private int type;

    private WebView web_work_type;
    private TextView tilte_text;
    private ImageView return_image;

    public static void startWorkTypeActivity(Context context, int type){
        Intent intent = new Intent(context, WorkTypeActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_type);
        init();
        setRegister();
        initWebView();
    }


    @Override
    public void onPause() {
        super.onPause();
        web_work_type.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        web_work_type.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_work_type.destroy();
    }

    private void init(){
        type = getIntent().getIntExtra("type", 0);
        web_work_type = (WebView) findViewById(R.id.web_work_type);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        if (type == 1){
            tilte_text.setText("全职");
        }
        if (type == 2){
            tilte_text.setText("兼职");
        }

    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //web_work_type.loadUrl("javascript:gojobFull()");
                //WorkTypeActivity.this.finish();
            }
        });
    }


    private void initWebView(){
        WebSettings webSettings = web_work_type.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        web_work_type.addJavascriptInterface(new GetFromH5(), "fenXiangFromH5");
        web_work_type.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //页面开始加载前
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:getUserId(\"" + MyApplication.tokenPersonal + "\")");
                //页面加载完毕
                //从 Android 传递参数到 H5 界面
                //view.loadUrl("javascript:getFromAndroid(\"" + list + "\")");
            }
        });
        //MyLog.e("WebViewActivity", "initWebView(WebViewActivity.java:96)" + MyApplication.h5Path + "/resumeDetail2?lists=" + lists);
        web_work_type.loadUrl(MyApplication.h5Path + "/JobFulltime?code=" + type + "&longitude=" + MyApplication.longitude + "" + "&latitude=" + MyApplication.latitude);
        /*if (type == 1){
            web_work_type.loadUrl(MyApplication.h5Path + "/JobFulltime?code=" + type + "&longitude=116.476133" + "" + "&latitude=40.020222");
        }
        if (type == 2){
            web_work_type.loadUrl(MyApplication.h5Path + "/jobPartTime?code=" + type + "&longitude=116.476133" + "" + "&latitude=40.020222");
        }*/

    }

    public class GetFromH5{

        /**
         * h5 调用分享接口
         * @param path
         */
        @JavascriptInterface
        public void fenXiangFromH5(String path){
            //ToastUtil.showToastTwo(path);
            WXShare wxShare = new WXShare(WorkTypeActivity.this);
            wxShare.shareUrl(0, WorkTypeActivity.this, path, "职派分享", "面试就有钱");
            MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + path);
        }

        @JavascriptInterface
        public void returnUnVisible(){
            return_image.setVisibility(View.INVISIBLE);
            MyLog.e("GetFromH5", "returnUnVisible(GetFromH5.java:145)" + "returnUnVisible");
        }

        @JavascriptInterface
        public void returnVisible(){
            return_image.setVisibility(View.VISIBLE);
            MyLog.e("GetFromH5", "returnVisible(GetFromH5.java:151)" + "returnVisible");
        }

        @JavascriptInterface
        public void toast(){
            ToastUtil.showToastTwo("您已申请过该职位");
        }

        @JavascriptInterface
        public void finishFromH5(){
            ActivityCollector.removeActivity(WorkTypeActivity.this);
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


}

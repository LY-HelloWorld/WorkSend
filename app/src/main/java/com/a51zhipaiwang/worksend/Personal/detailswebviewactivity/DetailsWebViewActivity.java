package com.a51zhipaiwang.worksend.Personal.detailswebviewactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.worktypeactivity.WorkTypeActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.WXShare;

import java.util.ArrayList;

public class DetailsWebViewActivity extends BaseActivity {

    private ArrayList<String> ids;
    private StringBuffer sIds;
    private WebView web_details;


    public static void startDetailsWebViewActivity(Context context, ArrayList<String> ids){
        Intent intent = new Intent(context, DetailsWebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ids", ids);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_web_view);
        init();
        initIds();
        initWebView();
        setRegister();
    }


    @Override
    public void onPause() {
        super.onPause();
        web_details.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        web_details.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_details.destroy();
    }

    private void init(){
        ids = getIntent().getExtras().getStringArrayList("ids");
        sIds = new StringBuffer();
        web_details = (WebView) findViewById(R.id.web_details);
    }


    private void initIds(){
        for (int i = 0; i < ids.size(); i++) {
            if (i != ids.size() - 1){
                sIds.append(ids.get(i) + ",");
            }else {
                sIds.append(ids.get(i));
            }

        }
        MyLog.e("DetailsWebViewActivity", "initIds(DetailsWebViewActivity.java:65)" + sIds.toString());
    }

    private void setRegister(){

    }



    private void initWebView(){
        WebSettings webSettings = web_details.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        web_details.addJavascriptInterface(new GetFromH5(), "fenXiangFromH5");
        web_details.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //页面开始加载前
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:getUserId(\"" + MyApplication.tokenPersonal + "\")");
            }
        });
        web_details.loadUrl(MyApplication.h5Path + "/jobDetail?idas=" + ids.toString().replace("[", "").replace("]", "")
                + "&longitude=" + MyApplication.longitude
                + "&latitude=" + MyApplication.latitude);
        MyLog.e("DetailsWebViewActivity", "initWebView(DetailsWebViewActivity.java:99)" + MyApplication.h5Path + "/jobDetail2?idas=" + ids.toString().replace("[", "").replace("]", "")
                + "&longitude=" + 116.476133
                + "&latitude=" + 40.020222);
    }

    public class GetFromH5{

        /**
         * h5 调用分享接口
         * @param path
         */
        @JavascriptInterface
        public void fenXiangFromH5(String path){
            //ToastUtil.showToastTwo(path);
            WXShare wxShare = new WXShare(DetailsWebViewActivity.this);
            wxShare.shareUrl(0, DetailsWebViewActivity.this, path, "职派分享", "面试就有钱");
            MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + path);
        }

        /*@JavascriptInterface
        public void returnUnVisible(){
            return_image.setVisibility(View.INVISIBLE);
            MyLog.e("GetFromH5", "returnUnVisible(GetFromH5.java:145)" + "returnUnVisible");
        }

        @JavascriptInterface
        public void returnVisible(){
            return_image.setVisibility(View.VISIBLE);
            MyLog.e("GetFromH5", "returnVisible(GetFromH5.java:151)" + "returnVisible");
        }
*/
        @JavascriptInterface
        public void toast(){
            ToastUtil.showToastTwo("您已申请过该职位");
        }

        @JavascriptInterface
        public void finishFromH5(){
            ActivityCollector.removeActivity(DetailsWebViewActivity.this);
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

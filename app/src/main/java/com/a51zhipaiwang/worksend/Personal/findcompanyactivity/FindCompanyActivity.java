package com.a51zhipaiwang.worksend.Personal.findcompanyactivity;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.searchactivity.SearchActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FindCompanyActivity extends BaseActivity {


    private WebView web_findcompany;
    private TextView tilte_text;
    private ImageView return_image;

    public static void startFindCompanyActivity(Context context) {
        Intent intent = new Intent(context, FindCompanyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_company);
        init();
        initWebView();
        setRegister();
    }


    @Override
    public void onPause() {
        super.onPause();
        web_findcompany.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        web_findcompany.onResume();
    }

    private void init() {
        web_findcompany = (WebView) findViewById(R.id.web_findcompany);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("找公司");
        return_image = (ImageView) findViewById(R.id.return_image);
    }

    private void initWebView() {
        WebSettings webSettings = web_findcompany.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);
        //webSettings.setDefaultTextEncodingName("UTF-8");

        web_findcompany.addJavascriptInterface(new GetFromH5(), "fenXiangFromH5");
        web_findcompany.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //页面开始加载前
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完毕
                //从 Android 传递参数到 H5 界面
                //view.loadUrl("javascript:getFromAndroid(\"" + list + "\")");
                view.loadUrl("javascript:getUserId(\"" + MyApplication.tokenPersonal + "\")");
            }
        });
       /* web_findcompany.loadUrl(MyApplication.h5Path + "/searchResult?company=" + company + "&longitude=" + MyApplication.longitude
                + "&latitude=" + MyApplication.latitude);
        MyLog.e("FindCompanyActivity", "initWebView(FindCompanyActivity.java:97)" + MyApplication.h5Path + "/searchResult?company=" + company + "&longitude=" + MyApplication.longitude
                + "&latitude=" + MyApplication.latitude);*/
       web_findcompany.loadUrl(MyApplication.h5Path + "/lookCompany?" + "longitude=" + MyApplication.longitude + "&latitude=" + MyApplication.latitude);
    }

    private void setRegister() {
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindCompanyActivity.this.finish();
            }
        });
    }


    class GetFromH5{
        @JavascriptInterface
        public void startFindActivity(){
            SearchActivity.startSearchActivity(FindCompanyActivity.this, SearchActivity.COMPANY);
        }

        @JavascriptInterface
        public void finishFromH5(){
            ActivityCollector.removeActivity(FindCompanyActivity.this);
        }

        @JavascriptInterface
        public void toast(){
            ToastUtil.showToastTwo("您已申请过该职位");
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

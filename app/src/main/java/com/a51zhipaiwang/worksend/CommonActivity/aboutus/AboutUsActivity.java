package com.a51zhipaiwang.worksend.CommonActivity.aboutus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;

public class AboutUsActivity extends BaseActivity {

    private WebView web_about_us;
    private ImageView return_image;
    private TextView tilte_text;

    public static void startAboutUsActivity(Context context){
        Intent intent = new Intent(context, AboutUsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        init();
        setRegister();
        initWebView();
    }

    private void init(){
        web_about_us = (WebView) findViewById(R.id.web_about_us);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("关于我们");
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.return_image:
                        AboutUsActivity.this.finish();
                        break;
                }
            }
        });
    }
    private void initWebView(){
        WebSettings webSettings = web_about_us.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        web_about_us.setWebViewClient(new WebViewClient() {

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
            }
        });
        web_about_us.loadUrl("https://mp.weixin.qq.com/s/nqQR-Arp2VK1_MlGJyqA7A");
    }

}

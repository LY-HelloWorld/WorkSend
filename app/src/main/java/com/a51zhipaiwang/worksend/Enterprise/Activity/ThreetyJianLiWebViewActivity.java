package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.WebViewJsInterface.CommonGetFromH5;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

public class ThreetyJianLiWebViewActivity extends BaseActivity {

    private WebView webView;
    private String id;
    private TextView titleText;
    private ImageView return_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threety_jian_li_web_view);
        init();
        webViewSet();
        setRegister();
    }

    public static void StartThreeJianLiWebViewActivity(BaseActivity context, String id, String title){
        Intent intent = new Intent(context, ThreetyJianLiWebViewActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivityForResult(intent, MyApplication.ChoiceJianLi);
    }

    private void init(){
        webView = (WebView) findViewById(R.id.webView);
        id = getIntent().getStringExtra("id");
        titleText = (TextView) findViewById(R.id.tilte_text);
        titleText.setText(getIntent().getStringExtra("title"));
        return_image = (ImageView) findViewById(R.id.return_image);
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreetyJianLiWebViewActivity.this.finish();
            }
        });
        MyLog.e("ThreetyJianLiWebViewActivity", "init(ThreetyJianLiWebViewActivity.java:45)" + MyApplication.h5Path + "/resumeDetail3?id=" + id);
    }

    private void webViewSet(){

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        webView.addJavascriptInterface(new CommonGetFromH5(this), "fenXiangFromH5");
        webView.addJavascriptInterface(new GetFromH5(), "GetFromH5");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:getUserId(\"" + MyApplication.tokenEnterprise + "\")");
            }
        });
        webView.loadUrl(MyApplication.h5Path + "/resumeDetail3?id=" + id);
        MyLog.e("ThreetyJianLiWebViewActivity", "webViewSet(ThreetyJianLiWebViewActivity.java:59)" + MyApplication.h5Path + "/resumeDetail3?id=" + id);
    }

    private void setRegister(){

    }
    public class GetFromH5{

        /**
         * @param choice
         */
        @JavascriptInterface
        public void choiceFromH5(String choice){
            MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + id);
            Intent intent = new Intent();
            intent.putExtra("choice", choice);
            intent.putExtra("id", id);
            setResult(RESULT_OK, intent);
            ThreetyJianLiWebViewActivity.this.finish();
        }

        @JavascriptInterface
        public void noChoiceFromH5(String choice){
            MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + id);
            Intent intent = new Intent();
            intent.putExtra("choice", choice);
            intent.putExtra("id", id);
            setResult(RESULT_OK, intent);
            ThreetyJianLiWebViewActivity.this.finish();
        }



    }



}

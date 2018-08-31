package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.CommonActivity.WebViewJsInterface.CommonGetFromH5;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity.BusinessInfomationActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;


public class BusinessInfoWebView extends BaseActivity {

    private WebView commonWebView;

    private String path;
    private String title;
    private ImageView return_image;
    private ImageView personal_center;

    private CommonWebViewClickListener commonWebViewClickListener;
    private TextView tilte_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web_view);
        init();
        setRegister();
    }


    public static void StartBusinessWebView(Context context, String path, String title){
        Intent intent = new Intent(context, BusinessInfoWebView.class);
        intent.putExtra("path", path);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        commonWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        commonWebView.onResume();
    }



    /**
     * 接受到h5 的参数
     */
    public class JsInteration {
        @JavascriptInterface
        public void back(String value) {
            //value就是 H5 界面传递过来的参数值
            MyLog.e("JsInteration", "back(JsInteration.java:97)" + value);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.BusinessInfoChangeFlag:
                if (resultCode == RESULT_OK){
                    this.finish();
                }
                break;
        }
    }

    private void init(){
        path = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");
        commonWebViewClickListener = new CommonWebViewClickListener();
        commonWebView = (WebView) findViewById(R.id.commonWebView);
        return_image = (ImageView) findViewById(R.id.return_image);
        personal_center = (ImageView) findViewById(R.id.personal_center);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        personal_center.setImageResource(R.mipmap.edit_image);
        personal_center.setVisibility(View.VISIBLE);
        tilte_text.setText(title);
        initWebView();
        //commonWebView.loadUrl(path);
        commonWebView.loadUrl(MyApplication.h5Path + "/companyDetail?token=" + MyApplication.tokenEnterprise);
        commonWebView.addJavascriptInterface(new JsInteration(), "android");
    }

    /**
     * 配置WebView
     *
     */
    private void initWebView(){
        WebSettings webSettings = commonWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        commonWebView.addJavascriptInterface(new CommonGetFromH5(this), "fenXiangFromH5");
        commonWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:shows(\"" + "getFromAndroid" + "\")");
                view.loadUrl("javascript:getUserId(\"" + MyApplication.tokenEnterprise + "\")");
            }
        });
    }


    private void setRegister(){
        return_image.setOnClickListener(commonWebViewClickListener);
        personal_center.setOnClickListener(commonWebViewClickListener);
    }




    class CommonWebViewClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    if (commonWebView.canGoBack()){
                        commonWebView.goBack();
                        return;
                    }else {
                        BusinessInfoWebView.this.finish();
                    }
                    break;
                case R.id.personal_center:
                    BusinessInfo businessInfo = new BusinessInfo();
                    businessInfo.setId(2);
                    BusinessInfomationActivity.StartBusinessInfomationActivity(BusinessInfoWebView.this, "企业信息", businessInfo);
                    //编辑企业信息
                    break;
            }
        }
    }


}

package com.a51zhipaiwang.worksend.Enterprise.Activity.WebViewActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WebViewActivity.IWebView;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWebViewActivityPresenter.IWebViewPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWebViewActivityPresenter.WebViewPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.WXShare;

import java.util.ArrayList;


public class WebViewActivity extends BaseActivity implements IWebView {

    private IWebViewPresenter webViewPresenter;
    private WebView jianLiWebView;
    private TextView tilte_text;
    private ImageView return_image;
    private ImageView personal_center;

    private ArrayList<SampleJianLiData> sampleJianLiData;
    private StringBuffer lists;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();
        initWebView();
        setRegister();

    }

    public static void StartWebViewActivity(Context context, ArrayList<SampleJianLiData> sampleJianLiData) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("sampleJianLiData", sampleJianLiData);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();
        jianLiWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        jianLiWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        jianLiWebView.destroy();
    }

    private void init() {
        webViewPresenter = new WebViewPresenter(this);
        handler = new Handler();
        sampleJianLiData = (ArrayList<SampleJianLiData>) getIntent().getExtras().getSerializable("sampleJianLiData");
        jianLiWebView = (WebView) findViewById(R.id.jianLiWebView);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("简历详情");
        return_image = (ImageView) findViewById(R.id.return_image);
        personal_center = (ImageView) findViewById(R.id.personal_center);

        lists = new StringBuffer();
        for (int i = 0; i < sampleJianLiData.size(); i++) {
            if (i != sampleJianLiData.size() - 1) {
                lists.append(sampleJianLiData.get(i).getId() + ",");
            } else {
                lists.append(sampleJianLiData.get(i).getId());
            }

        }
    }


    private void initWebView() {
        WebSettings webSettings = jianLiWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        jianLiWebView.addJavascriptInterface(new GetFromH5(), "fenXiangFromH5");
        jianLiWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //页面开始加载前
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:getUserId(\"" + MyApplication.tokenEnterprise + "\")");
            }
        });
        jianLiWebView.loadUrl(MyApplication.h5Path + "/resumeDetail2?lists=" + lists + "&token=" + MyApplication.tokenEnterprise);
    }

    private void setRegister() {

        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jianLiWebView.canGoBack()) {
                    jianLiWebView.goBack();
                } else {
                    WebViewActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void fenXiang(String path) {
        webViewPresenter.fenXiang(path);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void changeVisble(int visble) {
        return_image.setVisibility(visble);
    }


    public class GetFromH5 {

        /**
         * h5 调用分享接口
         *
         * @param path
         */
        @JavascriptInterface
        public void fenXiangFromH5(String path) {
            //ToastUtil.showToastTwo(path);
            WXShare wxShare = new WXShare(WebViewActivity.this);
            wxShare.shareUrl(0, WebViewActivity.this, path, "职派分享", "面试就有钱");
            MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + path);
        }

        @JavascriptInterface
        public void returnUnVisible() {
            return_image.post(new Runnable() {
                @Override
                public void run() {
                    return_image.setVisibility(View.INVISIBLE);
                }
            });
        }

        @JavascriptInterface
        public void returnVisible() {
            return_image.post(new Runnable() {
                @Override
                public void run() {
                    return_image.setVisibility(View.VISIBLE);
                }
            });
        }

        @JavascriptInterface
        public void toast(){
            ToastUtil.showToastTwo("您已申请过该职位");
        }

        @JavascriptInterface
        public void tip(String info){
            ToastUtil.showToastTwo(info);
        }
    }


}

package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.VideoDialogFragment;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.WXShare;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class VideoJianLIActivity extends BaseActivity {

    public static final int VIDEO_PERSONAL = 1;
    public static final int VIDEO_BUDINESS = 2;

    private MyVideoJianLiClickListener myVideoJianLiClickListener;

    private LinearLayout searchLayout;
    private LinearLayout returnLayout;
    private WebView videoWebView;

    private String title;
    private String path;
    private TextView titleText;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_jian_li);
        init();
        setRegister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoWebView.destroy();
    }

    public static void StartVideoJianLiActivity(Context context, String path, String title, int type){
        Intent intent = new Intent(context, VideoJianLIActivity.class);
        intent.putExtra("path", path);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public void screenInfo(String work, String sex){
        Map choiceDataMap = new HashMap();
        choiceDataMap.put("work", work);
        choiceDataMap.put("sex", sex);
        videoWebView.loadUrl("javascript:getchoiceDate('" + new JSONObject(choiceDataMap) + "')");
    }

    private void init(){
        myVideoJianLiClickListener = new MyVideoJianLiClickListener();

        path = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);

        searchLayout = (LinearLayout) findViewById(R.id.searchLayout);
        returnLayout = (LinearLayout) findViewById(R.id.returnLayout);
        videoWebView = (WebView) findViewById(R.id.videoWebView);
        titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText(title);
        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.getSettings().setSupportZoom(true);
        videoWebView.getSettings().setBuiltInZoomControls(true);
        videoWebView.getSettings().setLoadWithOverviewMode(true);
        videoWebView.getSettings().setUseWideViewPort(true);
        videoWebView.getSettings().setDomStorageEnabled(true);
        videoWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                videoWebView.loadUrl("javascript:getUserId(\"" + MyApplication.tokenEnterprise + "\")");
            }
        });
        videoWebView.loadUrl(path + "?token=" + MyApplication.tokenEnterprise);
        if (type == VIDEO_PERSONAL){
            searchLayout.setVisibility(View.VISIBLE);
        }else {
            searchLayout.setVisibility(View.INVISIBLE);
        }


        videoWebView.addJavascriptInterface(new GetFromH5(), "fenXiangFromH5");
    }

    private void setRegister(){
        searchLayout.setOnClickListener(myVideoJianLiClickListener);
        returnLayout.setOnClickListener(myVideoJianLiClickListener);
    }


    class MyVideoJianLiClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.searchLayout:
                    new VideoDialogFragment().show(getSupportFragmentManager(), "searchDialogFragment");
                    break;
                case R.id.returnLayout:
                    if (videoWebView.canGoBack()){
                        videoWebView.goBack();
                        return;
                    }
                    VideoJianLIActivity.this.finish();
                    break;
            }
        }
    }


    public class GetFromH5{

        /**
         * h5 调用分享接口
         * @param path
         */
        @JavascriptInterface
        public void fenXiangFromH5(String path){
            //ToastUtil.showToastTwo(path);
            WXShare wxShare = new WXShare(VideoJianLIActivity.this);
            wxShare.shareUrl(0, VideoJianLIActivity.this, path, "职派分享", "面试就有钱");
            MyLog.e("getFromH5", "fenXiangFromH5(getFromH5.java:119)" + path);
        }

        @JavascriptInterface
        public void returnUnVisible(){
            searchLayout.post(new Runnable() {
                @Override
                public void run() {
                    searchLayout.setVisibility(View.INVISIBLE);
                }
            });
        }

        @JavascriptInterface
        public void returnVisible(){
            searchLayout.post(new Runnable() {
                @Override
                public void run() {
                    searchLayout.setVisibility(View.VISIBLE);
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

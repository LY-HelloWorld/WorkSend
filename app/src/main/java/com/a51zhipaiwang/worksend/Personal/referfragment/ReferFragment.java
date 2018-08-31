package com.a51zhipaiwang.worksend.Personal.referfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.WebViewJsInterface.CommonGetFromH5;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.editarticlactivity.EditArtticlActivity;
import com.a51zhipaiwang.worksend.Personal.informationwebviewactivity.InformationWebViewActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/27
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
public class ReferFragment extends BaseFragment {

    private WebView web_refer;
    private ImageView return_image;
    private TextView tilte_text;

    public static ReferFragment newInstanceReferFragment(String token){
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        ReferFragment referFragment = new ReferFragment();
        referFragment.setArguments(bundle);
        return referFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refer, null);
        init(view);
        initWebView();
        setRegister();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.REFERFRAGMENT_FLAG:
                if (resultCode == RESULT_OK){
                    web_refer.loadUrl("javascript:changeBol(\"" + 1 + "\")");
                }
                break;
        }
    }

    private void init(View view){
        web_refer = (WebView) view.findViewById(R.id.web_refer);
        return_image = (ImageView) view.findViewById(R.id.return_image);
        return_image.setVisibility(View.INVISIBLE);
        tilte_text = (TextView) view.findViewById(R.id.tilte_text);
        tilte_text.setText("文章资讯");
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (web_refer.canGoBack()){
                    web_refer.goBack();
                }
            }
        });
    }

    private void initWebView(){
        WebSettings webSettings = web_refer.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        //web_findcompany.addJavascriptInterface(new GetFromH5(), "fenXiangFromH5");
        web_refer.addJavascriptInterface(new CommonGetFromH5(getActivity()), "fenXiangFromH5");
        web_refer.addJavascriptInterface(new CallAndroidFromH5(), "callAndroidFromH5");
        web_refer.setWebViewClient(new WebViewClient() {

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
                MyLog.e("ReferFragment", "onPageFinished(ReferFragment.java:110)" + "javascript:getUserId(\"" + getArguments().getString("token") + "\")");
                view.loadUrl("javascript:getUserId(\"" + getArguments().getString("token") + "\")");
            }
        });
        web_refer.loadUrl(MyApplication.h5Path + "/consult?token=" + MyApplication.tokenPersonal);
        //MyLog.e("WebViewActivity", "initWebView(WebViewActivity.java:96)" + MyApplication.h5Path + "/resumeDetail2?lists=" + lists);
    }

    @Override
    public void onPause() {
        super.onPause();
        web_refer.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        web_refer.onResume();
    }

    private class CallAndroidFromH5{

        @JavascriptInterface
        public void startEditActivity(){
            EditArtticlActivity.startEditArtticlActivity(ReferFragment.this, MyApplication.REFERFRAGMENT_FLAG);
        }

        @JavascriptInterface
        public void startInfoActivity(String id){
            InformationWebViewActivity.startInformationWebViewActivity(getActivity(), id);
        }


        @JavascriptInterface
        public void toast(){
            ToastUtil.showToastTwo("您已申请过该职位");
        }


    }


}

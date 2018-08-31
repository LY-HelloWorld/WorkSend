package com.a51zhipaiwang.worksend.Personal.informationwebviewactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.WebViewJsInterface.CommonGetFromH5;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.worktypeactivity.WorkTypeActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InformationWebViewActivity extends BaseActivity {

    private WebView web_information;
    private TextView tilte_text;
    private ImageView return_image;

    private String id;
    private EditText edx_information;

    public static void startInformationWebViewActivity(Context context, String id){
        Intent intent = new Intent(context, InformationWebViewActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_web_view);
        init();
        setRegister();
        initWebView();
    }


    @Override
    public void onPause() {
        super.onPause();
        web_information.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        web_information.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_information.destroy();
    }

    private void init(){
        web_information = (WebView) findViewById(R.id.web_information);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        id = getIntent().getStringExtra("id");
        tilte_text.setText("文章详情");
        edx_information = (EditText) findViewById(R.id.edx_information);
    }
    
    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformationWebViewActivity.this.finish();
            }
        });
        edx_information.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // 先隐藏键盘
                    ((InputMethodManager) edx_information.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(InformationWebViewActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 搜索，进行自己要的操作...
                    HashMap hashMap = new HashMap();
                    hashMap.put("id", id);
                    hashMap.put("commentName", edx_information.getText().toString());
                    MyLog.e("InformationWebViewActivity", "onEditorAction(InformationWebViewActivity.java:100)" + edx_information.getText().toString());
                    submitInfo(hashMap);
                    return true;
                }
                return false;
            }
        });
    }


    private void submitInfo(Map map){
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/comment/insComment.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.e("InformationWebViewActivity", "onResponse(InformationWebViewActivity.java:109)" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("success")){
                                String info = jsonObject.getString("info");
                                JSONObject infoJson = new JSONObject(info);
                                ToastUtil.showToastTwo(infoJson.getString("success"));
                                //调用h5
                                web_information.loadUrl("javascript:changeBol(1)");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showToastTwo("请检查您的网络连接！");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.showToastTwo("请检查您的网络连接！");
                    }
                }));
    }
    private void initWebView(){
        WebSettings webSettings = web_information.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);

        web_information.addJavascriptInterface(new CommonGetFromH5(InformationWebViewActivity.this), "fenXiangFromH5");
        web_information.setWebViewClient(new WebViewClient() {

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
        web_information.loadUrl(MyApplication.h5Path + "/consultDetail?" + id);
    }

}

package com.a51zhipaiwang.worksend.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


/**
 * Created by 罗怡 on 2017/8/13.
 * WebView的通用配置
 * 包括不跳转系统服务器
 * 下载监听
 * 下载进度
 * 下载完成后进入安装页面
 */

public class WebViewUtil {

    static String[] info;

    public static void setWebView(final Context context, final WebView webView, final ProgressBar progressBar, final LinearLayout progress_layout, final String path){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(path);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                //网页加载完毕后 将进度条隐藏
                progress_layout.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //当网页打开时 将进度条展示出来
                progressBar.setProgress(0);
                progress_layout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onReceivedError(final WebView view, int errorCode, String description, String failingUrl) {
                openErrorView(view, context);
            }

           /* @Override
            public void onReceivedError(final WebView view, WebResourceRequest request, WebResourceError error) {
                //super.onReceivedError(view, request, error);
                final LinearLayout parent = ((LinearLayout)view.getParent());
                if (parent.getChildCount() >= 1){
                    view.setVisibility(View.GONE);
                    final View hint_view = LayoutInflater.from(context).inflate(R.layout.hint_view, null);*//*
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) hint_view.getLayoutParams();
                        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);*//*
                    //hint_view.setLayoutParams(layoutParams);
                    hint_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            view.clearView();
                            parent.removeView(hint_view);
                            view.reload();
                            view.setVisibility(View.VISIBLE);
                        }
                    });
                    parent.addView(hint_view);
                }
            }*/

           /* @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //view.loadUrl(request.getUrl().toString());
                view.loadUrl(request.getUrl().toString());
                return true;
            }*/

        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                //DownManager.CheckPermission(url, (Activity) context);
                //DownManager.CheckPermission(url, (Activity) context);
            }
        });

    }

    /*public static void DownLoad(String url,final Context context){
        //自定义下载
        MyLog.e("WebViewUtil", "onDownloadStart(WebViewUtil.java:134)" + 123);
        info = url.split("/");
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("正在下载..");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()
                        , info[info.length - 1]) {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int i) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onResponse(File file, int i) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        MyLog.e("WebViewUtil", "inProgress(WebViewUtil.java:153)" + total);
                        if (progress == 1) {
                            progressDialog.dismiss();
                            CheckPermissionRead((Activity) context);
                        }

                        MyLog.e("WebViewUtil", "inProgress(WebViewUtil.java:157)" + progress);
                        progressDialog.setProgress((int) (100 * progress));
                    }
                });

    }*/


    public static void openErrorView(final WebView view, Context context){
        final LinearLayout parent = ((LinearLayout)view.getParent());
        if (parent.getChildCount() >= 1){/*
            view.setVisibility(View.GONE);
            final View hint_view = LayoutInflater.from(context).inflate(R.layout.hint_view, null);
            hint_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.clearView();
                    parent.removeView(hint_view);
                    view.reload();
                    view.onResume();
                    view.setVisibility(View.VISIBLE);
                }
            });
            parent.addView(hint_view);*/
        }
    }

    /**
     * 请求 写入权限，用于存储apk
     * @param url
     * @param activity
     */
    /*public static void CheckPermission(String url, Activity activity) {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                Toast.makeText(activity.getApplicationContext(), "当前存储权限不可用,请允许" + activity.getResources().getString(R.string.app_name) + "使用存储权限以便保存下载好的程序安装包", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getApplicationContext().getPackageName(), null);
                intent.setData(uri);
                intent.putExtra("url", url);
                activity.startActivityForResult(intent, 123);
            } else {
                DownManager.CheckPermission(url, activity);
            }
        } else {
            DownManager.CheckPermission(url, activity);
        }

    }*/

    /**
     * 请求 读取权限，用于安装apk
     * @param activity
     */
    public static void CheckPermissionRead(Activity activity) {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                Toast.makeText(activity.getApplicationContext(), "当前存储权限不可用,请允许" + activity.getResources().getString(R.string.app_name) + "使用存储权限以便读取好的程序安装包", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getApplicationContext().getPackageName(), null);
                intent.setData(uri);
                activity.startActivityForResult(intent, 123);
            } else {
                OpenApkUtil.openApk(activity, Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + info[info.length - 1]);
            }
        } else {
            OpenApkUtil.openApk(activity, Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + info[info.length - 1]);
        }*/

    }

}

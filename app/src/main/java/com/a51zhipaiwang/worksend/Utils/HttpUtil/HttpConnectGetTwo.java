package com.a51zhipaiwang.worksend.Utils.HttpUtil;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 罗怡 on 2018/4/18.
 */

public class HttpConnectGetTwo {

    private String path;
    private URL url;
    private HttpURLConnection connection;
    private ArrayList<String> params;
    private ArrayList<String> param_names;
    private HttpConnectGetTwoListener httpConnectGetTwoListener;

    public HttpConnectGetTwo(String path, @NonNull HttpConnectGetTwoListener httpConnectGetTwoListener) {
        this(path, null, null, httpConnectGetTwoListener);
    }

    public HttpConnectGetTwo(String path, ArrayList<String> params, ArrayList<String> param_names, @NonNull HttpConnectGetTwoListener httpConnectGetTwoListener) {
        this.path = path;
        this.params = params;
        this.param_names = param_names;
        this.httpConnectGetTwoListener = httpConnectGetTwoListener;
    }


    public void excute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(path)){
                    return;
                }
                try {
                    if (param_names != null
                            && params != null
                            && param_names.size() != 0
                            && param_names.size() == params.size()){

                        for (int i = 0; i < param_names.size(); i++) {
                            path += "&" + param_names.get(i) + "=" + params.get(i);
                        }

                    }
                    url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8 * 1000);
                    connection.setReadTimeout(8 * 1000);
                    InputStream inStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                    StringBuffer getInfo = new StringBuffer();
                    String line;
                    if (200 != connection.getResponseCode()){
                        if (httpConnectGetTwoListener != null){
                            httpConnectGetTwoListener.onError();
                        }
                    }
                    while ((line = reader.readLine()) != null) {
                        getInfo.append(line);
                    }
                    if (httpConnectGetTwoListener != null){
                        httpConnectGetTwoListener.onSuccess(getInfo.toString());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();



    }

    public interface HttpConnectGetTwoListener{
        public void onSuccess(String info);

        public void onError();
    }

}

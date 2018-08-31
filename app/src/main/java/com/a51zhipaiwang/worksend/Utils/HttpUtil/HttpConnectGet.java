package com.a51zhipaiwang.worksend.Utils.HttpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



/**
 * Created by 罗怡 on 2017/8/1.
 */

public class HttpConnectGet {

    private Handler handler;
    private String path;
    private URL url;
    private HttpURLConnection connection;
    private int success_what;
    public static final int HTTPCONNECTGET_ERROR = 1;
    private ArrayList<String> params;
    private ArrayList<String> param_names;

    public HttpConnectGet(Handler handler, String path, int success_what) {
        this.handler = handler;
        this.path = path;
        this.success_what = success_what;
        this.param_names = new ArrayList<>();
        this.params = new ArrayList<>();
    }

    public HttpConnectGet(Handler handler, String path, int success_what, ArrayList<String> params, ArrayList<String> param_names) {
        this.handler = handler;
        this.path = path;
        this.success_what = success_what;
        this.params = params;
        this.param_names = param_names;
    }

    public void excute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(path)){
                    return;
                }
                try {
                    if (param_names.size() != 0 && param_names.size() == params.size()){

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
                        Message message = handler.obtainMessage();
                        message.what = HTTPCONNECTGET_ERROR;
                        message.sendToTarget();
                    }
                    while ((line = reader.readLine()) != null) {
                        getInfo.append(line);
                    }
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("getInfo", String.valueOf(getInfo));
                    message.setData(bundle);
                    message.what = success_what;
                    message.sendToTarget();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();



    }

}

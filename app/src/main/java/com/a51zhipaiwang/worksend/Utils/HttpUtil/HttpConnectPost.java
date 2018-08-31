package com.a51zhipaiwang.worksend.Utils.HttpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by 罗怡 on 2017/8/4.
 */

public class HttpConnectPost  {

    private ArrayList<String> param_names;
    private ArrayList<String> params;
    private String path;
    private HttpURLConnection connection;
    private URL url;
    private Handler handler;
    private Message message;
    public static final int HTTPCONNECTPOST_ERROR = 20;
    private int success_code;

    public HttpConnectPost(String path, Handler handler
            , int success_code, ArrayList param_names, ArrayList params) {
        this.path = path;
        this.handler = handler;
        this.success_code = success_code;
        this.param_names = param_names;
        this.params = params;
    }

    public void excute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8 * 1000);
                    connection.setReadTimeout(8 * 1000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    StringBuffer param_buffer = new StringBuffer();
                    for (int i = 0; i < param_names.size(); i++) {
                        param_buffer.append(param_names.get(i) + "=" + params.get(i) + "&");
                    }
                    if (!TextUtils.isEmpty(param_buffer)) {
                        param_buffer.deleteCharAt(param_buffer.length() - 1);
                    }
                    OutputStream outputStream = connection.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    dataOutputStream.write(param_buffer.toString().getBytes("UTF-8"));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    message = handler.obtainMessage();
                    if (connection.getResponseCode() == 200){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuffer getInfo = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            getInfo.append(line);
                        }
                        reader.close();
                        Bundle bundle = new Bundle();
                        bundle.putString("getInfo", getInfo.toString());
                        message.what = success_code;
                        message.setData(bundle);
                        message.sendToTarget();

                    }else {
                        message.what = HTTPCONNECTPOST_ERROR;
                        message.sendToTarget();
                    }
                    connection.disconnect();

                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        }).start();
    }

}

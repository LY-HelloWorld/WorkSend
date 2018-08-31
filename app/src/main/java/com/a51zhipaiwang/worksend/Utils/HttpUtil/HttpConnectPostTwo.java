package com.a51zhipaiwang.worksend.Utils.HttpUtil;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 罗怡 on 2018/4/18.
 */

public class HttpConnectPostTwo {


    private String path;
    private URL url;
    private HttpURLConnection connection;
    private ArrayList<String> params;
    private ArrayList<String> param_names;
    private HttpConnectPostTwoListener httpConnectPostTwoListener;

    public HttpConnectPostTwo(String path, @NonNull HttpConnectPostTwoListener httpConnectPostTwoListener) {
        this(path, null, null, httpConnectPostTwoListener);
    }

    public HttpConnectPostTwo(String path, ArrayList<String> params, ArrayList<String> param_names, @NonNull HttpConnectPostTwoListener httpConnectPostTwoListener) {
        this.path = path;
        this.params = params;
        this.param_names = param_names;
        this.httpConnectPostTwoListener = httpConnectPostTwoListener;
    }


    public void excute() {
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
                    //如果有参数需要post
                    if (param_names != null && param_names.size() != 0) {
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
                    }
                    if (connection.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuffer getInfo = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            getInfo.append(line);
                        }
                        reader.close();
                        if (httpConnectPostTwoListener != null) {
                            httpConnectPostTwoListener.onSuccess(getInfo.toString());
                        }

                    } else {
                        if (httpConnectPostTwoListener != null) {
                            httpConnectPostTwoListener.onError();
                        }
                    }
                    connection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface HttpConnectPostTwoListener {
        public void onSuccess(String info);

        public void onError();
    }

}

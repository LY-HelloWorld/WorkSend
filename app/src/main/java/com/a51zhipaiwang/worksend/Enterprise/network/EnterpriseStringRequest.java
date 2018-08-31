package com.a51zhipaiwang.worksend.Enterprise.network;

import android.content.Intent;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   : 自定义Stringrequest请求
 *     version: 1.0
 * </pre>
 */
public class EnterpriseStringRequest extends StringRequest {

    private Map paramMap;
    private Object paramObject;

    public EnterpriseStringRequest(int method, String url, Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(method, url, listener, errorListener);
        this.paramMap = paramMap;
    }

    public EnterpriseStringRequest(int method, String url, Object paramObject, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(method, url, listener, errorListener);
        this.paramObject = paramObject;
    }

    public EnterpriseStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public EnterpriseStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map map = new HashMap();
        if (paramMap != null){
            map.put("jsonStr", new JSONObject(paramMap).toString());
            return map;
        }
        if (paramObject != null){
            map.put("jsonStr", new Gson().toJson(paramObject));
            return map;
        }
        return super.getParams();
    }

    @Override
    protected void deliverResponse(String response) {
        MyLog.e("EnterpriseStringRequest", "deliverResponse(EnterpriseStringRequest.java:66)" + response);
        if (response.equals(MyApplication.LOG_OUT)){
            MyApplication.context.sendBroadcast(new Intent(MyApplication.LOG_OUT));
            MyApplication.tokenEnterprise = "";
            SharedPreferencesUtil.saveSharedPreference("tokenEnterprise", "", MyApplication.userInfo, MyApplication.context);
            ToastUtil.showToastTwo("登录过期，请重新登录");
        }else {
            super.deliverResponse(response);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap hashMap = new HashMap();
        hashMap.put("token", MyApplication.tokenEnterprise);
        return hashMap;
    }
}

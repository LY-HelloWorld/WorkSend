package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseTongZhiActivityModel;


import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TongZhiActivityModel implements ITongZhiActivityModel {
    @Override
    public void getMessageInfo(final Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                , MyApplication.path + "api/enterpriseNew/messageDetailsAll.do?jsonStr=" + new JSONObject(paramsMap).toString(), listener, errorListener));
        */
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseNew/messageDetailsAll.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new JSONObject(paramsMap).toString());
                return map;
            }
        });
    }

    @Override
    public void surePosition(final Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/trialPost/updTrialPost.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new JSONObject(paramsMap).toString());
                MyLog.e("TongZhiActivityModel", "getParams(TongZhiActivityModel.java:45)" + new JSONObject(map).toString());
                return map;
            }
        });
    }
}

package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkManagerActivityModel;


import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkManagerActivityModel.IWorkManagerModel;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.solidfire.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WorkManagerModel implements IWorkManagerModel {
    @Override
    public void delelteWork(final Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                , MyApplication.path + "api/leafletsDetai/dePosition.do?jsonStr=" + new JSONObject(paramNameAndParmas).toString()
                , listener, errorListener));*/

        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/leafletsDetai/dePosition.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new JSONObject(paramNameAndParmas).toString());
                return map;
            }
        });

    }

    @Override
    public void getWorkNoParams(Response.ErrorListener errorListener, Response.Listener<String> listener) {
        this.getWork(null, errorListener, listener);
    }

    @Override
    public void getWork(final Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        if (paramNameAndParmas == null){/*
            MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                    , MyApplication.path + "api/leafletsDetai/position.do"
                    , listener, errorListener));*/
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/leafletsDetai/position.do",
                    listener,
                    errorListener));

        }else {
            /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                    , MyApplication.path + "api/leafletsDetai/position.do?jsonStr=" + new JSONObject(paramNameAndParmas).toString()
                    , listener, errorListener));*/

            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/leafletsDetai/position.do",
                    listener,
                    errorListener){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map map = new HashMap();
                    map.put("jsonStr", new JSONObject(paramNameAndParmas).toString());
                    return map;
                }
            });

            MyLog.e("WorkManagerModel", "getWork(WorkManagerModel.java:41)" + MyApplication.path + "api/leafletsDetai/position.do?jsonStr=" + new JSONObject(paramNameAndParmas).toString());
        }
    }

    @Override
    public void editWork(final WorkBean workBean, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        //将网络请求 添加到请求队列
        MyLog.e("PaiDanActivityModel", "submitPaiDanInfo(PaiDanActivityModel.java:19)" + new Gson().toJson(workBean));
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                , MyApplication.path + "api/leafletsDetai/leaflets.do?jsonStr=" + new Gson().toJson(workBean)
                , listener, errorListener));*/

        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/leafletsDetai/leaflets.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new com.google.gson.Gson().toJson(workBean));
                return map;
            }
        });

    }
}

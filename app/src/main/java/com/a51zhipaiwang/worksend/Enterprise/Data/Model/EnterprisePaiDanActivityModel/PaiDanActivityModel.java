package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterprisePaiDanActivityModel;


import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.solidfire.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class PaiDanActivityModel implements IPaiDanActivityModel {
    @Override
    public void submitPaiDanInfo(final WorkBean workBean, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        //将网络请求 添加到请求队列
        MyLog.e("PaiDanActivityModel", "submitPaiDanInfo(PaiDanActivityModel.java:19)" + new Gson().toJson(workBean));
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                , MyApplication.path + "api/leafletsDetai/leaflets.do?jsonStr=" + new Gson().toJson(workBean).replace(" ", "%2B")
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

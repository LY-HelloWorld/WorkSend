package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseTongZhiActivityModel;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

public interface ITongZhiActivityModel {


    public void getMessageInfo(Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener);


    public void surePosition(Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener);



}

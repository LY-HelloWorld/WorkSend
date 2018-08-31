package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiActivityModel;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

public interface IJianLiManagerActivityModel {

    public void deletJianLi(Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener);


    public void getJianLi(Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener);

    public void getJianLi(Response.ErrorListener errorListener, Response.Listener<String> listener);

}

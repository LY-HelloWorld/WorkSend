package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiFragmentModel;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

public interface IJianLiFragmentModel {
    /**
     * 获取简历列表
     */
    public void getHomeJianLiList(Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener, String path);


}

package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkClassfiycationActivityModel;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

public interface IWorkClassfiycationModel {
    /**
     * 获取工作列表
     */
    public void getWorkList(Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener, int type);

    /**
     * 查找工作
     * @param paramNamesAndParams
     * @param errorListener
     * @param listener
     */
    public void searchWork(Map paramNamesAndParams, Response.ErrorListener errorListener, Response.Listener<String> listener, int type);


}

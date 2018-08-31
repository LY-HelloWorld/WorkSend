package com.a51zhipaiwang.worksend.Personal.searchactivity.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.searchactivity.contract.ISearchContract;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/24
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class ISearchModel implements ISearchContract.Model {
    @Override
    public void getHistorySearchWork(Map map, final ISearchContract.Model.SearchCallBack searchCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/historicalRecords/searchRecord.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        searchCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        searchCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void getHotSearchWork(Map map, final SearchCallBack searchCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        searchCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        searchCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void deleteHistorySearch(Map map, final SearchCallBack searchCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/historicalRecords/delectSearchRecord.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        searchCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        searchCallBack.error(error.getMessage());
                    }
                }));
    }
}

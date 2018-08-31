package com.a51zhipaiwang.worksend.Personal.hotwork.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.hotwork.contract.IHotContract;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
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
public class IHotModel implements IHotContract.Model {
    @Override
    public void getHotWorkListAndTakeWork(Map map, final IHotCallBack iHotCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/topPosition/selTopPosition.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iHotCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iHotCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void takeWork(Map map, final IHotCallBack iHotCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/subscribe/addSubscribeToAPosition.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iHotCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iHotCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void cancelTakeWork(Map map, final IHotCallBack iHotCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/subscribe/deleteSubscribeToAPosition.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iHotCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iHotCallBack.error(error.getMessage());
                    }
                }));
    }
}

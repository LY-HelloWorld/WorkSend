package com.a51zhipaiwang.worksend.Personal.findworkactivity.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.findworkactivity.contract.IFindWorkContract;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/23
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
public class IFindWorkModel implements IFindWorkContract.Model {
    @Override
    public void getWorkList(Map map, final FindWorkCallBack findWorkCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/distributeLeaflets/searchForPositionInitialization.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        findWorkCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        findWorkCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void getRecommend(Map map, final FindWorkCallBack findWorkCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/distributeLeaflets/searchForPositionInitializationOneAll.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        findWorkCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        findWorkCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void getTakeWork(Map map, final FindWorkCallBack findWorkCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/subscribe/subscribeToAPosition.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        findWorkCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        findWorkCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void takeWork(Map map, final FindWorkCallBack findWorkCallBack) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/subscribe/addSubscribeToAPosition.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        findWorkCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        findWorkCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void deleteTakeWork(Map map, final FindWorkCallBack findWorkCallBack) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/subscribe/deleteSubscribeToAPosition.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        findWorkCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        findWorkCallBack.error(error.getMessage());
                    }
                }));
    }
}

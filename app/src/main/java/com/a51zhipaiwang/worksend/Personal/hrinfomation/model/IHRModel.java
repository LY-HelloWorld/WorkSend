package com.a51zhipaiwang.worksend.Personal.hrinfomation.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.hrinfomation.contract.IHRContract;
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
public class IHRModel implements IHRContract.Model {
    @Override
    public void getHRInfomation(Map map, final IHRContract.Model.HRInfomationCallBack hrInfomationCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/messageInitializationPage.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hrInfomationCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hrInfomationCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void deleteHRInfomation(Map map, final HRInfomationCallBack hrInfomationCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/deleteUserHaveNewMessagesOne.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hrInfomationCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hrInfomationCallBack.error(error.getMessage());
                    }
                }));
    }
}
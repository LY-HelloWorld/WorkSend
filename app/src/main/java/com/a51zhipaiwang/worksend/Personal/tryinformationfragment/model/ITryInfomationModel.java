package com.a51zhipaiwang.worksend.Personal.tryinformationfragment.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.tryinformationfragment.contract.ITryInfomationContract;
import com.a51zhipaiwang.worksend.Utils.MyLog;
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
public class ITryInfomationModel implements ITryInfomationContract.Model {
    @Override
    public void getTryInformation(Map map, final ITryInfomationContract.Model.ITryInformationCallBack iTryInformationCallBack) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/messageInitializationPage.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iTryInformationCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iTryInformationCallBack.error(error.getMessage());
                    }
                }));
        MyLog.e("ITryInfomationModel", "getTryInformation(ITryInfomationModel.java:48)" + map.get("userMessageState"));
    }

    @Override
    public void deleteTryInformation(Map map, final ITryInformationCallBack iTryInformationCallBack) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/deleteUserHaveNewMessagesOne.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iTryInformationCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iTryInformationCallBack.error(error.getMessage());
                    }
                }));
    }
}

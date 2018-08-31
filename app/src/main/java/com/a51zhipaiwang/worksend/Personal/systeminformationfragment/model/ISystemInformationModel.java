package com.a51zhipaiwang.worksend.Personal.systeminformationfragment.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.systeminformationfragment.contract.ISystemInformationContract;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/25
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
public class ISystemInformationModel implements ISystemInformationContract.Model {
    @Override
    public void getSystemInformation(Map map, final IBaseCallBack iBaseCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/messageInitializationPage.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iBaseCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iBaseCallBack.error(error.getMessage());
                    }
                }));
        MyLog.e("ISystemInformationModel", "getSystemInformation(ISystemInformationModel.java:48)" + map.get("userMessageState"));
    }

    @Override
    public void deleteSystemInfomation(Map map, final IBaseCallBack iBaseCallBack) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/deleteUserHaveNewMessagesa.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iBaseCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iBaseCallBack.error(error.getMessage());
                    }
                }));
    }
}

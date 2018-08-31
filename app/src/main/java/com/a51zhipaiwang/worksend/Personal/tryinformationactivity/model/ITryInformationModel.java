package com.a51zhipaiwang.worksend.Personal.tryinformationactivity.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.tryinformationactivity.contract.ITryInformationContract;
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
public class ITryInformationModel implements ITryInformationContract.Model {
    @Override
    public void getTryInfo(Map map, final ITryInformationContract.Model.ITryInfomationCallBack iTryInfomationCallBack) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/viewTheDetailsOfTheMessage.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iTryInfomationCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iTryInfomationCallBack.error(error.getMessage());
                    }
                }));

    }

    @Override
    public void applyTryInfo(Map map, final ITryInfomationCallBack iTryInfomationCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/receivingTrialPost.do",
                map,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iTryInfomationCallBack.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iTryInfomationCallBack.error(error.getMessage());
                    }
                }));
    }

    @Override
    public void cancelTryInfo(Map map, final ITryInfomationCallBack iTryInfomationCallBack) {

            MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                    MyApplication.path + "api/userMessage/cancelTheTrialPostasd.do",
                    map,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            iTryInfomationCallBack.success(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            iTryInfomationCallBack.error(error.getMessage());
                        }
                    }));
    }
}

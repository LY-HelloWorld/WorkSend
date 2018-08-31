package com.a51zhipaiwang.worksend.Personal.userinfoactivity.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   : 用户信息 提交编辑 实例
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
public class UserInfoModelImpl implements worksend.a51zhipaiwang.com.worksend.Personal.userinfoactivity.model.IUserInfoModel {
    @Override
    public void submit(Object userInfo, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userInformationTable/selUITS.do",
                userInfo,
                listener,
                errorListener
                ));
    }

    @Override
    public void getUserInfo(Map map, final IBaseCallBack iBaseCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userInformationTable/selUITS.do",
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
                }
        ));
    }
}

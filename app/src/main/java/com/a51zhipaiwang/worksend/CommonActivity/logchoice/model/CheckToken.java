package com.a51zhipaiwang.worksend.CommonActivity.logchoice.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/09
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
public class CheckToken implements ICheckTokenModel {

    @Override
    public void CheckTokenPersonal(Map map, Response.Listener listener, Response.ErrorListener errorListener) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseLog/panDUanToken.do",
                map,
                listener,
                errorListener));
    }

    @Override
    public void CheckTokenEnterprise(Map map, Response.Listener listener, Response.ErrorListener errorListener) {
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseLog/panDUanToken.do",
                map,
                listener,
                errorListener));
    }
}

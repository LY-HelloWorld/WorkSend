package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseLogActivityModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseLogActivityModel.ILogActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
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
public class LogActivityModel implements ILogActivityModel {
    @Override
    public void userLogIn(Map map, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseLog/judgementVerifyingCode.do",
                map,
                listener,
                errorListener));
    }
}

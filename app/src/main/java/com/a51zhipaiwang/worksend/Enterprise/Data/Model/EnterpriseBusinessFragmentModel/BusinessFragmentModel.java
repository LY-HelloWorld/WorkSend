package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessFragmentModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
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
 *     time   : 2018/08/03
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
public class BusinessFragmentModel implements IBusinessFragmentModel {
    @Override
    public void getBusiness(Map map, final IBaseCallBack iBaseCallBack) {
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseInfo/enter.do",
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
                        iBaseCallBack.success(error.getMessage());
                    }
                }));
    }
}
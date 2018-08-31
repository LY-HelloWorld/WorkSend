package com.a51zhipaiwang.worksend.Personal.trypathfragment.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.contract.ITryPathContract;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/20
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
public class ITryPathModel implements ITryPathContract.Model {
    @Override
    public void getTryWorking(Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener) {

        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/trialPost/selTPT.do",
                paramMap,
                listener,errorListener));
    }

    @Override
    public void getTryOutWork(Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/trialPost/selTPT.do",
                paramMap,
                listener,errorListener));

    }
}

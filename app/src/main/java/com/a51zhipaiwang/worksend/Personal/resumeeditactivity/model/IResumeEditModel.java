package com.a51zhipaiwang.worksend.Personal.resumeeditactivity.model;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.ResumeEntity;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.contract.IResumeEditContract;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.solidfire.gson.Gson;

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
public class IResumeEditModel implements IResumeEditContract.Model {
    @Override
    public void submitResume(ResumeEntity resumeEntity, final IBaseCallBack iBaseCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/curriculumVitaeDetailsUser/intCVDU.do",
                resumeEntity,
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
        MyLog.e("IResumeEditModel", "submitResume(IResumeEditModel.java:48)" + new Gson().toJson(resumeEntity));
    }

    @Override
    public void getResume(Map map, final IBaseCallBack iBaseCallBack) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/curriculumVitaeDetailsUser/detailsUser.do",
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

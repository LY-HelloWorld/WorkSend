package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiActivityModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiActivityModel.IJianLiManagerActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JianLiManagerActivityModel implements IJianLiManagerActivityModel {
    @Override
    public void deletJianLi(final Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener) {

        //将网络请求 添加到请求队列
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseCurriculumVitae/delEnterpriseCurriculumVitae.do?jsonStr=" + new JSONObject(paramsMap).toString(),
                listener,
                errorListener));*/


        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseCurriculumVitae/delEnterpriseCurriculumVitae.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new JSONObject(paramsMap).toString());
                return map;
            }
        });
    }

    @Override
    public void getJianLi(final Map paramsMap, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        if (paramsMap == null) {
            MyLog.e("JianLiManagerActivityModel", "getJianLi(JianLiManagerActivityModel.java:28)" + MyApplication.path + "api/enterpriseCurriculumVitae/selectEnterpriseCurriculumVitae.do");
            //将网络请求 添加到请求队列
            /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST,
                    MyApplication.path + "api/enterpriseCurriculumVitae/selEnterpriseCurriculumVitae.do",
                    listener,
                    errorListener));*/
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/enterpriseCurriculumVitae/selEnterpriseCurriculumVitae.do",
                    listener,
                    errorListener){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map map = new HashMap();
                    map.put("jsonStr", new JSONObject(paramsMap).toString());
                    return map;
                }
            });

        } else {
            MyLog.e("JianLiManagerActivityModel", "getJianLi(JianLiManagerActivityModel.java:34)" + MyApplication.path + "api/enterpriseCurriculumVitae/selEnterpriseCurriculumVitae.do?jsonStr=" + new JSONObject(paramsMap).toString());
            //将网络请求 添加到请求队列
            /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST,
                    MyApplication.path + "api/enterpriseCurriculumVitae/selEnterpriseCurriculumVitae.do?jsonStr=" + new JSONObject(paramsMap).toString(),
                    listener,
                    errorListener));*/
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/enterpriseCurriculumVitae/selEnterpriseCurriculumVitae.do",
                    listener,
                    errorListener){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map map = new HashMap();
                    map.put("jsonStr", new JSONObject(paramsMap).toString());
                    return map;
                }
            });
        }
    }

    @Override
    public void getJianLi(Response.ErrorListener errorListener, Response.Listener listener) {
        this.getJianLi(null, errorListener, listener);
    }
}

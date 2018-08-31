package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiFragmentModel;


import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiFragmentModel.IJianLiFragmentModel;
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

public class JianLiFragmentModel implements IJianLiFragmentModel {

    /**
     * 首页简历请求
     * @param paramNameAndParmas map类型的请求参数名 和请求参数
     * @param errorListener 请求错误监听
     * @param listener 请求反馈监听
     */
    @Override
    public void getHomeJianLiList(final Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener, String path) {
        //将网络请求 添加到请求队列
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                , MyApplication.path + "api/curriculumVitae/Initiali.do?jsonStr=" + new JSONObject(paramNameAndParmas).toString()
                , listener, errorListener));*/
        MyLog.e("JianLiFragmentModel", "getHomeJianLiList(JianLiFragmentModel.java:32)" + path + "?jsonStr=" + new JSONObject(paramNameAndParmas).toString());
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    path,
                    listener,
                    errorListener){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map map = new HashMap();
                    map.put("jsonStr", new JSONObject(paramNameAndParmas).toString());
                    return map;
                }
            });

        //requestQueue.add(new JsonObjectRequest(Request.Method.GET, "https://www.sojson.com/open/api/weather/json.shtml?city=北京", listener, errorListener));
    }

}

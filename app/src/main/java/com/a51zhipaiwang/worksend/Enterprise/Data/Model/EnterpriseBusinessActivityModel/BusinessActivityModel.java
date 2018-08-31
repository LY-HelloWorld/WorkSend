package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessActivityModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class BusinessActivityModel implements IBusinessAcitivityModel {
    @Override
    public void submitBusinessInfo(final BusinessInfo businessInfo, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        /*MyLog.e("BusinessActivityModel", "getHomeJianLiList(HomeFragmentModel.java:39)" + MyApplication.path + "api/curriculumVitae/Initiali.do?jsonStr=" + new Gson().toJson(businessInfo));
        JsonObjectRequest jsonObjectRequest = null;
        try {
            jsonObjectRequest = new MyJsonObjectRequest(Request.Method.POST,
                    MyApplication.path + "api/enterpriseInfo/insORupdEnterpriseInfo.do?jsonStr=" + URLEncoder.encode(new Gson().toJson(businessInfo), "utf-8"),
                    listener,
                    errorListener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MyApplication.requestQueue.add(jsonObjectRequest);*/
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseInfo/insORupdEnterpriseInfo.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new Gson().toJson(businessInfo));
                return map;
            }
        });
        MyLog.e("BusinessActivityModel", "submitBusinessInfo(BusinessActivityModel.java:55)" + "BusinessActivityModel");

    }
}

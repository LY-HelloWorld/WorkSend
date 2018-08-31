package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkClassfiycationActivityModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
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


public class WorkClassfycationModel implements IWorkClassfiycationModel {

    @Override
    public void getWorkList(final Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener, int type) {
        if (type == 1){
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/position/getPosition.do",
                    listener,
                    errorListener)
            );
        }else {
            MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                    MyApplication.path + "api/position/getPosition.do",
                    listener,
                    errorListener));
        }
    }

    @Override
    public void searchWork(final Map paramNamesAndParams, Response.ErrorListener errorListener, Response.Listener<String> listener, int type) {
        if (type == 1){
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/position/conditionPosition.do",
                    listener,
                    errorListener));
        }else {
            MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                    MyApplication.path + "api/position/conditionPosition.do",
                    listener,
                    errorListener));
        }

    }
}

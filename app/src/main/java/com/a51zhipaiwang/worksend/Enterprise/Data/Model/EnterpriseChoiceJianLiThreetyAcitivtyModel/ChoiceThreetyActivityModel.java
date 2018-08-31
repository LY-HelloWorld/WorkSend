package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseChoiceJianLiThreetyAcitivtyModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.SubChoiceListThreetyJianLi;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseChoiceJianLiThreetyAcitivtyModel.IChoiceJianLiThreetyActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChoiceThreetyActivityModel implements IChoiceJianLiThreetyActivityModel {
    @Override
    public void getJianLi(final Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener) {


        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/curriculumVitae/selectCurriculum.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new JSONObject(paramMap).toString());
                return map;
            }
        });
    }

    @Override
    public void submitList(final SubChoiceListThreetyJianLi subChoiceListThreetyJianLi, Response.ErrorListener errorListener, Response.Listener<String> listener) {

        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/userMessage/matchRobbingas.do",
                listener,
                errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("jsonStr", new Gson().toJson(subChoiceListThreetyJianLi));
                return map;
            }
        });

    }


}

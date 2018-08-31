package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseMoneyActivityModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.MoneyActivity.MoneyActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseMoneyActivityModel.IMoneyActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   : 充值model
 *     version: 1.0
 * </pre>
 */
public class MoneyActivityModel implements IMoneyActivityModel {

    @Override
    public void getMoney(Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener, int type) {
        MyLog.e("MoneyActivityModel", "getMoney(MoneyActivityModel.java:31)" + type);
        StringRequest stringRequest;
        if (type == MoneyActivity.PERSONAL){
            stringRequest = new PersonalStringRequest(Request.Method.POST,
                    MyApplication.path + "api/userInformationTable/checkTheBalance.do",
                    paramMap,
                    listener,
                    errorListener);
        }else {
            stringRequest = new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseInfo/queryPurse.do",
                paramMap,
                listener,
                errorListener);
        }
        MyApplication.requestQueue.add(stringRequest);
    }

    @Override
    public void rechargeMoney(Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener, int type) {
        /*MyApplication.requestQueue.add(new JsonObjectRequest(Request.Method.POST
                , MyApplication.path + "api/curriculumVitae/Initiali.do?jsonStr=" + new JSONObject(paramMap).toString()
                , listener, errorListener));*/
        listener.onResponse(null);
    }

    @Override
    public void withDraw(Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener, int type) {
        StringRequest stringRequest;
        if (type == MoneyActivity.PERSONAL){
            stringRequest = new PersonalStringRequest(Request.Method.POST,
                    MyApplication.path + "api/PresentRecord/deleteSubscribeToAPosition.do",
                    paramMap,
                    listener,
                    errorListener);
        }else {
            stringRequest = new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/userInformationTable/checkTheBalance.do",
                    paramMap,
                    listener,
                    errorListener);
        }
        MyApplication.requestQueue.add(stringRequest);
    }
}

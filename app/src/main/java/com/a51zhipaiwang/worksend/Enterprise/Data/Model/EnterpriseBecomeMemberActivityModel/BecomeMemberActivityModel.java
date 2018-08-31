package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBecomeMemberActivityModel;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBecomeMemberActivityModel.IBecomeMemberActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   : 成为会员model
 *     version: 1.0
 * </pre>
 */
public class BecomeMemberActivityModel implements IBecomeMemberActivityModel {
    @Override
    public void queryMember(Map map, Response.ErrorListener errorListener, Response.Listener listener) {
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/prisesas/selePrisesas.do",
                map,
                listener,
                errorListener));
        //listener.onResponse((int)(Math.random() * 24000));
    }
}

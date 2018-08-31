package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBecomeMemberPresenter;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BecomeMemberActivity.IBecomeMemberActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBecomeMemberActivityModel.BecomeMemberActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBecomeMemberActivityModel.IBecomeMemberActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBecomeMemberPresenter.IBecomeMemberAcitivityPresenter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   : 成为会员presenter
 *     version: 1.0
 * </pre>
 */
public class BecomeMemberAcitivityPresenter implements IBecomeMemberAcitivityPresenter {

    private IBecomeMemberActivityModel iBecomeMemberActivityModel;

    private IBecomeMemberActivity iBecomeMemberActivity;

    public BecomeMemberAcitivityPresenter(IBecomeMemberActivity iBecomeMemberActivity) {
        this.iBecomeMemberActivity = iBecomeMemberActivity;
        this.iBecomeMemberActivityModel = new BecomeMemberActivityModel();
    }

    @Override
    public void queryMember(Map map) {
        iBecomeMemberActivity.showLoadingDialog();
        iBecomeMemberActivityModel.queryMember(map, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iBecomeMemberActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iBecomeMemberActivity.closeLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("code").equals("success")){
                        JSONObject infoJson = new JSONObject(jsonObject.getString("info"));
                        iBecomeMemberActivity.setMemberInfo(infoJson.getString("introduce"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

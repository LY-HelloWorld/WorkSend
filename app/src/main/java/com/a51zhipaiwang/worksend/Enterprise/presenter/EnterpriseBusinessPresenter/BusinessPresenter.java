package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessPresenter;

import android.location.Geocoder;

import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity.IBusinessAcitivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessActivityModel.BusinessActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessActivityModel.IBusinessAcitivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessPresenter.IBusinessPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessPresenter implements IBusinessPresenter {

    private IBusinessAcitivity businessAcitivity;

    private IBusinessAcitivityModel businessAcitivityModel;

    public BusinessPresenter(IBusinessAcitivity businessAcitivity) {
        this.businessAcitivity = businessAcitivity;
        this.businessAcitivityModel = new BusinessActivityModel();
    }

    @Override
    public void submitBusinessInfo(BusinessInfo businessInfo) {
        businessAcitivity.showLoadingDialog();
        businessAcitivityModel.submitBusinessInfo(businessInfo, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                businessAcitivity.closeLoadingDialog();
                ToastUtil.showToastTwo("修改信息失败! 请检查您的网络连接");
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("BusinessPresenter", "onResponse(BusinessPresenter.java:36)" + response);
                businessAcitivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        String info = responseJson.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        String success = infoJson.getString("success");
                        if (success.equals("操作成功")){
                            ToastUtil.showToastTwo("修改信息成功");
                            businessAcitivity.returnSub(true);
                        }
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("修改信息失败! 请检查您的网络连接");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getLocationFromString(String locationString) {
        Geocoder geocoder = new Geocoder((BaseActivity)businessAcitivity);
        try {
            businessAcitivity.stringCoverLocation(geocoder.getFromLocationName(locationString, 1).get(0));
            MyLog.e("BusinessPresenter", "getLocationFromString(BusinessPresenter.java:49)" + geocoder.getFromLocationName(locationString, 1).get(0).getLongitude());
        } catch (Exception e) {
            businessAcitivity.stringCoverLocation(null);
            e.printStackTrace();
        }
    }
}

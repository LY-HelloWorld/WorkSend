package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseTongZhiActivityPresenter;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.TongZhiActivity.ITongZhiActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseTongZhiActivityModel.ITongZhiActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseTongZhiActivityModel.TongZhiActivityModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.solidfire.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class TongZhiActivityPresenter implements ITongZhiActivityPresenter {

    private ITongZhiActivity iTongZhiActivity;
    private ITongZhiActivityModel iTongZhiActivityModel;

    public TongZhiActivityPresenter(ITongZhiActivity iTongZhiActivity) {
        this.iTongZhiActivity = iTongZhiActivity;
        this.iTongZhiActivityModel = new TongZhiActivityModel();
    }

    @Override
    public void getWork(Map paramNamesAndParmas) {
        iTongZhiActivity.showLoadingDialog();
        iTongZhiActivityModel.getMessageInfo(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.e("TongZhiActivityPresenter", "onErrorResponse(TongZhiActivityPresenter.java:30)" + error.getMessage());
                iTongZhiActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iTongZhiActivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        String info = responseJson.getString("info");
                        ArrayList<MessageInfoBean> messageInfoBeans = new Gson().fromJson(info, new TypeToken<ArrayList<MessageInfoBean>>(){}.getType());
                        iTongZhiActivity.setReturnInfo(messageInfoBeans);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void surePosition(Map paramNamesAndParams) {
        iTongZhiActivity.showLoadingDialog();
        iTongZhiActivityModel.surePosition(paramNamesAndParams, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.e("TongZhiActivityPresenter", "onErrorResponse(TongZhiActivityPresenter.java:30)" + error.getMessage());
                iTongZhiActivity.closeLoadingDialog();
                iTongZhiActivity.surePosition(false);
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("TongZhiActivityPresenter", "onResponse(TongZhiActivityPresenter.java:70)" + response);
                iTongZhiActivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        String info = responseJson.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        if (infoJson.getString("success").equals("确认到岗")){
                            iTongZhiActivity.surePosition(true);
                        }else {
                            iTongZhiActivity.surePosition(false);
                        }
                    }else {
                        iTongZhiActivity.surePosition(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    iTongZhiActivity.surePosition(false);
                }
            }
        });
    }
}

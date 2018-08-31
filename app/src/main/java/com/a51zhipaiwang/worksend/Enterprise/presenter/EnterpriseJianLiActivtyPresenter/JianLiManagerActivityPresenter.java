package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiActivtyPresenter;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiManagerActivity.IJianLiManagerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiActivityModel.IJianLiManagerActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiActivityModel.JianLiManagerActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiActivtyPresenter.IJianLiManagerActivityPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.solidfire.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class JianLiManagerActivityPresenter implements IJianLiManagerActivityPresenter {

    private IJianLiManagerActivity iJianLiManagerActivity;

    private IJianLiManagerActivityModel iJianLiManagerActivityModel;


    public JianLiManagerActivityPresenter(IJianLiManagerActivity iJianLiManagerActivity) {
        this.iJianLiManagerActivity = iJianLiManagerActivity;
        this.iJianLiManagerActivityModel = new JianLiManagerActivityModel();
    }

    @Override
    public void getJianLi(Map paramNamesAndParmas) {
        iJianLiManagerActivity.showLoadingDialog();
        iJianLiManagerActivityModel.getJianLi(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iJianLiManagerActivity.closeLoadingDialog();
                MyLog.e("JianLiManagerActivityPresenter", "onErrorResponse(JianLiManagerActivityPresenter.java:39)" + error.getMessage());
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iJianLiManagerActivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        MyLog.e("JianLiManagerActivityPresenter", "onResponse(JianLiManagerActivityPresenter.java:45)" + response.toString());
                        String info = responseJson.getString("info");
                        ArrayList<SampleJianLiData> sampleJianLiData = new Gson().fromJson(info, new TypeToken<ArrayList<SampleJianLiData>>(){}.getType());
                        iJianLiManagerActivity.setJianLiList(sampleJianLiData);
                    }else {
                        iJianLiManagerActivity.setJianLiList(new ArrayList<SampleJianLiData>());
                    }
                } catch (JSONException e) {
                    iJianLiManagerActivity.setJianLiList(new ArrayList<SampleJianLiData>());
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void getJianLi() {
        this.getJianLi(null);
    }

    @Override
    public void deleteJianLi(Map paramNamesAndParams) {
        iJianLiManagerActivityModel.deletJianLi(paramNamesAndParams, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iJianLiManagerActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iJianLiManagerActivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        MyLog.e("JianLiManagerActivityPresenter", "onResponse(JianLiManagerActivityPresenter.java:45)" + response.toString());
                        String info = responseJson.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        if (infoJson.getString("success").equals("已删除")){
                            iJianLiManagerActivity.deletJianLiReturn(infoJson.getString("id"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

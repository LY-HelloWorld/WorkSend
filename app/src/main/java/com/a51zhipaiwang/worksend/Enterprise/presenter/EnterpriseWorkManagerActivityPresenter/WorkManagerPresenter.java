package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkManagerActivityPresenter;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkManagerActivity.IWorkManagerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkManagerActivityModel.IWorkManagerModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkManagerActivityModel.WorkManagerModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkManagerActivityPresenter.IWorkManagerPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.solidfire.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class WorkManagerPresenter implements IWorkManagerPresenter {

    private IWorkManagerActivity workManagerActivity;

    private IWorkManagerModel workManagerModel;

    public WorkManagerPresenter(IWorkManagerActivity workManagerActivity) {
        this.workManagerActivity = workManagerActivity;
        this.workManagerModel = new WorkManagerModel();
    }

    @Override
    public void editWork(final WorkBean workBean) {
        workManagerActivity.showLoadingDialog();
        workManagerModel.editWork(workBean, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                workManagerActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        JSONObject jsonObject = new JSONObject(info);
                        if ("派单成功".equals(jsonObject.getString("success"))){
                            workManagerActivity.editWork(workBean);
                        }else {
                            ToastUtil.showToastTwo("请检查您的网络连接!");
                        }
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("暂时没有数据");
                }
            }
        });
    }

    @Override
    public void deleteWork(Map paramsMap) {
        workManagerActivity.showLoadingDialog();
        workManagerModel.delelteWork(paramsMap, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                workManagerActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        String info = responseJson.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        if (infoJson.getString("success").equals("删除成功")){
                            String id = infoJson.getString("id");
                            MyLog.e("WorkManagerPresenter", "onResponse(WorkManagerPresenter.java:78)" + id);
                            workManagerActivity.deleteWork(id);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getWork(Map paramsMap) {
        workManagerActivity.showLoadingDialog();
        workManagerModel.getWork(paramsMap, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                workManagerActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                workManagerActivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("code").equals("success")){
                        String info = responseJson.getString("info");
                        ArrayList<WorkBean> workBeans = new Gson().fromJson(info, new TypeToken<ArrayList<WorkBean>>(){}.getType());
                        workManagerActivity.setWorkList(workBeans);
                    }else {
                        workManagerActivity.setWorkList(new ArrayList<WorkBean>());
                    }
                } catch (JSONException e) {
                    workManagerActivity.setWorkList(new ArrayList<WorkBean>());
                    workManagerActivity.closeLoadingDialog();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getWork() {
        getWork(null);
    }
}

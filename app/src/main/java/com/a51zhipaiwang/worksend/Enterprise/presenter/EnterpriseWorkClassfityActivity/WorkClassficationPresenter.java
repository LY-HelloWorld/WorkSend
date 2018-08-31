package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkClassfityActivity;

import com.a51zhipaiwang.worksend.Bean.WorkChoice;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.IWorkClassficationActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkClassfiycationActivityModel.IWorkClassfiycationModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkClassfiycationActivityModel.WorkClassfycationModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkClassfityActivity.IWorkClassficationActivityPresenter;
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


public class WorkClassficationPresenter implements IWorkClassficationActivityPresenter {

    private IWorkClassficationActivity iWorkClassficationActivity;

    private IWorkClassfiycationModel iWorkClassfiycationModel;

    private int type;

    public WorkClassficationPresenter(IWorkClassficationActivity iWorkClassficationActivity, int type) {
        this.iWorkClassficationActivity = iWorkClassficationActivity;
        this.iWorkClassfiycationModel = new WorkClassfycationModel();
        this.type = type;
    }

    @Override
    public void getWork(Map paramNamesAndParmas) {
        iWorkClassficationActivity.showLoadingDialog();
        iWorkClassfiycationModel.getWorkList(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iWorkClassficationActivity.closeLoadingDialog();
                MyLog.e("WorkClassficationPresenter", "onErrorResponse(WorkClassficationPresenter.java:32)" + error.getMessage());
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iWorkClassficationActivity.closeLoadingDialog();
                MyLog.e("WorkClassficationPresenter", "onResponse(WorkClassficationPresenter.java:45)" + response.toString());
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if ("success".equals(responseJson.getString("code"))){
                        String info = responseJson.getString("info");
                        ArrayList<WorkChoice> workChoices = new Gson().fromJson(info, new TypeToken<ArrayList<WorkChoice>>(){}.getType());
                        iWorkClassficationActivity.setWorkLiList(workChoices);
                    }
                } catch (JSONException e) {
                    MyLog.e("WorkClassficationPresenter", "onResponse(WorkClassficationPresenter.java:53)" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, type);
    }

    @Override
    public void searchWork(Map paramNamesAndParams) {
        iWorkClassficationActivity.showLoadingDialog();
        iWorkClassfiycationModel.searchWork(paramNamesAndParams, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iWorkClassficationActivity.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("WorkClassficationPresenter", "onResponse(WorkClassficationPresenter.java:74)" + response);
                iWorkClassficationActivity.closeLoadingDialog();
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if ("success".equals(responseJson.getString("code"))){
                        String info = responseJson.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        if (infoJson.getString("success").equals("没有此职位")){
                            iWorkClassficationActivity.setSearchWorkList(new ArrayList<WorkChoiceThreeStage>());
                            ToastUtil.showToastTwo("没有此职位");
                            return;
                        }
                        ArrayList<WorkChoiceThreeStage> workChoices = new Gson().fromJson(info, new TypeToken<ArrayList<WorkChoiceThreeStage>>(){}.getType());
                        iWorkClassficationActivity.setSearchWorkList(workChoices);
                    }
                } catch (JSONException e) {
                    MyLog.e("WorkClassficationPresenter", "onResponse(WorkClassficationPresenter.java:53)" + e.getMessage());
                    e.printStackTrace();
                }

            }
        }, type);
    }
}

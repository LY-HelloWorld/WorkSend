package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseChoiceThreetyActivityPresenter;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Bean.SubChoiceListThreetyJianLi;
import com.a51zhipaiwang.worksend.Enterprise.Activity.ChoiceJianliThreetyActivity.IChoiceJianLiThreetyAcitivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseChoiceJianLiThreetyAcitivtyModel.ChoiceThreetyActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseChoiceJianLiThreetyAcitivtyModel.IChoiceJianLiThreetyActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseChoiceThreetyActivityPresenter.IChoiceThreetyJIanLiActivityPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.solidfire.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class ChoiceThreeTyJianLiActivityPresenter implements IChoiceThreetyJIanLiActivityPresenter {

    private IChoiceJianLiThreetyActivityModel choiceJianLiThreetyActivityModel;

    private IChoiceJianLiThreetyAcitivity choiceJianLiThreetyAcitivity;

    public ChoiceThreeTyJianLiActivityPresenter(IChoiceJianLiThreetyAcitivity choiceJianLiThreetyAcitivity) {
        this.choiceJianLiThreetyAcitivity = choiceJianLiThreetyAcitivity;
        this.choiceJianLiThreetyActivityModel = new ChoiceThreetyActivityModel();
    }

    @Override
    public void getList(Map paramNamesAndParmas) {

        choiceJianLiThreetyAcitivity.showLoadingDialog();
        choiceJianLiThreetyActivityModel.getJianLi(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                choiceJianLiThreetyAcitivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("ChoiceThreeTyJianLiActivityPresenter", "onResponse(ChoiceThreeTyJianLiActivityPresenter.java:46)" + response);

                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        JSONObject infoObject = new JSONObject(info);
                        MyLog.e("ChoiceThreeTyJianLiActivityPresenter", "onResponse(ChoiceThreeTyJianLiActivityPresenter.java:53)" + info);
                        JSONArray sj = infoObject.getJSONArray("sj");
                        MyLog.e("HomeFragmentPresenterImp", "onResponse(HomeFragmentPresenterImp.java:54)" + sj);
                        ArrayList<SampleJianLiData> sampleJianLiData = new Gson().fromJson(sj.toString(), new TypeToken<ArrayList<SampleJianLiData>>(){}.getType());
                        choiceJianLiThreetyAcitivity.setJianLiList(sampleJianLiData);
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("暂时没有数据");
                }
                choiceJianLiThreetyAcitivity.closeLoadingDialog();
            }
        });
    }

    @Override
    public void submitChoiceList(SubChoiceListThreetyJianLi subChoiceListThreetyJianLi) {

        choiceJianLiThreetyAcitivity.showLoadingDialog();
        choiceJianLiThreetyActivityModel.submitList(subChoiceListThreetyJianLi, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                choiceJianLiThreetyAcitivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    MyLog.e("ChoiceThreeTyJianLiActivityPresenter", "onResponse(ChoiceThreeTyJianLiActivityPresenter.java:83)" + response);
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        if (infoJson.getString("success").equals("匹配成功")){
                            choiceJianLiThreetyAcitivity.submitReturn(true);
                        }else {
                            ToastUtil.showToastTwo("请检查您的网络连接!");
                        }
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("暂时没有数据");
                }
                choiceJianLiThreetyAcitivity.closeLoadingDialog();
            }
        });
    }
}

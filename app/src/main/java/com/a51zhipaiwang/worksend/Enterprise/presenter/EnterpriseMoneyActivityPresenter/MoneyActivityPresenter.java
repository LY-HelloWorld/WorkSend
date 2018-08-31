package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseMoneyActivityPresenter;

import android.support.v7.app.AppCompatActivity;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.MoneyActivity.IMoneyAcitivty;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseMoneyActivityModel.IMoneyActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseMoneyActivityModel.MoneyActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseMoneyActivityPresenter.IMoneyActivityPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.RechargeUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MoneyActivityPresenter implements IMoneyActivityPresenter {

    private IMoneyActivityModel moneyActivityModel;

    private IMoneyAcitivty moneyAcitivty;

    private RechargeUtil rechargeUtil;

    private int type;

    public MoneyActivityPresenter(IMoneyAcitivty moneyAcitivty, int type) {
        this.moneyAcitivty = moneyAcitivty;
        this.type = type;
        moneyActivityModel = new MoneyActivityModel();
        rechargeUtil = new RechargeUtil((AppCompatActivity)moneyAcitivty, MyApplication.APP_ID);
    }

    @Override
    public void getMoney(Map paramMap) {

        moneyAcitivty.showLoadingDialog();
        moneyActivityModel.getMoney(paramMap, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                moneyAcitivty.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接");
                moneyAcitivty.setMoney("0.00");
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("MoneyActivityPresenter", "onResponse(MoneyActivityPresenter.java:63)" + response);
                moneyAcitivty.closeLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("code").equals("success")){
                        String info = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        moneyAcitivty.setMoney(infoJson.getString("wallet"));
                    }else {
                        moneyAcitivty.setMoney("0.00");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    moneyAcitivty.setMoney("0.00");
                }
            }
        }, type);
    }

    @Override
    public void recharge(Map paramMap, int type) {
        moneyAcitivty.showLoadingDialog();
        rechargeUtil.getWeiXinPayOrder(paramMap, new RechargeUtil.IGetOrderReturn() {
            @Override
            public void error(String message) {
                moneyAcitivty.returnInfo(false);
            }
        }, type);
       /* moneyActivityModel.getMoney(paramMap, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                moneyAcitivty.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接");
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        });*/
    }

    @Override
    public void withDraw(Map map) {
        moneyAcitivty.showLoadingDialog();
        moneyActivityModel.withDraw(map, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        moneyAcitivty.closeLoadingDialog();
                        moneyAcitivty.returnWithDrawo("010");
                    }
                },
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        moneyAcitivty.closeLoadingDialog();
                        MyLog.e("MoneyActivityPresenter", "onResponse(MoneyActivityPresenter.java:119)" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("success")){
                                JSONObject infoJson = new JSONObject(jsonObject.getString("info"));
                                moneyAcitivty.returnWithDrawo(infoJson.getString("success"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            moneyAcitivty.returnWithDrawo("010");
                        }
                    }
                }, type);
    }
}

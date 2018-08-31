package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseLogActivityPresenter;

import android.support.v7.app.AppCompatActivity;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.logactitivityview.ILogActivityView;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseLogActivityModel.ILogActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseLogActivityModel.LogActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseLogActivityPresenter.ILogActivityPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class LogActivityPresenter implements ILogActivityPresenter {

    private ILogActivityModel iLogActivityModel;
    private ILogActivityView iLogActivityView;

    public LogActivityPresenter(ILogActivityView iLogActivityView) {
        this.iLogActivityView = iLogActivityView;
        this.iLogActivityModel = new LogActivityModel();
    }

    @Override
    public void userLogIn(Map paramMap) {
        iLogActivityView.showLoadingDialog();
        //iLogActivityView.logReturnInfo("");
        iLogActivityModel.userLogIn(paramMap, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("LogActivityPresenter", "onResponse(LogActivityPresenter.java:45)" + response);
                iLogActivityView.closeLoadingDialog();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("code").equals("success")){
                        JSONObject infoJson = new JSONObject(jsonObject.getString("info"));
                        if (infoJson.getString("success").equals("验证码错误")){
                            iLogActivityView.logReturnInfo("fail");
                        }else {
                            iLogActivityView.logReturnInfo("success");
                            String token = infoJson.getString("token");
                            SharedPreferencesUtil.saveSharedPreference("tokenEnterprise", token, MyApplication.userInfo, (AppCompatActivity)iLogActivityView);
                            MyApplication.tokenEnterprise = token;
                        }
                    }else {
                        ToastUtil.showToastTwo("登录失败！请检查您的网络连接");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ToastUtil.showToastTwo("登录失败！请检查您的网络连接");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.e("LogActivityPresenter", "onErrorResponse(LogActivityPresenter.java:53)" + error.getMessage());
                ToastUtil.showToastTwo("登录失败！请检查您的网络连接");
                iLogActivityView.closeLoadingDialog();

            }
        });
    }
}

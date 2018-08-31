package com.a51zhipaiwang.worksend.CommonActivity.logchoice.presenter;

import android.text.TextUtils;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.model.CheckToken;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.model.ICheckTokenModel;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.view.ILogChoiceView;
import com.a51zhipaiwang.worksend.Enterprise.Activity.HomeActivity;
import com.a51zhipaiwang.worksend.Personal.homeactivity.PersonalHomeActivity;
import com.a51zhipaiwang.worksend.Personal.logactivity.LogAcitivity;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;


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
public class LogChoicePresenter implements ILogChoicePresenter {

    private ILogChoiceView iLogChoiceView;
    private ICheckTokenModel checkTokenModel;

    public LogChoicePresenter(ILogChoiceView iLogChoiceView) {
        this.iLogChoiceView = iLogChoiceView;
        this.checkTokenModel = new CheckToken();
    }

    @Override
    public void workerChoice(final BaseActivity baseActivity) {
        //PersonalHomeActivity.startPersonalHomeAcitivty(baseActivity);
        if (TextUtils.isEmpty(MyApplication.tokenPersonal)){
            LogAcitivity.startLogActivity(baseActivity);
        }else {
            checkTokenModel.CheckTokenPersonal(null,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject responseJson = new JSONObject(response);
                                if (responseJson.getString("code").equals("success")){
                                    JSONObject infoJson = new JSONObject(responseJson.getString("info"));
                                    if (infoJson.getString("success").equals("ERROR")){
                                        LogAcitivity.startLogActivity(baseActivity);
                                    }else {
                                        PersonalHomeActivity.startPersonalHomeAcitivty(baseActivity);
                                    }
                                }
                            } catch (JSONException e) {
                                LogAcitivity.startLogActivity(baseActivity);
                                e.printStackTrace();
                            }
                            MyLog.e("LogChoicePresenter", "onResponse(LogChoicePresenter.java:53)" + response);
                            //
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            LogAcitivity.startLogActivity(baseActivity);
                        }
                    });
        }
    }

    @Override
    public void recruitChoice(final BaseActivity baseActivity) {
        if (TextUtils.isEmpty(MyApplication.tokenEnterprise)){
            com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.LogActivity.StartLogActivity(baseActivity);
        }else {
            checkTokenModel.CheckTokenEnterprise(null,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject responseJson = new JSONObject(response);
                                if (responseJson.getString("code").equals("success")){
                                    JSONObject infoJson = new JSONObject(responseJson.getString("info"));
                                    if (infoJson.getString("success").equals("ERROR")){
                                        com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.LogActivity.StartLogActivity(baseActivity);
                                    }else {
                                        HomeActivity.startHomeActivity(baseActivity);
                                    }
                                }
                            } catch (JSONException e) {
                                com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.LogActivity.StartLogActivity(baseActivity);
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.LogActivity.StartLogActivity(baseActivity);
                        }
                    });
        }
    }
}

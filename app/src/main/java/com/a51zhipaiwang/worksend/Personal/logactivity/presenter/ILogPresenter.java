package com.a51zhipaiwang.worksend.Personal.logactivity.presenter;

import android.support.v7.app.AppCompatActivity;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.homeactivity.PersonalHomeActivity;
import com.a51zhipaiwang.worksend.Personal.logactivity.contract.ILogContract;
import com.a51zhipaiwang.worksend.Personal.logactivity.model.ILogModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/25
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
public class ILogPresenter implements ILogContract.Presenter {

    private ILogContract.View view;
    private ILogContract.Model model;

    public ILogPresenter(ILogContract.View view) {
        this.view = view;
        this.model = new ILogModel();
    }

    @Override
    public void getSecriCode(Map map) {
        view.showLoadingDialog();
        model.getSecriCode(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ILogPresenter", "success(ILogPresenter.java:44)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        view.getSercircodeReturnInfo(true);
                    }else {
                        view.getSercircodeReturnInfo(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.getSercircodeReturnInfo(false);
                }
            }

            @Override
            public void error(String error) {
                MyLog.e("ILogPresenter", "error(ILogPresenter.java:50)" + error);
                view.getSercircodeReturnInfo(false);
            }
        });
    }

    @Override
    public void log(Map map) {
        view.showLoadingDialog();/*
        SharedPreferencesUtil.saveSharedPreference("tokenPersonal", "123", MyApplication.userInfo, (AppCompatActivity)view);
        MyApplication.tokenPersonal = "123";
        view.logSuccess(true);*/
        model.log(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ILogPresenter", "success(ILogPresenter.java:62)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        JSONObject infoJson = new JSONObject(jsonObject.getString("info"));
                        String token = infoJson.getString("token");
                        SharedPreferencesUtil.saveSharedPreference("tokenPersonal", token, MyApplication.userInfo, (AppCompatActivity)view);
                        MyApplication.tokenPersonal = token;
                        view.logSuccess(true);
                    }else {
                        view.logSuccess(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.logSuccess(false);
                }
                //view.logSuccess(true);
            }

            @Override
            public void error(String error) {
                MyLog.e("ILogPresenter", "error(ILogPresenter.java:67)" + error);
                view.logSuccess(false);
            }
        });
    }
}

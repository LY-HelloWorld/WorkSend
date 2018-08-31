package com.a51zhipaiwang.worksend.CommonActivity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;


import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.LogChoiceActivity;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observer;


public class BaseActivity extends AppCompatActivity {

    protected String city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        if (TextUtils.isEmpty(SharedPreferencesUtil.readSharedPreference("city", MyApplication.cityInfo, "", this))){
            city = "定位失败";
        }else {
            city = SharedPreferencesUtil.readSharedPreference("city", MyApplication.cityInfo, "", this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    public void logOut(){
        ActivityCollector.finishAll();
        this.startActivity(new Intent(this, LogChoiceActivity.class));
    }

    public void checkPermission(String[] permissions){
        RxPermissions.getInstance(this)
                .request(permissions)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToastTwo("请开启相关权限");

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (!aBoolean){
                            ToastUtil.showToastTwo("主人，我被禁止啦，去设置权限设置那把我打开哟");
                        }else {
                            doSomethingAfterGetPermissions();
                        }
                    }
                });
    }

    public void doSomethingAfterGetPermissions(){

    }



}

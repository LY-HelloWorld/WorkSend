package com.a51zhipaiwang.worksend.Personal.findbackpasswordactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.findbackpasswordactivity.contract.FindBackPasswordContract;
import com.a51zhipaiwang.worksend.R;

public class FindbackPasswordActivity extends BaseActivity implements FindBackPasswordContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findback_password);
    }

    @Override
    public void startTimeing() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerError(String errorInfo) {

    }

    @Override
    public String getPhoneNum() {
        return null;
    }

    @Override
    public String getVerifyCode() {
        return null;
    }

    @Override
    public String getPassWord() {
        return null;
    }

    @Override
    public String getRePassWord() {
        return null;
    }

    @Override
    public void showTip(int type) {

    }

    @Override
    public void clearTip() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }
}

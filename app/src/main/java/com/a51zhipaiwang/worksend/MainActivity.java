package com.a51zhipaiwang.worksend;

import android.Manifest;
import android.os.Bundle;

import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE});
    }

    @Override
    public void doSomethingAfterGetPermissions() {
        ToastUtil.showToastTwo("请求成功");
    }
}

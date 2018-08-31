package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;


public class BaseFragment extends Fragment {

    protected String city;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCity();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        getCity();
    }


    protected void getCity(){

        if (TextUtils.isEmpty(SharedPreferencesUtil.readSharedPreference("city", MyApplication.cityInfo, "", MyApplication.context))){
            city = "定位失败";
        }else {
            city = SharedPreferencesUtil.readSharedPreference("city", MyApplication.cityInfo, "", MyApplication.context);
        }

    }

    /**
     * 展示加载动画
     */
    public void showLoading(){

    }

    /**
     * 关闭加载动画
     */
    public void closeLoading(){}


}

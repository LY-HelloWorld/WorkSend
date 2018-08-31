package com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;

import java.util.ArrayList;


public interface IJianLiListFragment {

    public void setJianLiList(ArrayList<SampleJianLiData> jianLiList);

    public void addJianLiList(ArrayList<SampleJianLiData> jianLiList);

    /**
     * 展示加载框
     */
    public void showLoadingDialog();


    /**
     * 关闭加载框
     */
    public void closeLoadingDialog();



}

package com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;

import java.util.ArrayList;


public interface IHomeFragment {

    public final int ADDINFO = 1;
    public final int SETINFO = 2;


    /**
     * 展示加载框
     */
    public void showLoadingDialog();


    /**
     * 关闭加载框
     */
    public void closeLoadingDialog();


    /**
     * 将返回的数据 设置简历列表
     * @param jianLiList
     */
    public void setJianLiList(ArrayList<SampleJianLiData> jianLiList, int type);


}

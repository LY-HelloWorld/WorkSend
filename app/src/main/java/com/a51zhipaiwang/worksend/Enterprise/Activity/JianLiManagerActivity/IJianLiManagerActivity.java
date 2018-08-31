package com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiManagerActivity;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

import java.util.ArrayList;


public interface IJianLiManagerActivity extends IBaseActivity {



    /**
     * 将返回的数据 设置工作
     * @param sampleJianLiData
     */
    public void setJianLiList(ArrayList<SampleJianLiData> sampleJianLiData);


    public void deletJianLiList(String id);

    public void deletJianLiReturn(String id);


}

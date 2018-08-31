package com.a51zhipaiwang.worksend.Enterprise.Activity.ChoiceJianliThreetyActivity;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

import java.util.ArrayList;

public interface IChoiceJianLiThreetyAcitivity extends IBaseActivity {

    /**
     * 设置简历列表
     * @param sampleJianLiData
     */
    public void setJianLiList(ArrayList<SampleJianLiData> sampleJianLiData);

    public void submitReturn(boolean returnInfo);


}

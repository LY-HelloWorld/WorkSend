package com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity;

import com.a51zhipaiwang.worksend.Bean.WorkChoice;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

import java.util.ArrayList;


public interface IWorkClassficationActivity extends IBaseActivity {



    /**
     * 将返回的数据 设置工作
     * @param workChoices
     */
    public void setWorkLiList(ArrayList<WorkChoice> workChoices);

    /**
     * 设置搜索的职位列表
     * @param workChoiceThreeStages
     */
    public void setSearchWorkList(ArrayList<WorkChoiceThreeStage> workChoiceThreeStages);

}

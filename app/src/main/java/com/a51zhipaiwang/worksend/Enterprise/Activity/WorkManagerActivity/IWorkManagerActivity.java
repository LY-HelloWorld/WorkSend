package com.a51zhipaiwang.worksend.Enterprise.Activity.WorkManagerActivity;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

import java.util.ArrayList;


public interface IWorkManagerActivity extends IBaseActivity {



    public void setWorkList(ArrayList<WorkBean> workBeans);

    public void deleteWork(String id);

    public void addWork(boolean addOrNot);

    public void editWork(WorkBean workBean);

}

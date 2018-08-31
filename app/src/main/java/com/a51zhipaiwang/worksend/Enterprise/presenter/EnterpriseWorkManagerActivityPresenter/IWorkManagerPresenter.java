package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkManagerActivityPresenter;

import com.a51zhipaiwang.worksend.Bean.WorkBean;

import java.util.Map;


public interface IWorkManagerPresenter {

    public void editWork(WorkBean workBean);

    public void deleteWork(Map paramsMap);

    public void getWork(Map paramsMap);

    public void getWork();
}

package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWorkManagerActivityModel;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.android.volley.Response;

import java.util.Map;

public interface IWorkManagerModel {

    /**
     * 删除工作
     */
    public void delelteWork(Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener);



    public void getWorkNoParams(Response.ErrorListener errorListener, Response.Listener<String> listener);
    /**
     * 获取工作列表
     * @param paramNameAndParmas
     * @param errorListener
     * @param listener
     */
    public void getWork(Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener);


    /**
     * 编辑工作
     * @param workBean
     * @param errorListener
     * @param listener
     */
    public void editWork(WorkBean workBean, Response.ErrorListener errorListener, Response.Listener<String> listener);


}

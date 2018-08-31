package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessFragmentPresenter;

import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessFragmentModel.BusinessFragmentModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessFragmentModel.IBusinessFragmentModel;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BusinessFragment.IBusinessFragment;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/03
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class BusinessFragmentPresenter implements IBusinessFragmentPresenter {

    private IBusinessFragmentModel model;
    private IBusinessFragment view;

    public BusinessFragmentPresenter(IBusinessFragment view) {
        this.view = view;
        this.model = new BusinessFragmentModel();
    }

    @Override
    public void getBusinessInfo(Map map) {
        view.showLoadingDialog();
        model.getBusiness(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                try {
                    MyLog.e("BusinessFragmentPresenter", "success(BusinessFragmentPresenter.java:49)" + info);
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        BusinessInfo businessInfo = new Gson().fromJson(sInfo, BusinessInfo.class);
                        view.setBusinessInfo(businessInfo);
                    }else {
                        view.setBusinessInfo(null);
                    }
                } catch (Exception e) {
                    view.setBusinessInfo(null);
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                view.setBusinessInfo(null);
            }
        });
    }
}

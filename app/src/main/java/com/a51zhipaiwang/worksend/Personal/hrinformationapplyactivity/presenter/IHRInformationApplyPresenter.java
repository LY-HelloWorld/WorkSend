package com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity.presenter;

import com.a51zhipaiwang.worksend.Personal.entity.HRSendInfor;
import com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity.contract.IHRInformationApplyContract;
import com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity.model.IHRInformationApplyModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/24
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
public class IHRInformationApplyPresenter implements IHRInformationApplyContract.Presenter {

    private IHRInformationApplyContract.View view;
    private IHRInformationApplyContract.Model model;

    public IHRInformationApplyPresenter(IHRInformationApplyContract.View view) {
        this.view = view;
        this.model = new IHRInformationApplyModel();
    }

    @Override
    public void getWorkInfo(Map map) {
        view.showLoadingDialog();
        model.getWorkInfo(map, new IHRInformationApplyContract.Model.IHRInformationApplyCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IHRInformationApplyPresenter", "success(IHRInformationApplyPresenter.java:41)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        if (!infoJson.getString("success").equals("沒有数据")){
                            HRSendInfor hrSendInfor = new Gson().fromJson(infoJson.getString("success"), HRSendInfor.class);
                            view.setWorkInfo(hrSendInfor);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void applyWork(Map map) {
        view.showLoadingDialog();
        model.applyWork(map, new IHRInformationApplyContract.Model.IHRInformationApplyCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        ToastUtil.showToastTwo(infoJson.getString("success"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyLog.e("IHRInformationApplyPresenter", "success(IHRInformationApplyPresenter.java:76)" + info);
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();
            }
        });
    }

    @Override
    public void cancel(Map map) {
        view.showLoadingDialog();
        model.cancel(map, new IHRInformationApplyContract.Model.IHRInformationApplyCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IHRInformationApplyPresenter", "success(IHRInformationApplyPresenter.java:105)" + info);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}

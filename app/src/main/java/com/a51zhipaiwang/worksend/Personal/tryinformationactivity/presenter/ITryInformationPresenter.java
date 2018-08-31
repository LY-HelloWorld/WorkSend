package com.a51zhipaiwang.worksend.Personal.tryinformationactivity.presenter;

import com.a51zhipaiwang.worksend.Personal.entity.HRSendInfor;
import com.a51zhipaiwang.worksend.Personal.tryinformationactivity.contract.ITryInformationContract;
import com.a51zhipaiwang.worksend.Personal.tryinformationactivity.model.ITryInformationModel;
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
public class ITryInformationPresenter implements ITryInformationContract.Presenter {

    private ITryInformationContract.View view;
    private ITryInformationContract.Model model;

    public ITryInformationPresenter(ITryInformationContract.View view) {
        this.view = view;
        this.model = new ITryInformationModel();
    }

    @Override
    public void getTryInfo(Map map) {
        view.showLoadingDialog();
        model.getTryInfo(map, new ITryInformationContract.Model.ITryInfomationCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ITryInformationPresenter", "success(ITryInformationPresenter.java:40)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        if (!infoJson.getString("success").equals("沒有数据")){
                            HRSendInfor hrSendInfor = new Gson().fromJson(infoJson.getString("success"), HRSendInfor.class);
                            view.setTryInfo(hrSendInfor);
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
        //view.setTryInfo(new Object());
    }

    @Override
    public void applyTryInfo(Map map) {
        view.showLoadingDialog();
        model.applyTryInfo(map, new ITryInformationContract.Model.ITryInfomationCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ITryInformationPresenter", "success(ITryInformationPresenter.java:76)" + info);
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
            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void cancelTryInfo(Map map) {

        view.showLoadingDialog();
        model.cancelTryInfo(map, new ITryInformationContract.Model.ITryInfomationCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ITryInformationPresenter", "success(ITryInformationPresenter.java:104)" + info);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}

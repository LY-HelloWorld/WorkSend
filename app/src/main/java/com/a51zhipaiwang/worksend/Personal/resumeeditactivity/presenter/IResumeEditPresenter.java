package com.a51zhipaiwang.worksend.Personal.resumeeditactivity.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.ResumeEntity;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.contract.IResumeEditContract;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.model.IResumeEditModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/25
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
public class IResumeEditPresenter implements IResumeEditContract.Presenter {

    private IResumeEditContract.View view;
    private IResumeEditContract.Model model;

    public IResumeEditPresenter(IResumeEditContract.View view) {
        this.view = view;
        this.model = new IResumeEditModel();
    }

    @Override
    public void submitResume(ResumeEntity resumeEntity) {
        view.showLoadingDialog();
        model.submitResume(resumeEntity, new IBaseCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                MyLog.e("IResumeEditPresenter", "success(IResumeEditPresenter.java:42)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        if (infoJson.getString("success").equals("操作成功")){
                            view.returnInfo("success");
                        }else {
                            view.returnInfo("error");
                        }
                    }else {
                        view.returnInfo("error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.returnInfo("error");
                }
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();
                view.returnInfo("error");
            }
        });
    }

    @Override
    public void getResume(Map map) {
        view.showLoadingDialog();
        model.getResume(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IResumeEditPresenter", "success(IResumeEditPresenter.java:82)" + info);
                view.closeLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        ResumeEntity resumeEntity = new Gson().fromJson(jsonObject.getString("info"), ResumeEntity.class);
                        view.setResume(resumeEntity);
                    }else {
                        view.setResume(new ResumeEntity());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();

            }
        });
    }
}

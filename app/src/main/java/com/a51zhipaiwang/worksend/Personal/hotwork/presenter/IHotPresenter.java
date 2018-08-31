package com.a51zhipaiwang.worksend.Personal.hotwork.presenter;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Personal.entity.HotWork;
import com.a51zhipaiwang.worksend.Personal.entity.TakeWork;
import com.a51zhipaiwang.worksend.Personal.hotwork.contract.IHotContract;
import com.a51zhipaiwang.worksend.Personal.hotwork.model.IHotModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.google.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
public class IHotPresenter implements IHotContract.Presenter {

    private IHotContract.View view;
    private IHotContract.Model model;

    public IHotPresenter(IHotContract.View view) {
        this.view = view;
        this.model = new IHotModel();
    }

    @Override
    public void getHotWorkListAndTakeWork(Map map) {
        view.showLoadingDialog();

        model.getHotWorkListAndTakeWork(map, new IHotContract.Model.IHotCallBack() {
            @Override
            public void success(String success) {
                try {
                    MyLog.e("IHotPresenter", "success(IHotPresenter.java:52)" + success);
                    JSONObject jsonObject = new JSONObject(success);
                    if (jsonObject.getString("code").equals("success")){
                        String info = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        ArrayList rm = new Gson().fromJson(infoJson.getString("rm"), new TypeToken<ArrayList<HotWork>>(){}.getType());
                        ArrayList dy = new Gson().fromJson(infoJson.getString("dy"), new TypeToken<ArrayList<TakeWork>>(){}.getType());
                        view.setHotWorkListAndTakeWork(rm, dy);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                ToastUtil.showToastTwo("请检查您的网络连接！");
            }
        });
    }

    @Override
    public void takeWork(Map map) {
        model.takeWork(map, new IHotContract.Model.IHotCallBack() {
            @Override
            public void success(String success) {
                MyLog.e("IHotPresenter", "success(IHotPresenter.java:76)" + success);
            }

            @Override
            public void error(String error) {
MyLog.e("IHotPresenter", "error(IHotPresenter.java:82)" + error);
            }
        });
    }

    @Override
    public void cancelTakeWork(Map map) {
        model.cancelTakeWork(map, new IHotContract.Model.IHotCallBack() {
            @Override
            public void success(String success) {
                MyLog.e("IHotPresenter", "success(IHotPresenter.java:76)" + success);
            }

            @Override
            public void error(String error) {
                MyLog.e("IHotPresenter", "error(IHotPresenter.java:82)" + error);
            }
        });
    }
}

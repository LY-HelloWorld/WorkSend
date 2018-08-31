package com.a51zhipaiwang.worksend.Personal.searchactivity.presenter;

import com.a51zhipaiwang.worksend.Personal.entity.HistorySearch;
import com.a51zhipaiwang.worksend.Personal.searchactivity.contract.ISearchContract;
import com.a51zhipaiwang.worksend.Personal.searchactivity.model.ISearchModel;
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
public class ISearchPresenter implements ISearchContract.Presenter {

    private ISearchContract.View view;
    private ISearchContract.Model model;

    public ISearchPresenter(ISearchContract.View view) {
        this.view = view;
        this.model = new ISearchModel();
    }

    @Override
    public void getHistorySearchWork(Map map) {
        view.showLoadingDialog();
        model.getHistorySearchWork(map, new ISearchContract.Model.SearchCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    MyLog.e("ISearchPresenter", "success(ISearchPresenter.java:52)" + info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        if (infoJson.getString("success").equals("当前没有搜索记录")){
                            view.setHistorySearchWork(new ArrayList());
                        }else {
                            ArrayList arrayList = new Gson().fromJson(infoJson.getString("success"), new TypeToken<ArrayList<HistorySearch>>(){}.getType());
                            view.setHistorySearchWork(arrayList);
                        }
                    }else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        });
    }

    @Override
    public void getHotSearchWork(Map map) {
        view.showLoadingDialog();
        model.getHistorySearchWork(map, new ISearchContract.Model.SearchCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                view.setHotSearchWork(new ArrayList());
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        });
    }

    @Override
    public void deleteHistorySearch(Map map) {
        view.showLoadingDialog();
        model.deleteHistorySearch(map, new ISearchContract.Model.SearchCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ISearchPresenter", "success(ISearchPresenter.java:100)" + info);
                view.closeLoadingDialog();
                view.deleteHistorySearchWork(true);
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        });
    }
}

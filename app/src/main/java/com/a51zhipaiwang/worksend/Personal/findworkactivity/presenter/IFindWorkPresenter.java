package com.a51zhipaiwang.worksend.Personal.findworkactivity.presenter;

import android.databinding.ObservableArrayList;

import com.a51zhipaiwang.worksend.Personal.entity.CommendWork;
import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.Personal.entity.TakeWork;
import com.a51zhipaiwang.worksend.Personal.findworkactivity.contract.IFindWorkContract;
import com.a51zhipaiwang.worksend.Personal.findworkactivity.model.IFindWorkModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/23
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
public class IFindWorkPresenter implements IFindWorkContract.Presenter {

    private IFindWorkContract.Model findWorkModel;
    private IFindWorkContract.View findWorkView;

    public IFindWorkPresenter(IFindWorkContract.View findWorkView) {
        this.findWorkView = findWorkView;
        this.findWorkModel = new IFindWorkModel();
    }

    @Override
    public void getWorkList(Map map, final int type) {
        findWorkView.showLoadingDialog();
        findWorkModel.getWorkList(map, new IFindWorkContract.Model.FindWorkCallBack() {
            @Override
            public void success(String response) {
                findWorkView.closeLoadingDialog();
                MyLog.e("IFindWorkPresenter", "success(IFindWorkPresenter.java:50)" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("code").equals("success")){
                        String info = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        ArrayList arrayList = new Gson().fromJson(infoJson.getString("success"), new TypeToken<ArrayList<FindWork>>(){}.getType());
                        findWorkView.setWorkList(arrayList, type);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToastTwo("暂时没有符合的职位了");
                }
            }

            @Override
            public void error(String errMessage) {
                findWorkView.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        });
    }

    @Override
    public void getRecommend(Map map) {
        findWorkView.showLoadingDialog();
        findWorkModel.getRecommend(map, new IFindWorkContract.Model.FindWorkCallBack() {
            @Override
            public void success(String response) {
                findWorkView.closeLoadingDialog();
                MyLog.e("IFindWorkPresenter", "success(IFindWorkPresenter.java:83)" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("code").equals("success")){
                        String info = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        ArrayList tpArray = new Gson().fromJson(infoJson.getString("tp"), new TypeToken<ArrayList<CommendWork>>(){}.getType());
                        findWorkView.setRecommend(tpArray);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToastTwo("没有更多热门职位了");
                }
            }

            @Override
            public void error(String errMessage) {
                findWorkView.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        });
    }

    @Override
    public void getTakeWork(Map map) {
        findWorkView.showLoadingDialog();
        findWorkModel.getTakeWork(map, new IFindWorkContract.Model.FindWorkCallBack() {
            @Override
            public void success(String response) {
                findWorkView.closeLoadingDialog();
                try {
                    MyLog.e("IFindWorkPresenter", "success(IFindWorkPresenter.java:87)" + response);
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("code").equals("success")){
                        String info = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(info);
                        ArrayList<TakeWork> dy = new Gson().fromJson(infoJson.getString("success"), new TypeToken<ArrayList<TakeWork>>(){}.getType());
                        findWorkView.setTakeWork(dy);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String errMessage) {
                findWorkView.closeLoadingDialog();
                ToastUtil.showToastTwo("请检查您的网络连接!");
            }
        });
    }

    @Override
    public void takeWork(Map map) {
        findWorkView.showLoadingDialog();
        findWorkModel.takeWork(map, new IFindWorkContract.Model.FindWorkCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IFindWorkPresenter", "success(IFindWorkPresenter.java:113)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        findWorkView.takeWorkReturn(true);
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
    public void deleteTakeWork(Map map) {
        findWorkView.showLoadingDialog();
        findWorkModel.deleteTakeWork(map, new IFindWorkContract.Model.FindWorkCallBack() {
            @Override
            public void success(String info) {
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        JSONObject infoJson = new JSONObject(jsonObject.getString("info"));
                        if (infoJson.getString("success").equals("删除成功")){
                            findWorkView.deleteWorkReturn(true);
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
}

package com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.contract.IResumeContract;
import com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.model.IResumeModel;
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
 *     time   : 2018/07/27
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
public class IResumePresenter implements IResumeContract.Presenter {

    private IResumeContract.View view;
    private IResumeContract.Model model;

    public IResumePresenter(IResumeContract.View view) {
        this.view = view;
        this.model = new IResumeModel();
    }

    @Override
    public void getResumeList(Map map) {
        view.showLoadingDialog();
        model.getResumeList(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IResumePresenter", "success(IResumePresenter.java:42)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        ArrayList<FindWork> arrayList = new Gson().fromJson(infoJson.getString("success"), new TypeToken<ArrayList<FindWork>>(){}.getType());
                        view.setResumeList(arrayList);
                    }else {
                        ToastUtil.showToastTwo("没有当前搜索职位");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    MyLog.e("IResumePresenter", "success(IResumePresenter.java:63)" + e.getMessage());
                }
            }

            @Override
            public void error(String error) {
                MyLog.e("IResumePresenter", "error(IResumePresenter.java:68)" + error);
            }
        });
        /*ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        view.setResumeList(arrayList);*/
    }
}

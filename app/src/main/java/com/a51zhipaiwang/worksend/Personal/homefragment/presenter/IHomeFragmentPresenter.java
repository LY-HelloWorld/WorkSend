package com.a51zhipaiwang.worksend.Personal.homefragment.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.Information;
import com.a51zhipaiwang.worksend.Personal.homefragment.contract.IHomeFragmentContract;
import com.a51zhipaiwang.worksend.Personal.homefragment.model.IHomeFragmentModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
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
public class IHomeFragmentPresenter implements IHomeFragmentContract.Presenter {
    
    private IHomeFragmentContract.View view;
    private IHomeFragmentContract.Model model;

    public IHomeFragmentPresenter(IHomeFragmentContract.View view) {
        this.view = view;
        this.model = new IHomeFragmentModel();
    }

    @Override
    public void getInfoList(Map map) {
        view.showLoadingDialog();
        model.getInfoList(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                try {
                    MyLog.e("IHomeFragmentPresenter", "success(IHomeFragmentPresenter.java:49)" + info);
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String content = jsonObject.getString("info");
                        ArrayList arrayList = new Gson().fromJson(content, new TypeToken<ArrayList<Information>>(){}.getType());
                        view.setInfoList(arrayList);
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
    public void subResume(Map map) {
        view.showLoadingDialog();
        model.subResume(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IHomeFragmentPresenter", "success(IHomeFragmentPresenter.java:77)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        if (infoJson.getString("success").equals("020")){
                            view.returnSubResume(true);
                        }else {
                            view.returnSubResume(false);
                        }
                    }else {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
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

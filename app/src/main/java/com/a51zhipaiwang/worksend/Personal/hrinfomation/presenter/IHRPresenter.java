package com.a51zhipaiwang.worksend.Personal.hrinfomation.presenter;

import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.hrinfomation.contract.IHRContract;
import com.a51zhipaiwang.worksend.Personal.hrinfomation.model.IHRModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
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
public class IHRPresenter implements IHRContract.Presenter {

    private IHRContract.View view;
    private IHRContract.Model model;

    public IHRPresenter(IHRContract.View view) {
        this.view = view;
        this.model = new IHRModel();
    }

    @Override
    public void getHRInfomation(Map map) {
        view.showLoadingDialog();
        model.getHRInfomation(map, new IHRContract.Model.HRInfomationCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("IHRPresenter", "success(IHRPresenter.java:41)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        String success = infoJson.getString("success");
                        ArrayList arrayList = new Gson().fromJson(success, new TypeToken<ArrayList<Infor>>(){}.getType());
                        view.setHRInfomation(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                MyLog.e("IHRPresenter", "error(IHRPresenter.java:47)" + error);
            }
        });
    }

    @Override
    public void deleteHRInfomation(Map map) {

        view.showLoadingDialog();
        model.deleteHRInfomation(map, new IHRContract.Model.HRInfomationCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                MyLog.e("IHRPresenter", "success(IHRPresenter.java:78)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        view.deleteReturnInfo(infoJson.getString("id"));
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

package com.a51zhipaiwang.worksend.Personal.systeminformationactivity.presenter;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.systeminformationactivity.contract.ISystemInformationActivityContract;
import com.a51zhipaiwang.worksend.Personal.systeminformationactivity.model.ISystemInformationActivityModel;
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
public class ISystemInformationActivityPresenter implements ISystemInformationActivityContract.Presenter {

    private ISystemInformationActivityContract.View view;
    private ISystemInformationActivityContract.Model model;

    public ISystemInformationActivityPresenter(ISystemInformationActivityContract.View view) {
        this.view = view;
        this.model = new ISystemInformationActivityModel();
    }

    @Override
    public void getSystemInfomation(Map map) {
        view.showLoadingDialog();
        model.getSystemInfomation(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ISystemInformationActivityPresenter", "success(ISystemInformationActivityPresenter.java:44)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        String success = infoJson.getString("success");
                        ArrayList arrayList = new Gson().fromJson(success, new TypeToken<ArrayList<Infor>>(){}.getType());
                        view.setSystemInformation(arrayList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                MyLog.e("IHRPresenter", "error(IHRPresenter.java:47)" + error);
            }
        });
    }
}

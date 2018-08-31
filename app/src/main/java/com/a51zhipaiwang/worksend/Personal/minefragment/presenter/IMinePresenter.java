package com.a51zhipaiwang.worksend.Personal.minefragment.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.UserInfo;
import com.a51zhipaiwang.worksend.Personal.minefragment.contract.IMineContract;
import com.a51zhipaiwang.worksend.Personal.minefragment.model.IMineModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/28
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
public class IMinePresenter implements IMineContract.Presenter {

    private IMineContract.View view;
    private IMineContract.Model model;

    public IMinePresenter(IMineContract.View view) {
        this.view = view;
        this.model = new IMineModel();
    }

    @Override
    public void getUserInfo(Map map) {
        view.showLoadingDialog();
        model.getUserInfo(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        UserInfo userInfo = new Gson().fromJson(sInfo, UserInfo.class);
                        view.setUserInfo(userInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyLog.e("IMinePresenter", "success(IMinePresenter.java:41)" + info);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}

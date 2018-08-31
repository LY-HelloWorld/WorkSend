package com.a51zhipaiwang.worksend.Personal.userinfoactivity.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.UserInfo;
import com.a51zhipaiwang.worksend.Personal.userinfoactivity.model.UserInfoModelImpl;
import com.a51zhipaiwang.worksend.Personal.userinfoactivity.view.IUserInfoView;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
public class UserInfoPresenterImpl implements IUserInfoPresenter {


    private IUserInfoView iUserInfoView;
    private worksend.a51zhipaiwang.com.worksend.Personal.userinfoactivity.model.IUserInfoModel iUserInfoModel;

    public UserInfoPresenterImpl(IUserInfoView iUserInfoView) {
        this.iUserInfoView = iUserInfoView;
        this.iUserInfoModel = new UserInfoModelImpl();
    }

    @Override
    public void submitUserInfo(Object object) {

    }

    @Override
    public void getUserInfo(Map map) {
        iUserInfoView.showLoadingDialog();
        iUserInfoModel.getUserInfo(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("UserInfoPresenterImpl", "success(UserInfoPresenterImpl.java:47)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        UserInfo userInfo = new Gson().fromJson(sInfo, UserInfo.class);
                        iUserInfoView.setUserInfo(userInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                MyLog.e("UserInfoPresenterImpl", "error(UserInfoPresenterImpl.java:53)" + error);
            }
        });
    }
}

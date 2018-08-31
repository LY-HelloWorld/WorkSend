package com.a51zhipaiwang.worksend.Personal.registeractivity.presenter;

import android.text.TextUtils;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.registeractivity.contract.RegisterContract;
import com.a51zhipaiwang.worksend.Personal.registeractivity.model.RegisterModel;
import com.a51zhipaiwang.worksend.Utils.CheckUserInfo;

import java.util.HashMap;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/30
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
public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.Model model;
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.model = new RegisterModel();
    }

    @Override
    public boolean checkUserInfo() {
        if (!checkPhone(view.getPhoneNum())){
            view.showTip(1);
            return false;
        }
        String inputVerifyCode = view.getVerifyCode();
        if (TextUtils.isEmpty(inputVerifyCode) || inputVerifyCode.length() != 4){
            view.showTip(2);
            return false;
        }
        if (!CheckUserInfo.CheckPassWord(view.getPassWord())){
            view.showTip(3);
            return false;
        }
        if (!view.getPassWord().equals(view.getRePassWord())){
            view.showTip(4);
            return false;
        }
        return true;
    }

    @Override
    public void register() {
        if (checkUserInfo()){
            view.clearTip();
            view.showLoadingDialog();
            HashMap map = new HashMap();
            model.register(map, new IBaseCallBack() {
                @Override
                public void success(String info) {
                    view.closeLoadingDialog();
                    view.registerSuccess();
                }

                @Override
                public void error(String error) {
                    view.closeLoadingDialog();
                    view.registerError("");
                }
            });
        }
    }

    @Override
    public void getVerfifyCode() {
        if (checkPhone(view.getPhoneNum())){
            view.clearTip();
            view.showLoadingDialog();
            HashMap hashMap = new HashMap();
            model.getVerifiyCode(hashMap, new IBaseCallBack() {
                @Override
                public void success(String info) {
                    view.closeLoadingDialog();
                    view.startTimeing();
                }

                @Override
                public void error(String error) {
                    view.closeLoadingDialog();
                    view.registerError("获取验证码失败");
                }
            });
        }
    }

    public boolean checkPhone(String phone){
        return CheckUserInfo.CheckTelNum(phone);
    }

}

package com.a51zhipaiwang.worksend.Personal.registeractivity.contract;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;

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
public interface RegisterContract {
    interface Model {

        public void getVerifiyCode(HashMap map, IBaseCallBack iBaseCallBack);

        public void register(HashMap map, IBaseCallBack iBaseCallBack);

    }

    interface View extends IBaseActivityView{

        public void startTimeing();

        public void registerSuccess();

        public void registerError(String errorInfo);

        public String getPhoneNum();

        public String getVerifyCode();

        public String getPassWord();

        public String getRePassWord();

        /**
         * 展示 输入错误的提示
         * @param type 1 账号错误， 2 验证码错误， 3 密码错误， 4 两次密码不同
         */
        public void showTip(int type);

        public void clearTip();


    }

    interface Presenter {

        public boolean checkUserInfo();

        public void register();

        public void getVerfifyCode();

    }
}

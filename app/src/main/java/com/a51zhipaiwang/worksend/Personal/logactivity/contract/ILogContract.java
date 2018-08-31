package com.a51zhipaiwang.worksend.Personal.logactivity.contract;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;

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
public interface ILogContract {
    interface Model {
        public void getSecriCode(Map map, IBaseCallBack iBaseCallBack);

        public void log(Map map, IBaseCallBack iBaseCallBack);

    }

    interface View extends IBaseActivityView {

        public void getSercircodeReturnInfo(boolean returnInfo);

        public void logSuccess(boolean returnLog);

    }

    interface Presenter {
        public void getSecriCode(Map map);

        public void log(Map map);
    }
}

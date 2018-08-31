package com.a51zhipaiwang.worksend.Personal.tryinformationactivity.contract;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;

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
public interface ITryInformationContract {
    interface Model {

        public void getTryInfo(Map map, ITryInfomationCallBack iTryInfomationCallBack);

        public void applyTryInfo(Map map, ITryInfomationCallBack iTryInfomationCallBack);

        public void cancelTryInfo(Map map, ITryInfomationCallBack iTryInfomationCallBack);

        public interface ITryInfomationCallBack extends IBaseCallBack {

        }

    }

    interface View extends IBaseActivityView {

        public void setTryInfo(Object object);

    }

    interface Presenter {

        public void getTryInfo(Map map);

        public void applyTryInfo(Map map);

        public void cancelTryInfo(Map map);
    }
}

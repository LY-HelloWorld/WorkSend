package com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity.contract;

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
public interface IHRInformationApplyContract {
    interface Model {
        public void getWorkInfo(Map map, IHRInformationApplyCallBack ihrInformationApplyCallBack);

        public void applyWork(Map map, IHRInformationApplyCallBack ihrInformationApplyCallBack);

        public void cancel(Map map, IHRInformationApplyCallBack ihrInformationApplyCallBack);

        public interface IHRInformationApplyCallBack extends IBaseCallBack {
        }
    }

    interface View<T> extends IBaseActivityView {
        public void setWorkInfo(T workInfo);

    }

    interface Presenter {

        public void getWorkInfo(Map map);

        public void applyWork(Map map);

        public void cancel(Map map);

    }
}

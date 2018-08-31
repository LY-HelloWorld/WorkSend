package com.a51zhipaiwang.worksend.Personal.resumeeditactivity.contract;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.ResumeEntity;

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
public interface IResumeEditContract {
    interface Model {

        public void submitResume(ResumeEntity resumeEntity, IBaseCallBack iBaseCallBack);

        public void getResume(Map map, IBaseCallBack iBaseCallBack);

    }

    interface View extends IBaseActivityView {

        public void setResume(ResumeEntity resume);

        public void returnInfo(String string);

    }

    interface Presenter {

        public void submitResume(ResumeEntity resumeEntity);
        public void getResume(Map map);
    }
}

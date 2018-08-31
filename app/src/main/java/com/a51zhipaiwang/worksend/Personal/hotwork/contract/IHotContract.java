package com.a51zhipaiwang.worksend.Personal.hotwork.contract;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.entity.HotWork;
import com.a51zhipaiwang.worksend.Personal.entity.TakeWork;

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
public interface IHotContract {
    interface Model {

        public void getHotWorkListAndTakeWork(Map map, IHotCallBack iHotCallBack);

        public void takeWork(Map map, IHotCallBack iHotCallBack);

        public void cancelTakeWork(Map map, IHotCallBack iHotCallBack);

        interface IHotCallBack{
            public void success(String success);

            public void error(String error);
        }
    }

    interface View extends IBaseActivityView {

        public void setHotWorkListAndTakeWork(ArrayList<HotWork> hotWorkList, ArrayList<TakeWork> takeWork);

    }

    interface Presenter {

        public void getHotWorkListAndTakeWork(Map map);

        public void takeWork(Map map);

        public void cancelTakeWork(Map map);

    }
}

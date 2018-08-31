package com.a51zhipaiwang.worksend.Personal.trypathfragment.contract;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.android.volley.Response;

import java.util.ArrayList;
import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/20
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
public interface ITryPathContract {
    interface Model {
        //获取试岗中列表
        public void getTryWorking(Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener);
        //获取试岗完成列表
        public void getTryOutWork(Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener);
    }

    interface View extends IBaseActivityView {

        public void setTryWorking(ArrayList tryWorkingList);

        public void setTryOutWork(ArrayList tryOutWorkList);

        public void setReturnInfo(String info);
    }

    interface Presenter {

        public void getTryWorking(Map paramMap);

        public void getTryOutWork(Map paramMap);


    }
}

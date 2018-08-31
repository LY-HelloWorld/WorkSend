package com.a51zhipaiwang.worksend.Personal.findworkactivity.contract;

import android.databinding.ObservableArrayList;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.TakeWork;
import com.android.volley.Response;

import java.util.ArrayList;
import java.util.Map;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/23
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
public interface IFindWorkContract {
    interface Model {

        public void getWorkList(Map map, FindWorkCallBack findWorkCallBack);

        public void getRecommend(Map map, FindWorkCallBack findWorkCallBack);

        public void getTakeWork(Map map, FindWorkCallBack findWorkCallBack);

        public void takeWork(Map map, FindWorkCallBack findWorkCallBack);

        public void deleteTakeWork(Map map, FindWorkCallBack findWorkCallBack);

        public interface FindWorkCallBack extends IBaseCallBack {
        }

    }

    interface View extends IBaseActivityView {

        public final int REMOVE_ADD = 1;
        public final int ADD = 2;

        public void setWorkList(ArrayList workList, int type);

        public void setRecommend(ArrayList recommendWorkList);

        public void setTakeWork(ArrayList takeWorkList);

        public void takeWorkReturn(boolean takeWorkReturn);

        public void deleteWorkReturn(boolean deleteTakeReturn);

    }

    interface Presenter {

        /**
         * 数据返回
         * @param map
         * @param type 如果是1 则先move再添加 2则添加
         */
        public void getWorkList(Map map, int type);

        public void getRecommend(Map map);

        public void getTakeWork(Map map);

        public void takeWork(Map map);

        public void deleteTakeWork(Map map);

    }
}

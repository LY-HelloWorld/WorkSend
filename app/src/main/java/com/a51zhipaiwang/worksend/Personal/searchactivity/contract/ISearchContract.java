package com.a51zhipaiwang.worksend.Personal.searchactivity.contract;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;

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
public interface ISearchContract {
    interface Model {

        public void getHistorySearchWork(Map map, SearchCallBack searchCallBack);

        public void getHotSearchWork(Map map, SearchCallBack searchCallBack);

        public void deleteHistorySearch(Map map, SearchCallBack searchCallBack);


        public interface SearchCallBack extends IBaseCallBack {

        }

    }

    interface View extends IBaseActivityView {

        public void setHistorySearchWork(ArrayList historySearchWorkList);

        public void setHotSearchWork(ArrayList searchWorkList);

        public void searchWork(String workName);

        public void deleteHistorySearchWork(boolean delete);


    }

    interface Presenter {

        public void getHistorySearchWork(Map map);

        public void getHotSearchWork(Map map);

        public void deleteHistorySearch(Map map);

    }
}

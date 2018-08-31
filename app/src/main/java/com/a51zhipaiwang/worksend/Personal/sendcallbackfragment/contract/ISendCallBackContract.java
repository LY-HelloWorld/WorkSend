package com.a51zhipaiwang.worksend.Personal.sendcallbackfragment.contract;

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
public interface ISendCallBackContract {
    interface Model {

        public void getCallBackInfoList(Map map, IBaseCallBack iBaseCallBack);

        public void deleteCallBackItem(Map map, IBaseCallBack iBaseCallBack);
    }

    interface View extends IBaseActivityView {

        public void setCallBackList(Object object);

        public void setDeleteReturn(boolean bDeleteReturn);

    }

    interface Presenter {

        public void getCallBackInfoList(Map map);

        public void deleteCallBackItem(Map map);
    }
}

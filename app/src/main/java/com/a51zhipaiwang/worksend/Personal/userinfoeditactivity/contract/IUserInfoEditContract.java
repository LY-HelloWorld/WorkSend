package com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.contract;

import android.content.Context;
import android.location.Address;
import android.net.Uri;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;
import com.android.volley.Response;

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
public interface IUserInfoEditContract {
    interface Model {
        //提交用户信息 网络请求
        public void submitUserInfo(Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener);

        //上传用户头像图片
        public void upLoadUserImage(Context context, Uri uri, UpLoadFile.UpLoadFileListener upLoadFileListener);
    }

    interface View extends IBaseActivityView {

        //提交用户信息 反馈
        public void submitReturnInfo(boolean bReturnInfo);

        //文字转化为经纬度 反馈
        public void setLocationFromText(Address address);

        //上传用户头像反馈
        public void setUpLoadImageReturnInfo(String sReturnInfo);

    }

    interface Presenter {

        //提交用户信息
        public void submitUserInfo(Map paramMap);

        //文字转化为经纬度
        public void coverLocationFromText(String sLocation);

        //上传图片
        public void upLoadUserImage(Uri uri);

    }
}

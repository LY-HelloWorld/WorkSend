package com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.model;

import android.content.Context;
import android.net.Uri;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.contract.IUserInfoEditContract;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;
import com.android.volley.Request;
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
public class IUserInfoEditModel implements IUserInfoEditContract.Model {
    @Override
    public void submitUserInfo(Map paramMap, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userInformationTable/insUserInformationTable.do",
                paramMap,
                listener,
                errorListener));
    }

    @Override
    public void upLoadUserImage(Context context, Uri uri, UpLoadFile.UpLoadFileListener upLoadFileListener) {
        UpLoadFile.UpLoadFileResult(context, uri, upLoadFileListener);
    }
}

package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseLogActivityModel;

import com.android.volley.Response;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
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
public interface ILogActivityModel {

    public void userLogIn(Map map, Response.Listener<String> listener, Response.ErrorListener errorListener);

}

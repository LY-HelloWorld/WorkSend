package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseHomeFragment;

import com.android.volley.Response;

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
public interface IHomeFragmentModel {
    public void getHomeJianLiList(final Map paramNameAndParmas, Response.ErrorListener errorListener, Response.Listener<String> listener);
}

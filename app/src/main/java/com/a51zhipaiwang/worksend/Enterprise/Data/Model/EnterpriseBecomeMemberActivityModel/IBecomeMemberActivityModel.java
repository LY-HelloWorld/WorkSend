package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBecomeMemberActivityModel;

import com.android.volley.Response;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   : 成为会员model 接口
 *     version: 1.0
 * </pre>
 */
public interface IBecomeMemberActivityModel {

    public void queryMember(Map map, Response.ErrorListener errorListener, Response.Listener listener);

}

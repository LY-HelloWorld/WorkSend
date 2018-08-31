package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseMoneyActivityModel;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   : 充值model接口
 *     version: 1.0
 * </pre>
 */
public interface IMoneyActivityModel {

    public void getMoney(Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener, int type);

    public void rechargeMoney(Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener, int type);

    public void withDraw(Map paramMap, Response.ErrorListener errorListener, Response.Listener<String> listener, int type);


}

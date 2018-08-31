package com.a51zhipaiwang.worksend.Utils;

import android.content.Context;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/06
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
public class RechargeUtil {

    private IWXAPI iwxapi;

    public RechargeUtil(Context context, String appId) {
        iwxapi = WXAPIFactory.createWXAPI(context, null);
        iwxapi.registerApp(appId);
    }


    /**
     * @param hashMap
     * @param iGetOrderReturn
     * @param type            1 是企业端 2是个人段
     */
    public void getWeiXinPayOrder(Map hashMap, final IGetOrderReturn iGetOrderReturn, int type) {
        StringRequest stringRequest;
        if (type == 1) {
            stringRequest = new EnterpriseStringRequest(Request.Method.POST,
                    MyApplication.path + "api/wxpay/requestPay.do",
                    hashMap,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                MyLog.e("RechargeUtil", "onResponse(RechargeUtil.java:55)" + response);
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject desc = new JSONObject(jsonObject.getString("desc"));
                                pay(desc.getString("appid"),
                                        desc.getString("package"),
                                        desc.getString("prepayid"),
                                        desc.getString("noncestr"),
                                        desc.getString("timestamp"),
                                        desc.getString("sign"),
                                        desc.getString("partnerid"));
                            } catch (JSONException e) {
                                iGetOrderReturn.error("请检查网络连接");
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            MyLog.e("RechargeUtil", "onErrorResponse(RechargeUtil.java:74)" + error.getMessage());
                            iGetOrderReturn.error("请检查网络连接");
                        }
                    });
        } else {
            stringRequest = new PersonalStringRequest(Request.Method.POST,
                    MyApplication.path + "api/wxpay/requestPay.do",
                    hashMap,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                MyLog.e("RechargeUtil", "onResponse(RechargeUtil.java:55)" + response);
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject desc = new JSONObject(jsonObject.getString("desc"));
                                pay(desc.getString("appid"),
                                        desc.getString("package"),
                                        desc.getString("prepayid"),
                                        desc.getString("noncestr"),
                                        desc.getString("timestamp"),
                                        desc.getString("sign"),
                                        desc.getString("partnerid"));
                            } catch (JSONException e) {
                                iGetOrderReturn.error("请检查网络连接");
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            MyLog.e("RechargeUtil", "onErrorResponse(RechargeUtil.java:74)" + error.getMessage());
                            iGetOrderReturn.error("请检查网络连接");
                        }
                    });
        }
        MyApplication.requestQueue.add(stringRequest);
    }


    private void pay(String appId, String packageValue, String prepayId, String nonceStr, String timeStamp, String sign, String partnerid) {

        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerid;
        request.prepayId = prepayId;
        request.packageValue = packageValue;
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;
        //iwxapi.sendReq(request);
        MyLog.e("RechargeUtil", "pay(RechargeUtil.java:91)" + iwxapi.sendReq(request));
    }

    public interface IGetOrderReturn {
        public void error(String message);
    }


}

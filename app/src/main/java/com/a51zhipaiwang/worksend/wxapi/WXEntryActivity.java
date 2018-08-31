package com.a51zhipaiwang.worksend.wxapi;

import android.os.Bundle;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.homeactivity.PersonalHomeActivity;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(MyApplication.APP_ID);
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {

                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        ToastUtil.showToastTwo(baseResp.errCode + "");
        //MyLog.e("WXEntryActivity", "onResp(WXEntryActivity.java:53)" + baseResp.getType());
        String result;
        switch (baseResp.getType()) {
            case ConstantsAPI.COMMAND_SENDAUTH:
                switch (baseResp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        MyApplication.requestQueue.add(new StringRequest(Request.Method.GET,
                                "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                                        + MyApplication.APP_ID
                                        + "&secret="
                                        + MyApplication.AppSecret
                                        + "&code=" + ((SendAuth.Resp)baseResp).code
                                        + "&grant_type=authorization_code",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        MyLog.e("WXEntryActivity", "onResponse(WXEntryActivity.java:86)" + response);
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            logFromWeiXin(jsonObject.getString("openid"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            ToastUtil.showToastTwo("登录失败");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ToastUtil.showToastTwo("登录失败");
                            }
                        }));
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        result = "发送取消";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                        result = "发送被拒绝";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                    default:
                        result = "发送返回";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                }
                break;
            //分享
            case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                switch (baseResp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        result = "分享成功";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        result = "取消分享";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_SENT_FAILED:
                        result = "分享失败";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                    default:
                        result = "未知原因";
                        ToastUtil.showToastTwo(result);
                        finish();
                        break;
                }
                break;
            case ConstantsAPI.COMMAND_PAY_BY_WX:
                int errCode = baseResp.errCode;
                if (errCode == -1) {
                    ToastUtil.showToastTwo(baseResp.errStr);
                    finish();
                } else if (errCode == 0) {
                    ToastUtil.showToastTwo("支付完成");
                    this.finish();
                } else {
                    ToastUtil.showToastTwo("支付失败");
                    finish();
                }
                break;
        }
    }


    private void logFromWeiXin(String openId){
        HashMap hashMap = new HashMap();
        hashMap.put("openid", openId);
        MyApplication.requestQueue.add(new PersonalStringRequest(Request.Method.POST,
                MyApplication.path + "api/userLogin/userWeChatLogin.do",
                hashMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            MyLog.e("WXEntryActivity", "onResponse(WXEntryActivity.java:161)" + response);
                            if (jsonObject.getString("code").equals("success")){
                                String info = jsonObject.getString("info");
                                JSONObject infoJson = new JSONObject(info);
                                if (infoJson.getString("success").equals("010")){
                                    ToastUtil.showToastTwo("登录成功");
                                    SharedPreferencesUtil.saveSharedPreference("tokenPersonal", infoJson.getString("token"), MyApplication.userInfo, WXEntryActivity.this);
                                    MyApplication.tokenPersonal = infoJson.getString("token");
                                    PersonalHomeActivity.startPersonalHomeAcitivty(WXEntryActivity.this );
                                }else {
                                    ToastUtil.showToastTwo("微信登录失败！请检查您的网络连接");
                                }
                                WXEntryActivity.this.finish();
                            }else {
                                ToastUtil.showToastTwo("微信登录失败！请检查您的网络连接");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtil.showToastTwo("微信登录失败！请检查您的网络连接");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.showToastTwo("微信登录失败！请检查您的网络连接");
                    }
                }));
    }

}

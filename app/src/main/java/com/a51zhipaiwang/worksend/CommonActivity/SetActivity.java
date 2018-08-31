package com.a51zhipaiwang.worksend.CommonActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.aboutus.AboutUsActivity;
import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.LogChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.InfomationItemFragment;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.Util;
import com.a51zhipaiwang.worksend.Utils.WXShare;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class SetActivity extends BaseActivity {

    public static final int ENTERPRISE = 1;
    public static final int PERSONAL = 2;

    private int type;

    private TextView tilte_text;
    private ImageView return_image;
    private Button logOutBt;
    private LinearLayout aboutUsLayout;
    private LinearLayout tuiJianLayout;
    private LinearLayout haoPingLayout;

    private SetActivityClickListener setActivityClickListener;

    private IWXAPI api;


    public static void startSetActivity(Context context, int type) {
        Intent intent = new Intent(context, SetActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);/*
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID,false);
        //将应用的appid注册到微信
        api.registerApp(MyApplication.APP_ID);*/
        init();
        setRegister();
    }

    private void init() {
        type = getIntent().getIntExtra("type", 0);

        setActivityClickListener = new SetActivityClickListener();

        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);

        logOutBt = (Button) findViewById(R.id.logOutBt);
        aboutUsLayout = (LinearLayout) findViewById(R.id.aboutUsLayout);
        tuiJianLayout = (LinearLayout) findViewById(R.id.tuiJianLayout);
        haoPingLayout = (LinearLayout) findViewById(R.id.haoPingLayout);

        tilte_text.setText("设置中心");
    }

    private String buildTransaction(String type) {

        return TextUtils.isEmpty(type) ? String.valueOf(System.currentTimeMillis()) : (type + System.currentTimeMillis());
    }


    private void setRegister() {
        return_image.setOnClickListener(setActivityClickListener);
        logOutBt.setOnClickListener(setActivityClickListener);
        aboutUsLayout.setOnClickListener(setActivityClickListener);
        tuiJianLayout.setOnClickListener(setActivityClickListener);
        haoPingLayout.setOnClickListener(setActivityClickListener);
    }

    public void shareUrl(int flag, Context context, String url, String title, String descroption) {
        //初始化一个WXWebpageObject填写url
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = url;
        //用WXWebpageObject对象初始化一个WXMediaMessage，天下标题，描述
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = descroption;
        //这块需要注意，图片的像素千万不要太大，不然的话会调不起来微信分享，
        //我在做的时候和我们这的UIMM说随便给我一张图，她给了我一张1024*1024的图片
        //当时也不知道什么原因，后来在我的机智之下换了一张像素小一点的图片好了！
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_share);
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    class SetActivityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    SetActivity.this.finish();
                    break;
                case R.id.logOutBt:
                    StringRequest stringRequest;
                    if (type == PERSONAL) {
                        stringRequest = new PersonalStringRequest(Request.Method.POST,
                                MyApplication.path + "api/userLogin/cancellationLogin.do",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getString("code").equals("success")) {
                                                String info = jsonObject.getString("info");
                                                JSONObject infoJson = new JSONObject(info);
                                                if (infoJson.getString("success").equals("010")) {
                                                    ActivityCollector.finishAll();
                                                    SetActivity.this.startActivity(new Intent(SetActivity.this, LogChoiceActivity.class));
                                                    MyApplication.tokenPersonal = "";
                                                    SharedPreferencesUtil.saveSharedPreference("tokenPersonal", "", MyApplication.userInfo, MyApplication.context);
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                    } else {
                        stringRequest = new EnterpriseStringRequest(Request.Method.POST,
                                MyApplication.path + "api/userLogin/cancellationLogin.do",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getString("code").equals("success")) {
                                                String info = jsonObject.getString("info");
                                                JSONObject infoJson = new JSONObject(info);
                                                if (infoJson.getString("success").equals("010")) {
                                                    ActivityCollector.finishAll();
                                                    SetActivity.this.startActivity(new Intent(SetActivity.this, LogChoiceActivity.class));
                                                    MyApplication.tokenEnterprise = "";
                                                    SharedPreferencesUtil.saveSharedPreference("tokenEnterprise", "", MyApplication.userInfo, MyApplication.context);

                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                    }
                    MyApplication.requestQueue.add(stringRequest);
                    break;
                case R.id.aboutUsLayout:
                    AboutUsActivity.startAboutUsActivity(SetActivity.this);
                    break;
                case R.id.tuiJianLayout:
                    ToastUtil.showToastTwo("分享");
                    WXShare wxShare = new WXShare(SetActivity.this);
                    wxShare.shareUrl(0, SetActivity.this, "www.51zhipaiwang.com", "职派推荐", "面试就有钱");

                    break;
                case R.id.haoPingLayout:
                    break;
            }
        }
    }

}

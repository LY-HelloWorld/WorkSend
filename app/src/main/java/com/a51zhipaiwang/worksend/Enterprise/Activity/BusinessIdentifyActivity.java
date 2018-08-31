package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity.BusinessInfomationActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BaseFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.IdentifyFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.PaiDanFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.PayFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.ShenHeFragment;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;


public class BusinessIdentifyActivity extends BaseActivity {

    //返回按钮
    private ImageView return_image;
    //点击监听
    private BusinessIdentifyActivityClickListener businessIdentifyActivityClickListener;
    private BroadcastReceiver broadcastReceiver;

    //支付状态
    public static final String PAY_STATE = "010";
    //认证状态
    public static final String IDENTIFY_STATE = "020";
    //审核状态
    public static final String SHEN_HE_STATE = "030";
    //派单
    public static final String PAI_DAN_STATE = "040";

    private String type = "010";
    private String title;
    private BaseFragment fragment;
    private ImageView identifyImage;
    private View identifView;
    private ImageView shenHeImage;
    private View shenHeView;
    private ImageView paiDanRenZhengImage;
    private ImageView yaJinImage;
    private View yaJinView;
    private TextView tilte_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_identify);
        init();
        setRegister();
        setState(type);
        setFragment(type);
        addFramlayout();
        initRechargeBroadCastReceiver();
    }

    /**
     *
     * @param context
     * @param tpye 启动相应的显示界面
     */
    public static void startBusinessActivity(Context context, String tpye, String title){
        Intent intent = new Intent(context, BusinessIdentifyActivity.class);
        intent.putExtra("type", tpye);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UpLoadLog:
                if (resultCode == RESULT_OK){
                    UpLoadFile.UpLoadFileResult(BusinessIdentifyActivity.this, data.getData(), new UpLoadFile.UpLoadFileListener() {
                        @Override
                        public void success(String info) {
                            returnLogoSubmitSuccess(info);
                        }

                        @Override
                        public void error(String info) {
                        }
                    });
                }
                break;
        }
    }


    public void returnLogoSubmitSuccess(String info) {
        try {
            JSONObject logoJson = new JSONObject(info);
            if (logoJson.getString("result").equals("上传成功")){
                if (fragment instanceof IdentifyFragment){
                    ((IdentifyFragment) fragment).setImagepath(MyApplication.upLoadPath + logoJson.getString("lj"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        return_image = (ImageView) findViewById(R.id.return_image);
        yaJinImage = (ImageView) findViewById(R.id.yaJinImage);
        yaJinView = findViewById(R.id.yaJinView);
        identifyImage = (ImageView) findViewById(R.id.identifyImage);
        identifView = findViewById(R.id.identifView);
        shenHeImage = (ImageView) findViewById(R.id.shenHeImage);
        shenHeView = findViewById(R.id.shenHeView);
        paiDanRenZhengImage = (ImageView) findViewById(R.id.paiDanRenZhengImage);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        type = getIntent().getStringExtra("type");
        //type = PAY_STATE;
        title = getIntent().getStringExtra("title");
        tilte_text.setText(title);
        businessIdentifyActivityClickListener = new BusinessIdentifyActivityClickListener();
    }

    private void initRechargeBroadCastReceiver(){
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, final Intent intent) {
                MyLog.e("BusinessIdentifyActivity", "onReceive(BusinessIdentifyActivity.java:147)" + "onReceive");
                switch (intent.getAction()) {
                    case MyApplication.RECHARGE_SUCCESS:
                        setState(IDENTIFY_STATE);
                        setFragment(IDENTIFY_STATE);
                        addFramlayout();
                        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                                MyApplication.path + "api/prisesas/updCol.do",
                                null,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        MyLog.e("BusinessIdentifyActivity", "onResponse(BusinessIdentifyActivity.java:162)" + response);
                                        try {
                                            JSONObject responseJson = new JSONObject(response);
                                            if (responseJson.getString("code").equals("success")){
                                                Intent intent1 = new Intent();
                                                intent1.setAction(MyApplication.CHANGE_BUSINESS_FLAG);
                                                BusinessIdentifyActivity.this.sendBroadcast(intent1);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        MyLog.e("BusinessIdentifyActivity", "onErrorResponse(BusinessIdentifyActivity.java:168)" + error);
                                    }
                                }));
                        break;
                    case MyApplication.IDENTIFY_UPLOAD_SUCCESS:
                        setState(SHEN_HE_STATE);
                        setFragment(SHEN_HE_STATE);
                        addFramlayout();
                        break;
                }
                BusinessIdentifyActivity.this.sendBroadcast(new Intent(MyApplication.CHANGE_BUSINESS_FLAG));
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyApplication.RECHARGE_SUCCESS);
        intentFilter.addAction(MyApplication.IDENTIFY_UPLOAD_SUCCESS);
        this.registerReceiver(broadcastReceiver, new IntentFilter(intentFilter));
    }

    private void setRegister(){
        return_image.setOnClickListener(businessIdentifyActivityClickListener);
    }

    private void setState(String type){
        switch (type) {
            case PAI_DAN_STATE:
                paiDanRenZhengImage.setImageResource(R.mipmap.pai_dan_success);
                shenHeImage.setImageResource(R.mipmap.jin_du_2);
                shenHeView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                identifyImage.setImageResource(R.mipmap.jin_du_2);
                identifView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                identifyImage.setImageResource(R.mipmap.jin_du_2);
                identifView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                yaJinImage.setImageResource(R.mipmap.jin_du_2);
                yaJinView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                break;
            case SHEN_HE_STATE:
                shenHeImage.setImageResource(R.mipmap.jin_du_1);
                shenHeView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                identifyImage.setImageResource(R.mipmap.jin_du_2);
                identifView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                yaJinImage.setImageResource(R.mipmap.jin_du_2);
                yaJinView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                break;
            case IDENTIFY_STATE:
                identifyImage.setImageResource(R.mipmap.jin_du_1);
                identifView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                yaJinImage.setImageResource(R.mipmap.jin_du_2);
                yaJinView.setBackgroundColor(getResources().getColor(R.color.blue_green));
                break;
        }
    }

    private void setFragment(String type) {
        switch (type){
            case PAY_STATE:
                fragment = new PayFragment();
                break;
            case IDENTIFY_STATE:
                fragment = new IdentifyFragment();
                break;
            case SHEN_HE_STATE:
                fragment = new ShenHeFragment();
                break;
            case PAI_DAN_STATE:
                fragment = new PaiDanFragment();
                break;
        }

    }


    private void addFramlayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.identityFrameLayout, fragment).commitAllowingStateLoss();
    }


    class BusinessIdentifyActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    //关闭activity
                    BusinessIdentifyActivity.this.finish();
                    break;
            }
        }
    }


}

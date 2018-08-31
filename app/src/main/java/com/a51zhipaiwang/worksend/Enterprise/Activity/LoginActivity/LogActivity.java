package com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.HomeActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.logactitivityview.ILogActivityView;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseLogActivityPresenter.ILogActivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseLogActivityPresenter.LogActivityPresenter;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/7/19
 *     desc   : 企业端登录activity
 *     version: 1.0
 *
        常量
        字段
        构造函数
        重写函数和回调
        公有函数
        私有函数
        内部类或接口
 * </pre>
 */
public class LogActivity extends BaseActivity implements ILogActivityView {

    private ILogActivityPresenter iLogActivityPresenter;
    private LogActivityClickListener logActivityClickListener;

    private LinearLayout lv_title_layout;
    private TextView tilte_text;
    private ImageView return_image;
    private EditText edx_log_number;
    private EditText edx_log_password;
    private Button btn_log;
    private LinearLayout lv_phone_num;
    private TextView tx_get_security_code;

    private boolean bGetVercode = false;

    public static void StartLogActivity(Context context){
        Intent intent = new Intent(context, LogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        init();
        setRegister();
    }


    @Override
    public String checkUserInputInfo() {
        String sUserNum = edx_log_number.getText().toString();
        String sUserPassWord = edx_log_password.getText().toString();
        if (TextUtils.isEmpty(sUserNum)){
            return "请输入正确的手机号";
        }
        if (TextUtils.isEmpty(sUserPassWord)){
            return "请输入正确的验证码";
        }
        return "success";
    }

    @Override
    public Map setMapWithUserInfo() {
        Map paramMap = new HashMap();
        paramMap.put("", "");
        return paramMap;
    }

    @Override
    public void logReturnInfo(String returnInfo) {
        if ("success".equals(returnInfo)){
            HomeActivity.startHomeActivity(LogActivity.this);
        }else {
            ToastUtil.showToastTwo("验证码错误");
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }


    private void init(){
        iLogActivityPresenter = new LogActivityPresenter(this);
        logActivityClickListener = new LogActivityClickListener();

        lv_title_layout = (LinearLayout) findViewById(R.id.lv_title_layout);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        edx_log_number = (EditText) findViewById(R.id.edx_log_number);
        edx_log_password = (EditText) findViewById(R.id.edx_log_password);
        btn_log = (Button) findViewById(R.id.btn_log);
        lv_phone_num = (LinearLayout) findViewById(R.id.lv_phone_num);
        tx_get_security_code = (TextView) findViewById(R.id.tx_get_security_code);

        tilte_text.setText("登录");

    }

    private void setRegister(){
        btn_log.setOnClickListener(logActivityClickListener);
        return_image.setOnClickListener(logActivityClickListener);
        lv_phone_num.setOnClickListener(logActivityClickListener);
        tx_get_security_code.setOnClickListener(logActivityClickListener);
    }

    class LogActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    LogActivity.this.finish();
                    break;
                case R.id.btn_log:
                    String info = checkUserInputInfo();
                    if (!bGetVercode){
                        ToastUtil.showToastTwo("未获取验证码");
                        break;
                    }
                    if (info.equals("success")){
                        HashMap hashMap = new HashMap();
                        hashMap.put("phone", edx_log_number.getText().toString());
                        hashMap.put("verificationCode", edx_log_password.getText().toString());
                        iLogActivityPresenter.userLogIn(hashMap);
                    }else {
                        ToastUtil.showToastTwo(info);
                    }
                    break;
                case R.id.lv_phone_num:

                    break;
                case R.id.tx_get_security_code:
                    if (edx_log_number.getText().length() != 11){
                        ToastUtil.showToastTwo("请输入正确的手机号");
                    }else {
                        tx_get_security_code.setClickable(false);
                        new LogActivityTimeCount(60 * 1000, 1000).start();
                        HashMap hashMap = new HashMap();
                        hashMap.put("phone", edx_log_number.getText().toString());
                        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                                MyApplication.path + "api/enterpriseLog/verificationCodeAcquisition.do",
                                hashMap,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        MyLog.e("LogActivityClickListener", "onResponse(LogActivityClickListener.java:156)" + response);
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getString("code").equals("success")){
                                                bGetVercode = true;
                                                ToastUtil.showToastTwo("获取验证码成功！");
                                            }else {
                                                ToastUtil.showToastTwo("获取验证码失败！请检查您的网络连接");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            MyLog.e("LogActivityClickListener", "onResponse(LogActivityClickListener.java:196)" + e.getMessage());
                                            ToastUtil.showToastTwo("获取验证码失败！请检查您的网络连接");
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        MyLog.e("LogActivityClickListener", "onErrorResponse(LogActivityClickListener.java:203)" + error.getMessage());
                                        ToastUtil.showToastTwo("获取验证码失败！请检查您的网络连接");
                                    }
                                }));
                    }
                    break;
            }
        }
    }

    class LogActivityTimeCount extends CountDownTimer {
        public LogActivityTimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            tx_get_security_code.setText(l / 1000 + "s后可再次获取");
        }

        @Override
        public void onFinish() {
            tx_get_security_code.setClickable(true);
            tx_get_security_code.setText("获取验证码");
        }
    }

}

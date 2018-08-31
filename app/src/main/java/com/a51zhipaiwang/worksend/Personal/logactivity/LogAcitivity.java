package com.a51zhipaiwang.worksend.Personal.logactivity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.LogChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.LogActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.homeactivity.PersonalHomeActivity;
import com.a51zhipaiwang.worksend.Personal.logactivity.contract.ILogContract;
import com.a51zhipaiwang.worksend.Personal.logactivity.presenter.ILogPresenter;
import com.a51zhipaiwang.worksend.Personal.registeractivity.RegisterActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.CheckUserInfo;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;


public class LogAcitivity extends BaseActivity implements ILogContract.View{


    private IWXAPI api;

    private LogAcitivityClickListener logAcitivityClickListener;
    private ILogContract.Presenter presenter;
    private boolean bGetVerfityCode = false;

    private EditText edx_log_number;
    private EditText edx_log_password;
    private TextView tx_get_security_code;
    private Button btn_log;
    private LinearLayout lv_phone_num;
    private ImageView return_image;
    private LinearLayout lv_bg;
    private TextView tx_register;
    private TextView tx_forget_password;
    private TextView tx_phone_error_hint;
    private TextView tx_password_error_hint;

    public static void startLogActivity(Context context){
        context.startActivity(new Intent(context, LogAcitivity.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_acitivity);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID,false);
        //将应用的appid注册到微信
        api.registerApp(MyApplication.APP_ID);
        init();
        setRegister();
    }

    @Override
    public void getSercircodeReturnInfo(boolean returnInfo) {
        if (returnInfo){
            bGetVerfityCode = true;
            tx_get_security_code.setClickable(false);
            new LogActivityTimeCount(60 * 1000, 1000).start();
        }
    }

    @Override
    public void logSuccess(boolean returnLog) {
        if (returnLog){
            PersonalHomeActivity.startPersonalHomeAcitivty(this);
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
        presenter = new ILogPresenter(this);
        edx_log_number = (EditText) findViewById(R.id.edx_log_number);
        edx_log_password = (EditText) findViewById(R.id.edx_log_password);
        tx_get_security_code = (TextView) findViewById(R.id.tx_get_security_code);
        return_image = (ImageView) findViewById(R.id.return_image);
        btn_log = (Button) findViewById(R.id.btn_log);
        lv_phone_num = (LinearLayout) findViewById(R.id.lv_phone_num);
        lv_bg = (LinearLayout) findViewById(R.id.lv_bg);
        tx_register = (TextView) findViewById(R.id.tx_register);
        tx_forget_password = (TextView) findViewById(R.id.tx_forget_password);
        tx_phone_error_hint = (TextView) findViewById(R.id.tx_phone_error_hint);
        tx_password_error_hint = (TextView) findViewById(R.id.tx_password_error_hint);

    }

    private void setRegister(){
        logAcitivityClickListener = new LogAcitivityClickListener();
        btn_log.setOnClickListener(logAcitivityClickListener);
        lv_phone_num.setOnClickListener(logAcitivityClickListener);
        tx_get_security_code.setOnClickListener(logAcitivityClickListener);
        return_image.setOnClickListener(logAcitivityClickListener);
        tx_register.setOnClickListener(logAcitivityClickListener);
    }

    /**
     * 展示手机号码输入错误提示
     * @param hintInfo 提示信息
     */
    private void showPhoneErrorHint(String hintInfo){
        if (!TextUtils.isEmpty(hintInfo)){
            tx_phone_error_hint.setText(hintInfo);
            tx_phone_error_hint.setVisibility(View.VISIBLE);
        }else {
            tx_phone_error_hint.setVisibility(View.VISIBLE);
        }
    }

    private void invisiblePhoneErrorHint(){
        tx_phone_error_hint.setVisibility(View.INVISIBLE);
    }

    private void showPasswordErrorHint(String hintInfo){
        if (!TextUtils.isEmpty(hintInfo)){
            tx_password_error_hint.setText(hintInfo);
            tx_password_error_hint.setVisibility(View.VISIBLE);
        }else {
            tx_password_error_hint.setVisibility(View.VISIBLE);
        }
    }

    private void invisiblePasswordErrorHint(){
        tx_password_error_hint.setVisibility(View.INVISIBLE);
    }


    class LogAcitivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_log:
                    if (!CheckUserInfo.CheckTelNum(edx_log_number.getText().toString())){
                        showPhoneErrorHint("");
                        break;
                    }else {
                        invisiblePhoneErrorHint();
                    }
                    /*if (edx_log_password.getText().length() != 4){
                        ToastUtil.showToastTwo("请输入四位验证码");
                        break;
                    }*/
                    if (!CheckUserInfo.CheckPassWord(edx_log_password.getText().toString())){
                        showPasswordErrorHint("");
                        break;
                    }else {
                        invisiblePasswordErrorHint();
                    }
                    if (bGetVerfityCode){
                        HashMap hashMap = new HashMap();
                        hashMap.put("phone", edx_log_number.getText().toString());
                        hashMap.put("verificationCode", edx_log_password.getText().toString());
                        presenter.log(hashMap);
                    }else {
                        ToastUtil.showToastTwo("未获取验证码");
                    }
                    break;
                case R.id.lv_phone_num:
                    ToastUtil.showToastTwo("微信登录");
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";//
                    req.state = "wechat_sdk_微信登录";
                    api.sendReq(req);
                    break;
                case R.id.return_image:
                    LogAcitivity.this.finish();
                    //ActivityCollector.removeActivity(LogAcitivity.this);
                    break;
                case R.id.tx_get_security_code:
                    if (CheckUserInfo.CheckTelNum(edx_log_number.getText().toString())){
                        tx_get_security_code.setClickable(false);
                        HashMap hashMap = new HashMap();
                        hashMap.put("phone", edx_log_number.getText().toString());
                        presenter.getSecriCode(hashMap);
                    }
                    break;
                case R.id.tx_register:
                    RegisterActivity.startRegisterActivity(LogAcitivity.this);
                    break;
            }
        }
    }

    class LogActivityTimeCount extends CountDownTimer{
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

package com.a51zhipaiwang.worksend.Personal.registeractivity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a51zhipaiwang.worksend.CommonActivity.DisclaimerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.RegionActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.registeractivity.contract.RegisterContract;
import com.a51zhipaiwang.worksend.Personal.registeractivity.presenter.RegisterPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/8/30
 *     desc   : 个人端注册界面
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
public class RegisterActivity extends BaseActivity implements RegisterContract.View, View.OnClickListener{

    private ImageView return_image;
    private TextView tilte_text;
    private EditText edx_log_number;
    private EditText edx_verficode;
    private EditText edx_log_password;
    private EditText edx_re_log_password;
    private TextView tx_phone_error_hint;
    private TextView tx_verify_error_hint;
    private TextView tx_password_error_hint;
    private TextView tx_re_password_error_hint;
    private TextView tx_verificoe;
    private Button btn_log;
    private TextView tx_register_arrgement;

    private RegisterContract.Presenter presenter;

    public static void startRegisterActivity(Context context){
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        setRegister();
    }


    @Override
    public void startTimeing() {
        tx_verificoe.setEnabled(false);
        new MyTimeCounter(60 * 1000, 1000).start();
    }

    @Override
    public void registerSuccess() {
        ToastUtil.showToastTwo("注册成功");
        this.finish();
    }

    @Override
    public void registerError(String errorInfo) {
        ToastUtil.showToastTwo(errorInfo);
    }

    @Override
    public String getPhoneNum() {
        return edx_log_number.getText().toString();
    }

    @Override
    public String getVerifyCode() {
        return edx_verficode.getText().toString();
    }

    @Override
    public String getPassWord() {
        return edx_log_password.getText().toString();
    }

    @Override
    public String getRePassWord() {
        return edx_re_log_password.getText().toString();
    }

    @Override
    public void showTip(int type) {
        clearTip();
        switch (type) {
            case 1:
                tx_phone_error_hint.setVisibility(View.VISIBLE);
                break;
            case 2:
                tx_verify_error_hint.setVisibility(View.VISIBLE);
                break;
            case 3:
                tx_password_error_hint.setVisibility(View.VISIBLE);
                break;
            case 4:
                tx_re_password_error_hint.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void clearTip() {
        tx_phone_error_hint.setVisibility(View.INVISIBLE);
        tx_verify_error_hint.setVisibility(View.INVISIBLE);
        tx_password_error_hint.setVisibility(View.INVISIBLE);
        tx_re_password_error_hint.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_image:
                RegisterActivity.this.finish();
                break;
            case R.id.tx_verificoe:
                presenter.getVerfifyCode();
                break;
            case R.id.btn_log:
                presenter.register();
                break;
            case R.id.tx_register_arrgement:
                DisclaimerActivity.startDisclaimerActivity(RegisterActivity.this);
                break;
        }
    }
    
    private void init(){
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        edx_log_number = (EditText) findViewById(R.id.edx_log_number);
        edx_verficode = (EditText) findViewById(R.id.edx_verficode);
        edx_log_password = (EditText) findViewById(R.id.edx_log_password);
        edx_re_log_password = (EditText) findViewById(R.id.edx_re_log_password);
        
        tx_phone_error_hint = (TextView) findViewById(R.id.tx_phone_error_hint);
        tx_verify_error_hint = (TextView) findViewById(R.id.tx_verify_error_hint);
        tx_password_error_hint = (TextView) findViewById(R.id.tx_password_error_hint);
        tx_re_password_error_hint = (TextView) findViewById(R.id.tx_re_password_error_hint);

        tx_verificoe = (TextView) findViewById(R.id.tx_verificoe);
        btn_log = (Button) findViewById(R.id.btn_log);
        tx_register_arrgement = (TextView) findViewById(R.id.tx_register_arrgement);

        presenter = new RegisterPresenter(this);
    }

    private void setRegister(){
        return_image.setOnClickListener(this);
        tx_verificoe.setOnClickListener(this);
        btn_log.setOnClickListener(this);
        tx_register_arrgement.setOnClickListener(this);
    }

    class MyTimeCounter extends CountDownTimer{
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyTimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tx_verificoe.setText("已发送" + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            tx_verificoe.setText("获取验证码");
            tx_verificoe.setEnabled(true);
        }
    }

}

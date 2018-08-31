
package com.a51zhipaiwang.worksend.CommonActivity.MoneyActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.DisclaimerActivity;
import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.LogChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseMoneyActivityPresenter.IMoneyActivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseMoneyActivityPresenter.MoneyActivityPresenter;
import com.a51zhipaiwang.worksend.Personal.homeactivity.PersonalHomeActivity;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.EditInputMoneyFilter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.RechargeUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * <pre>
 *     author : ly
 *     e-mail : xxx@xx
 *     time   : 2018/07/17
 *     desc   :
 *     version: ${version}
 * </pre>
 */


public class MoneyActivity extends BaseActivity implements IMoneyAcitivty {

    public static final int ENTERPRISE = 1;
    public static final int PERSONAL = 2;

    private final int RECHARGE = 1;
    private final int WITHDRAW = 2;

    private int type;

    private MoneyActivityClickListener mMoneyClickListener;
    private IMoneyActivityPresenter mMoneyPresenter;
    private BroadcastReceiver broadcastReceiver;

    private Button btn_recharge;
    private TextView tx_money;
    private ImageView return_image;
    private TextView tilte_text;
    private Button btn_withdraw;
    private LinearLayout lv_title_layout;

    private float totalMoney;
    private CheckBox checkbox;
    private TextView service_text;

    public static void StartMoneyActivity(Context context, String title, int type) {
        Intent intent = new Intent(context, MoneyActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        init();
        initBroadCastReceiver();
        setRegister();
        getMoney();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setMoney(String sMoney) {
        tx_money.setText(sMoney);
    }

    @Override
    public void returnInfo(boolean bReturn) {
        if (bReturn) {
            getMoney();
            ToastUtil.showToastTwo("充值成功");
        }else {
            ToastUtil.showToastTwo("充值失败!");
        }
    }

    @Override
    public void returnWithDrawo(String info) {
        switch (info) {
            case "010":
                ToastUtil.showToastTwo("余额不足");
                break;
            case "020":
                ToastUtil.showToastTwo("不是微信登录");
                break;
            case "030":
                ToastUtil.showToastTwo("金额不匹配");
                break;
            case "040":
                ToastUtil.showToastTwo("提现成功!7天工作日内到账");
                break;
        }
    }


    private void init() {
        type = getIntent().getIntExtra("type", 0);
        mMoneyPresenter = new MoneyActivityPresenter(this, type);
        btn_recharge = (Button) findViewById(R.id.btn_recharge);
        tx_money = (TextView) findViewById(R.id.tx_money);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        btn_withdraw = (Button) findViewById(R.id.btn_withdraw);
        lv_title_layout = (LinearLayout) findViewById(R.id.lv_title_layout);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        service_text = (TextView) findViewById(R.id.service_text);
        tilte_text.setText(getIntent().getStringExtra("title"));
        mMoneyClickListener = new MoneyActivityClickListener();
        if (type == PERSONAL) {
            btn_withdraw.setVisibility(View.VISIBLE);
        }
        lv_title_layout.setBackgroundColor(getResources().getColor(R.color.translate));

    }

    /**
     * 监听充值结果
     */
    private void initBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case MyApplication.RECHARGE_SUCCESS:
                        getMoney();
                        StringRequest stringRequest;
                        if (type == PERSONAL){
                            HashMap hashMap = new HashMap();
                            hashMap.put("wallet", totalMoney);
                            stringRequest = new PersonalStringRequest(Request.Method.POST,
                                    MyApplication.path + "api/userInformationTable/userUpdPurse.do",
                                    hashMap,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                if (jsonObject.getString("code").equals("success")){
                                                    String info = jsonObject.getString("info");
                                                    JSONObject infoJson = new JSONObject(info);
                                                    if (infoJson.getString("success").equals("020")){
                                                        getMoney();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    });
                        }else {
                            HashMap hashMap = new HashMap();
                            hashMap.put("enterprisePurse", totalMoney);
                            stringRequest = new EnterpriseStringRequest(Request.Method.POST,
                                    MyApplication.path + "api/enterpriseInfo/updPurse.do",
                                    hashMap,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                if (jsonObject.getString("code").equals("success")){
                                                    String info = jsonObject.getString("info");
                                                    JSONObject infoJson = new JSONObject(info);
                                                    if (infoJson.getString("success").equals("020")){
                                                        getMoney();
                                                    }
                                                }
                                            } catch (JSONException e) {
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
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(MyApplication.RECHARGE_SUCCESS);
        this.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void setRegister() {
        btn_recharge.setOnClickListener(mMoneyClickListener);
        return_image.setOnClickListener(mMoneyClickListener);
        btn_withdraw.setOnClickListener(mMoneyClickListener);
        service_text.setOnClickListener(mMoneyClickListener);
    }

    /**
     * 获取余额
     */
    private void getMoney() {
        mMoneyPresenter.getMoney(new HashMap());
    }

    /**
     * 充值弹框
     * type = 1 充值弹框
     * type = 2 提现弹框
     * notice 充值 将数值* 100 ！！！！！！！！！！！！！！！！！！
     */
    private void showRechargeAlert() {
        final Dialog alertDialog = new AlertDialog.Builder(MoneyActivity.this).create();
        View view = LayoutInflater.from(MoneyActivity.this).inflate(R.layout.alert_money_layout, null);
        final EditText editText = ((EditText) view.findViewById(R.id.edx_input_money));
        editText.setFilters(new InputFilter[]{new EditInputMoneyFilter()});
        /*if (type == WITHDRAW){
            ((TextView)view.findViewById(R.id.tx_title)).setText("提现到微信");
        }*/
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.btn_recharge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果是充值
                //if (type == RECHARGE){
                HashMap hashMap = new HashMap();
                try {
                    totalMoney = Float.valueOf(editText.getText().toString());
                    hashMap.put("totalMoney", totalMoney);
                } catch (Exception e) {
                    ToastUtil.showToastTwo("请输入正确的金额");
                    return;
                }
                if (type == PERSONAL){
                    if (TextUtils.isEmpty(MyApplication.tokenPersonal)){
                        ToastUtil.showToastTwo("请登录");
                        ActivityCollector.finishAll();
                        LogChoiceActivity.startLogChoiceActivity(MoneyActivity.this);
                    }else {
                        hashMap.put("payType", "010");
                        hashMap.put("payUser", MyApplication.tokenPersonal);
                        mMoneyPresenter.recharge(hashMap, type);
                    }
                }

                if (type == ENTERPRISE){
                    if (TextUtils.isEmpty(MyApplication.tokenEnterprise)){
                        ToastUtil.showToastTwo("请登录");
                        ActivityCollector.finishAll();
                        LogChoiceActivity.startLogChoiceActivity(MoneyActivity.this);
                    }else {
                        hashMap.put("payType", "010");
                        hashMap.put("payUser", MyApplication.tokenEnterprise);
                        mMoneyPresenter.recharge(hashMap, type);
                    }
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.setContentView(view);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    class MoneyActivityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    MoneyActivity.this.finish();
                    break;
                case R.id.btn_recharge:
                    if (checkbox.isChecked()){
                        showRechargeAlert();
                    }else {
                        ToastUtil.showToastTwo("请同意职派协议");
                    }
                    break;
                case R.id.btn_withdraw:
                    if (checkbox.isChecked()){
                        HashMap hashMap = new HashMap();
                        mMoneyPresenter.withDraw(hashMap);
                    }else {
                        ToastUtil.showToastTwo("请同意职派协议");
                    }
                    break;
                case R.id.service_text:
                    //ToastUtil.showToastTwo("查看服务信息");
                    DisclaimerActivity.startDisclaimerActivity(MoneyActivity.this);
                    break;
            }
        }
    }

}


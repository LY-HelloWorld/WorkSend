package com.a51zhipaiwang.worksend.Personal.tryinformationactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.HRSendInfor;
import com.a51zhipaiwang.worksend.Personal.tryinformationactivity.contract.ITryInformationContract;
import com.a51zhipaiwang.worksend.Personal.tryinformationactivity.presenter.ITryInformationPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class TryInfomationActivity extends BaseActivity implements ITryInformationContract.View {

    private TryInfomationActivityClickListener tryInfomationActivityClickListener;
    private ITryInformationContract.Presenter presenter;

    private String id;
    private int currentId = 0;
    private TextView tx_enterprise_name;
    private TextView tx_time;
    private TextView tx_location;
    private TextView tx_phone;
    private TextView tx_money;
    private TextView tilte_text;
    private ImageView return_image;
    private Button btn_cancel;
    private Button btn_sure;
    private HRSendInfor hrSendInfor;

    public static void startTryInfomationActivity(Context context, String id){
        Intent intent = new Intent(context, TryInfomationActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_infomation);
        init();
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("id", id);
        presenter.getTryInfo(hashMap);
    }

    @Override
    public void setTryInfo(Object object) {
        if (object != null){
            hrSendInfor = (HRSendInfor) object;
            tx_enterprise_name.setText(hrSendInfor.getCorporateName());
            tx_location.setText(hrSendInfor.getCompanyAddress());
            //tx_money.setText(IdCoverText.coverMoney(hrSendInfor.getSalary()));
            tx_money.setText(hrSendInfor.getTrialPostSalary());
            tx_phone.setText(hrSendInfor.getEnterprisePhone());
            tx_time.setText(hrSendInfor.getCreationtime());
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(){
        tryInfomationActivityClickListener = new TryInfomationActivityClickListener();
        presenter = new ITryInformationPresenter(this);
        id = getIntent().getStringExtra("id");
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text.setText("试岗消息");
        tx_enterprise_name = (TextView) findViewById(R.id.tx_enterprise_name);
        tx_time = (TextView) findViewById(R.id.tx_time);
        tx_location = (TextView) findViewById(R.id.tx_location);
        tx_phone = (TextView) findViewById(R.id.tx_phone);
        tx_money = (TextView) findViewById(R.id.tx_money);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_sure = (Button) findViewById(R.id.btn_sure);

    }

    private void setRegister(){
        return_image.setOnClickListener(tryInfomationActivityClickListener);
        btn_cancel.setOnClickListener(tryInfomationActivityClickListener);
        btn_sure.setOnClickListener(tryInfomationActivityClickListener);
    }

    class TryInfomationActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_cancel:
                case R.id.return_image:
                    if (hrSendInfor != null){
                        HashMap hashMap = new HashMap();
                        hashMap.put("id", id);
                        presenter.cancelTryInfo(hashMap);
                    }
                    TryInfomationActivity.this.finish();
                    break;
                case R.id.btn_sure:
                    if (hrSendInfor != null && hrSendInfor.getTestPostState().equals("010")){
                        HashMap hashMap = new HashMap();
                        hashMap.put("id", id);
                        presenter.applyTryInfo(hashMap);
                    }else {
                        ToastUtil.showToastTwo("已申请了");
                    }
                    break;
            }
        }
    }


}

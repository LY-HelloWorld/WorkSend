package com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.HRSendInfor;
import com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity.contract.IHRInformationApplyContract;
import com.a51zhipaiwang.worksend.Personal.hrinformationapplyactivity.presenter.IHRInformationApplyPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.HashMap;


public class HRInformationApplyActivity extends BaseActivity implements IHRInformationApplyContract.View<Object> {

    private IHRInformationApplyPresenter ihrInformationApplyPresenter;
    private HRInformationClickListener hrInformationClickListener;

    private TextView tx_enterprise_name;
    private TextView tx_location;
    private TextView tx_money;
    private TextView tx_work_name;
    private TextView tx_work_description;
    private Button btn_cancel;
    private Button btn_sure;
    private TextView tilte_text;
    private ImageView return_image;
    private HRSendInfor hrSendInfor;

    private String id;

    public static void startHRInformationApplyActivity(Context context, String id){
        Intent intent = new Intent(context, HRInformationApplyActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrinformation_apply);
        init();
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("id", id);
        ihrInformationApplyPresenter.getWorkInfo(hashMap);
    }

    @Override
    public void setWorkInfo(Object workInfo) {
        if (workInfo != null){
            hrSendInfor = (HRSendInfor) workInfo;
            tx_enterprise_name.setText(hrSendInfor.getCorporateName());
            tx_location.setText(hrSendInfor.getCompanyAddress());
            tx_money.setText(IdCoverText.coverMoney(hrSendInfor.getSnatchingState()));
            tx_work_description.setText(hrSendInfor.getMessageContent());
            tx_work_name.setText(hrSendInfor.getZwName());
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }
    
    private void init(){
        ihrInformationApplyPresenter = new IHRInformationApplyPresenter(this);
        id = getIntent().getStringExtra("id");
        tx_enterprise_name = (TextView) findViewById(R.id.tx_enterprise_name);
        tx_location = (TextView) findViewById(R.id.tx_location);
        tx_money = (TextView) findViewById(R.id.tx_money);
        tx_work_name = (TextView) findViewById(R.id.tx_work_name);
        tx_work_description = (TextView) findViewById(R.id.tx_work_description);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text.setText("HR派单消息");
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_sure = (Button) findViewById(R.id.btn_sure);
    }
    
    private void setRegister(){
        hrInformationClickListener = new HRInformationClickListener();
        return_image.setOnClickListener(hrInformationClickListener);
        btn_cancel.setOnClickListener(hrInformationClickListener);
        btn_sure.setOnClickListener(hrInformationClickListener);
    }

    class HRInformationClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_cancel:
                case R.id.return_image:
                    if (hrSendInfor != null){
                        HashMap hashMap = new HashMap();
                        hashMap.put("id", id);
                        ihrInformationApplyPresenter.cancel(hashMap);
                    }
                    HRInformationApplyActivity.this.finish();
                    break;
                case R.id.btn_sure:
                    if (hrSendInfor != null && hrSendInfor.getSnatchingState().equals("020")){
                        HashMap hashMap = new HashMap();
                        hashMap.put("id", id);
                        ihrInformationApplyPresenter.applyWork(hashMap);
                    }else {
                        ToastUtil.showToastTwo("已抢单了");
                    }
                    break;

            }
        }
    }

}

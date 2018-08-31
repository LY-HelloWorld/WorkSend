package com.a51zhipaiwang.worksend.Personal.activityinformationactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.activityinformationactivity.contract.IActivityCallBackActivityContract;
import com.a51zhipaiwang.worksend.Personal.activityinformationactivity.presenter.ISendCallBackActivityPresenter;
import com.a51zhipaiwang.worksend.Personal.adapter.SendCallBackListAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ActivityCallBackActivity extends BaseActivity implements IActivityCallBackActivityContract.View {

    private ListView liv_callback_info;
    private SendCallBackListAdapter sendCallBackListAdapter;
    private IActivityCallBackActivityContract.Presenter presenter;
    private ImageView return_image;
    private TextView tilte_text;

    public static void startSendCallBackActivity(Context context){
        Intent intent = new Intent(context, ActivityCallBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_call_back);
        init();
        setRegister();
        presenter.getActivityBackInfoList(new HashMap());
    }



    @Override
    public void setActivityBackInfoList(ArrayList arrayList) {
        sendCallBackListAdapter = new SendCallBackListAdapter(arrayList, ActivityCallBackActivity.this, true, SendCallBackListAdapter.SENDBACK);
        liv_callback_info.setAdapter(sendCallBackListAdapter);
        liv_callback_info.setSelection(arrayList.size() - 1);
    }


    private void init(){
        presenter = new ISendCallBackActivityPresenter(this);
        liv_callback_info = (ListView) findViewById(R.id.liv_callback_info);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("投递反馈");
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCallBackActivity.this.finish();
            }
        });
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }
}

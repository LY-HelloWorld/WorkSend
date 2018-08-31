package com.a51zhipaiwang.worksend.Personal.sendcallbackactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.adapter.SendCallBackListAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.sendcallbackactivity.contract.ISendCallBackActivityContract;
import com.a51zhipaiwang.worksend.Personal.sendcallbackactivity.presenter.ISendCallBackActivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;


public class SendCallBackActivity extends BaseActivity implements ISendCallBackActivityContract.View {

    private ListView liv_callback_info;
    private SendCallBackListAdapter sendCallBackListAdapter;
    private ISendCallBackActivityContract.Presenter presenter;
    private ImageView return_image;
    private TextView tilte_text;

    public static void startSendCallBackActivity(Context context){
        Intent intent = new Intent(context, SendCallBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_call_back);
        init();
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("userMessageState", "020");
        presenter.getSendBackInfoList(hashMap);
    }

    @Override
    public void setSendBackInfoList(ArrayList arrayList) {
        if (arrayList != null && !arrayList.isEmpty()){
            sendCallBackListAdapter = new SendCallBackListAdapter(arrayList, SendCallBackActivity.this, true, SendCallBackListAdapter.SENDBACK);
            liv_callback_info.setAdapter(sendCallBackListAdapter);
            liv_callback_info.setSelection(arrayList.size() - 1);
        }else {
            ToastUtil.showToastTwo("暂无信息");
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

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
                SendCallBackActivity.this.finish();
            }
        });
    }

}

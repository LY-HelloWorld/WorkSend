package com.a51zhipaiwang.worksend.Personal.systeminformationactivity;

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
import com.a51zhipaiwang.worksend.Personal.systeminformationactivity.contract.ISystemInformationActivityContract;
import com.a51zhipaiwang.worksend.Personal.systeminformationactivity.presenter.ISystemInformationActivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemInfomationActivity extends BaseActivity implements ISystemInformationActivityContract.View{

    private ISystemInformationActivityContract.Presenter presenter;

    private ListView liv_system_information;
    private ImageView return_image;
    private TextView tilte_text;

    public static void startSystemInformationActivity(Context context){
        context.startActivity(new Intent(context, SystemInfomationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_infomation);
        init();
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("userMessageState", "040");
        presenter.getSystemInfomation(hashMap);
    }

    @Override
    public void setSystemInformation(ArrayList arrayList) {
        if (arrayList != null && !arrayList.isEmpty()){
            liv_system_information.setAdapter(new SendCallBackListAdapter(arrayList, this, true, SendCallBackListAdapter.SYSTEM));
            liv_system_information.setSelection(arrayList.size() - 1);
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
        presenter = new ISystemInformationActivityPresenter(this);
        liv_system_information = (ListView) findViewById(R.id.liv_system_information);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("通知消息");
    }

    private void setRegister(){
        return_image.setOnClickListener(new SystemInfomationActivityClickListener());
    }

    class SystemInfomationActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    SystemInfomationActivity.this.finish();
                    break;
            }
        }
    }

}

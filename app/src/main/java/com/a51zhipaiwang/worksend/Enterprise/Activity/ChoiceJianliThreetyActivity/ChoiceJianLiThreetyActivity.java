package com.a51zhipaiwang.worksend.Enterprise.Activity.ChoiceJianliThreetyActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Bean.SubChoiceListThreetyJianLi;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.ChoiceJianliThreetyActivity.IChoiceJianLiThreetyAcitivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.ThreetyJianLiWebViewActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.JianLiAdapterTwo;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseChoiceThreetyActivityPresenter.ChoiceThreeTyJianLiActivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseChoiceThreetyActivityPresenter.IChoiceThreetyJIanLiActivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ChoiceJianLiThreetyActivity extends BaseActivity implements IChoiceJianLiThreetyAcitivity {

    private ListView jianLiList;
    private Button sureBt;

    private IChoiceThreetyJIanLiActivityPresenter choiceThreetyJIanLiActivityPresenter;

    private int page = 0;

    private String title;
    private String distributeLeafletsId;

    private int currentChoiceid;

    private int choiceCount = 0;

    private ArrayList<SampleJianLiData> sampleJianLiData;
    private TextView titleText;

    private SubChoiceListThreetyJianLi mSubChoiceListThreetyJianLi;
    private ImageView return_image;


    public static void StartChoiceJianLiThreetyActivity(Context context, String distributeLeafletsId, String title){
        Intent intent = new Intent(context, ChoiceJianLiThreetyActivity.class);
        intent.putExtra("distributeLeafletsId", distributeLeafletsId);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_jian_li_threety);
        init();
        getJianLi();
        setRegister();
    }

    private void init(){
        title = getIntent().getStringExtra("title");
        distributeLeafletsId = getIntent().getStringExtra("distributeLeafletsId");
        choiceThreetyJIanLiActivityPresenter = new ChoiceThreeTyJianLiActivityPresenter(this);
        jianLiList = (ListView) findViewById(R.id.jianLiList);
        sureBt = (Button) findViewById(R.id.sureBt);
        titleText = (TextView) findViewById(R.id.tilte_text);
        titleText.setText(title);
        return_image = (ImageView) findViewById(R.id.return_image);
        mSubChoiceListThreetyJianLi = new SubChoiceListThreetyJianLi(distributeLeafletsId);
    }


    private void getJianLi(){
        HashMap hashMap = new HashMap();
        hashMap.put("distributeLeafletsId", distributeLeafletsId);
        hashMap.put("longitude", MyApplication.longitude);
        hashMap.put("latitude", MyApplication.latitude);
        hashMap.put("page", 1);
        choiceThreetyJIanLiActivityPresenter.getList(hashMap);
    }

    private void setRegister(){
        sureBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (sampleJianLiData == null || sampleJianLiData.size() == 0){
                    //ToastUtil.showToastTwo("");
                    return;
                }
                if (mSubChoiceListThreetyJianLi == null || mSubChoiceListThreetyJianLi.getResumeId().size() == 0){
                    return;
                }*/
                choiceThreetyJIanLiActivityPresenter.submitChoiceList(mSubChoiceListThreetyJianLi);
            }
        });
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoiceJianLiThreetyActivity.this.finish();
            }
        });
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setJianLiList(ArrayList<SampleJianLiData> data) {
        this.sampleJianLiData = data;
        jianLiList.setAdapter(new JianLiAdapterTwo(true, this, false, 3, sampleJianLiData));
        jianLiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.e("ChoiceJianLiThreetyActivity", "onItemClick(ChoiceJianLiThreetyActivity.java:108)" + "ChoiceJianLiThreetyActivity");
                currentChoiceid = i;
                ThreetyJianLiWebViewActivity.StartThreeJianLiWebViewActivity(ChoiceJianLiThreetyActivity.this, sampleJianLiData.get(i).getId(), "简历详情");
            }
        });
    }

    @Override
    public void submitReturn(boolean returnInfo) {
        if (returnInfo){
            ToastUtil.showToastTwo("提交成功!");
            ChoiceJianLiThreetyActivity.this.finish();
        }else {
            ToastUtil.showToastTwo("请检查您的网络连接!");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.ChoiceJianLi:
                if (resultCode == RESULT_OK){
                    String choice = data.getStringExtra("choice");
                    if ("010".equals(choice)){
                        mSubChoiceListThreetyJianLi.getResumeId().add(sampleJianLiData.get(currentChoiceid).getId());
                        choiceCount++;
                        sampleJianLiData.get(currentChoiceid).setChoice(true);
                        ((JianLiAdapterTwo)jianLiList.getAdapter()).notifyDataSetChanged();
                    }else {
                        mSubChoiceListThreetyJianLi.getResumeId().remove(sampleJianLiData.get(currentChoiceid).getId());
                        choiceCount--;
                        sampleJianLiData.get(currentChoiceid).setChoice(false);
                        ((JianLiAdapterTwo)jianLiList.getAdapter()).notifyDataSetChanged();
                    }
                    if (choiceCount < 0){
                        choiceCount = 0;
                    }
                    sureBt.setText("确定(" + choiceCount + ")");
                }
                break;
        }
    }
}

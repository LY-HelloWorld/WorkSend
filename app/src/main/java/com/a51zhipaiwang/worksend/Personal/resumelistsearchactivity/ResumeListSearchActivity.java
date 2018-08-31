package com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.adapter.ResumeListAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.detailswebviewactivity.DetailsWebViewActivity;
import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.contract.IResumeContract;
import com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.presenter.IResumePresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;

import java.util.ArrayList;
import java.util.HashMap;

public class ResumeListSearchActivity extends BaseActivity implements IResumeContract.View{

    private ResumeListSearchActivityClicklistener resumeListSearchActivityClicklistener;
    private IResumeContract.Presenter presenter;

    private TextView tilte_text;
    private ImageView return_image;
    private TextView tx_work;
    private ListView liv_work;

    private ArrayList arrayList;

    public static void startResumeListSearchActivity(Context context, String work){
        Intent intent = new Intent(context, ResumeListSearchActivity.class);
        intent.putExtra("work", work);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_list_search);
        init();
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("Identification", "010");
        hashMap.put("recruitmentPosition", getIntent().getStringExtra("work"));
        hashMap.put("enterpriseName", "");
        hashMap.put("longitude", MyApplication.longitude);
        hashMap.put("latitude", MyApplication.latitude);
        hashMap.put("page", "1");
        presenter.getResumeList(hashMap);
    }

    @Override
    public void setResumeList(ArrayList arr) {
        this.arrayList = arr;
        liv_work.setAdapter(new ResumeListAdapter(arrayList, ResumeListSearchActivity.this));
        liv_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ToastUtil.showToastTwo("点击" + ((FindWork)ResumeListSearchActivity.this.arrayList.get(i)).getEnterpriseName());
                ArrayList<String> ids = new ArrayList<>();
                for (int j = i; j < arrayList.size(); j++) {
                    ids.add(((FindWork)arrayList.get(j)).getDistributeLeafletsDetailsId());
                }
                MyLog.e("ResumeListSearchActivity", "onItemClick(ResumeListSearchActivity.java:78)" + ids);
                DetailsWebViewActivity.startDetailsWebViewActivity(ResumeListSearchActivity.this, ids);

            }
        });
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(){
        presenter = new IResumePresenter(this);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tx_work = (TextView) findViewById(R.id.tx_work);
        liv_work = (ListView) findViewById(R.id.liv_work);
        tilte_text.setText("找工作");
        tx_work.setText(getIntent().getStringExtra("work"));

    }

    private void setRegister(){
        resumeListSearchActivityClicklistener = new ResumeListSearchActivityClicklistener();
        return_image.setOnClickListener(resumeListSearchActivityClicklistener);
    }


    class ResumeListSearchActivityClicklistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    ResumeListSearchActivity.this.finish();
                    break;
            }
        }
    }

}

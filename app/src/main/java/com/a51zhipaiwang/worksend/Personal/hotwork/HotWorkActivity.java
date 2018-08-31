package com.a51zhipaiwang.worksend.Personal.hotwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Personal.adapter.HotWorkListAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.HotWork;
import com.a51zhipaiwang.worksend.Personal.entity.TakeWork;
import com.a51zhipaiwang.worksend.Personal.hotwork.contract.IHotContract;
import com.a51zhipaiwang.worksend.Personal.hotwork.presenter.IHotPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.View.LoadingListView;

import java.util.ArrayList;
import java.util.HashMap;


public class HotWorkActivity extends BaseActivity implements IHotContract.View {

    private int page = 0;
    private HashMap hashMap;

    private IHotContract.Presenter presenter;
    private HotWorkActivityClickListener hotWorkActivityClickListener;
    private TextView tilte_text;
    private ImageView return_image;
    private LoadingListView liv_hot_work;
    private HotWorkListAdapter hotWorkListAdapter;
    private ArrayList<HotWork> hotWorkList;

    public static void startHotWorkActivity(Context context){
        Intent intent = new Intent(context, HotWorkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_work);
        init();
        setRegister();
        getList();
    }

    @Override
    public void setHotWorkListAndTakeWork(final ArrayList<HotWork> arrayList, ArrayList<TakeWork> takeWorks) {
        HotWorkActivity.this.hotWorkList.addAll(arrayList);
        for (int i = 0, len = hotWorkList.size(); i < len; i++) {
            for (int j = 0, klen = takeWorks.size(); j < klen; j++) {
                if (hotWorkList.get(i).getPositionId().equals(takeWorks.get(j).getCol1())){
                    hotWorkList.get(i).setTakeOrNot(true);
                    break;
                }
            }
        }
        hotWorkListAdapter = new HotWorkListAdapter(hotWorkList, HotWorkActivity.this);
        liv_hot_work.setAdapter(hotWorkListAdapter);
        liv_hot_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap hashMap = new HashMap();
                if (i < adapterView.getCount() - 1){
                    if (HotWorkActivity.this.hotWorkList.get(i).isTakeOrNot()){
                        hashMap.put("col1", HotWorkActivity.this.hotWorkList.get(i).getPositionId());
                        presenter.cancelTakeWork(hashMap);
                    }else {
                        hashMap.put("positionName", HotWorkActivity.this.hotWorkList.get(i).getPositionName());
                        hashMap.put("id", HotWorkActivity.this.hotWorkList.get(i).getPositionId());
                        presenter.takeWork(hashMap);
                    }
                    HotWorkActivity.this.hotWorkList.get(i).setTakeOrNot(!HotWorkActivity.this.hotWorkList.get(i).isTakeOrNot());
                    hotWorkListAdapter.notifyDataSetChanged();
                }
            }
        });
        liv_hot_work.setLoadInterface(new LoadingListView.LoadInterface() {
            @Override
            public void load() {
                getList();
            }
        });
        liv_hot_work.loadComplete();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void getList(){
        page++;
        hashMap.put("page", page);
        presenter.getHotWorkListAndTakeWork(hashMap);

    }

    private void init(){
        presenter = new IHotPresenter(this);
        hashMap = new HashMap();
        hotWorkList = new ArrayList<>();
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("热门行业");
        return_image = (ImageView) findViewById(R.id.return_image);
        liv_hot_work = (LoadingListView) findViewById(R.id.liv_hot_work);
    }

    private void setRegister(){
        hotWorkActivityClickListener = new HotWorkActivityClickListener();
        return_image.setOnClickListener(hotWorkActivityClickListener);
        liv_hot_work.setLoadInterface(new LoadingListView.LoadInterface() {
            @Override
            public void load() {
                presenter.getHotWorkListAndTakeWork(new HashMap());
            }
        });
    }

    class HotWorkActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    HotWorkActivity.this.finish();
                    break;/*
                case R.id.tx_load_more:
                    break;*/
            }
        }
    }


}

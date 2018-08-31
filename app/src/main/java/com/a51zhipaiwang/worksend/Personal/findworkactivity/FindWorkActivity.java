package com.a51zhipaiwang.worksend.Personal.findworkactivity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.Personal.adapter.FindWorkGridViewAdapter;
import com.a51zhipaiwang.worksend.Personal.adapter.FindWorkListViewAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.detailswebviewactivity.DetailsWebViewActivity;
import com.a51zhipaiwang.worksend.Personal.entity.CommendWork;
import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.Personal.entity.TakeWork;
import com.a51zhipaiwang.worksend.Personal.findworkactivity.contract.IFindWorkContract;
import com.a51zhipaiwang.worksend.Personal.findworkactivity.presenter.IFindWorkPresenter;
import com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.ResumeListSearchActivity;
import com.a51zhipaiwang.worksend.Personal.searchactivity.SearchActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.ListViewMeasureHeightUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.databinding.ActivityFindWorkBinding;

import java.util.ArrayList;
import java.util.HashMap;

import com.a51zhipaiwang.worksend.BR;


public class FindWorkActivity extends BaseActivity implements IFindWorkContract.View {

    private int page = 1;
    private ActivityFindWorkBinding activityFindWorkBinding;
    private ObservableArrayList<TakeWork> takeWorks;
    private ImageView return_image;
    private TextView tilte_text;
    private IFindWorkContract.Presenter presenter;
    private WorkChoiceThreeStage workChoiceThreeStage;
    private TakeWork takeWork;
    private ArrayList arrayList;

    public static void startFindWorkActivity(Context context) {
        context.startActivity(new Intent(context, FindWorkActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        init();
    }

    @Override
    public void setWorkList(ArrayList workList, int type) {
        if (type == REMOVE_ADD) {
            arrayList.clear();
        }
        arrayList.addAll(workList);
        activityFindWorkBinding.livWork.setAdapter(new FindWorkListViewAdapter(FindWorkActivity.this, arrayList));
        ListViewMeasureHeightUtil.meaSureHeight(activityFindWorkBinding.livWork);
        activityFindWorkBinding.livWork.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> ids = new ArrayList<>();
                for (int j = i; j < arrayList.size(); j++) {
                    ids.add(((FindWork) arrayList.get(i)).getDistributeLeafletsDetailsId());
                }
                DetailsWebViewActivity.startDetailsWebViewActivity(FindWorkActivity.this, ids);
            }
        });
    }

    @Override
    public void setRecommend(ArrayList recommendWorkList) {
        activityFindWorkBinding.grvWork.setAdapter(new FindWorkGridViewAdapter(recommendWorkList, FindWorkActivity.this, BR.commendWork));
        ListViewMeasureHeightUtil.meaSureHeight(activityFindWorkBinding.grvWork);
        activityFindWorkBinding.grvWork.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CommendWork commendWork = (CommendWork) view.getTag();
                ResumeListSearchActivity.startResumeListSearchActivity(FindWorkActivity.this, commendWork.getPositionName());
            }
        });

    }

    @Override
    public void setTakeWork(ArrayList takeWorkList) {
        if (takeWorkList != null) {
            takeWorks.addAll(takeWorkList);
        }
    }

    @Override
    public void takeWorkReturn(boolean takeWorkReturn) {
        if (takeWorkReturn) {
            for (int i = 0; i < takeWorks.size(); i++) {
                if (takeWorks.get(i).getCol1().equals(workChoiceThreeStage.getId())){
                    ToastUtil.showToastTwo("已订阅该职位");
                    return;
                }
            }
            TakeWork takeWork = new TakeWork();
            takeWork.setCol1(String.valueOf(workChoiceThreeStage.getId()));
            takeWork.setRecruitmentPosition(workChoiceThreeStage.getPositionName());
            takeWorks.add(takeWork);
        }
        page = 1;
        HashMap workListHash = new HashMap();
        workListHash.put("page", page);
        presenter.getWorkList(workListHash, REMOVE_ADD);
    }

    @Override
    public void deleteWorkReturn(boolean deleteTakeReturn) {
        if (takeWork != null) {
            takeWorks.remove(takeWork);
        }
        page = 1;
        HashMap workListHash = new HashMap();
        workListHash.put("page", page);
        presenter.getWorkList(workListHash, REMOVE_ADD);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    /**
     * 初始化 databinding
     */
    private void initDataBinding() {

        activityFindWorkBinding = DataBindingUtil.setContentView(this, R.layout.activity_find_work);

    }

    private void init() {
        presenter = new IFindWorkPresenter(this);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("找职位");
        takeWorks = new ObservableArrayList<>();
        arrayList = new ArrayList();
        activityFindWorkBinding.setClicklistener(new FindWorkActivityClickListener());
        activityFindWorkBinding.setLongclicklistener(new FindWorkAcitivtyLongClickListener());
        activityFindWorkBinding.setTakeworklist(takeWorks);
        //presenter.getRecommend(new HashMap());
        HashMap workListHash = new HashMap();
        workListHash.put("page", page);
        presenter.getWorkList(workListHash, ADD);
        presenter.getTakeWork(new HashMap());
        presenter.getRecommend(new HashMap());

    }

    private void setRegister() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.WorkClassificationFlag:
                if (resultCode == RESULT_OK) {
                    Bundle workBundle = data.getExtras();
                    workChoiceThreeStage = (WorkChoiceThreeStage) workBundle.getSerializable("workChoiceThreeStage");
                    for (int i = 0; i < takeWorks.size(); i++) {
                        if (takeWorks.get(i).getCol1().equals(String.valueOf(workChoiceThreeStage.getId()))){
                            ToastUtil.showToastTwo("已订阅该职位");
                            return;
                        }
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("positionName", workChoiceThreeStage.getPositionName());
                    hashMap.put("id", workChoiceThreeStage.getId());
                    presenter.takeWork(hashMap);
                    page = 1;
                    HashMap workListHash = new HashMap();
                    workListHash.put("page", page);
                    presenter.getWorkList(workListHash, REMOVE_ADD);
                }
                break;
            case MyApplication.MoreWork:
                if (resultCode == RESULT_OK) {
                    Bundle workBundle = data.getExtras();
                    workChoiceThreeStage = (WorkChoiceThreeStage) workBundle.getSerializable("workChoiceThreeStage");
                    ResumeListSearchActivity.startResumeListSearchActivity(FindWorkActivity.this, workChoiceThreeStage.getPositionName());
                }
                break;
        }
    }

    class FindWorkActivityClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_search_work:
                    SearchActivity.startSearchActivity(FindWorkActivity.this, SearchActivity.WORK);
                    break;
                case R.id.img_add_recommend_work:
                    if (takeWorks.size() < 3) {
                        WorkCassificationActivity.startWorkCassificationActivity(FindWorkActivity.this, MyApplication.WorkClassificationFlag, WorkCassificationActivity.PERSONAL);
                    }else {
                        ToastUtil.showToastTwo("最多订阅3个职位");
                    }
                    /*Intent intent = new Intent(FindWorkActivity.this, WorkCassificationActivity.class);
                    FindWorkActivity.this.startActivityForResult(intent, MyApplication.WorkClassificationFlag);*/
                    break;
                case R.id.lv_more_work:
                    Intent moreIntent = new Intent(FindWorkActivity.this, WorkCassificationActivity.class);
                    FindWorkActivity.this.startActivityForResult(moreIntent, MyApplication.MoreWork);
                    break;
                case R.id.return_image:
                    ActivityCollector.removeActivity(FindWorkActivity.this);
                    //.finish();
                    break;
                case R.id.tx_first_recommend_work:
                    if (view.getTag() != null) {
                        takeWork = (TakeWork) view.getTag();
                        ResumeListSearchActivity.startResumeListSearchActivity(FindWorkActivity.this, takeWork.getRecruitmentPosition());
                    }
                    break;
                case R.id.tx_second_recommend_work:
                    if (view.getTag() != null) {
                        takeWork = (TakeWork) view.getTag();
                        ResumeListSearchActivity.startResumeListSearchActivity(FindWorkActivity.this, takeWork.getRecruitmentPosition());
                    }
                    break;
                case R.id.tx_three_recommend_work:
                    if (view.getTag() != null) {
                        takeWork = (TakeWork) view.getTag();
                        ResumeListSearchActivity.startResumeListSearchActivity(FindWorkActivity.this, takeWork.getRecruitmentPosition());
                    }
                    break;
                case R.id.loadMore:
                    page++;
                    HashMap workListHash = new HashMap();
                    workListHash.put("page", page);
                    presenter.getWorkList(workListHash, ADD);
                    break;

            }
        }
    }

    class FindWorkAcitivtyLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            switch (view.getId()) {
                case R.id.tx_first_recommend_work:
                    if (view.getTag() != null) {
                        takeWork = (TakeWork) view.getTag();
                        HashMap hashMap = new HashMap();
                        hashMap.put("col1", (takeWork.getCol1()));
                        presenter.deleteTakeWork(hashMap);
                    }
                    break;
                case R.id.tx_second_recommend_work:
                    if (view.getTag() != null) {
                        takeWork = (TakeWork) view.getTag();
                        HashMap hashMap = new HashMap();
                        hashMap.put("col1", (takeWork.getCol1()));
                        presenter.deleteTakeWork(hashMap);
                    }
                    break;
                case R.id.tx_three_recommend_work:
                    if (view.getTag() != null) {
                        takeWork = (TakeWork) view.getTag();
                        HashMap hashMap = new HashMap();
                        hashMap.put("col1", (takeWork.getCol1()));
                        presenter.deleteTakeWork(hashMap);
                    }
                    break;
            }
            return true;
        }
    }

}

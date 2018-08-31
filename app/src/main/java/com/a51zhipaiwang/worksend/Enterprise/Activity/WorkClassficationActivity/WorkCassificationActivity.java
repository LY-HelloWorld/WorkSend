package com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkChoice;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkListActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkSearchInfoActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkSearchListActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.WorkClassfyAdapter;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.TwoStageFragment;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkClassfityActivity.IWorkClassficationActivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkClassfityActivity.WorkClassficationPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;



public class WorkCassificationActivity extends BaseActivity implements IWorkClassficationActivity {

    public static final int ENTERPRISE = 1;
    public static final int PERSONAL = 2;

    private ListView workList;
    private LinearLayout returnLayout;

    private IWorkClassficationActivityPresenter iWorkClassficationActivityPresenter;

    private ArrayList<TwoStageFragment> twoStageFragments;

    private FragmentManager fragmentManager;
    private ImageView searchImage;

    private WorkClassficationActivityClickListener workClassficationActivityClickListener;
    private EditText searchEdit;

    private int type;


    public static void startWorkCassificationActivity(AppCompatActivity context, int requestCode, int type){
        Intent intent = new Intent(context, WorkCassificationActivity.class);
        intent.putExtra("type", type);
        context.startActivityForResult(intent, requestCode);
    }

    public static void startWorkCassificationActivityFromFragment(Fragment fragment, int requestCode, int type){
        Intent intent = new Intent(fragment.getActivity(), WorkCassificationActivity.class);
        intent.putExtra("type", type);
        fragment.getActivity().startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_cassification);
        init();
        setRegister();
    }

    public void returnWorkInfo(WorkChoiceThreeStage workChoiceThreeStage){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("workChoiceThreeStage", workChoiceThreeStage);
        intent.putExtras(bundle);
        this.setResult(RESULT_OK, intent);
        this.finish();
        MyLog.e("WorkCassificationActivity", "returnWorkInfo(WorkCassificationActivity.java:83)" + workChoiceThreeStage.getPositionName());
    }

    /**
     * 展示加载动画
     */
    @Override
    public void showLoadingDialog() {

    }

    /**
     * 关闭加载动画
     */
    @Override
    public void closeLoadingDialog() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.WorkSearch:
                if (resultCode == RESULT_OK){
                    returnWorkInfo((WorkChoiceThreeStage) data.getExtras().getSerializable("workChoiceThreeStage"));
                }
                break;
        }
    }

    /**
     * 设置页面相关监听
     */
    public void setRegister(){

        searchImage.setOnClickListener(workClassficationActivityClickListener);

        returnLayout.setOnClickListener(workClassficationActivityClickListener);

        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) searchEdit.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(WorkCassificationActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 搜索，进行自己要的操作...
                    searchWork(searchEdit.getText().toString());

                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 设置工作列表
     * @param workChoices
     */
    @Override
    public void setWorkLiList(final ArrayList<WorkChoice> workChoices) {
        workList.setAdapter(new WorkClassfyAdapter(workChoices));
        for (int i = 0; i < workChoices.size(); i++) {
            TwoStageFragment twoStageFragment = new TwoStageFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("workTwoStage", workChoices.get(i).getChildren());
            twoStageFragment.setArguments(bundle);
            twoStageFragments.add(twoStageFragment);
        }
        //如果没有数据 不添加二级工作菜单
        if (workChoices.size() == 0){
            return;
        }
        //默认添加第一个一级目录的第二级目录
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.vorkFrame, twoStageFragments.get(0))
                .commit();
        workList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //点击一级目录 则切换对应的fragment
                fragmentManager.beginTransaction().replace(R.id.vorkFrame, twoStageFragments.get(i)).commit();
            }
        });
    }

    @Override
    public void setSearchWorkList(ArrayList<WorkChoiceThreeStage> workChoiceThreeStages) {
        if (workChoiceThreeStages == null || workChoiceThreeStages.size() == 0){
            return;
        }
        WorkListActivity.StartWorkListActivity(this, workChoiceThreeStages, "搜索结果");
    }


    /**
     * 初始化
     */
    private void init(){
        type = getIntent().getIntExtra("type", 0);
        iWorkClassficationActivityPresenter = new WorkClassficationPresenter(this, type);
        workClassficationActivityClickListener = new WorkClassficationActivityClickListener();
        twoStageFragments = new ArrayList<>();

        workList = (ListView) findViewById(R.id.workList);
        returnLayout = (LinearLayout) findViewById(R.id.returnLayout);
        searchImage = (ImageView) findViewById(R.id.searchImage);
        searchEdit = (EditText) findViewById(R.id.searchEdit);
        iWorkClassficationActivityPresenter.getWork(new HashMap());
    }


    private void setWorkTabLayoutInfo(){

    }

    private void searchWork(String work){
        WorkSearchListActivity.startWorkSearchListActivity(this, work, MyApplication.WorkSearch);
        //this.startActivity(new Intent(this, WorkSearchInfoActivity.class));
        //WorkSearchList2Activity.startWorkSearchList2Activity(WorkCassificationActivity.this, work, MyApplication.WorkSearch);
        //PersonalHomeActivity.startPersonalHomeAcitivty(this);
    }


    class WorkClassficationActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.returnLayout:
                    WorkCassificationActivity.this.finish();
                    break;
                case R.id.searchImage:
                    MyLog.e("WorkClassficationActivityClickListener", "onClick(WorkClassficationActivityClickListener.java:155)" + "searchImage");
                    if (!TextUtils.isEmpty(searchEdit.getText())){
                        HashMap searchMap = new HashMap();
                        searchMap.put("positionName", searchEdit.getText().toString());
                        iWorkClassficationActivityPresenter.searchWork(searchMap);
                    }else {
                        ToastUtil.showToastTwo("请输入正确的职位信息");
                    }

                    break;
            }
        }
    }



}

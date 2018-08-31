package com.a51zhipaiwang.worksend.Personal.searchactivity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Personal.adapter.SearchWorkAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.HistorySearch;
import com.a51zhipaiwang.worksend.Personal.findcompanyactivity.FindCompanyListActivity;
import com.a51zhipaiwang.worksend.Personal.resumelistsearchactivity.ResumeListSearchActivity;
import com.a51zhipaiwang.worksend.Personal.searchactivity.contract.ISearchContract;
import com.a51zhipaiwang.worksend.Personal.searchactivity.presenter.ISearchPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchActivity extends BaseActivity implements ISearchContract.View {

    public static final int WORK = 1;
    public static final int COMPANY = 2;

    private ActivitySearchBinding activitySearchBinding;
    private ISearchContract.Presenter presenter;

    private ArrayList<WorkChoiceThreeStage> hotWorkArrList;
    private ArrayList historyWorkArrList;

    private String searchType = "";

    public static void startSearchActivity(Context context, int type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(SearchActivity.this, R.layout.activity_search);
        init();
        setRegister();
    }

    @Override
    public void setHistorySearchWork(ArrayList historySearchWorkList) {
        historyWorkArrList = historySearchWorkList;
        activitySearchBinding.gdvSearchHistory.setAdapter(new SearchWorkAdapter(historyWorkArrList, SearchActivity.this));
        activitySearchBinding.gdvSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ResumeListSearchActivity.startResumeListSearchActivity(SearchActivity.this, ((HistorySearch) view.getTag()).getRecentSearch());
                //ToastUtil.showToastTwo(((HistorySearch) view.getTag()).getRecentSearch());
            }
        });
    }

    @Override
    public void setHotSearchWork(ArrayList searchWorkList) {
        if (hotWorkArrList == null) {
            hotWorkArrList = new ArrayList<>();
        }
        for (int i = 0; i < 10; i++) {
            WorkChoiceThreeStage workChoiceThreeStage = new WorkChoiceThreeStage();
            workChoiceThreeStage.setPositionName("产品经理");
            workChoiceThreeStage.setId(i);
            hotWorkArrList.add(workChoiceThreeStage);
        }
        /*activitySearchBinding.gdvHotSearch.setAdapter(new SearchWorkAdapter(hotWorkArrList, SearchActivity.this));
        activitySearchBinding.gdvHotSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.showToastTwo(((WorkChoiceThreeStage) view.getTag()).getPositionName());
            }
        });*/
    }

    @Override
    public void searchWork(String workName) {
        //ToastUtil.showToastTwo(workName);
        if (TextUtils.isEmpty(workName)){
            ToastUtil.showToastTwo("请输入信息");
        }else {
            if (searchType.equals("010")){
                ResumeListSearchActivity.startResumeListSearchActivity(SearchActivity.this, workName);
            }else {
                FindCompanyListActivity.startFindCompanyListActivity(SearchActivity.this, workName);
            }
        }
    }

    @Override
    public void deleteHistorySearchWork(boolean delete) {
        if (delete) {
            historyWorkArrList.clear();
            ((SearchWorkAdapter) activitySearchBinding.gdvSearchHistory.getAdapter()).notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init() {
        presenter = new ISearchPresenter(this);
        presenter.getHotSearchWork(new HashMap());
        presenter.getHistorySearchWork(new HashMap());
        activitySearchBinding.edxSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) activitySearchBinding.edxSearch.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 搜索，进行自己要的操作...
                    searchWork(activitySearchBinding.edxSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
        if (getIntent().getIntExtra("type", 0) == WORK){
            activitySearchBinding.spnStype.setSelection(0);
        }
        if (getIntent().getIntExtra("type", 0) == COMPANY){
            activitySearchBinding.spnStype.setSelection(1);
        }
    }

    private void setRegister() {
        activitySearchBinding.setClicklistener(new SearchActivityClickListener());
        activitySearchBinding.spnStype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    searchType = "010";
                }else {
                    searchType = "020";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    class SearchActivityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_delete_history:
                    presenter.deleteHistorySearch(new HashMap());
                    break;
                case R.id.tx_cancel:
                    SearchActivity.this.finish();
                    break;
            }
        }
    }


}

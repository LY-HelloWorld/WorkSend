package com.a51zhipaiwang.worksend.Enterprise.Activity.WorkManagerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.PaiDanActivity.PaiDanActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkManagerActivity.IWorkManagerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.WorkManagerAdapter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkManagerActivityPresenter.IWorkManagerPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkManagerActivityPresenter.WorkManagerPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;


public class WorkManagerActivity extends BaseActivity implements IWorkManagerActivity {

    private ListView workManagerList;
    private WorkManagerAdapter workManagerAdapter;

    private ArrayList<WorkBean> workBeans;
    private ImageView personal_center;

    private WorkManagerClickListener workManagerClickListener;
    private ImageView return_image;

    private IWorkManagerPresenter iWorkManagerPresenter;
    private TextView tilte_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        init();
        setRegister();
    }

    /**
     * 初始化
     */
    private void init() {

        iWorkManagerPresenter = new WorkManagerPresenter(this);
        workBeans = new ArrayList<>();
        workManagerClickListener = new WorkManagerClickListener();
        workManagerList = (ListView) findViewById(R.id.workManagerList);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("职位管理");
        personal_center = (ImageView) findViewById(R.id.personal_center);
        return_image = (ImageView) findViewById(R.id.return_image);
        personal_center.setVisibility(View.VISIBLE);
        personal_center.setImageResource(R.mipmap.add_work_image);

        iWorkManagerPresenter.getWork();
    }

    /**
     * 设置监听
     */
    private void setRegister() {

        personal_center.setOnClickListener(workManagerClickListener);
        return_image.setOnClickListener(workManagerClickListener);

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setWorkList(ArrayList<WorkBean> workBeans) {
        if (workBeans == null || workBeans.size() == 0){
            ToastUtil.showToastTwo("提示未发布职业");
        }
        this.workBeans = workBeans;
        workManagerAdapter = new WorkManagerAdapter(true, this, workBeans, workManagerClickListener, iWorkManagerPresenter);
        workManagerList.setAdapter(workManagerAdapter);
    }

    @Override
    public void deleteWork(String id) {
        MyLog.e("WorkManagerActivity", "deleteWork(WorkManagerActivity.java:92)" + id);
        for (WorkBean workBean: workBeans) {
            MyLog.e("WorkManagerActivity", "deleteWork(WorkManagerActivity.java:93)" + workBean.getId());
            if (workBean.getId().equals(id)){
                workBeans.remove(workBean);
                break;
            }
        }
        MyLog.e("WorkManagerActivity", "deleteWork(WorkManagerActivity.java:96)" + workBeans.size());
        workManagerAdapter.notifyDataSetChanged();
        ToastUtil.showToastTwo("删除职位成功!");
    }

    @Override
    public void addWork(boolean addOrNot) {

    }

    @Override
    public void editWork(WorkBean workBean) {
        for (int i = 0; i < workBeans.size(); i++) {
            if (workBeans.get(i).getId().equals(workBean.getId())){
                workBeans.add(i, workBean);
            }
        }
        workManagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MyApplication.AddWork:
                if (resultCode == RESULT_OK){
                    Bundle workBundle = data.getExtras();
                    WorkBean workBean = (WorkBean) workBundle.getSerializable("work");
                    //如果id相同则是修改
                    for (int i = 0; i < workBeans.size(); i++) {
                        if (workBeans.get(i).getId().equals(workBean.getId())){
                            workBeans.add(i, workBean);
                            workManagerAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                    //否则是添加
                    workBeans.add(workBean);
                    workManagerAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    class WorkManagerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.editJianLiBt:
                    WorkBean workBean = (WorkBean) view.getTag();
                    ToastUtil.showToastTwo("编辑");
                    MyLog.e("WorkManagerClickListener", "onClick(WorkManagerClickListener.java:155)" + workBean.getId());
                    PaiDanActivity.StartPaiDanActivity(WorkManagerActivity.this, workBean);
                    break;
                case R.id.personal_center:
                    PaiDanActivity.StartPaiDanActivity(WorkManagerActivity.this, new WorkBean());
                    break;
                case R.id.return_image:
                    WorkManagerActivity.this.finish();
                    break;
            }
        }
    }
}

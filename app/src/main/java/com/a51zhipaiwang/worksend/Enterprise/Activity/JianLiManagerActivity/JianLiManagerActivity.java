package com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiManagerActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiManagerActivity.IJianLiManagerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.JianLiAdapter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiActivtyPresenter.IJianLiManagerActivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiActivtyPresenter.JianLiManagerActivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;


public class JianLiManagerActivity extends BaseActivity implements IJianLiManagerActivity {

    private ListView jianLiManagerList;
    private ImageView return_image;
    private ImageView personal_center;
    private TextView tilte_text;

    private IJianLiManagerActivityPresenter iJianLiManagerActivityPresenter;

    private JianLiAdapter jianLiAdapter;

    private ArrayList<SampleJianLiData> sampleJianLiData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_li_manager);
        init();
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("longitude", MyApplication.longitude);
        hashMap.put("latitude", MyApplication.latitude);
        iJianLiManagerActivityPresenter.getJianLi(hashMap);

    }

    /**
     * 初始化
     */
    private void init(){
        iJianLiManagerActivityPresenter = new JianLiManagerActivityPresenter(this);
        //初始化列表
        jianLiManagerList = (ListView) findViewById(R.id.jianLiManagerList);
        //初始化返回键 和编辑按键
        return_image = (ImageView) findViewById(R.id.return_image);
        personal_center = (ImageView) findViewById(R.id.personal_center);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("简历管理");
        setTitleImage();
    }

    /**
     * 设置监听
     */
    private void setRegister(){
        //jianLiManagerList.setAdapter(new JianLiAdapter(true, JianLiManagerActivity.this, true, 3, ));
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JianLiManagerActivity.this.finish();
            }
        });
    }

    /**
     * 配置title显示图片
     */
    private void setTitleImage(){
        return_image.setVisibility(View.VISIBLE);
        personal_center.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setJianLiList(ArrayList<SampleJianLiData> sampleJianLiData) {
        if (sampleJianLiData == null || sampleJianLiData.size() == 0){
            ToastUtil.showToastTwo("发布职位后才有简历管理");
        }
        this.sampleJianLiData = sampleJianLiData;
        jianLiAdapter = new JianLiAdapter(true, this, true, 3, sampleJianLiData);
        jianLiManagerList.setAdapter(jianLiAdapter);
    }

    @Override
    public void deletJianLiList(String id) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", id);
        iJianLiManagerActivityPresenter.deleteJianLi(hashMap);
    }

    @Override
    public void deletJianLiReturn(String id) {
        for (int i = 0; i < sampleJianLiData.size(); i++) {
            if (sampleJianLiData.get(i).getId().equals(id)){
                sampleJianLiData.remove(i);
                break;
            }
        }
        jianLiAdapter.notifyDataSetChanged();
    }
}

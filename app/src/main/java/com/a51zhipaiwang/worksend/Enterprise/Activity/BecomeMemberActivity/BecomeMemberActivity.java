package com.a51zhipaiwang.worksend.Enterprise.Activity.BecomeMemberActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BecomeMemberActivity.IBecomeMemberActivity;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBecomeMemberPresenter.BecomeMemberAcitivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBecomeMemberPresenter.IBecomeMemberAcitivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;

import java.util.HashMap;
public class BecomeMemberActivity extends BaseActivity implements IBecomeMemberActivity {

    private BusinessInfo businessInfo;
    private GlideUtil glideUtil;

    private ProgressBar pbar_member;
    private ImageView img_head;
    private TextView tx_business_name;
    private ImageView return_image;
    private TextView tx_current_info;
    private TextView tx_grade;
    private TextView tx_gap_next;

    private IBecomeMemberAcitivityPresenter iBecomeMemberAcitivityPresenter;


    public static void StartBecomeMemberActivity(Context context, BusinessInfo businessInfo){

        Intent intent = new Intent(context, BecomeMemberActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("businessInfo", businessInfo);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_member);
        init();
        setRegister();
        queryMember();
        initBusinessInfo();
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setMemberInfo(String memberInfo) {
        int iMember = Integer.valueOf(memberInfo);
        pbar_member.setProgress(iMember);
        tx_current_info.setText(iMember + "/" + "5600");
        if (iMember < 800){
            tx_grade.setText("1");
            tx_gap_next.setText("距下一等级还差" + (800 - iMember));
        }
        if (iMember >= 800 && iMember < 1600){
            tx_grade.setText("2");
            tx_gap_next.setText("距下一等级还差" + (1600 - iMember));
        }
        if (iMember >= 1600 && iMember < 2400){
            tx_grade.setText("3");
            tx_gap_next.setText("距下一等级还差" + (2400 - iMember));
        }
        if (iMember >= 2400 && iMember < 3200){
            tx_grade.setText("4");
            tx_gap_next.setText("距下一等级还差" + (3200 - iMember));
        }
        if (iMember >= 3200 && iMember < 4000){
            tx_grade.setText("5");
            tx_gap_next.setText("距下一等级还差" + (4000 - iMember));
        }
        if (iMember >= 4000 && iMember < 4800){
            tx_grade.setText("6");
            tx_gap_next.setText("距下一等级还差" + (4800 - iMember));
        }
        if (iMember >= 4800){
            tx_grade.setText("7");
            tx_gap_next.setText("满级会员");
        }

    }

    private void init(){
        businessInfo = (BusinessInfo) getIntent().getExtras().getSerializable("businessInfo");
        glideUtil = new GlideUtil();
        pbar_member = (ProgressBar) findViewById(R.id.pbar_member);
        img_head = (ImageView) findViewById(R.id.img_head);
        tx_business_name = (TextView) findViewById(R.id.tx_business_name);
        return_image = (ImageView) findViewById(R.id.return_image);
        tx_current_info = (TextView) findViewById(R.id.tx_current_info);
        tx_grade = (TextView) findViewById(R.id.tx_grade);
        tx_gap_next = (TextView) findViewById(R.id.tx_gap_next);

        iBecomeMemberAcitivityPresenter = new BecomeMemberAcitivityPresenter(this);
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BecomeMemberActivity.this.finish();
            }
        });
    }

    private void queryMember(){
        iBecomeMemberAcitivityPresenter.queryMember(new HashMap());
    }

    private void initBusinessInfo(){
        if (businessInfo != null){
            glideUtil.GlideImage(businessInfo.getEnterpriseLogo(), this, img_head);
            tx_business_name.setText(businessInfo.getEnterpriseName());
        }
    }

}

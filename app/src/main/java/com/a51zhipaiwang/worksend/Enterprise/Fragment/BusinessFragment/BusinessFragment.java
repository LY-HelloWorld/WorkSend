package com.a51zhipaiwang.worksend.Enterprise.Fragment.BusinessFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.CommonActivity.MoneyActivity.MoneyActivity;
import com.a51zhipaiwang.worksend.CommonActivity.SendBackActivity.SendBackIdeaActivity;
import com.a51zhipaiwang.worksend.CommonActivity.SetActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BecomeMemberActivity.BecomeMemberActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessIdentifyActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfoWebView;
import com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiManagerActivity.JianLiManagerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.ServiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkManagerActivity.WorkManagerActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BaseFragment;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessFragmentPresenter.BusinessFragmentPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessFragmentPresenter.IBusinessFragmentPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.MyLog;

import java.util.HashMap;


public class BusinessFragment extends BaseFragment implements IBusinessFragment{

    private BusinessClickListener businessClickListener;
    private IBusinessFragmentPresenter presenter;
    private BusinessInfo businessInfo;
    private GlideUtil glideUtil;

    private BroadcastReceiver broadcastReceiver;

    private LinearLayout jianLiManagerLayout;
    private LinearLayout workMangerLayout;
    private LinearLayout businessIndentifyLayout;
    private LinearLayout setLayout;

    private TextView tilte_text;
    private ImageView return_image;

    private int type = 1;
    private LinearLayout businessInfoLayout;
    private LinearLayout lv_money;
    private LinearLayout lv_become_member;
    private LinearLayout lv_service_activity;
    private LinearLayout lv_title_layout;
    private ImageView img_business_logo;
    private TextView tx_business_name;
    private ImageView img_identification;
    private TextView tx_identification;
    private LinearLayout lv_send_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business, null);
        init(view);
        setRegister();
        presenter.getBusinessInfo(new HashMap());
        initBroadCastReceiver();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setBusinessInfo(Object businessInfo) {
        if (businessInfo != null){
            this.businessInfo = (BusinessInfo) businessInfo;
            initBusinessInfo(this.businessInfo);
        }
    }

    private void initBusinessInfo(BusinessInfo businessInfo){
        if (businessInfo != null){
            Context context = getActivity();
            if (TextUtils.isEmpty(businessInfo.getEnterpriseLogo())){
                glideUtil.GlideImage(R.drawable.icon_round, context, img_business_logo);
            }else {
                glideUtil.GlideImage(businessInfo.getEnterpriseLogo(), context, img_business_logo, R.drawable.icon_round);
            }
            if (TextUtils.isEmpty(businessInfo.getEnterpriseName())){
                tx_business_name.setText("未完善企业地址");
            }else {
                tx_business_name.setText(businessInfo.getEnterpriseName());
            }
            if ("040".equals(businessInfo.getAuthenticationState())){
                tx_identification.setText("已认证");
                glideUtil.GlideImage(R.drawable.img_identifiy, context, img_identification);
            }else {
                tx_identification.setText("未认证");
                glideUtil.GlideImage(R.mipmap.ren_zheng, context, img_identification);
            }
        }else {
            this.businessInfo = businessInfo;
        }
    }

    private void initBroadCastReceiver(){
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                MyLog.e("BusinessFragment", "onReceive(BusinessFragment.java:132)" + "initBroadCastReceiver");
                switch (intent.getAction()) {
                    case MyApplication.CHANGE_BUSINESS_FLAG:
                        presenter.getBusinessInfo(new HashMap());
                        break;
                }
            }
        };
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(MyApplication.CHANGE_BUSINESS_FLAG));
    }

    /**
     * 初始化数据
     * @param view
     */
    private void init(View view){
        glideUtil = new GlideUtil();
        presenter = new BusinessFragmentPresenter(this);
        tilte_text = (TextView) view.findViewById(R.id.tilte_text);
        return_image = (ImageView) view.findViewById(R.id.return_image);
        return_image.setVisibility(View.INVISIBLE);

        businessClickListener = new BusinessClickListener();

        jianLiManagerLayout = (LinearLayout) view.findViewById(R.id.jianLiManagerLayout);
        workMangerLayout = (LinearLayout) view.findViewById(R.id.workMangerLayout);
        businessIndentifyLayout = (LinearLayout) view.findViewById(R.id.businessIndentifyLayout);
        setLayout = (LinearLayout) view.findViewById(R.id.setLayout);
        businessInfoLayout = (LinearLayout) view.findViewById(R.id.businessInfoLayout);
        lv_money = (LinearLayout) view.findViewById(R.id.lv_money);
        lv_become_member = (LinearLayout) view.findViewById(R.id.lv_become_member);
        lv_service_activity = (LinearLayout) view.findViewById(R.id.lv_service_activity);
        lv_title_layout = (LinearLayout) view.findViewById(R.id.lv_title_layout);
        lv_send_back = (LinearLayout) view.findViewById(R.id.lv_send_back);
        lv_title_layout.setBackgroundColor(getActivity().getResources().getColor(R.color.translate));

        img_business_logo = (ImageView) view.findViewById(R.id.img_business_logo);
        tx_business_name = (TextView) view.findViewById(R.id.tx_business_name);
        img_identification = (ImageView) view.findViewById(R.id.img_identification);
        tx_identification = (TextView) view.findViewById(R.id.tx_identification);


    }

    private void setRegister(){
        jianLiManagerLayout.setOnClickListener(businessClickListener);
        workMangerLayout.setOnClickListener(businessClickListener);
        businessIndentifyLayout.setOnClickListener(businessClickListener);
        setLayout.setOnClickListener(businessClickListener);
        return_image.setOnClickListener(businessClickListener);
        businessInfoLayout.setOnClickListener(businessClickListener);
        lv_money.setOnClickListener(businessClickListener);
        lv_become_member.setOnClickListener(businessClickListener);
        lv_service_activity.setOnClickListener(businessClickListener);
        lv_send_back.setOnClickListener(businessClickListener);

    }


    private class BusinessClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.jianLiManagerLayout:
                    getActivity().startActivity(new Intent(getActivity(), JianLiManagerActivity.class));
                    break;
                case R.id.workMangerLayout:
                    getActivity().startActivity(new Intent(getActivity(), WorkManagerActivity.class));
                    break;
                case R.id.businessIndentifyLayout:
                    BusinessIdentifyActivity.startBusinessActivity(getActivity(), businessInfo.getAuthenticationState(), "企业认证");
                    break;
                case R.id.setLayout:
                    SetActivity.startSetActivity(getActivity(), SetActivity.ENTERPRISE);
                    break;
                case R.id.return_image:
                    getActivity().finish();
                    break;
                case R.id.businessInfoLayout:
                    BusinessInfoWebView.StartBusinessWebView(getActivity(), "", "企业信息");
                    break;
                case R.id.lv_money:
                    MoneyActivity.StartMoneyActivity(getActivity(), "我的钱包", MoneyActivity.ENTERPRISE);
                    break;
                case R.id.lv_become_member:
                    if ("040".equals(businessInfo.getAuthenticationState())){
                        BecomeMemberActivity.StartBecomeMemberActivity(getActivity(), businessInfo);
                    }else {
                        BusinessIdentifyActivity.startBusinessActivity(getActivity(), businessInfo.getAuthenticationState(), "企业认证");
                    }
                    break;
                case R.id.lv_service_activity:
                    ServiceActivity.StartServiceActivity(getActivity(), "客服中心");
                    break;
                case R.id.lv_send_back:
                    SendBackIdeaActivity.startSendBackIdeaActivity(getActivity(), businessInfo);
                    break;

            }
        }
    }


}

package com.a51zhipaiwang.worksend.Personal.minefragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.CommonActivity.MoneyActivity.MoneyActivity;
import com.a51zhipaiwang.worksend.CommonActivity.SetActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.entity.UserInfo;
import com.a51zhipaiwang.worksend.Personal.minefragment.contract.IMineContract;
import com.a51zhipaiwang.worksend.Personal.minefragment.presenter.IMinePresenter;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.ResumeEditActivity;
import com.a51zhipaiwang.worksend.Personal.trypathactivity.TryPathActivity;
import com.a51zhipaiwang.worksend.Personal.userinfoactivity.UserInfoActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.MyLog;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class MineFragment extends BaseFragment implements IMineContract.View{


    private MineFragmentClickListener mineFragmentClickListener;
    private MineFragmentBroadCastReceiver mineFragmentBroadCastReceiver;
    private GlideUtil glideUtil;
    private IMineContract.Presenter presenter;
    private LinearLayout lv_user_info;
    private LinearLayout lv_user_money;
    private LinearLayout lv_user_resume;
    private LinearLayout lv_try_path;
    private LinearLayout lv_set;

    private UserInfo userInfo;
    private CircleImageView circle_image;
    private TextView tx_user_name;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerBrocastReceiver(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_mine, null);
        init(view);
        setRegister();
        presenter.getUserInfo(new HashMap());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mineFragmentBroadCastReceiver);
    }

    @Override
    public void setUserInfo(Object object) {
        if (object != null){
            userInfo = (UserInfo) object;
            glideUtil.GlideImage(userInfo.getUserImg(), getActivity(), circle_image);
            //glideUtil.GlideImage("", getActivity(), circle_image);
            tx_user_name.setText(userInfo.getUsername());
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }


    private void init(View view){
        presenter = new IMinePresenter(this);
        glideUtil = new GlideUtil();
        lv_user_info = (LinearLayout) view.findViewById(R.id.lv_user_info);
        lv_user_money = (LinearLayout) view.findViewById(R.id.lv_user_money);
        lv_user_resume = (LinearLayout) view.findViewById(R.id.lv_user_resume);
        lv_try_path = (LinearLayout) view.findViewById(R.id.lv_try_path);
        lv_set = (LinearLayout) view.findViewById(R.id.lv_set);
        circle_image = (CircleImageView) view.findViewById(R.id.circle_image);
        tx_user_name = (TextView) view.findViewById(R.id.tx_user_name);
    }

    private void setRegister(){
        mineFragmentClickListener = new MineFragmentClickListener();
        lv_user_info.setOnClickListener(mineFragmentClickListener);
        lv_user_money.setOnClickListener(mineFragmentClickListener);
        lv_user_resume.setOnClickListener(mineFragmentClickListener);
        lv_try_path.setOnClickListener(mineFragmentClickListener);
        lv_set.setOnClickListener(mineFragmentClickListener);
    }


    private void registerBrocastReceiver(Context context){
        mineFragmentBroadCastReceiver = new MineFragmentBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyApplication.CHANGE_USER_IMAGE_FLAG);   //添加action
        context.registerReceiver(mineFragmentBroadCastReceiver,intentFilter);
    }

    class MineFragmentClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lv_user_info:
                    UserInfoActivity.StartUserInfoActivity(getActivity());
                    break;
                case R.id.lv_user_money:
                    MoneyActivity.StartMoneyActivity(getActivity(), "我的钱包", MoneyActivity.PERSONAL);
                    break;
                case R.id.lv_user_resume:
                    ResumeEditActivity.startResumeEditActivity(getActivity());
                    break;
                case R.id.lv_try_path:
                    if (getActivity() != null){
                        TryPathActivity.StartTryPathActivity(getActivity());
                    }
                    break;
                case R.id.lv_set:
                    if (getActivity() != null){
                        SetActivity.startSetActivity(getActivity(), SetActivity.PERSONAL);
                    }
                    break;

            }
        }
    }

    class MineFragmentBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null){
                switch (intent.getAction()) {
                    case MyApplication.CHANGE_USER_IMAGE_FLAG:
                        if (glideUtil != null && circle_image != null){
                            UserInfo userInfo = (UserInfo) intent.getExtras().getSerializable("userInfo");
                            setUserInfo(userInfo);
                        }
                        break;
                }
            }
        }
    }


}

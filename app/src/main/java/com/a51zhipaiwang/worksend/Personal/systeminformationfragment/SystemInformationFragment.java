package com.a51zhipaiwang.worksend.Personal.systeminformationfragment;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.base.BaseInfomationFragment;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.systeminformationactivity.SystemInfomationActivity;
import com.a51zhipaiwang.worksend.Personal.systeminformationfragment.contract.ISystemInformationContract;
import com.a51zhipaiwang.worksend.Personal.systeminformationfragment.presenter.ISystemInformationPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.HashMap;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/25
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
public class SystemInformationFragment extends BaseInfomationFragment implements ISystemInformationContract.View{

    private ISystemInformationContract.Presenter presenter;
    private LocalReceiver localReceiver;

    private boolean showOrNot = false;
    private TextView tx_notification;
    private TextView tx_time;
    private TextView tx_read;
    private ImageView img_delete_infomation;
    private LinearLayout lv_information;

    private boolean flag = true;
    private Thread getInfoThread;

    public static SystemInformationFragment newInstance(){
        return new SystemInformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_systeminformation, null);
        init(view);
        setRegister();
        initBroadCast();

        getInfoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    getInfo();
                    MyLog.e("HRInformationFragment", "run(HRInformationFragment.java:71)" + "getInfo");
                    try {
                        Thread.sleep(1 * 60 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getInfoThread.start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(localReceiver);
        flag = false;
        getInfoThread.interrupt();
    }

    @Override
    public void setSystemInformation(Object object) {
        if (object != null){
            lv_information.setVisibility(View.VISIBLE);
            Infor infor = (Infor) object;
            tx_time.setText(infor.getCreationtime());
            if (infor.getNewMessage().equals("010")){
                tx_read.setText("未读");
                tx_read.setTextColor(getResources().getColor(R.color.red));
            }else {
                tx_read.setText("已读");
                tx_read.setTextColor(getResources().getColor(R.color.blue_green));
            }
        }else {
            lv_information.setVisibility(View.GONE);
        }
    }

    @Override
    public void setDeleteReturn(boolean bDeleteReturn) {
        if (bDeleteReturn){
            lv_information.setVisibility(View.GONE);
        }else {
            ToastUtil.showToastTwo("请检查您的网络连接");
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(View view){
        presenter = new ISystemInformationPresenter(this);
        tx_notification = (TextView) view.findViewById(R.id.tx_notification);
        tx_time = (TextView) view.findViewById(R.id.tx_time);
        tx_read = (TextView) view.findViewById(R.id.tx_read);
        img_delete_infomation = (ImageView) view.findViewById(R.id.img_delete_infomation);
        lv_information = (LinearLayout) view.findViewById(R.id.lv_information);


    }

    private void setRegister(){
        img_delete_infomation.setOnClickListener(new SystemInformationClicklistener());
        lv_information.setOnClickListener(new SystemInformationClicklistener());
    }
    /**
     * 初始化广播监听
     */
    private void initBroadCast(){

        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCAL_BROADCAST);   //添加action
        intentFilter.addAction(SHOW_DELETE_BROADCAST);   //添加action
        getActivity().registerReceiver(localReceiver,intentFilter);
    }

    private void getInfo(){

        HashMap hashMap = new HashMap();
        hashMap.put("userMessageState", "040");
        presenter.getSystemInformation(hashMap);
    }

    class SystemInformationClicklistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lv_information:
                    tx_read.setText("已读");
                    tx_read.setTextColor(getResources().getColor(R.color.blue_green));
                    SystemInfomationActivity.startSystemInformationActivity(getActivity());
                    break;
                case R.id.img_delete_infomation:
                    HashMap hashMap = new HashMap();
                    hashMap.put("", "040");
                    presenter.deleteSystemInfomation(hashMap);
                    break;
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case SHOW_DELETE_BROADCAST:
                    if (!showOrNot){
                        img_delete_infomation.setVisibility(View.VISIBLE);
                    }else {
                        img_delete_infomation.setVisibility(View.GONE);
                    }
                    showOrNot = !showOrNot;
                    break;
            }
        }
    }
}

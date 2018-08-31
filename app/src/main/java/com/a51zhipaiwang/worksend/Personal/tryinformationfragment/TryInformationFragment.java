package com.a51zhipaiwang.worksend.Personal.tryinformationfragment;


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
import android.widget.AdapterView;
import android.widget.ListView;

import com.a51zhipaiwang.worksend.Personal.adapter.TryInfomationListAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseInfomationFragment;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.tryinformationactivity.TryInfomationActivity;
import com.a51zhipaiwang.worksend.Personal.tryinformationfragment.contract.ITryInfomationContract;
import com.a51zhipaiwang.worksend.Personal.tryinformationfragment.presenter.ITryInfomationPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/24
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
public class TryInformationFragment extends BaseInfomationFragment implements ITryInfomationContract.View{

    private ITryInfomationContract.Presenter presenter;
    private ListView liv_try_information;
    private LocalReceiver localReceiver;

    private TryInfomationListAdapter tryInfomationListAdapter;

    private ArrayList infoArray;

    private boolean flag = true;
    private Thread getInfoThread;

    public static TryInformationFragment newInstance(){
        return new TryInformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_try_infomation, null);
        init(view);
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
    public void setTryInformation(ArrayList arrayList) {
        infoArray = arrayList;
        tryInfomationListAdapter = new TryInfomationListAdapter(infoArray, getActivity());
        liv_try_information.setAdapter(tryInfomationListAdapter);
        liv_try_information.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Infor infor = (Infor) infoArray.get(i);
                ((Infor) infoArray.get(i)).setNewMessage("020");
                tryInfomationListAdapter.notifyDataSetChanged();
                TryInfomationActivity.startTryInfomationActivity(getActivity(), infor.getId());
            }
        });
    }

    @Override
    public void deleteReturnInfo(String id) {
        for (int i = 0, len = infoArray.size(); i < len; i++) {
            if (((Infor)infoArray.get(i)).getId().equals(id)){
                infoArray.remove(i);
                tryInfomationListAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(View view){
        presenter = new ITryInfomationPresenter(this);
        liv_try_information = (ListView) view.findViewById(R.id.liv_try_information);
    }

    private void setRegister(){

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
        hashMap.put("userMessageState", "030");
        presenter.getTryInformation(hashMap);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case LOCAL_BROADCAST:
                    int index = intent.getIntExtra("index", 0);
                    HashMap hashMap = new HashMap();
                    hashMap.put("id", ((Infor)infoArray.get(index)).getId());
                    presenter.deleteTryInformation(hashMap);
                    break;
                case SHOW_DELETE_BROADCAST:
                    if (tryInfomationListAdapter != null){
                        tryInfomationListAdapter.changeShowOrNot();
                        tryInfomationListAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

}

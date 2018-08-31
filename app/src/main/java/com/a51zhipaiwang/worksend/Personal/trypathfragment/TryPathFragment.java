package com.a51zhipaiwang.worksend.Personal.trypathfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.adapter.TryPathAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.contract.ITryPathContract;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.presenter.ITryPathPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/20
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
public class TryPathFragment extends BaseFragment implements ITryPathContract.View{

    public static final int TRY_PATH_ING = 1;
    public static final int TRY_PATH_OUT = 2;

    private ListView liv_try_path;
    private ITryPathContract.Presenter presenter;

    public static TryPathFragment newInStance(int type){
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        TryPathFragment tryPathFragment = new TryPathFragment();
        tryPathFragment.setArguments(bundle);
        return tryPathFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_try_path, null);
        init(view);
        getList();
        return view;
    }

    @Override
    public void setTryWorking(ArrayList tryWorkingList) {
        setAdapter(tryWorkingList, TRY_PATH_ING);
    }

    @Override
    public void setTryOutWork(ArrayList tryOutWorkList) {
        setAdapter(tryOutWorkList, TRY_PATH_OUT);
    }

    @Override
    public void setReturnInfo(String info) {
        ToastUtil.showToastTwo(info);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(View view){
        liv_try_path = (ListView) view.findViewById(R.id.liv_try_path);
        presenter = new ITryPathPresenter(this);
    }

    private void getList(){
        HashMap hashMap = new HashMap();
        switch (getArguments().getInt("type", 0)) {
            case TRY_PATH_ING:
                hashMap.put("trialPostState", "010");
                presenter.getTryWorking(hashMap);
                break;
            case TRY_PATH_OUT:
                hashMap.put("trialPostState", "020");
                presenter.getTryOutWork(hashMap);
                break;
        }
    }

    private void setAdapter(ArrayList workList, int type){
        liv_try_path.setAdapter(new TryPathAdapter(getActivity(), true, workList, type));
    }


}

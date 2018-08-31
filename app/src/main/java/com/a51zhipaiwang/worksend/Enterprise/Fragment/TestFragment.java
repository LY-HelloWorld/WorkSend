package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.R;


public class TestFragment extends BaseFragment {

    private TextView viewById;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        init(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewById.setText(getArguments().getString("content") + "");
    }

    /**
     * 初始化
     */
    private void init(View view){
        viewById = (TextView) view.findViewById(R.id.testText);
    }

    /**
     * 设置监听
     */
    private void setRegister(){

    }


}

package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a51zhipaiwang.worksend.R;


public class ShenHeFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shen_he, null);
        init(view);
        setRegister();
        return view;
    }

    private void init(View view){

    }

    private void setRegister(){

    }


}

package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceTwoStage;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.TwoStageListAdapter;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;


public class TwoStageFragment extends BaseFragment {

    private ListView twoStageList;

    private ArrayList<WorkChoiceTwoStage> workChoiceTwoStages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_stage, null);
        init(view);
        setRegister();
        setAdatapter();
        return view;
    }

    private void init(View view){
        workChoiceTwoStages = (ArrayList<WorkChoiceTwoStage>) getArguments().getSerializable("workTwoStage");
        twoStageList = (ListView) view.findViewById(R.id.twoStageList);
    }

    private void setRegister(){

    }

    private void setAdatapter(){
        twoStageList.setAdapter(new TwoStageListAdapter(true, workChoiceTwoStages, getActivity()));
    }



}

package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;

public class WorkListAdapter extends BaseAdapter {

    private ArrayList<WorkChoiceThreeStage> workChoiceThreeStages;

    private Context context;

    private int showCount;

    private boolean showAll;

    public WorkListAdapter(ArrayList<WorkChoiceThreeStage> workChoiceThreeStages, Context context, int showCount) {
        this.workChoiceThreeStages = workChoiceThreeStages;
        this.context = context;
        this.showCount = showCount;
    }

    public WorkListAdapter(ArrayList<WorkChoiceThreeStage> workChoiceThreeStages, Context context, boolean showAll) {
        this.workChoiceThreeStages = workChoiceThreeStages;
        this.context = context;
        this.showAll = showAll;
    }

    @Override
    public int getCount() {
        if (workChoiceThreeStages == null){
            return 0;
        }
        if (showAll){
            return workChoiceThreeStages.size();
        }
        if (workChoiceThreeStages.size() >= showCount){
            return showCount;
        }else {
            return workChoiceThreeStages.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return workChoiceThreeStages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_work_list, null);
        ((TextView)view.findViewById(R.id.workName)).setText(workChoiceThreeStages.get(i).getPositionName());
        return view;
    }
}

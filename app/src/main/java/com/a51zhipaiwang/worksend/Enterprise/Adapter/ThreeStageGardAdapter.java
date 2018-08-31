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

public class ThreeStageGardAdapter extends BaseAdapter {

    //全部展示
    private boolean showAll = false;
    //展示个数
    private int showCount = 0;

    private ArrayList<WorkChoiceThreeStage> workChoiceThreeStages;

    private Context context;

    public ThreeStageGardAdapter(boolean showAll, ArrayList<WorkChoiceThreeStage> workChoiceThreeStages, Context context) {
        this.showAll = showAll;
        this.workChoiceThreeStages = workChoiceThreeStages;
        this.context = context;
    }

    public ThreeStageGardAdapter(int showCount, ArrayList<WorkChoiceThreeStage> workChoiceThreeStages, Context context) {
        this.showCount = showCount;
        this.workChoiceThreeStages = workChoiceThreeStages;
        this.context = context;
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

        ThreeStageGardHolder threeStageGardHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_grid_work, null);
            threeStageGardHolder = new ThreeStageGardHolder();
            threeStageGardHolder.three_stage_text = view.findViewById(R.id.three_stage_text);
            view.setTag(threeStageGardHolder);
        }else {
            threeStageGardHolder = (ThreeStageGardHolder) view.getTag();
        }
        threeStageGardHolder.three_stage_text.setText(workChoiceThreeStages.get(i).getPositionName());
        return view;
    }

    class ThreeStageGardHolder{

        TextView three_stage_text;

        public ThreeStageGardHolder() {
        }
    }




}

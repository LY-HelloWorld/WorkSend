package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceTwoStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ListViewMeasureHeightUtil;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;

/**
 * 二级 目录适配器
 */
public class TwoStageListAdapter extends BaseAdapter {


    //全部展示
    private boolean showAll = false;
    //展示个数
    private int showCount = 0;

    private ArrayList<WorkChoiceTwoStage> workChoiceTwoStages;

    private Context context;

    public TwoStageListAdapter(boolean showAll, ArrayList<WorkChoiceTwoStage> workChoiceTwoStages, Context context) {
        this.showAll = showAll;
        this.workChoiceTwoStages = workChoiceTwoStages;
        this.context = context;
    }

    public TwoStageListAdapter(int showCount, ArrayList<WorkChoiceTwoStage> workChoiceTwoStages, Context context) {
        this.showCount = showCount;
        this.workChoiceTwoStages = workChoiceTwoStages;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (workChoiceTwoStages == null){
            return 0;
        }
        if (showAll){
            return workChoiceTwoStages.size();
        }
        if (workChoiceTwoStages.size() >= showCount){
            return showCount;
        }else {
            return workChoiceTwoStages.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return workChoiceTwoStages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {

        TwoStageListViewHolder twoStageListViewHolder;
        if (contentView == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.item_two_stage, null);
            twoStageListViewHolder = new TwoStageListViewHolder();
            twoStageListViewHolder.twoStageText = contentView.findViewById(R.id.twoStageText);
            twoStageListViewHolder.gridView = contentView.findViewById(R.id.gridView);
            contentView.setTag(twoStageListViewHolder);
        }else {
            twoStageListViewHolder = (TwoStageListViewHolder) contentView.getTag();
        }
        twoStageListViewHolder.twoStageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToastUtil.showToastTwo("gridView");
            }
        });
        twoStageListViewHolder.twoStageText.setText(workChoiceTwoStages.get(i).getPositionName());
        twoStageListViewHolder.gridView.setAdapter(new ThreeStageGardAdapter(true, workChoiceTwoStages.get(i).getChildren(), context));
        //测量两个srcollview 嵌套不能全部展示的高度
        ListViewMeasureHeightUtil.meaSureHeight(twoStageListViewHolder.gridView);
        twoStageListViewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.e("TwoStageListAdapter", "onItemClick(TwoStageListAdapter.java:99)" + i);
                ((WorkCassificationActivity)context).returnWorkInfo(((WorkChoiceThreeStage)adapterView.getItemAtPosition(i)));
            }
        });

        return contentView;
    }


    class TwoStageListViewHolder {

        TextView twoStageText;
        GridView gridView;

        public TwoStageListViewHolder() {
        }
    }





}



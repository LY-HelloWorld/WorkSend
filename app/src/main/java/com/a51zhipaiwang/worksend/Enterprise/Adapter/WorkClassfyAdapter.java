package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkChoice;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;


public class WorkClassfyAdapter extends BaseAdapter {

    private ArrayList<WorkChoice> workChoices;

    private int currentItem = -1;

    public WorkClassfyAdapter(ArrayList<WorkChoice> workChoices) {
        this.workChoices = workChoices;
    }

    @Override
    public int getCount() {
        if (workChoices != null){
            return workChoices.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return workChoices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        /**
         * 如果不存在view时才新建，优化
         */
        ViewHolder viewHolder;
        if (view == null){
            view = View.inflate(viewGroup.getContext(), R.layout.work_classfy_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = view.findViewById(R.id.workClassfyText);
            viewHolder.item_layout = view.findViewById(R.id.item_layout);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(workChoices.get(i).getPositionName());
        return view;
    }


    class ViewHolder {
        TextView textView;
        LinearLayout item_layout;

    }


}

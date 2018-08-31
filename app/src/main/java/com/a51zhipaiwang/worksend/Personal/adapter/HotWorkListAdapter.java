package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Personal.entity.HotWork;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;

import java.util.ArrayList;

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
public class HotWorkListAdapter extends BaseAdapter {

    private ArrayList arrayList;
    private Context context;


    public HotWorkListAdapter(ArrayList workChoiceThreeStages, Context context) {
        this.arrayList = workChoiceThreeStages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HotWorkListViewHolder hotWorkListViewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_hot_work, null);
            hotWorkListViewHolder = new HotWorkListViewHolder();
            hotWorkListViewHolder.tx_take = view.findViewById(R.id.tx_take);
            hotWorkListViewHolder.tx_work_name = view.findViewById(R.id.tx_work_name);
            hotWorkListViewHolder.tx_hot_number = view.findViewById(R.id.tx_hot_number);
            view.setTag(hotWorkListViewHolder);
        }else {
            hotWorkListViewHolder = (HotWorkListViewHolder) view.getTag();
        }
        HotWork hotWork = (HotWork) arrayList.get(i);
        Resources resources = context.getResources();
        if (hotWork.isTakeOrNot()){
            hotWorkListViewHolder.tx_take.setTextColor(Color.WHITE);
            hotWorkListViewHolder.tx_take.setText("已订阅");
            hotWorkListViewHolder.tx_take.setBackground(resources.getDrawable(R.drawable.tx_take_bg));
        }else {
            hotWorkListViewHolder.tx_take.setTextColor(resources.getColor(R.color.blue_green));
            hotWorkListViewHolder.tx_take.setText("订阅");
            hotWorkListViewHolder.tx_take.setBackground(resources.getDrawable(R.drawable.tx_take_false_bg));
        }
        if (i == 0){
            hotWorkListViewHolder.tx_hot_number.setBackground(resources.getDrawable(R.drawable.bg_hot_num_one));
        }
        if (i == 1){
            hotWorkListViewHolder.tx_hot_number.setBackground(resources.getDrawable(R.drawable.bg_hot_num_two));
        }
        if (i == 2){
            hotWorkListViewHolder.tx_hot_number.setBackground(resources.getDrawable(R.drawable.bg_hot_num_three));
        }
        if (i != 0 && i != 1 && i != 2){
            hotWorkListViewHolder.tx_hot_number.setBackground(resources.getDrawable(R.drawable.bg_hot_num_common));
        }
        int j = i + 1;
        hotWorkListViewHolder.tx_hot_number.setText(j + "");
        hotWorkListViewHolder.tx_work_name.setText(hotWork.getPositionName());
        return view;
    }

    class HotWorkListViewHolder{
        TextView tx_work_name;
        TextView tx_take;
        TextView tx_hot_number;
    }


}

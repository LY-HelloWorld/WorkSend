package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Personal.entity.CommendWork;
import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/23
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
public class FindWorkGridViewAdapter extends BaseAdapter {

    private ArrayList<CommendWork> workChoiceThreeStages;
    private Context context;
    private int variableId;

    public FindWorkGridViewAdapter(ArrayList<CommendWork> workChoiceThreeStages, Context context, int variableId) {
        this.workChoiceThreeStages = workChoiceThreeStages;
        this.context = context;
        this.variableId = variableId;
    }

    @Override
    public int getCount() {
        return workChoiceThreeStages.size();
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
        ViewDataBinding viewDataBinding;
        if (view == null){
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_grid_recommend_work_databinding,
                viewGroup,
                false);
        }else {
            viewDataBinding = DataBindingUtil.getBinding(view);
        }
        viewDataBinding.setVariable(variableId, workChoiceThreeStages.get(i));
        viewDataBinding.executePendingBindings();
        return viewDataBinding.getRoot();
    }
}

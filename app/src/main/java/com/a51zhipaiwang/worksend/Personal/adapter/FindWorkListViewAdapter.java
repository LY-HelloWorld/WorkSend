package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.BR;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;
import com.a51zhipaiwang.worksend.Utils.MyLog;

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
public class FindWorkListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FindWork> workBeans;

    public FindWorkListViewAdapter(Context context, ArrayList<FindWork> workBeans) {
        this.context = context;
        this.workBeans = workBeans;
    }

    @Override
    public int getCount() {
        return workBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return workBeans.get(i);
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
                    R.layout.item_work,
                    viewGroup,
                    false);
            view = viewDataBinding.getRoot();
        }else {
            viewDataBinding = DataBindingUtil.getBinding(view);
        }
        MyLog.e("FindWorkListViewAdapter", "getView(FindWorkListViewAdapter.java:74)" + workBeans.get(i).getDistributeLeafletsDetailsId());
        viewDataBinding.setVariable(BR.findwork, workBeans.get(i));
        ((TextView)view.findViewById(R.id.tx_money)).setText(IdCoverText.coverMoney(workBeans.get(i).getSalaryStandard()));
        //viewDataBinding.executePendingBindings();
        return view;
    }
}

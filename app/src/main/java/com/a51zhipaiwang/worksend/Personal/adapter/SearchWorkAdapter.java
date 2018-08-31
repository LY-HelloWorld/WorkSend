package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;

import java.util.ArrayList;
import com.a51zhipaiwang.worksend.BR;
import com.a51zhipaiwang.worksend.R;


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
public class SearchWorkAdapter extends BaseAdapter {

    private ArrayList arrayList;
    private Context context;

    public SearchWorkAdapter(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
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
        ViewDataBinding viewDataBinding;
        if (view == null){
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_grid_work_databinding,
                    viewGroup,
                    false);
        }else {
            viewDataBinding = DataBindingUtil.getBinding(view);
        }
        viewDataBinding.setVariable(BR.history, arrayList.get(i));
        viewDataBinding.executePendingBindings();
        return viewDataBinding.getRoot();
    }
}

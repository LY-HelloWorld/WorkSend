package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.entity.FindWork;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;

import java.util.ArrayList;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/27
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
public class ResumeListAdapter extends BaseAdapter {

    private ArrayList arrayList;
    private Context context;

    public ResumeListAdapter(ArrayList arrayList, Context context) {
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
        ResumeViewHolder resumeViewHolder;
        FindWork findWork = (FindWork) arrayList.get(i);
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_resume, null);
            resumeViewHolder = new ResumeViewHolder();
            resumeViewHolder.tx_work = view.findViewById(R.id.tx_work);
            resumeViewHolder.tx_salary = view.findViewById(R.id.tx_salary);
            resumeViewHolder.tx_company = view.findViewById(R.id.tx_company);
            resumeViewHolder.tx_location = view.findViewById(R.id.tx_location);
            view.setTag(resumeViewHolder);
        }else {
            resumeViewHolder = (ResumeViewHolder) view.getTag();
        }
        resumeViewHolder.tx_work.setText(findWork.getZwName());
        resumeViewHolder.tx_salary.setText(IdCoverText.coverMoney(findWork.getSalaryStandard()));
        resumeViewHolder.tx_company.setText(findWork.getEnterpriseName());
        resumeViewHolder.tx_location.setText(findWork.getCol5());
        return view;
    }

    class ResumeViewHolder{
        private TextView tx_work;
        private TextView tx_salary;
        private TextView tx_company;
        private TextView tx_location;
    }


}

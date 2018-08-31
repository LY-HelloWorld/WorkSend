package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWorkManagerActivityPresenter.IWorkManagerPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.IdCoverText;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkManagerAdapter extends BaseAdapter {
    //全部展示
    private boolean showAll = false;
    //展示个数
    private int showCount = 0;

    private ArrayList<WorkBean> workBeans;

    private Context context;

    private View.OnClickListener clickListener;

    private IWorkManagerPresenter workManagerPresenter;


    public WorkManagerAdapter(boolean showAll, Context context, ArrayList<WorkBean> workBeans, IWorkManagerPresenter workManagerPresenter) {
        this.showAll = showAll;
        this.context = context;
        this.workBeans = workBeans;
        this.workManagerPresenter = workManagerPresenter;
    }

    public WorkManagerAdapter(int showCount, Context context, ArrayList<WorkBean> workBeans, IWorkManagerPresenter workManagerPresenter) {
        this.showCount = showCount;
        this.context = context;
        this.workBeans = workBeans;
        this.workManagerPresenter = workManagerPresenter;
    }

    public WorkManagerAdapter(boolean showAll, Context context, ArrayList<WorkBean> workBeans, View.OnClickListener clickListener, IWorkManagerPresenter workManagerPresenter) {
        this.showAll = showAll;
        this.context = context;
        this.workBeans = workBeans;
        this.clickListener = clickListener;
        this.workManagerPresenter = workManagerPresenter;
    }

    public WorkManagerAdapter(int showCount, Context context, ArrayList<WorkBean> workBeans, View.OnClickListener clickListener, IWorkManagerPresenter workManagerPresenter) {
        this.showCount = showCount;
        this.context = context;
        this.workBeans = workBeans;
        this.workManagerPresenter = workManagerPresenter;
    }

    @Override
    public int getCount() {
        if (workBeans == null){
            return 0;
        }
        if (showAll){
            return workBeans.size();
        }
        if (workBeans.size() >= showCount){
            return showCount;
        }else {
            return workBeans.size();
        }
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
    public View getView(final int i, View contentView, ViewGroup viewGroup) {
        final int currentPostion = i;
        final WorkBean workBean = workBeans.get(i);
        JianLiManagerHolder jianLiManagerHolder;
        if (contentView == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.item_work_send, null);
            jianLiManagerHolder = new JianLiManagerHolder();
            jianLiManagerHolder.deleteJianLiBt = contentView.findViewById(R.id.deleteJianLiBt);
            jianLiManagerHolder.editJianLiBt = contentView.findViewById(R.id.editJianLiBt);
            jianLiManagerHolder.workTitleText = contentView.findViewById(R.id.workTitleText);
            jianLiManagerHolder.moneyText = contentView.findViewById(R.id.moneyText);
            jianLiManagerHolder.cityText = contentView.findViewById(R.id.cityText);
            jianLiManagerHolder.educationText = contentView.findViewById(R.id.educationText);
            jianLiManagerHolder.workText = contentView.findViewById(R.id.workText);
            jianLiManagerHolder.sexText = contentView.findViewById(R.id.sexText);
            jianLiManagerHolder.workCity = contentView.findViewById(R.id.workCity);
            contentView.setTag(jianLiManagerHolder);
        }else {
            jianLiManagerHolder = (JianLiManagerHolder) contentView.getTag();
        }
        jianLiManagerHolder.workTitleText.setText(workBean.getZwName());
        jianLiManagerHolder.moneyText.setText(IdCoverText.coverMoney(workBean.getSalaryStandard()));
        jianLiManagerHolder.cityText.setText(workBean.getArea());
        jianLiManagerHolder.educationText.setText(IdCoverText.coverEducation(workBean.getEducation()));
        jianLiManagerHolder.workText.setText(workBean.getZwName());
        jianLiManagerHolder.sexText.setText(IdCoverText.coverSex(workBean.getSex()));
        jianLiManagerHolder.workCity.setText(workBean.getCity());
        if (clickListener != null){
            jianLiManagerHolder.editJianLiBt.setOnClickListener(clickListener);
            jianLiManagerHolder.editJianLiBt.setTag(workBean);
        }
        jianLiManagerHolder.deleteJianLiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("id", workBean.getId());
                workManagerPresenter.deleteWork(hashMap);
            }
        });
        return contentView;
    }


    class JianLiManagerHolder {
        Button editJianLiBt;
        Button deleteJianLiBt;
        TextView workTitleText;
        TextView moneyText;
        TextView cityText;
        TextView educationText;
        TextView workText;
        TextView sexText;
        TextView workCity;

        public JianLiManagerHolder() {
        }
    }


}

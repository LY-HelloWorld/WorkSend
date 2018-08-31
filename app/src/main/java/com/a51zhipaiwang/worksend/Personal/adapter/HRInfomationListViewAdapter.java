package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.base.BaseInfomationFragment;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.R;

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
public class HRInfomationListViewAdapter extends BaseAdapter {

    private ArrayList arrayList;
    private Context context;
    private boolean showOrNot = false;


    public HRInfomationListViewAdapter(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void changeDelete(){
        this.showOrNot = !showOrNot;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        HRInfomationViewHolder hrInfomationViewHolder;
        Infor infor = (Infor) arrayList.get(i);
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_hr_information, null);
            hrInfomationViewHolder = new HRInfomationViewHolder();
            hrInfomationViewHolder.tx_enterprise = view.findViewById(R.id.tx_enterprise);
            hrInfomationViewHolder.tx_time = view.findViewById(R.id.tx_time);
            hrInfomationViewHolder.tx_read = view.findViewById(R.id.tx_read);
            hrInfomationViewHolder.img_delete_infomation = view.findViewById(R.id.img_delete_infomation);
            hrInfomationViewHolder.img_icon = view.findViewById(R.id.img_icon);
            view.setTag(hrInfomationViewHolder);
        }else {
            hrInfomationViewHolder = (HRInfomationViewHolder) view.getTag();
        }
        if (infor.getNewMessage().equals("010")){
            hrInfomationViewHolder.tx_read.setText("未读");
            hrInfomationViewHolder.tx_read.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            hrInfomationViewHolder.tx_read.setText("已读");
            hrInfomationViewHolder.tx_read.setTextColor(context.getResources().getColor(R.color.blue_green));
        }
        if (showOrNot){
            hrInfomationViewHolder.img_delete_infomation.setVisibility(View.VISIBLE);
            hrInfomationViewHolder.img_delete_infomation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(BaseInfomationFragment.LOCAL_BROADCAST);
                    intent.putExtra("index", i);
                    context.sendBroadcast(intent);
                }
            });
        }else {
            hrInfomationViewHolder.img_delete_infomation.setVisibility(View.GONE);
        }
        hrInfomationViewHolder.tx_time.setText(infor.getCreationtime());
        hrInfomationViewHolder.tx_enterprise.setText(infor.getCorporateName());
        hrInfomationViewHolder.img_icon.setImageResource(R.drawable.img_hr);
        return view;
    }

    class HRInfomationViewHolder{
        TextView tx_enterprise;
        TextView tx_time;
        TextView tx_read;
        ImageView img_delete_infomation;
        ImageView img_icon;
    }


}

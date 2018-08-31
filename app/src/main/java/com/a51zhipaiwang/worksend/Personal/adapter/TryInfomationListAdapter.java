package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.content.Intent;
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
public class TryInfomationListAdapter extends BaseAdapter {

    private ArrayList arrayList;
    private Context context;

    private boolean showOrNot;


    public TryInfomationListAdapter(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void changeShowOrNot(){
        showOrNot = !showOrNot;
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
        TryInfomationViewHolder tryInfomationViewHolder;
        Infor infor = (Infor) arrayList.get(i);
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_try_information, null);
            tryInfomationViewHolder = new TryInfomationViewHolder();
            tryInfomationViewHolder.tx_enterprise = view.findViewById(R.id.tx_enterprise);
            tryInfomationViewHolder.tx_time = view.findViewById(R.id.tx_time);
            tryInfomationViewHolder.tx_read = view.findViewById(R.id.tx_read);
            tryInfomationViewHolder.img_delete_infomation = view.findViewById(R.id.img_delete_infomation);
            tryInfomationViewHolder.img_try = view.findViewById(R.id.img_try);
            view.setTag(tryInfomationViewHolder);
        }else {
            tryInfomationViewHolder = (TryInfomationViewHolder) view.getTag();
        }
        if (infor.getNewMessage().equals("010")){
            tryInfomationViewHolder.tx_read.setText("未读");
            tryInfomationViewHolder.tx_read.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            tryInfomationViewHolder.tx_read.setText("已读");
            tryInfomationViewHolder.tx_read.setTextColor(context.getResources().getColor(R.color.blue_green));
        }
        if (showOrNot){
            tryInfomationViewHolder.img_delete_infomation.setVisibility(View.VISIBLE);
            tryInfomationViewHolder.img_delete_infomation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(BaseInfomationFragment.LOCAL_BROADCAST);
                    intent.putExtra("index", i);
                    context.sendBroadcast(intent);
                }
            });
        }else {
            tryInfomationViewHolder.img_delete_infomation.setVisibility(View.GONE);
        }
        tryInfomationViewHolder.tx_time.setText(infor.getCreationtime());
        tryInfomationViewHolder.tx_enterprise.setText(infor.getCorporateName());
        tryInfomationViewHolder.img_try.setImageResource(R.drawable.img_try);
        return view;
    }

    class TryInfomationViewHolder{
        TextView tx_enterprise;
        TextView tx_time;
        TextView tx_read;
        ImageView img_delete_infomation;
        ImageView img_try;
    }
}

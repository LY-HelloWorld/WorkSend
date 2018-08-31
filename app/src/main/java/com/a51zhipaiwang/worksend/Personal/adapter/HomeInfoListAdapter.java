package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.entity.Information;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;

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
public class HomeInfoListAdapter extends BaseAdapter {

    private ArrayList arrayList;
    private Context context;
    private GlideUtil glideUtil;

    public HomeInfoListAdapter(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        glideUtil = new GlideUtil();
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
        ViewHolder viewHolder;
        Information information = (Information) arrayList.get(i);
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_home_info, null);
            viewHolder = new ViewHolder();
            viewHolder.tx_info_name = view.findViewById(R.id.tx_info_name);
            viewHolder.tx_info_content = view.findViewById(R.id.tx_info_content);
            viewHolder.img_info = view.findViewById(R.id.img_info);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tx_info_content.setText(information.getContent());
        glideUtil.GlideImage(information.getPicture1(), context, viewHolder.img_info);
        viewHolder.tx_info_name.setText(information.getTitle());
        return view;
    }


    class ViewHolder {
        TextView tx_info_name;
        TextView tx_info_content;
        ImageView img_info;
    }
}

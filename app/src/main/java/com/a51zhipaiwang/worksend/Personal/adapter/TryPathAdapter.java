package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.entity.TryPath;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.TryPathFragment;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/20
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
public class TryPathAdapter extends BaseAdapter {

    private Context context;
    private boolean bShowAll;
    private int iShowCount;
    private ArrayList tryPathList;
    private int type;

    public TryPathAdapter(Context context, boolean bShowAll, ArrayList tryPathList, int type) {
        this.context = context;
        this.bShowAll = bShowAll;
        this.tryPathList = tryPathList;
        this.type = type;
    }

    public TryPathAdapter(Context context, int iShowCount, ArrayList tryPathList, int type) {
        this.context = context;
        this.iShowCount = iShowCount;
        this.tryPathList = tryPathList;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (bShowAll) {
            return tryPathList.size();
        }
        if (tryPathList.size() > iShowCount) {
            return iShowCount;
        } else {
            return tryPathList.size();
        }

    }

    @Override
    public Object getItem(int i) {
        return tryPathList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        TryPath tryPath = (TryPath) tryPathList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_try_path, null);
            viewHolder = new ViewHolder();
            viewHolder.tx_try_path_money = (TextView) view.findViewById(R.id.tx_trp_path_money);
            viewHolder.tx_try_path_time = (TextView) view.findViewById(R.id.tx_try_path_time);
            viewHolder.tx_try_path_business_name = (TextView) view.findViewById(R.id.tx_try_path_business_name);
            viewHolder.tx_try_path_state = (TextView) view.findViewById(R.id.tx_try_path_state);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        switch (type) {
            case TryPathFragment.TRY_PATH_ING:
                viewHolder.tx_try_path_state.setText("试岗中");
                viewHolder.tx_try_path_state.setTextColor(context.getResources().getColor(R.color.blue_green));
                viewHolder.tx_try_path_money.setTextColor(context.getResources().getColor(R.color.red));
                break;
            case TryPathFragment.TRY_PATH_OUT:
                viewHolder.tx_try_path_state.setText("试岗结束");
                viewHolder.tx_try_path_state.setTextColor(context.getResources().getColor(R.color.text_hui));
                viewHolder.tx_try_path_money.setTextColor(context.getResources().getColor(R.color.text_hui));
                break;

        }
        viewHolder.tx_try_path_business_name.setText(tryPath.getEnterpriseName());
        viewHolder.tx_try_path_time.setText(tryPath.getTrialPostTime());
        viewHolder.tx_try_path_money.setText(tryPath.getTrialPostSalary());
        return view;
    }

    class ViewHolder {

        TextView tx_try_path_money;
        TextView tx_try_path_time;
        TextView tx_try_path_business_name;
        TextView tx_try_path_state;

    }


}

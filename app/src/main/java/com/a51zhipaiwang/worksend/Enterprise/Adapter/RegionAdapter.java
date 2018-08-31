package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;

public class RegionAdapter extends BaseAdapter {

    //全部展示
    private boolean showAll = false;
    //展示个数
    private int showCount = 0;

    private ArrayList<City> cities;

    private Context context;

    public RegionAdapter(boolean showAll, ArrayList<City> cities, Context context) {
        this.showAll = showAll;
        this.cities = cities;
        this.context = context;
    }

    public RegionAdapter(int showCount, ArrayList<City> cities, Context context) {
        this.showCount = showCount;
        this.cities = cities;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (cities == null){
            return 0;
        }
        if (showAll){
            return cities.size();
        }
        if (cities.size() >= showCount){
            return showCount;
        }else {
            return cities.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RegionViewHolder regionViewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_region, null);
            regionViewHolder = new RegionViewHolder();
            regionViewHolder.regionText = view.findViewById(R.id.regionText);
            view.setTag(regionViewHolder);
        }else {
            regionViewHolder = (RegionViewHolder) view.getTag();
        }
        regionViewHolder.regionText.setText(cities.get(i).getName());
        return view;
    }

    class RegionViewHolder {
        TextView regionText;
    }

}

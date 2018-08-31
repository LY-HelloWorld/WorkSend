package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Bean.Citys;
import com.a51zhipaiwang.worksend.Bean.Provence;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class CityChoiceRecyclerAdapater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private ArrayList<Provence> provences;

    //布局类型 头部，字母，城市
    private final int HEAD = 0;
    private final int WORD = 1;
    private final int CITY = 2;

    private HeadViewHolder headViewHolder;
    private WordViewHolder wordViewHolder;
    private CityViewHolder cityViewHolder;


    public CityChoiceRecyclerAdapater(Context context, ArrayList<Provence> provences) {
        this.context = context;
        this.provences = provences;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == HEAD) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_choice_head, viewGroup, false);
            return new HeadViewHolder(view);
        } else if (viewType == WORD) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_choice_word, viewGroup, false);
            return new WordViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_choice_city, viewGroup, false);
            return new CityViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (position == 0) {
            headViewHolder = (HeadViewHolder) viewHolder;
            //不回收viewHolder
            headViewHolder.setIsRecyclable(false);
            //判断当前城市是否为空 如果不为空则填写信息
            final String city = SharedPreferencesUtil
                    .readSharedPreference("city", MyApplication.cityInfo, "", context);
            if (!TextUtils.isEmpty(city)) {
                headViewHolder.current_location_btn.setText(city);
            }else {
                headViewHolder.current_location_btn.setText("定位失败");
            }
            //设置查看当前位置监听
            headViewHolder.dingWeiLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //查看权限
                    ((CityChoiceActivity) context).
                            checkPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
                }
            });
            //设置选择当前位置监听
            headViewHolder.currentLocationLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(city)){
                        City city1 = new City();
                        city1.setLat(MyApplication.latitude);
                        city1.setLog(MyApplication.longitude);
                        city1.setName(headViewHolder.current_location_btn.getText().toString());
                        ((CityChoiceActivity) context).choiceCurrentLocation(city1);
                    }else {
                        ToastUtil.showToastTwo("获取当前位置失败！请确认定位权限是否开启");
                    }
                }
            });
        } else {
            int count = 0;
            for (int i = 0; i < provences.size(); i++) {
                count += 1;
                if (position == count) {
                    wordViewHolder = (WordViewHolder) viewHolder;
                    wordViewHolder.setIsRecyclable(false);
                    if (provences.get(i).getChildren().size() == 0){
                        wordViewHolder.textViewWord.setVisibility(View.GONE);
                    }else {
                        wordViewHolder.textViewWord.setText(provences.get(i).getCode());
                    }
                } else {
                    final List<Citys> addressList = provences.get(i).getChildren();
                    for (int j = 0; j < addressList.size(); j++) {
                        count += 1;
                        if (position == count) {
                            cityViewHolder = (CityViewHolder) viewHolder;
                            cityViewHolder.setIsRecyclable(false);
                            cityViewHolder.textViewCity.setText(addressList.get(j).getName());
                            final int finalJ = j;
                            cityViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ((CityChoiceActivity) context).feedBackCity(addressList.get(finalJ).getChildren());
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (provences == null) {
            return 1;
        }
        int childCount = provences.size();//字母的数量
        for (int i = 0; i < provences.size(); i++) {
            childCount += provences.get(i).getChildren().size();//地区的数量
        }
        return childCount + 1;
    }

    @Override
    public int getItemViewType(int position) {

        int count = 0;
        if (position == count) return HEAD;//下标为0的固定显示头部布局。

        for (int i = 0; i < provences.size(); i++) {
            count++;
            if (position == count) {
                return WORD;
            }
            List<Citys> addressList = provences.get(i).getChildren();
            for (int j = 0; j < addressList.size(); j++) {
                count++;
                if (position == count) {
                    return CITY;
                }
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<Provence> getCitys() {
        return provences;
    }

    public HeadViewHolder getHeadViewHolder() {
        return headViewHolder;
    }

    /**
     * 城市选择器头部ViewHolder
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder {
        public TextView current_location_btn;
        LinearLayout dingWeiLayout;
        LinearLayout currentLocationLayout;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            dingWeiLayout = itemView.findViewById(R.id.dingWeiLayout);
            currentLocationLayout = itemView.findViewById(R.id.currentLocationLayout);
            current_location_btn = (TextView) itemView.findViewById(R.id.current_location_btn);
        }
    }

    /**
     * 城市选择器字母ViewHolder
     */
    public class WordViewHolder extends RecyclerView.ViewHolder {

        TextView textViewWord;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.city_choice_word);
        }
    }

    /**
     * 城市选择器城市ViewHolder
     */
    public class CityViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCity;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.city_choice_city);
        }
    }


}

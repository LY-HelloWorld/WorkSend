package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.TongZhiActivity.TongZhiActivity;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;
public class ChatReturnAdapter extends BaseAdapter {

    //数据
    private ArrayList<MessageInfoBean> tongZhiInfomations;
    //上下文
    private Context context;
    //展示数目
    private int showNum;
    //是否全部展示
    private boolean showAll;
    //上一条的时间
    private String lastTime;
    private int type;

    public ChatReturnAdapter(ArrayList<MessageInfoBean> tongZhiInfomations, Context context, int showNum, int type) {
        this.tongZhiInfomations = tongZhiInfomations;
        this.context = context;
        this.showNum = showNum;
        this.type = type;
    }

    public ChatReturnAdapter(ArrayList<MessageInfoBean> tongZhiInfomations, Context context, boolean showAll, int type) {
        this.tongZhiInfomations = tongZhiInfomations;
        this.context = context;
        this.showAll = showAll;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (tongZhiInfomations == null){
            return 0;
        }
        if (showAll){
            return tongZhiInfomations.size();
        }
        if (tongZhiInfomations.size() >= showNum){
            return showNum;
        }else {
            return tongZhiInfomations.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return tongZhiInfomations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoler viewHoler;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_return_infomation, null);
            viewHoler = new ViewHoler();
            viewHoler.chatInfo = view.findViewById(R.id.chatText);
            viewHoler.timeText = view.findViewById(R.id.timeText);
            viewHoler.readJianLiLayout = view.findViewById(R.id.readJianLiLayout);
            viewHoler.tiShiText = view.findViewById(R.id.tiShiText);
            viewHoler.tiShiImage = view.findViewById(R.id.tiShiImage);
            view.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler) view.getTag();
        }
        MessageInfoBean tongZhiInfomationData = tongZhiInfomations.get(i);
        if (i != 0){
            //保存上一个的年月日 作为是否展示下一条时间依据
            lastTime = tongZhiInfomations.get(i - 1).getCreationtime();
            //如果时间相同不展示
            if (!lastTime.equals(tongZhiInfomationData.getCreationtime())){
                viewHoler.timeText.setVisibility(View.VISIBLE);
                viewHoler.timeText.setText(tongZhiInfomationData.getCreationtime());
            }
        }else {
            //展示第一条数据的时间
            viewHoler.timeText.setVisibility(View.VISIBLE);
            viewHoler.timeText.setText(tongZhiInfomationData.getCreationtime());
        }
        viewHoler.chatInfo.setText(tongZhiInfomationData.getMessageContent());
        if (type == TongZhiActivity.QIANGDAN && "010".equals(tongZhiInfomationData.getCol3())){
            viewHoler.tiShiText.setText("已到岗");
            viewHoler.tiShiText.setTextColor(context.getResources().getColor(R.color.text_hui));
            viewHoler.tiShiImage.setVisibility(View.INVISIBLE);
            return view;
        }

        if (type == TongZhiActivity.QIANGDAN && "020".equals(tongZhiInfomationData.getCol3())){
            viewHoler.tiShiText.setTextColor(context.getResources().getColor(R.color.blue_green));
            viewHoler.tiShiText.setText("确认到岗");
            viewHoler.tiShiImage.setVisibility(View.VISIBLE);
            return view;
        }
        if (type == TongZhiActivity.PAIDANRETURN && "020".equals(tongZhiInfomationData.getCol2())){
            viewHoler.tiShiText.setText("派单已失效");
            viewHoler.tiShiText.setTextColor(context.getResources().getColor(R.color.text_hui));
            viewHoler.tiShiImage.setVisibility(View.INVISIBLE);
            return view;
        }
        if (type == TongZhiActivity.PAIDANRETURN && "010".equals(tongZhiInfomationData.getCol2())){
            viewHoler.tiShiText.setText("点击查看简历");
            viewHoler.tiShiText.setTextColor(context.getResources().getColor(R.color.blue_green));
            viewHoler.tiShiImage.setVisibility(View.VISIBLE);
            return view;
        }
        return view;
    }

    class ViewHoler {
        TextView chatInfo;
        TextView timeText;
        TextView tiShiText;
        ImageView tiShiImage;
        LinearLayout readJianLiLayout;
    }



}

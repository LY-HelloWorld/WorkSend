package com.a51zhipaiwang.worksend.Enterprise.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.InfomationFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.InfomationItemFragment;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;

public class InfomationAdapter extends BaseAdapter {
    //全部展示
    private boolean showAll = false;
    //展示个数
    private int showCount = 0;

    private ArrayList<MessageInfoBean> messageInfoBeans;

    private Context context;

    private boolean bShowDelete = false;

    private int type;

    public InfomationAdapter(boolean showAll, Context context, ArrayList<MessageInfoBean> strings, int type) {
        this.showAll = showAll;
        this.context = context;
        this.messageInfoBeans = strings;
        this.type = type;
    }

    public InfomationAdapter(int showCount, Context context, ArrayList<MessageInfoBean> strings, int type) {
        this.showCount = showCount;
        this.context = context;
        this.messageInfoBeans = strings;
        this.type = type;
    }

    public void setbShowDelete(boolean bShowDelete) {
        this.bShowDelete = bShowDelete;
    }

    @Override
    public int getCount() {
        if (messageInfoBeans == null) {
            return 0;
        }
        if (showAll) {
            return messageInfoBeans.size();
        }
        if (messageInfoBeans.size() >= showCount) {
            return showCount;
        } else {
            return messageInfoBeans.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return messageInfoBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View contentView, ViewGroup viewGroup) {
        InfomationViewHolder infomationViewHolder;
        final MessageInfoBean messageInfoBean = messageInfoBeans.get(i);
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.item_infomation, null);
            infomationViewHolder = new InfomationViewHolder();
            infomationViewHolder.tongZhiText = contentView.findViewById(R.id.tongZhiText);
            infomationViewHolder.textTime = contentView.findViewById(R.id.textTime);
            infomationViewHolder.textContent = contentView.findViewById(R.id.textContent);
            infomationViewHolder.newMessageImage = contentView.findViewById(R.id.newMessageImage);
            infomationViewHolder.img_delete_infomation = contentView.findViewById(R.id.img_delete_infomation);
            infomationViewHolder.messageImage = contentView.findViewById(R.id.messageImage);
            contentView.setTag(infomationViewHolder);
        } else {
            infomationViewHolder = (InfomationViewHolder) contentView.getTag();
        }
        if (type == InfomationItemFragment.NOTIFICATION_TYPE){
            infomationViewHolder.messageImage.setImageResource(R.drawable.notifiycation);
        }else {
            infomationViewHolder.messageImage.setImageResource(R.drawable.feed_back);
        }
        if (bShowDelete) {
            infomationViewHolder.img_delete_infomation.setVisibility(View.VISIBLE);
            infomationViewHolder.img_delete_infomation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(InfomationItemFragment.LOCAL_BROADCAST);
                    intent.putExtra("index", messageInfoBean.getEnterpriseMessageState());
                    context.sendBroadcast(intent);
                }
            });
        } else {
            infomationViewHolder.img_delete_infomation.setVisibility(View.GONE);
        }
        if (messageInfoBean.getMessageState().equals("010")) {
            contentView.setVisibility(View.GONE);
            return contentView;
        }
        if (messageInfoBean.getMessageState().equals("020")) {
            infomationViewHolder.newMessageImage.setVisibility(View.VISIBLE);
            infomationViewHolder.textContent.setText(messageInfoBean.getMessageContent());
            infomationViewHolder.textTime.setText(messageInfoBean.getCreationtime());
        }
        if (messageInfoBean.getMessageState().equals("030")) {
            infomationViewHolder.newMessageImage.setVisibility(View.GONE);
            infomationViewHolder.textContent.setText(messageInfoBean.getMessageContent());
            infomationViewHolder.textTime.setText(messageInfoBean.getCreationtime());
        }
        infomationViewHolder.tongZhiText.setText(messageInfoBean.getMessageName());
        return contentView;
    }


    public class InfomationViewHolder {
        TextView tongZhiText;
        TextView textContent;
        TextView textTime;
        ImageView newMessageImage;
        ImageView img_delete_infomation;
        ImageView messageImage;
    }


}

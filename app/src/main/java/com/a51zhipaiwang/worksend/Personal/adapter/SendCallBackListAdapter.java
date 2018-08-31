package com.a51zhipaiwang.worksend.Personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
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
public class SendCallBackListAdapter extends BaseAdapter {

    public static int SENDBACK = 1;
    public static int SYSTEM = 2;
    //数据
    private ArrayList<Infor> infors;
    //上下文
    private Context context;
    //展示数目
    private int showNum;
    //是否全部展示
    private boolean showAll;
    //上一条的时间
    private String lastTime;

    private int type;

    public SendCallBackListAdapter(ArrayList<Infor> infors, Context context, int showNum, int type) {
        this.infors = infors;
        this.context = context;
        this.showNum = showNum;
        this.type = type;
    }

    public SendCallBackListAdapter(ArrayList<Infor> infors, Context context, boolean showAll, int type) {
        this.infors = infors;
        this.context = context;
        this.showAll = showAll;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (infors == null){
            return 0;
        }
        if (showAll){
            return infors.size();
        }
        if (infors.size() >= showNum){
            return showNum;
        }else {
            return infors.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return infors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoler viewHoler;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_callback_information, null);
            viewHoler = new ViewHoler();
            viewHoler.chatInfo = view.findViewById(R.id.chatText);
            viewHoler.timeText = view.findViewById(R.id.timeText);
            viewHoler.img_head = view.findViewById(R.id.img_head);
            view.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler) view.getTag();
        }
        Infor infor = infors.get(i);
        if (i != 0){
            //保存上一个的年月日 作为是否展示下一条时间依据
            lastTime = infors.get(i - 1).getCreationtime();
            //如果时间相同不展示
            if (!lastTime.equals(infor.getCreationtime())){
                viewHoler.timeText.setVisibility(View.VISIBLE);
                viewHoler.timeText.setText(infor.getCreationtime());
            }else {
                viewHoler.timeText.setVisibility(View.GONE);
            }
        }else {
            //展示第一条数据的时间
            viewHoler.timeText.setVisibility(View.VISIBLE);
            viewHoler.timeText.setText(infor.getCreationtime());
        }
        if (type == SYSTEM){
            viewHoler.chatInfo.setText(infor.getMessageContent());
            viewHoler.img_head.setImageResource(R.drawable.icon_round);
        }else {
            viewHoler.chatInfo.setText(infor.getCorporateName() + "的HR拒绝您的请求");
            viewHoler.img_head.setImageResource(R.drawable.img_send_back_head);
        }

        return view;
    }

    class ViewHoler {
        TextView chatInfo;
        TextView timeText;
        ImageView img_head;
    }
}

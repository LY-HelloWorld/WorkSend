package com.a51zhipaiwang.worksend.Enterprise.Activity.TongZhiActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.ChoiceJianliThreetyActivity.ChoiceJianLiThreetyActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.TongZhiActivity.ITongZhiActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.ChatReturnAdapter;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.TongZhiAdapter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseTongZhiActivityPresenter.ITongZhiActivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseTongZhiActivityPresenter.TongZhiActivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TongZhiActivity extends BaseActivity implements ITongZhiActivity {

    private ListView tongZhiListView;

    private ArrayList<MessageInfoBean> messageInfoBeans;

    //通知消息数据类型
    public static final int TONGZHI = 1;
    //活动消息数据类型
    public static final int HUODONG = 2;
    //派单反馈
    public static final int PAIDANRETURN = 3;
    //抢单
    public static final int QIANGDAN = 4;
    private TextView tilte_text;
    //当前界面类型
    private int currentType;
    private ImageView return_image;

    private ITongZhiActivityPresenter iTongZhiActivityPresenter;

    private int currentChoiceItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_zhi);
        init();
        setRegister();
        getMessage();
    }

    public static void StartTongZhiActivity(Context context, String title, int type) {
        Intent intent = new Intent(context, TongZhiActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setReturnInfo(ArrayList<MessageInfoBean> messageInfoBeans) {
        this.messageInfoBeans = messageInfoBeans;
        setAdapter();
    }

    @Override
    public void surePosition(boolean bSurePosition) {
        if (bSurePosition){
            ToastUtil.showToastTwo("已到岗");
        }else {
            ToastUtil.showToastTwo("请检查您的网络连接");
        }
    }



    private void init() {
        iTongZhiActivityPresenter = new TongZhiActivityPresenter(this);
        tongZhiListView = (ListView) findViewById(R.id.tongZhiListView);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text.setText(getIntent().getStringExtra("title"));
        currentType = getIntent().getIntExtra("type", 0);
        messageInfoBeans = new ArrayList<>();
    }

    /**
     * 设置监听
     */
    private void setRegister() {
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TongZhiActivity.this.finish();
            }
        });
    }

    /**
     * 设置list adapter
     */
    private void setAdapter() {
        switch (currentType) {
            case TONGZHI:
                TongZhiAdapter tongZhiAdapter = new TongZhiAdapter(messageInfoBeans, this, true);
                tongZhiListView.setAdapter(tongZhiAdapter);
                break;
            case HUODONG:
                TongZhiAdapter huodongAdapter = new TongZhiAdapter(messageInfoBeans, this, true);
                tongZhiListView.setAdapter(huodongAdapter);
                break;
            case PAIDANRETURN:
                final ChatReturnAdapter paiDanReturnAdapter = new ChatReturnAdapter(messageInfoBeans, this, true, PAIDANRETURN);
                tongZhiListView.setAdapter(paiDanReturnAdapter);
                tongZhiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MyLog.e("TongZhiActivity", "onItemClick(TongZhiActivity.java:112)" + "tongZhiListView");
                        if (messageInfoBeans.get(i).getCol2().equals("020")){
                            ToastUtil.showToastTwo("派单已失效");
                            return;
                        }else {
                            messageInfoBeans.get(i).setCol2("020");
                            ChoiceJianLiThreetyActivity.StartChoiceJianLiThreetyActivity(TongZhiActivity.this, messageInfoBeans.get(i).getDistributeLeafletsId(), "简历详情");
                            paiDanReturnAdapter.notifyDataSetChanged();
                        }

                    }
                });
                break;
            case QIANGDAN:
                final ChatReturnAdapter qiangDanAdapter = new ChatReturnAdapter(messageInfoBeans, this, true, QIANGDAN);
                tongZhiListView.setAdapter(qiangDanAdapter);
                tongZhiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MyLog.e("TongZhiActivity", "onItemClick(TongZhiActivity.java:112)" + "tongZhiListView");
                        MessageInfoBean messageInfoBean = messageInfoBeans.get(i);
                        MyLog.e("TongZhiActivity", "onItemClick(TongZhiActivity.java:157)" + messageInfoBean.getCol3());
                        if (messageInfoBean.getCol3().equals("010")){
                            ToastUtil.showToastTwo("已到岗");
                            return;
                        }else {
                            Map map = new HashMap();
                            map.put("distributeLeafletsId", messageInfoBean.getDistributeLeafletsId());
                            map.put("resumeId", messageInfoBean.getResumeId());
                            iTongZhiActivityPresenter.surePosition(map);
                        }

                    }
                });
                break;
        }
        tongZhiListView.setSelection(messageInfoBeans.size() - 1);
    }


    private void getMessage() {
        HashMap paramsMap = new HashMap();
        switch (currentType) {
            case TONGZHI:
                paramsMap.put("enterpriseMessageState", "010");
                break;
            case HUODONG:
                paramsMap.put("enterpriseMessageState", "020");
                break;
            case PAIDANRETURN:
                paramsMap.put("enterpriseMessageState", "030");
                break;
            case QIANGDAN:
                paramsMap.put("enterpriseMessageState", "040");
                break;
        }
        iTongZhiActivityPresenter.getWork(paramsMap);
    }



}

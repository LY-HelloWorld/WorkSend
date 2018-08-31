package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.TongZhiActivity.TongZhiActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.InfomationAdapter;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BaseFragment;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.solidfire.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class InfomationItemFragment extends BaseFragment {

    public static final String LOCAL_BROADCAST = "INFOMATION_ITEM_FRAGMENT";
    public static final String SHOW_DELETE_BROADCAST = "DELETE_INFO_CAST";

    private BroadcastReceiver broadcastReceiver;

    public static final int NOTIFICATION_TYPE = 1;
    public static final int SCRAPE_TYPE = 2;
    private InfomationItemBroadcastReceiver localReceiver;
    private ListView infomationList;

    private String[] titles;

    private MessageInfoBean tongZhiMessageInfoBean;

    private MessageInfoBean ziPaiMessageInfoBean;

    private MessageInfoBean paiDanMessage;

    private MessageInfoBean qiangMessage;

    private ArrayList<MessageInfoBean> messageInfoBeans;

    private ArrayList<MessageInfoBean> paiDanMessageInfoBeans;

    private boolean bShowDelete = true;

    private InfomationAdapter infomationAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infomation_item, null);
        init(view);
        initBroadCast();
        setRegister();
        getInfo();
        return view;
    }

    public static InfomationItemFragment getInfomationInstance(String[] titles) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("titles", titles);
        InfomationItemFragment infomationItemFragment = new InfomationItemFragment();
        infomationItemFragment.setArguments(bundle);
        return infomationItemFragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(localReceiver);
    }

    /**
     * 删除本地列表信息
     *
     * @param sId
     */
    private void removeInfomation(String sId) {
        for (int i = 0, len = messageInfoBeans.size(); i < len; i++) {
            if (messageInfoBeans.get(i).getEnterpriseMessageState().equals(sId)) {
                messageInfoBeans.remove(messageInfoBeans.get(i));
                infomationAdapter.notifyDataSetChanged();
                return;
            }
        }
        for (int i = 0, len = paiDanMessageInfoBeans.size(); i < len; i++) {
            if (paiDanMessageInfoBeans.get(i).getEnterpriseMessageState().equals(sId)) {
                paiDanMessageInfoBeans.remove(paiDanMessageInfoBeans.get(i));
                infomationAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * 删除消息 网络请求
     *
     * @param sInfoId
     */
    private void deleteInfo(final String sInfoId) {
        HashMap hashMap = new HashMap();
        hashMap.put("enterpriseMessageState", sInfoId);
        MyLog.e("InfomationItemFragment", "deleteInfo(InfomationItemFragment.java:89)" + new JSONObject(hashMap).toString());
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/enterpriseNew/removalMessageas.do?jsonStr=" + new JSONObject(hashMap).toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String sInfo) {
                        MyLog.e("InfomationItemFragment", "onResponse(InfomationItemFragment.java:95)" + sInfo);
                        try {
                            JSONObject response = new JSONObject(sInfo);
                            if (response.getString("code").equals("success")) {
                                String info = response.getString("info");
                                JSONObject infoJson = new JSONObject(info);
                                if (infoJson.getString("success").equals("删除成功")) {
                                    ToastUtil.showToastTwo("删除成功");
                                    removeInfomation(sInfoId);
                                }
                            } else {
                                ToastUtil.showToastTwo("删除失败!请检查您的网络连接!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.e("InfomationItemFragment", "onErrorResponse(InfomationItemFragment.java:100)" + error.getMessage());
            }
        }));
    }

    /**
     * 初始化广播监听
     */
    private void initBroadCast() {

        localReceiver = new InfomationItemBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCAL_BROADCAST);   //添加action
        intentFilter.addAction(SHOW_DELETE_BROADCAST);   //添加action
        intentFilter.addAction(MyApplication.NEWINFO);   //添加action
        getActivity().registerReceiver(localReceiver, intentFilter);
    }


    private void init(View view) {
        infomationList = (ListView) view.findViewById(R.id.infomationList);
        titles = getArguments().getStringArray("titles");
        messageInfoBeans = new ArrayList<>();
        paiDanMessageInfoBeans = new ArrayList<>();
        tongZhiMessageInfoBean = new MessageInfoBean();
        ziPaiMessageInfoBean = new MessageInfoBean();
        paiDanMessage = new MessageInfoBean();
        qiangMessage = new MessageInfoBean();


    }

    private void setRegister() {
        if (titles[0].equals("通知信息")) {
            infomationAdapter = new InfomationAdapter(true,
                    getActivity(),
                    messageInfoBeans,
                    NOTIFICATION_TYPE);
        } else {
            infomationAdapter = new InfomationAdapter(true,
                    getActivity(),
                    paiDanMessageInfoBeans,
                    SCRAPE_TYPE);
        }
        infomationList.setAdapter(infomationAdapter);
        infomationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (messageInfoBeans.size() > i && messageInfoBeans.get(i).getEnterpriseMessageState().equals("010")) {
                    TongZhiActivity.StartTongZhiActivity(getActivity(), "通知信息", TongZhiActivity.TONGZHI);
                    messageInfoBeans.get(i).setMessageState("030");
                    ((InfomationAdapter) infomationList.getAdapter()).notifyDataSetChanged();
                    return;
                }
                if (messageInfoBeans.size() > i && messageInfoBeans.get(i).getEnterpriseMessageState().equals("020")) {
                    TongZhiActivity.StartTongZhiActivity(getActivity(), "职派活动", TongZhiActivity.HUODONG);
                    messageInfoBeans.get(i).setMessageState("030");
                    ((InfomationAdapter) infomationList.getAdapter()).notifyDataSetChanged();
                    return;
                }
                if (paiDanMessageInfoBeans.size() > i && paiDanMessageInfoBeans.get(i).getEnterpriseMessageState().equals("030")) {
                    TongZhiActivity.StartTongZhiActivity(getActivity(), "派单成功反馈", TongZhiActivity.PAIDANRETURN);
                    paiDanMessageInfoBeans.get(i).setMessageState("030");
                    ((InfomationAdapter) infomationList.getAdapter()).notifyDataSetChanged();
                    return;
                }
                if (paiDanMessageInfoBeans.size() > i && paiDanMessageInfoBeans.get(i).getEnterpriseMessageState().equals("040")) {
                    TongZhiActivity.StartTongZhiActivity(getActivity(), "已经抢单了", TongZhiActivity.QIANGDAN);
                    paiDanMessageInfoBeans.get(i).setMessageState("030");
                    ((InfomationAdapter) infomationList.getAdapter()).notifyDataSetChanged();
                }
                /*switch (titles[i]) {
                    case "通知信息":
                        break;
                    case "职派活动":
                        break;
                    case "派单成功反馈":
                        TongZhiActivity.StartTongZhiActivity(getActivity(), "派单成功反馈", TongZhiActivity.PAIDANRETURN);
                        paiDanMessageInfoBeans.get(0).setMessageState("030");
                        ((InfomationAdapter)infomationList.getAdapter()).notifyDataSetChanged();
                        break;
                    case "已经抢单了":
                        TongZhiActivity.StartTongZhiActivity(getActivity(), "已经抢单了", TongZhiActivity.QIANGDAN);
                        paiDanMessageInfoBeans.get(1).setMessageState("030");
                        ((InfomationAdapter)infomationList.getAdapter()).notifyDataSetChanged();
                        break;
                }*/
            }
        });
    }


    private void getInfo() {
        if (titles[0].equals("通知信息")) {
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST, MyApplication.path + "api/enterpriseNew/querySystemMessage.do",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String sInfo) {
                            MyLog.e("InfomationItemFragment", "onResponse(InfomationItemFragment.java:216)" + sInfo);
                            try {
                                JSONObject response = new JSONObject(sInfo);
                                if (response.getString("code").equals("success")) {
                                    String info = response.getString("info");
                                    JSONObject infoJson = new JSONObject(info);
                                    String shu = infoJson.getString("shu");
                                    JSONObject shuJson = new JSONObject(shu);
                                    String tz = infoJson.getString("tz");
                                    JSONObject tzJson = new JSONObject(tz);
                                    String tzString = tzJson.getString("success");

                                    String zp = infoJson.getString("zp");
                                    JSONObject zpJson = new JSONObject(zp);
                                    String zpString = zpJson.getString("success");
                                    if (tzString.equals("020") || tzString.equals("030")) {
                                        tongZhiMessageInfoBean = new Gson().fromJson(shuJson.getString("txsj"), MessageInfoBean.class);
                                    }
                                    if (zpString.equals("020") || zpString.equals("030")) {
                                        ziPaiMessageInfoBean = new Gson().fromJson(shuJson.getString("zpsj"), MessageInfoBean.class);
                                    }
                                    tongZhiMessageInfoBean.setMessageState(tzString);
                                    ziPaiMessageInfoBean.setMessageState(zpString);
                                    tongZhiMessageInfoBean.setMessageName("通知消息");
                                    ziPaiMessageInfoBean.setMessageName("职派活动");
                                    if (!TextUtils.isEmpty(tongZhiMessageInfoBean.getCreationtime())) {
                                        messageInfoBeans.add(tongZhiMessageInfoBean);
                                    }
                                    if (!TextUtils.isEmpty(ziPaiMessageInfoBean.getCreationtime())) {
                                        messageInfoBeans.add(ziPaiMessageInfoBean);
                                    }
                                    setRegister();
                                }
                            } catch (JSONException e) {
                                MyLog.e("InfomationItemFragment", "onResponse(InfomationItemFragment.java:127)" + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }));
        } else {
            //派单反馈信息请求
            MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST, MyApplication.path + "api/enterpriseNew/singleFeedback.do",
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String sInfo) {
                    MyLog.e("InfomationItemFragment", "onResponse(InfomationItemFragment.java:260)" + sInfo);
                    try {
                        JSONObject response = new JSONObject(sInfo);
                        if (response.getString("code").equals("success")) {
                            String info = response.getString("info");
                            JSONObject infoJson = new JSONObject(info);
                            String sj = infoJson.getString("sj");
                            MyLog.e("InfomationItemFragment", "onResponse(InfomationItemFragment.java:106)" + sj);
                            JSONObject shuJson = new JSONObject(sj);
                            String tz = infoJson.getString("tz");
                            JSONObject tzJson = new JSONObject(tz);
                            String tzString = tzJson.getString("success");

                            String zp = infoJson.getString("zp");
                            JSONObject zpJson = new JSONObject(zp);
                            String zpString = zpJson.getString("success");
                            if (tzString.equals("020") || tzString.equals("030")) {
                                paiDanMessage = new Gson().fromJson(shuJson.getString("cg"), MessageInfoBean.class);
                            }
                            if (zpString.equals("020") || zpString.equals("030")) {
                                qiangMessage = new Gson().fromJson(shuJson.getString("sg"), MessageInfoBean.class);
                            }
                            paiDanMessage.setMessageState(tzString);
                            qiangMessage.setMessageState(zpString);
                            paiDanMessage.setMessageName("派单成功反馈");
                            qiangMessage.setMessageName("已经抢单了");
                            if (!TextUtils.isEmpty(paiDanMessage.getCreationtime())) {
                                paiDanMessageInfoBeans.add(paiDanMessage);
                            }
                            if (!TextUtils.isEmpty(qiangMessage.getCreationtime())) {
                                paiDanMessageInfoBeans.add(qiangMessage);
                            }
                            setRegister();
                        }
                    } catch (JSONException e) {
                        MyLog.e("InfomationItemFragment", "onResponse(InfomationItemFragment.java:127)" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }));
        }
    }


    /**
     * 注册监听器 实现删除信息的效果
     */
    class InfomationItemBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case LOCAL_BROADCAST:
                    String index = intent.getStringExtra("index");
                    deleteInfo(index);
                    break;
                case SHOW_DELETE_BROADCAST:
                    infomationAdapter.setbShowDelete(bShowDelete);
                    infomationAdapter.notifyDataSetChanged();
                    bShowDelete = !bShowDelete;
                    break;
                case MyApplication.NEWINFO:
                    getInfo();
                    break;
            }
        }
    }


}

package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.RechargeUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.WXPayUtils;
import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class PayFragment extends BaseFragment {

    private Button btn_sure;
    private HashMap hashMap;

    private RechargeUtil rechargeUtil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, null);
        init(view);
        setRegister();
        return view;
    }

    /**
     * payType 020 会员 010 普通
     * @param view
     */
    private void init(View view){
        rechargeUtil = new RechargeUtil(getActivity(), MyApplication.APP_ID);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        hashMap = new HashMap();
        //hashMap.put("totalMoney", 288 * 100);
        hashMap.put("totalMoney", 1);
        hashMap.put("payType", "020");
        hashMap.put("payUser", MyApplication.tokenEnterprise);
    }


    private void setRegister(){
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechargeUtil.getWeiXinPayOrder(hashMap, new RechargeUtil.IGetOrderReturn() {
                    @Override
                    public void error(String message) {
                        ToastUtil.showToastTwo(message);
                    }
                }, 1);
            }
        });
    }


}

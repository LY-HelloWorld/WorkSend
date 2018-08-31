package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterprisePaiDanActivity;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.PaiDanActivity.IPaiDanActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterprisePaiDanActivityModel.IPaiDanActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterprisePaiDanActivityModel.PaiDanActivityModel;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterprisePaiDanActivity.IPaiDanAcitivityPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class PaiDanActivityPresenter implements IPaiDanAcitivityPresenter {

    private IPaiDanActivity paiDanActivity;
    private IPaiDanActivityModel paiDanActivityModel;

    public PaiDanActivityPresenter(IPaiDanActivity paiDanActivity) {
        this.paiDanActivity = paiDanActivity;
        paiDanActivityModel = new PaiDanActivityModel();
    }

    @Override
    public void submitPaiDan(WorkBean workBean) {

        paiDanActivity.showLoadingDialog();
        paiDanActivityModel.submitPaiDanInfo(workBean, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.e("PaiDanActivityPresenter", "onErrorResponse(PaiDanActivityPresenter.java:33)" + "error" + error.getMessage());
                paiDanActivity.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("PaiDanActivityPresenter", "onResponse(PaiDanActivityPresenter.java:39)" + response);
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        JSONObject jsonObject = new JSONObject(info);
                        if ("派单成功".equals(jsonObject.getString("success"))){
                            paiDanActivity.setReturnInfo(true);
                        }else {
                            ToastUtil.showToastTwo(jsonObject.getString("success"));
                        }
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("暂时没有数据");
                }
                paiDanActivity.closeLoadingDialog();
            }
        });

    }
}

package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseHomeFragment;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseHomeFragment.HomeFragmentModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseHomeFragment.IHomeFragmentModel;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment.IHomeFragment;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseHomeFragment.IHomeFragmentPresenter;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.solidfire.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class HomeFragmentPresenterImp implements IHomeFragmentPresenter {

    private IHomeFragmentModel homeFragmentModel;
    private IHomeFragment homeFragment;

    public HomeFragmentPresenterImp(IHomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        homeFragmentModel = new HomeFragmentModel();
    }

    @Override
    public void getList(Map paramNamesAndParmas, final int type) {
        homeFragment.showLoadingDialog();
        homeFragmentModel.getHomeJianLiList(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.e("HomeFragmentPresenterImp", "onErrorResponse(HomeFragmentPresenterImp.java:39)" + error.getMessage());
                homeFragment.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyLog.e("HomeFragmentPresenterImp", "onResponse(HomeFragmentPresenterImp.java:44)" + response);
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        JSONObject infoObject = new JSONObject(info);
                        if (infoObject.getString("success").equals("暂时没有数据")){
                            ToastUtil.showToastTwo("暂时没有数据");
                        }else {
                            JSONArray rows = infoObject.getJSONArray("rows");
                            ArrayList<SampleJianLiData> sampleJianLiData = new Gson().fromJson(rows.toString(), new TypeToken<ArrayList<SampleJianLiData>>(){}.getType());
                            homeFragment.setJianLiList(sampleJianLiData, type);
                        }
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("暂时没有数据");
                }
                homeFragment.closeLoadingDialog();
            }
        });

    }

}

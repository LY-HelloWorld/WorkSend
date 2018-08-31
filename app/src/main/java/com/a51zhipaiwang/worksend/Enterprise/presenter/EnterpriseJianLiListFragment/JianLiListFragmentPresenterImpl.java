package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiListFragment;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiFragmentModel.IJianLiFragmentModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseJianLiFragmentModel.JianLiFragmentModel;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment.IJianLiListFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment.JianLiListFragment;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiListFragment.IJianLiListFragmentPresenter;
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


public class JianLiListFragmentPresenterImpl implements IJianLiListFragmentPresenter {

    private IJianLiListFragment jianLiListFragment;
    private IJianLiFragmentModel iJianLiFragmentModel;

    public JianLiListFragmentPresenterImpl(IJianLiListFragment jianLiListFragment) {
        this.jianLiListFragment = jianLiListFragment;
        this.iJianLiFragmentModel = new JianLiFragmentModel();
    }

    @Override
    public void getList(Map paramNamesAndParmas, String path, final int type) {
        iJianLiFragmentModel.getHomeJianLiList(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showToastTwo("请检查您的网络连接!");
                jianLiListFragment.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                MyLog.e("HomeFragmentPresenterImp", "onResponse(HomeFragmentPresenterImp.java:46)" + response);
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        ArrayList<SampleJianLiData> sampleJianLiData = new Gson().fromJson(info,
                                new TypeToken<ArrayList<SampleJianLiData>>() {}.getType());
                        if (type == JianLiListFragment.SETINFO) {
                            jianLiListFragment.setJianLiList(sampleJianLiData);
                        }
                        if (type == JianLiListFragment.ADDINFO) {
                            jianLiListFragment.addJianLiList(sampleJianLiData);
                        }
                    }
                } catch (Exception e) {
                    ToastUtil.showToastTwo("暂时没有数据");
                }
                jianLiListFragment.closeLoadingDialog();
            }
        }, path);
    }

    @Override
    public void addList(Map paramNamesAndParmas, String path, final int type) {
        iJianLiFragmentModel.getHomeJianLiList(paramNamesAndParmas, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showToastTwo("请检查您的网络连接!");
                jianLiListFragment.closeLoadingDialog();
            }
        }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (!responseJson.getString("code").equals("success")) {
                        ToastUtil.showToastTwo("请检查您的网络连接!");
                    } else {
                        String info = responseJson.getString("info");
                        JSONObject infoObject = new JSONObject(info);
                        JSONArray rows = infoObject.getJSONArray("rows");
                        ArrayList<SampleJianLiData> sampleJianLiData = new Gson().fromJson(rows.toString(), new TypeToken<ArrayList<SampleJianLiData>>() {
                        }.getType());
                        //jianLiListFragment.addJianLiList(sampleJianLiData);
                        if (type == JianLiListFragment.SETINFO) {
                            jianLiListFragment.setJianLiList(sampleJianLiData);
                        }
                        if (type == JianLiListFragment.ADDINFO) {
                            jianLiListFragment.addJianLiList(sampleJianLiData);
                        }
                    }
                } catch (JSONException e) {
                    ToastUtil.showToastTwo("暂时没有新的数据");
                }
                jianLiListFragment.closeLoadingDialog();
            }
        }, path);
    }

}

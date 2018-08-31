package com.a51zhipaiwang.worksend.Personal.trypathfragment.presenter;

import com.a51zhipaiwang.worksend.Personal.entity.TryPath;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.contract.ITryPathContract;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.model.ITryPathModel;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.solidfire.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/20
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
public class ITryPathPresenter implements ITryPathContract.Presenter {

    private ITryPathContract.Model iTryPathModel;
    private ITryPathContract.View iTryPathView;

    public ITryPathPresenter(ITryPathContract.View iTryPathView) {
        this.iTryPathView = iTryPathView;
        this.iTryPathModel = new ITryPathModel();
    }

    @Override
    public void getTryWorking(Map paramMap) {
        iTryPathView.showLoadingDialog();
        iTryPathModel.getTryWorking(paramMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("success")){
                                String info = jsonObject.getString("info");
                                JSONObject infoJson = new JSONObject(info);
                                String list = infoJson.getString("list");
                                ArrayList arrayList = new Gson().fromJson(list, new TypeToken<ArrayList<TryPath>>(){}.getType());
                                iTryPathView.setTryWorking(arrayList);
                            }
                        } catch (JSONException e) {
                            iTryPathView.setReturnInfo("请检查您的网络连接");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iTryPathView.setReturnInfo("请检查您的网络连接");
                    }
                });
    }

    @Override
    public void getTryOutWork(Map paramMap) {
        iTryPathView.showLoadingDialog();
        iTryPathModel.getTryWorking(paramMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("success")){
                                String info = jsonObject.getString("info");
                                JSONObject infoJson = new JSONObject(info);
                                String list = infoJson.getString("list");
                                ArrayList arrayList = new Gson().fromJson(list, new TypeToken<ArrayList<TryPath>>(){}.getType());
                                iTryPathView.setTryOutWork(arrayList);
                            }
                        } catch (JSONException e) {
                            iTryPathView.setReturnInfo("请检查您的网络连接");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iTryPathView.setReturnInfo("请检查您的网络连接");
                    }
                });
    }
}

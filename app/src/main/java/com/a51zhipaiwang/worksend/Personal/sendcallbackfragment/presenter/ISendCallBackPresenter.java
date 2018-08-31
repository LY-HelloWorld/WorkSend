package com.a51zhipaiwang.worksend.Personal.sendcallbackfragment.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.sendcallbackfragment.contract.ISendCallBackContract;
import com.a51zhipaiwang.worksend.Personal.sendcallbackfragment.model.ISendCallBackModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

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
public class ISendCallBackPresenter implements ISendCallBackContract.Presenter {

    private ISendCallBackContract.View view;
    private ISendCallBackContract.Model model;

    public ISendCallBackPresenter(ISendCallBackContract.View view) {
        this.view = view;
        this.model = new ISendCallBackModel();
    }

    @Override
    public void getCallBackInfoList(Map map) {
        view.showLoadingDialog();
        model.getCallBackInfoList(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                try {
                    MyLog.e("ISendCallBackPresenter", "success(ISendCallBackPresenter.java:50)" + info);
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        String success = infoJson.getString("success");
                        if (!success.equals("沒有数据")){
                            ArrayList arrayList = new Gson().fromJson(success, new TypeToken<ArrayList<Infor>>(){}.getType());
                            if (arrayList != null && arrayList.size() >= 1){
                                view.setCallBackList(arrayList.get(0));
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                MyLog.e("IHRPresenter", "error(IHRPresenter.java:47)" + error);
            }
        });
    }

    @Override
    public void deleteCallBackItem(Map map) {
        view.showLoadingDialog();
        model.deleteCallBackItem(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ISendCallBackPresenter", "success(ISendCallBackPresenter.java:81)" + info);
                view.closeLoadingDialog();
                view.setDeleteReturn(true);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}

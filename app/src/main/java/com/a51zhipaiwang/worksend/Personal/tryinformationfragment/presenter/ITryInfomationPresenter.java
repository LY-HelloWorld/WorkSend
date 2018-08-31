package com.a51zhipaiwang.worksend.Personal.tryinformationfragment.presenter;

import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.tryinformationfragment.contract.ITryInfomationContract;
import com.a51zhipaiwang.worksend.Personal.tryinformationfragment.model.ITryInfomationModel;
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
public class ITryInfomationPresenter implements ITryInfomationContract.Presenter {

    private ITryInfomationContract.View view;
    private ITryInfomationContract.Model model;

    public ITryInfomationPresenter(ITryInfomationContract.View view) {
        this.view = view;
        this.model = new ITryInfomationModel();
    }

    @Override
    public void getTryInformation(Map map) {
        view.showLoadingDialog();

        model.getTryInformation(map, new ITryInfomationContract.Model.ITryInformationCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ITryInfomationPresenter", "success(ITryInfomationPresenter.java:50)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        String success = infoJson.getString("success");
                        if (success.equals("沒有数据")){
                            view.setTryInformation(new ArrayList());
                        }else {
                            ArrayList arrayList = new Gson().fromJson(success, new TypeToken<ArrayList<Infor>>(){}.getType());
                            view.setTryInformation(arrayList);
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
        /*model.getTryInformation(map, new ITryInfomationContract.Model.ITryInformationCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
                view.setTryInformation(arrayList);
            }

            @Override
            public void error(String error) {

            }
        });*/
    }

    @Override
    public void deleteTryInformation(Map map) {
        view.showLoadingDialog();
        model.deleteTryInformation(map, new ITryInfomationContract.Model.ITryInformationCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                MyLog.e("ITryInfomationPresenter", "success(ITryInfomationPresenter.java:99)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        view.deleteReturnInfo(infoJson.getString("id"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String error) {

            }
        });
    }
}

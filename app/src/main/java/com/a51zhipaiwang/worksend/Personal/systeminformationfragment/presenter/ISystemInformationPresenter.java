package com.a51zhipaiwang.worksend.Personal.systeminformationfragment.presenter;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.systeminformationfragment.contract.ISystemInformationContract;
import com.a51zhipaiwang.worksend.Personal.systeminformationfragment.model.ISystemInformationModel;
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
 *     time   : 2018/07/25
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
public class ISystemInformationPresenter implements ISystemInformationContract.Presenter {

    private ISystemInformationContract.View view;
    private ISystemInformationContract.Model model;

    public ISystemInformationPresenter(ISystemInformationContract.View view) {
        this.view = view;
        this.model = new ISystemInformationModel();
    }

    @Override
    public void getSystemInformation(Map map) {
        view.showLoadingDialog();
        model.getSystemInformation(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ISystemInformationPresenter", "success(ISystemInformationPresenter.java:50)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        String success = infoJson.getString("success");
                        if (success.equals("沒有数据")){
                            view.setSystemInformation(null);
                        }else {
                            ArrayList<Infor> arrayList = new Gson().fromJson(success, new TypeToken<ArrayList<Infor>>(){}.getType());
                            if (arrayList != null && arrayList.size() >= 1){
                                view.setSystemInformation(arrayList.get(0));
                            }
                        }
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

    @Override
    public void deleteSystemInfomation(Map map) {

        view.showLoadingDialog();
        model.deleteSystemInfomation(map, new IBaseCallBack() {
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

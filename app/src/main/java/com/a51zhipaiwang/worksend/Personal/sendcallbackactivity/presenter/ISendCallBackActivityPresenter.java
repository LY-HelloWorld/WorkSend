package com.a51zhipaiwang.worksend.Personal.sendcallbackactivity.presenter;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.entity.HRSendInfor;
import com.a51zhipaiwang.worksend.Personal.entity.Infor;
import com.a51zhipaiwang.worksend.Personal.sendcallbackactivity.contract.ISendCallBackActivityContract;
import com.a51zhipaiwang.worksend.Personal.sendcallbackactivity.model.ISendCallBackActivityModel;
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
public class ISendCallBackActivityPresenter implements ISendCallBackActivityContract.Presenter {

    private ISendCallBackActivityContract.View view;
    private ISendCallBackActivityContract.Model model;

    public ISendCallBackActivityPresenter(ISendCallBackActivityContract.View view) {
        this.view = view;
        this.model = new ISendCallBackActivityModel();
    }

    @Override
    public void getSendBackInfoList(Map map) {
        view.showLoadingDialog();
        model.getSendBackInfoList(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                MyLog.e("ISendCallBackActivityPresenter", "success(ISendCallBackActivityPresenter.java:52)" + info);
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        String success = infoJson.getString("success");
                        if (success.equals("沒有数据")){
                            view.setSendBackInfoList(new ArrayList<Infor>());
                        }else {
                            ArrayList arrayList = new Gson().fromJson(success, new TypeToken<ArrayList<Infor>>(){}.getType());
                            view.setSendBackInfoList(arrayList);
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
}

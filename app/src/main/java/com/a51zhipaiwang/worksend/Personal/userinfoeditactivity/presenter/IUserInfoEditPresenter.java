package com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.presenter;

import android.net.Uri;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.contract.IUserInfoEditContract;
import com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.model.IUserInfoEditModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

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
public class IUserInfoEditPresenter implements IUserInfoEditContract.Presenter {

    private IUserInfoEditModel iUserInfoEditModel;
    private IUserInfoEditContract.View view;

    public IUserInfoEditPresenter(IUserInfoEditContract.View view) {
        this.view = view;
        this.iUserInfoEditModel = new IUserInfoEditModel();
    }

    @Override
    public void submitUserInfo(Map paramMap) {
        view.showLoadingDialog();
        iUserInfoEditModel.submitUserInfo(paramMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.e("IUserInfoEditPresenter", "onResponse(IUserInfoEditPresenter.java:50)" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("success")){
                                String sInfo = jsonObject.getString("info");
                                JSONObject infoJson = new JSONObject(sInfo);
                                if (infoJson.getString("success").equals("新增用户信息成功") || infoJson.getString("success").equals("修改用户信息成功")){
                                    view.submitReturnInfo(true);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        view.submitReturnInfo(false);
                    }
                });
    }

    @Override
    public void coverLocationFromText(String sLocation) {
        //Geocoder geocoder = Geocoder
        /*
        try {
            view.setLocationFromText(geocoder.getFromLocationName(sLocation, 1).get(0));
        } catch (Exception e) {
            view.setLocationFromText(null);
            e.printStackTrace();
        }*/
    }

    @Override
    public void upLoadUserImage(Uri uri) {
        iUserInfoEditModel.upLoadUserImage(((BaseActivity) view),
                uri, new UpLoadFile.UpLoadFileListener() {
            @Override
            public void success(String info) {
                try {
                    MyLog.e("IUserInfoEditPresenter", "success(IUserInfoEditPresenter.java:96)" + info);
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("result").equals("上传成功")){
                        String path = jsonObject.getString("lj");
                        view.setUpLoadImageReturnInfo(path);
                    }else {
                        ToastUtil.showToastTwo("上传失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String info) {
                MyLog.e("IUserInfoEditPresenter", "error(IUserInfoEditPresenter.java:111)" + info);
                ToastUtil.showToastTwo("上传失败");
            }
        });
    }
}

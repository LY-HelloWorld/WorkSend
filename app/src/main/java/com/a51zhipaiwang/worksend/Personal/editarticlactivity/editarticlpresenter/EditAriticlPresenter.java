package com.a51zhipaiwang.worksend.Personal.editarticlactivity.editarticlpresenter;

import android.net.Uri;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Personal.editarticlactivity.editarticlcontract.EditAriticlContract;
import com.a51zhipaiwang.worksend.Personal.editarticlactivity.editarticlmodel.EditAriticlModel;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/29
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
public class EditAriticlPresenter implements EditAriticlContract.Presenter {

    private EditAriticlContract.View view;
    private EditAriticlContract.Model model;

    public EditAriticlPresenter(EditAriticlContract.View view) {
        this.view = view;
        this.model = new EditAriticlModel();
    }

    @Override
    public void upLoadUserImage(Uri uri, final int type) {
        view.showLoadingDialog();
        model.upLoadImage(((BaseActivity) view),
                uri, new UpLoadFile.UpLoadFileListener() {
                    @Override
                    public void success(String info) {
                        view.closeLoadingDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(info);
                            if (jsonObject.getString("result").equals("上传成功")){
                                String path = jsonObject.getString("lj");
                                view.returnUploadImage(path, type);
                            }else {
                                ToastUtil.showToastTwo("上传失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(String info) {
                        view.closeLoadingDialog();
                        view.returnUploadImage("", view.ERROR);
                    }
                });
    }

    @Override
    public void upLoadAriticl(Map map) {
        view.showLoadingDialog();
        model.upLoadAriticl(map, new IBaseCallBack() {
            @Override
            public void success(String info) {
                view.closeLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("code").equals("success")){
                        String sInfo = jsonObject.getString("info");
                        JSONObject infoJson = new JSONObject(sInfo);
                        if (infoJson.getString("success").equals("文章发布成功")){
                            view.returnUpLoadArticl(true);
                        }else {
                            view.returnUpLoadArticl(false);
                        }
                    }else {
                        view.returnUpLoadArticl(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.returnUpLoadArticl(false);
                }
                MyLog.e("EditAriticlPresenter", "success(EditAriticlPresenter.java:78)" + info);
            }

            @Override
            public void error(String error) {
                view.closeLoadingDialog();
                view.returnUpLoadArticl(false);
            }
        });
    }
}

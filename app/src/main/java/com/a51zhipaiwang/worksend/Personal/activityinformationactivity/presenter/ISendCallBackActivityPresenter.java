package com.a51zhipaiwang.worksend.Personal.activityinformationactivity.presenter;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Personal.activityinformationactivity.contract.IActivityCallBackActivityContract;
import com.a51zhipaiwang.worksend.Personal.activityinformationactivity.model.ISendCallBackActivityModel;

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
public class ISendCallBackActivityPresenter implements IActivityCallBackActivityContract.Presenter {

    private IActivityCallBackActivityContract.View view;
    private IActivityCallBackActivityContract.Model model;

    public ISendCallBackActivityPresenter(IActivityCallBackActivityContract.View view) {
        this.view = view;
        this.model = new ISendCallBackActivityModel();
    }

    @Override
    public void getActivityBackInfoList(Map map) {
        view.showLoadingDialog();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 24; i++) {
            MessageInfoBean messageInfoBean = new MessageInfoBean();
            messageInfoBean.setCreationtime("2018-01-02");
            messageInfoBean.setMessageContent("测试消息");
            arrayList.add(messageInfoBean);
        }
        view.setActivityBackInfoList(arrayList);

    }
}

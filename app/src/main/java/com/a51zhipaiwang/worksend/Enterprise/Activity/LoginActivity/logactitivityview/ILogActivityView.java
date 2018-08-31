package com.a51zhipaiwang.worksend.Enterprise.Activity.LoginActivity.logactitivityview;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
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
public interface ILogActivityView extends IBaseActivity {

    /**
     * 检查用户输入信息
     * @return
     */
    public String checkUserInputInfo();

    /**
     * 配置用户信息提交参数
     * @return
     */
    public Map setMapWithUserInfo();

    /**
     * 登录反馈信息
     * @param returnInfo 具体反馈信息
     */
    public void logReturnInfo(String returnInfo);




}

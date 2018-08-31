package com.a51zhipaiwang.worksend.Enterprise.Fragment.BusinessFragment;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/08/03
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
public interface IBusinessFragment {


    /**
     * 展示加载框
     */
    public void showLoadingDialog();


    /**
     * 关闭加载框
     */
    public void closeLoadingDialog();


    public void setBusinessInfo(Object businessInfo);

}

package com.a51zhipaiwang.worksend.CommonActivity.logchoice.presenter;


import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface ILogChoicePresenter {

    /**
     * 我是求职者
     * @param baseActivity
     */
    public void workerChoice(BaseActivity baseActivity);

    /**
     * 我是招聘者
     * @param baseActivity
     */
    public void recruitChoice(BaseActivity baseActivity);


}

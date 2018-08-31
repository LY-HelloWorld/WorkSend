package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseMoneyActivityPresenter;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/17
 *     desc   : 充值presenter
 *     version: 1.0
 * </pre>
 */
public interface IMoneyActivityPresenter {

    public void getMoney(Map paramMap);

    public void recharge(Map paramMap, int type);

    public void withDraw(Map paramMap);

}

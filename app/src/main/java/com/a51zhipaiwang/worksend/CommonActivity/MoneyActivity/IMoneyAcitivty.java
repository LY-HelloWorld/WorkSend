package com.a51zhipaiwang.worksend.CommonActivity.MoneyActivity;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

public interface IMoneyAcitivty extends IBaseActivity {



    /**
     * 获取金额
     * @param sMoney
     */
    public void setMoney(String sMoney);


    public void returnInfo(boolean bReturn);


    /**
     * 提现反馈
     * @param info
     */
    public void returnWithDrawo(String info);

}

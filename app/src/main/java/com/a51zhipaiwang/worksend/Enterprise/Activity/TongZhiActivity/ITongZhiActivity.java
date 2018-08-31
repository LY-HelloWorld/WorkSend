package com.a51zhipaiwang.worksend.Enterprise.Activity.TongZhiActivity;

import com.a51zhipaiwang.worksend.Bean.MessageInfoBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

import java.util.ArrayList;

public interface ITongZhiActivity extends IBaseActivity {


    /**
     * 将返回的数据 设置信息
     * @param messageInfoBeans
     */
    public void setReturnInfo(ArrayList<MessageInfoBean> messageInfoBeans);


    public void surePosition(boolean bSurePosition);

}

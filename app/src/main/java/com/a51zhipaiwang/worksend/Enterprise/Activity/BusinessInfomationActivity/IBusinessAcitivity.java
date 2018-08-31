package com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity;

import android.location.Address;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.IBaseActivity;

public interface IBusinessAcitivity extends IBaseActivity {

    /**
     * 反馈公司信息，上传信息
     * @param info
     */
    public void returnSub(boolean info);

    /**
     * 反馈logo上传消息
     * @param info
     */
    public void returnLogoSubmitSuccess(String info);

    /**
     * 反馈视频上传消息
     * @param info
     */
    public void returnVideoSubmitSuccess(String info);

    /**
     * 反馈封面上传消息
     * @param info
     */
    public void returnFengMianSubmitSuccess(String info);



    /**
     * 地址转换为经纬度
     * @param address
     */
    public void stringCoverLocation(Address address);

}

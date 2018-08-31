package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessPresenter;


import com.a51zhipaiwang.worksend.Bean.BusinessInfo;

public interface IBusinessPresenter {

    public void submitBusinessInfo(BusinessInfo businessInfo);

    public void getLocationFromString(String locationString);


}

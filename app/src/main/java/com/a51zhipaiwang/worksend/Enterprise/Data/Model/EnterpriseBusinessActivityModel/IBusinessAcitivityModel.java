package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseBusinessActivityModel;

import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;

public interface IBusinessAcitivityModel {

    public void submitBusinessInfo(BusinessInfo businessInfo, Response.ErrorListener errorListener, Response.Listener<String> listener);

}

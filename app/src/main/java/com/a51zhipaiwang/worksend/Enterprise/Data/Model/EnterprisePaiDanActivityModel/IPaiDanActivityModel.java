package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterprisePaiDanActivityModel;

import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.android.volley.Response;


public interface IPaiDanActivityModel {

    public void submitPaiDanInfo(WorkBean workBean, Response.ErrorListener errorListener, Response.Listener<String> listener);

}

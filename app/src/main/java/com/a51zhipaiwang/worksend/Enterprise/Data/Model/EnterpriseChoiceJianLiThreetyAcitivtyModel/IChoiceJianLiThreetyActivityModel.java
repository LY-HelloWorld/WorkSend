package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseChoiceJianLiThreetyAcitivtyModel;

import com.a51zhipaiwang.worksend.Bean.SubChoiceListThreetyJianLi;
import com.android.volley.Response;

import java.util.Map;


public interface IChoiceJianLiThreetyActivityModel {


    public void getJianLi(Map map, Response.ErrorListener errorListener, Response.Listener<String> listener);

    public void submitList(SubChoiceListThreetyJianLi subChoiceListThreetyJianLi, Response.ErrorListener errorListener, Response.Listener<String> listener);

}

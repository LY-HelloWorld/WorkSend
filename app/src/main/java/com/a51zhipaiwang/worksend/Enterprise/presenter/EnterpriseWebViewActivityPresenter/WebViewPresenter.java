package com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseWebViewActivityPresenter;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WebViewActivity.IWebView;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WebViewActivity.WebViewActivity;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWebViewModel.IWebViewModel;
import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWebViewModel.WebViewActivityModel;
import com.a51zhipaiwang.worksend.Utils.WXShare;

public class WebViewPresenter implements IWebViewPresenter {

    private IWebViewModel webViewModel;

    private IWebView webView;

    public WebViewPresenter(IWebView webView) {
        this.webView = webView;
        this.webViewModel = new WebViewActivityModel();
    }

    @Override
    public void fenXiang(String path) {
        webViewModel.fenXiang(path);

        WXShare wxShare = new WXShare(((BaseActivity)webView));
        wxShare.shareUrl(1, ((BaseActivity)webView), path, "职派分享", "面试就有钱");
    }
}

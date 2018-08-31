package com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWebViewModel;

import com.a51zhipaiwang.worksend.Enterprise.Data.Model.EnterpriseWebViewModel.IWebViewModel;

public class WebViewActivityModel implements IWebViewModel {
    @Override
    public void fenXiang(String title, String subTitle, String path, String imagePath) {

    }

    @Override
    public void fenXiang(String subTitle, String path, String imagePath) {
        this.fenXiang("", "", path, "");
    }

    @Override
    public void fenXiang(String path, String imagePath) {
        this.fenXiang("", path, "");
    }

    @Override
    public void fenXiang(String path) {
        this.fenXiang(path, "");
    }
}

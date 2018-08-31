package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;

public class BusinessInfo implements Serializable {
    private Integer id;

    private String enterpriseName;

    private String enterpriseLogo;

    private String enterpriseVideo;

    private String product;

    private String companyNature;

    private String industry;

    private String companyScale;

    private String enterprisePurse;

    private String enterprisePhone;

    private String enterprisePosition;

    private String enterpriseLatitudeLongitude;

    private int loginId;

    private String creationName;

    private String creationTime;

    private String companyIntroduce;

    private String col1;

    private String col2;

    private String authenticationState;

    private String longitude;

    private String latitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo == null ? null : enterpriseLogo.trim();
    }

    public String getEnterpriseVideo() {
        return enterpriseVideo;
    }

    public void setEnterpriseVideo(String enterpriseVideo) {
        this.enterpriseVideo = enterpriseVideo == null ? null : enterpriseVideo.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature == null ? null : companyNature.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale == null ? null : companyScale.trim();
    }

    public String getEnterprisePurse() {
        return enterprisePurse;
    }

    public void setEnterprisePurse(String enterprisePurse) {
        this.enterprisePurse = enterprisePurse == null ? null : enterprisePurse.trim();
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone == null ? null : enterprisePhone.trim();
    }

    public String getEnterprisePosition() {
        return enterprisePosition;
    }

    public void setEnterprisePosition(String enterprisePosition) {
        this.enterprisePosition = enterprisePosition == null ? null : enterprisePosition.trim();
    }

    public String getEnterpriseLatitudeLongitude() {
        return enterpriseLatitudeLongitude;
    }

    public void setEnterpriseLatitudeLongitude(String enterpriseLatitudeLongitude) {
        this.enterpriseLatitudeLongitude = enterpriseLatitudeLongitude == null ? null : enterpriseLatitudeLongitude.trim();
    }

   
    public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName == null ? null : creationName.trim();
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime == null ? null : creationTime.trim();
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce == null ? null : companyIntroduce.trim();
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1 == null ? null : col1.trim();
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2 == null ? null : col2.trim();
    }

    public String getAuthenticationState() {
        return authenticationState;
    }

    public void setAuthenticationState(String authenticationState) {
        this.authenticationState = authenticationState == null ? null : authenticationState.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
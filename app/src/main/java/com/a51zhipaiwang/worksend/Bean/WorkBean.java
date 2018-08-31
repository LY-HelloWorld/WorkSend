package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;

public class WorkBean implements Serializable{
    //id
    private String id = "";
    //城市
    private String city = "";
    //区域
    private String area = "";

    private String recruitmentModule = "";

    private String recruitmentIndustry = "";
    //职位
    private String recruitmentPosition = "";
    //性别
    private String sex = "";
    //工作经验
    private String workExperience = "";
    //教育经历
    private String education = "";
    //工资
    private String salaryStandard = "";
    //试用工资
    private String trialPostSalary = "";
    //试用时间
    private String trialPostTime = "";
    //工作描述
    private String jobDescription = "";

    private String creationTime = "";

    private String creationName = "";

    private String modificationTime = "";

    private String modificationName = "";

    private int enterpriseInfoId = 0;

    private String enterpriseName = "";

    private String enterprisePhone = "";

    private String workNature = "";

    private String col1 = "";

    private String col2 = "";

    private String distributeSalary = "";

    private String longitude;

    private String latitude;

    private String zwName;
   

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getRecruitmentModule() {
        return recruitmentModule;
    }

    public void setRecruitmentModule(String recruitmentModule) {
        this.recruitmentModule = recruitmentModule == null ? null : recruitmentModule.trim();
    }

    public String getRecruitmentIndustry() {
        return recruitmentIndustry;
    }

    public void setRecruitmentIndustry(String recruitmentIndustry) {
        this.recruitmentIndustry = recruitmentIndustry == null ? null : recruitmentIndustry.trim();
    }

    public String getRecruitmentPosition() {
        return recruitmentPosition;
    }

    public void setRecruitmentPosition(String recruitmentPosition) {
        this.recruitmentPosition = recruitmentPosition == null ? null : recruitmentPosition.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience == null ? null : workExperience.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getSalaryStandard() {
        return salaryStandard;
    }

    public void setSalaryStandard(String salaryStandard) {
        this.salaryStandard = salaryStandard == null ? null : salaryStandard.trim();
    }

    public String getTrialPostSalary() {
        return trialPostSalary;
    }

    public void setTrialPostSalary(String trialPostSalary) {
        this.trialPostSalary = trialPostSalary == null ? null : trialPostSalary.trim();
    }

    public String getTrialPostTime() {
        return trialPostTime;
    }

    public void setTrialPostTime(String trialPostTime) {
        this.trialPostTime = trialPostTime == null ? null : trialPostTime.trim();
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription == null ? null : jobDescription.trim();
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime == null ? null : creationTime.trim();
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName == null ? null : creationName.trim();
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime == null ? null : modificationTime.trim();
    }

    public String getModificationName() {
        return modificationName;
    }

    public void setModificationName(String modificationName) {
        this.modificationName = modificationName == null ? null : modificationName.trim();
    }

    
    public int getEnterpriseInfoId() {
		return enterpriseInfoId;
	}

	public void setEnterpriseInfoId(int enterpriseInfoId) {
		this.enterpriseInfoId = enterpriseInfoId;
	}

	public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone == null ? null : enterprisePhone.trim();
    }

    public String getWorkNature() {
        return workNature;
    }

    public void setWorkNature(String workNature) {
        this.workNature = workNature == null ? null : workNature.trim();
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

    public String getDistributeSalary() {
        return distributeSalary;
    }

    public void setDistributeSalary(String distributeSalary) {
        this.distributeSalary = distributeSalary == null ? null : distributeSalary.trim();
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

    public String getZwName() {
        return zwName;
    }

    public void setZwName(String zwName) {
        this.zwName = zwName;
    }
}
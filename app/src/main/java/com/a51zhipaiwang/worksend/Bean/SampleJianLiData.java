package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;

public class SampleJianLiData implements Serializable {
    //id
    private String id;
    //教育
    private String education;
    //距离
    private String kilometre;
    //维度
    private String latitude;
    //经度
    private String longitude;
    //在职状态
    private String positionstatus;
    //性别
    private String sex;
    //用户名
    private String userName;
    //工作经历
    private String workingLife;
    //期望城市
    private String expectCity;
    //用户头像
    private String col1;
    //用户年龄
    private String userAge;
    //工资
    private String salaryexpectation;
    //是否选中
    private boolean choice = false;
    //职位名字
    private String zwName;

    private String curriculumVitaeDetailsId;

    private String col2;

    private String expectedcareer;

    private String presentaddress;

    public String getCurriculumVitaeDetailsId() {
        return curriculumVitaeDetailsId;
    }

    public void setCurriculumVitaeDetailsId(String curriculumVitaeDetailsId) {
        this.curriculumVitaeDetailsId = curriculumVitaeDetailsId;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getExpectedcareer() {
        return expectedcareer;
    }

    public void setExpectedcareer(String expectedcareer) {
        this.expectedcareer = expectedcareer;
    }

    public String getPresentaddress() {
        return presentaddress;
    }

    public void setPresentaddress(String presentaddress) {
        this.presentaddress = presentaddress;
    }

    public String getZwName() {
        return zwName;
    }

    public void setZwName(String zwName) {
        this.zwName = zwName;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }


    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
    }

    public String getKilometre() {
        return kilometre;
    }

    public void setKilometre(String kilometre) {
        this.kilometre = kilometre;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPositionstatus() {
        return this.positionstatus;
    }

    public void setPositionstatus(String positionstatus) {
        this.positionstatus = positionstatus;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(String workingLife) {
        this.workingLife = workingLife;
    }

    public String getExpectCity() {
        return expectCity;
    }

    public void setExpectCity(String expectCity) {
        this.expectCity = expectCity;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getSalaryexpectation() {
        return salaryexpectation;
    }

    public void setSalaryexpectation(String salaryexpectation) {
        this.salaryexpectation = salaryexpectation;
    }
}

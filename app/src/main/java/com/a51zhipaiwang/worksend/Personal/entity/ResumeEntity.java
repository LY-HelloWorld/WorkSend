package com.a51zhipaiwang.worksend.Personal.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/25
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class ResumeEntity implements Serializable {


    private String id;
    private String userName;
    private String sex;
    private String userAge;
    private String presentaddress;
    //视频封面
    private String col4;
    private String  visualscreen;
    private String workingLife;
    //求职类型（010兼职/020全职）
    private String col2;
    private String expectedcareer;
    private String expectedcareerName;
    private String salaryexpectation;
    private String education;
    private String expectCity;
    //职位状态(1离职/2在职)
    private String positionstatus;
    private String masteryOfSkillsOne;
    private String masteryOfSkillsTwo;
    private String masteryOfSkillsThree;
    private String otherMasterySkills;
    private String otherAwardsone;
    private String otherAwardstwo;
    private String otherAwardsthree;
    private String longitude;
    private String latitude;
    private ArrayList<TblEducationalExperience> ee;
    private ArrayList<TblWorkExperience> es;
    private ArrayList<TblProjectExperience> pe;

    public ResumeEntity() {
        ee = new ArrayList<>();
        es = new ArrayList<>();
        pe = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getPresentaddress() {
        return presentaddress;
    }

    public void setPresentaddress(String presentaddress) {
        this.presentaddress = presentaddress;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getVisualscreen() {
        return visualscreen;
    }

    public void setVisualscreen(String visualscreen) {
        this.visualscreen = visualscreen;
    }

    public String getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(String workingLife) {
        this.workingLife = workingLife;
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

    public String getSalaryexpectation() {
        return salaryexpectation;
    }

    public void setSalaryexpectation(String salaryexpectation) {
        this.salaryexpectation = salaryexpectation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExpectCity() {
        return expectCity;
    }

    public void setExpectCity(String expectCity) {
        this.expectCity = expectCity;
    }

    public String getPositionstatus() {
        return positionstatus;
    }

    public void setPositionstatus(String positionstatus) {
        this.positionstatus = positionstatus;
    }

    public String getMasteryOfSkillsOne() {
        return masteryOfSkillsOne;
    }

    public void setMasteryOfSkillsOne(String masteryOfSkillsOne) {
        this.masteryOfSkillsOne = masteryOfSkillsOne;
    }

    public String getMasteryOfSkillsTwo() {
        return masteryOfSkillsTwo;
    }

    public void setMasteryOfSkillsTwo(String masteryOfSkillsTwo) {
        this.masteryOfSkillsTwo = masteryOfSkillsTwo;
    }

    public String getMasteryOfSkillsThree() {
        return masteryOfSkillsThree;
    }

    public void setMasteryOfSkillsThree(String masteryOfSkillsThree) {
        this.masteryOfSkillsThree = masteryOfSkillsThree;
    }

    public String getOtherMasterySkills() {
        return otherMasterySkills;
    }

    public void setOtherMasterySkills(String otherMasterySkills) {
        this.otherMasterySkills = otherMasterySkills;
    }

    public String getOtherAwardsone() {
        return otherAwardsone;
    }

    public void setOtherAwardsone(String otherAwardsone) {
        this.otherAwardsone = otherAwardsone;
    }

    public String getOtherAwardstwo() {
        return otherAwardstwo;
    }

    public void setOtherAwardstwo(String otherAwardstwo) {
        this.otherAwardstwo = otherAwardstwo;
    }

    public String getOtherAwardsthree() {
        return otherAwardsthree;
    }

    public void setOtherAwardsthree(String otherAwardsthree) {
        this.otherAwardsthree = otherAwardsthree;
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

    public ArrayList<TblEducationalExperience> getEe() {
        return ee;
    }

    public void setEe(ArrayList<TblEducationalExperience> ee) {
        this.ee = ee;
    }

    public ArrayList<TblWorkExperience> getEs() {
        return es;
    }

    public void setEs(ArrayList<TblWorkExperience> es) {
        this.es = es;
    }

    public ArrayList<TblProjectExperience> getPe() {
        return pe;
    }

    public void setPe(ArrayList<TblProjectExperience> pe) {
        this.pe = pe;
    }

    public String getExpectedcareerName() {
        return expectedcareerName;
    }

    public void setExpectedcareerName(String expectedcareerName) {
        this.expectedcareerName = expectedcareerName;
    }
}

package com.a51zhipaiwang.worksend.Personal.entity;

import java.io.Serializable;

public class TblEducationalExperience implements Serializable {
    private Integer id;

    //大学名
    private String universityname;
    //专业
    private String major;
    //学制
    private String schoolsystem;
    //入学时间
    private String enrolmenttime;
    //毕业时间
    private String graduationtime;
    //专业类别
    private String professionalcategory;
    //专业描述
    private String professionaldescription;

    private String creationtime;

    private String creationuser;

    private Integer col1;

    private String col2;
    //在校经验
    private String associationActivity;
    //学校等级
    private String sschoolGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityname() {
        return universityname;
    }

    public void setUniversityname(String universityname) {
        this.universityname = universityname == null ? null : universityname.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getSchoolsystem() {
        return schoolsystem;
    }

    public void setSchoolsystem(String schoolsystem) {
        this.schoolsystem = schoolsystem == null ? null : schoolsystem.trim();
    }

    public String getEnrolmenttime() {
        return enrolmenttime;
    }

    public void setEnrolmenttime(String enrolmenttime) {
        this.enrolmenttime = enrolmenttime == null ? null : enrolmenttime.trim();
    }

    public String getGraduationtime() {
        return graduationtime;
    }

    public void setGraduationtime(String graduationtime) {
        this.graduationtime = graduationtime == null ? null : graduationtime.trim();
    }

    public String getProfessionalcategory() {
        return professionalcategory;
    }

    public void setProfessionalcategory(String professionalcategory) {
        this.professionalcategory = professionalcategory == null ? null : professionalcategory.trim();
    }

    public String getProfessionaldescription() {
        return professionaldescription;
    }

    public void setProfessionaldescription(String professionaldescription) {
        this.professionaldescription = professionaldescription == null ? null : professionaldescription.trim();
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime == null ? null : creationtime.trim();
    }

    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser == null ? null : creationuser.trim();
    }

    public Integer getCol1() {
        return col1;
    }

    public void setCol1(Integer col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2 == null ? null : col2.trim();
    }

    public String getAssociationActivity() {
        return associationActivity;
    }

    public void setAssociationActivity(String associationActivity) {
        this.associationActivity = associationActivity == null ? null : associationActivity.trim();
    }

    public String getSschoolGrade() {
        return sschoolGrade;
    }

    public void setSschoolGrade(String sschoolGrade) {
        this.sschoolGrade = sschoolGrade == null ? null : sschoolGrade.trim();
    }
}
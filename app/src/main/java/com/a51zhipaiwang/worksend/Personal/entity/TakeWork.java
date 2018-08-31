package com.a51zhipaiwang.worksend.Personal.entity;

import java.io.Serializable;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/27
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
public class TakeWork implements Serializable {

    private String col1;
    private String id;
    private String recruitmentPosition;
    private String col12;
    private String creationtime;

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecruitmentPosition() {
        return recruitmentPosition;
    }

    public void setRecruitmentPosition(String recruitmentPosition) {
        this.recruitmentPosition = recruitmentPosition;
    }

    public String getCol12() {
        return col12;
    }

    public void setCol12(String col12) {
        this.col12 = col12;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }
}

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
public class TryPath implements Serializable {

    private String enterpriseName;
    private String id;
    private String trialPostSalary;
    private String trialPostState;
    private String trialPostTime;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrialPostSalary() {
        return trialPostSalary;
    }

    public void setTrialPostSalary(String trialPostSalary) {
        this.trialPostSalary = trialPostSalary;
    }

    public String getTrialPostState() {
        return trialPostState;
    }

    public void setTrialPostState(String trialPostState) {
        this.trialPostState = trialPostState;
    }

    public String getTrialPostTime() {
        return trialPostTime;
    }

    public void setTrialPostTime(String trialPostTime) {
        this.trialPostTime = trialPostTime;
    }
}

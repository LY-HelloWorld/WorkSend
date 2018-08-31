package com.a51zhipaiwang.worksend.Personal.entity;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/28
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
public class CommendWork {

    private String id;
    private String positionId;
    private String positionName;
    private String recruitmentPosition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getRecruitmentPosition() {
        return recruitmentPosition;
    }

    public void setRecruitmentPosition(String recruitmentPosition) {
        this.recruitmentPosition = recruitmentPosition;
    }
}

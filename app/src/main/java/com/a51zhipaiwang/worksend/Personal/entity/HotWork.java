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
public class HotWork implements Serializable{

    private String applyFrequency;

    private String positionId;

    private String id;

    private String positionName;

    private boolean takeOrNot;

    public boolean isTakeOrNot() {
        return takeOrNot;
    }

    public void setTakeOrNot(boolean takeOrNot) {
        this.takeOrNot = takeOrNot;
    }

    public String getApplyFrequency() {
        return applyFrequency;
    }

    public void setApplyFrequency(String applyFrequency) {
        this.applyFrequency = applyFrequency;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}

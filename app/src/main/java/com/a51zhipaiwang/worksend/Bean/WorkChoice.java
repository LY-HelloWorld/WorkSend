package com.a51zhipaiwang.worksend.Bean;

import java.util.ArrayList;

public class WorkChoice {

    private String positionName;

    private int id;

    private ArrayList<WorkChoiceTwoStage> children;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<WorkChoiceTwoStage> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<WorkChoiceTwoStage> children) {
        this.children = children;
    }
}

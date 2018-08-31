package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkChoiceTwoStage implements Serializable{

    private String positionName;

    private int id;

    private ArrayList<WorkChoiceThreeStage> children;

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

    public ArrayList<WorkChoiceThreeStage> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<WorkChoiceThreeStage> children) {
        this.children = children;
    }
}

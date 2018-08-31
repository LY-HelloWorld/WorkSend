package com.a51zhipaiwang.worksend.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import java.io.Serializable;

public class WorkChoiceThreeStage implements Serializable{
    private String positionName;

    private int id;

    private boolean takeOrNot;

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

    public boolean isTakeOrNot() {
        return takeOrNot;
    }

    public void setTakeOrNot(boolean takeOrNot) {
        this.takeOrNot = takeOrNot;
    }
}

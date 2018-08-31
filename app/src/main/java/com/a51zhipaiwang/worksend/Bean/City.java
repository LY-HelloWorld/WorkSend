package com.a51zhipaiwang.worksend.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.a51zhipaiwang.worksend.BR;

import java.io.Serializable;

public class City extends BaseObservable implements Serializable {

    private String name;
    private String log;
    private String lat;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}

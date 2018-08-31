package com.a51zhipaiwang.worksend.Bean;


import java.io.Serializable;
import java.util.ArrayList;

public class Citys implements Serializable{

    private String name;
    private String log;
    private String lat;

    private ArrayList<City> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<City> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<City> children) {
        this.children = children;
    }
}

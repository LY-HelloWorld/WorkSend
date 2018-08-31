package com.a51zhipaiwang.worksend.Bean;


import java.util.ArrayList;

public class Provence {
    private String code;
    private ArrayList<Citys> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Citys> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Citys> children) {
        this.children = children;
    }
}

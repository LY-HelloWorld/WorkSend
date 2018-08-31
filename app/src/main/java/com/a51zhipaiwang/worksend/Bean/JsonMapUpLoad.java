package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonMapUpLoad implements Serializable {

    private HashMap<String, HashMap<String, String>> map = new HashMap();

    public Map getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
    }
}

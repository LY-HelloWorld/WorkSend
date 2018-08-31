package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;

public class TongZhiInfomationData implements Serializable {

    private String time;

    private String infomationContent;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfomationContent() {
        return infomationContent;
    }

    public void setInfomationContent(String infomationContent) {
        this.infomationContent = infomationContent;
    }
}

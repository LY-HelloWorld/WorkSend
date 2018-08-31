package com.a51zhipaiwang.worksend.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SubChoiceListThreetyJianLi implements Serializable {

    private String distributeLeafletsId;

    private Set<String> resumeId;

    public SubChoiceListThreetyJianLi(String distributeLeafletsId) {
        this.distributeLeafletsId = distributeLeafletsId;
        this.resumeId = new HashSet<>();
    }

    public String getDistributeLeafletsId() {
        return distributeLeafletsId;
    }

    public void setDistributeLeafletsId(String distributeLeafletsId) {
        this.distributeLeafletsId = distributeLeafletsId;
    }

    public Set<String> getResumeId() {
        return resumeId;
    }

    public void setResumeId(Set<String> resumeId) {
        this.resumeId = resumeId;
    }
}

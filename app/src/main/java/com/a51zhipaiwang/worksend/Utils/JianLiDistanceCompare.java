package com.a51zhipaiwang.worksend.Utils;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;

import java.util.Comparator;


public class JianLiDistanceCompare implements Comparator<SampleJianLiData> {


    @Override
    public int compare(SampleJianLiData sampleJianLiData, SampleJianLiData t1) {
        return (int) (Float.valueOf(sampleJianLiData.getKilometre()) - Float.valueOf(t1.getKilometre()));
    }

}

package com.a51zhipaiwang.worksend;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.a51zhipaiwang.worksend.Utils.MyLog;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.a51zhipaiwang.worksend", appContext.getPackageName());
    }

    @Test
    public void test(){

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date dateFormat1 = simpleDateFormat.parse("2018-13-03");
            Date dateFormat2 = simpleDateFormat.parse("2018-13-02");
            assertEquals(true, dateFormat1.getTime() > dateFormat2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            MyLog.e("ExampleInstrumentedTest", "test(ExampleInstrumentedTest.java:44)" + e.getMessage());
        }
    }

}

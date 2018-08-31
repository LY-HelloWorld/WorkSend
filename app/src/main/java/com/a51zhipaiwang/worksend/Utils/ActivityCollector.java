package com.a51zhipaiwang.worksend.Utils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗怡 on 2017/2/7.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    /**
     * 将Activity添加到列表中进行管理
     * @param activity
     */
    public static void addActivity(Activity activity){
        MyLog.e("ActivityCollector", "addActivity(ActivityCollector.java:18)" + "add");
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    /**
     * 将Activity从列表中移除
     * @param activity
     */
    public static void removeActivity(Activity activity){
        MyLog.e("ActivityCollector", "removeActivity(ActivityCollector.java:29)" + "remove");
        if (activities.contains(activity)) {
            activities.remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭所有的Activity
     */

    public static void finishAll(){
        MyLog.e("ActivityCollector", "finishAll(ActivityCollector.java:41)" + "finishAll");
        for (Activity activity :activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
            MyLog.e("ActivityCollector", "finishAll(ActivityCollector.java:39)" + activity.getPackageResourcePath());
        }
        activities.clear();
    }
}

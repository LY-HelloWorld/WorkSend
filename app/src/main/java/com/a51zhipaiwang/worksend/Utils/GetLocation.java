package com.a51zhipaiwang.worksend.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.a51zhipaiwang.worksend.Utils.MyLog;

public class GetLocation {

    private static LocationManager locationManager;

    private static LocationManager getSingleton(Context context) {
        if (locationManager == null) {                         //Single Checked
            synchronized (GetLocation.class) {
                if (locationManager == null) {                 //Double Checked
                    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                }
            }
        }
        return locationManager;
    }


    public static void getLocation(Context context) {

        getSingleton(context);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //位置信息变化时触发
                MyLog.e("GetLocation", "onLocationChanged(GetLocation.java:32)" + location.getLatitude());
                MyLog.e("GetLocation", "onLocationChanged(GetLocation.java:33)" + location.getLongitude());
                MyLog.e("GetLocation", "onLocationChanged(GetLocation.java:34)" + location.getAltitude());
                MyLog.e("GetLocation", "onLocationChanged(GetLocation.java:35)" + location.getTime());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        /**
         * 绑定监听
         * 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种，前者是GPS,后者是GPRS以及WIFI定位
         * 参数2，位置信息更新周期.单位是毫秒
         * 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
         * 参数4，监听
         * 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
         */

        //检查是否开启权限！
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "权限不够", Toast.LENGTH_LONG).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }


}

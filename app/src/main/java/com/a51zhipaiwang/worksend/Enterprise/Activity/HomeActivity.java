package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BusinessFragment.BusinessFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment.HomeFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment.IHomeFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.InfomationFragment;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import static com.a51zhipaiwang.worksend.Application.MyApplication.cityInfo;

public class HomeActivity extends BaseActivity {

    private ViewPager view_pager;
    private TabLayout tal_layout;

    //首页 信息 我的 三个Fragment
    private ArrayList<Fragment> fragments;
    private String[] tals = {"首页", "信息", "企业管理"};
    private int[] talImages = {R.drawable.home_tal_image
            , R.drawable.infomation_tal_image
            , R.drawable.business_tal_image};

    public LocationClient mLocationClient = null;

    private boolean closeFlag = false;

    private Thread getInfoThread;

    public static void startHomeActivity(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SDKInitializer.initialize(getApplicationContext());
        //butterknife绑定 必须set Content View后
        //ButterKnife.bind(this);
        init();
        setRegister();
        checkPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        setTal();
        openGetNewInfoMessage();

    }

    /**
     * 初始化
     */
    private void init() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tal_layout = (TabLayout) findViewById(R.id.tal_layout);

        view_pager.setOffscreenPageLimit(3);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new InfomationFragment());
        fragments.add(new BusinessFragment());

        mLocationClient = new LocationClient(getApplicationContext());//声明LocationClient类
        initDingWei();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeFlag = true;
        getInfoThread.interrupt();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finishAll();
    }

    /**
     * 设置监听
     */
    private void setRegister() {
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        setDingWeiRegister();
    }


    /**
     * 初始化定位
     */
    private void initDingWei() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标
        option.setScanSpan(0);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        //option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setIgnoreKillProcess(true);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mLocationClient.setLocOption(option);
    }

    /**
     * 设置定位监听
     */
    private void setDingWeiRegister() {

        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                //如果不为空 则已经获取到当前定位信息
                if (!TextUtils.isEmpty(bdLocation.getCity())) {
                    SharedPreferencesUtil.saveSharedPreference("city", bdLocation.getProvince(), cityInfo, HomeActivity.this);
                    MyApplication.longitude = String.valueOf(bdLocation.getLongitude());
                    MyApplication.latitude = String.valueOf(bdLocation.getLatitude());
                    SharedPreferencesUtil.saveSharedPreference("longitude", MyApplication.longitude, cityInfo, HomeActivity.this);
                    SharedPreferencesUtil.saveSharedPreference("latitude", MyApplication.latitude, cityInfo, HomeActivity.this);
                    setDingwei(bdLocation.getCity());
                    //获取经纬度后 实现经纬度简历搜索
                    ((HomeFragment) fragments.get(0)).setMapEasy(MyApplication.longitude, MyApplication.latitude, "1", "6");
                    ((HomeFragment) fragments.get(0)).getList(IHomeFragment.SETINFO);
                }
                mLocationClient.stop();
            }
        });
    }

    @Override
    public void doSomethingAfterGetPermissions() {
        super.doSomethingAfterGetPermissions();
        //开启定位
        OpenDingWei();
    }

    /**
     * 开始一次定位
     */
    public void OpenDingWei() {
        //初始化配置
        initDingWei();
        //开启定位
        mLocationClient.start();
    }

    private void setDingwei(String city) {
        ((HomeFragment) fragments.get(0)).setDingWeiText(city);
    }


    /**
     * 设置底部tal 及其点击变化效果
     */
    private void setTal() {
        tal_layout.setupWithViewPager(view_pager);
        for (int i = 0; i < tal_layout.getTabCount(); i++) {
            TabLayout.Tab tab = tal_layout.getTabAt(i);
            tab.setTag(i);
            tab.setCustomView(R.layout.tal_layout);
            View tabView = tab.getCustomView();
            TextView tal_text = tabView.findViewById(R.id.tal_text);
            tal_text.setText(tals[i]);
            ImageView tal_image = tabView.findViewById(R.id.tal_image);
            tal_image.setBackgroundResource(talImages[i]);

        }
    }

    private void openGetNewInfoMessage() {
        getInfoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!closeFlag) {
                    MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST, MyApplication.path + "api/enterpriseNew/haveNewMessage.do",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String sInfo) {

                                    try {
                                        JSONObject response = new JSONObject(sInfo);
                                        if ("success".equals(response.getString("code"))) {
                                            String info = response.getString("info");
                                            JSONObject jsonObject = new JSONObject(info);
                                            String message = jsonObject.getString("success");
                                            if (message.equals("010")) {
                                                Intent intent = new Intent();
                                                intent.setAction(MyApplication.NEWINFO);
                                                HomeActivity.this.sendBroadcast(intent);
                                                setRedImageVisible(true);
                                            } else {
                                                setRedImageVisible(false);
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            MyLog.e("HomeActivity", "onErrorResponse(HomeActivity.java:231)" + error.getMessage());
                        }
                    }));
                    try {
                        //定时请求通知等消息
                        Thread.sleep(1 * 60 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        getInfoThread.start();
    }

    /**
     * 设置 红点是否可见
     */
    public void setRedImageVisible(boolean visible) {
        if (tal_layout != null && tal_layout.getTabAt(1) != null) {
            if (visible) {
                tal_layout.getTabAt(1).getCustomView().findViewById(R.id.messageRedImage).setVisibility(View.VISIBLE);
            } else {
                tal_layout.getTabAt(1).getCustomView().findViewById(R.id.messageRedImage).setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MyApplication.WorkClassificationFlag:
                if (resultCode == RESULT_OK) {
                    ((HomeFragment) fragments.get(0)).setWorkText(data.getExtras().getString("content"));
                } else {
                    ((HomeFragment) fragments.get(0)).setWorkText("点击选择职位");
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

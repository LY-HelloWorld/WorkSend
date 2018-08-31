package com.a51zhipaiwang.worksend.Personal.homeactivity;

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
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.homefragment.PersonalHomeFragment;
import com.a51zhipaiwang.worksend.Personal.infomationfragment.InfomationFragment;
import com.a51zhipaiwang.worksend.Personal.minefragment.MineFragment;
import com.a51zhipaiwang.worksend.Personal.referfragment.ReferFragment;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.util.ArrayList;
public class PersonalHomeActivity extends BaseActivity {

    private ArrayList<Fragment> baseFragments;
    private String[] tals = {"首页", "资讯", "消息", "我的"};
    private int[] talImages = {R.drawable.home_tal_image,
            R.drawable.business_tal_image,
            R.drawable.infomation_tal_image,
            R.drawable.personal_mine_tal_image
    };

    private ViewPager viewpager_personal;
    private TabLayout tablayout_personal;

    public LocationClient mLocationClient = null;

    public static void startPersonalHomeAcitivty(Context context){
        context.startActivity(new Intent(context, PersonalHomeActivity.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_home);
        init();
        initViewPagerAdapter();
        initTabLayout();
        setRegister();
        checkPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        //initDingWei();
    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        //MyLog.e("PersonalHomeActivity", "onBackPressed(PersonalHomeActivity.java:69)" + "onBackPressed");
        //this.finish();
        //ActivityCollector.finishAll();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void doSomethingAfterGetPermissions() {
        super.doSomethingAfterGetPermissions();
        //开启定位
        OpenDingWei();
    }

    public void setViewPagerPosition(int position){
        viewpager_personal.setCurrentItem(position);
    }

    private void init(){
        baseFragments = new ArrayList<>();
        baseFragments.add(new PersonalHomeFragment());
        baseFragments.add(ReferFragment.newInstanceReferFragment(MyApplication.tokenPersonal));
        baseFragments.add(new InfomationFragment());
        baseFragments.add(new MineFragment());
        viewpager_personal = (ViewPager) findViewById(R.id.viewpager_personal);
        tablayout_personal = (TabLayout) findViewById(R.id.tablayout_personal);
        mLocationClient = new LocationClient(getApplicationContext());//声明LocationClient类
    }

    private void initViewPagerAdapter(){
        viewpager_personal.setOffscreenPageLimit(4);
        viewpager_personal.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return baseFragments.get(i);
            }

            @Override
            public int getCount() {
                return baseFragments.size();
            }
        });
    }

    private void initTabLayout(){
        tablayout_personal.setupWithViewPager(viewpager_personal);
        for (int i = 0; i < tablayout_personal.getTabCount(); i++) {
            TabLayout.Tab tabView = tablayout_personal.getTabAt(i);
            tabView.setCustomView(R.layout.tal_layout);
            View view = tabView.getCustomView();
            TextView tal_text = view.findViewById(R.id.tal_text);
            tal_text.setText(tals[i]);
            ImageView tal_image = view.findViewById(R.id.tal_image);
            tal_image.setBackgroundResource(talImages[i]);
        }
    }

    private void setRegister(){
        setDingWeiRegister();
    }

    /**
     * 开始一次定位
     */
    public void OpenDingWei(){
        //初始化配置
        initDingWei();
        //开启定位
        mLocationClient.start();
    }

    /**
     * 初始化定位
     */
    private void initDingWei(){
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
    private void setDingWeiRegister(){

        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                //如果不为空 则已经获取到当前定位信息
                if (!TextUtils.isEmpty(bdLocation.getCity())){
                    SharedPreferencesUtil.saveSharedPreference("city", bdLocation.getProvince(), MyApplication.cityInfo,PersonalHomeActivity.this);
                    MyApplication.longitude = String.valueOf(bdLocation.getLongitude());
                    MyApplication.latitude = String.valueOf(bdLocation.getLatitude());
                    SharedPreferencesUtil.saveSharedPreference("longitude", MyApplication.longitude, MyApplication.cityInfo,PersonalHomeActivity.this);
                    SharedPreferencesUtil.saveSharedPreference("latitude", MyApplication.latitude, MyApplication.cityInfo,PersonalHomeActivity.this);
                    setDingwei(bdLocation.getProvince());
                }
                mLocationClient.stop();
            }
        });
    }


    private void setDingwei(String city){
        ((PersonalHomeFragment)baseFragments.get(0)).setLocation(city);
    }

}

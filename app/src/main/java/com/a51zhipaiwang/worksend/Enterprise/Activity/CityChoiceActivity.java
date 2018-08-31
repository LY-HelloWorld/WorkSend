package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Bean.Provence;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.CityChoiceRecyclerAdapater;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GetJson;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.View.QuickChoiceCityView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.google.gson.reflect.TypeToken;
import com.solidfire.gson.Gson;

import java.util.ArrayList;



public class CityChoiceActivity extends BaseActivity {
    /*
        @BindView(R.id.cityRecyclerView)*/
    RecyclerView cityRecyclerView;

    //列表适配器
    private CityChoiceRecyclerAdapater cityChoiceRecyclerAdapater;
    private QuickChoiceCityView quickChoiceCityView;

    public LocationClient mLocationClient = null;

    private String city;
    private ImageView return_image;

    public static void startCityChoiceActivity(Activity context, int requestCode){
        context.startActivityForResult(new Intent(context, CityChoiceActivity.class), requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choice);
        SDKInitializer.initialize(getApplicationContext());
        //butterknife绑定 必须set Content View后
        //ButterKnife.bind(this);
        init();
        setRegister();
        checkPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        setData();
    }

    /**
     * 初始化变量
     */
    private void init() {
        cityRecyclerView = findViewById(R.id.cityRecyclerView);
        quickChoiceCityView = findViewById(R.id.quickChoiceCityView);
        return_image = (ImageView) findViewById(R.id.return_image);

        /*//判断城市定位是否完成
        if (TextUtils.isEmpty(SharedPreferencesUtil
                .readSharedPreference("city", MyApplication.cityInfo, "", CityChoiceActivity.this))){
            //定位相关初始化并且开启
            OpenDingWei();
        }*/
    }

    private void initDingWei() {
        mLocationClient = new LocationClient(getApplicationContext());//声明LocationClient类
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
     * 设置注册监听等操作
     */
    private void setRegister() {
        setRecyclerViewRegister();
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityChoiceActivity.this.finish();
            }
        });
    }

    /**
     * 设置recyckerview相关注册
     */
    private void setRecyclerViewRegister() {
        //初始化布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置排列方式 垂直
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //为RecyclerView设置布局方式
        cityRecyclerView.setLayoutManager(linearLayoutManager);

        //设置索引监听器
        quickChoiceCityView.setOnIndexChangeListener(new QuickChoiceCityView.OnIndexChangeListener() {
            @Override
            public void onIndexChange(String words) {
                //如果选择当 则直接滚至顶部
                if (words.equals("当")) {
                    cityRecyclerView.getLayoutManager().scrollToPosition(0);
                    return;
                }
                CityChoiceRecyclerAdapater cityChoiceRecyclerAdapater = (CityChoiceRecyclerAdapater) cityRecyclerView.getAdapter();
                //获取到城市信息
                ArrayList<Provence> cityData = cityChoiceRecyclerAdapater.getCitys();
                if (cityData != null && cityData.size() > 0) {
                    int count = 0;
                    //循环匹配字母 如果相同则滚至当前位置 退出循环
                    for (int i = 0; i < cityData.size(); i++) {
                        Provence datasBean = cityData.get(i);
                        //如果找到匹配的 滚至当前位置 退出循环
                        if (datasBean.getCode().equals(words)) {
                            LinearLayoutManager llm = (LinearLayoutManager) cityRecyclerView
                                    .getLayoutManager();
                            llm.scrollToPositionWithOffset(count + 1, 0);
                            return;
                        }
                        //没有匹配 位置+1，
                        count += datasBean.getChildren().size() + 1;
                    }
                }
            }
        });
    }

    /**
     * 设置定位监听
     */
    private void setDingWeiRegister() {

        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (!TextUtils.isEmpty(bdLocation.getCity())) {
                    setCurrentLocation(bdLocation.getCity());
                    MyApplication.latitude = String.valueOf(bdLocation.getLatitude());
                    MyApplication.longitude = String.valueOf(bdLocation.getLongitude());
                }
                mLocationClient.stop();
            }
        });
    }

    /**
     * 开始一次定位
     */
    public void OpenDingWei() {
        //初始化配置
        initDingWei();
        //监听定位
        setDingWeiRegister();
        //开启定位
        mLocationClient.start();
    }

    @Override
    public void doSomethingAfterGetPermissions() {
        super.doSomethingAfterGetPermissions();
        OpenDingWei();
    }

    /**
     * 用于RecycleView 向Activity 反馈当前选择的城市
     * 并且跳转到区域选择信息
     * 跳转城市选择信息
     *
     * @param cities
     */
    public void feedBackCity(ArrayList<City> cities) {
        RegionActivity.StartRegionAcitity(this, cities);
    }

    /**
     * 点击当前位置
     * @param city
     */
    public void choiceCurrentLocation(City city){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", city);
        intent.putExtras(bundle);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }


    /**
     * 设置当前的位置信息
     *
     * @param city
     */
    private void setCurrentLocation(String city) {
        ((CityChoiceRecyclerAdapater) cityRecyclerView.getAdapter()).getHeadViewHolder().current_location_btn.setText(city);
    }


    private void setData() {
        //模拟Gson城市数据
        Gson gson = new Gson();
        ArrayList<Provence> cityData = gson.fromJson(GetJson.GetCityJson(CityChoiceActivity.this, "city.json"), new TypeToken<ArrayList<Provence>>() {
        }.getType());
        //将数据配置到列表适配器中并且将适配器添加到列表中
        cityChoiceRecyclerAdapater = new CityChoiceRecyclerAdapater(this, cityData);
        cityRecyclerView.setAdapter(cityChoiceRecyclerAdapater);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case MyApplication.ChoiceRegionFlag:
                if (resultCode == RESULT_OK) {
                    this.setResult(RESULT_OK, data);
                    this.finish();
                }
                break;
        }
    }
}

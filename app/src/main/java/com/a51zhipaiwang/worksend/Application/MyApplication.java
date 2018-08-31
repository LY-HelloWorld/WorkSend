package com.a51zhipaiwang.worksend.Application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    public static final String APP_ID = "wxaae442a3f8384963";

    public static final String AppSecret = "070f3113f93b3d23f2273f68f27185f8";

    //选择工作情况的请求码 用于requestCode
    public static final int ChoiceContionFlag = 1;
    //选择城市情况的请求码
    public static final int ChoiceCityFlag = 2;
    //选择区域请求码
    public static final int ChoiceRegionFlag = 3;
    //定位请求码
    public static final int DingWeiFlag = 4;
    //工作选择请求码
    public static final int WorkClassificationFlag = 5;
    //添加工作
    public static final int AddWork = 6;
    //上传视频 图片
    public static final int UpLoadLog = 7;
    public static final int UpLoadVideo = 8;
    public static final int UpLoadFengMian = 9;
    //搜索工作请求码
    public static final int SearchWork = 10;
    //30分简历反馈
    public static final int ChoiceJianLi = 11;
    //企业信息修改请求码
    public static final int BusinessInfoChangeFlag = 12;
    //个人用户信息编辑
    public static final int UserInfoEditFlag = 13;
    //教育经历
    public static final int EduEdit = 14;
    //工作经历
    public static final int WorkEdit = 15;
    //项目经验
    public static final int ProgressEdit = 16;
    //个人信息
    public static final int WorkIntentionEdit = 17;
    //上传
    public static final int UpLoad = 18;
    //更多职位
    public static final int MoreWork = 19;
    //修改用户头像广播标识
    public static final String CHANGE_USER_IMAGE_FLAG = "CHANGE_USER_IMAGE_FLAG";
    //企业端搜索工作
    public static final int WorkSearch = 20;
    //修改公司信息
    public static final String CHANGE_BUSINESS_FLAG = "CHANGE_BUSINESS_FLAG";
    public static final String RECHARGE_SUCCESS = "RECHARGE_SUCCESS";
    public static final String VIP_SUCCESS = "VIP_SUCCESS";
    public static final String IDENTIFY_UPLOAD_SUCCESS = "IDENTIFY_UPLOAD_SUCCESS";
    public static final String NEWINFO = "NEWINFO";
    public static final String LOG_OUT = "LOG_OUT";
    public static final int REFERFRAGMENT_FLAG = 21;

    //存储城市的标识
    public static final String cityInfo = "cityInfo";
    //存储用户信息标识
    public static final String userInfo = "userInfo";


    //public static final String path = "http://47.104.30.68:8080/ws_api/";
    //public static final String path = "http://192.168.1.124:8080/ws_api/";
    public static final String path = "http://192.168.31.179:8080/ws_api/";
    //public static final String path = "http://192.168.31.105:8080/ws_api/";
    //public static final String path = "http://192.168.31.105:8080/ws_api/";

    //public static final String h5Path = "http://192.168.31.14:8081/#";
    //public static final String h5Path = "http://192.168.1.140:8082/#";
    //public static final String h5Path = "http://47.104.30.68:8081/#";
    public static final String h5Path = "http://47.104.30.68:8081/dist/#";

    //public static final String upLoadPath = "http://192.168.31.84:8081/ws_api/";
    //public static final String upLoadPath = "http://192.168.31.179:8080/ws_api/video/";
    public static final String upLoadPath = "http://47.104.30.68:8080/ws_api/";

    public static String tokenPersonal;
    public static String tokenEnterprise;
    //定位经度 默认为天安门经纬度
    public static String longitude = "116.403694";
    //定位维度
    public static String latitude = "39.914492";

    //网络请求
    public static RequestQueue requestQueue;

    public static Context context;

    public static String DateMatch = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))";


    @Override
    public void onCreate() {
        super.onCreate();

        // 加载系统默认设置，字体不随用户设置变化
        // 防止用户修改文字大小。导致APP出现布局问题
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        context = this;
        requestQueue = Volley.newRequestQueue(MyApplication.context);
        tokenPersonal = SharedPreferencesUtil.readSharedPreference("tokenPersonal", MyApplication.userInfo, "", this);
        tokenEnterprise = SharedPreferencesUtil.readSharedPreference("tokenEnterprise", MyApplication.userInfo, "", this);
    }
}

package com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.ChoiceConditionActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.JianLiActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.PaiDanActivity.PaiDanActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.VideoJianLIActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BaseFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment.IHomeFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment.JianLiListFragment;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseHomeFragment.HomeFragmentPresenterImp;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseHomeFragment.IHomeFragmentPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import static android.app.Activity.RESULT_OK;


public class HomeFragment extends BaseFragment implements IHomeFragment {

    //轮播图
    private XBanner homeBanner;

    //轮播图路径
    private ArrayList<Integer> banner_path = new ArrayList<Integer>(Arrays.asList(R.mipmap.home_banner_one, R.mipmap.home_banner_two, R.mipmap.home_banner_three));
    //选择职位
    private LinearLayout choiceConditionLayout;
    //附近人才
    private LinearLayout recentLayout;
    //首页点击监听
    private MyHomeFragmentOnClickListener myHomeFragmentOnClickListener;
    //标题
    private TextView tilte_text;
    //定位信息text
    private TextView dingWeiText;
    //搜索Image
    private ImageView searchImage;
    //派单布局
    private LinearLayout paiDanLayout;
    //视频简历布局
    private LinearLayout videoJianLiLayout;

    //简历列表Fragment
    private JianLiListFragment jianLiListFragment;
    //请求参数map
    private HashMap<String, String> jianLiHashMap;
    //当前页
    private int page = 1;

    private IHomeFragmentPresenter homeFragmentPresenter;
    private LinearLayout yingJieShengLayout;
    private LinearLayout qiYeXiuLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, null);
        init(view);
        setRegister();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDingWeiText(city);
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void init(View view) {
        jianLiHashMap = new HashMap();
        setMapEasy(MyApplication.longitude, MyApplication.latitude, "1", "6");
        //presenter
        homeFragmentPresenter = new HomeFragmentPresenterImp(this);
        myHomeFragmentOnClickListener = new MyHomeFragmentOnClickListener();
        jianLiListFragment = JianLiListFragment.GetJianLiListFragmentInstance(false,
                3,
                jianLiHashMap,
                JianLiListFragment.HAVESCROLLVIEW,
                MyApplication.path + "api/curriculumVitae/Initiali.do");

        //初始化title
        tilte_text = (TextView) view.findViewById(R.id.tilte_text);
        dingWeiText = (TextView) view.findViewById(R.id.dingWeiText);
        searchImage = (ImageView) view.findViewById(R.id.searchImage);
        dingWeiText.setVisibility(View.VISIBLE);
        searchImage.setVisibility(View.VISIBLE);

        //初始化banner
        homeBanner = (XBanner) view.findViewById(R.id.homeBanner);
        homeBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                new GlideUtil().GlideImage(banner_path.get(position), getActivity(), (ImageView) view);
            }
        });
        homeBanner.setData(banner_path, null);

        //初始化简历列表Fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.jianLiFrame, jianLiListFragment);
        fragmentTransaction.commit();

        //选择条件筛选
        choiceConditionLayout = (LinearLayout) view.findViewById(R.id.choiceConditionLayout);
        //最近人才
        recentLayout = (LinearLayout) view.findViewById(R.id.recentLayout);
        //派单
        paiDanLayout = (LinearLayout) view.findViewById(R.id.paiDanLayout);
        //视频简历
        videoJianLiLayout = (LinearLayout) view.findViewById(R.id.videoJianLiLayout);
        //应届生
        yingJieShengLayout = (LinearLayout) view.findViewById(R.id.yingJieShengLayout);
        //企业秀
        qiYeXiuLayout = (LinearLayout) view.findViewById(R.id.qiYeXiuLayout);

    }


    /**
     * 设置监听
     */
    private void setRegister() {

        //title
        tilte_text.setOnClickListener(myHomeFragmentOnClickListener);
        //定位
        dingWeiText.setOnClickListener(myHomeFragmentOnClickListener);
        //搜索
        searchImage.setOnClickListener(myHomeFragmentOnClickListener);
        //选择条件筛选监听
        choiceConditionLayout.setOnClickListener(myHomeFragmentOnClickListener);
        //最近人才监听
        recentLayout.setOnClickListener(myHomeFragmentOnClickListener);
        //派单监听
        paiDanLayout.setOnClickListener(myHomeFragmentOnClickListener);
        //视频简历监听
        videoJianLiLayout.setOnClickListener(myHomeFragmentOnClickListener);
        //应届生
        yingJieShengLayout.setOnClickListener(myHomeFragmentOnClickListener);
        //企业秀
        qiYeXiuLayout.setOnClickListener(myHomeFragmentOnClickListener);

    }

    /**
     * 设置定位信息
     *
     * @param dingWeiText
     */
    public void setDingWeiText(String dingWeiText) {
        this.dingWeiText.setText(dingWeiText);
    }

    /**
     * 设置title工作信息
     *
     * @param workText
     */
    public void setWorkText(String workText) {
        this.tilte_text.setText(workText);
    }

    /**
     * 展示加载框
     */
    @Override
    public void showLoadingDialog() {
        ToastUtil.showToastTwo("加载中");
    }

    /**
     * 关闭加载框
     */
    @Override
    public void closeLoadingDialog() {
        ToastUtil.showToastTwo("加载完成");
    }

    /**
     * 设置简历列表
     *
     * @param jianLiList
     */
    @Override
    public void setJianLiList(ArrayList jianLiList, int type) {
        MyLog.e("HomeFragmen123t", "setJianLiList(HomeFragment.java:222)" + jianLiList.size());
        if (type == SETINFO){
            MyLog.e("HomeFragmen123t", "setJianLiList(HomeFragment.java:223)" + jianLiList.size());
            jianLiListFragment.setJianLiList(jianLiList);
        }else {
            jianLiListFragment.addJianLiList(jianLiList);
        }
    }

    /**
     * 设置全部请求数据
     *
     * @param longitude      经度 not null
     * @param latitude       维度 not null
     * @param page           当前页 not null
     * @param rows           每页多少数据 not null
     * @param expectedcareer 期望职业
     * @param expectCity     期望城市
     * @param sex            性别
     * @param positionstatus 职位状态
     * @param workingLife    工作年限
     * @param education      教育经历
     */
    public void setMap(String longitude, String latitude
            , String page, String rows
            , String expectedcareer, String expectCity
            , String sex, String positionstatus
            , String workingLife, String education) {
        this.page = Integer.valueOf(page);
        jianLiHashMap.put("longitude", longitude);
        jianLiHashMap.put("latitude", latitude);
        jianLiHashMap.put("page", page);
        jianLiHashMap.put("rows", rows);
        jianLiHashMap.put("expectedcareer", expectedcareer);
        jianLiHashMap.put("expectCity", expectCity);
        jianLiHashMap.put("sex", sex);
        jianLiHashMap.put("positionstatus", positionstatus);
        jianLiHashMap.put("workingLife", workingLife);
        jianLiHashMap.put("education", education);
    }

    /**
     * 设置请求map 简单版本
     * @param longitude      经度 not null
     * @param latitude       维度 not null
     * @param page           当前页 not null
     * @param rows           每页多少数据 not null
     */
    public void setMapEasy(String longitude, String latitude
            , String page, String rows){
        setMap(longitude, latitude, String.valueOf(page)
                , rows, "", "", "", "", "", "");
    }

    public void getList(int type){
        //开启请求简历
        //homeFragmentPresenter.getList(jianLiHashMap, type);
        jianLiListFragment.getListWithOutPath(type);
    }

    /**
     * 首页fragment 点击监听事件
     */
    class MyHomeFragmentOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                //跳转条件筛选监听
                case R.id.choiceConditionLayout:
                    intent = new Intent(getActivity(), ChoiceConditionActivity.class);
                    getActivity().startActivityFromFragment(HomeFragment.this, intent, MyApplication.ChoiceContionFlag);
                    break;
                case R.id.recentLayout:
                    setMapEasy(SharedPreferencesUtil.readSharedPreference("longitude", MyApplication.cityInfo, MyApplication.longitude, getActivity())
                            , SharedPreferencesUtil.readSharedPreference("latitude", MyApplication.cityInfo, MyApplication.latitude, getActivity())
                            , "1", "6");
                    JianLiActivity.StartRecentActivity(getActivity(), jianLiHashMap, "附近人才", MyApplication.path + "api/curriculumVitae/nearby.do");
                    break;
                case R.id.tilte_text:
                case R.id.searchImage:
                    WorkCassificationActivity.startWorkCassificationActivityFromFragment(HomeFragment.this, MyApplication.WorkClassificationFlag, WorkCassificationActivity.ENTERPRISE);
                    //WorkCassificationActivity.startWorkCassificationActivity((AppCompatActivity) getActivity(), MyApplication.WorkClassificationFlag, WorkCassificationActivity.ENTERPRISE);
                    /*intent = new Intent(getActivity(), WorkCassificationActivity.class);
                    getActivity().startActivityFromFragment(HomeFragment.this, intent, MyApplication.WorkClassificationFlag);*/
                    break;
                case R.id.dingWeiText:
                    intent = new Intent(getActivity(), CityChoiceActivity.class);
                    getActivity().startActivityFromFragment(HomeFragment.this, intent, MyApplication.DingWeiFlag);
                    break;
                case R.id.paiDanLayout:
                    intent = new Intent(getActivity(), PaiDanActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.videoJianLiLayout:
                    VideoJianLIActivity.StartVideoJianLiActivity(getActivity(), MyApplication.h5Path + "/peopleVideo", "视频简历", VideoJianLIActivity.VIDEO_PERSONAL);
                    break;
                case R.id.qiYeXiuLayout:
                    VideoJianLIActivity.StartVideoJianLiActivity(getActivity(), MyApplication.h5Path + "/companyVideo", "企业秀", VideoJianLIActivity.VIDEO_BUDINESS);
                    break;
                case R.id.yingJieShengLayout:
                    setMap(SharedPreferencesUtil.readSharedPreference("longitude", MyApplication.cityInfo, MyApplication.longitude, getActivity())
                            , SharedPreferencesUtil.readSharedPreference("latitude", MyApplication.cityInfo, MyApplication.latitude, getActivity())
                            , "1", "6", "", "", "", "", "010", "");
                    JianLiActivity.StartRecentActivity(getActivity(), jianLiHashMap, "应届毕业生", MyApplication.path + "api/curriculumVitae/graduatingra.do");
                    break;
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //控制工作条件选择反馈
            case MyApplication.ChoiceContionFlag:
                if (resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    WorkBean workBean = (WorkBean) bundle.getSerializable("work");
                    City city = (City) bundle.getSerializable("city");
                    page = 1;
                    setMap(city.getLog(), city.getLat(), String.valueOf(page), "6", "", workBean.getCity(), workBean.getSex(), workBean.getWorkNature(), workBean.getWorkExperience(), workBean.getEducation());
                    getList(SETINFO);
                    //setMap();
                }else {
                    MyLog.e("HomeFragment", "onActivityResult(HomeFragment.java:94)" + "失败");
                }
                break;
            case MyApplication.WorkClassificationFlag:
                if (resultCode == RESULT_OK){
                    Bundle workBundle = data.getExtras();
                    WorkChoiceThreeStage workChoiceThreeStage = (WorkChoiceThreeStage) workBundle.getSerializable("workChoiceThreeStage");
                    tilte_text.setText(workChoiceThreeStage.getPositionName());
                    page  = 1;
                    setMap(SharedPreferencesUtil.readSharedPreference("longitude", MyApplication.cityInfo, MyApplication.longitude, getActivity())
                            , SharedPreferencesUtil.readSharedPreference("latitude", MyApplication.cityInfo, MyApplication.latitude, getActivity())
                            , String.valueOf(page), "6", String.valueOf(workChoiceThreeStage.getId()), "", "", "", "", "");
                    getList(SETINFO);
                }
                break;
            case MyApplication.DingWeiFlag:
                if (resultCode == RESULT_OK){
                    City city = (City)data.getExtras().getSerializable("region");
                    dingWeiText.setText(city.getName());
                    MyApplication.longitude = String.valueOf(city.getLog());
                    MyApplication.latitude = String.valueOf(city.getLat());
                    SharedPreferencesUtil.saveSharedPreference("city", city.getName(), MyApplication.cityInfo,getActivity());
                    SharedPreferencesUtil.saveSharedPreference("longitude", city.getLog(), MyApplication.cityInfo,getActivity());
                    SharedPreferencesUtil.saveSharedPreference("latitude", city.getLat(), MyApplication.cityInfo,getActivity());
                    setMap(city.getLog(), city.getLat(), String.valueOf(page), "6"
                            , "", "", "", "", "", "");
                    getList(SETINFO);

                }else {
                    dingWeiText.setText(city + "");
                }
                break;
        }
    }
}

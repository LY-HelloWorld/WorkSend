package com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.JianLiAdapter;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.BaseFragment;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment.IJianLiListFragment;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseJianLiListFragment.JianLiListFragmentPresenterImpl;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.JianLiDistanceCompare;
import com.a51zhipaiwang.worksend.Utils.ListViewMeasureHeightUtil;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class JianLiListFragment extends BaseFragment implements IJianLiListFragment {


    public static final int ADDINFO = 1;
    public static final int SETINFO = 2;

    private ListView jianLiList;

    private HashMap paramMap;

    private JianLiListFragmentPresenterImpl liListFragmentPresenter;

    public static final int HAVESCROLLVIEW = 1;

    public static final int NOTHAVESCROLLVIEW = 2;

    private int type;
    private TextView loadMore;

    private ArrayList<SampleJianLiData> sampleJianLiDatas;

    private String path;

    /**
     * 启动简历列表fragment
     * @param showDelete 是否显示删除按钮
     * @param showInfoNum 显示基本信息数量
     */
    public static JianLiListFragment GetJianLiListFragmentInstance(boolean showDelete, int showInfoNum, HashMap paramMap, int type, String path){
        JianLiListFragment jianLiListFragment = new JianLiListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("showDelete", showDelete);
        bundle.putInt("showInfoNum", showInfoNum);
        bundle.putInt("type", type);
        bundle.putSerializable("paramMap", paramMap);
        bundle.putString("path", path);
        jianLiListFragment.setArguments(bundle);
        return jianLiListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jian_li, null);
        init(view);
        setRegister();
        return view;
    }

    /**
     * 初始化
     * @param view
     */
    private void init(View view){
        liListFragmentPresenter = new JianLiListFragmentPresenterImpl(this);
        path = getArguments().getString("path");
        sampleJianLiDatas = new ArrayList<>();
        if (getArguments() != null && getArguments().getSerializable("paramMap") != null){
            paramMap = (HashMap) getArguments().getSerializable("paramMap");
            type =  getArguments().getInt("type");
        }else {
            paramMap = new HashMap();
        }
        jianLiList = (ListView) view.findViewById(R.id.jianLiList);
        loadMore = (TextView) view.findViewById(R.id.loadMore);
        getList(path, SETINFO);

    }

    /**
     * 设置监听
     */
    private void setRegister(){
        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paramMap != null){
                    String page = (String) paramMap.get("page");
                    page = String.valueOf(Integer.valueOf(page) + 1);
                    paramMap.put("page", page);
                }
                liListFragmentPresenter.addList(paramMap, path, ADDINFO);
            }
        });
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
        //this.page = Integer.valueOf(page);
        paramMap.put("longitude", longitude);
        paramMap.put("latitude", latitude);
        paramMap.put("page", page);
        paramMap.put("rows", rows);
        paramMap.put("expectedcareer", expectedcareer);
        paramMap.put("expectCity", expectCity);
        paramMap.put("sex", sex);
        paramMap.put("positionstatus", positionstatus);
        paramMap.put("workingLife", workingLife);
        paramMap.put("education", education);
    }

    public void getListWithOutPath(int type){
        getList(path, type);
    }

    private void getList(String path, int type){
        //开启请求简历
        liListFragmentPresenter.getList(paramMap, path, type);
    }

    /**
     *
     * @param jianLiListdata
     */
    @Override
    public void setJianLiList(ArrayList<SampleJianLiData> jianLiListdata) {
        if (jianLiListdata == null || jianLiListdata.size() == 0){
            ToastUtil.showToastTwo("暂时没有更多的简历");
        }
        if (sampleJianLiDatas != null){
            sampleJianLiDatas.clear();
            sampleJianLiDatas.addAll(jianLiListdata);
        }
        Bundle bundle = getArguments();
        Collections.sort(sampleJianLiDatas, new JianLiDistanceCompare());
        if (jianLiList.getAdapter() == null){
            jianLiList.setAdapter(new JianLiAdapter(true
                , getActivity()
                , bundle.getBoolean("showDelete", false)
                , bundle.getInt("showInfoNum", 3)
                , sampleJianLiDatas));
        }else {
            ((JianLiAdapter)jianLiList.getAdapter()).notifyDataSetChanged();
        }
        for (int i = 0; i < jianLiListdata.size(); i++) {
            MyLog.e("JianLiListFragment", "setJianLiList(JianLiListFragment.java:167)" + sampleJianLiDatas.get(i).getKilometre());
        }
        if (type == HAVESCROLLVIEW){
            ListViewMeasureHeightUtil.meaSureHeight(jianLiList);
        }
    }

    @Override
    public void addJianLiList(ArrayList<SampleJianLiData> jianLiListData) {
        if (jianLiListData == null || jianLiListData.size() == 0){
            ToastUtil.showToastTwo("暂时没有更多的简历");
        }
        if (sampleJianLiDatas == null){
            sampleJianLiDatas = new ArrayList<>();
        }else {
            sampleJianLiDatas.addAll(jianLiListData);
        }
        ((JianLiAdapter)jianLiList.getAdapter()).notifyDataSetChanged();
        if (type == HAVESCROLLVIEW){
            ListViewMeasureHeightUtil.meaSureHeight(jianLiList);
        }
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
}

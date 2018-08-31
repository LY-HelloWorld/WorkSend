package com.a51zhipaiwang.worksend.Personal.homefragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.Personal.adapter.HomeInfoListAdapter;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.entity.Information;
import com.a51zhipaiwang.worksend.Personal.findcompanyactivity.FindCompanyActivity;
import com.a51zhipaiwang.worksend.Personal.findworkactivity.FindWorkActivity;
import com.a51zhipaiwang.worksend.Personal.homeactivity.PersonalHomeActivity;
import com.a51zhipaiwang.worksend.Personal.homefragment.contract.IHomeFragmentContract;
import com.a51zhipaiwang.worksend.Personal.homefragment.presenter.IHomeFragmentPresenter;
import com.a51zhipaiwang.worksend.Personal.hotwork.HotWorkActivity;
import com.a51zhipaiwang.worksend.Personal.informationwebviewactivity.InformationWebViewActivity;
import com.a51zhipaiwang.worksend.Personal.intelligentMatchActivity.IntelligentMatchActivity;
import com.a51zhipaiwang.worksend.Personal.resumeeditactivity.ResumeEditActivity;
import com.a51zhipaiwang.worksend.Personal.resumesuccess.ResumeSuccessActivity;
import com.a51zhipaiwang.worksend.Personal.searchactivity.SearchActivity;
import com.a51zhipaiwang.worksend.Personal.worktypeactivity.WorkTypeActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ListViewMeasureHeightUtil;
import com.a51zhipaiwang.worksend.Utils.SharedPreferencesUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.databinding.FragmentPersonalHomeBinding;

import java.util.ArrayList;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class PersonalHomeFragment extends BaseFragment implements IHomeFragmentContract.View{

    private PersonalHomeFragmentClicklistener personalHomeFragmentClicklistener;
    private FragmentPersonalHomeBinding fragmentPersonalHomeBinding;
    private IHomeFragmentContract.Presenter presenter;

    private LinearLayout lv_find_work;
    private LinearLayout lv_choice_location;

    private City city;
    private LinearLayout lv_search;
    private ImageView img_hot_work;
    private ImageView img_match;
    private ListView lvi_personal_infomation;
    private LinearLayout lv_common_sence;
    private Button btn_submit_resume;
    private LinearLayout lv_all_time;
    private LinearLayout lv_part_time;
    private LinearLayout lv_find_company;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPersonalHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_home, container, false);
        View view = fragmentPersonalHomeBinding.getRoot();
        init(view);
        setRegister();
        HashMap hashMap = new HashMap();
        hashMap.put("page", 1);
        presenter.getInfoList(hashMap);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case MyApplication.DingWeiFlag:
                if (resultCode == RESULT_OK){
                    City cityRegion = (City)data.getExtras().getSerializable("region");
                    city.setLat(cityRegion.getLat());
                    city.setLog(cityRegion.getLog());
                    city.setName(cityRegion.getName());
                    SharedPreferencesUtil.saveSharedPreference("city", city.getName(), MyApplication.cityInfo,getActivity());
                    SharedPreferencesUtil.saveSharedPreference("longitude", city.getLog(), MyApplication.cityInfo,getActivity());
                    SharedPreferencesUtil.saveSharedPreference("latitude", city.getLat(), MyApplication.cityInfo,getActivity());
                   // ToastUtil.showToastTwo(city.getName());
                }else {
                }
                break;
        }
    }

    @Override
    public void setInfoList(final ArrayList arrayList) {
        lvi_personal_infomation.setAdapter(new HomeInfoListAdapter(arrayList, getActivity()));
        ListViewMeasureHeightUtil.meaSureHeight(lvi_personal_infomation);
        lvi_personal_infomation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ToastUtil.showToastTwo(i + "");
                InformationWebViewActivity.startInformationWebViewActivity(getActivity(), ((Information)arrayList.get(i)).getId());
            }
        });
    }

    @Override
    public void returnSubResume(boolean bReturnSubResume) {
        if (bReturnSubResume){
            ResumeSuccessActivity.startResumeSuccessActivity(getActivity());
        }else {
            ResumeEditActivity.startResumeEditActivity(getActivity());
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    public void setLocation(String location){
        city.setName(location);
    }

    private void init(View view){
        presenter = new IHomeFragmentPresenter(this);
        lv_find_work = (LinearLayout) view.findViewById(R.id.lv_find_work);
        lv_choice_location = (LinearLayout) view.findViewById(R.id.lv_choice_location);
        lv_search = (LinearLayout) view.findViewById(R.id.lv_search);
        img_hot_work = (ImageView) view.findViewById(R.id.img_hot_work);
        img_match = (ImageView) view.findViewById(R.id.img_match);
        lvi_personal_infomation = (ListView) view.findViewById(R.id.lvi_personal_infomation);
        lv_common_sence = (LinearLayout) view.findViewById(R.id.lv_common_sence);
        lv_all_time = (LinearLayout) view.findViewById(R.id.lv_all_time);
        lv_part_time = (LinearLayout) view.findViewById(R.id.lv_part_time);
        lv_find_company = (LinearLayout) view.findViewById(R.id.lv_find_company);
        city = new City();
        city.setName("北京");
        city.setLat(MyApplication.latitude);
        city.setLog(MyApplication.longitude);
        fragmentPersonalHomeBinding.setCity(city);

    }

    private void setRegister(){
        personalHomeFragmentClicklistener = new PersonalHomeFragmentClicklistener();
        lv_find_work.setOnClickListener(personalHomeFragmentClicklistener);
        lv_choice_location.setOnClickListener(personalHomeFragmentClicklistener);
        lv_search.setOnClickListener(personalHomeFragmentClicklistener);
        img_hot_work.setOnClickListener(personalHomeFragmentClicklistener);
        img_match.setOnClickListener(personalHomeFragmentClicklistener);
        lv_common_sence.setOnClickListener(personalHomeFragmentClicklistener);
        lv_all_time.setOnClickListener(personalHomeFragmentClicklistener);
        lv_part_time.setOnClickListener(personalHomeFragmentClicklistener);
        lv_find_company.setOnClickListener(personalHomeFragmentClicklistener);
        fragmentPersonalHomeBinding.btnSubmitResume.setOnClickListener(personalHomeFragmentClicklistener);

    }



    class PersonalHomeFragmentClicklistener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lv_find_work:
                    if (getActivity() != null){
                        FindWorkActivity.startFindWorkActivity(getActivity());
                    }
                    break;
                case R.id.lv_choice_location:
                    if (getActivity() != null){
                        Intent intent = new Intent(getActivity(), CityChoiceActivity.class);
                        getActivity().startActivityFromFragment(PersonalHomeFragment.this, intent, MyApplication.DingWeiFlag);
                    }
                    break;
                case R.id.lv_search:
                    if (getActivity() != null){
                        SearchActivity.startSearchActivity(getActivity(), SearchActivity.WORK);
                    }
                    break;
                case R.id.img_hot_work:
                    if (getActivity() != null){
                        HotWorkActivity.startHotWorkActivity(getActivity());
                    }
                    break;
                case R.id.lv_common_sence:
                    ((PersonalHomeActivity)getActivity()).setViewPagerPosition(1);
                    break;
                case R.id.img_match:
                    IntelligentMatchActivity.startIntelligentMatchActivity(getActivity());
                    break;
                case R.id.btn_submit_resume:
                    presenter.subResume(new HashMap());
                    break;
                case R.id.lv_part_time:
                    WorkTypeActivity.startWorkTypeActivity(getActivity(), WorkTypeActivity.PARTTIMEWORK);
                    break;
                case R.id.lv_all_time:
                    WorkTypeActivity.startWorkTypeActivity(getActivity(), WorkTypeActivity.FULLWORK);
                    break;
                case R.id.lv_find_company:
                    FindCompanyActivity.startFindCompanyActivity(getActivity());
                   // SearchActivity.startSearchActivity(getActivity(), SearchActivity.COMPANY);
                    break;
            }
        }
    }


}

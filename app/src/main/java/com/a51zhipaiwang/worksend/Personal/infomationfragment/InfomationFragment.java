package com.a51zhipaiwang.worksend.Personal.infomationfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Enterprise.Fragment.InfomationItemFragment;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.hrinfomation.HRInformationFragment;
import com.a51zhipaiwang.worksend.Personal.sendcallbackfragment.SendCallBackFragment;
import com.a51zhipaiwang.worksend.Personal.systeminformationfragment.SystemInformationFragment;
import com.a51zhipaiwang.worksend.Personal.tryinformationfragment.TryInformationFragment;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;


/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/24
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
public class InfomationFragment extends BaseFragment {

    private ViewPager vp_personal_infomation;
    private TabLayout tab_personal_infomation;
    private TextView tilte_text;
    private ImageView return_image;
    private ImageView personal_center;

    private String[] tals = {"派单消息", "投递反馈", "试岗通知", "系统消息"};
    private ArrayList<BaseFragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_infomation, null);
        init(view);
        setRegister();
        setTal();
        return view;
    }

    private void init(View view){
        fragments = new ArrayList<>();
        fragments.add(HRInformationFragment.newInstance());
        fragments.add(SendCallBackFragment.newInstance());
        fragments.add(TryInformationFragment.newInstance());
        fragments.add(SystemInformationFragment.newInstance());
        vp_personal_infomation = (ViewPager) view.findViewById(R.id.vp_personal_infomation);
        tab_personal_infomation = (TabLayout) view.findViewById(R.id.tab_personal_infomation);
        tilte_text = (TextView) view.findViewById(R.id.tilte_text);
        return_image = (ImageView) view.findViewById(R.id.return_image);
        personal_center = (ImageView) view.findViewById(R.id.personal_center);
        personal_center.setVisibility(View.VISIBLE);
        personal_center.setVisibility(View.VISIBLE);
        personal_center.setImageResource(R.drawable.img_delete_information);
        return_image.setVisibility(View.INVISIBLE);
        tilte_text.setText("消息中心");
        vp_personal_infomation.setOffscreenPageLimit(4);
    }

    private void setRegister(){
        vp_personal_infomation.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        personal_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(InfomationItemFragment.SHOW_DELETE_BROADCAST);
                getActivity().sendBroadcast(intent);
            }
        });
    }
    /**
     * 设置底部tal 及其点击变化效果
     */
    private void setTal() {
        tab_personal_infomation.setupWithViewPager(vp_personal_infomation);
        for (int i = 0; i < tab_personal_infomation.getTabCount(); i++) {
            TabLayout.Tab tab = tab_personal_infomation.getTabAt(i);
            tab.setTag(i);
            tab.setCustomView(R.layout.info_tab_text);
            View tabView = tab.getCustomView();
            TextView tal_text = tabView.findViewById(R.id.tabText);
            tal_text.setText(tals[i]);

        }
    }

}

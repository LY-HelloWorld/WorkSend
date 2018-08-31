package com.a51zhipaiwang.worksend.Enterprise.Fragment;

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

import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;

import java.util.ArrayList;

public class InfomationFragment extends BaseFragment {

    private ArrayList<Fragment> fragments;
    private ViewPager infomationViewPager;
    private TabLayout infomationTabLayout;

    private String[] tals = {"系统消息", "派单反馈"};
    private TextView tilte_text;
    private ImageView return_image;
    private ImageView edit_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infomation, null);
        init(view);
        setRegister();
        setTal();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /*if (getActivity() != null){
            ((HomeActivity)getActivity()).setRedImageVisible(!isVisibleToUser);
        }*/

    }

    private void init(View view){
        tilte_text = (TextView) view.findViewById(R.id.tilte_text);
        tilte_text.setText("消息中心");
        return_image = (ImageView) view.findViewById(R.id.return_image);
        return_image.setVisibility(View.INVISIBLE);
        edit_image = (ImageView) view.findViewById(R.id.personal_center);
        edit_image.setVisibility(View.VISIBLE);
        edit_image.setImageResource(R.drawable.img_delete_information);
        //初始化fragment
        fragments = new ArrayList<>();
        fragments.add(InfomationItemFragment.getInfomationInstance(new String[]{"通知信息", "职派活动"}));
        fragments.add(InfomationItemFragment.getInfomationInstance(new String[]{"派单成功反馈", "已经抢单了"}));

        infomationViewPager = (ViewPager) view.findViewById(R.id.infomationViewPager);
        infomationViewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        infomationTabLayout = (TabLayout) view.findViewById(R.id.infomationTabLayout);
    }

    private void setRegister(){
        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLog.e("InfomationFragment", "onClick(InfomationFragment.java:83)" + "edit_image");
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
        infomationTabLayout.setupWithViewPager(infomationViewPager);
        for (int i = 0; i < infomationTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = infomationTabLayout.getTabAt(i);
            tab.setTag(i);
            tab.setCustomView(R.layout.info_tab_text);
            View tabView = tab.getCustomView();
            TextView tal_text = tabView.findViewById(R.id.tabText);
            tal_text.setText(tals[i]);

        }
    }

}

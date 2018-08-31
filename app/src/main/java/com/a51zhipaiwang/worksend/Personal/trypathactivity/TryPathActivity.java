package com.a51zhipaiwang.worksend.Personal.trypathactivity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.trypathfragment.TryPathFragment;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;
/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/7/20
 *     desc   : 试岗 activity
 *     version: 1.0
 *
        常量
        字段
        构造函数
        重写函数和回调
        公有函数
        私有函数
        内部类或接口
 * </pre>
 */
public class TryPathActivity extends BaseActivity {

    private ArrayList<BaseFragment> baseFragmentArrayList;

    private ImageView return_image;
    private TabLayout tabl_try_path;
    private ViewPager vip_try_path;
    private TextView tilte_text;


    public static void StartTryPathActivity(Context context){
        context.startActivity(new Intent(context, TryPathActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_path);
        init();
        initViewPager();
        initTabLayout();
        setRegister();
    }

    private void init(){
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tabl_try_path = (TabLayout) findViewById(R.id.tabl_try_path);
        vip_try_path = (ViewPager) findViewById(R.id.vip_try_path);
        tilte_text.setText("试岗足迹");
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TryPathActivity.this.finish();
            }
        });
    }


    private void initViewPager(){
        baseFragmentArrayList = new ArrayList<>();
        baseFragmentArrayList.add(TryPathFragment.newInStance(TryPathFragment.TRY_PATH_ING));
        baseFragmentArrayList.add(TryPathFragment.newInStance(TryPathFragment.TRY_PATH_OUT));
        vip_try_path.setOffscreenPageLimit(2);
        vip_try_path.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return baseFragmentArrayList.get(i);
            }

            @Override
            public int getCount() {
                return baseFragmentArrayList.size();
            }
        });

    }

    private void initTabLayout(){
        tabl_try_path.setupWithViewPager(vip_try_path);
        String[] tals = {"试岗状态中", "试岗已结束"};
        for (int i = 0; i < tals.length; i++) {
            TabLayout.Tab tab = tabl_try_path.getTabAt(i);
            if (tab != null){
                tab.setCustomView(R.layout.info_tab_text);
                View tabView = tab.getCustomView();
                if (tabView != null){
                    TextView tal_text = tabView.findViewById(R.id.tabText);
                    tal_text.setText(tals[i]);
                }
            }
        }
    }



}

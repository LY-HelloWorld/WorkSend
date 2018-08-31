package com.a51zhipaiwang.worksend.CommonActivity.startactivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.CommonActivity.fragment.StartFragmentOne;
import com.a51zhipaiwang.worksend.CommonActivity.fragment.StartFragmentThree;
import com.a51zhipaiwang.worksend.CommonActivity.fragment.StartFragmentTwo;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.LogChoiceActivity;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;

public class StartActivity extends BaseActivity {

    private ViewPager vp_start;

    private ArrayList<Fragment> startFragments;
    private Button btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        setRegister();
    }

    private void init(){
        vp_start = (ViewPager) findViewById(R.id.vp_start);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        startFragments = new ArrayList<>();
        startFragments.add(new StartFragmentOne());
        startFragments.add(new StartFragmentTwo());
        startFragments.add(new StartFragmentThree());
    }

    private void setRegister(){
        vp_start.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return startFragments.get(i);
            }

            @Override
            public int getCount() {
                return startFragments.size();
            }
        });


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LogChoiceActivity.class);
                StartActivity.this.startActivity(intent);
                StartActivity.this.finish();
            }
        });
    }


}

package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.JianLiFragment.JianLiListFragment;
import com.a51zhipaiwang.worksend.R;

import java.util.HashMap;


public class JianLiActivity extends BaseActivity {

    public static final int GRADUATE = 1;
    public static final int NEARBY = 2;

    private String path;
    private FragmentManager supportFragmentManager;
    private ImageView return_image;

    private HashMap paramMap;
    private TextView tilte_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        init();
        setRegister();
    }


    public static void StartRecentActivity(Context context, HashMap paramMap, String title, String path){

        Intent intent = new Intent(context, JianLiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("paramMap", paramMap);
        bundle.putString("title", title);
        bundle.putString("path", path);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }


    private void init(){
        paramMap = (HashMap) getIntent().getExtras().getSerializable("paramMap");
        path = getIntent().getStringExtra("path");
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText(getIntent().getExtras().getString("title"));
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        JianLiListFragment jianLiListFragment = JianLiListFragment.GetJianLiListFragmentInstance(false, 3, paramMap, JianLiListFragment.NOTHAVESCROLLVIEW, path);
        jianLiListFragment.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.recentFrame, jianLiListFragment);
        fragmentTransaction.commit();
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JianLiActivity.this.finish();
            }
        });
    }


}

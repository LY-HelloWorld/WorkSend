package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.RegionAdapter;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;

public class RegionActivity extends BaseActivity {

    private ArrayList<City> regions;
    private ListView regionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        init();
        setRegister();
    }

    public static void StartRegionAcitity(BaseActivity context, ArrayList<City> regions){
        Intent intent = new Intent(context, RegionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("regions", regions);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, MyApplication.ChoiceRegionFlag);
    }

    private void init(){
        regionList = (ListView) findViewById(R.id.regionList);
        regions = (ArrayList<City>) getIntent().getExtras().getSerializable("regions");
    }

    private void setRegister(){
        regionList.setAdapter(new RegionAdapter(true, regions, this));
        regionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("region", regions.get(i));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                RegionActivity.this.finish();
            }
        });
    }



}

package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.WorkListAdapter;
import com.a51zhipaiwang.worksend.R;

import java.util.ArrayList;


public class WorkListActivity extends BaseActivity {

    private ListView workSearchList;
    private ImageView return_image;
    private TextView tilte_text;

    private ArrayList<WorkChoiceThreeStage> workChoiceThreeStages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);
        init();
        setRegister();
    }


    public static void StartWorkListActivity(BaseActivity context, ArrayList<WorkChoiceThreeStage> workChoiceThreeStages, String title){
        Intent intent = new Intent(context, WorkListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("workChoiceThreeStages", workChoiceThreeStages);
        bundle.putString("title", title);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, MyApplication.SearchWork);
    }

    private void init(){
        workSearchList = (ListView) findViewById(R.id.workSearchList);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);

        workChoiceThreeStages = (ArrayList<WorkChoiceThreeStage>) getIntent().getExtras().getSerializable("workChoiceThreeStages");
        tilte_text.setText(getIntent().getExtras().getString("title"));
    }

    private void setRegister(){
        workSearchList.setAdapter(new WorkListAdapter(workChoiceThreeStages, this, true));
        workSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("workChoiceThreeStage", workChoiceThreeStages.get(i));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                WorkListActivity.this.finish();
            }
        });
    }



}

package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.Enterprise.Adapter.WorkSearchAdapter;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/8/2
 *     desc   : 企业端工作类别搜索
 *     version: 1.0
 *
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class WorkSearchListActivity extends BaseActivity {

    private String work;
    private HashMap hashMap;
    private ArrayList<WorkChoiceThreeStage> workChoiceThreeStages;

    private TextView tilte_text;
    private ImageView return_image;
    private TextView tx_work;
    private ListView liv_work;

    public static void startWorkSearchListActivity(AppCompatActivity baseActivity, String work, int requestCode) {
        Intent intent = new Intent(baseActivity, WorkSearchListActivity.class);
        intent.putExtra("work", work);
        baseActivity.startActivityForResult(intent, requestCode);
        //baseActivity.startActivity(intent);
        MyLog.e("WorkSearchListActivity", "startWorkSearchListActivity(WorkSearchListActivity.java:57)" + work);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_search_list);
        init();
        setRegister();
        getWork();
    }

    private void init() {
        work = getIntent().getStringExtra("work");
        hashMap = new HashMap();
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tx_work = (TextView) findViewById(R.id.tx_work);
        liv_work = (ListView) findViewById(R.id.liv_work);
        tx_work.setText(work);
        hashMap.put("positionName", work);
    }

    private void setRegister() {
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkSearchListActivity.this.finish();
            }
        });
    }

    private void getWork() {
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/position/conditionPosition.do",
                hashMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            MyLog.e("WorkSearchListActivity", "onResponse(WorkSearchListActivity.java:100)" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("success")) {
                                String info = jsonObject.getString("info");
                                MyLog.e("WorkSearchListActivity", "onResponse(WorkSearchListActivity.java:109)" + info);
                                ArrayList<WorkChoiceThreeStage> workChoiceThreeStages = new Gson().fromJson(info
                                        , new TypeToken<ArrayList<WorkChoiceThreeStage>>() {
                                        }.getType());
                                setList(workChoiceThreeStages);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showToastTwo("没有此职位！");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.showToastTwo("没有此职位！");
                    }
                }));
    }

    private void setList(ArrayList<WorkChoiceThreeStage> workChoiceThreeStages) {
        this.workChoiceThreeStages = workChoiceThreeStages;
        liv_work.setAdapter(new WorkSearchAdapter(workChoiceThreeStages, WorkSearchListActivity.this));
        liv_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                returnChoice(WorkSearchListActivity.this.workChoiceThreeStages.get(i));
            }
        });
    }

    private void returnChoice(WorkChoiceThreeStage workChoiceThreeStage) {
        MyLog.e("WorkSearchListActivity", "returnChoice(WorkSearchListActivity.java:140)" + workChoiceThreeStage.getPositionName());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("workChoiceThreeStage", workChoiceThreeStage);
        intent.putExtras(bundle);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

}

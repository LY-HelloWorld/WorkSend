package com.a51zhipaiwang.worksend.CommonActivity.SendBackActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.Personal.network.PersonalStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SendBackIdeaActivity extends BaseActivity {


    private SendBackIdeaActivityClickListener sendBackIdeaActivityClickListener;
    private BusinessInfo businessInfo;

    private TextView tilte_text;
    private ImageView return_image;
    private EditText edx_email;
    private EditText edx_content;
    private Button btn_send;

    public static void startSendBackIdeaActivity(Context context, BusinessInfo businessInfo){
        Intent intent = new Intent(context, SendBackIdeaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("businessInfo", businessInfo);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_back_idea);
        init();
        setRegister();
    }

    private void init(){
        //businessInfo = (BusinessInfo) getIntent().getExtras().getSerializable("businessInfo");
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        edx_email = (EditText) findViewById(R.id.edx_email);
        edx_content = (EditText) findViewById(R.id.edx_content);
        btn_send = (Button) findViewById(R.id.btn_send);
        tilte_text.setText("意见反馈");
    }

    private void setRegister(){
        sendBackIdeaActivityClickListener = new SendBackIdeaActivityClickListener();
        return_image.setOnClickListener(sendBackIdeaActivityClickListener);
        btn_send.setOnClickListener(sendBackIdeaActivityClickListener);
    }

    //检查信息输入
    private boolean checkInfo(){
        if (TextUtils.isEmpty(edx_email.getText())){
            ToastUtil.showToastTwo("请输入邮箱");
            return false;
        }
        if (TextUtils.isEmpty(edx_content.getText())){
            ToastUtil.showToastTwo("请输入反馈内容");
            return false;
        }
        return true;
    }

    //提交反馈信息
    private void sendInfo(HashMap hashMap){
        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                MyApplication.path + "api/company/feedback.do",
                hashMap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.e("SendBackIdeaActivity", "onResponse(SendBackIdeaActivity.java:83)" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code").equals("SUCCESS")){
                                ToastUtil.showToastTwo("反馈成功");
                                SendBackIdeaActivity.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        MyLog.e("SendBackIdeaActivity", "onErrorResponse(SendBackIdeaActivity.java:90)" + error.getMessage());
                    }
                }));
    }

    class SendBackIdeaActivityClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    SendBackIdeaActivity.this.finish();
                    break;
                case R.id.btn_send:
                    if (checkInfo()){
                        HashMap hashMap = new HashMap();
                        //hashMap.put("enterprise_name", businessInfo.getEnterpriseName());
                        hashMap.put("tbl_feedback_content", edx_content.getText().toString());
                        hashMap.put("feedback_email", edx_email.getText().toString());
                        sendInfo(hashMap);
                    }
                    break;
            }
        }
    }

}

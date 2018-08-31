package com.a51zhipaiwang.worksend.Personal.resumesuccess;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;

public class ResumeSuccessActivity extends BaseActivity {

    private ResumeSuccessActivityOnClickListener resumeSuccessActivityOnClickListener;

    private Button btn_sure;
    private ImageView return_image;
    private TextView tilte_text;

    public static void startResumeSuccessActivity(Context context){
        context.startActivity(new Intent(context, ResumeSuccessActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_success);
        init();
        setRegister();
    }

    private void init(){
        return_image = (ImageView) findViewById(R.id.return_image);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("上传成功");
    }

    private void setRegister(){
        resumeSuccessActivityOnClickListener = new ResumeSuccessActivityOnClickListener();
        btn_sure.setOnClickListener(resumeSuccessActivityOnClickListener);
        return_image.setOnClickListener(resumeSuccessActivityOnClickListener);
    }

    class ResumeSuccessActivityOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                case R.id.btn_sure:
                    ResumeSuccessActivity.this.finish();
                    break;
            }
        }
    }


}

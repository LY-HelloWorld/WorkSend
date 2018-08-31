package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.R;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/7/18
 *     desc   : 客服中心activity
 *     version: 1.0
 * </pre>
 */
public class ServiceActivity extends BaseActivity {

    private ImageView return_image;
    private TextView tilte_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        init();
        setRegister();
    }

    public static void StartServiceActivity(Context context, String title){
        Intent intent = new Intent(context, ServiceActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


    private void init(){
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText(getIntent().getStringExtra("title"));
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceActivity.this.finish();
            }
        });
    }

}

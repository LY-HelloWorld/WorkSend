package com.a51zhipaiwang.worksend.Personal.intelligentMatchActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;

public class IntelligentMatchActivity extends BaseActivity {

    private ImageView img_return;

    public static void startIntelligentMatchActivity(Context context){
        context.startActivity(new Intent(context, IntelligentMatchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent_match);
        init();
        setRegister();
    }

    private void init(){
        img_return = (ImageView) findViewById(R.id.img_return);
    }

    private void setRegister(){
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img_return:
                        IntelligentMatchActivity.this.finish();
                        break;
                }
            }
        });
    }

}

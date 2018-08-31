package com.a51zhipaiwang.worksend.CommonActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;

public class DisclaimerActivity extends BaseActivity {

    private TextView tx_disclaimer;
    private TextView tilte_text;
    private ImageView return_image;

    public static void startDisclaimerActivity(Context context){
        context.startActivity(new Intent(context, DisclaimerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        init();
        setRegister();
    }

    private void init(){
        tx_disclaimer = (TextView) findViewById(R.id.tx_disclaimer);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text.setText("服务条款");
    }

    private void setRegister(){
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisclaimerActivity.this.finish();
            }
        });
    }

}

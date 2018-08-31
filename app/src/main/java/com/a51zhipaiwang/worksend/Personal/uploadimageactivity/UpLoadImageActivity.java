package com.a51zhipaiwang.worksend.Personal.uploadimageactivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity.BusinessInfomationActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import org.json.JSONException;
import org.json.JSONObject;

public class UpLoadImageActivity extends BaseActivity {

    private UpLoadImageActivityClickListener upLoadImageActivityClickListener;

    private String videoPath;
    private String fengMianPath;

    private Button btn_submit;
    private TextView tilte_text;
    private ImageView return_image;
    private ImageView upLoadVideoImage;
    private ImageView upLoadImageFengMian;

    public static void startUpLoadImageActivity(BaseActivity baseActivity, int requestCode) {
        baseActivity.startActivityForResult(new Intent(baseActivity, UpLoadImageActivity.class), requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load_image);
        init();
        setRegister();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UpLoadVideo:
                if (resultCode == RESULT_OK) {
                    UpLoadFile.UpLoadFileResult(UpLoadImageActivity.this, data.getData(), new UpLoadFile.UpLoadFileListener() {
                        @Override
                        public void success(String info) {
                            MyLog.e("BusinessInfomationActivity", "success(BusinessInfomationActivity.java:118)" + info);
                            upLoadVideoImage.setImageResource(R.mipmap.up_load_success);
                            returnVideoSubmitSuccess(info);
                        }

                        @Override
                        public void error(String info) {
                            MyLog.e("BusinessInfomationActivity", "error(BusinessInfomationActivity.java:124)" + info);
                        }
                    });
                }
                break;
            case MyApplication.UpLoadFengMian:
                if (resultCode == RESULT_OK) {
                    UpLoadFile.UpLoadFileResult(UpLoadImageActivity.this, data.getData(), new UpLoadFile.UpLoadFileListener() {
                        @Override
                        public void success(String info) {
                            MyLog.e("BusinessInfomationActivity", "success(BusinessInfomationActivity.java:118)" + info);
                            upLoadImageFengMian.setImageResource(R.mipmap.up_load_success);
                            returnFengMianSubmitSuccess(info);
                        }

                        @Override
                        public void error(String info) {
                            MyLog.e("BusinessInfomationActivity", "error(BusinessInfomationActivity.java:124)" + info);
                        }
                    });
                }
                break;
        }
    }


    public void returnVideoSubmitSuccess(String info) {
        MyLog.e("BusinessInfomationActivity", "returnVideoSubmitSuccess(BusinessInfomationActivity.java:94)" + info);
        try {
            JSONObject logoJson = new JSONObject(info);
            if (logoJson.getString("result").equals("上传成功")){
                videoPath = MyApplication.upLoadPath + logoJson.getString("lj");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void returnFengMianSubmitSuccess(String info) {
        MyLog.e("BusinessInfomationActivity", "returnFengMianSubmitSuccess(BusinessInfomationActivity.java:99)" + info);
        try {
            JSONObject logoJson = new JSONObject(info);
            if (logoJson.getString("result").equals("上传成功")){
                fengMianPath = MyApplication.upLoadPath + logoJson.getString("lj");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        btn_submit = (Button) findViewById(R.id.btn_submit);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        upLoadVideoImage = (ImageView) findViewById(R.id.upLoadVideoImage);
        upLoadImageFengMian = (ImageView) findViewById(R.id.upLoadImageFengMian);
        tilte_text.setText("上传信息");
    }

    private void setRegister() {
        upLoadImageActivityClickListener = new UpLoadImageActivityClickListener();
        return_image.setOnClickListener(upLoadImageActivityClickListener);
        btn_submit.setOnClickListener(upLoadImageActivityClickListener);
        upLoadVideoImage.setOnClickListener(upLoadImageActivityClickListener);
        upLoadImageFengMian.setOnClickListener(upLoadImageActivityClickListener);
    }


    class UpLoadImageActivityClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    UpLoadImageActivity.this.finish();
                    break;
                case R.id.btn_submit:
                    if (!TextUtils.isEmpty(videoPath) && !TextUtils.isEmpty(fengMianPath)){
                        Intent intent = new Intent();
                        intent.putExtra("videoPath", videoPath);
                        intent.putExtra("fengMianPath", fengMianPath);
                        setResult(RESULT_OK, intent);
                        UpLoadImageActivity.this.finish();
                    }else {
                        ToastUtil.showToastTwo("请完善信息");
                    }
                    break;
                case R.id.upLoadVideoImage:
                    UpLoadFile.UpLoadFile(UpLoadImageActivity.this, MyApplication.UpLoadVideo, "video/*");
                    break;
                case R.id.upLoadImageFengMian:
                    UpLoadFile.UpLoadFile(UpLoadImageActivity.this, MyApplication.UpLoadFengMian, "image/*");
                    break;
            }
        }
    }

}

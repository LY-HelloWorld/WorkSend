package com.a51zhipaiwang.worksend.Personal.editprogressactivity;

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
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.TblProjectExperience;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EditProgressActivity extends BaseActivity {

    private TblProjectExperience tblProjectExperience;
    private EditProgressActivityClickListener editProgressActivityClickListener;

    private EditText edx_progress_name;
    private EditText edx_progress_start_time;
    private EditText edx_progress_end_time;
    private EditText edx_work;
    private EditText edx_main_duty;
    private TextView tilte_text;
    private ImageView return_image;
    private Button btn_save;


    public static void startEditProgressActivity(BaseActivity context, int requestCode){
        context.startActivityForResult(new Intent(context, EditProgressActivity.class), requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_progress);
        init();
        setRegister();
    }


    private void init(){
        tblProjectExperience = new TblProjectExperience();
        edx_progress_name = (EditText) findViewById(R.id.edx_progress_name);
        edx_progress_start_time = (EditText) findViewById(R.id.edx_progress_start_time);
        edx_progress_end_time = (EditText) findViewById(R.id.edx_progress_end_time);
        edx_work = (EditText) findViewById(R.id.edx_work);
        edx_main_duty = (EditText) findViewById(R.id.edx_main_duty);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("项目经验");
        return_image = (ImageView) findViewById(R.id.return_image);
        btn_save = (Button) findViewById(R.id.btn_save);

    }

    private void setRegister(){
        editProgressActivityClickListener = new EditProgressActivityClickListener();
        btn_save.setOnClickListener(editProgressActivityClickListener);
        return_image.setOnClickListener(editProgressActivityClickListener);
    }

    private boolean checkInfo(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date startDate;
        Date endDate;
        if (TextUtils.isEmpty(edx_progress_name.getText())){
            ToastUtil.showToastTwo("请输入项目名称");
            return false;
        }
        if (TextUtils.isEmpty(edx_progress_start_time.getText()) || edx_progress_start_time.getText().length() < 10){
            ToastUtil.showToastTwo("请输入项目起始时间格式如2018-01-01");
            return false;
        }

        if (!edx_progress_start_time.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }
        try {
            startDate = simpleDateFormat.parse(edx_progress_start_time.getText().toString());
        }catch (Exception e){
            ToastUtil.showToastTwo("请输入项目起始时间格式如2018-01-01");
            return false;
        }
        if (TextUtils.isEmpty(edx_progress_end_time.getText())|| edx_progress_end_time.getText().length() < 10){
            ToastUtil.showToastTwo("请输入项目终止时间格式如2018-01-01");
            return false;
        }

        if (!edx_progress_end_time.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }
        try {
            endDate = simpleDateFormat.parse(edx_progress_end_time.getText().toString());
        }catch (Exception e){
            ToastUtil.showToastTwo("请输入项目终止时间格式如2018-01-01");
            return false;
        }
        if (startDate.getTime() >= endDate.getTime()){
            ToastUtil.showToastTwo("开始时间不能早于或等于结束时间");
            return false;
        }

        if (TextUtils.isEmpty(edx_work.getText())){
            ToastUtil.showToastTwo("请输入项目职位");
            return false;
        }
        if (edx_main_duty.getText().length() < 6){
            ToastUtil.showToastTwo("请输入项目职责并且不少于6个字");
            return false;
        }
        return true;
    }

    private void setInfo(){
        tblProjectExperience.setEntryname(edx_progress_name.getText().toString());
        tblProjectExperience.setStarttime(edx_progress_start_time.getText().toString());
        tblProjectExperience.setEndtime(edx_progress_end_time.getText().toString());
        tblProjectExperience.setPosition(edx_work.getText().toString());
        tblProjectExperience.setResponsibilities(edx_main_duty.getText().toString());
    }

    class EditProgressActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    ActivityCollector.removeActivity(EditProgressActivity.this);
                    break;
                case R.id.btn_save:
                    if (checkInfo()){
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        setInfo();
                        bundle.putSerializable("tblProjectExperience", tblProjectExperience);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        ActivityCollector.removeActivity(EditProgressActivity.this);
                    }
                    break;
            }
        }
    }

}

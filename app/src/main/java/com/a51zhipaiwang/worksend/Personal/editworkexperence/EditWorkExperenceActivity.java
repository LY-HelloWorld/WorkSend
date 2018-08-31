package com.a51zhipaiwang.worksend.Personal.editworkexperence;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.TblWorkExperience;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditWorkExperenceActivity extends BaseActivity {

    private TblWorkExperience tblWorkExperience;
    private EditWorkExperenceClickListener editWorkExperenceClickListener;

    private Button btn_save;
    private EditText edx_company_name;
    private EditText edx_start_time;
    private EditText edx_end_time;
    private EditText edx_work;
    private EditText edx_work_content;
    private RadioGroup rag_money;
    private RadioButton rdb_moneyNoLimit;
    private RadioButton rdb_five;
    private RadioButton rdb_ten;
    private RadioButton rdb_fifting;
    private RadioButton rdb_twenty;
    private RadioButton rdb_out_twenty;
    private TextView tilte_text;
    private ImageView return_image;

    public static void startEditWorkExperenceActivity(BaseActivity context, int requestCode){
        context.startActivityForResult(new Intent(context, EditWorkExperenceActivity.class), requestCode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_work_experence);
        init();
        setRegister();
    }

    private void init(){
        tblWorkExperience = new TblWorkExperience();
        btn_save = (Button) findViewById(R.id.btn_save);
        edx_company_name = (EditText) findViewById(R.id.edx_company_name);
        edx_start_time = (EditText) findViewById(R.id.edx_start_time);
        edx_end_time = (EditText) findViewById(R.id.edx_end_time);
        edx_work = (EditText) findViewById(R.id.edx_work);
        edx_work_content = (EditText) findViewById(R.id.edx_work_content);
        rag_money = (RadioGroup) findViewById(R.id.rag_money);
        rdb_moneyNoLimit = (RadioButton) findViewById(R.id.rdb_moneyNoLimit);
        rdb_five = (RadioButton) findViewById(R.id.rdb_five);
        rdb_ten = (RadioButton) findViewById(R.id.rdb_ten);
        rdb_fifting = (RadioButton) findViewById(R.id.rdb_fifting);
        rdb_twenty = (RadioButton) findViewById(R.id.rdb_twenty);
        rdb_out_twenty = (RadioButton) findViewById(R.id.rdb_out_twenty);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text.setText("工作经验");
        rdb_moneyNoLimit.setChecked(true);
    }

    private void setRegister(){
        editWorkExperenceClickListener = new EditWorkExperenceClickListener();
        rag_money.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getId()) {
                    case R.id.rag_money:
                        if (rdb_moneyNoLimit.isChecked()){
                            tblWorkExperience.setSalary("010");
                        }
                        if (rdb_five.isChecked()){
                            tblWorkExperience.setSalary("020");
                        }
                        if (rdb_ten.isChecked()){
                            tblWorkExperience.setSalary("030");
                        }
                        if (rdb_fifting.isChecked()){
                            tblWorkExperience.setSalary("040");
                        }
                        if (rdb_twenty.isChecked()){
                            tblWorkExperience.setSalary("050");
                        }
                        if (rdb_out_twenty.isChecked()){
                            tblWorkExperience.setSalary("060");
                        }
                        break;
                }
            }
        });
        return_image.setOnClickListener(editWorkExperenceClickListener);
        btn_save.setOnClickListener(editWorkExperenceClickListener);
    }

    private boolean checkInfo(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date startDate;
        Date endDate;

        if (TextUtils.isEmpty(edx_company_name.getText())){
            ToastUtil.showToastTwo("请输入公司名称");
            return false;
        }

        if (TextUtils.isEmpty(edx_start_time.getText())){
            ToastUtil.showToastTwo("请输入日期格式如2018-1-1");
            return false;
        }
        try {
            startDate = simpleDateFormat.parse(edx_start_time.getText().toString());
        } catch (ParseException e) {
            ToastUtil.showToastTwo("请输入日期格式如2018-1-1");
            return false;
        }

        if (!edx_start_time.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }

        if (TextUtils.isEmpty(edx_end_time.getText())){
            ToastUtil.showToastTwo("请输入日期格式如2018-1-1");
            return false;
        }
        try {
            endDate = simpleDateFormat.parse(edx_end_time.getText().toString());
        } catch (ParseException e) {
            ToastUtil.showToastTwo("请输入日期格式如2018-1-1");
            return false;
        }

        if (!edx_end_time.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }
        if (startDate.getTime() >= endDate.getTime()){
            ToastUtil.showToastTwo("开始时间不能早于或等于结束时间");
            return false;
        }
        if (TextUtils.isEmpty(edx_work.getText())){
            ToastUtil.showToastTwo("请输入工作");
            return false;
        }
        if (edx_work_content.getText().length() < 6){
            ToastUtil.showToastTwo("请输入工作内容并且不小于6个字");
            return false;
        }
        return true;
    }

    private void setWorkExperience(){
        tblWorkExperience.setCorporatename(edx_company_name.getText().toString());
        tblWorkExperience.setPosition(edx_work.getText().toString());
        tblWorkExperience.setStarttime(edx_start_time.getText().toString());
        tblWorkExperience.setEndtime(edx_end_time.getText().toString());
        tblWorkExperience.setJobcontent(edx_work_content.getText().toString());
    }

    class EditWorkExperenceClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_save:
                    if (checkInfo()){
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        setWorkExperience();
                        bundle.putSerializable("tblWorkExperience", tblWorkExperience);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        ActivityCollector.removeActivity(EditWorkExperenceActivity.this);
                    }
                    break;
                case R.id.return_image:
                    ActivityCollector.removeActivity(EditWorkExperenceActivity.this);
                    break;
            }
        }
    }

}

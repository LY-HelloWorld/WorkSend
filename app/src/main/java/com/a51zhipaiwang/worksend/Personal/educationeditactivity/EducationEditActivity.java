package com.a51zhipaiwang.worksend.Personal.educationeditactivity;

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
import com.a51zhipaiwang.worksend.Personal.entity.TblEducationalExperience;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EducationEditActivity extends BaseActivity {

    private EducationEditActivityClickListener educationEditActivityClickListener;
    private RadioCheckChange radioCheckChange;
    private TblEducationalExperience tblEducationalExperience;

    private TextView tilte_text;
    private ImageView return_image;
    private EditText edx_school_name;
    private EditText edx_major;
    private EditText edx_come_school;
    private EditText edx_graducation;
    private EditText edx_major_type;
    private EditText edx_school_experince;
    private Button btn_save;
    private RadioButton allDayRadioBt;
    private RadioButton notAllDayRadioBt;
    private RadioButton twoRadioBt;
    private RadioButton nineRadioBt;
    private RadioButton commonRadioBt;
    private RadioGroup rag_type;
    private RadioGroup rag_school_type;

    public static void startEducationEditActivity(BaseActivity context, int requestCode) {
        Intent intent = new Intent(context, EducationEditActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit);
        init();
        setRegister();
        initData();
    }

    private void init() {
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("教育经历");
        return_image = (ImageView) findViewById(R.id.return_image);
        edx_school_name = (EditText) findViewById(R.id.edx_school_name);
        edx_major = (EditText) findViewById(R.id.edx_major);
        edx_come_school = (EditText) findViewById(R.id.edx_come_school);
        edx_graducation = (EditText) findViewById(R.id.edx_graducation);
        edx_major_type = (EditText) findViewById(R.id.edx_major_type);
        edx_school_experince = (EditText) findViewById(R.id.edx_school_experince);
        btn_save = (Button) findViewById(R.id.btn_save);
        allDayRadioBt = (RadioButton) findViewById(R.id.allDayRadioBt);
        notAllDayRadioBt = (RadioButton) findViewById(R.id.notAllDayRadioBt);
        twoRadioBt = (RadioButton) findViewById(R.id.twoRadioBt);
        nineRadioBt = (RadioButton) findViewById(R.id.nineRadioBt);
        commonRadioBt = (RadioButton) findViewById(R.id.commonRadioBt);
        rag_type = (RadioGroup) findViewById(R.id.rag_type);
        rag_school_type = (RadioGroup) findViewById(R.id.rag_school_type);
        tblEducationalExperience = new TblEducationalExperience();

    }

    private void setRegister() {
        educationEditActivityClickListener = new EducationEditActivityClickListener();
        radioCheckChange = new RadioCheckChange();
        return_image.setOnClickListener(educationEditActivityClickListener);
        btn_save.setOnClickListener(educationEditActivityClickListener);
        rag_type.setOnCheckedChangeListener(radioCheckChange);
        rag_school_type.setOnCheckedChangeListener(radioCheckChange);
    }

    private boolean checkInfo() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date startDate;
        Date endDate;

        if (TextUtils.isEmpty(edx_school_name.getText())){
            ToastUtil.showToastTwo("请输入学校");
            return false;
        }
        if (TextUtils.isEmpty(edx_major.getText())){
            ToastUtil.showToastTwo("请输入学校");
            return false;
        }

        try {
            startDate = simpleDateFormat.parse(edx_come_school.getText().toString());
        } catch (ParseException e) {
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }
        if (TextUtils.isEmpty(edx_come_school.getText()) || edx_come_school.getText().length() < 10){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }

        if (!edx_come_school.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }
        try {
            endDate = simpleDateFormat.parse(edx_graducation.getText().toString());
        } catch (ParseException e) {
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }
        if (TextUtils.isEmpty(edx_graducation.getText()) || edx_graducation.getText().length() < 10){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }

        if (!edx_graducation.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }

        if (startDate.getTime() >= endDate.getTime()){
            ToastUtil.showToastTwo("开始时间不能早于或等于结束时间");
            return false;
        }
        if (TextUtils.isEmpty(edx_major_type.getText())){
            ToastUtil.showToastTwo("请输入专业类别");
            return false;
        }
        if (edx_school_experince.getText().length() < 6){
            ToastUtil.showToastTwo("请输入在校经历至少6个字");
            return false;
        }
        return true;
    }

    private void setExperence() {
        tblEducationalExperience.setUniversityname(edx_school_name.getText().toString());
        tblEducationalExperience.setMajor(edx_major.getText().toString());
        tblEducationalExperience.setEnrolmenttime(edx_come_school.getText().toString());
        tblEducationalExperience.setGraduationtime(edx_graducation.getText().toString());
        tblEducationalExperience.setProfessionalcategory(edx_major_type.getText().toString());
        tblEducationalExperience.setProfessionaldescription(edx_school_experince.getText().toString());
    }

    private void initData(){
        allDayRadioBt.setChecked(true);
        commonRadioBt.setChecked(true);
    }


    class EducationEditActivityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    ActivityCollector.removeActivity(EducationEditActivity.this);
                    break;
                case R.id.btn_save:
                    if (checkInfo()) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        setExperence();
                        bundle.putSerializable("tblEducationalExperience", tblEducationalExperience);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        ActivityCollector.removeActivity(EducationEditActivity.this);
                    }
                    break;
            }
        }
    }

    class RadioCheckChange implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getId()) {
                case R.id.rag_school_type:
                    if (twoRadioBt.isChecked()) {
                        tblEducationalExperience.setSschoolGrade("010");
                    }
                    if (nineRadioBt.isChecked()) {
                        tblEducationalExperience.setSschoolGrade("020");
                    }
                    if (commonRadioBt.isChecked()) {
                        tblEducationalExperience.setSschoolGrade("030");
                    }

                    break;
                case R.id.rag_type:
                    if (allDayRadioBt.isChecked()) {
                        tblEducationalExperience.setSchoolsystem("010");
                    }
                    if (notAllDayRadioBt.isChecked()) {
                        tblEducationalExperience.setSchoolsystem("020");
                    }
                    break;
            }
        }
    }

}

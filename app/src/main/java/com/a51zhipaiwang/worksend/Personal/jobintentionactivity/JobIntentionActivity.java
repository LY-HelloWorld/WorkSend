package com.a51zhipaiwang.worksend.Personal.jobintentionactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.ResumeEntity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ActivityCollector;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.View.MyLinearlayout;


public class JobIntentionActivity extends BaseActivity {

    private JobIntentionActivityChangeListener jobIntentionActivityChangeListener;
    private JobIntentionActivityClickListener jobIntentionActivityClickListener;
    private ResumeEntity resumeEntity;

    private MyLinearlayout lv_expact_work;
    private MyLinearlayout lv_expact_city;
    private RadioGroup workExperenceRadioGroup;
    private RadioGroup educationRadioGroup;
    private RadioGroup rag_money;
    private RadioGroup rag_work_state;
    private RadioGroup rag_work_type;
    private RadioButton workExpressNoLimit;
    private RadioButton yingJieShengRadioBt;
    private RadioButton threeYearRadioBt;
    private RadioButton fiveYearRadioBt;
    private RadioButton tenYearRadioBt;
    private RadioButton outTenYearRadioBt;

    private RadioButton noLimitStudyRadioBt;
    private RadioButton highSchoolRadioBt;
    private RadioButton technicalSchoolRadioBt;
    private RadioButton highTechnicalSchoolRadioBt;
    private RadioButton benKeRadioBt;
    private RadioButton suoShiRadioBt;

    private RadioButton rdb_moneyNoLimit;
    private RadioButton rdb_five;
    private RadioButton rdb_ten;
    private RadioButton rdb_fifting;
    private RadioButton rdb_twenty;
    private RadioButton rdb_out_twenty;

    private RadioButton rab_out_work;
    private RadioButton rab_in_work;

    private RadioButton rab_all_work;
    private RadioButton rab_part_time_work;
    private ImageView return_image;
    private TextView tilte_text;
    private TextView tx_work;
    private TextView tx_city;
    private Button btn_save;

    public static void startJobIntentionActivity(Activity context, int requestCode){
        Intent intent = new Intent(context, JobIntentionActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_intention);
        init();
        setRegister();
        initRadioButton();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.WorkClassificationFlag:
                if (resultCode == RESULT_OK){
                    Bundle workBundle = data.getExtras();
                    WorkChoiceThreeStage workChoiceThreeStage = (WorkChoiceThreeStage) workBundle.getSerializable("workChoiceThreeStage");
                    tx_work.setText(workChoiceThreeStage.getPositionName());
                    resumeEntity.setExpectedcareer(workChoiceThreeStage.getId() + "");
                    resumeEntity.setExpectedcareerName(workChoiceThreeStage.getPositionName() + "");
                }
                break;
            case MyApplication.ChoiceCityFlag:
                if (resultCode == RESULT_OK){
                    City city = (City)data.getExtras().getSerializable("region");
                    tx_city.setText(city.getName());
                    MyApplication.longitude = String.valueOf(city.getLog());
                    MyApplication.latitude = String.valueOf(city.getLat());
                    resumeEntity.setExpectCity(city.getName() + "");
                    resumeEntity.setLatitude(city.getLat() + "");
                    resumeEntity.setLongitude(city.getLog() + "");
                }
                break;
        }
    }

    private void initRadioButton(){
        workExpressNoLimit.setChecked(true);
        noLimitStudyRadioBt.setChecked(true);
        rdb_moneyNoLimit.setChecked(true);
        rab_out_work.setChecked(true);
        rab_all_work.setChecked(true);
    }
    private void init() {
        resumeEntity = new ResumeEntity();
        lv_expact_work = (MyLinearlayout) findViewById(R.id.lv_expact_work);
        lv_expact_city = (MyLinearlayout) findViewById(R.id.lv_expact_city);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("求职意愿");
        workExperenceRadioGroup = (RadioGroup) findViewById(R.id.workExperenceRadioGroup);
        educationRadioGroup = (RadioGroup) findViewById(R.id.educationRadioGroup);
        rag_money = (RadioGroup) findViewById(R.id.rag_money);
        rag_work_state = (RadioGroup) findViewById(R.id.rag_work_state);
        rag_work_type = (RadioGroup) findViewById(R.id.rag_work_type);

        workExpressNoLimit = (RadioButton) findViewById(R.id.workExpressNoLimit);
        yingJieShengRadioBt = (RadioButton) findViewById(R.id.yingJieShengRadioBt);
        threeYearRadioBt = (RadioButton) findViewById(R.id.threeYearRadioBt);
        fiveYearRadioBt = (RadioButton) findViewById(R.id.fiveYearRadioBt);
        tenYearRadioBt = (RadioButton) findViewById(R.id.tenYearRadioBt);
        outTenYearRadioBt = (RadioButton) findViewById(R.id.outTenYearRadioBt);

        noLimitStudyRadioBt = (RadioButton) findViewById(R.id.noLimitStudyRadioBt);
        highSchoolRadioBt = (RadioButton) findViewById(R.id.highSchoolRadioBt);
        technicalSchoolRadioBt = (RadioButton) findViewById(R.id.technicalSchoolRadioBt);
        highTechnicalSchoolRadioBt = (RadioButton) findViewById(R.id.highTechnicalSchoolRadioBt);
        benKeRadioBt = (RadioButton) findViewById(R.id.benKeRadioBt);
        suoShiRadioBt = (RadioButton) findViewById(R.id.suoShiRadioBt);

        rdb_moneyNoLimit = (RadioButton) findViewById(R.id.rdb_moneyNoLimit);
        rdb_five = (RadioButton) findViewById(R.id.rdb_five);
        rdb_ten = (RadioButton) findViewById(R.id.rdb_ten);
        rdb_fifting = (RadioButton) findViewById(R.id.rdb_fifting);
        rdb_twenty = (RadioButton) findViewById(R.id.rdb_twenty);
        rdb_out_twenty = (RadioButton) findViewById(R.id.rdb_out_twenty);

        rab_out_work = (RadioButton) findViewById(R.id.rab_out_work);
        rab_in_work = (RadioButton) findViewById(R.id.rab_in_work);

        rab_all_work = (RadioButton) findViewById(R.id.rab_all_work);
        rab_part_time_work = (RadioButton) findViewById(R.id.rab_part_time_work);

        tx_work = (TextView) findViewById(R.id.tx_work);
        tx_city = (TextView) findViewById(R.id.tx_city);

        btn_save = (Button) findViewById(R.id.btn_save);

    }

    private void setRegister() {
        jobIntentionActivityChangeListener = new JobIntentionActivityChangeListener();
        jobIntentionActivityClickListener = new JobIntentionActivityClickListener();
        workExperenceRadioGroup.setOnCheckedChangeListener(jobIntentionActivityChangeListener);
        educationRadioGroup.setOnCheckedChangeListener(jobIntentionActivityChangeListener);
        rag_money.setOnCheckedChangeListener(jobIntentionActivityChangeListener);
        rag_work_state.setOnCheckedChangeListener(jobIntentionActivityChangeListener);
        rag_work_type.setOnCheckedChangeListener(jobIntentionActivityChangeListener);

        return_image.setOnClickListener(jobIntentionActivityClickListener);
        lv_expact_work.setOnClickListener(jobIntentionActivityClickListener);
        lv_expact_city.setOnClickListener(jobIntentionActivityClickListener);
        btn_save.setOnClickListener(jobIntentionActivityClickListener);

    }

    private boolean checkInfo(){
        if (!TextUtils.isEmpty(tx_work.getText()) &&
                !TextUtils.isEmpty(tx_city.getText())){
            return true;
        }else {
            return false;
        }
    }

    class JobIntentionActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lv_expact_work:
                    WorkCassificationActivity.startWorkCassificationActivity(JobIntentionActivity.this, MyApplication.WorkClassificationFlag, WorkCassificationActivity.PERSONAL);
                    /*Intent intent = new Intent(JobIntentionActivity.this, WorkCassificationActivity.class);
                    JobIntentionActivity.this.startActivityForResult(intent, MyApplication.WorkClassificationFlag);*/
                    break;
                case R.id.lv_expact_city:
                    CityChoiceActivity.startCityChoiceActivity(JobIntentionActivity.this, MyApplication.ChoiceCityFlag);
                    break;
                case R.id.return_image:
                    ActivityCollector.removeActivity(JobIntentionActivity.this);
                    break;
                case R.id.btn_save:
                    if (checkInfo()){
                        Intent intent1 = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("resumeEntity", resumeEntity);
                        intent1.putExtras(bundle);
                        setResult(RESULT_OK, intent1);
                        ActivityCollector.removeActivity(JobIntentionActivity.this);
                    }else {
                        ToastUtil.showToastTwo("请完善信息");
                    }
                    break;
            }
        }
    }

    class JobIntentionActivityChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getId()) {
                case R.id.workExperenceRadioGroup:
                    if (workExpressNoLimit.isChecked()) {
                        resumeEntity.setWorkingLife("010");
                    }
                    if (yingJieShengRadioBt.isChecked()) {
                        resumeEntity.setWorkingLife("020");
                    }
                    if (threeYearRadioBt.isChecked()) {
                        resumeEntity.setWorkingLife("030");
                    }
                    if (fiveYearRadioBt.isChecked()) {
                        resumeEntity.setWorkingLife("040");
                    }
                    if (tenYearRadioBt.isChecked()) {
                        resumeEntity.setWorkingLife("050");
                    }
                    if (outTenYearRadioBt.isChecked()) {
                        resumeEntity.setWorkingLife("060");
                    }
                    break;
                case R.id.educationRadioGroup:
                    if (noLimitStudyRadioBt.isChecked()) {
                        resumeEntity.setEducation("010");
                    }
                    if (highSchoolRadioBt.isChecked()) {
                        resumeEntity.setEducation("020");
                    }
                    if (technicalSchoolRadioBt.isChecked()) {
                        resumeEntity.setEducation("030");
                    }
                    if (highTechnicalSchoolRadioBt.isChecked()) {
                        resumeEntity.setEducation("040");
                    }
                    if (benKeRadioBt.isChecked()) {
                        resumeEntity.setEducation("050");
                    }
                    if (suoShiRadioBt.isChecked()) {
                        resumeEntity.setEducation("060");
                    }
                    break;
                case R.id.rag_money:
                    if (rdb_moneyNoLimit.isChecked()) {
                        resumeEntity.setSalaryexpectation("010");
                    }
                    if (rdb_five.isChecked()) {
                        resumeEntity.setSalaryexpectation("020");
                    }
                    if (rdb_ten.isChecked()) {
                        resumeEntity.setSalaryexpectation("030");
                    }
                    if (rdb_fifting.isChecked()) {
                        resumeEntity.setSalaryexpectation("040");
                    }
                    if (rdb_twenty.isChecked()) {
                        resumeEntity.setSalaryexpectation("050");
                    }
                    if (rdb_out_twenty.isChecked()) {
                        resumeEntity.setSalaryexpectation("060");
                    }
                    break;
                case R.id.rag_work_state:
                    if (rab_out_work.isChecked()) {
                        resumeEntity.setPositionstatus("010");
                    }
                    if (rab_in_work.isChecked()) {
                        resumeEntity.setPositionstatus("020");
                    }
                    break;
                case R.id.rag_work_type:
                    if (rab_all_work.isChecked()) {
                        resumeEntity.setCol2("010");
                    }
                    if (rab_part_time_work.isChecked()) {
                        resumeEntity.setCol2("020");
                    }
                    break;
            }
        }
    }

}

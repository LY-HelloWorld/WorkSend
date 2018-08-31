package com.a51zhipaiwang.worksend.Enterprise.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;


public class ChoiceConditionActivity extends BaseActivity {

    private Button resetBt;
    private Button sureBt;
    private TextView cityText;
    private RadioButton sexNoLimitRadioBt;
    private RadioButton workNoLimitRadioBt;
    private RadioButton workExpressNoLimit;
    private RadioButton noLimitStudyRadioBt;

    private ChoiceConditionTextWatch choiceConditionTextWatch;
    private ChoiceConditionClickListener choiceConditionClickListener;
    private ChoiceConditionCheckListener choiceConditionCheckListener;

    private ImageView return_image;
    private RadioGroup sexRadioGroup;
    private RadioGroup workRadioGroup;
    private RadioGroup educationRadioGroup;
    private RadioGroup workExperenceRadioGroup;


    private WorkBean workBean;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_choice_condtion);
        init();
        setRegister();
        setReset();
    }

    private void init(){
        workBean = new WorkBean();
        choiceConditionCheckListener = new ChoiceConditionCheckListener();
        choiceConditionTextWatch = new ChoiceConditionTextWatch();
        choiceConditionClickListener = new ChoiceConditionClickListener();
        return_image = (ImageView) findViewById(R.id.return_image);
        resetBt = (Button) findViewById(R.id.resetBt);
        sureBt = (Button) findViewById(R.id.sureBt);
        cityText = (TextView) findViewById(R.id.cityText);
        sexNoLimitRadioBt = (RadioButton) findViewById(R.id.sexNoLimitRadioBt);

        workNoLimitRadioBt = (RadioButton) findViewById(R.id.workNoLimitRadioBt);

        workExpressNoLimit = (RadioButton) findViewById(R.id.workExpressNoLimit);
        noLimitStudyRadioBt = (RadioButton) findViewById(R.id.noLimitStudyRadioBt);

        sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
        workRadioGroup = (RadioGroup) findViewById(R.id.workRadioGroup);
        workExperenceRadioGroup = (RadioGroup) findViewById(R.id.workExperenceRadioGroup);
        educationRadioGroup = (RadioGroup) findViewById(R.id.educationRadioGroup);
    }

    private void setRegister(){
        resetBt.setOnClickListener(choiceConditionClickListener);

        sureBt.setOnClickListener(choiceConditionClickListener);
        cityText.setOnClickListener(choiceConditionClickListener);
        return_image.setOnClickListener(choiceConditionClickListener);

        cityText.addTextChangedListener(choiceConditionTextWatch);
        sexRadioGroup.setOnCheckedChangeListener(choiceConditionCheckListener);
        workRadioGroup.setOnCheckedChangeListener(choiceConditionCheckListener);
        workExperenceRadioGroup.setOnCheckedChangeListener(choiceConditionCheckListener);
        educationRadioGroup.setOnCheckedChangeListener(choiceConditionCheckListener);
    }

    /**
     * 初始化已选选项
     */
    private void setReset(){
        sexNoLimitRadioBt.setChecked(true);
        workNoLimitRadioBt.setChecked(true);
        workExpressNoLimit.setChecked(true);
        noLimitStudyRadioBt.setChecked(true);
        cityText.setText("");
    }

    private boolean checkInfo(){
        return !TextUtils.isEmpty(cityText.getText());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.ChoiceCityFlag:
                if (resultCode == RESULT_OK){
                    city = (City)data.getExtras().getSerializable("region");
                    cityText.setText(city.getName());
                    workBean.setArea(city.getName());
                }
                break;
        }
    }


    class ChoiceConditionCheckListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getId()) {
                //性别
                case R.id.sexRadioGroup:
                    switch (i) {
                        case R.id.sexNoLimitRadioBt:
                            workBean.setSex("030");
                            break;
                        case R.id.manRadioBt:
                            workBean.setSex("010");
                            break;
                        case R.id.wemanRadioBt:
                            workBean.setSex("020");
                            break;
                    }
                    break;
                 //工作状态
                case R.id.workRadioGroup:
                    switch (i) {
                        case R.id.workNoLimitRadioBt:
                            workBean.setWorkNature("030");
                            break;
                        case R.id.outWorkRadioBt:
                            workBean.setWorkNature("010");
                            break;
                        case R.id.workRadioBt:
                            workBean.setWorkNature("020");
                            break;
                    }
                    break;
                //工作经验
                case R.id.workExperenceRadioGroup:
                    switch (i) {
                        case R.id.workExpressNoLimit:
                            workBean.setWorkExperience("010");
                            break;
                        case R.id.yingJieShengRadioBt:
                            workBean.setWorkExperience("020");
                            break;/*
                        case R.id.oneYearRadioBt:
                            workBean.setWorkExperience("030");
                            break;*/
                        case R.id.threeYearRadioBt:
                            workBean.setWorkExperience("030");
                            break;
                        case R.id.fiveYearRadioBt:
                            workBean.setWorkExperience("040");
                            break;
                        case R.id.tenYearRadioBt:
                            workBean.setWorkExperience("050");
                            break;
                        case R.id.outTenYearRadioBt:
                            workBean.setWorkExperience("060");
                            break;
                    }
                    break;
                //学历
                case R.id.educationRadioGroup:
                    switch (i) {
                        case R.id.noLimitStudyRadioBt:
                            workBean.setEducation("010");
                            break;
                        case R.id.highSchoolRadioBt:
                            workBean.setEducation("020");
                            break;
                        case R.id.technicalSchoolRadioBt:
                            workBean.setEducation("030");
                            break;
                        case R.id.highTechnicalSchoolRadioBt:
                            workBean.setEducation("040");
                            break;
                        case R.id.benKeRadioBt:
                            workBean.setEducation("050");
                            break;
                        case R.id.suoShiRadioBt:
                            workBean.setEducation("060");
                            break;
                    }
                    break;
            }
        }
    }

    /**
     * 输入监听
     */
    class ChoiceConditionTextWatch implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            //修改输入框颜色
            if (!TextUtils.isEmpty(cityText.getText().toString())){
                cityText.setTextColor(getResources().getColor(R.color.oriange));
                cityText.setBackground(getResources().getDrawable(R.drawable.radio_check_bg));
            }else {
                cityText.setTextColor(getResources().getColor(R.color.textColor));
                cityText.setBackground(getResources().getDrawable(R.drawable.radio_check_false_bg));
            }
        }
    }


    class ChoiceConditionClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.noLimitStudyRadioBt:
                    break;
                case R.id.sureBt:
                    if (checkInfo()){
                        ToastUtil.showToastTwo("筛选完成");
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("info", "测试");
                        bundle.putSerializable("work", workBean);
                        bundle.putSerializable("city", city);
                        intent.putExtras(bundle);
                        ChoiceConditionActivity.this.setResult(Activity.RESULT_OK, intent);
                        ChoiceConditionActivity.this.finish();
                    }else {
                        ToastUtil.showToastTwo("信息没输入完");
                    }
                    break;
                case R.id.resetBt:
                    setReset();
                    break;
                case R.id.cityText:
                    Intent intent = new Intent(ChoiceConditionActivity.this, CityChoiceActivity.class);
                    ChoiceConditionActivity.this.startActivityForResult(intent, MyApplication.ChoiceCityFlag);
                    break;
                case R.id.return_image:
                    ChoiceConditionActivity.this.finish();
                    break;
            }
        }
    }


}

package com.a51zhipaiwang.worksend.Enterprise.Activity.PaiDanActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Bean.WorkBean;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterprisePaiDanActivity.IPaiDanAcitivityPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterprisePaiDanActivity.PaiDanActivityPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;



public class PaiDanActivity extends BaseActivity implements IPaiDanActivity {

    //城市文字框
    private TextView cityText;
    private RadioButton sexNoLimitRadioBt;
    private RadioButton manRadioBt;
    private RadioButton womenRadioBt;
    private RadioButton allWork;
    private RadioButton partTimeWork;
    private RadioButton workExpressNoLimit;
    private RadioButton graduateRadioBt;
    private RadioButton oneYearBt;
    private RadioButton inThreeYearBt;
    private RadioButton inFiveYearBt;
    private RadioButton inTenYear;
    private RadioButton outTenYear;
    private RadioButton noLimitStudyRadioBt;
    private RadioButton highSchoolRadioBt;
    private RadioButton technicalSchoolRadioBt;
    private RadioButton highTechnicalSchoolRadioBt;
    private RadioButton benKeRadioBt;
    private RadioButton suoShiRadioBt;
    private Button resetBt;
    private Button sureBt;

    private PaiDanActivityClickListener paiDanActivityClickListener;
    private PaiDanTextWatch paiDanTextWatch;
    private PaiDanCheckChangeListener paiDanCheckChangeListener;

    private TextView workText;
    private TextView tilte_text;
    private ImageView return_image;
    private EditText workTime;
    private EditText workMoney;
    private RadioButton noLimitMoney;
    private RadioButton fiveRMB;
    private RadioButton tenRMB;
    private RadioButton fivtingRMB;
    private RadioButton twtentyRMB;
    private RadioButton upTewtentyRMB;
    private EditText workDescribtion;
    //工作
    private WorkBean workBean;
    //城市
    private City city;
    private RadioGroup workStatusRadioGroup;
    private RadioGroup sexRadioGroup;
    private RadioGroup workExpressRadioGroup;
    private RadioGroup educationRadioGroup;
    private RadioGroup moneyRadioGroup;

    private IPaiDanAcitivityPresenter paiDanAcitivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_dan);
        init();
        setRegister();
        setReset();
    }

    public static void StartPaiDanActivity(Context context, WorkBean work){
        Intent intent = new Intent(context, PaiDanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("work", work);
        intent.putExtras(bundle);
        ((BaseActivity)context).startActivityForResult(intent, MyApplication.AddWork);
    }

    /**
     * 初始化
     */
    private void init(){
        paiDanAcitivityPresenter = new PaiDanActivityPresenter(this);
        try {
            workBean = (WorkBean) getIntent().getExtras().getSerializable("work");
        }catch (Exception e){
            workBean = new WorkBean();
        }
        //title文字
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("派单");
        //返回
        return_image = (ImageView) findViewById(R.id.return_image);
        return_image.setVisibility(View.VISIBLE);
        //城市文字展示
        cityText = (TextView) findViewById(R.id.cityText);
        //性别无限制单选
        sexNoLimitRadioBt = (RadioButton) findViewById(R.id.sexNoLimitRadioBt);
        //男单选
        manRadioBt = (RadioButton) findViewById(R.id.manRadioBt);
        //女单选
        womenRadioBt = (RadioButton) findViewById(R.id.womenRadioBt);
        //全职
        allWork = (RadioButton) findViewById(R.id.allWork);
        //兼职
        partTimeWork = (RadioButton) findViewById(R.id.partTimeWork);
        //工作
        workText = (TextView) findViewById(R.id.workText);
        //工作经验不限
        workExpressNoLimit = (RadioButton) findViewById(R.id.workExpressNoLimit);
        //应届生
        graduateRadioBt = (RadioButton) findViewById(R.id.graduateRadioBt);
        //一年工作经验
        oneYearBt = (RadioButton) findViewById(R.id.oneYearBt);
        //三年以内
        inThreeYearBt = (RadioButton) findViewById(R.id.inThreeYearBt);
        //五年以内
        inFiveYearBt = (RadioButton) findViewById(R.id.inFiveYearBt);
        //十年以内
        inTenYear = (RadioButton) findViewById(R.id.inTenYear);
        //十年以上
        outTenYear = (RadioButton) findViewById(R.id.outTenYear);
        //学历没限制
        noLimitStudyRadioBt = (RadioButton) findViewById(R.id.noLimitStudyRadioBt);
        //高中及一下
        highSchoolRadioBt = (RadioButton) findViewById(R.id.highSchoolRadioBt);
        //中专
        technicalSchoolRadioBt = (RadioButton) findViewById(R.id.technicalSchoolRadioBt);
        //大专
        highTechnicalSchoolRadioBt = (RadioButton) findViewById(R.id.highTechnicalSchoolRadioBt);
        //本科
        benKeRadioBt = (RadioButton) findViewById(R.id.benKeRadioBt);
        //硕士及以上
        suoShiRadioBt = (RadioButton) findViewById(R.id.suoShiRadioBt);
        //工资不限
        noLimitMoney = (RadioButton) findViewById(R.id.noLimitMoney);
        //5k
        fiveRMB = (RadioButton) findViewById(R.id.fiveRMB);
        //10k
        tenRMB = (RadioButton) findViewById(R.id.tenRMB);
        //15k
        fivtingRMB = (RadioButton) findViewById(R.id.fivtingRMB);
        //20k
        twtentyRMB = (RadioButton) findViewById(R.id.twtentyRMB);
        //20k+
        upTewtentyRMB = (RadioButton) findViewById(R.id.upTewtentyRMB);
        //重置按钮
        resetBt = (Button) findViewById(R.id.resetBt);
        //派单按钮
        sureBt = (Button) findViewById(R.id.sureBt);
        //试岗时间
        workTime = (EditText) findViewById(R.id.workTime);
        //试岗金额
        workMoney = (EditText) findViewById(R.id.workMoney);
        //工作描述输入框
        workDescribtion = (EditText) findViewById(R.id.workDescribtion);
        //工作状态
        workStatusRadioGroup = (RadioGroup) findViewById(R.id.workStatusRadioGroup);
        //性别
        sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
        //工作经验
        workExpressRadioGroup = (RadioGroup) findViewById(R.id.workExpressRadioGroup);
        //教育
        educationRadioGroup = (RadioGroup) findViewById(R.id.educationRadioGroup);
        //工资
        moneyRadioGroup = (RadioGroup) findViewById(R.id.moneyRadioGroup);

        //点击监听
        paiDanActivityClickListener = new PaiDanActivityClickListener();
        //输入监听
        paiDanTextWatch = new PaiDanTextWatch();
        //单选监听
        paiDanCheckChangeListener = new PaiDanCheckChangeListener();

    }



    /**
     * 设置点击监听s
     */
    private void setRegister(){
        return_image.setOnClickListener(paiDanActivityClickListener);
        workText.setOnClickListener(paiDanActivityClickListener);
        sureBt.setOnClickListener(paiDanActivityClickListener);
        resetBt.setOnClickListener(paiDanActivityClickListener);
        sexNoLimitRadioBt.setOnClickListener(paiDanActivityClickListener);
        manRadioBt.setOnClickListener(paiDanActivityClickListener);
        womenRadioBt.setOnClickListener(paiDanActivityClickListener);
        allWork.setOnClickListener(paiDanActivityClickListener);
        partTimeWork.setOnClickListener(paiDanActivityClickListener);
        workExpressNoLimit.setOnClickListener(paiDanActivityClickListener);
        graduateRadioBt.setOnClickListener(paiDanActivityClickListener);
        oneYearBt.setOnClickListener(paiDanActivityClickListener);
        inThreeYearBt.setOnClickListener(paiDanActivityClickListener);
        inFiveYearBt.setOnClickListener(paiDanActivityClickListener);
        inTenYear.setOnClickListener(paiDanActivityClickListener);
        outTenYear.setOnClickListener(paiDanActivityClickListener);
        noLimitStudyRadioBt.setOnClickListener(paiDanActivityClickListener);
        highSchoolRadioBt.setOnClickListener(paiDanActivityClickListener);
        technicalSchoolRadioBt.setOnClickListener(paiDanActivityClickListener);
        highTechnicalSchoolRadioBt.setOnClickListener(paiDanActivityClickListener);
        benKeRadioBt.setOnClickListener(paiDanActivityClickListener);
        suoShiRadioBt.setOnClickListener(paiDanActivityClickListener);
        cityText.setOnClickListener(paiDanActivityClickListener);
        //单选框监听
        workStatusRadioGroup.setOnCheckedChangeListener(paiDanCheckChangeListener);
        sexRadioGroup.setOnCheckedChangeListener(paiDanCheckChangeListener);
        workExpressRadioGroup.setOnCheckedChangeListener(paiDanCheckChangeListener);
        educationRadioGroup.setOnCheckedChangeListener(paiDanCheckChangeListener);
        moneyRadioGroup.setOnCheckedChangeListener(paiDanCheckChangeListener);

        //设置输入监听
        workMoney.addTextChangedListener(paiDanTextWatch);
        workTime.addTextChangedListener(paiDanTextWatch);
        cityText.addTextChangedListener(paiDanTextWatch);
        workText.addTextChangedListener(paiDanTextWatch);
        workDescribtion.addTextChangedListener(paiDanTextWatch);
    }

    //重置单选框
    private void setReset(){

        sexNoLimitRadioBt.setChecked(true);
        allWork.setChecked(true);
        workExpressNoLimit.setChecked(true);
        noLimitStudyRadioBt.setChecked(true);
        noLimitMoney.setChecked(true);
        workTime.setText("");
        workMoney.setText("");
        cityText.setText("");
        workText.setText("");
        workDescribtion.setText("");

    }

    /**
     * 检查是否选择了工作地区， 职位，试岗薪资，试岗时间， 职位描述
     * 全部输入返回true
     * 否则返回false
     * @return
     */
    private boolean checkInfo(){
        if (!TextUtils.isEmpty(cityText.getText())
                && !TextUtils.isEmpty(cityText.getText())
                && !TextUtils.isEmpty(workMoney.getText())
                && !TextUtils.isEmpty(workTime.getText())
                && !TextUtils.isEmpty(workDescribtion.getText())){
            return true;
        }
        return false;
    }


    private void setParamMap(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.DingWeiFlag:
                if (resultCode == RESULT_OK){
                    city = (City)data.getExtras().getSerializable("region");
                    cityText.setText(city.getName());
                    workBean.setCity(city.getName());
                    workBean.setLatitude(city.getLat());
                    workBean.setLongitude(city.getLog());
                }else {
                    ToastUtil.showToastTwo("未选择城市");
                }
                break;
            case MyApplication.WorkClassificationFlag:
                if (resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    WorkChoiceThreeStage workChoiceThreeStage = (WorkChoiceThreeStage) bundle.getSerializable("workChoiceThreeStage");
                    workText.setText(workChoiceThreeStage.getPositionName());
                    workBean.setRecruitmentPosition(String.valueOf(workChoiceThreeStage.getId()));
                }else {
                    ToastUtil.showToastTwo("未选择工作");
                }
                break;
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void setReturnInfo(boolean returnInfo) {
        if (returnInfo){
            ToastUtil.showToastTwo("派单成功");
            this.finish();
        }else {

        }
    }


    /**
     * 输入监听
     */
    class PaiDanTextWatch implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            //修改输入框颜色
            if (!TextUtils.isEmpty(workMoney.getText().toString())){
                workMoney.setTextColor(getResources().getColor(R.color.blue_green));
                workMoney.setBackground(getResources().getDrawable(R.drawable.radio_check_bg));
                workBean.setTrialPostSalary(workMoney.getText().toString());
            }else {
                workMoney.setTextColor(getResources().getColor(R.color.textColor));
                workMoney.setBackground(getResources().getDrawable(R.drawable.radio_check_false_bg));
                workBean.setTrialPostSalary("");
            }
            if (!TextUtils.isEmpty(workTime.getText().toString())){
                workTime.setTextColor(getResources().getColor(R.color.blue_green));
                workTime.setBackground(getResources().getDrawable(R.drawable.radio_check_bg));
                workBean.setTrialPostTime(workTime.getText().toString());
            }else {
                workTime.setTextColor(getResources().getColor(R.color.textColor));
                workTime.setBackground(getResources().getDrawable(R.drawable.radio_check_false_bg));
                workBean.setTrialPostSalary("");
            }
            if (!TextUtils.isEmpty(workText.getText().toString())){
                workText.setTextColor(getResources().getColor(R.color.blue_green));
                workText.setBackground(getResources().getDrawable(R.drawable.radio_check_bg));
            }else {
                workText.setTextColor(getResources().getColor(R.color.textColor));
                workText.setBackground(getResources().getDrawable(R.drawable.radio_check_false_bg));
            }
            if (!TextUtils.isEmpty(cityText.getText().toString())){
                cityText.setTextColor(getResources().getColor(R.color.blue_green));
                cityText.setBackground(getResources().getDrawable(R.drawable.radio_check_bg));
            }else {
                cityText.setTextColor(getResources().getColor(R.color.textColor));
                cityText.setBackground(getResources().getDrawable(R.drawable.radio_check_false_bg));
            }
            if (!TextUtils.isEmpty(workDescribtion.getText().toString())){
                workDescribtion.setTextColor(getResources().getColor(R.color.blue_green));
                workDescribtion.setBackground(getResources().getDrawable(R.drawable.angle));
                workBean.setJobDescription(workDescribtion.getText().toString());
            }else {
                workDescribtion.setTextColor(getResources().getColor(R.color.textColor));
                workDescribtion.setBackground(getResources().getDrawable(R.drawable.angle_false));
                workBean.setJobDescription("");
            }
        }
    }

    class PaiDanCheckChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getId()) {
                //工作性质
                case R.id.workStatusRadioGroup:
                    if (i == R.id.allWork){
                        workBean.setWorkNature("010");
                    }
                    if (i == R.id.partTimeWork){
                        workBean.setWorkNature("020");
                    }
                    break;
                //性别
                case R.id.sexRadioGroup:
                    if (i == R.id.sexNoLimitRadioBt){
                        workBean.setSex("030");
                    }
                    if (i == R.id.manRadioBt){
                        workBean.setSex("010");
                    }
                    if (i == R.id.womenRadioBt){
                        workBean.setSex("020");
                    }
                    break;
                //工作经验
                case R.id.workExpressRadioGroup:
                    switch (i){
                        case R.id.workExpressNoLimit:
                            workBean.setWorkExperience("010");
                            break;
                        case R.id.graduateRadioBt:
                            workBean.setWorkExperience("020");
                            break;/*
                        case R.id.oneYearBt:
                            workBean.setWorkExperience("030");
                            break;*/
                        case R.id.inThreeYearBt:
                            workBean.setWorkExperience("030");
                            break;
                        case R.id.inFiveYearBt:
                            workBean.setWorkExperience("040");
                            break;
                        case R.id.inTenYear:
                            workBean.setWorkExperience("050");
                            break;
                        case R.id.outTenYear:
                            workBean.setWorkExperience("060");
                            break;
                    }
                    break;
                //教育经历
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
                //工资
                case R.id.moneyRadioGroup:
                    switch (i) {
                        case R.id.noLimitMoney:
                            workBean.setSalaryStandard("010");
                            break;
                        case R.id.fiveRMB:
                            workBean.setSalaryStandard("020");
                            break;
                        case R.id.tenRMB:
                            workBean.setSalaryStandard("030");
                            break;
                        case R.id.fivtingRMB:
                            workBean.setSalaryStandard("040");
                            break;
                        case R.id.twtentyRMB:
                            workBean.setSalaryStandard("050");
                            break;
                        case R.id.upTewtentyRMB:
                            workBean.setSalaryStandard("060");
                            break;
                    }
                    break;

            }
        }
    }


    /**
     * 点击监听类
     */
    class PaiDanActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    PaiDanActivity.this.finish();
                    break;
                //派单按钮
                case R.id.sureBt:
                    //判断数据是否都已经输入
                    if (checkInfo()){
                        Intent intent = new Intent();
                        Bundle workBundle = new Bundle();
                        workBundle.putSerializable("work", workBean);
                        intent.putExtras(workBundle);
                        setResult(RESULT_OK, intent);
                        paiDanAcitivityPresenter.submitPaiDan(workBean);
                    }else {
                        ToastUtil.showToastTwo("请检查您输入的信息");
                    }
                    break;
                case R.id.resetBt:
                    setReset();
                    break;
                //定位按钮 获取定位信息
                case R.id.cityText:
                    Intent intentCity = new Intent(PaiDanActivity.this, CityChoiceActivity.class);
                    PaiDanActivity.this.startActivityForResult(intentCity, MyApplication.DingWeiFlag);
                    break;
                case R.id.workText:
                    WorkCassificationActivity.startWorkCassificationActivity(PaiDanActivity.this, MyApplication.WorkClassificationFlag, WorkCassificationActivity.ENTERPRISE);
                    /*Intent intentWork = new Intent(PaiDanActivity.this, WorkCassificationActivity.class);
                    PaiDanActivity.this.startActivityForResult(intentWork, MyApplication.WorkClassificationFlag);*/
                    break;
                //性别无限制
                case R.id.sexNoLimitRadioBt:
                    ToastUtil.showToastTwo("性别无限制");
                    break;
                //男
                case R.id.manRadioBt:
                    ToastUtil.showToastTwo("性别男");
                    break;
                //女
                case R.id.womenRadioBt:
                    ToastUtil.showToastTwo("性别女");
                    break;
                //工作无限制
                case R.id.allWork:
                    ToastUtil.showToastTwo("全职");
                    break;
                //离职
                case R.id.partTimeWork:
                    ToastUtil.showToastTwo("兼职");
                    break;
                //工作经验无限制
                case R.id.workExpressNoLimit:
                    ToastUtil.showToastTwo("工作经验无限制");
                    break;
                //应届毕业生
                case R.id.graduateRadioBt:
                    ToastUtil.showToastTwo("应届毕业生");
                    break;
                //一年以内
                case R.id.oneYearBt:
                    ToastUtil.showToastTwo("一年以内");
                    break;
                //三年以内
                case R.id.inThreeYearBt:
                    ToastUtil.showToastTwo("三年以内");
                    break;
                //五年以内
                case R.id.inFiveYearBt:
                    ToastUtil.showToastTwo("五年以内");
                    break;
                //十年以内
                case R.id.inTenYear:
                    ToastUtil.showToastTwo("十年以内");
                    break;
                //十年以上
                case R.id.outTenYear:
                    ToastUtil.showToastTwo("十年以上");
                    break;
                //学历无限制
                case R.id.noLimitStudyRadioBt:
                    ToastUtil.showToastTwo("学历无限制");
                    break;
                //高中一下
                case R.id.highSchoolRadioBt:
                    ToastUtil.showToastTwo("高中及以下");
                    break;
                //中专
                case R.id.technicalSchoolRadioBt:
                    ToastUtil.showToastTwo("中专");
                    break;
                //大专
                case R.id.highTechnicalSchoolRadioBt:
                    ToastUtil.showToastTwo("大专");
                    break;
                //本科
                case R.id.benKeRadioBt:
                    ToastUtil.showToastTwo("本科");
                    break;
                //硕士
                case R.id.suoShiRadioBt:
                    ToastUtil.showToastTwo("硕士");
                    break;
                case R.id.noLimitMoney:
                    ToastUtil.showToastTwo("工资不限");
                    break;
                case R.id.fiveRMB:
                    ToastUtil.showToastTwo("5k");
                    break;
                case R.id.tenRMB:
                    ToastUtil.showToastTwo("10k");
                    break;
                case R.id.fivtingRMB:
                    ToastUtil.showToastTwo("15k");
                    break;
                case R.id.twtentyRMB:
                    ToastUtil.showToastTwo("20k");
                    break;
                case R.id.upTewtentyRMB:
                    ToastUtil.showToastTwo("20k+");
                    break;


            }
        }
    }



}

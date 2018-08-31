package com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.BusinessInfo;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BaseActivity.BaseActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessInfomationActivity.IBusinessAcitivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessPresenter.BusinessPresenter;
import com.a51zhipaiwang.worksend.Enterprise.presenter.EnterpriseBusinessPresenter.IBusinessPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessInfomationActivity extends BaseActivity implements IBusinessAcitivity {

    private ImageView logoUpLoadImage;
    private ImageView upLoadVideoImage;
    private ImageView upLoadImageFengMian;
    private EditText companyNameEdit;
    private EditText qiXiaChanPinEdit;
    private EditText companyHangYeEdit;
    private EditText companyGuiMoEdit;
    private TextView companyLocationText;
    private EditText companyDescribtionEdit;
    private Button returnBt;
    private Button sureBt;

    private BusinessInfomationClickListener businessInfomationClickListener;

    private IBusinessPresenter businessPresenter;
    private TextView tilte_text;
    private ImageView return_image;

    private String title;

    private String logoPath = "";

    private String videoPath = "";

    private String fengMianPath = "";

    private BusinessInfo businessInfo;
    private EditText companyLocationDetailEdit;
    private RadioGroup radg_business;
    private RadioButton rdb_listed;
    private RadioButton rdb_not_listed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_infomation);
        init();
        setRegister();
    }

    public static void StartBusinessInfomationActivity(BaseActivity context, String title, BusinessInfo businessInfo){
        Intent intent = new Intent(context, BusinessInfomationActivity.class);
        intent.putExtra("title", title);
        Bundle bundle = new Bundle();
        bundle.putSerializable("businessInfo", businessInfo);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, MyApplication.BusinessInfoChangeFlag);

    }

    private void init(){
        title = getIntent().getStringExtra("title");
        businessInfo = (BusinessInfo) getIntent().getExtras().getSerializable("businessInfo");
        logoUpLoadImage = (ImageView) findViewById(R.id.logoUpLoadImage);
        upLoadVideoImage = (ImageView) findViewById(R.id.upLoadVideoImage);
        upLoadImageFengMian = (ImageView) findViewById(R.id.upLoadImageFengMian);
        companyNameEdit = (EditText) findViewById(R.id.companyNameEdit);
        qiXiaChanPinEdit = (EditText) findViewById(R.id.qiXiaChanPinEdit);
        companyHangYeEdit = (EditText) findViewById(R.id.companyHangYeEdit);
        companyGuiMoEdit = (EditText) findViewById(R.id.companyGuiMoEdit);
        companyLocationText = (TextView) findViewById(R.id.companyLocationText);
        companyDescribtionEdit = (EditText) findViewById(R.id.companyDescribtionEdit);
        companyLocationDetailEdit = (EditText) findViewById(R.id.companyLocationDetailEdit);
        radg_business = (RadioGroup) findViewById(R.id.radg_business);
        rdb_listed = (RadioButton) findViewById(R.id.rdb_listed);
        rdb_not_listed = (RadioButton) findViewById(R.id.rdb_not_listed);
        rdb_not_listed.setChecked(true);
        returnBt = (Button) findViewById(R.id.returnBt);
        sureBt = (Button) findViewById(R.id.sureBt);

        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        tilte_text.setText(title);

        businessInfomationClickListener = new BusinessInfomationClickListener();
        businessPresenter = new BusinessPresenter(this);
    }

    private void setRegister(){
        logoUpLoadImage.setOnClickListener(businessInfomationClickListener);
        upLoadVideoImage.setOnClickListener(businessInfomationClickListener);
        upLoadImageFengMian.setOnClickListener(businessInfomationClickListener);
        companyNameEdit.setOnClickListener(businessInfomationClickListener);
        qiXiaChanPinEdit.setOnClickListener(businessInfomationClickListener);
        companyHangYeEdit.setOnClickListener(businessInfomationClickListener);
        companyGuiMoEdit.setOnClickListener(businessInfomationClickListener);
        companyLocationText.setOnClickListener(businessInfomationClickListener);
        companyDescribtionEdit.setOnClickListener(businessInfomationClickListener);
        returnBt.setOnClickListener(businessInfomationClickListener);
        sureBt.setOnClickListener(businessInfomationClickListener);
        return_image.setOnClickListener(businessInfomationClickListener);
        companyLocationText.setOnClickListener(businessInfomationClickListener);
        radg_business.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rdb_listed.isChecked()){
                    businessInfo.setCompanyNature("010");
                }
                if (rdb_not_listed.isChecked()){
                    businessInfo.setCompanyNature("020");
                }
            }
        });
    }


    @Override
    public void returnSub(boolean info) {
        if (info){
            Intent intent = new Intent();
            intent.setAction(MyApplication.CHANGE_BUSINESS_FLAG);
            /*Bundle bundle = new Bundle();
            bundle.putSerializable("businessInfo", businessInfo);
            intent.putExtras(bundle);*/
            BusinessInfomationActivity.this.sendBroadcast(intent);
            setResult(RESULT_OK);
            this.finish();
        }
    }

    @Override
    public void returnLogoSubmitSuccess(String info) {
        MyLog.e("BusinessInfomationActivity", "returnLogoSubmitSuccess(BusinessInfomationActivity.java:89)" + info);
        try {
            JSONObject logoJson = new JSONObject(info);
            if (logoJson.getString("result").equals("上传成功")){
                logoPath = MyApplication.upLoadPath + logoJson.getString("lj");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
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

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    @Override
    public void stringCoverLocation(Address address) {
        if (address != null){
            businessInfo.setLatitude(address.getLatitude() + "");
            businessInfo.setLongitude(address.getLongitude() + "");
            //如果信息都输入了 则提交信息
            if (checkInfo()){
                setBusinnessInfo();
                subInfo();
            }
        }else {
            ToastUtil.showToastTwo("请输入详细的地址");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UpLoadLog:
                if (resultCode == RESULT_OK){
                    UpLoadFile.UpLoadFileResult(BusinessInfomationActivity.this, data.getData(), new UpLoadFile.UpLoadFileListener() {
                        @Override
                        public void success(String info) {
                            MyLog.e("BusinessInfomationActivity", "success(BusinessInfomationActivity.java:228)" + info);
                            logoUpLoadImage.setImageResource(R.mipmap.up_load_success);
                            returnLogoSubmitSuccess(info);
                        }

                        @Override
                        public void error(String info) {
                            MyLog.e("BusinessInfomationActivity", "error(BusinessInfomationActivity.java:124)" + info);
                        }
                    });
                }
                break;
            case MyApplication.UpLoadVideo:
                if (resultCode == RESULT_OK) {
                    UpLoadFile.UpLoadFileResult(BusinessInfomationActivity.this, data.getData(), new UpLoadFile.UpLoadFileListener() {
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
                    UpLoadFile.UpLoadFileResult(BusinessInfomationActivity.this, data.getData(), new UpLoadFile.UpLoadFileListener() {
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
            case MyApplication.ChoiceCityFlag:
                if (resultCode == RESULT_OK){
                    City city = (City) data.getExtras().getSerializable("region");
                    companyLocationText.setText(city.getName());
                    businessInfo.setLatitude(city.getLat());
                    businessInfo.setLongitude(city.getLog());
                }
                break;
        }
    }


    /**
     * 检查信息是否输入完成
     * @return
     */
    private boolean checkInfo(){
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + companyNameEdit.getText());
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + qiXiaChanPinEdit.getText());
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + companyHangYeEdit.getText());
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + companyGuiMoEdit.getText());
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + companyDescribtionEdit.getText());
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + logoPath);
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + videoPath);
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + fengMianPath);
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + companyLocationText.getText());
        MyLog.e("BusinessInfomationActivity", "checkInfo(BusinessInfomationActivity.java:290)" + companyLocationDetailEdit.getText());
        if (!TextUtils.isEmpty(companyNameEdit.getText())
                && !TextUtils.isEmpty(qiXiaChanPinEdit.getText())
                && !TextUtils.isEmpty(companyHangYeEdit.getText())
                && !TextUtils.isEmpty(companyGuiMoEdit.getText())
                && !TextUtils.isEmpty(companyDescribtionEdit.getText())
                && !TextUtils.isEmpty(logoPath)
                && !TextUtils.isEmpty(videoPath)
                && !TextUtils.isEmpty(fengMianPath)
                && !TextUtils.isEmpty(companyLocationText.getText())
                && !TextUtils.isEmpty(companyLocationDetailEdit.getText())){
            return true;
        }else {
            return false;
        }
    }

    private void setBusinnessInfo(){
        businessInfo.setCompanyIntroduce(companyDescribtionEdit.getText().toString());
        businessInfo.setEnterpriseName(companyNameEdit.getText().toString());
        businessInfo.setProduct(qiXiaChanPinEdit.getText().toString());
        businessInfo.setIndustry(companyHangYeEdit.getText().toString());
        businessInfo.setCompanyScale(companyGuiMoEdit.getText().toString());
        businessInfo.setEnterpriseLogo(logoPath);
        businessInfo.setEnterpriseVideo(videoPath);
        businessInfo.setCol2(fengMianPath);
        businessInfo.setEnterprisePosition(companyLocationText.getText().toString() + companyLocationDetailEdit.getText().toString());
    }

    private void subInfo(){
        businessPresenter.submitBusinessInfo(businessInfo);
    }


    class BusinessInfomationClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.logoUpLoadImage:
                    UpLoadFile.UpLoadFile(BusinessInfomationActivity.this, MyApplication.UpLoadLog, "image/*");
                    break;
                case R.id.upLoadVideoImage:
                    UpLoadFile.UpLoadFile(BusinessInfomationActivity.this, MyApplication.UpLoadVideo, "video/*");
                    break;
                case R.id.upLoadImageFengMian:
                    UpLoadFile.UpLoadFile(BusinessInfomationActivity.this, MyApplication.UpLoadFengMian, "image/*");
                    break;
                case R.id.returnBt:
                    BusinessInfomationActivity.this.finish();
                    break;
                case R.id.sureBt:
                    if (checkInfo()){
                        setBusinnessInfo();
                        subInfo();
                    }else {
                        ToastUtil.showToastTwo("请输入信息");
                    }/*
                    businessInfo.setEnterprisePosition(companyLocationText.getText().toString());
                    businessPresenter.getLocationFromString(companyLocationText.getText().toString());*/
                    break;
                case R.id.return_image:
                    BusinessInfomationActivity.this.finish();
                    break;
                case R.id.companyLocationText:
                    CityChoiceActivity.startCityChoiceActivity(BusinessInfomationActivity.this, MyApplication.ChoiceCityFlag);
                    break;
            }
        }
    }


}

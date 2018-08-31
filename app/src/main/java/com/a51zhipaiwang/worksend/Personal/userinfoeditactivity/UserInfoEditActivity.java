package com.a51zhipaiwang.worksend.Personal.userinfoeditactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.City;
import com.a51zhipaiwang.worksend.Enterprise.Activity.CityChoiceActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.UserInfo;
import com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.contract.IUserInfoEditContract;
import com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.presenter.IUserInfoEditPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoEditActivity extends BaseActivity implements IUserInfoEditContract.View {

    private int CHECK_NAME_ERROR = 1;
    private int CHECK_CITY_ERROR = 2;
    private int CHECK_TIME_ERROR = 3;
    private int CHECK_UPLOAD_ERROR = 4;
    private int CHECK_SUCCESS = 5;

    private UserInfoEditActivityClickListener userInfoEditActivityClickListener;
    private IUserInfoEditPresenter iUserInfoEditPresenter;

    private EditText edx_user_name;
    private TextView tx_user_city;
    private EditText edx_user_brithday;
    private CircleImageView cimg_head;
    private Button btn_edit;
    private TextView tilte_text;
    private ImageView return_image;

    private String sUpImagePath;
    private UserInfo userInfo;

    private String latitude;
    private String longitude;

    public static void StartUserInfoEditActivity(Activity context, Serializable userInfo, int requestCode) {
        Intent intent = new Intent(context, UserInfoEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", userInfo);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);
        init();
        setRegister();
        initUserInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UpLoadLog:
                if (resultCode == RESULT_OK) {
                    //防止为null 异常
                    if (data != null) {
                        //上传图片
                        iUserInfoEditPresenter.upLoadUserImage(data.getData());
                    }
                }
                break;
             case MyApplication.ChoiceCityFlag:
                 if (resultCode == RESULT_OK){
                     City city = (City) data.getExtras().getSerializable("region");
                     latitude = city.getLat();
                     longitude = city.getLog();
                     tx_user_city.setText(city.getName());
                 }
                 break;
        }
    }

    /**
     * 提交用户信息反馈信息
     * @param bReturnInfo
     */
    @Override
    public void submitReturnInfo(boolean bReturnInfo) {
        if (bReturnInfo) {
            ToastUtil.showToastTwo("修改成功");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", userInfo);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            notificationChangeUserImage();
            this.finish();
        }
    }

    /**
     * 文字转地址反馈信息
     * 如果成功则 检查其余信息是否填写完善 如果完善则提交信息，否则提示用户
     *
     * @param address
     */
    @Override
    public void setLocationFromText(Address address) {
        if (address != null) {
            ToastUtil.showToastTwo(address.getCountryName());

        } else {
            ToastUtil.showToastTwo("请输入详细的地址");
        }
    }

    /**
     * 头像上传反馈信息
     *
     * @param sReturnInfo
     */
    @Override
    public void setUpLoadImageReturnInfo(String sReturnInfo) {
        sUpImagePath = MyApplication.upLoadPath + sReturnInfo;
        cimg_head.setImageResource(R.mipmap.up_load_success);
        userInfo.setUserImg(sUpImagePath);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init() {
        iUserInfoEditPresenter = new IUserInfoEditPresenter(this);
        edx_user_name = (EditText) findViewById(R.id.edx_user_name);
        tx_user_city = (TextView) findViewById(R.id.tx_user_city);
        edx_user_brithday = (EditText) findViewById(R.id.edx_user_brithday);
        cimg_head = (CircleImageView) findViewById(R.id.cimg_head);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        userInfo = (UserInfo) getIntent().getExtras().getSerializable("userInfo");
        tilte_text.setText("修改信息");

    }

    private void setRegister() {
        userInfoEditActivityClickListener = new UserInfoEditActivityClickListener();
        return_image.setOnClickListener(userInfoEditActivityClickListener);
        btn_edit.setOnClickListener(userInfoEditActivityClickListener);
        cimg_head.setOnClickListener(userInfoEditActivityClickListener);
        tx_user_city.setOnClickListener(userInfoEditActivityClickListener);
    }

    private void notificationChangeUserImage(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", userInfo);
        intent.putExtras(bundle);
        intent.setAction(MyApplication.CHANGE_USER_IMAGE_FLAG);
        sendBroadcast(intent);
    }

    /**
     * 初始化用户信息 将以前填写的信息  设置
     */
    private void initUserInfo() {
        edx_user_name.setText(userInfo.getUsername());
        tx_user_city.setText(userInfo.getCity());
        edx_user_brithday.setText(userInfo.getBirthday());
        new GlideUtil().GlideImage(userInfo.getUserImg(), UserInfoEditActivity.this, cimg_head, R.drawable.icon);
    }

    /**
     * 判断用户信息
     *
     * @return
     */
    private boolean checkUserInfo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

        if (TextUtils.isEmpty(edx_user_name.getText())) {
            ToastUtil.showToastTwo("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(tx_user_city.getText())) {
            ToastUtil.showToastTwo("请输入详细的地址");
            return false;
        }

        try {
            simpleDateFormat.parse(edx_user_brithday.getText().toString());
        } catch (ParseException e) {
            ToastUtil.showToastTwo("请输入日期格式如2018-1-1");
            return false;
        }

        if (TextUtils.isEmpty(edx_user_brithday.getText()) || edx_user_brithday.getText().length() > 10) {
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }

        if (!edx_user_brithday.getText().toString().matches(MyApplication.DateMatch)){
            ToastUtil.showToastTwo("请输入日期格式如2018-01-01");
            return false;
        }

        if (TextUtils.isEmpty(userInfo.getUserImg())) {
            ToastUtil.showToastTwo("请上传头像");
            return false;
        }
        return true;
    }


    class UserInfoEditActivityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    UserInfoEditActivity.this.finish();
                    break;
                case R.id.btn_edit:
                    //iUserInfoEditPresenter.coverLocationFromText(edx_user_city.getText().toString());
                    if (checkUserInfo()){
                            HashMap hashMap = new HashMap();
                            hashMap.put("userImg", sUpImagePath);
                            hashMap.put("id", userInfo.getId());
                            hashMap.put("username", edx_user_name.getText().toString());
                            hashMap.put("city", tx_user_city.getText().toString());
                            hashMap.put("birthday", edx_user_brithday.getText().toString());
                            hashMap.put("longitude", longitude);
                            hashMap.put("latitude", latitude);
                            userInfo.setUsername(edx_user_name.getText().toString());
                            userInfo.setCity(tx_user_city.getText().toString());
                            userInfo.setBirthday(edx_user_brithday.getText().toString());
                            iUserInfoEditPresenter.submitUserInfo(hashMap);
                    }
                    break;
                case R.id.cimg_head:
                    UpLoadFile.UpLoadFile(UserInfoEditActivity.this, MyApplication.UpLoadLog, "image/*");
                    break;
                case R.id.tx_user_city:
                    CityChoiceActivity.startCityChoiceActivity(UserInfoEditActivity.this, MyApplication.ChoiceCityFlag);
                    break;
            }
        }
    }


}

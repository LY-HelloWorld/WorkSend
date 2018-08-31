package com.a51zhipaiwang.worksend.Personal.userinfoactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.SampleJianLiData;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.entity.UserInfo;
import com.a51zhipaiwang.worksend.Personal.userinfoactivity.presenter.IUserInfoPresenter;
import com.a51zhipaiwang.worksend.Personal.userinfoactivity.presenter.UserInfoPresenterImpl;
import com.a51zhipaiwang.worksend.Personal.userinfoactivity.view.IUserInfoView;
import com.a51zhipaiwang.worksend.Personal.userinfoeditactivity.UserInfoEditActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/7/20
 *     desc   : 用户信息
 *     version: 1.0
 *
        常量
        字段
        构造函数
        重写函数和回调
        公有函数
        私有函数
        内部类或接口
 * </pre>
 */
public class UserInfoActivity extends BaseActivity implements IUserInfoView{

    private UserInfoActivityClickListener userInfoActivityClickListener;
    private IUserInfoPresenter iUserInfoPresenter;

    private TextView tx_user_name;
    private TextView tx_user_city;
    private TextView tx_user_brithday;
    private CircleImageView cimg_head;
    private TextView tilte_text;
    private ImageView return_image;
    private Button btn_edit;

    private UserInfo userInfo;

    private GlideUtil glideUtil;

    public static void StartUserInfoActivity(Context context){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
        setRegister();
        iUserInfoPresenter.getUserInfo(new HashMap());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UserInfoEditFlag:
                if (resultCode == RESULT_OK){
                    userInfo = (UserInfo) data.getExtras().getSerializable("userInfo");
                    setUserInfo(userInfo);
                }
                break;
        }
    }

    @Override
    public void submitReturnInfo(boolean bReturnInfo) {

    }

    @Override
    public void setUserInfo(Object object) {
        if (object != null){
            userInfo = (UserInfo) object;
            glideUtil.GlideImage(userInfo.getUserImg(), this, cimg_head);
            tx_user_name.setText(userInfo.getUsername());
            tx_user_brithday.setText(userInfo.getBirthday());
            tx_user_city.setText(userInfo.getCity());
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(){
        iUserInfoPresenter = new UserInfoPresenterImpl(this);
        glideUtil = new GlideUtil();
        tx_user_name = (TextView) findViewById(R.id.tx_user_name);
        tx_user_city = (TextView) findViewById(R.id.tx_user_city);
        tx_user_brithday = (TextView) findViewById(R.id.tx_user_brithday);
        cimg_head = (CircleImageView) findViewById(R.id.cimg_head);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        return_image = (ImageView) findViewById(R.id.return_image);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        tilte_text.setText("个人资料");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRegister(){
        userInfoActivityClickListener = new UserInfoActivityClickListener();
        btn_edit.setOnClickListener(userInfoActivityClickListener);
        return_image.setOnClickListener(userInfoActivityClickListener);
    }


    class UserInfoActivityClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.return_image:
                    UserInfoActivity.this.finish();
                    break;
                case R.id.btn_edit:
                    UserInfoEditActivity.StartUserInfoEditActivity(UserInfoActivity.this, userInfo, MyApplication.UserInfoEditFlag);
                    break;
            }
        }
    }


}

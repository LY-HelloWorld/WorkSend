package com.a51zhipaiwang.worksend.CommonActivity.logchoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.a51zhipaiwang.worksend.CommonActivity.base.BaseActivity;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.presenter.ILogChoicePresenter;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.presenter.LogChoicePresenter;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.view.ILogChoiceView;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/7/19
 *     desc   : 登录选择activity
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
public class LogChoiceActivity extends BaseActivity implements ILogChoiceView {

    private ILogChoicePresenter iLogChoicePresenter;
    private LogChoiceClickListener logChoiceClickListener;

    private ImageView img_need_work;
    private ImageView img_recruit;

    public static void startLogChoiceActivity(Context context){
        context.startActivity(new Intent(context, LogChoiceActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_choice);
        init();
        setRegister();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }



    private void init(){
        iLogChoicePresenter = new LogChoicePresenter(this);
        logChoiceClickListener = new LogChoiceClickListener();
        img_need_work = (ImageView) findViewById(R.id.img_need_work);
        img_recruit = (ImageView) findViewById(R.id.img_recruit);
    }

    private void setRegister(){
        img_need_work.setOnClickListener(logChoiceClickListener);
        img_recruit.setOnClickListener(logChoiceClickListener);
    }

    class LogChoiceClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_need_work:
                    iLogChoicePresenter.workerChoice(LogChoiceActivity.this);
                    break;
                case R.id.img_recruit:
                    iLogChoicePresenter.recruitChoice(LogChoiceActivity.this);
                    break;
            }
        }
    }

}

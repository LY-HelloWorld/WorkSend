package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Bean.WorkChoiceThreeStage;
import com.a51zhipaiwang.worksend.Enterprise.Activity.VideoJianLIActivity;
import com.a51zhipaiwang.worksend.Enterprise.Activity.WorkClassficationActivity.WorkCassificationActivity;
import com.a51zhipaiwang.worksend.Enterprise.Fragment.HomeFragment.HomeFragment;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;


import static android.app.Activity.RESULT_OK;

public class VideoDialogFragment extends DialogFragment {



    private MyVideoDialogFragmentClickListener myVideoDialogFragmentClickListener;
    private VideoFragmentTextWatcher videoFragmentTextWatcher;

    private Button sureBt;
    private TextView workText;
    private WorkChoiceThreeStage workChoiceThreeStage;
    private RadioButton manRadioBt;
    private RadioButton wemanRadioBt;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    /**
     * 初始化 弹框布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.video_search_alert, null);

    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        Window window = getDialog().getWindow();
        DisplayMetrics metric = new DisplayMetrics();
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        defaultDisplay.getMetrics(metric);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = (int) (375 * metric.density);
        layoutParams.width = (int) (250 * metric.density);
        window.setAttributes(layoutParams);

        init(view);
        setRegister();
    }

    /**
     * 初始化
     * @param view
     */
    private void init(View view){
        myVideoDialogFragmentClickListener = new MyVideoDialogFragmentClickListener();
        videoFragmentTextWatcher = new VideoFragmentTextWatcher();
        //搜搜按钮
        sureBt = (Button) view.findViewById(R.id.sureBt);
        //工作
        workText = (TextView) view.findViewById(R.id.workText);
        manRadioBt = (RadioButton) view.findViewById(R.id.manRadioBt);
        wemanRadioBt = (RadioButton) view.findViewById(R.id.wemanRadioBt);
    }


    /**
     * 设置监听
     */
    private void setRegister(){
        workText.setOnClickListener(myVideoDialogFragmentClickListener);
        sureBt.setOnClickListener(myVideoDialogFragmentClickListener);
        workText.addTextChangedListener(videoFragmentTextWatcher);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.WorkClassificationFlag:
                if (resultCode == RESULT_OK){
                    workChoiceThreeStage = (WorkChoiceThreeStage) data.getExtras().getSerializable("workChoiceThreeStage");
                    workText.setText(workChoiceThreeStage.getPositionName());
                }else {
                    ToastUtil.showToastTwo("未选择工作");
                }
                break;
        }
    }


    class MyVideoDialogFragmentClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.workText:
                    WorkCassificationActivity.startWorkCassificationActivityFromFragment(VideoDialogFragment.this, MyApplication.WorkClassificationFlag, WorkCassificationActivity.ENTERPRISE);
                    //WorkCassificationActivity.startWorkCassificationActivity((AppCompatActivity) getActivity(), MyApplication.WorkClassificationFlag, WorkCassificationActivity.ENTERPRISE);
                    /*Intent intentWork = new Intent(getActivity(), WorkCassificationActivity.class);
                    getActivity().startActivityFromFragment(VideoDialogFragment.this, intentWork, MyApplication.WorkClassificationFlag);*/
                    //getActivity().startActivityForResult();
                    break;
                case R.id.sureBt:
                    if (workChoiceThreeStage == null){
                        ToastUtil.showToastTwo("未选择工作");
                        break;
                    }
                    VideoDialogFragment.this.dismiss();
                    if (manRadioBt.isChecked()){
                        ((VideoJianLIActivity)getActivity()).screenInfo(String.valueOf(workChoiceThreeStage.getId()), "010");
                    }else {
                        ((VideoJianLIActivity)getActivity()).screenInfo(String.valueOf(workChoiceThreeStage.getId()), "020");
                    }
                    break;
            }
        }
    }

    class VideoFragmentTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            //修改输入框颜色
            if (!TextUtils.isEmpty(workText.getText().toString())){
                workText.setTextColor(getResources().getColor(R.color.blue_green));
                workText.setBackground(getResources().getDrawable(R.drawable.radio_check_bg));
            }else {
                workText.setTextColor(getResources().getColor(R.color.textColor));
                workText.setBackground(getResources().getDrawable(R.drawable.radio_check_false_bg));
            }
        }
    }
}

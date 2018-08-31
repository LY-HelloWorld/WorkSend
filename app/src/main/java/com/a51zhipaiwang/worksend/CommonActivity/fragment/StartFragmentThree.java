package com.a51zhipaiwang.worksend.CommonActivity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.a51zhipaiwang.worksend.CommonActivity.base.BaseFragment;
import com.a51zhipaiwang.worksend.CommonActivity.logchoice.LogChoiceActivity;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.GlideUtil;
import com.a51zhipaiwang.worksend.Utils.ReleaseResourceUtil.ReleaseResourceUtil;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/29
 *     desc   :
 *     version: 1.0
 * 常量
 * 字段
 * 构造函数
 * 重写函数和回调
 * 公有函数
 * 私有函数
 * 内部类或接口
 * </pre>
 */
public class StartFragmentThree extends BaseFragment{


    private Button btn_come;
    private ImageView img_start;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_three, null);
        init(view);
        setRegister();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ReleaseResourceUtil.ReleaseImage(img_start);
    }

    private void init(View view){
        btn_come = (Button) view.findViewById(R.id.btn_come);
        img_start = (ImageView) view.findViewById(R.id.img_start);
    }

    private void setRegister(){
        btn_come.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogChoiceActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }

}

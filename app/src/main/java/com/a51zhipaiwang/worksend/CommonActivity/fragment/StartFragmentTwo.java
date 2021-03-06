package com.a51zhipaiwang.worksend.CommonActivity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a51zhipaiwang.worksend.CommonActivity.base.BaseFragment;
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
public class StartFragmentTwo extends BaseFragment{


    private GlideUtil glideUtil;
    private ImageView img_start;
    private LinearLayout lv_bg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_two, null);
        init(view);
        setRegister();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ReleaseResourceUtil.ReleaseImage(img_start);
    }

    @SuppressLint("ResourceAsColor")
    private void init(View view){
        glideUtil = new GlideUtil();
        img_start = (ImageView) view.findViewById(R.id.img_start);
        lv_bg = (LinearLayout) view.findViewById(R.id.lv_bg);
    }

    private void setRegister(){
        glideUtil.GlideImage(R.drawable.start_two, getActivity(), img_start);
    }

}

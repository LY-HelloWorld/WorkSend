package com.a51zhipaiwang.worksend.Enterprise.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Enterprise.Activity.BusinessIdentifyActivity;
import com.a51zhipaiwang.worksend.Enterprise.network.EnterpriseStringRequest;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class IdentifyFragment extends BaseFragment {

    private String upLoadImage;
    private IdentifyFragmentClickListener identifyFragmentClickListener;

    private EditText edx_business_name;
    private ImageView img_up_load;
    private Button btn_sure;
    private TextView tilte_text;
    private ImageView return_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_identify, null);
        init(view);
        setRegister();
        return view;
    }

    public void setImagepath(String imagepath){
        MyLog.e("IdentifyFragment", "setImagepath(IdentifyFragment.java:42)" + imagepath);
        upLoadImage = imagepath;
        img_up_load.setImageResource(R.drawable.up_load_success_square);
    }

    private void init(View view){
        edx_business_name = (EditText) view.findViewById(R.id.edx_business_name);
        img_up_load = (ImageView) view.findViewById(R.id.img_up_load);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        //tilte_text = (TextView) view.findViewById(R.id.tilte_text);
        //return_image = (ImageView) view.findViewById(R.id.return_image);
    }

    private void setRegister(){
        identifyFragmentClickListener = new IdentifyFragmentClickListener();
        img_up_load.setOnClickListener(identifyFragmentClickListener);
        btn_sure.setOnClickListener(identifyFragmentClickListener);
    }

    private boolean checkInfo() {
        if (TextUtils.isEmpty(edx_business_name.getText())) {
            ToastUtil.showToastTwo("请输入正确的公司名称");
            return false;
        }
        if (TextUtils.isEmpty(upLoadImage)) {
            ToastUtil.showToastTwo("请上传执照照片");
            return false;
        }
        return true;
    }

    class IdentifyFragmentClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_up_load:
                    UpLoadFile.UpLoadFile(getActivity(), MyApplication.UpLoadLog, "image/*");
                    break;
                case R.id.btn_sure:
                    if (checkInfo()){
                        HashMap hashMap = new HashMap();
                        hashMap.put("enterpriseName", edx_business_name.getText().toString());
                        hashMap.put("businessLicense", upLoadImage);
                        MyApplication.requestQueue.add(new EnterpriseStringRequest(Request.Method.POST,
                                MyApplication.path + "api/prisesas/updEC.do",
                                hashMap,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getString("code").equals("success")){
                                                JSONObject infoJson = new JSONObject(jsonObject.getString("info"));
                                                if (infoJson.getString("success").equals("操作成功")){
                                                    ToastUtil.showToastTwo("操作成功");
                                                    Intent intent = new Intent();
                                                    intent.setAction(MyApplication.IDENTIFY_UPLOAD_SUCCESS);
                                                    MyLog.e("IdentifyFragmentClickListener", "onResponse(IdentifyFragmentClickListener.java:112)" + "send");
                                                    getActivity().sendBroadcast(intent);
                                                }
                                            }
                                        } catch (Exception e) {
                                            ToastUtil.showToastTwo("操作失败");
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        ToastUtil.showToastTwo("操作失败");
                                    }
                                }));
                    }
                    break;
            }
        }
    }


}

package com.a51zhipaiwang.worksend.Personal.editarticlactivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Personal.base.BaseActivity;
import com.a51zhipaiwang.worksend.Personal.base.BaseFragment;
import com.a51zhipaiwang.worksend.Personal.editarticlactivity.editarticlcontract.EditAriticlContract;
import com.a51zhipaiwang.worksend.Personal.editarticlactivity.editarticlpresenter.EditAriticlPresenter;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditArtticlActivity extends BaseActivity implements EditAriticlContract.View{

    private EditArticlActivityClicklistener editArticlActivityClicklistener;
    private EditAriticlContract.Presenter presenter;

    private String firstImagePath;
    private String secondImagePath;

    private EditText edx_ariticl_content;
    private EditText edx_title;
    private Button btn_send;
    private ImageView img_upload_first;
    private ImageView img_upload_two;
    private TextView tilte_text;
    private ImageView return_image;

    public static void startEditArtticlActivity(BaseFragment fragment, int requestCode){
        fragment.startActivityForResult(new Intent(fragment.getActivity(), EditArtticlActivity.class), requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artticl);
        init();
        setRegister();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MyApplication.UpLoad:
                if (resultCode == RESULT_OK){
                    presenter.upLoadUserImage(data.getData(), FIRSTIMAGE);
                }
                break;
            case MyApplication.UpLoadLog:
                if (resultCode == RESULT_OK){
                    presenter.upLoadUserImage(data.getData(), SECONDIMAGE);
                }
                break;
        }
    }


    @Override
    public void returnUploadImage(String path, int type) {
        if (type == ERROR){
            ToastUtil.showToastTwo("上传失败！请检查您的网络连接");
            return;
        }
        if (type == FIRSTIMAGE){
            firstImagePath = MyApplication.upLoadPath + path;
            img_upload_first.setImageResource(R.drawable.up_load_success_square);
            return;
        }
        if (type == SECONDIMAGE){
            secondImagePath = MyApplication.upLoadPath + path;
            img_upload_two.setImageResource(R.drawable.up_load_success_square);
        }
    }

    @Override
    public void returnUpLoadArticl(boolean bUpLoadAricalReturn) {
        if (bUpLoadAricalReturn){
            ToastUtil.showToastTwo("文章发布成功");
            setResult(RESULT_OK);
            this.finish();
        }else {
            ToastUtil.showToastTwo("文章发布失败！请检查您的网络连接");
        }
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void closeLoadingDialog() {

    }

    private void init(){
        presenter = new EditAriticlPresenter(this);
        edx_ariticl_content = (EditText) findViewById(R.id.edx_ariticl_content);
        edx_title = (EditText) findViewById(R.id.edx_title);
        btn_send = (Button) findViewById(R.id.btn_send);
        img_upload_first = (ImageView) findViewById(R.id.img_upload_first);
        img_upload_two = (ImageView) findViewById(R.id.img_upload_two);
        tilte_text = (TextView) findViewById(R.id.tilte_text);
        tilte_text.setText("编辑文章");
        return_image = (ImageView) findViewById(R.id.return_image);
    }

    private void setRegister(){
        editArticlActivityClicklistener = new EditArticlActivityClicklistener();
        btn_send.setOnClickListener(editArticlActivityClicklistener);
        img_upload_first.setOnClickListener(editArticlActivityClicklistener);
        img_upload_two.setOnClickListener(editArticlActivityClicklistener);
        return_image.setOnClickListener(editArticlActivityClicklistener);
    }

    private boolean checkInfo(){
        if (!TextUtils.isEmpty(edx_title.getText()) &&
                !TextUtils.isEmpty(edx_ariticl_content.getText()) &&
                !TextUtils.isEmpty(firstImagePath)){
            return true;
        }else {
            return false;
        }
    }

    private HashMap setMap(){
        HashMap hashMap = new HashMap();
        hashMap.put("title", edx_title.getText().toString());
        hashMap.put("content", edx_ariticl_content.getText().toString());
        hashMap.put("picture1", firstImagePath);
        hashMap.put("picture2", secondImagePath);
        return hashMap;
    }

    class EditArticlActivityClicklistener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_send:
                    if (checkInfo()){
                        presenter.upLoadAriticl(setMap());
                    }
                    break;
                case R.id.img_upload_two:
                    UpLoadFile.UpLoadFile(EditArtticlActivity.this, MyApplication.UpLoadLog, "image/*");
                    break;
                case R.id.img_upload_first:
                    UpLoadFile.UpLoadFile(EditArtticlActivity.this, MyApplication.UpLoad, "image/*");
                    break;
                case R.id.return_image:
                    EditArtticlActivity.this.finish();
                    break;
            }
        }
    }

}

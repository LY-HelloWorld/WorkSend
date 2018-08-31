package com.a51zhipaiwang.worksend.Utils.UpLoadFile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.Utils.GetTime.GetTime;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.GetFilePath;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;

public class UpLoadFile {

    //基本路径
    public static String HOST = MyApplication.upLoadPath;

    private static final String TAG = "UpLoadFile";

    private static String type = "";


    public static void UpLoadFile(Activity context, int requestCode, String type){
        UpLoadFile.type = type;
        checkPermisstionReadExTernalStorage(context, requestCode, type);
    }

    /**
     * ActivityResult 回调
     * @param context
     * @param fileUri
     */
    public static void UpLoadFileResult(Context context, Uri fileUri, UpLoadFileListener upLoadFileListener){
        //上传
        UpLoad(GetFilePath.getPath(context, fileUri), upLoadFileListener);
    }


    public static void UpLoad(String path, final UpLoadFileListener upLoadFileListener){
        if (TextUtils.isEmpty(path)){
            Log.e(TAG, "path ======   null");
            return;
        }
        File file = new File(path);
        if (type.equals("image/*")){
            if (file.length() > 5 * 1024 * 1024){
                ToastUtil.showToastTwo("图片不能超过5M");
                return;
            }
        }else if (type.equals("video/*")){
            if (file.length() > 50 * 1024 * 1024){
                ToastUtil.showToastTwo("视频不能超过50M");
                return;
            }
        }
        MyLog.e("UpLoadFile", "UpLoad(UpLoadFile.java:58)" + file.length());
        //实例化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //
        upLoadFileInterface upLoadFileInterface = retrofit.create(upLoadFileInterface.class);
        //初始化网络请求
        //请求体
        RequestBody photoBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MyLog.e("UpLoadFile", "UpLoad(UpLoadFile.java:85)" + file.getName());
        String[] strings = file.getName().split("\\.");
        String fileIndex = strings[strings.length - 1];
        MyLog.e("UpLoadFile", "UpLoad(UpLoadFile.java:88)" + fileIndex);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", GetTime.getTime() + "." + fileIndex, photoBody);
        Call<UpJson> call1 = upLoadFileInterface.upLoadFile(photo);
        call1.enqueue(new Callback<UpJson>() {
            @Override
            public void onResponse(Call<UpJson> call, Response<UpJson> response) {
                MyLog.e("UpLoadFile", "onResponse(UpLoadFile.java:74)" + response.body().getInfo());
                upLoadFileListener.success(response.body().getInfo());
            }

            @Override
            public void onFailure(Call<UpJson> call, Throwable t) {
                MyLog.e("UpLoadFile", "onFailure(UpLoadFile.java:81)" + t.getMessage());
                upLoadFileListener.error(t.getMessage());
                ToastUtil.showToastTwo("上传失败!");

            }
        });
    }



    private static void checkPermisstionReadExTernalStorage(final Activity context, final int RequestCode, final String type){

        RxPermissions.getInstance(context)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)//多个权限用","隔开
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Main2Activity", "onCompleted(Main2Activity.java:47)" + "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Main2Activity", "onError(Main2Activity.java:52)" + e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.e("Main2Activity", "onNext(Main2Activity.java:57)" + aBoolean);
                        if (aBoolean){
                            Intent intent = new Intent();
                            intent.setType(type);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            context.startActivityForResult(intent,
                                    RequestCode);
                        }else {
                            Toast.makeText(context, "请开启读取相册视频权限", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public interface UpLoadFileListener{

        public void success(String info);

        public void error(String info);

    }



}

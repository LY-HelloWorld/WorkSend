package com.a51zhipaiwang.worksend.Utils.UpLoadFile;

import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpJson;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface upLoadFileInterface {

    @Multipart
    @POST("api/file/upload.do")
    Call<UpJson> upLoadFile(@Part MultipartBody.Part photo);

}

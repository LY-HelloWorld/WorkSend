package com.a51zhipaiwang.worksend.Personal.editarticlactivity.editarticlcontract;

import android.content.Context;
import android.net.Uri;

import com.a51zhipaiwang.worksend.Personal.base.IBaseActivityView;
import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.a51zhipaiwang.worksend.Utils.UpLoadFile.UpLoadFile;

import java.util.Map;

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
public interface EditAriticlContract {
    interface Model {
        public void upLoadImage(Context context, Uri uri, UpLoadFile.UpLoadFileListener upLoadFileListener);

        public void upLoadAriticl(Map map, IBaseCallBack iBaseCallBack);

    }

    interface View extends IBaseActivityView{

        public final int ERROR = 0;

        public final int FIRSTIMAGE = 1;

        public final int SECONDIMAGE = 2;

        public void returnUploadImage(String path, int type);

        public void returnUpLoadArticl(boolean bUpLoadAricalReturn);

    }

    interface Presenter {

        //上传图片
        public void upLoadUserImage(Uri uri, int type);

        public void upLoadAriticl(Map map);

    }
}

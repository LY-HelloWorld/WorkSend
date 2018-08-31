package worksend.a51zhipaiwang.com.worksend.Personal.userinfoactivity.model;

import com.a51zhipaiwang.worksend.Personal.base.IBaseCallBack;
import com.android.volley.Response;

import java.util.Map;

/**
 * <pre>
 *     author : ly
 *     e-mail : 891682474@qq.com
 *     time   : 2018/07/19
 *     desc   : 用户信息提交 修改 Model
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
public interface IUserInfoModel {

    public void submit(Object userInfo, Response.Listener<String> listener, Response.ErrorListener errorListener);

    public void getUserInfo(Map map, IBaseCallBack iBaseCallBack);

}

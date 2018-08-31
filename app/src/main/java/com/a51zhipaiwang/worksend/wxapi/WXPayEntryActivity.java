package com.a51zhipaiwang.worksend.wxapi;

import com.a51zhipaiwang.worksend.Application.MyApplication;
import com.a51zhipaiwang.worksend.R;
import com.a51zhipaiwang.worksend.Utils.MyLog;
import com.a51zhipaiwang.worksend.Utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;
	private static final String APP_ID = MyApplication.APP_ID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, APP_ID, false);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}



	/**
	 * 得到支付结果回调
	 */
	@Override
	public void onResp(BaseResp resp) {
		MyLog.e("WXPayEntryActivity", "onResp(WXPayEntryActivity.java:51)" + resp.getType());
		MyLog.e("WXPayEntryActivity", "onResp(WXPayEntryActivity.java:53)" + resp.errCode);
		if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
			switch (resp.errCode) {
				case BaseResp.ErrCode.ERR_OK://成功
					ToastUtil.showToastTwo("支付成功");
					Intent intent = new Intent();
					intent.setAction(MyApplication.RECHARGE_SUCCESS);
					sendBroadcast(intent);
					WXPayEntryActivity.this.finish();
					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL://取消支付
					ToastUtil.showToastTwo("取消支付");
					WXPayEntryActivity.this.finish();
					break;
				case BaseResp.ErrCode.ERR_COMM:// -1
					ToastUtil.showToastTwo("支付失败");
					WXPayEntryActivity.this.finish();
					break;
			}
		}
	}
}
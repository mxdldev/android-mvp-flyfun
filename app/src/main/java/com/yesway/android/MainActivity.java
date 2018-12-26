package com.yesway.android;

import com.yesway.android.base.BaseActivity;
import com.yesway.android.base.BaseMvpActivity;
import com.yesway.android.net.ApiManager;
import com.yesway.android.net.dto.response.LoginResponse;
import com.yesway.android.net.http.Response;
import com.yesway.android.net.model.IUserModel;
import com.yesway.android.view.ExpandableTextView;
import com.yesway.android.view.RotateProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends BaseActivity {

	private RotateProgressDialog mRotateProgressDialog;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ExpandableTextView expandableTextView = findViewById(R.id.expand_text_view);
		expandableTextView.setText("我们为全球首个人工智能区块链数字资产交易平台（全球前十）提供全面技术支持服务\n"
				+ "公司以区块链技术开发应用为核心业务方向，获得一线资本千万美元的投资，全力打造全球区块链行业中，安全、透明的区块链资产技术服务平台是我们的企业使命");

//		mRotateProgressDialog = new RotateProgressDialog();
//		mRotateProgressDialog.show(getSupportFragmentManager(), "tag");
//		mHandler = new Handler();
//		mHandler.postDelayed(getMytag(), 1000);
		View viewById = findViewById(R.id.txt_value);

	}

	private Runnable getMytag() {
		return new Runnable() {
			@Override
			public void run() {
				int value = (int) (Math.random() * 100);
				mRotateProgressDialog.setProgress(value);
				mHandler.postDelayed(getMytag(), 1000);
			}
		};
	}

	@Override
	public void initView(Bundle savedInstanceState) {

	}

	@Override
	public void initData() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_meun, menu);
		return super.onCreateOptionsMenu(menu);
	}
}

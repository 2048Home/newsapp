package com.example.base;

import com.example.api.API;
import com.example.newapp.AddAddressActivity;
import com.example.newapp.R;
import com.example.service.MyService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity implements OnClickListener {
	private TextView tv_title;
	private TextView tv_add;
	private ImageView iv_back;

	public void initTitle() {
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_add = (TextView) findViewById(R.id.tv_add);
		iv_back = (ImageView) findViewById(R.id.iv_back);

//		tv_add.setOnClickListener(this);
		iv_back.setOnClickListener(this);
	}

	public void setTitle(String str) {
		if (!TextUtils.isEmpty(str)) {
			tv_title.setText(str);
		}
	}

	public void setImageVisiable() {
		if (iv_back.getVisibility() != View.VISIBLE) {
			iv_back.setVisibility(View.VISIBLE);
		}
	}

	public void setAddVisiable() {
		if (tv_add.getVisibility() != View.VISIBLE) {
			tv_add.setVisibility(View.VISIBLE);
		}
	}

	public void setImageInvisiable() {
		if (iv_back.getVisibility() == View.VISIBLE) {
			iv_back.setVisibility(View.GONE);
		}
	}

	public void setAddInvisiable() {
		if (tv_add.getVisibility() == View.VISIBLE) {
			tv_add.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.iv_back:
			this.finish();
			break;

		default:
			break;
		}
	}
	
	
	
	/**
	 * 登录
	 * @param paramContext
	 * @param url
	 * @param name
	 * @param password
	 * @param paramMessenger
	 */
	public void get_login_in(Context paramContext,String url,	String name ,String password,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("name", name);
		intent.putExtra("password", password);
		intent.putExtra("api", API.LOGIN_API);
		intent.putExtra(API.LOGIN_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	/**
	 * 注册
	 * @param paramContext
	 * @param url
	 * @param name
	 * @param password
	 * @param address
	 * @param paramMessenger
	 */
	public void get_register_in(Context paramContext,String url,	String name ,String password,String address,String forgetAnswer,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("name", name);
		intent.putExtra("password", password);
		intent.putExtra("address", address);
		intent.putExtra("forgetAnswer", forgetAnswer);
		intent.putExtra("api", API.REGISTER_API);
		intent.putExtra(API.REGISTER_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	
	
	/**
	 * 修改密码
	 * @param paramContext
	 * @param url
	 * @param name
	 * @param password
	 * @param againpassword
	 * @param paramMessenger
	 */
	public void get_change_password(Context paramContext,String url,	String name ,String password,String againpassword,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("name", name);
		intent.putExtra("password", password);
		intent.putExtra("againpassword", againpassword);
		intent.putExtra("api", API.ChangPassword_API);
		intent.putExtra(API.ChangPassword_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	
	
	/**
	 * 我的订单
	 * @param paramContext
	 * @param url
	 * @param userId
	 * @param paramMessenger
	 */
	public void get_myorder(Context paramContext,String url,	String userId, Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("userId", userId);
		intent.putExtra("api", API.FinishOrderOrder_API);
		intent.putExtra(API.FinishOrderOrder_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	/**
	 * 修改地址
	 * @param paramContext
	 * @param url
	 * @param name
	 * @param password
	 * @param paramMessenger
	 */
	public void get_change_address(Context paramContext,String url,	String name ,String address,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("name", name);
		intent.putExtra("address", address);
		intent.putExtra("api", API.CHANGE_ADDRESS_API);
		intent.putExtra(API.CHANGE_ADDRESS_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	/**
	 * 获取我的地址
	 * @param paramContext
	 * @param url
	 * @param name
	 * @param paramMessenger
	 */
	public void get_address(Context paramContext,String url,	String name ,Messenger paramMessenger){
		Intent intent=new Intent(paramContext,MyService.class);
		intent.putExtra("url", url);
		intent.putExtra("name", name);
		intent.putExtra("api", API.ADDRESS_API);
		intent.putExtra(API.ADDRESS_MESSAGE, paramMessenger);
		paramContext.startService(intent);
		
	}
	
	

}

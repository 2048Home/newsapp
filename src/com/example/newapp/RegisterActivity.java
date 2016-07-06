package com.example.newapp;

import java.io.ObjectInputStream.GetField;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.myapplication.MyApplication;
import com.example.utils.InfoUtils;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	/**
	 * 返回键
	 */
	private TextView tv_back;
	/**
	 * 用户名
	 */
	private EditText et_username;
	/**
	 * 密码
	 */
	private EditText et_password;
	/**
	 * 登录按钮
	 */
	private Button login_in;
	/**
	 * 地址信息
	 */	
	private EditText et_address;
	SharedPreferences userSp;
	Editor edit;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				Toast.makeText(RegisterActivity.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				  if(msg.obj.equals("error")){
		        	  Toast.makeText(RegisterActivity.this, InfoUtils.LOGIN_ERROR, Toast.LENGTH_SHORT).show();
		          }else {
		        	  Toast.makeText(RegisterActivity.this, InfoUtils.LOGIN_SUCCESS, Toast.LENGTH_SHORT).show();
		        	  edit.putString("username", et_username.getText().toString().trim());
		        	  edit.putString("password", et_password.getText().toString().trim());
		        	  edit.putBoolean("isLogin", true);
		        	  edit.commit();
		        	  finish();
		          }
				
				
				break;

			case InfoUtils.INFO_ERROR:
				Toast.makeText(RegisterActivity.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;
			}

		};
	};
	


	 

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO 自动生成的方法存根
		super.onCreate(arg0);
		setContentView(R.layout.register);
		initView();
	}

	private void initView() {
		// TODO 自动生成的方法存根
		userSp=getSharedPreferences("UserInfo", MODE_PRIVATE);
		edit=userSp.edit();
		tv_back = (TextView) findViewById(R.id.tv_back);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_address = (EditText) findViewById(R.id.et_address);
		login_in = (Button) findViewById(R.id.login_in);

		tv_back.setOnClickListener(this);
		et_username.setOnClickListener(this);
		et_password.setOnClickListener(this);
		login_in.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.tv_back:
			this.finish();
			break;
		case R.id.et_username:

			break;
		case R.id.et_password:

			break;
		case R.id.login_in:
			String url = API.REGISTER_URL;
			try {
				get_register_in(RegisterActivity.this, url, et_username.getText()
						.toString().trim(),
						et_password.getText().toString().trim(), URLEncoder.encode(et_address
								.getText().toString().trim(),"UTF-8"), "", new Messenger(
								handler));
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
		
			break;

		default:
			break;
		}
	}

}

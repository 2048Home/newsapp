package com.example.newapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.utils.InfoUtils;

public class ModifyPasswordActivity extends BaseActivity implements
		OnClickListener {

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
	 * 修改密码
	 */
	private EditText et_modify_password;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				Toast.makeText(ModifyPasswordActivity.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
				break;

			case InfoUtils.INFO_ERROR:

				break;

			}
		};
	};

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO 自动生成的方法存根
		super.onCreate(arg0);
		setContentView(R.layout.modify_password);
		initView();
	}

	private void initView() {
		// TODO 自动生成的方法存根
		tv_back = (TextView) findViewById(R.id.tv_back);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_modify_password = (EditText) findViewById(R.id.et_modify_password);
		et_modify_password.setVisibility(View.GONE);
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
			String url = API.ChangPassword_URL;
			String username = et_username.getText().toString().trim()==null?"":et_username.getText().toString().trim();
			String password = et_password.getText().toString().trim()==null?"":et_password.getText().toString().trim();
//			String modify_password = et_modify_password.getText().toString()
//					.trim()==null?"":et_modify_password.getText().toString().trim();
			get_change_password(ModifyPasswordActivity.this, url, username,
					password, /*modify_password,*/ new Messenger(handler));
			break;

		default:
			break;
		}
	}

}

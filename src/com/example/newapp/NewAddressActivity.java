package com.example.newapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.AddressAdapter;
import com.example.adapter.AddressAdapter.OnChooeseListener;
import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.bean.Address;
import com.example.utils.InfoUtils;

/**
 * 收货地址
 * 
 * @作者 陈籽屹
 * @时间 2016年6月7日
 */
public class NewAddressActivity extends BaseActivity implements OnClickListener {

	private ListView lv_get_address;
	AddressAdapter addressAdapter;
	private List<Address> listdata;
	SharedPreferences userSp;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				Toast.makeText(NewAddressActivity.this,
						msg.obj.toString().trim(), Toast.LENGTH_SHORT).show();
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
		setContentView(R.layout.activity_get_address);
		initView();
		getData();
	}

	private void initView() {
		// TODO 自动生成的方法存根
		initTitle();
		setTitle("收货地址");
		setImageVisiable();
		setAddVisiable();
		findViewById(R.id.tv_add).setOnClickListener(this);
		listdata = new ArrayList<>();
		userSp = getSharedPreferences("UserInfo", MODE_PRIVATE);
		lv_get_address = (ListView) findViewById(R.id.lv_get_address);
		addressAdapter = new AddressAdapter(listdata, NewAddressActivity.this);
		addressAdapter.setOnChooeseListener(new OnChooeseListener() {

			@Override
			public void onChooese(int position, Intent intent) {
				// TODO 自动生成的方法存根
				if (intent == null) {
					listdata.remove(position);
					addressAdapter.notifyDataSetChanged();

				} else {
					startActivityForResult(intent, 100);
				}
			}
		});
		lv_get_address.setAdapter(addressAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_add:
			Intent intent = new Intent();
			intent.putExtra("flag", "index");
			intent.setClass(NewAddressActivity.this, AddAddressActivity.class);
			startActivityForResult(intent, 100);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO 自动生成的方法存根
		super.onActivityResult(arg0, arg1, arg2);
		if (arg1 == 20) {
			// Bundle bun = arg2.getExtras();
			// Address address = (Address) bun.get("address");
			// listdata.add(address);
			// addressAdapter.notifyDataSetChanged();
		} else {
			// // Bundle bun = arg2.getExtras();
			// Address address = (Address) bun.get("address");
			// int position = bun.getInt("position");
			// listdata.remove(position);
			// listdata.add(address);
			// addressAdapter.notifyDataSetChanged();
		}
	}

	private void getData() {
		String url = API.ADDRESS_URL;
		String userName = userSp != null ? userSp.getString("username", "")
				: "";
		get_address(NewAddressActivity.this, url, userName, new Messenger(
				handler));
	}
}

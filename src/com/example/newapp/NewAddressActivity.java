package com.example.newapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.AddressAdapter;
import com.example.adapter.AddressAdapter.OnChooeseListener;
import com.example.api.API;
import com.example.base.BaseActivity;
import com.example.bean.Address;
import com.example.bean.Good;
import com.example.utils.InfoUtils;
import com.example.utils.JsonUtils;
import com.example.utils.SPutils;

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
	private List<Address> templist;
	SharedPreferences userSp;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case InfoUtils.INFO_SUCCESS:
				// Toast.makeText(NewAddressActivity.this,
				// msg.obj.toString().trim(), Toast.LENGTH_SHORT).show();
				listdata.clear();
				templist = (List<Address>) msg.getData()
						.getParcelableArrayList("list").get(0);
				listdata.addAll(templist);
				addressAdapter.notifyDataSetChanged();
				break;
			case InfoUtils.INFO_ERROR:

				break;
			case InfoUtils.CHANGE_ADDRESS_SUCCESS:
				Toast.makeText(NewAddressActivity.this,
						msg.obj.toString().trim(), Toast.LENGTH_SHORT).show();
				getData();
				break;
			case InfoUtils.CHANGE_ADDRESS_ERROR:

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
		templist = new ArrayList<>();
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
			// Intent intent = new Intent();
			// intent.putExtra("flag", "index");
			// intent.setClass(NewAddressActivity.this,
			// AddAddressActivity.class);
			// startActivityForResult(intent, 100);
			changeAddress();
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

	/**
	 * 修改地址
	 */
	public void changeAddress() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				NewAddressActivity.this);
		final EditText edit = new EditText(NewAddressActivity.this);
		ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		edit.setLayoutParams(lp);
//		edit.setBackground(null);
		// edit.setText(address == null ? "" : address);
		builder.setMessage("请输入地址").setView(edit)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						dialog.dismiss();
					};
				})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						String url = API.CHANGE_ADDRESS_URL;
						String userName = SPutils
								.getUserName(NewAddressActivity.this);
						String address = edit.getText().toString();
						try {
							get_change_address(NewAddressActivity.this, url,
									userName, URLEncoder.encode(address,"UTF-8"), new Messenger(handler));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}).setCancelable(false).create().show();
		;
	}
}
